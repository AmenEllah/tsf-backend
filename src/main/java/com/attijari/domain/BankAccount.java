package com.attijari.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A BankAccount.
 */
@Entity
@Table(name = "bank_account")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle_fr")
    private String libelleFR;

    @Column(name = "libelle_en")
    private String libelleEN;

    @Column(name = "description_fr")
    private String descriptionFR;

    @Column(name = "description_en")
    private String descriptionEN;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "bankAccount")
    private Set<RequestBankAccount> requestBankAccounts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleFR() {
        return libelleFR;
    }

    public BankAccount libelleFR(String libelleFR) {
        this.libelleFR = libelleFR;
        return this;
    }

    public void setLibelleFR(String libelleFR) {
        this.libelleFR = libelleFR;
    }

    public String getLibelleEN() {
        return libelleEN;
    }

    public BankAccount libelleEN(String libelleEN) {
        this.libelleEN = libelleEN;
        return this;
    }

    public void setLibelleEN(String libelleEN) {
        this.libelleEN = libelleEN;
    }

    public String getDescriptionFR() {
        return descriptionFR;
    }

    public BankAccount descriptionFR(String descriptionFR) {
        this.descriptionFR = descriptionFR;
        return this;
    }

    public void setDescriptionFR(String descriptionFR) {
        this.descriptionFR = descriptionFR;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public BankAccount descriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
        return this;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public Set<RequestBankAccount> getRequestBankAccounts() {
        return requestBankAccounts;
    }

    public BankAccount requestBankAccounts(Set<RequestBankAccount> requestBankAccounts) {
        this.requestBankAccounts = requestBankAccounts;
        return this;
    }

    public BankAccount addRequestBankAccount(RequestBankAccount requestBankAccount) {
        this.requestBankAccounts.add(requestBankAccount);
        requestBankAccount.setBankAccount(this);
        return this;
    }

    public BankAccount removeRequestBankAccount(RequestBankAccount requestBankAccount) {
        this.requestBankAccounts.remove(requestBankAccount);
        requestBankAccount.setBankAccount(null);
        return this;
    }

    public void setRequestBankAccounts(Set<RequestBankAccount> requestBankAccounts) {
        this.requestBankAccounts = requestBankAccounts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        return id != null && id.equals(((BankAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccount{" +
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
