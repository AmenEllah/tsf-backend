package com.attijari.service.dto;

import com.attijari.domain.AdressInfo;

import java.io.Serializable;

/**
 * A DTO for the {@link AdressInfo} entity.
 */
public class AdressInfoDTO implements Serializable {

    private Long id;

    private String address;

    private Integer zip;

    private String city;

    private CountryDTO country;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdressInfoDTO)) {
            return false;
        }

        return id != null && id.equals(((AdressInfoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdressInfoDTO{" +
            "id=" + id +
            ", address='" + address + '\'' +
            ", zip=" + zip +
            ", city='" + city + '\'' +
            ", country=" + country +
            '}';
    }
}
