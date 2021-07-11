package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.AdressInfo;
import com.attijari.web.rest.AdressInfoResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link AdressInfo} entity. This class is used
 * in {@link AdressInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /adress-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AdressInfoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter address;

    private IntegerFilter zip;

    private StringFilter city;

    private LongFilter personalInfoId;

    private LongFilter countryId;

    public AdressInfoCriteria() {
    }

    public AdressInfoCriteria(AdressInfoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.zip = other.zip == null ? null : other.zip.copy();
        this.city = other.city == null ? null : other.city.copy();
        this.personalInfoId = other.personalInfoId == null ? null : other.personalInfoId.copy();
        this.countryId = other.countryId == null ? null : other.countryId.copy();
    }

    @Override
    public AdressInfoCriteria copy() {
        return new AdressInfoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public IntegerFilter getZip() {
        return zip;
    }

    public void setZip(IntegerFilter zip) {
        this.zip = zip;
    }

    public StringFilter getCity() {
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }

    public LongFilter getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(LongFilter personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public LongFilter getCountryId() {
        return countryId;
    }

    public void setCountryId(LongFilter countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AdressInfoCriteria that = (AdressInfoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(address, that.address) &&
            Objects.equals(zip, that.zip) &&
            Objects.equals(city, that.city) &&
            Objects.equals(countryId, that.countryId) &&
            Objects.equals(personalInfoId, that.personalInfoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        address,
        countryId,
        zip,
        city,
        personalInfoId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdressInfoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (zip != null ? "zip=" + zip + ", " : "") +
                (city != null ? "city=" + city + ", " : "") +
                (personalInfoId != null ? "personalInfoId=" + personalInfoId + ", " : "") +
                (countryId != null ? "countryId=" + countryId + ", " : "") +
            "}";
    }

}
