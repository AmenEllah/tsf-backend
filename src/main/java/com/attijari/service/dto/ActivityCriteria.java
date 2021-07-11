package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Activity;
import com.attijari.web.rest.ActivityResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Activity} entity. This class is used
 * in {@link ActivityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /activities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ActivityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter activityNameFR;

    private StringFilter activityNameEN;

    private StringFilter code;

    private LongFilter financialInfoId;

    public ActivityCriteria() {
    }

    public ActivityCriteria(ActivityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.activityNameFR = other.activityNameFR == null ? null : other.activityNameFR.copy();
        this.activityNameEN = other.activityNameEN == null ? null : other.activityNameEN.copy();
        this.financialInfoId = other.financialInfoId == null ? null : other.financialInfoId.copy();
        this.code = other.code == null ? null : other.code.copy();
    }

    @Override
    public ActivityCriteria copy() {
        return new ActivityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getActivityNameFR() {
        return activityNameFR;
    }

    public void setActivityNameFR(StringFilter activityNameFR) {
        this.activityNameFR = activityNameFR;
    }

    public StringFilter getActivityNameEN() {
        return activityNameEN;
    }

    public void setActivityNameEN(StringFilter activityNameEN) {
        this.activityNameEN = activityNameEN;
    }

    public LongFilter getFinancialInfoId() {
        return financialInfoId;
    }

    public void setFinancialInfoId(LongFilter financialInfoId) {
        this.financialInfoId = financialInfoId;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ActivityCriteria that = (ActivityCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(activityNameFR, that.activityNameFR) &&
                Objects.equals(activityNameEN, that.activityNameEN) &&
                Objects.equals(code, that.code) &&
                Objects.equals(financialInfoId, that.financialInfoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            activityNameFR,
            activityNameEN,
            code,
            financialInfoId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActivityCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (activityNameFR != null ? "activityNameFR=" + activityNameFR + ", " : "") +
            (activityNameEN != null ? "activityNameEN=" + activityNameEN + ", " : "") +
            (financialInfoId != null ? "financialInfoId=" + financialInfoId + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            "}";
    }

}
