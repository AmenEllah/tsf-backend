package com.attijari.service.dto;

import com.attijari.domain.enumeration.Civility;

import java.time.LocalDate;
import java.util.Set;

public class UserChoiceDTO {
    private String firstName;
    private String lastName;
    private Civility civility;
    private String email;
    private String phone ;
    private LocalDate birthday;
    private String langue;
    private String nativeCountry;
    private Set<RequestBankAccountDTO> requestBankAccounts;

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

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getNativeCountry() {
        return nativeCountry;
    }

    public void setNativeCountry(String nativeCountry) {
        this.nativeCountry = nativeCountry;
    }

    @Override
    public String toString() {
        return "UserChoiceDTO{" +
            "firstname='" + firstName + '\'' +
            ", lastname='" + lastName + '\'' +
            ", civility=" + civility +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", birthday=" + birthday +
            ", langue='" + langue + '\'' +
            ", nativeCountry='" + nativeCountry + '\'' +
            '}';
    }

    public Set<RequestBankAccountDTO> getRequestBankAccounts() {
        return requestBankAccounts;
    }

    public void setRequestBankAccounts(Set<RequestBankAccountDTO> requestBankAccounts) {
        this.requestBankAccounts = requestBankAccounts;
    }
}
