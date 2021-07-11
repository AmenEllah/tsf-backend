package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;


/**
 * A Country.
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name_fr")
    private String nameFR;

    @Column(name = "name_en")
    private String nameEN;

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

    public String getNameFR() {
        return nameFR;
    }

    public Country nameFR(String nameFR) {
        this.nameFR = nameFR;
        return this;
    }

    public void setNameFR(String nameFR) {
        this.nameFR = nameFR;
    }

    public String getNameEN() {
        return nameEN;
    }

    public Country nameEN(String nameEN) {
        this.nameEN = nameEN;
        return this;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getCode() {
        return code;
    }

    public Country code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + id +
            ", nameFR='" + nameFR + '\'' +
            ", nameEN='" + nameEN + '\'' +
            ", code='" + code + '\'' +
            ", flag='" + flag + '\'' +
            '}';
    }
}
