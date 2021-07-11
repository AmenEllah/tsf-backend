package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.SubcontractingStaff;
import com.attijari.web.rest.SubcontractingStaffResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SubcontractingStaff} entity. This class is used
 * in {@link SubcontractingStaffResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /subcontracting-staffs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SubcontractingStaffCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter matricule;

    private IntegerFilter idBu;

    private IntegerFilter idRole;

    private StringFilter email;

    private IntegerFilter idPoste;

    public SubcontractingStaffCriteria() {
    }

    public SubcontractingStaffCriteria(SubcontractingStaffCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.matricule = other.matricule == null ? null : other.matricule.copy();
        this.idBu = other.idBu == null ? null : other.idBu.copy();
        this.idRole = other.idRole == null ? null : other.idRole.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.idPoste = other.idPoste == null ? null : other.idPoste.copy();
    }

    @Override
    public SubcontractingStaffCriteria copy() {
        return new SubcontractingStaffCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getMatricule() {
        return matricule;
    }

    public void setMatricule(IntegerFilter matricule) {
        this.matricule = matricule;
    }

    public IntegerFilter getIdBu() {
        return idBu;
    }

    public void setIdBu(IntegerFilter idBu) {
        this.idBu = idBu;
    }

    public IntegerFilter getIdRole() {
        return idRole;
    }

    public void setIdRole(IntegerFilter idRole) {
        this.idRole = idRole;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public IntegerFilter getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(IntegerFilter idPoste) {
        this.idPoste = idPoste;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SubcontractingStaffCriteria that = (SubcontractingStaffCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(matricule, that.matricule) &&
            Objects.equals(idBu, that.idBu) &&
            Objects.equals(idRole, that.idRole) &&
            Objects.equals(email, that.email) &&
            Objects.equals(idPoste, that.idPoste);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        matricule,
        idBu,
        idRole,
        email,
        idPoste
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubcontractingStaffCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (matricule != null ? "matricule=" + matricule + ", " : "") +
                (idBu != null ? "idBu=" + idBu + ", " : "") +
                (idRole != null ? "idRole=" + idRole + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (idPoste != null ? "idPoste=" + idPoste + ", " : "") +
            "}";
    }

}
