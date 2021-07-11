package com.attijari.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

import com.attijari.domain.StaffPermission;
import com.attijari.domain.enumeration.ActionPermission;
import com.attijari.domain.enumeration.ScopePermission;
import com.attijari.domain.enumeration.StaffType;

/**
 * A DTO for the {@link StaffPermission} entity.
 */
public class StaffPermissionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idRole;

    @NotNull
    private Integer idBu;

    @NotNull
    private ActionPermission action;

    @NotNull
    private ScopePermission scopePermission;

    private StaffType staffType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdBu() {
        return idBu;
    }

    public void setIdBu(Integer idBu) {
        this.idBu = idBu;
    }

    public ActionPermission getAction() {
        return action;
    }

    public void setAction(ActionPermission action) {
        this.action = action;
    }

    public ScopePermission getScopePermission() {
        return scopePermission;
    }

    public void setScopePermission(ScopePermission scopePermission) {
        this.scopePermission = scopePermission;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StaffPermissionDTO)) {
            return false;
        }

        return id != null && id.equals(((StaffPermissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StaffPermissionDTO{" +
            "id=" + getId() +
            ", idRole=" + getIdRole() +
            ", idBu=" + getIdBu() +
            ", action='" + getAction() + "'" +
            ", scopePermission='" + getScopePermission() + "'" +
            ", staffType='" + getStaffType() + "'" +
            "}";
    }
}
