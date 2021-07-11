package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Country;
import com.attijari.web.rest.CountryResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Country} entity. This class is used
 * in {@link CountryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /countries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CountryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameFR;

    private StringFilter nameEN;

    private StringFilter code;

    private StringFilter flag;


    public CountryCriteria() {
    }

    public CountryCriteria(CountryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nameFR = other.nameFR == null ? null : other.nameFR.copy();
        this.nameEN = other.nameEN == null ? null : other.nameEN.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.flag = other.flag == null ? null : other.flag.copy();
    }

    @Override
    public CountryCriteria copy() {
        return new CountryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNameFR() {
        return nameFR;
    }

    public void setNameFR(StringFilter nameFR) {
        this.nameFR = nameFR;
    }

    public StringFilter getNameEN() {
        return nameEN;
    }

    public void setNameEN(StringFilter nameEN) {
        this.nameEN = nameEN;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getFlag() {
        return flag;
    }

    public void setFlag(StringFilter flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CountryCriteria that = (CountryCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(nameFR, that.nameFR) &&
                Objects.equals(nameEN, that.nameEN) &&
                Objects.equals(code, that.code) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            nameFR,
            nameEN,
            code,
            flag
        );
    }

    @Override
    public String toString() {
        return "CountryCriteria{" +
            "id=" + id +
            ", nameFR=" + nameFR +
            ", nameEN=" + nameEN +
            ", code=" + code +
            ", flag=" + flag +
            '}';
    }
}
