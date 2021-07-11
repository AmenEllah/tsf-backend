package com.attijari.config.optConfig;


import javax.naming.directory.Attributes;

public class UserAD {
    public static final String[] requestedAttributes = {"telephonenumber", "displayname", "employeeID", "sAMAccountName"};
    private String telephoneNumber;
    private String displayName;
    private String employeeId;
    private String samAccountName;

    public UserAD(String telephoneNumber, String displayName, String employeeId, String samAccountName) {
        this.telephoneNumber = telephoneNumber;
        this.displayName = displayName;
        this.employeeId = employeeId;
        this.samAccountName = samAccountName;
    }

    public UserAD() {

    }
    public UserAD(Attributes attributes) {
        if (attributes != null && attributes.size() >= requestedAttributes.length) {
            this.telephoneNumber = String.valueOf(attributes.get(requestedAttributes[0])).substring(String.valueOf(attributes.get(requestedAttributes[0])).indexOf(":") + 2);
            this.displayName = String.valueOf(attributes.get(requestedAttributes[1])).substring(String.valueOf(attributes.get(requestedAttributes[1])).indexOf(":") + 2);
            this.employeeId = String.valueOf(attributes.get(requestedAttributes[2])).substring(String.valueOf(attributes.get(requestedAttributes[2])).indexOf(":") + 2);
            this.samAccountName = String.valueOf(attributes.get(requestedAttributes[3])).substring(String.valueOf(attributes.get(requestedAttributes[3])).indexOf(":") + 2);
        }

    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSamAccountName() {
        return samAccountName;
    }

    public void setSamAccountName(String samAccountName) {
        this.samAccountName = samAccountName;
    }
}
