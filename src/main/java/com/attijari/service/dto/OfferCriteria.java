package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Offer;
import com.attijari.web.rest.OfferResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Offer} entity. This class is used
 * in {@link OfferResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /offers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OfferCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private DoubleFilter price;

    private StringFilter url;

    private LongFilter requestId;

    private LongFilter detailOfferId;

    private StringFilter code;

    public OfferCriteria() {
    }

    public OfferCriteria(OfferCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.url = other.url == null ? null : other.url.copy();
        this.requestId = other.requestId == null ? null : other.requestId.copy();
        this.detailOfferId = other.detailOfferId == null ? null : other.detailOfferId.copy();
        this.code = other.code == null ? null : other.code.copy();
    }

    @Override
    public OfferCriteria copy() {
        return new OfferCriteria(this);
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

    public DoubleFilter getPrice() {
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public LongFilter getRequestId() {
        return requestId;
    }

    public void setRequestId(LongFilter requestId) {
        this.requestId = requestId;
    }

    public LongFilter getDetailOfferId() {
        return detailOfferId;
    }

    public void setDetailOfferId(LongFilter detailOfferId) {
        this.detailOfferId = detailOfferId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OfferCriteria that = (OfferCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(url, that.url) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(code, that.code) &&
                Objects.equals(detailOfferId, that.detailOfferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            price,
            url,
            requestId,
            detailOfferId,
            code
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfferCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (url != null ? "url=" + url + ", " : "") +
            (requestId != null ? "requestId=" + requestId + ", " : "") +
            (detailOfferId != null ? "detailOfferId=" + detailOfferId + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            "}";
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }
}
