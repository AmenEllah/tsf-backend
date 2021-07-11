package com.attijari.web.rest;

import com.attijari.service.dto.*;
import com.attijari.web.rest.errors.InvalidTokenException;
import com.attijari.config.optConfig.MiddleWareService;
import com.attijari.config.optConfig.UserAD;
import com.attijari.domain.AccessToken;
import com.attijari.domain.Authority;
import com.attijari.domain.User;
import com.attijari.repository.AccessTokenRepository;
import com.attijari.repository.AuthorityRepository;
import com.attijari.repository.UserRepository;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.security.jwt.JWTFilter;
import com.attijari.security.jwt.TokenProvider;
import com.attijari.service.ActiveStaffQueryService;
import com.attijari.service.RequestService;
import com.attijari.service.SubcontractingStaffQueryService;
import com.attijari.web.rest.vm.LoginVM;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final MiddleWareService middleWareService;

    private final ActiveStaffQueryService activeStaffQueryService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final RequestService requestService;

    private final SubcontractingStaffQueryService subcontractingStaffQueryService;

    private final AccessTokenRepository accessTokenRepository;


    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, MiddleWareService middleWareService, ActiveStaffQueryService activeStaffQueryService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, RequestService requestService, SubcontractingStaffQueryService subcontractingStaffQueryService, AccessTokenRepository accessTokenRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.middleWareService = middleWareService;
        this.activeStaffQueryService = activeStaffQueryService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.requestService = requestService;
        this.subcontractingStaffQueryService = subcontractingStaffQueryService;
        this.accessTokenRepository = accessTokenRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody(required = false) LoginVM loginVM, @RequestParam(name = "access_token", required = false) String accessToken) {
        UserAD userAD = middleWareService.authenticationAD(loginVM.getUsername(), loginVM.getPassword());
        String jwt = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        List<ActiveStaffDTO> activeStaffDTOs = new ArrayList<>();
        List<SubcontractingStaffDTO> subcontractingStaffDTOS = new ArrayList<>();
        boolean rememberMe = loginVM.isRememberMe() != null && loginVM.isRememberMe();
        if (userAD != null) {
            ActiveStaffCriteria activeStaffCriteria = new ActiveStaffCriteria();
            activeStaffCriteria.setMatricule((IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(userAD.getEmployeeId())));
            activeStaffDTOs = activeStaffQueryService.findByCriteria(activeStaffCriteria);
            if(!activeStaffDTOs.isEmpty()){
                updateStaffAD(userAD, loginVM.getPassword(), activeStaffDTOs);
            } else {
                SubcontractingStaffCriteria subcontractingStaffCriteria = new SubcontractingStaffCriteria();
                subcontractingStaffCriteria.setMatricule((IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(userAD.getEmployeeId())));
                subcontractingStaffDTOS = this.subcontractingStaffQueryService.findByCriteria(subcontractingStaffCriteria);
                if(!subcontractingStaffDTOS.isEmpty()){
                    updateSubStaffAD(userAD, loginVM.getPassword(), subcontractingStaffDTOS);
                }
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (!activeStaffDTOs.isEmpty()) {
            jwt = tokenProvider.createToken(authentication, rememberMe, activeStaffDTOs.get(0));

        } else if(!subcontractingStaffDTOS.isEmpty()) {
            jwt = tokenProvider.createToken(authentication, rememberMe, subcontractingStaffDTOS.get(0));
        }
        else {
            jwt = tokenProvider.createToken(authentication, rememberMe);
        }
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/authenticate-token")
    public ResponseEntity<JWTToken> authorizeByToken(@RequestParam(name = "access_token") String accessToken) {

        Optional<User> existingUser = Optional.empty();
        Optional<AccessToken> existingAccessToken = accessTokenRepository.findById(accessToken);
        String jwt = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        if (existingAccessToken.isPresent() && existingAccessToken.get().getUser() != null) {
            existingUser = Optional.of(existingAccessToken.get().getUser());
            existingAccessToken.get().setNumberOfUse(existingAccessToken.get().getNumberOfUse() + 1);
            accessTokenRepository.save(existingAccessToken.get());
            String newPassword = RandomStringUtils.random(6, false, true);
            existingUser.get().setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(existingUser.get());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(existingUser.get().getEmail(), newPassword);
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = tokenProvider.createToken(authentication, true);
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        } else {
            throw new InvalidTokenException();
        }
    }

    @PostMapping("/authenticate-by-request")
    public ResponseEntity<JWTToken> authorizeByReference(@Valid @RequestBody LoginRP loginRP) {
        Optional<RequestDTO> optionalRequestDTO = requestService.findOne(loginRP.getRequestId());
        if (optionalRequestDTO.isPresent() && optionalRequestDTO.get().getUserId() != null) {
            LoginVM loginVM = new LoginVM();
            loginVM.setUsername(optionalRequestDTO.get().getPersonalInfo().getEmail());
            loginVM.setPassword(loginRP.getPassword());
            loginVM.setRememberMe(false);
            return authorize(loginVM, null);
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    private User updateStaffAD(UserAD userAD, String password, List<ActiveStaffDTO> activeStaffDTOs) {
        User user = createUser(userAD, password);
        if (!activeStaffDTOs.isEmpty()) {
            user.setEmail(activeStaffDTOs.get(0).getEmail());
        }
        return userRepository.save(user);
    }

    private User updateSubStaffAD(UserAD userAD, String password, List<SubcontractingStaffDTO> subcontractingStaffDTOS) {
        User user = createUser(userAD, password);
        if (!subcontractingStaffDTOS.isEmpty()) {
            user.setEmail(subcontractingStaffDTOS.get(0).getEmail());
        }
        return userRepository.save(user);
    }

    private User createUser(UserAD userAD, String password) {
        Optional<User> existingUser = userRepository.findOneByLogin(StringUtils.lowerCase(userAD.getSamAccountName(), Locale.ENGLISH));
        User user = new User();
        existingUser.ifPresent(value -> user.setId(value.getId()));
        user.setLogin(StringUtils.lowerCase(userAD.getSamAccountName(), Locale.ENGLISH));
        user.setPassword(passwordEncoder.encode(password));
        if (userAD.getDisplayName().contains(" ")) {
            user.setFirstName(userAD.getDisplayName().substring(0, userAD.getDisplayName().indexOf(" ")));
            user.setLastName(userAD.getDisplayName().substring(userAD.getDisplayName().indexOf(" ") + 1));
        } else if (userAD.getDisplayName().contains(".")) {
            user.setFirstName(userAD.getDisplayName().substring(0, userAD.getDisplayName().indexOf(".")));
            user.setLastName(userAD.getDisplayName().substring(userAD.getDisplayName().indexOf(".") + 1));
        } else {
            user.setFirstName(userAD.getDisplayName());
            user.setLastName("");
        }
        user.setActivated(true);
        user.setMatricule(Integer.valueOf(userAD.getEmployeeId()));
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.STAFF).ifPresent(authorities::add);
        user.setAuthorities(authorities);
        return user;
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

    static class LoginRP {
        private Long requestId;
        private String password;

        public Long getRequestId() {
            return requestId;
        }

        public void setRequestId(Long requestId) {
            this.requestId = requestId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
