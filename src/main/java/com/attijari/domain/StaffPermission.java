package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

import com.attijari.domain.enumeration.ActionPermission;
import com.attijari.domain.enumeration.ScopePermission;
import com.attijari.domain.enumeration.StaffType;

/**
 * A StaffPermission.
 */
@Entity
@Table(name = "staff_permission")
public class StaffPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "id_bu")
    private Integer idBu;

    @Enumerated(EnumType.STRING)
    @Column(name = "action")
    private ActionPermission action;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_permission")
    private ScopePermission scopePermission;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_type")
    private StaffType staffType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public StaffPermission idRole(Integer idRole) {
        this.idRole = idRole;
        return this;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdBu() {
        return idBu;
    }

    public StaffPermission idBu(Integer idBu) {
        this.idBu = idBu;
        return this;
    }

    public void setIdBu(Integer idBu) {
        this.idBu = idBu;
    }

    public ActionPermission getAction() {
        return action;
    }

    public StaffPermission action(ActionPermission action) {
        this.action = action;
        return this;
    }

    public void setAction(ActionPermission action) {
        this.action = action;
    }

    public ScopePermission getScopePermission() {
        return scopePermission;
    }

    public StaffPermission scopePermission(ScopePermission scopePermission) {
        this.scopePermission = scopePermission;
        return this;
    }

    public void setScopePermission(ScopePermission scopePermission) {
        this.scopePermission = scopePermission;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public StaffPermission staffType(StaffType staffType) {
        this.staffType = staffType;
        return this;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StaffPermission)) {
            return false;
        }
        return id != null && id.equals(((StaffPermission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StaffPermission{" +
            "id=" + getId() +
            ", idRole=" + getIdRole() +
            ", idBu=" + getIdBu() +
            ", action='" + getAction() + "'" +
            ", scopePermission='" + getScopePermission() + "'" +
            ", staffType='" + getStaffType() + "'" +
            "}";
    }
}
