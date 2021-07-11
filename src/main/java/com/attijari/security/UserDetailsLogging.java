package com.attijari.security;

import com.attijari.domain.enumeration.StaffType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsLogging extends User implements MyUserDetails {

    private final Integer mat;

    private final String agencyCode;

    private final Integer idBU;

    private final Integer idRole;

    private final Integer idPoste;

    private StaffType staffType;

    public UserDetailsLogging(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer mat, String agencyCode, Integer idBU, Integer idRole, Integer idPoste, StaffType staffType) {
        super(username, password, true, true, true, true, authorities);
        this.mat = mat;
        this.agencyCode = agencyCode;
        this.idBU = idBU;
        this.idRole = idRole;
        this.idPoste = idPoste;
        this.staffType = staffType;
    }

    public UserDetailsLogging(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Integer mat, String agencyCode, Integer idBU, Integer idRole, Integer idPoste, StaffType staffType) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.mat = mat;
        this.agencyCode = agencyCode;
        this.idBU = idBU;
        this.idRole = idRole;
        this.idPoste = idPoste;
        this.staffType = staffType;
    }


    @Override
    public Integer getMat() {
        return mat;
    }

    @Override
    public String getAgencyCode() {
        return agencyCode;
    }

    @Override
    public Integer getIdBU() {
        return idBU;
    }

    @Override
    public Integer getIdRole() {
        return idRole;
    }

    @Override
    public Integer getIdPoste() {
        return idPoste;
    }

    @Override
    public StaffType getStaffType() {
        return staffType;
    }
}
