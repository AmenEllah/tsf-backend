package com.attijari.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import com.attijari.domain.enumeration.Civility;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * A PersonalInfo.
 */
@Entity
@Table(name = "personal_info")
public class PersonalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    @Nullable
    @Enumerated(EnumType.STRING)
    @Column(name = "civility")
    private Civility civility;
    @Nullable
    @Column(name = "first_name")
    private String firstName;
    @Nullable
    @Column(name = "last_name")
    private String lastName;
    @Nullable
    @Column(name = "email")
    private String email;
    @Nullable
    @Column(name = "native_country")
    private String nativeCountry;
    @Nullable
    @Column(name = "birthday")
    private LocalDate birthday;
    @Nullable
    @Column(name = "client_abt")
    private Boolean clientABT;
    @Nullable
    @Column(name = "rib")
    private String rib;
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    private Nationality nationality;

    @Nullable
    @Column(name = "nbrkids")
    private Integer nbrkids;
    @Nullable
    @Column(name = "marital_status")
    private String maritalStatus;
    @Nullable
    @Column(name = "phone")
    private String phone;
    @Nullable
    @Column(name = "american_index")
    private Boolean americanIndex;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "cin")
    private String cin;

    @Column(name = "abroad_residancy_proof")
    private String abroadResidancyProof;

    @Column(name = "abroad_residancy_number")
    private String abroadResidancyNumber;

    @Column(name = "cin_delivery_date")
    private Instant cinDeliveryDate;

    @Column(name = "abro_resid_deliv_date")
    private Instant abroadResidancyDeliveryDate;

    @Column(name = "abro_resid_exp_date")
    private Instant abroadResidancyExpirationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private AdressInfo adressInfo;

    @ManyToOne
    private Agency agency;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private FinancialInfo financialInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    private Nationality secondNationality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Civility getCivility() {
        return civility;
    }

    public PersonalInfo civility(Civility civility) {
        this.civility = civility;
        return this;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonalInfo firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonalInfo lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public PersonalInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNativeCountry() {
        return nativeCountry;
    }

    public PersonalInfo nativeCountry(String nativeCountry) {
        this.nativeCountry = nativeCountry;
        return this;
    }

    public void setNativeCountry(String nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public PersonalInfo birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean isClientABT() {
        return clientABT;
    }

    public PersonalInfo clientABT(Boolean clientABT) {
        this.clientABT = clientABT;
        return this;
    }

    public void setClientABT(Boolean clientABT) {
        this.clientABT = clientABT;
    }

    public String getRib() {
        return rib;
    }

    public PersonalInfo rib(String rib) {
        this.rib = rib;
        return this;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    @Nullable
    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(@Nullable Nationality nationality) {
        this.nationality = nationality;
    }

    public Nationality getSecondNationality() {
        return secondNationality;
    }

    public PersonalInfo secondNationality(Nationality secondNationality) {
        this.secondNationality = secondNationality;
        return this;
    }

    public void setSecondNationality(Nationality secondNationality) {
        this.secondNationality = secondNationality;
    }

    public Integer getNbrkids() {
        return nbrkids;
    }

    public PersonalInfo nbrkids(Integer nbrkids) {
        this.nbrkids = nbrkids;
        return this;
    }

    public void setNbrkids(Integer nbrkids) {
        this.nbrkids = nbrkids;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public PersonalInfo maritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhone() {
        return phone;
    }

    public PersonalInfo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isAmericanIndex() {
        return americanIndex;
    }

    public PersonalInfo americanIndex(Boolean americanIndex) {
        this.americanIndex = americanIndex;
        return this;
    }

    public void setAmericanIndex(Boolean americanIndex) {
        this.americanIndex = americanIndex;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public PersonalInfo accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCin() {
        return cin;
    }

    public PersonalInfo cin(String cin) {
        this.cin = cin;
        return this;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAbroadResidancyProof() {
        return abroadResidancyProof;
    }

    public PersonalInfo abroadResidancyProof(String abroadResidancyProof) {
        this.abroadResidancyProof = abroadResidancyProof;
        return this;
    }

    public void setAbroadResidancyProof(String abroadResidancyProof) {
        this.abroadResidancyProof = abroadResidancyProof;
    }

    public String getAbroadResidancyNumber() {
        return abroadResidancyNumber;
    }

    public PersonalInfo abroadResidancyNumber(String abroadResidancyNumber) {
        this.abroadResidancyNumber = abroadResidancyNumber;
        return this;
    }

    public void setAbroadResidancyNumber(String abroadResidancyNumber) {
        this.abroadResidancyNumber = abroadResidancyNumber;
    }

    public Instant getCinDeliveryDate() {
        return cinDeliveryDate;
    }

    public PersonalInfo cinDeliveryDate(Instant cinDeliveryDate) {
        this.cinDeliveryDate = cinDeliveryDate;
        return this;
    }

    public void setCinDeliveryDate(Instant cinDeliveryDate) {
        this.cinDeliveryDate = cinDeliveryDate;
    }

    public Instant getAbroadResidancyDeliveryDate() {
        return abroadResidancyDeliveryDate;
    }

    public PersonalInfo abroadResidancyDeliveryDate(Instant abroadResidancyDeliveryDate) {
        this.abroadResidancyDeliveryDate = abroadResidancyDeliveryDate;
        return this;
    }

    public void setAbroadResidancyDeliveryDate(Instant abroadResidancyDeliveryDate) {
        this.abroadResidancyDeliveryDate = abroadResidancyDeliveryDate;
    }

    public Instant getAbroadResidancyExpirationDate() {
        return abroadResidancyExpirationDate;
    }

    public PersonalInfo abroadResidancyExpirationDate(Instant abroadResidancyExpirationDate) {
        this.abroadResidancyExpirationDate = abroadResidancyExpirationDate;
        return this;
    }

    public void setAbroadResidancyExpirationDate(Instant abroadResidancyExpirationDate) {
        this.abroadResidancyExpirationDate = abroadResidancyExpirationDate;
    }

    public AdressInfo getAdressInfo() {
        return adressInfo;
    }

    public PersonalInfo adressInfo(AdressInfo adressInfo) {
        this.adressInfo = adressInfo;
        return this;
    }

    public void setAdressInfo(AdressInfo adressInfo) {
        this.adressInfo = adressInfo;
    }

    public Agency getAgency() {
        return agency;
    }

    public PersonalInfo agency(Agency agency) {
        this.agency = agency;
        return this;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public FinancialInfo getFinancialInfo() {
        return financialInfo;
    }

    public PersonalInfo financialInfo(FinancialInfo financialInfo) {
        this.financialInfo = financialInfo;
        return this;
    }

    public void setFinancialInfo(FinancialInfo financialInfo) {
        this.financialInfo = financialInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonalInfo)) {
            return false;
        }
        return id != null && id.equals(((PersonalInfo) o).id);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
            "id=" + id +
            ", civility=" + civility +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", nativeCountry='" + nativeCountry + '\'' +
            ", birthday=" + birthday +
            ", clientABT=" + clientABT +
            ", rib='" + rib + '\'' +
            ", nationality=" + nationality +
            ", secondNationality='" + secondNationality + '\'' +
            ", nbrkids=" + nbrkids +
            ", maritalStatus='" + maritalStatus + '\'' +
            ", phone='" + phone + '\'' +
            ", americanIndex=" + americanIndex +
            ", accountNumber='" + accountNumber + '\'' +
            ", cin='" + cin + '\'' +
            ", abroadResidancyProof='" + abroadResidancyProof + '\'' +
            ", abroadResidancyNumber='" + abroadResidancyNumber + '\'' +
            ", cinDeliveryDate=" + cinDeliveryDate +
            ", abroadResidancyDeliveryDate=" + abroadResidancyDeliveryDate +
            ", abroadResidancyExpirationDate=" + abroadResidancyExpirationDate +
            ", adressInfo=" + adressInfo +
            ", agency=" + agency +
            ", financialInfo=" + financialInfo +
            ", country=" + country +
            '}';
    }
}
