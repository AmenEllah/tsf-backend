package com.attijari.service.dto;

import com.attijari.domain.BotScan;

import java.io.Serializable;

/**
 * A DTO for the {@link BotScan} entity.
 */
public class BotScanDTO implements Serializable {

    private Long id;

    private String ref_demande;

    private String cliDelta;

    private String signature;

    private String compte;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef_demande() {
        return ref_demande;
    }

    public void setRef_demande(String ref_demande) {
        this.ref_demande = ref_demande;
    }

    public String getCliDelta() {
        return cliDelta;
    }

    public void setCliDelta(String cliDelta) {
        this.cliDelta = cliDelta;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BotScanDTO)) {
            return false;
        }

        return id != null && id.equals(((BotScanDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BotScanDTO{" +
            "id=" + getId() +
            ", ref_demande='" + getRef_demande() + "'" +
            ", cliDelta='" + getCliDelta() + "'" +
            ", signature='" + getSignature() + "'" +
            ", compte='" + getCompte() + "'" +
            "}";
    }
}
