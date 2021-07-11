package com.attijari.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Set;

import com.attijari.domain.Request;
import com.attijari.domain.enumeration.RejectStatus;
import com.attijari.domain.enumeration.RequestAffectation;
import com.attijari.domain.enumeration.RequestStatus;
import com.attijari.domain.enumeration.StateRequest;

/**
 * A DTO for the {@link Request} entity.
 */
public class RequestDTO implements Serializable {

    private Long id;

    private LocalDate visioDate;

    private Boolean visioStatus;

    private LocalDate sendingMailDate;

    private Boolean state;

    private String step;

    private String codeVerification;

    private String rejectCause;

    private String remoteUserIp;

    private String amplitudeRef;

    private StateRequest requestState;

    private RequestStatus requestStatus;

    private Set<RequestBankAccountDTO> requestBankAccounts;

    private PersonalInfoDTO personalInfo;

    private SubscriberStatusDTO subscriberStatus;

    private Long offerId;

    private OfferDTO offer;

    private DocumentDTO document;

    private  Set<AttachmentDTO> attachments;

    private  Set<DerogationDTO> derogations;

    private Long userId;

    private String userAccountLogin;

    private String matricule;

    private String agencyCode;

    private Boolean hasCertificate;

    private Boolean politicallyExposed;

    private Boolean greenCard;

    private String tokenToSign;

    private RequestAffectation requestAffectation;

    private String matDerogation;

    private RejectStatus rejectStatus;

    private Instant createdDate;

    private String takenBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisioDate() {
        return visioDate;
    }

    public void setVisioDate(LocalDate visioDate) {
        this.visioDate = visioDate;
    }

    public LocalDate getSendingMailDate() {
        return sendingMailDate;
    }

    public void setSendingMailDate(LocalDate sendingMailDate) {
        this.sendingMailDate = sendingMailDate;
    }

    public Boolean isState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCodeVerification() {
        return codeVerification;
    }

    public void setCodeVerification(String codeVerification) {
        this.codeVerification = codeVerification;
    }

    public String getAmplitudeRef() {
        return amplitudeRef;
    }

    public void setAmplitudeRef(String amplitudeRef) {
        this.amplitudeRef = amplitudeRef;
    }

    public StateRequest getRequestState() {
        return requestState;
    }

    public void setRequestState(StateRequest requestState) {
        this.requestState = requestState;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getRemoteUserIp() {
        return remoteUserIp;
    }

    public void setRemoteUserIp(String remoteUserIp) {
        this.remoteUserIp = remoteUserIp;
    }

    public Boolean getVisioStatus() {
        return visioStatus;
    }

    public void setVisioStatus(Boolean visioStatus) {
        this.visioStatus = visioStatus;
    }

    public PersonalInfoDTO getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfoDTO personalInfo) {
        this.personalInfo = personalInfo;
    }

    public OfferDTO getOffer() {
        return offer;
    }

    public void setOffer(OfferDTO offer) {
        this.offer = offer;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccountLogin() {
        return userAccountLogin;
    }

    public void setUserAccountLogin(String userAccountLogin) {
        this.userAccountLogin = userAccountLogin;
    }

    public Boolean getState() {
        return state;
    }


    public Set<DerogationDTO> getDerogations() {
        return derogations;
    }

    public void setDerogations(Set<DerogationDTO> derogations) {
        this.derogations = derogations;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Boolean getHasCertificate() {
        return hasCertificate;
    }

    public void setHasCertificate(Boolean hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    public Set<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }

    public Boolean getPoliticallyExposed() {
        return politicallyExposed;
    }

    public void setPoliticallyExposed(Boolean politicallyExposed) {
        this.politicallyExposed = politicallyExposed;
    }

    public Boolean getGreenCard() {
        return greenCard;
    }

    public void setGreenCard(Boolean greenCard) {
        this.greenCard = greenCard;
    }

    public String getMatDerogation() {
        return matDerogation;
    }

    public void setMatDerogation(String matDerogation) {
        this.matDerogation = matDerogation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestDTO)) {
            return false;
        }

        return id != null && id.equals(((RequestDTO) o).id);
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause;
    }

    public SubscriberStatusDTO getSubscriberStatus() {
        return subscriberStatus;
    }

    public void setSubscriberStatus(SubscriberStatusDTO subscriberStatus) {
        this.subscriberStatus = subscriberStatus;
    }

    public String getTokenToSign() {
        return tokenToSign;
    }

    public void setTokenToSign(String tokenToSign) {
        this.tokenToSign = tokenToSign;
    }

    @Override
    public int hashCode() {
        return 31;
    }


    public Set<RequestBankAccountDTO> getRequestBankAccounts() {
        return requestBankAccounts;
    }

    public void setRequestBankAccounts(Set<RequestBankAccountDTO> requestBankAccounts) {
        this.requestBankAccounts = requestBankAccounts;
    }

    public RequestAffectation getRequestAffectation() {
        return requestAffectation;
    }

    public void setRequestAffectation(RequestAffectation requestAffectation) {
        this.requestAffectation = requestAffectation;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
            "id=" + id +
            ", visioDate=" + visioDate +
            ", visioStatus=" + visioStatus +
            ", sendingMailDate=" + sendingMailDate +
            ", state=" + state +
            ", step='" + step + '\'' +
            ", codeVerification='" + codeVerification + '\'' +
            ", rejectCause='" + rejectCause + '\'' +
            ", remoteUserIp='" + remoteUserIp + '\'' +
            ", amplitudeRef='" + amplitudeRef + '\'' +
            ", requestState=" + requestState +
            ", requestStatus=" + requestStatus +
            ", requestBankAccounts=" + requestBankAccounts +
            ", personalInfo=" + personalInfo +
            ", subscriberStatus=" + subscriberStatus +
            ", offerId=" + offerId +
            ", offer=" + offer +
            ", document=" + document +
            ", attachments=" + attachments +
            ", derogations=" + derogations +
            ", userId=" + userId +
            ", userAccountLogin='" + userAccountLogin + '\'' +
            ", matricule='" + matricule + '\'' +
            ", agencyCode='" + agencyCode + '\'' +
            ", hasCertificate=" + hasCertificate +
            ", politicallyExposed=" + politicallyExposed +
            ", greenCard=" + greenCard +
            ", tokenToSign='" + tokenToSign + '\'' +
            ", requestAffectation='" + requestAffectation + '\'' +
            ", matDerogation='" + matDerogation + '\'' +
            '}';
    }

    public RejectStatus getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(RejectStatus rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }
}
