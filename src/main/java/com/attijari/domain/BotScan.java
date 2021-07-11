package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A BotScan.
 */
@Entity
@Table(name = "bot_scan")
public class BotScan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ref_demande")
    private String d_ref_demande;

    @Column(name = "cli_delta")
    private String a_cliDelta;

    @Column(name = "signature")
    private String b_signature;

    @Column(name = "compte")
    private String c_compte;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef_demande() {
        return d_ref_demande;
    }

    public BotScan ref_demande(String ref_demande) {
        this.d_ref_demande = ref_demande;
        return this;
    }

    public void setRef_demande(String ref_demande) {
        this.d_ref_demande = ref_demande;
    }

    public String getCliDelta() {
        return a_cliDelta;
    }

    public BotScan cliDelta(String cliDelta) {
        this.a_cliDelta = cliDelta;
        return this;
    }

    public void setCliDelta(String cliDelta) {
        this.a_cliDelta = cliDelta;
    }

    public String getSignature() {
        return b_signature;
    }

    public BotScan signature(String signature) {
        this.b_signature = signature;
        return this;
    }

    public void setSignature(String signature) {
        this.b_signature = signature;
    }

    public String getCompte() {
        return c_compte;
    }

    public BotScan compte(String compte) {
        this.c_compte = compte;
        return this;
    }

    public void setCompte(String compte) {
        this.c_compte = compte;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BotScan)) {
            return false;
        }
        return id != null && id.equals(((BotScan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BotScan{" +
            "id=" + getId() +
            ", ref_demande='" + getRef_demande() + "'" +
            ", cliDelta='" + getCliDelta() + "'" +
            ", signature='" + getSignature() + "'" +
            ", compte='" + getCompte() + "'" +
            "}";
    }
}
