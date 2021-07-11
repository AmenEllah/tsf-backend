package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Nationality;
import com.attijari.web.rest.NationalityResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Nationality} entity. This class is used
 * in {@link NationalityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nationalities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NationalityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelleFR;

    private StringFilter libelleEN;

    private StringFilter code;

    private StringFilter flag;

    public NationalityCriteria() {
    }

    public NationalityCriteria(NationalityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelleFR = other.libelleFR == null ? null : other.libelleFR.copy();
        this.libelleEN = other.libelleEN == null ? null : other.libelleEN.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.flag = other.flag == null ? null : other.flag.copy();
    }

    @Override
    public NationalityCriteria copy() {
        return new NationalityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibelleFR() {
        return libelleFR;
    }

    public void setLibelleFR(StringFilter libelleFR) {
        this.libelleFR = libelleFR;
    }

    public StringFilter getLibelleEN() {
        return libelleEN;
    }

    public void setLibelleEN(StringFilter libelleEN) {
        this.libelleEN = libelleEN;
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
        final NationalityCriteria that = (NationalityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelleFR, that.libelleFR) &&
            Objects.equals(libelleEN, that.libelleEN) &&
            Objects.equals(code, that.code) &&
            Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelleFR,
        libelleEN,
        code,
        flag
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NationalityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelleFR != null ? "libelleFR=" + libelleFR + ", " : "") +
                (libelleEN != null ? "libelleEN=" + libelleEN + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (flag != null ? "flag=" + flag + ", " : "") +
            "}";
    }

}
