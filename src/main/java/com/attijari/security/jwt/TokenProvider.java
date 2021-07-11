package com.attijari.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import com.attijari.domain.enumeration.StaffType;
import com.attijari.security.UserDetailsLogging;
import com.attijari.service.StaffPermissionQueryService;
import com.attijari.service.dto.ActiveStaffDTO;
import com.attijari.service.dto.StaffPermissionCriteria;
import com.attijari.service.dto.StaffPermissionDTO;
import com.attijari.service.dto.SubcontractingStaffDTO;
import io.github.jhipster.service.filter.IntegerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.github.jhipster.config.JHipsterProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    private final JHipsterProperties jHipsterProperties;

    private final StaffPermissionQueryService staffPermissionQueryService;

    public TokenProvider(JHipsterProperties jHipsterProperties, StaffPermissionQueryService staffPermissionQueryService) {
        this.jHipsterProperties = jHipsterProperties;
        this.staffPermissionQueryService = staffPermissionQueryService;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
        if (!StringUtils.isEmpty(secret)) {
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds =
            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt()
                .getTokenValidityInSecondsForRememberMe();
    }
    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public String createToken(Authentication authentication, boolean rememberMe, ActiveStaffDTO activeStaffDTO) {
        Map<String, Object> staffAuthorities = new HashMap<>();
        if (activeStaffDTO != null) {

            StaffPermissionCriteria staffPermissionCriteria = new StaffPermissionCriteria();
            staffPermissionCriteria.setIdRole((IntegerFilter) new IntegerFilter().setEquals(activeStaffDTO.getIdRole()));
            List<StaffPermissionDTO> staffPermissionDTOS = staffPermissionQueryService.findByCriteria(staffPermissionCriteria);
            staffPermissionDTOS.removeIf(staffPermissionDTO1 -> staffPermissionDTO1.getIdBu() != null && !staffPermissionDTO1.getIdBu().equals(activeStaffDTO.getIdBu()));

            staffAuthorities.put("mat", activeStaffDTO.getMatricule());
            staffAuthorities.put("idRole", activeStaffDTO.getIdRole());
            staffAuthorities.put("idPoste", activeStaffDTO.getIdPoste());
            staffAuthorities.put("idBU", activeStaffDTO.getIdBu());
            if (!staffPermissionDTOS.isEmpty()) {
                staffAuthorities.put("staffRole", staffPermissionDTOS.get(0).getStaffType());
            } else {
                staffAuthorities.put("staffRole", null);
            }
        }
        return buildToken(authentication, rememberMe, staffAuthorities);
    }

    public String createToken(Authentication authentication, boolean rememberMe, SubcontractingStaffDTO subcontractingStaffDTO) {
        Map<String, Object> staffAuthorities = new HashMap<>();
        if (subcontractingStaffDTO != null) {

            StaffPermissionCriteria staffPermissionCriteria = new StaffPermissionCriteria();
            staffPermissionCriteria.setIdRole((IntegerFilter) new IntegerFilter().setEquals(subcontractingStaffDTO.getIdRole()));
            List<StaffPermissionDTO> staffPermissionDTOS = staffPermissionQueryService.findByCriteria(staffPermissionCriteria);
            staffPermissionDTOS.removeIf(staffPermissionDTO1 -> staffPermissionDTO1.getIdBu() != null && !staffPermissionDTO1.getIdBu().equals(subcontractingStaffDTO.getIdBu()));

            staffAuthorities.put("mat", subcontractingStaffDTO.getMatricule());
            staffAuthorities.put("idRole", subcontractingStaffDTO.getIdRole());
            staffAuthorities.put("idPoste", subcontractingStaffDTO.getIdPoste());
            staffAuthorities.put("idBU", subcontractingStaffDTO.getIdBu());
            if (!staffPermissionDTOS.isEmpty()) {
                staffAuthorities.put("staffRole", staffPermissionDTOS.get(0).getStaffType());
            } else {
                staffAuthorities.put("staffRole", null);
            }
        }
        return buildToken(authentication, rememberMe, staffAuthorities);
    }

    private String buildToken(Authentication authentication, boolean rememberMe, Map<String, Object> staffAuthorities) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .addClaims(staffAuthorities)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetailsLogging principal = new UserDetailsLogging(claims.getSubject(), "", authorities, (Integer)  claims.get("mat"), null,(Integer) claims.get("idBU") , (Integer)  claims.get("idRole"), (Integer)  claims.get("idPoste"), claims.get("staffRole")!= null? StaffType.valueOf((String) claims.get("staffRole")): null);


        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
}
