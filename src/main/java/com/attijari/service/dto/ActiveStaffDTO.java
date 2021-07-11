package com.attijari.service.dto;

import com.attijari.domain.ActiveStaff;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link ActiveStaff} entity.
 */
public class ActiveStaffDTO implements Serializable {

    @NotNull
    private Integer matricule;

    @NotNull
    private Integer idBu;

    @NotNull
    private Integer idRole;

    private String email;

    @NotNull
    private Integer idPoste;

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Integer getIdBu() {
        return idBu;
    }

    public void setIdBu(Integer idBu) {
        this.idBu = idBu;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActiveStaffDTO)) {
            return false;
        }

        return matricule != null && matricule.equals(((ActiveStaffDTO) o).matricule);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActiveStaffDTO{" +
            ", matricule=" + getMatricule() +
            ", idBu=" + getIdBu() +
            ", idRole=" + getIdRole() +
            ", email='" + getEmail() + "'" +
            ", idPoste=" + getIdPoste() +
            "}";
    }
}
