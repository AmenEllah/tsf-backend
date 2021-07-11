package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A AdressInfo.
 */
@Entity
@Table(name = "adress_info")
public class AdressInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "city")
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonalInfo personalInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public AdressInfo address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZip() {
        return zip;
    }

    public AdressInfo zip(Integer zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public AdressInfo city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public AdressInfo personalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
        return this;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdressInfo)) {
            return false;
        }
        return id != null && id.equals(((AdressInfo) o).id);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdressInfo{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", zip=" + getZip() +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }
}
