package com.attijari.domain;

import javax.annotation.Nullable;
import javax.persistence.*;

import java.io.Serializable;

/**
 * A Agency.
 */
@Entity
@Table(name = "agency")
public class Agency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    @Nullable
    @Column(name = "name")
    private String name;
    @Nullable
    @Column(name = "address")
    private String address;
    @Nullable
    @Column(name = "zip")
    private Integer zip;
    @Nullable
    @Column(name = "city")
    private String city;

    @Column(name = "code")
    private String code;

    @Column(name = "manager_code")
    private String managerCode;

    @Column(name = "manager_lib")
    private String managerLib;

    @ManyToOne(fetch = FetchType.EAGER)
    private Municipality municipality;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Agency name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Agency address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZip() {
        return zip;
    }

    public Agency zip(Integer zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public Agency city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public Agency municipality(Municipality municipality) {
        this.municipality = municipality;
        return this;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agency)) {
            return false;
        }
        return id != null && id.equals(((Agency) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Agency{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", zip=" + zip +
            ", city='" + city + '\'' +
            ", code='" + code + '\'' +
            ", municipality=" + municipality +
            '}';
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getManagerLib() {
        return managerLib;
    }

    public void setManagerLib(String managerLib) {
        this.managerLib = managerLib;
    }
}
