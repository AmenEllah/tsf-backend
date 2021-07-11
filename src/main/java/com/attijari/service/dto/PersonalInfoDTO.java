package com.attijari.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;

import com.attijari.domain.PersonalInfo;
import com.attijari.domain.enumeration.Civility;

/**
 * A DTO for the {@link PersonalInfo} entity.
 */
public class PersonalInfoDTO implements Serializable {

    private Long id;

    private Civility civility;

    private String firstName;

    private String lastName;

    private String email;

    private String nativeCountry;

    private LocalDate birthday;

    private Boolean clientABT;

    private String rib;

    private NationalityDTO nationality;

    private NationalityDTO secondNationality;

    private Integer nbrkids;

    private String maritalStatus;

    private String phone;

    private Boolean americanIndex;

    private String accountNumber;

    private String cin;

    private String abroadResidancyProof;

    private String abroadResidancyNumber;

    private Instant cinDeliveryDate;

    private Instant abroadResidancyDeliveryDate;

    private Instant abroadResidancyExpirationDate;

    private Long agencyId;

    private AgencyDTO agency;

    private FinancialInfoDTO financialInfo;

    private AdressInfoDTO adressInfo;

    private CountryDTO country;

    private NationalityDTO secondNationalityInfo;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNativeCountry() {
        return nativeCountry;
    }

    public void setNativeCountry(String nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean isClientABT() {
        return clientABT;
    }

    public void setClientABT(Boolean clientABT) {
        this.clientABT = clientABT;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public NationalityDTO getSecondNationality() {
        return secondNationality;
    }

    public void setSecondNationality(NationalityDTO secondNationality) {
        this.secondNationality = secondNationality;
    }

    public Integer getNbrkids() {
        return nbrkids;
    }

    public void setNbrkids(Integer nbrkids) {
        this.nbrkids = nbrkids;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isAmericanIndex() {
        return americanIndex;
    }

    public void setAmericanIndex(Boolean americanIndex) {
        this.americanIndex = americanIndex;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAbroadResidancyProof() {
        return abroadResidancyProof;
    }

    public void setAbroadResidancyProof(String abroadResidancyProof) {
        this.abroadResidancyProof = abroadResidancyProof;
    }

    public String getAbroadResidancyNumber() {
        return abroadResidancyNumber;
    }

    public void setAbroadResidancyNumber(String abroadResidancyNumber) {
        this.abroadResidancyNumber = abroadResidancyNumber;
    }

    public Instant getCinDeliveryDate() {
        return cinDeliveryDate;
    }

    public void setCinDeliveryDate(Instant cinDeliveryDate) {
        this.cinDeliveryDate = cinDeliveryDate;
    }

    public Instant getAbroadResidancyDeliveryDate() {
        return abroadResidancyDeliveryDate;
    }

    public void setAbroadResidancyDeliveryDate(Instant abroadResidancyDeliveryDate) {
        this.abroadResidancyDeliveryDate = abroadResidancyDeliveryDate;
    }

    public Instant getAbroadResidancyExpirationDate() {
        return abroadResidancyExpirationDate;
    }

    public void setAbroadResidancyExpirationDate(Instant abroadResidancyExpirationDate) {
        this.abroadResidancyExpirationDate = abroadResidancyExpirationDate;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public AgencyDTO getAgency() {
        return agency;
    }

    public void setAgency(AgencyDTO agency) {
        this.agency = agency;
    }

    public FinancialInfoDTO getFinancialInfo() {
        return financialInfo;
    }

    public void setFinancialInfo(FinancialInfoDTO financialInfo) {
        this.financialInfo = financialInfo;
    }

    public AdressInfoDTO getAdressInfo() {
        return adressInfo;
    }

    public void setAdressInfo(AdressInfoDTO adressInfo) {
        this.adressInfo = adressInfo;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public NationalityDTO getSecondNationalityInfo() {
        return secondNationalityInfo;
    }

    public void setSecondNationalityInfo(NationalityDTO secondNationalityInfo) {
        this.secondNationalityInfo = secondNationalityInfo;
    }

    public NationalityDTO getNationality() {
        return nationality;
    }

    public void setNationality(NationalityDTO nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonalInfoDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonalInfoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PersonalInfoDTO{" +
            "id=" + id +
            ", civility=" + civility +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", nativeCountry='" + nativeCountry + '\'' +
            ", birthday=" + birthday +
            ", clientABT=" + clientABT +
            ", rib='" + rib + '\'' +
            ", nationality='" + nationality + '\'' +
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
            ", agencyId=" + agencyId +
            ", agency=" + agency +
            ", adressInfo=" + adressInfo +
            ", financialInfo=" + financialInfo +
            ", country=" + country +
            ", secondNationality=" + secondNationality +
            '}';
    }
}
