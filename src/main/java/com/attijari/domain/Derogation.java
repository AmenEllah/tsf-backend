package com.attijari.domain;

import com.attijari.domain.enumeration.RequestAffectation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Derogation.
 */
@Entity
@Table(name = "derogation")
public class Derogation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "affectation")
    private RequestAffectation affectation;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "advisor_message")
    private String advisorMessage;

    @ManyToOne
    @JsonIgnoreProperties(value = "derogations", allowSetters = true)
    private Request request;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public Derogation message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestAffectation getAffectation() {
        return affectation;
    }

    public Derogation affectation(RequestAffectation affectation) {
        this.affectation = affectation;
        return this;
    }

    public void setAffectation(RequestAffectation affectation) {
        this.affectation = affectation;
    }

    public String getMatricule() {
        return matricule;
    }

    public Derogation matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Request getRequest() {
        return request;
    }

    public Derogation request(Request request) {
        this.request = request;
        return this;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Derogation)) {
            return false;
        }
        return id != null && id.equals(((Derogation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Derogation{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", affectation='" + getAffectation() + "'" +
            ", matricule='" + getMatricule() + "'" +
            "}";
    }

    public String getAdvisorMessage() {
        return advisorMessage;
    }

    public void setAdvisorMessage(String advisorMessage) {
        this.advisorMessage = advisorMessage;
    }
}
