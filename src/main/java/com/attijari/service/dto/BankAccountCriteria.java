package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.BankAccount;
import com.attijari.web.rest.BankAccountResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link BankAccount} entity. This class is used
 * in {@link BankAccountResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bank-accounts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BankAccountCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelleFR;

    private StringFilter libelleEN;

    private StringFilter descriptionFR;

    private StringFilter descriptionEN;

    private LongFilter requestBankAccountId;

    private StringFilter code;


    public BankAccountCriteria() {
    }

    public BankAccountCriteria(BankAccountCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelleFR = other.libelleFR == null ? null : other.libelleFR.copy();
        this.libelleEN = other.libelleEN == null ? null : other.libelleEN.copy();
        this.descriptionFR = other.descriptionFR == null ? null : other.descriptionFR.copy();
        this.descriptionEN = other.descriptionEN == null ? null : other.descriptionEN.copy();
        this.requestBankAccountId = other.requestBankAccountId == null ? null : other.requestBankAccountId.copy();
        this.code = other.code == null ? null : other.code.copy();
    }

    @Override
    public BankAccountCriteria copy() {
        return new BankAccountCriteria(this);
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

    public StringFilter getDescriptionFR() {
        return descriptionFR;
    }

    public void setDescriptionFR(StringFilter descriptionFR) {
        this.descriptionFR = descriptionFR;
    }

    public StringFilter getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(StringFilter descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public LongFilter getRequestBankAccountId() {
        return requestBankAccountId;
    }

    public void setRequestBankAccountId(LongFilter requestBankAccountId) {
        this.requestBankAccountId = requestBankAccountId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BankAccountCriteria that = (BankAccountCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(libelleFR, that.libelleFR) &&
                Objects.equals(libelleEN, that.libelleEN) &&
                Objects.equals(descriptionFR, that.descriptionFR) &&
                Objects.equals(descriptionEN, that.descriptionEN) &&
                Objects.equals(code, that.code) &&
                Objects.equals(requestBankAccountId, that.requestBankAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            libelleFR,
            libelleEN,
            descriptionFR,
            descriptionEN,
            requestBankAccountId,
            code
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccountCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (libelleFR != null ? "libelleFR=" + libelleFR + ", " : "") +
            (libelleEN != null ? "libelleEN=" + libelleEN + ", " : "") +
            (descriptionFR != null ? "descriptionFR=" + descriptionFR + ", " : "") +
            (descriptionEN != null ? "descriptionEN=" + descriptionEN + ", " : "") +
            (requestBankAccountId != null ? "requestBankAccountId=" + requestBankAccountId + ", " : "") +
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
