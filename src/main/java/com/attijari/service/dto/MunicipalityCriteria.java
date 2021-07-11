package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Municipality;
import com.attijari.web.rest.MunicipalityResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Municipality} entity. This class is used
 * in {@link MunicipalityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /municipalities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MunicipalityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private LongFilter agencyId;

    private LongFilter governorateId;

    public MunicipalityCriteria() {
    }

    public MunicipalityCriteria(MunicipalityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.agencyId = other.agencyId == null ? null : other.agencyId.copy();
        this.governorateId = other.governorateId == null ? null : other.governorateId.copy();
    }

    @Override
    public MunicipalityCriteria copy() {
        return new MunicipalityCriteria(this);
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

    public LongFilter getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(LongFilter agencyId) {
        this.agencyId = agencyId;
    }

    public LongFilter getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(LongFilter governorateId) {
        this.governorateId = governorateId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MunicipalityCriteria that = (MunicipalityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(agencyId, that.agencyId) &&
            Objects.equals(governorateId, that.governorateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        agencyId,
        governorateId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MunicipalityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (agencyId != null ? "agencyId=" + agencyId + ", " : "") +
                (governorateId != null ? "governorateId=" + governorateId + ", " : "") +
            "}";
    }

}
