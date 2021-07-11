package com.attijari.service.dto;

import com.attijari.domain.SubcontractingStaff;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link SubcontractingStaff} entity.
 */
public class SubcontractingStaffDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer matricule;

    @NotNull
    private Integer idBu;

    @NotNull
    private Integer idRole;

    private String email;

    private Integer idPoste;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        if (!(o instanceof SubcontractingStaffDTO)) {
            return false;
        }

        return id != null && id.equals(((SubcontractingStaffDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubcontractingStaffDTO{" +
            "id=" + getId() +
            ", matricule=" + getMatricule() +
            ", idBu=" + getIdBu() +
            ", idRole=" + getIdRole() +
            ", email='" + getEmail() + "'" +
            ", idPoste=" + getIdPoste() +
            "}";
    }
}
