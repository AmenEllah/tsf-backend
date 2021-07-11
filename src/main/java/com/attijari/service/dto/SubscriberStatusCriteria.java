package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.SubscriberStatus;
import com.attijari.web.rest.SubscriberStatusResource;
import io.github.jhipster.service.Criteria;
import com.attijari.domain.enumeration.appelVisioStatus;
import com.attijari.domain.enumeration.withCertifStatus;
import com.attijari.domain.enumeration.withSignatureStatus;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SubscriberStatus} entity. This class is used
 * in {@link SubscriberStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /subscriber-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SubscriberStatusCriteria implements Serializable, Criteria {
    /**
     * Class for filtering appelVisioStatus
     */
    public static class appelVisioStatusFilter extends Filter<appelVisioStatus> {

        public appelVisioStatusFilter() {
        }

        public appelVisioStatusFilter(appelVisioStatusFilter filter) {
            super(filter);
        }

        @Override
        public appelVisioStatusFilter copy() {
            return new appelVisioStatusFilter(this);
        }

    }
    /**
     * Class for filtering withCertifStatus
     */
    public static class withCertifStatusFilter extends Filter<withCertifStatus> {

        public withCertifStatusFilter() {
        }

        public withCertifStatusFilter(withCertifStatusFilter filter) {
            super(filter);
        }

        @Override
        public withCertifStatusFilter copy() {
            return new withCertifStatusFilter(this);
        }

    }
    /**
     * Class for filtering withSignatureStatus
     */
    public static class withSignatureStatusFilter extends Filter<withSignatureStatus> {

        public withSignatureStatusFilter() {
        }

        public withSignatureStatusFilter(withSignatureStatusFilter filter) {
            super(filter);
        }

        @Override
        public withSignatureStatusFilter copy() {
            return new withSignatureStatusFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter email;

    private StringFilter numCin;

    private appelVisioStatusFilter withAppelVisio;

    private withCertifStatusFilter withCertif;

    private withSignatureStatusFilter withSignature;

    private StringFilter dateAppelVisio;

    public SubscriberStatusCriteria() {
    }

    public SubscriberStatusCriteria(SubscriberStatusCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.numCin = other.numCin == null ? null : other.numCin.copy();
        this.withAppelVisio = other.withAppelVisio == null ? null : other.withAppelVisio.copy();
        this.withCertif = other.withCertif == null ? null : other.withCertif.copy();
        this.withSignature = other.withSignature == null ? null : other.withSignature.copy();
        this.dateAppelVisio = other.dateAppelVisio == null ? null : other.dateAppelVisio.copy();
    }

    @Override
    public SubscriberStatusCriteria copy() {
        return new SubscriberStatusCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getNumCin() {
        return numCin;
    }

    public void setNumCin(StringFilter numCin) {
        this.numCin = numCin;
    }

    public appelVisioStatusFilter getWithAppelVisio() {
        return withAppelVisio;
    }

    public void setWithAppelVisio(appelVisioStatusFilter withAppelVisio) {
        this.withAppelVisio = withAppelVisio;
    }

    public withCertifStatusFilter getWithCertif() {
        return withCertif;
    }

    public void setWithCertif(withCertifStatusFilter withCertif) {
        this.withCertif = withCertif;
    }

    public withSignatureStatusFilter getWithSignature() {
        return withSignature;
    }

    public void setWithSignature(withSignatureStatusFilter withSignature) {
        this.withSignature = withSignature;
    }


    public StringFilter getDateAppelVisio() {
        return dateAppelVisio;
    }

    public void setDateAppelVisio(StringFilter dateAppelVisio) {
        this.dateAppelVisio = dateAppelVisio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SubscriberStatusCriteria that = (SubscriberStatusCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(email, that.email) &&
            Objects.equals(numCin, that.numCin) &&
            Objects.equals(withAppelVisio, that.withAppelVisio) &&
            Objects.equals(withCertif, that.withCertif) &&
            Objects.equals(withSignature, that.withSignature) &&
            Objects.equals(dateAppelVisio, that.dateAppelVisio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        email,
        numCin,
        withAppelVisio,
        withCertif,
        withSignature,
        dateAppelVisio
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubscriberStatusCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (numCin != null ? "numCin=" + numCin + ", " : "") +
                (withAppelVisio != null ? "withAppelVisio=" + withAppelVisio + ", " : "") +
                (withCertif != null ? "withCertif=" + withCertif + ", " : "") +
                (withSignature != null ? "withSignature=" + withSignature + ", " : "") +
                (dateAppelVisio != null ? "dateAppelVisio=" + dateAppelVisio + ", " : "") +
            "}";
    }

}
