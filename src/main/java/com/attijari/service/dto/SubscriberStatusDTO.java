package com.attijari.service.dto;

import java.io.Serializable;

import com.attijari.domain.SubscriberStatus;
import com.attijari.domain.enumeration.appelVisioStatus;
import com.attijari.domain.enumeration.withCertifStatus;
import com.attijari.domain.enumeration.withSignatureStatus;

/**
 * A DTO for the {@link SubscriberStatus} entity.
 */
public class SubscriberStatusDTO implements Serializable {

    private Long id;

    private String email;

    private String NumCin;

    private appelVisioStatus WithAppelVisio;

    private withCertifStatus WithCertif;

    private withSignatureStatus WithSignature;

    private String DateAppelVisio;

    private String signature;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumCin() {
        return NumCin;
    }

    public void setNumCin(String numCin) {
        NumCin = numCin;
    }

    public appelVisioStatus getWithAppelVisio() {
        return WithAppelVisio;
    }

    public void setWithAppelVisio(appelVisioStatus withAppelVisio) {
        WithAppelVisio = withAppelVisio;
    }

    public withCertifStatus getWithCertif() {
        return WithCertif;
    }

    public void setWithCertif(withCertifStatus withCertif) {
        WithCertif = withCertif;
    }

    public withSignatureStatus getWithSignature() {
        return WithSignature;
    }

    public void setWithSignature(withSignatureStatus withSignature) {
        WithSignature = withSignature;
    }

    public String getDateAppelVisio() {
        return DateAppelVisio;
    }

    public void setDateAppelVisio(String dateAppelVisio) {
        DateAppelVisio = dateAppelVisio;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriberStatusDTO)) {
            return false;
        }

        return id != null && id.equals(((SubscriberStatusDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubscriberStatusDTO{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", NumCin='" + NumCin + '\'' +
            ", WithAppelVisio=" + WithAppelVisio +
            ", WithCertif=" + WithCertif +
            ", WithSignature=" + WithSignature +
            ", DateAppelVisio='" + DateAppelVisio + '\'' +
            ", signature='" + signature + '\'' +
            '}';
    }
}
