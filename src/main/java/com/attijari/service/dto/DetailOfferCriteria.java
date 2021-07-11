package com.attijari.service.dto;

import com.attijari.domain.DetailOffer;
import com.attijari.web.rest.DetailOfferResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link DetailOffer} entity. This class is used
 * in {@link DetailOfferResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /detail-offers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DetailOfferCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private LongFilter offerId;

    private StringFilter descriptionEn;


    public DetailOfferCriteria() {
    }

    public DetailOfferCriteria(DetailOfferCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.offerId = other.offerId == null ? null : other.offerId.copy();
        this.descriptionEn = other.descriptionEn == null ? null : other.descriptionEn.copy();
    }

    @Override
    public DetailOfferCriteria copy() {
        return new DetailOfferCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public LongFilter getOfferId() {
        return offerId;
    }

    public void setOfferId(LongFilter offerId) {
        this.offerId = offerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DetailOfferCriteria that = (DetailOfferCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(offerId, that.offerId) &&
                Objects.equals(descriptionEn, that.descriptionEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            description,
            offerId,
            descriptionEn
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailOfferCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (offerId != null ? "offerId=" + offerId + ", " : "") +
            (descriptionEn != null ? "descriptionEn=" + descriptionEn + ", " : "") +
            "}";
    }

    public StringFilter getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(StringFilter descriptionEn) {
        this.descriptionEn = descriptionEn;
    }
}
