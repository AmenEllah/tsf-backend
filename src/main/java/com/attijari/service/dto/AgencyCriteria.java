package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Agency;
import com.attijari.web.rest.AgencyResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Agency} entity. This class is used
 * in {@link AgencyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /agencies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AgencyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter address;

    private IntegerFilter zip;

    private StringFilter city;

    private StringFilter code;

    private LongFilter municipalityId;

    private StringFilter managerCode;

    private StringFilter managerLib;


    public AgencyCriteria() {
    }

    public AgencyCriteria(AgencyCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.zip = other.zip == null ? null : other.zip.copy();
        this.city = other.city == null ? null : other.city.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.municipalityId = other.municipalityId == null ? null : other.municipalityId.copy();
        this.managerCode = other.managerCode == null ? null : other.managerCode.copy();
        this.managerLib = other.managerLib == null ? null : other.managerLib.copy();
    }

    @Override
    public AgencyCriteria copy() {
        return new AgencyCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
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

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public LongFilter getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(LongFilter municipalityId) {
        this.municipalityId = municipalityId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AgencyCriteria that = (AgencyCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(zip, that.zip) &&
                Objects.equals(city, that.city) &&
                Objects.equals(code, that.code) &&
                Objects.equals(managerCode, that.managerCode) &&
                Objects.equals(managerLib, that.managerLib) &&
                Objects.equals(municipalityId, that.municipalityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            address,
            zip,
            city,
            code,
            managerCode,
            managerLib,
            municipalityId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgencyCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (zip != null ? "zip=" + zip + ", " : "") +
            (city != null ? "city=" + city + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (municipalityId != null ? "municipalityId=" + municipalityId + ", " : "") +
            (managerCode != null ? "managerCode=" + managerCode + ", " : "") +
            (managerLib != null ? "managerLib=" + managerLib + ", " : "") +
            "}";
    }

    public StringFilter getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(StringFilter managerCode) {
        this.managerCode = managerCode;
    }

    public StringFilter getManagerLib() {
        return managerLib;
    }

    public void setManagerLib(StringFilter managerLib) {
        this.managerLib = managerLib;
    }
}
