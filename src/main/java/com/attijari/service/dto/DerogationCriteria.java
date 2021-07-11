package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Derogation;
import com.attijari.domain.enumeration.RequestAffectation;
import com.attijari.web.rest.DerogationResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link Derogation} entity. This class is used
 * in {@link DerogationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /derogations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DerogationCriteria implements Serializable, Criteria {
    public StringFilter getAdvisorMessage() {
        return advisorMessage;
    }

    public void setAdvisorMessage(StringFilter advisorMessage) {
        this.advisorMessage = advisorMessage;
    }

    /**
     * Class for filtering RequestAffectation
     */
    public static class RequestAffectationFilter extends Filter<RequestAffectation> {

        public RequestAffectationFilter() {
        }

        public RequestAffectationFilter(RequestAffectationFilter filter) {
            super(filter);
        }

        @Override
        public RequestAffectationFilter copy() {
            return new RequestAffectationFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter message;

    private RequestAffectationFilter affectation;

    private StringFilter matricule;

    private LongFilter requestId;

    private StringFilter advisorMessage;


    public DerogationCriteria() {
    }

    public DerogationCriteria(DerogationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.message = other.message == null ? null : other.message.copy();
        this.affectation = other.affectation == null ? null : other.affectation.copy();
        this.matricule = other.matricule == null ? null : other.matricule.copy();
        this.requestId = other.requestId == null ? null : other.requestId.copy();
        this.advisorMessage = other.advisorMessage == null ? null : other.advisorMessage.copy();
    }

    @Override
    public DerogationCriteria copy() {
        return new DerogationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMessage() {
        return message;
    }

    public void setMessage(StringFilter message) {
        this.message = message;
    }

    public RequestAffectationFilter getAffectation() {
        return affectation;
    }

    public void setAffectation(RequestAffectationFilter affectation) {
        this.affectation = affectation;
    }

    public StringFilter getMatricule() {
        return matricule;
    }

    public void setMatricule(StringFilter matricule) {
        this.matricule = matricule;
    }

    public LongFilter getRequestId() {
        return requestId;
    }

    public void setRequestId(LongFilter requestId) {
        this.requestId = requestId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DerogationCriteria that = (DerogationCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(message, that.message) &&
                Objects.equals(affectation, that.affectation) &&
                Objects.equals(matricule, that.matricule) &&
                Objects.equals(advisorMessage, that.advisorMessage) &&
                Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            message,
            affectation,
            matricule,
            requestId,
            advisorMessage);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DerogationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (message != null ? "message=" + message + ", " : "") +
            (affectation != null ? "affectation=" + affectation + ", " : "") +
            (matricule != null ? "matricule=" + matricule + ", " : "") +
            (requestId != null ? "requestId=" + requestId + ", " : "") +
            (advisorMessage != null ? "requestId=" + advisorMessage + ", " : "") +
            "}";
    }

}
