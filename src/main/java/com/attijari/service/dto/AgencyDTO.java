package com.attijari.service.dto;

import com.attijari.domain.Agency;

import java.io.Serializable;

/**
 * A DTO for the {@link Agency} entity.
 */
public class AgencyDTO implements Serializable {

    private Long id;

    private String name;

    private String address;

    private Integer zip;

    private String city;

    private String code;

    private Long municipalityId;

    private MunicipalityDTO municipality;

    private String managerCode;

    private String managerLib;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
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

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
    }

    public MunicipalityDTO getMunicipality() {
        return municipality;
    }

    public void setMunicipality(MunicipalityDTO municipality) {
        this.municipality = municipality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgencyDTO)) {
            return false;
        }

        return id != null && id.equals(((AgencyDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AgencyDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", zip=" + zip +
            ", city='" + city + '\'' +
            ", code='" + code + '\'' +
            ", municipalityId=" + municipalityId +
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
