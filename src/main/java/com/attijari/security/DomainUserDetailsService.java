package com.attijari.security;

import com.attijari.domain.User;
import com.attijari.service.ActiveStaffQueryService;
import com.attijari.service.StaffPermissionQueryService;
import com.attijari.service.SubcontractingStaffQueryService;
import com.attijari.service.dto.*;
import com.attijari.repository.UserRepository;
import io.github.jhipster.service.filter.IntegerFilter;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    private final ActiveStaffQueryService activeStaffQueryService;

    private final StaffPermissionQueryService staffPermissionQueryService;

    private final SubcontractingStaffQueryService subcontractingStaffQueryService;

    public DomainUserDetailsService(UserRepository userRepository, ActiveStaffQueryService activeStaffQueryService, StaffPermissionQueryService staffPermissionQueryService, SubcontractingStaffQueryService subcontractingStaffQueryService) {
        this.userRepository = userRepository;
        this.activeStaffQueryService = activeStaffQueryService;
        this.staffPermissionQueryService = staffPermissionQueryService;
        this.subcontractingStaffQueryService = subcontractingStaffQueryService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

    }

    private UserDetailsLogging createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.getActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        ActiveStaffDTO activeStaffDTO = null;
        SubcontractingStaffDTO subcontractingStaffDTO = null;
        if (user.getMatricule() != null) {
            ActiveStaffCriteria activeStaffCriteria = new ActiveStaffCriteria();
            activeStaffCriteria.setMatricule((IntegerFilter) new IntegerFilter().setEquals(user.getMatricule()));
            List<ActiveStaffDTO> activeStaffDTOS = this.activeStaffQueryService.findByCriteria(activeStaffCriteria);
            if(!activeStaffDTOS.isEmpty()){
                activeStaffDTO = activeStaffDTOS.get(0);
            } else {
                SubcontractingStaffCriteria subcontractingStaffCriteria = new SubcontractingStaffCriteria();
                subcontractingStaffCriteria.setMatricule((IntegerFilter) new IntegerFilter().setEquals(user.getMatricule()));
                List<SubcontractingStaffDTO> subcontractingStaffDTOS = this.subcontractingStaffQueryService.findByCriteria(subcontractingStaffCriteria);
                if(!subcontractingStaffDTOS.isEmpty()){
                    subcontractingStaffDTO = subcontractingStaffDTOS.get(0);
                }
            }
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        if (activeStaffDTO != null) {
            Integer idBu = activeStaffDTO.getIdBu();
            StaffPermissionCriteria staffPermissionCriteria = new StaffPermissionCriteria();
            staffPermissionCriteria.setIdRole((IntegerFilter) new IntegerFilter().setEquals(activeStaffDTO.getIdRole()));
            List<StaffPermissionDTO> staffPermissionDTOS = staffPermissionQueryService.findByCriteria(staffPermissionCriteria);
            staffPermissionDTOS.removeIf(staffPermissionDTO1 -> staffPermissionDTO1.getIdBu() != null && !staffPermissionDTO1.getIdBu().equals(idBu));
            if (!staffPermissionDTOS.isEmpty()) {
                return new UserDetailsLogging(user.getLogin(), user.getPassword(), grantedAuthorities, activeStaffDTO.getMatricule(), null, activeStaffDTO.getIdBu(), activeStaffDTO.getIdRole(), activeStaffDTO.getIdPoste(), staffPermissionDTOS.get(0).getStaffType());
            } else {
                return new UserDetailsLogging(user.getLogin(), user.getPassword(), grantedAuthorities, activeStaffDTO.getMatricule(), null, activeStaffDTO.getIdBu(), activeStaffDTO.getIdRole(), activeStaffDTO.getIdPoste(),null);
            }

        }
        else if (subcontractingStaffDTO != null) {
            Integer idBu = subcontractingStaffDTO.getIdBu();
            StaffPermissionCriteria staffPermissionCriteria = new StaffPermissionCriteria();
            staffPermissionCriteria.setIdRole((IntegerFilter) new IntegerFilter().setEquals(subcontractingStaffDTO.getIdRole()));
            List<StaffPermissionDTO> staffPermissionDTOS = staffPermissionQueryService.findByCriteria(staffPermissionCriteria);
            staffPermissionDTOS.removeIf(staffPermissionDTO1 -> staffPermissionDTO1.getIdBu() != null && !staffPermissionDTO1.getIdBu().equals(idBu));
            if (!staffPermissionDTOS.isEmpty()) {
                return new UserDetailsLogging(user.getLogin(), user.getPassword(), grantedAuthorities, subcontractingStaffDTO.getMatricule(), null, subcontractingStaffDTO.getIdBu(), subcontractingStaffDTO.getIdRole(), subcontractingStaffDTO.getIdPoste(), staffPermissionDTOS.get(0).getStaffType());
            } else {
                return new UserDetailsLogging(user.getLogin(), user.getPassword(), grantedAuthorities, subcontractingStaffDTO.getMatricule(), null, subcontractingStaffDTO.getIdBu(), subcontractingStaffDTO.getIdRole(), subcontractingStaffDTO.getIdPoste(),null);
            }

        }else {
            return new UserDetailsLogging(user.getLogin(), user.getPassword(), true, true, true, true, grantedAuthorities, null, null, null, null, null, null);
        }
    }
}
