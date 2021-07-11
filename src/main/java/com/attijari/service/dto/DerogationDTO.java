package com.attijari.service.dto;

import java.io.Serializable;

import com.attijari.domain.Derogation;
import com.attijari.domain.enumeration.RequestAffectation;

/**
 * A DTO for the {@link Derogation} entity.
 */
public class DerogationDTO implements Serializable {

    private Long id;

    private String message;

    private RequestAffectation affectation;

    private String matricule;

    private Long requestId;

    private String lastModifiedBy;

    private String createdBy;

    private String advisorMessage;

    public DerogationDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestAffectation getAffectation() {
        return affectation;
    }

    public void setAffectation(RequestAffectation affectation) {
        this.affectation = affectation;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DerogationDTO)) {
            return false;
        }

        return id != null && id.equals(((DerogationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DerogationDTO{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", affectation='" + getAffectation() + "'" +
            ", matricule=" + getMatricule() +
            ", requestId=" + getRequestId() +
            "}";
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getAdvisorMessage() {
        return advisorMessage;
    }

    public void setAdvisorMessage(String advisorMessage) {
        this.advisorMessage = advisorMessage;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
