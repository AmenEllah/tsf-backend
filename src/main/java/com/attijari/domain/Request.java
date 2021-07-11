package com.attijari.domain;

import com.attijari.domain.enumeration.RejectStatus;
import com.attijari.domain.enumeration.RequestAffectation;
import com.attijari.domain.enumeration.RequestStatus;
import com.attijari.domain.enumeration.StateRequest;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Request.
 */
@Entity
@Table(name = "request")
public class Request extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "visio_date")
    private LocalDate visioDate;

    @Column(name = "sending_mail_date")
    private LocalDate sendingMailDate;

    @Column(name = "state")
    private Boolean state;

    @Column(name = "step")
    private String step;

    @Column(name = "code_verification")
    private String codeVerification;

    @Column(name = "amplitude_ref")
    private String amplitudeRef;

    @Column(name = "visio_status")
    private Boolean visioStatus = false;

    @Column(name = "remote_ip")
    private String remoteUserIp;

    @Lob
    @Column(name = "reject_cause")
    private String rejectCause;

    @Column(name = "reject_status")
    @Enumerated(EnumType.STRING)
    private RejectStatus rejectStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_state")
    private StateRequest requestState;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @ManyToOne
    private Offer offer;

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private PersonalInfo personalInfo;

    @OneToOne
    private SubscriberStatus subscriberStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Document document;

    @OneToMany(mappedBy = "request",cascade = CascadeType.ALL,
        orphanRemoval = true)
    private Set<RequestBankAccount> requestBankAccounts = new HashSet<>();

    @OneToMany(mappedBy = "request" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Derogation> derogations = new HashSet<>();

    @OneToMany(mappedBy = "request", cascade = CascadeType.REMOVE)
    private  Set<Attachment> attachments = new HashSet<>();

    @ManyToOne(optional = true)
    private User user;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "has_certificate")
    private Boolean hasCertificate;


    @Column(name = "politically_exposed")
    private Boolean politicallyExposed;

    @Column(name = "green_card")
    private Boolean greenCard;

    @Column(name = "token_to_sign")
    private String tokenToSign;

    @Column(name = "request_affectation")
    @Enumerated(EnumType.STRING)
    private RequestAffectation requestAffectation;

    @Column(name = "mat_derogation")
    private String matDerogation;

    @Column(name = "taken_by")
    private String takenBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisioDate() {
        return visioDate;
    }

    public Request visioDate(LocalDate visioDate) {
        this.visioDate = visioDate;
        return this;
    }

    public void setVisioDate(LocalDate visioDate) {
        this.visioDate = visioDate;
    }

    public LocalDate getSendingMailDate() {
        return sendingMailDate;
    }

    public Request sendingMailDate(LocalDate sendingMailDate) {
        this.sendingMailDate = sendingMailDate;
        return this;
    }

    public void setSendingMailDate(LocalDate sendingMailDate) {
        this.sendingMailDate = sendingMailDate;
    }

    public Boolean isState() {
        return state;
    }

    public Request state(Boolean state) {
        this.state = state;
        return this;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getStep() {
        return step;
    }

    public Request step(String step) {
        this.step = step;
        return this;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCodeVerification() {
        return codeVerification;
    }

    public Request codeVerification(String codeVerification) {
        this.codeVerification = codeVerification;
        return this;
    }

    public void setCodeVerification(String codeVerification) {
        this.codeVerification = codeVerification;
    }

    public String getAmplitudeRef() {
        return amplitudeRef;
    }

    public Request amplitudeRef(String amplitudeRef) {
        this.amplitudeRef = amplitudeRef;
        return this;
    }

    public void setAmplitudeRef(String amplitudeRef) {
        this.amplitudeRef = amplitudeRef;
    }

    public StateRequest getRequestState() {
        return requestState;
    }

    public Request requestState(StateRequest requestState) {
        this.requestState = requestState;
        return this;
    }

    public void setRequestState(StateRequest requestState) {
        this.requestState = requestState;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public Request requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Offer getOffer() {
        return offer;
    }

    public Request offer(Offer offer) {
        this.offer = offer;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public Boolean getVisioStatus() {
        return visioStatus;
    }
    public Request visioStatus(Boolean visioStatus) {
        this.visioStatus = visioStatus;
        return this;
    }

    public void setVisioStatus(Boolean visioStatus) {
        this.visioStatus = visioStatus;
    }

    public String getRejectCause() {
        return rejectCause;
    }
    public Request rejectCode(String rejectCode) {
        this.rejectCause = rejectCode;
        return this;
    }
    public void setRejectCause(String rejectCode) {
        this.rejectCause = rejectCode;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public Request personalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
        return this;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Document getDocument() {
        return document;
    }

    public Request document(Document document) {
        this.document = document;
        return this;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Set<RequestBankAccount> getRequestBankAccounts() {
        return requestBankAccounts;
    }

    public Request requestBankAccounts(Set<RequestBankAccount> requestBankAccounts) {
        this.requestBankAccounts = requestBankAccounts;
        return this;
    }

    public Request addRequestBankAccount(RequestBankAccount requestBankAccount) {
        this.requestBankAccounts.add(requestBankAccount);
        requestBankAccount.setRequest(this);
        return this;
    }

    public Request removeRequestBankAccount(RequestBankAccount requestBankAccount) {
        this.requestBankAccounts.remove(requestBankAccount);
        requestBankAccount.setRequest(null);
        return this;
    }

    public void setRequestBankAccounts(Set<RequestBankAccount> requestBankAccounts) {
        this.requestBankAccounts = requestBankAccounts;
    }

    public String getRemoteUserIp() {
        return remoteUserIp;
    }

    public void setRemoteUserIp(String remoteUserIp) {
        this.remoteUserIp = remoteUserIp;
    }


    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public Request attachments(Set<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Request addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setRequest(this);
        return this;
    }

    public Request removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
        attachment.setRequest(null);
        return this;
    }

    public Request derogations(Set<Derogation> derogations) {
        this.derogations = derogations;
        return this;
    }
    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here
    public Set<Derogation> getDerogations() {
        return derogations;
    }

    public void setDerogations(Set<Derogation> derogations) {
        this.derogations = derogations;
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

    public SubscriberStatus getSubscriberStatus() {
        return subscriberStatus;
    }

    public void setSubscriberStatus(SubscriberStatus subscriberStatus) {
        this.subscriberStatus = subscriberStatus;
    }

    public String getTokenToSign() {
        return tokenToSign;
    }

    public void setTokenToSign(String tokenToSign) {
        this.tokenToSign = tokenToSign;
    }

    public RequestAffectation getRequestAffectation() {
        return requestAffectation;
    }

    public void setRequestAffectation(RequestAffectation requestAffectation) {
        this.requestAffectation = requestAffectation;
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
        if (!(o instanceof Request)) {
            return false;
        }
        return id != null && id.equals(((Request) o).id);
    }


    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Request{" +
            "id=" + id +
            ", visioDate=" + visioDate +
            ", sendingMailDate=" + sendingMailDate +
            ", state=" + state +
            ", step='" + step + '\'' +
            ", codeVerification='" + codeVerification + '\'' +
            ", amplitudeRef='" + amplitudeRef + '\'' +
            ", visioStatus=" + visioStatus +
            ", remoteUserIp='" + remoteUserIp + '\'' +
            ", rejectCause='" + rejectCause + '\'' +
            ", requestState=" + requestState +
            ", requestStatus=" + requestStatus +
            ", offer=" + offer +
            ", personalInfo=" + personalInfo +
            ", subscriberStatus=" + subscriberStatus +
            ", document=" + document +
            ", requestBankAccounts=" + requestBankAccounts +
            ", derogations=" + derogations +
            ", attachments=" + attachments +
            ", user=" + user +
            ", matricule='" + matricule + '\'' +
            ", hasCertificate=" + hasCertificate +
            ", politicallyExposed=" + politicallyExposed +
            ", greenCard=" + greenCard +
            ", tokenToSign='" + tokenToSign + '\'' +
            ", requestAffectation='" + requestAffectation + '\'' +
            ", matDerogation=" + matDerogation +
            '}';
    }

    public RejectStatus getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(RejectStatus rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }
}
