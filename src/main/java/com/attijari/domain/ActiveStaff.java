package com.attijari.domain;


import org.hibernate.annotations.Immutable;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ActiveStaff.
 */
@Entity
@Table(name = "AGENTS_ACTIFS")
@Immutable
public class ActiveStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "matricule", nullable = false)
    private Integer matricule;

    @Column(name = "id_bu")
    private Integer idBu;

    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "email")
    private String email;

    @Column(name = "id_poste")
    private Integer idPoste;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getMatricule() {
        return matricule;
    }

    public ActiveStaff matricule(Integer matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Integer getIdBu() {
        return idBu;
    }

    public ActiveStaff idBu(Integer idBu) {
        this.idBu = idBu;
        return this;
    }

    public void setIdBu(Integer idBu) {
        this.idBu = idBu;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public ActiveStaff idRole(Integer idRole) {
        this.idRole = idRole;
        return this;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getEmail() {
        return email;
    }

    public ActiveStaff email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public ActiveStaff idPoste(Integer idPoste) {
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
        if (!(o instanceof ActiveStaff)) {
            return false;
        }
        return matricule != null && matricule.equals(((ActiveStaff) o).matricule);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActiveStaff{" +
            ", matricule=" + getMatricule() +
            ", idBu=" + getIdBu() +
            ", idRole=" + getIdRole() +
            ", email='" + getEmail() + "'" +
            ", idPoste=" + getIdPoste() +
            "}";
    }
}
