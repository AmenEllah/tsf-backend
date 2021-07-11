package com.attijari.security;

import com.attijari.domain.enumeration.StaffType;
import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserDetails extends UserDetails {

    Integer getMat();

    String getAgencyCode();

    Integer getIdBU();

    Integer getIdRole();

    Integer getIdPoste();

    StaffType getStaffType();
}
