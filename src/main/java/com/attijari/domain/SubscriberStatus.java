package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

import com.attijari.domain.enumeration.appelVisioStatus;
import com.attijari.domain.enumeration.withCertifStatus;
import com.attijari.domain.enumeration.withSignatureStatus;

/**
 * A SubscriberStatus.
 */
@Entity
@Table(name = "subscriber_status")
public class SubscriberStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "num_cin")
    private String NumCin;

    @Enumerated(EnumType.STRING)
    @Column(name = "with_appel_visio")
    private appelVisioStatus WithAppelVisio;

    @Enumerated(EnumType.STRING)
    @Column(name = "with_certif")
    private withCertifStatus WithCertif;

    @Enumerated(EnumType.STRING)
    @Column(name = "with_signature")
    private withSignatureStatus WithSignature;

    @Column(name = "date_appel_visio")
    private String DateAppelVisio;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriberStatus)) {
            return false;
        }
        return id != null && id.equals(((SubscriberStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SubscriberStatus{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", NumCin='" + NumCin + '\'' +
            ", WithAppelVisio=" + WithAppelVisio +
            ", WithCertif=" + WithCertif +
            ", WithSignature=" + WithSignature +
            ", DateAppelVisio='" + DateAppelVisio + '\'' +
            '}';
    }
}
