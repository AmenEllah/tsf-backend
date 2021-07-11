package com.attijari.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SubcontractingStaff.
 */
@Entity
@Table(name = "subcontracting_staff")
public class SubcontractingStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "matricule", nullable = false, unique = true)
    private Integer matricule;

    @NotNull
    @Column(name = "id_bu", nullable = false)
    private Integer idBu;

    @NotNull
    @Column(name = "id_role", nullable = false)
    private Integer idRole;

    @Column(name = "email")
    private String email;

    @Column(name = "id_poste")
    private Integer idPoste;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public SubcontractingStaff matricule(Integer matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Integer getIdBu() {
        return idBu;
    }

    public SubcontractingStaff idBu(Integer idBu) {
        this.idBu = idBu;
        return this;
    }

    public void setIdBu(Integer idBu) {
        this.idBu = idBu;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public SubcontractingStaff idRole(Integer idRole) {
        this.idRole = idRole;
        return this;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getEmail() {
        return email;
    }

    public SubcontractingStaff email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public SubcontractingStaff idPoste(Integer idPoste) {
        this.idPoste = idPoste;
        return this;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubcontractingStaff)) {
            return false;
        }
        return id != null && id.equals(((SubcontractingStaff) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubcontractingStaff{" +
            "id=" + getId() +
            ", matricule=" + getMatricule() +
            ", idBu=" + getIdBu() +
            ", idRole=" + getIdRole() +
            ", email='" + getEmail() + "'" +
            ", idPoste=" + getIdPoste() +
            "}";
    }
}
