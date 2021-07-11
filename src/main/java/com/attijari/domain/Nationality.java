package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Nationality.
 */
@Entity
@Table(name = "nationality")
public class Nationality implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_fr")
    private String libelleFR;

    @Column(name = "libelle_en")
    private String libelleEN;

    @Column(name = "code")
    private String code;

    @Column(name = "flag")
    private String flag;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleFR() {
        return libelleFR;
    }

    public Nationality libelleFR(String libelleFR) {
        this.libelleFR = libelleFR;
        return this;
    }

    public void setLibelleFR(String libelleFR) {
        this.libelleFR = libelleFR;
    }

    public String getLibelleEN() {
        return libelleEN;
    }

    public Nationality libelleEN(String libelleEN) {
        this.libelleEN = libelleEN;
        return this;
    }

    public void setLibelleEN(String libelleEN) {
        this.libelleEN = libelleEN;
    }

    public String getCode() {
        return code;
    }

    public Nationality code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public Nationality flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nationality)) {
            return false;
        }
        return id != null && id.equals(((Nationality) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Nationality{" +
            "id=" + getId() +
            ", libelleFR='" + getLibelleFR() + "'" +
            ", libelleEN='" + getLibelleEN() + "'" +
            ", code='" + getCode() + "'" +
            ", flag='" + getFlag() + "'" +
            "}";
    }
}
