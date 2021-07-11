package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.BotScan;
import com.attijari.web.rest.BotScanResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link BotScan} entity. This class is used
 * in {@link BotScanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bot-scans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BotScanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ref_demande;

    private StringFilter cliDelta;

    private StringFilter signature;

    private StringFilter compte;

    public BotScanCriteria() {
    }

    public BotScanCriteria(BotScanCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ref_demande = other.ref_demande == null ? null : other.ref_demande.copy();
        this.cliDelta = other.cliDelta == null ? null : other.cliDelta.copy();
        this.signature = other.signature == null ? null : other.signature.copy();
        this.compte = other.compte == null ? null : other.compte.copy();
    }

    @Override
    public BotScanCriteria copy() {
        return new BotScanCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getRef_demande() {
        return ref_demande;
    }

    public void setRef_demande(StringFilter ref_demande) {
        this.ref_demande = ref_demande;
    }

    public StringFilter getCliDelta() {
        return cliDelta;
    }

    public void setCliDelta(StringFilter cliDelta) {
        this.cliDelta = cliDelta;
    }

    public StringFilter getSignature() {
        return signature;
    }

    public void setSignature(StringFilter signature) {
        this.signature = signature;
    }

    public StringFilter getCompte() {
        return compte;
    }

    public void setCompte(StringFilter compte) {
        this.compte = compte;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BotScanCriteria that = (BotScanCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ref_demande, that.ref_demande) &&
            Objects.equals(cliDelta, that.cliDelta) &&
            Objects.equals(signature, that.signature) &&
            Objects.equals(compte, that.compte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ref_demande,
        cliDelta,
        signature,
        compte
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BotScanCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ref_demande != null ? "ref_demande=" + ref_demande + ", " : "") +
                (cliDelta != null ? "cliDelta=" + cliDelta + ", " : "") +
                (signature != null ? "signature=" + signature + ", " : "") +
                (compte != null ? "compte=" + compte + ", " : "") +
            "}";
    }

}
