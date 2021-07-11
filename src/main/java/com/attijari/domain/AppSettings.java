package com.attijari.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.attijari.domain.enumeration.SenderProvider;

import com.attijari.domain.enumeration.SignerProvider;

/**
 * A AppSettings.
 */
@Entity
@Table(name = "app_settings")
public class AppSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mail_sender_provider", nullable = false)
    private SenderProvider mailSenderProvider;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "signer_provider", nullable = false)
    private SignerProvider signerProvider;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SenderProvider getMailSenderProvider() {
        return mailSenderProvider;
    }

    public AppSettings mailSenderProvider(SenderProvider mailSenderProvider) {
        this.mailSenderProvider = mailSenderProvider;
        return this;
    }

    public void setMailSenderProvider(SenderProvider mailSenderProvider) {
        this.mailSenderProvider = mailSenderProvider;
    }

    public SignerProvider getSignerProvider() {
        return signerProvider;
    }

    public AppSettings signerProvider(SignerProvider signerProvider) {
        this.signerProvider = signerProvider;
        return this;
    }

    public void setSignerProvider(SignerProvider signerProvider) {
        this.signerProvider = signerProvider;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppSettings)) {
            return false;
        }
        return id != null && id.equals(((AppSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppSettings{" +
            "id=" + getId() +
            ", mailSenderProvider='" + getMailSenderProvider() + "'" +
            ", signerProvider='" + getSignerProvider() + "'" +
            "}";
    }
}
