package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.StaffPermission;
import com.attijari.domain.enumeration.ActionPermission;
import com.attijari.domain.enumeration.ScopePermission;
import com.attijari.domain.enumeration.StaffType;
import com.attijari.web.rest.StaffPermissionResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;

/**
 * Criteria class for the {@link StaffPermission} entity. This class is used
 * in {@link StaffPermissionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /staff-permissions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StaffPermissionCriteria implements Serializable, Criteria {
    /**
     * Class for filtering ActionPermission
     */
    public static class ActionPermissionFilter extends Filter<ActionPermission> {

        public ActionPermissionFilter() {
        }

        public ActionPermissionFilter(ActionPermissionFilter filter) {
            super(filter);
        }

        @Override
        public ActionPermissionFilter copy() {
            return new ActionPermissionFilter(this);
        }

    }
    /**
     * Class for filtering ScopePermission
     */
    public static class ScopePermissionFilter extends Filter<ScopePermission> {

        public ScopePermissionFilter() {
        }

        public ScopePermissionFilter(ScopePermissionFilter filter) {
            super(filter);
        }

        @Override
        public ScopePermissionFilter copy() {
            return new ScopePermissionFilter(this);
        }

    }
    /**
     * Class for filtering StaffType
     */
    public static class StaffTypeFilter extends Filter<StaffType> {

        public StaffTypeFilter() {
        }

        public StaffTypeFilter(StaffTypeFilter filter) {
            super(filter);
        }

        @Override
        public StaffTypeFilter copy() {
            return new StaffTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter idRole;

    private IntegerFilter idBu;

    private ActionPermissionFilter action;

    private ScopePermissionFilter scopePermission;

    private StaffTypeFilter staffType;

    public StaffPermissionCriteria() {
    }

    public StaffPermissionCriteria(StaffPermissionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.idRole = other.idRole == null ? null : other.idRole.copy();
        this.idBu = other.idBu == null ? null : other.idBu.copy();
        this.action = other.action == null ? null : other.action.copy();
        this.scopePermission = other.scopePermission == null ? null : other.scopePermission.copy();
        this.staffType = other.staffType == null ? null : other.staffType.copy();
    }

    @Override
    public StaffPermissionCriteria copy() {
        return new StaffPermissionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getIdRole() {
        return idRole;
    }

    public void setIdRole(IntegerFilter idRole) {
        this.idRole = idRole;
    }

    public IntegerFilter getIdBu() {
        return idBu;
    }

    public void setIdBu(IntegerFilter idBu) {
        this.idBu = idBu;
    }

    public ActionPermissionFilter getAction() {
        return action;
    }

    public void setAction(ActionPermissionFilter action) {
        this.action = action;
    }

    public ScopePermissionFilter getScopePermission() {
        return scopePermission;
    }

    public void setScopePermission(ScopePermissionFilter scopePermission) {
        this.scopePermission = scopePermission;
    }

    public StaffTypeFilter getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffTypeFilter staffType) {
        this.staffType = staffType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StaffPermissionCriteria that = (StaffPermissionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(idRole, that.idRole) &&
            Objects.equals(idBu, that.idBu) &&
            Objects.equals(action, that.action) &&
            Objects.equals(scopePermission, that.scopePermission) &&
            Objects.equals(staffType, that.staffType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        idRole,
        idBu,
        action,
        scopePermission,
        staffType
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StaffPermissionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idRole != null ? "idRole=" + idRole + ", " : "") +
                (idBu != null ? "idBu=" + idBu + ", " : "") +
                (action != null ? "action=" + action + ", " : "") +
                (scopePermission != null ? "scopePermission=" + scopePermission + ", " : "") +
                (staffType != null ? "staffType=" + staffType + ", " : "") +
            "}";
    }

}
