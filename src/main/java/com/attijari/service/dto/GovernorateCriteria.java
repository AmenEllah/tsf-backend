package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Governorate;
import com.attijari.web.rest.GovernorateResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Governorate} entity. This class is used
 * in {@link GovernorateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /governorates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GovernorateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private LongFilter municipalityId;

    public GovernorateCriteria() {
    }

    public GovernorateCriteria(GovernorateCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.municipalityId = other.municipalityId == null ? null : other.municipalityId.copy();
    }

    @Override
    public GovernorateCriteria copy() {
        return new GovernorateCriteria(this);
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
        final GovernorateCriteria that = (GovernorateCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(municipalityId, that.municipalityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        municipalityId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GovernorateCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (municipalityId != null ? "municipalityId=" + municipalityId + ", " : "") +
            "}";
    }

}
