package com.attijari.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.attijari.domain.PersonalInfo;
import com.attijari.web.rest.PersonalInfoResource;
import io.github.jhipster.service.Criteria;
import com.attijari.domain.enumeration.Civility;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link PersonalInfo} entity. This class is used
 * in {@link PersonalInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /personal-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PersonalInfoCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Civility
     */
    public static class CivilityFilter extends Filter<Civility> {

        public CivilityFilter() {
        }

        public CivilityFilter(CivilityFilter filter) {
            super(filter);
        }

        @Override
        public CivilityFilter copy() {
            return new CivilityFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private CivilityFilter civility;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter email;

    private StringFilter nativeCountry;

    private LocalDateFilter birthday;

    private BooleanFilter clientABT;

    private StringFilter rib;

    private IntegerFilter nbrkids;

    private StringFilter maritalStatus;

    private StringFilter phone;

    private BooleanFilter americanIndex;

    private StringFilter accountNumber;

    private StringFilter cin;

    private StringFilter abroadResidancyProof;

    private StringFilter abroadResidancyNumber;

    private InstantFilter cinDeliveryDate;

    private InstantFilter abroadResidancyDeliveryDate;

    private InstantFilter abroadResidancyExpirationDate;

    private LongFilter requestId;

    private LongFilter adressInfoId;

    private LongFilter agencyId;

    private LongFilter financialInfoId;

    private LongFilter countryId;

    private LongFilter nationalityId;

    private LongFilter secondNationalityId;

    public PersonalInfoCriteria() {
    }

    public PersonalInfoCriteria(PersonalInfoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.civility = other.civility == null ? null : other.civility.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.nativeCountry = other.nativeCountry == null ? null : other.nativeCountry.copy();
        this.birthday = other.birthday == null ? null : other.birthday.copy();
        this.clientABT = other.clientABT == null ? null : other.clientABT.copy();
        this.rib = other.rib == null ? null : other.rib.copy();
        this.nbrkids = other.nbrkids == null ? null : other.nbrkids.copy();
        this.maritalStatus = other.maritalStatus == null ? null : other.maritalStatus.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.americanIndex = other.americanIndex == null ? null : other.americanIndex.copy();
        this.accountNumber = other.accountNumber == null ? null : other.accountNumber.copy();
        this.cin = other.cin == null ? null : other.cin.copy();
        this.abroadResidancyProof = other.abroadResidancyProof == null ? null : other.abroadResidancyProof.copy();
        this.abroadResidancyNumber = other.abroadResidancyNumber == null ? null : other.abroadResidancyNumber.copy();
        this.cinDeliveryDate = other.cinDeliveryDate == null ? null : other.cinDeliveryDate.copy();
        this.abroadResidancyDeliveryDate = other.abroadResidancyDeliveryDate == null ? null : other.abroadResidancyDeliveryDate.copy();
        this.abroadResidancyExpirationDate = other.abroadResidancyExpirationDate == null ? null : other.abroadResidancyExpirationDate.copy();
        this.requestId = other.requestId == null ? null : other.requestId.copy();
        this.adressInfoId = other.adressInfoId == null ? null : other.adressInfoId.copy();
        this.agencyId = other.agencyId == null ? null : other.agencyId.copy();
        this.financialInfoId = other.financialInfoId == null ? null : other.financialInfoId.copy();
        this.countryId = other.countryId == null ? null : other.countryId.copy();
        this.nationalityId = other.nationalityId == null ? null : other.nationalityId.copy();
        this.secondNationalityId = other.secondNationalityId == null ? null : other.secondNationalityId.copy();
    }

    @Override
    public PersonalInfoCriteria copy() {
        return new PersonalInfoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public CivilityFilter getCivility() {
        return civility;
    }

    public void setCivility(CivilityFilter civility) {
        this.civility = civility;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getNativeCountry() {
        return nativeCountry;
    }

    public void setNativeCountry(StringFilter nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    public LocalDateFilter getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateFilter birthday) {
        this.birthday = birthday;
    }

    public BooleanFilter getClientABT() {
        return clientABT;
    }

    public void setClientABT(BooleanFilter clientABT) {
        this.clientABT = clientABT;
    }

    public StringFilter getRib() {
        return rib;
    }

    public void setRib(StringFilter rib) {
        this.rib = rib;
    }

    public LongFilter getSecondNationalityId() {
        return secondNationalityId;
    }

    public void setSecondNationalityId(LongFilter secondNationalityId) {
        this.secondNationalityId = secondNationalityId;
    }

    public IntegerFilter getNbrkids() {
        return nbrkids;
    }

    public void setNbrkids(IntegerFilter nbrkids) {
        this.nbrkids = nbrkids;
    }

    public StringFilter getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(StringFilter maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public BooleanFilter getAmericanIndex() {
        return americanIndex;
    }

    public void setAmericanIndex(BooleanFilter americanIndex) {
        this.americanIndex = americanIndex;
    }

    public StringFilter getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(StringFilter accountNumber) {
        this.accountNumber = accountNumber;
    }

    public StringFilter getCin() {
        return cin;
    }

    public void setCin(StringFilter cin) {
        this.cin = cin;
    }

    public StringFilter getAbroadResidancyProof() {
        return abroadResidancyProof;
    }

    public void setAbroadResidancyProof(StringFilter abroadResidancyProof) {
        this.abroadResidancyProof = abroadResidancyProof;
    }

    public StringFilter getAbroadResidancyNumber() {
        return abroadResidancyNumber;
    }

    public void setAbroadResidancyNumber(StringFilter abroadResidancyNumber) {
        this.abroadResidancyNumber = abroadResidancyNumber;
    }

    public InstantFilter getCinDeliveryDate() {
        return cinDeliveryDate;
    }

    public void setCinDeliveryDate(InstantFilter cinDeliveryDate) {
        this.cinDeliveryDate = cinDeliveryDate;
    }

    public InstantFilter getAbroadResidancyDeliveryDate() {
        return abroadResidancyDeliveryDate;
    }

    public void setAbroadResidancyDeliveryDate(InstantFilter abroadResidancyDeliveryDate) {
        this.abroadResidancyDeliveryDate = abroadResidancyDeliveryDate;
    }

    public InstantFilter getAbroadResidancyExpirationDate() {
        return abroadResidancyExpirationDate;
    }

    public void setAbroadResidancyExpirationDate(InstantFilter abroadResidancyExpirationDate) {
        this.abroadResidancyExpirationDate = abroadResidancyExpirationDate;
    }

    public LongFilter getRequestId() {
        return requestId;
    }

    public void setRequestId(LongFilter requestId) {
        this.requestId = requestId;
    }

    public LongFilter getAdressInfoId() {
        return adressInfoId;
    }

    public void setAdressInfoId(LongFilter adressInfoId) {
        this.adressInfoId = adressInfoId;
    }

    public LongFilter getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(LongFilter agencyId) {
        this.agencyId = agencyId;
    }

    public LongFilter getFinancialInfoId() {
        return financialInfoId;
    }

    public void setFinancialInfoId(LongFilter financialInfoId) {
        this.financialInfoId = financialInfoId;
    }

    public LongFilter getCountryId() {
        return countryId;
    }

    public void setCountryId(LongFilter countryId) {
        this.countryId = countryId;
    }

    public LongFilter getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(LongFilter nationalityId) {
        this.nationalityId = nationalityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PersonalInfoCriteria that = (PersonalInfoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(civility, that.civility) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(nativeCountry, that.nativeCountry) &&
            Objects.equals(birthday, that.birthday) &&
            Objects.equals(clientABT, that.clientABT) &&
            Objects.equals(rib, that.rib) &&
            Objects.equals(nbrkids, that.nbrkids) &&
            Objects.equals(maritalStatus, that.maritalStatus) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(americanIndex, that.americanIndex) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(cin, that.cin) &&
            Objects.equals(abroadResidancyProof, that.abroadResidancyProof) &&
            Objects.equals(abroadResidancyNumber, that.abroadResidancyNumber) &&
            Objects.equals(cinDeliveryDate, that.cinDeliveryDate) &&
            Objects.equals(abroadResidancyDeliveryDate, that.abroadResidancyDeliveryDate) &&
            Objects.equals(abroadResidancyExpirationDate, that.abroadResidancyExpirationDate) &&
            Objects.equals(requestId, that.requestId) &&
            Objects.equals(adressInfoId, that.adressInfoId) &&
            Objects.equals(agencyId, that.agencyId) &&
            Objects.equals(financialInfoId, that.financialInfoId) &&
            Objects.equals(countryId, that.countryId) &&
            Objects.equals(nationalityId, that.nationalityId) &&
            Objects.equals(secondNationalityId, that.secondNationalityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        civility,
        firstName,
        lastName,
        email,
        nativeCountry,
        birthday,
        clientABT,
        rib,
        nbrkids,
        maritalStatus,
        phone,
        americanIndex,
        accountNumber,
        cin,
        abroadResidancyProof,
        abroadResidancyNumber,
        cinDeliveryDate,
        abroadResidancyDeliveryDate,
        abroadResidancyExpirationDate,
        requestId,
        adressInfoId,
        agencyId,
        financialInfoId,
        countryId,
        nationalityId,
        secondNationalityId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalInfoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (civility != null ? "civility=" + civility + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (nativeCountry != null ? "nativeCountry=" + nativeCountry + ", " : "") +
                (birthday != null ? "birthday=" + birthday + ", " : "") +
                (clientABT != null ? "clientABT=" + clientABT + ", " : "") +
                (rib != null ? "rib=" + rib + ", " : "") +
                (nbrkids != null ? "nbrkids=" + nbrkids + ", " : "") +
                (maritalStatus != null ? "maritalStatus=" + maritalStatus + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (americanIndex != null ? "americanIndex=" + americanIndex + ", " : "") +
                (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") +
                (cin != null ? "cin=" + cin + ", " : "") +
                (abroadResidancyProof != null ? "abroadResidancyProof=" + abroadResidancyProof + ", " : "") +
                (abroadResidancyNumber != null ? "abroadResidancyNumber=" + abroadResidancyNumber + ", " : "") +
                (cinDeliveryDate != null ? "cinDeliveryDate=" + cinDeliveryDate + ", " : "") +
                (abroadResidancyDeliveryDate != null ? "abroadResidancyDeliveryDate=" + abroadResidancyDeliveryDate + ", " : "") +
                (abroadResidancyExpirationDate != null ? "abroadResidancyExpirationDate=" + abroadResidancyExpirationDate + ", " : "") +
                (requestId != null ? "requestId=" + requestId + ", " : "") +
                (adressInfoId != null ? "adressInfoId=" + adressInfoId + ", " : "") +
                (agencyId != null ? "agencyId=" + agencyId + ", " : "") +
                (financialInfoId != null ? "financialInfoId=" + financialInfoId + ", " : "") +
                (countryId != null ? "countryId=" + countryId + ", " : "") +
                (nationalityId != null ? "nationalityId=" + nationalityId + ", " : "") +
                (secondNationalityId != null ? "secondNationalityId=" + secondNationalityId + ", " : "") +
            "}";
    }

}
