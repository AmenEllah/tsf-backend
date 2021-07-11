package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.Request;
import com.attijari.domain.enumeration.RequestAffectation;
import com.attijari.domain.enumeration.RejectStatus;
import com.attijari.web.rest.RequestResource;
import io.github.jhipster.service.Criteria;
import com.attijari.domain.enumeration.StateRequest;
import com.attijari.domain.enumeration.RequestStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link Request} entity. This class is used
 * in {@link RequestResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /requests?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RequestCriteria implements Serializable, Criteria {
    public StringFilter getCin() {
        return cin;
    }

    public void setCin(StringFilter cin) {
        this.cin = cin;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public RejectStatusFilter getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(RejectStatusFilter rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public StringFilter getDateAppelVisio() {
        return dateAppelVisio;
    }

    public void setDateAppelVisio(StringFilter dateAppelVisio) {
        this.dateAppelVisio = dateAppelVisio;
    }

    public StringFilter getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(StringFilter takenBy) {
        this.takenBy = takenBy;
    }

    /**
     * Class for filtering StateRequest
     */
    public static class StateRequestFilter extends Filter<StateRequest> {

        public StateRequestFilter() {
        }

        public StateRequestFilter(StateRequestFilter filter) {
            super(filter);
        }

        @Override
        public StateRequestFilter copy() {
            return new StateRequestFilter(this);
        }

    }

    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {

        public RequestStatusFilter() {
        }

        public RequestStatusFilter(RequestStatusFilter filter) {
            super(filter);
        }

        @Override
        public RequestStatusFilter copy() {
            return new RequestStatusFilter(this);
        }

    }

    public static class RejectStatusFilter extends Filter<RejectStatus> {

        public RejectStatusFilter() {
        }

        public RejectStatusFilter(RejectStatusFilter filter) {
            super(filter);
        }

        @Override
        public RejectStatusFilter copy() {
            return new RejectStatusFilter(this);
        }

    }


    public static class RequestAffectationFilter extends Filter<RequestAffectation> {

        public RequestAffectationFilter() {
        }

        public RequestAffectationFilter(Filter<RequestAffectation> filter) {
            super(filter);
        }

        @Override
        public RequestAffectationFilter copy() {
            return new RequestAffectationFilter().copy();
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter visioDate;

    private LocalDateFilter sendingMailDate;

    private BooleanFilter state;

    private StringFilter step;

    private StringFilter remoteUserIp;

    private StringFilter rejectCause;

    private StringFilter codeVerification;

    private StringFilter amplitudeRef;

    private BooleanFilter visioStatus;

    private StateRequestFilter requestState;

    private RequestStatusFilter requestStatus;

    private LongFilter offerId;

    private LongFilter personalInfoId;

    private LongFilter subscriberStatusId;

    private LongFilter documentId;

    private LongFilter requestBankAccountId;

    private LongFilter userId;

    private StringFilter userAccountLogin;

    private StringFilter agencyCode;

    private StringFilter matricule;

    private LongFilter derogationsId;

    private BooleanFilter hasCertificate;

    private BooleanFilter politicallyExposed;

    private BooleanFilter greenCard;

    private StringFilter tokenToSign;

    private RequestAffectationFilter requestAffectation;

    private StringFilter cin;

    private StringFilter email;

    private StringFilter matDerogation;

    private RejectStatusFilter rejectStatus;

    private StringFilter dateAppelVisio;

    private StringFilter takenBy;

    public RequestCriteria() {
    }

    public RequestCriteria(RequestCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.visioDate = other.visioDate == null ? null : other.visioDate.copy();
        this.sendingMailDate = other.sendingMailDate == null ? null : other.sendingMailDate.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.visioStatus = other.visioStatus == null ? null : other.visioStatus.copy();
        this.step = other.step == null ? null : other.step.copy();
        this.remoteUserIp = other.remoteUserIp == null ? null : other.remoteUserIp.copy();
        this.codeVerification = other.codeVerification == null ? null : other.codeVerification.copy();
        this.rejectCause = other.rejectCause == null ? null : other.rejectCause.copy();
        this.amplitudeRef = other.amplitudeRef == null ? null : other.amplitudeRef.copy();
        this.requestState = other.requestState == null ? null : other.requestState.copy();
        this.requestStatus = other.requestStatus == null ? null : other.requestStatus.copy();
        this.offerId = other.offerId == null ? null : other.offerId.copy();
        this.personalInfoId = other.personalInfoId == null ? null : other.personalInfoId.copy();
        this.documentId = other.documentId == null ? null : other.documentId.copy();
        this.requestBankAccountId = other.requestBankAccountId == null ? null : other.requestBankAccountId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.userAccountLogin = other.userAccountLogin == null ? null : other.userAccountLogin.copy();
        this.agencyCode = other.agencyCode == null ? null : other.agencyCode.copy();
        this.matricule = other.matricule == null ? null : other.matricule.copy();
        this.derogationsId = other.derogationsId == null ? null : other.derogationsId.copy();
        this.hasCertificate = other.hasCertificate == null ? null : other.hasCertificate.copy();
        this.politicallyExposed = other.politicallyExposed == null ? null : other.politicallyExposed.copy();
        this.greenCard = other.greenCard == null ? null : other.greenCard.copy();
        this.subscriberStatusId = other.subscriberStatusId == null ? null : other.subscriberStatusId.copy();
        this.tokenToSign = other.tokenToSign == null ? null : other.tokenToSign.copy();
        this.requestAffectation = other.requestAffectation == null ? null : other.requestAffectation.copy();
        this.cin = other.cin == null ? null : other.cin.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.matDerogation = other.matDerogation == null ? null : other.matDerogation.copy();
        this.rejectStatus = other.rejectStatus == null ? null : other.rejectStatus.copy();
        this.dateAppelVisio = other.dateAppelVisio == null ? null : other.dateAppelVisio.copy();
        this.takenBy = other.takenBy == null ? null : other.takenBy.copy();
    }

    @Override
    public RequestCriteria copy() {
        return new RequestCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getVisioDate() {
        return visioDate;
    }

    public void setVisioDate(LocalDateFilter visioDate) {
        this.visioDate = visioDate;
    }

    public LocalDateFilter getSendingMailDate() {
        return sendingMailDate;
    }

    public void setSendingMailDate(LocalDateFilter sendingMailDate) {
        this.sendingMailDate = sendingMailDate;
    }

    public BooleanFilter getState() {
        return state;
    }

    public void setState(BooleanFilter state) {
        this.state = state;
    }

    public BooleanFilter getVisioStatus() {
        return visioStatus;
    }

    public void setVisioStatus(BooleanFilter visioStatus) {
        this.visioStatus = visioStatus;
    }

    public StringFilter getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(StringFilter rejectCause) {
        this.rejectCause = rejectCause;
    }

    public StringFilter getStep() {
        return step;
    }

    public void setStep(StringFilter step) {
        this.step = step;
    }

    public StringFilter getCodeVerification() {
        return codeVerification;
    }

    public void setCodeVerification(StringFilter codeVerification) {
        this.codeVerification = codeVerification;
    }

    public StringFilter getAmplitudeRef() {
        return amplitudeRef;
    }

    public void setAmplitudeRef(StringFilter amplitudeRef) {
        this.amplitudeRef = amplitudeRef;
    }

    public StateRequestFilter getRequestState() {
        return requestState;
    }

    public void setRequestState(StateRequestFilter requestState) {
        this.requestState = requestState;
    }

    public RequestStatusFilter getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusFilter requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LongFilter getOfferId() {
        return offerId;
    }

    public void setOfferId(LongFilter offerId) {
        this.offerId = offerId;
    }

    public LongFilter getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(LongFilter personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getRequestBankAccountId() {
        return requestBankAccountId;
    }

    public void setRequestBankAccountId(LongFilter requestBankAccountId) {
        this.requestBankAccountId = requestBankAccountId;
    }

    public StringFilter getRemoteUserIp() {
        return remoteUserIp;
    }

    public void setRemoteUserIp(StringFilter remoteUserIp) {
        this.remoteUserIp = remoteUserIp;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public StringFilter getUserAccountLogin() {
        return userAccountLogin;
    }

    public void setUserAccountLogin(StringFilter userAccountLogin) {
        this.userAccountLogin = userAccountLogin;
    }

    public StringFilter getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(StringFilter agencyCode) {
        this.agencyCode = agencyCode;
    }

    public StringFilter getMatricule() {
        return matricule;
    }

    public void setMatricule(StringFilter matricule) {
        this.matricule = matricule;
    }

    public BooleanFilter getHasCertificate() {
        return hasCertificate;
    }

    public void setHasCertificate(BooleanFilter hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    public LongFilter getDerogationsId() {
        return derogationsId;
    }

    public void setDerogationsId(LongFilter derogationsId) {
        this.derogationsId = derogationsId;
    }

    public BooleanFilter getPoliticallyExposed() {
        return politicallyExposed;
    }

    public void setPoliticallyExposed(BooleanFilter politicallyExposed) {
        this.politicallyExposed = politicallyExposed;
    }

    public BooleanFilter getGreenCard() {
        return greenCard;
    }

    public void setGreenCard(BooleanFilter greenCard) {
        this.greenCard = greenCard;
    }

    public LongFilter getSubscriberStatusId() {
        return subscriberStatusId;
    }

    public void setSubscriberStatusId(LongFilter subscriberStatusId) {
        this.subscriberStatusId = subscriberStatusId;
    }

    public StringFilter getTokenToSign() {
        return tokenToSign;
    }

    public void setTokenToSign(StringFilter tokenToSign) {
        this.tokenToSign = tokenToSign;
    }

    public RequestAffectationFilter getRequestAffectation() {
        return requestAffectation;
    }

    public void setRequestAffectation(RequestAffectationFilter requestAffectation) {
        this.requestAffectation = requestAffectation;
    }

    public StringFilter getMatDerogation() {
        return matDerogation;
    }

    public void setMatDerogation(StringFilter matDerogation) {
        this.matDerogation = matDerogation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RequestCriteria that = (RequestCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(visioDate, that.visioDate) &&
                Objects.equals(sendingMailDate, that.sendingMailDate) &&
                Objects.equals(state, that.state) &&
                Objects.equals(remoteUserIp, that.remoteUserIp) &&
                Objects.equals(step, that.step) &&
                Objects.equals(codeVerification, that.codeVerification) &&
                Objects.equals(amplitudeRef, that.amplitudeRef) &&
                Objects.equals(requestState, that.requestState) &&
                Objects.equals(requestStatus, that.requestStatus) &&
                Objects.equals(offerId, that.offerId) &&
                Objects.equals(personalInfoId, that.personalInfoId) &&
                Objects.equals(documentId, that.documentId) &&
                Objects.equals(requestBankAccountId, that.requestBankAccountId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userAccountLogin, that.userAccountLogin) &&
                Objects.equals(agencyCode, that.agencyCode) &&
                Objects.equals(matricule, that.matricule) &&
                Objects.equals(hasCertificate, that.hasCertificate) &&
                Objects.equals(derogationsId, that.derogationsId) &&
                Objects.equals(politicallyExposed, that.politicallyExposed) &&
                Objects.equals(subscriberStatusId, that.subscriberStatusId) &&
                Objects.equals(greenCard, that.greenCard) &&
                Objects.equals(tokenToSign, that.tokenToSign) &&
                Objects.equals(cin, that.cin) &&
                Objects.equals(email, that.email) &&
                Objects.equals(requestAffectation, that.requestAffectation) &&
                Objects.equals(matDerogation, that.matDerogation)&&
                Objects.equals(dateAppelVisio, that.dateAppelVisio)&&
                Objects.equals(takenBy, that.takenBy)&&
                Objects.equals(rejectStatus, that.rejectStatus);


    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            visioDate,
            sendingMailDate,
            state,
            step,
            remoteUserIp,
            codeVerification,
            amplitudeRef,
            requestState,
            requestStatus,
            offerId,
            personalInfoId,
            documentId,
            requestBankAccountId,
            userId,
            userAccountLogin,
            agencyCode,
            derogationsId,
            matricule,
            hasCertificate,
            politicallyExposed,
            greenCard,
            subscriberStatusId,
            cin,
            tokenToSign,
            email,
            requestAffectation,
            matDerogation,
            dateAppelVisio,
            rejectStatus,
            takenBy
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (visioDate != null ? "visioDate=" + visioDate + ", " : "") +
            (sendingMailDate != null ? "sendingMailDate=" + sendingMailDate + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (step != null ? "step=" + step + ", " : "") +
            (remoteUserIp != null ? "step=" + remoteUserIp + ", " : "") +
            (codeVerification != null ? "codeVerification=" + codeVerification + ", " : "") +
            (amplitudeRef != null ? "amplitudeRef=" + amplitudeRef + ", " : "") +
            (requestState != null ? "requestState=" + requestState + ", " : "") +
            (requestStatus != null ? "requestStatus=" + requestStatus + ", " : "") +
            (offerId != null ? "offerId=" + offerId + ", " : "") +
            (personalInfoId != null ? "personalInfoId=" + personalInfoId + ", " : "") +
            (documentId != null ? "documentId=" + documentId + ", " : "") +
            (requestBankAccountId != null ? "requestBankAccountId=" + requestBankAccountId + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (userAccountLogin != null ? "userAccountLogin=" + userAccountLogin + ", " : "") +
            (agencyCode != null ? "agencyCode=" + agencyCode + ", " : "") +
            (matricule != null ? "matricule=" + matricule + ", " : "") +
            (hasCertificate != null ? "hasCertificate=" + hasCertificate + ", " : "") +
            (derogationsId != null ? "derogationsId=" + derogationsId + ", " : "") +
            (subscriberStatusId != null ? "subscriberStatusId=" + subscriberStatusId + ", " : "") +
            (tokenToSign != null ? "tokenToSign=" + tokenToSign + ", " : "") +
            (requestAffectation != null ? "requestAffectation=" + requestAffectation + ", " : "") +
            (cin != null ? "cin=" + cin + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (matDerogation != null ? "matDerogation=" + matDerogation + ", " : "") +
            (rejectStatus != null ? "rejectStatus=" + rejectStatus + ", " : "") +
            (dateAppelVisio != null ? "dateAppelVisio=" + dateAppelVisio + ", " : "") +
            (takenBy != null ? "takenBy=" + takenBy + ", " : "") +
            "}";
    }

}
