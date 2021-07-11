package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Category;
import com.attijari.web.rest.CategoryResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Category} entity. This class is used
 * in {@link CategoryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /categories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CategoryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter categoryNameFR;

    private StringFilter categoryNameEN;

    private LongFilter financialInfoId;

    public CategoryCriteria() {
    }

    public CategoryCriteria(CategoryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.categoryNameFR = other.categoryNameFR == null ? null : other.categoryNameFR.copy();
        this.categoryNameEN = other.categoryNameEN == null ? null : other.categoryNameEN.copy();
        this.financialInfoId = other.financialInfoId == null ? null : other.financialInfoId.copy();
    }

    @Override
    public CategoryCriteria copy() {
        return new CategoryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCategoryNameFR() {
        return categoryNameFR;
    }

    public void setCategoryNameFR(StringFilter categoryNameFR) {
        this.categoryNameFR = categoryNameFR;
    }

    public StringFilter getCategoryNameEN() {
        return categoryNameEN;
    }

    public void setCategoryNameEN(StringFilter categoryNameEN) {
        this.categoryNameEN = categoryNameEN;
    }

    public LongFilter getFinancialInfoId() {
        return financialInfoId;
    }

    public void setFinancialInfoId(LongFilter financialInfoId) {
        this.financialInfoId = financialInfoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CategoryCriteria that = (CategoryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(categoryNameFR, that.categoryNameFR) &&
            Objects.equals(categoryNameEN, that.categoryNameEN) &&
            Objects.equals(financialInfoId, that.financialInfoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        categoryNameFR,
        categoryNameEN,
        financialInfoId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (categoryNameFR != null ? "categoryNameFR=" + categoryNameFR + ", " : "") +
                (categoryNameEN != null ? "categoryNameEN=" + categoryNameEN + ", " : "") +
                (financialInfoId != null ? "financialInfoId=" + financialInfoId + ", " : "") +
            "}";
    }

}
