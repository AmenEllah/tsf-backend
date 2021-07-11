package com.attijari.security;

import com.attijari.domain.ActiveStaff;
import com.attijari.domain.enumeration.StaffType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (MyUserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }


    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user.
     */
    public static Optional<String> getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .filter(authentication -> authentication.getCredentials() instanceof String)
            .map(authentication -> (String) authentication.getCredentials());
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
            getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the {@code isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean isCurrentUserInRole(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
            getAuthorities(authentication).anyMatch(authority::equals);
    }

    public static Optional<ActiveStaff> getStaffDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails springSecurityUser = (MyUserDetails) authentication.getPrincipal();
        if (springSecurityUser.getIdBU() == null && springSecurityUser.getIdPoste() == null && springSecurityUser.getIdRole() == null && springSecurityUser.getMat() == null){
            return Optional.empty();
        } else {
            ActiveStaff activeStaff = new ActiveStaff();
            activeStaff.setIdBu(springSecurityUser.getIdBU());
            activeStaff.setIdRole(springSecurityUser.getIdRole());
            activeStaff.setIdPoste(springSecurityUser.getIdPoste());
            activeStaff.setMatricule(springSecurityUser.getMat());
            return Optional.of(activeStaff);
        }
    }

    public static Optional<StaffType> getStaffType() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails springSecurityUser = (MyUserDetails) authentication.getPrincipal();
        return Optional.ofNullable(springSecurityUser.getStaffType());
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority);
    }

}
