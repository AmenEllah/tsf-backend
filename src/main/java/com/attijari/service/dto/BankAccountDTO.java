package com.attijari.service.dto;

import com.attijari.domain.BankAccount;

import java.io.Serializable;

/**
 * A DTO for the {@link BankAccount} entity.
 */
public class BankAccountDTO implements Serializable {

    private Long id;

    private String libelleFR;

    private String libelleEN;

    private String descriptionFR;

    private String descriptionEN;

    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleFR() {
        return libelleFR;
    }

    public void setLibelleFR(String libelleFR) {
        this.libelleFR = libelleFR;
    }

    public String getLibelleEN() {
        return libelleEN;
    }

    public void setLibelleEN(String libelleEN) {
        this.libelleEN = libelleEN;
    }

    public String getDescriptionFR() {
        return descriptionFR;
    }

    public void setDescriptionFR(String descriptionFR) {
        this.descriptionFR = descriptionFR;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((BankAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccountDTO{" +
            "id=" + getId() +
            ", libelleFR='" + getLibelleFR() + "'" +
            ", libelleEN='" + getLibelleEN() + "'" +
            ", descriptionFR='" + getDescriptionFR() + "'" +
            ", descriptionEN='" + getDescriptionEN() + "'" +
            "}";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
