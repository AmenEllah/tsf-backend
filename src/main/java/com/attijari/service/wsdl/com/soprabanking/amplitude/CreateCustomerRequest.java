package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createCustomerRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameToReturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeFieldCode1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeFieldCode2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeFieldCode3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="situation" type="{http://soprabanking.com/amplitude}customerSituationCreate" minOccurs="0"/>
 *         &lt;element name="specificInformation" type="{http://soprabanking.com/amplitude}customerSpecInfoCreate" minOccurs="0"/>
 *         &lt;element name="generalAttributes" type="{http://soprabanking.com/amplitude}customerGeneralAttributesCreate" minOccurs="0"/>
 *         &lt;element name="reportingAttributes" type="{http://soprabanking.com/amplitude}customerReportingAttributesCreate" minOccurs="0"/>
 *         &lt;element name="marketingAttributes" type="{http://soprabanking.com/amplitude}customerMarketingAttributesCreate" minOccurs="0"/>
 *         &lt;element name="fatcaAttributes" type="{http://soprabanking.com/amplitude}customerFatcaAttributesCreate" minOccurs="0"/>
 *         &lt;element name="paymentMethods" type="{http://soprabanking.com/amplitude}customerPaymentMethods" minOccurs="0"/>
 *         &lt;element name="adressesList" type="{http://soprabanking.com/amplitude}customerAddressesCreate" minOccurs="0"/>
 *         &lt;element name="phoneNumbersList" type="{http://soprabanking.com/amplitude}customerPhoneNumbersCreate" minOccurs="0"/>
 *         &lt;element name="emailAdressesList" type="{http://soprabanking.com/amplitude}customerEmailAddressesCreate" minOccurs="0"/>
 *         &lt;element name="contactsList" type="{http://soprabanking.com/amplitude}modifyCustomerContactsRequest" minOccurs="0"/>
 *         &lt;element name="customerProfile" type="{http://soprabanking.com/amplitude}createCustomerProfile" minOccurs="0"/>
 *         &lt;element name="customerAsset" type="{http://soprabanking.com/amplitude}modifyCustomerAssetRequest" minOccurs="0"/>
 *         &lt;element name="idPapersList" type="{http://soprabanking.com/amplitude}customerIdPapersCreate" minOccurs="0"/>
 *         &lt;element name="documentsList" type="{http://soprabanking.com/amplitude}modifyCustomerDocumentsRequest" minOccurs="0"/>
 *         &lt;element name="internationalOperationsList" type="{http://soprabanking.com/amplitude}modifyCustomerInternationalOperationsRequest" minOccurs="0"/>
 *         &lt;element name="defaultCustomer" type="{http://soprabanking.com/amplitude}customerDefaultCustomer" minOccurs="0"/>
 *         &lt;element name="kpp" type="{http://soprabanking.com/amplitude}customerKpp" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerRequest", propOrder = { "customerCode",
		"customerType", "language", "titleCode", "lastName", "nameToReturn",
		"freeFieldCode1", "freeFieldCode2", "freeFieldCode3", "memo",
		"externalIdentifier", "situation", "specificInformation",
		"generalAttributes", "reportingAttributes", "marketingAttributes",
		"fatcaAttributes", "paymentMethods", "adressesList",
		"phoneNumbersList", "emailAdressesList", "contactsList",
		"customerProfile", "customerAsset", "idPapersList", "documentsList",
		"internationalOperationsList", "defaultCustomer", "kpp" })
public class CreateCustomerRequest {

	protected String customerCode;
	@XmlElement(required = true)
	protected String customerType;
	protected String language;
	@XmlElement(required = true)
	protected String titleCode;
	protected String lastName;
	protected String nameToReturn;
	protected String freeFieldCode1;
	protected String freeFieldCode2;
	protected String freeFieldCode3;
	protected String memo;
	protected String externalIdentifier;
	protected CustomerSituationCreate situation;
	protected CustomerSpecInfoCreate specificInformation;
	protected CustomerGeneralAttributesCreate generalAttributes;
	protected CustomerReportingAttributesCreate reportingAttributes;
	protected CustomerMarketingAttributesCreate marketingAttributes;
	protected CustomerFatcaAttributesCreate fatcaAttributes;
	protected CustomerPaymentMethods paymentMethods;
	protected CustomerAddressesCreate adressesList;
	protected CustomerPhoneNumbersCreate phoneNumbersList;
	protected CustomerEmailAddressesCreate emailAdressesList;
	protected ModifyCustomerContactsRequest contactsList;
	protected CreateCustomerProfile customerProfile;
	protected ModifyCustomerAssetRequest customerAsset;
	protected CustomerIdPapersCreate idPapersList;
	protected ModifyCustomerDocumentsRequest documentsList;
	protected ModifyCustomerInternationalOperationsRequest internationalOperationsList;
	protected CustomerDefaultCustomer defaultCustomer;
	protected CustomerKpp kpp;

	/**
	 * Gets the value of the customerCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Sets the value of the customerCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomerCode(String value) {
		this.customerCode = value;
	}

	/**
	 * Gets the value of the customerType property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * Sets the value of the customerType property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomerType(String value) {
		this.customerType = value;
	}

	/**
	 * Gets the value of the language property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the value of the language property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLanguage(String value) {
		this.language = value;
	}

	/**
	 * Gets the value of the titleCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTitleCode() {
		return titleCode;
	}

	/**
	 * Sets the value of the titleCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTitleCode(String value) {
		this.titleCode = value;
	}

	/**
	 * Gets the value of the lastName property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the value of the lastName property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLastName(String value) {
		this.lastName = value;
	}

	/**
	 * Gets the value of the nameToReturn property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNameToReturn() {
		return nameToReturn;
	}

	/**
	 * Sets the value of the nameToReturn property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setNameToReturn(String value) {
		this.nameToReturn = value;
	}

	/**
	 * Gets the value of the freeFieldCode1 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFreeFieldCode1() {
		return freeFieldCode1;
	}

	/**
	 * Sets the value of the freeFieldCode1 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFreeFieldCode1(String value) {
		this.freeFieldCode1 = value;
	}

	/**
	 * Gets the value of the freeFieldCode2 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFreeFieldCode2() {
		return freeFieldCode2;
	}

	/**
	 * Sets the value of the freeFieldCode2 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFreeFieldCode2(String value) {
		this.freeFieldCode2 = value;
	}

	/**
	 * Gets the value of the freeFieldCode3 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFreeFieldCode3() {
		return freeFieldCode3;
	}

	/**
	 * Sets the value of the freeFieldCode3 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFreeFieldCode3(String value) {
		this.freeFieldCode3 = value;
	}

	/**
	 * Gets the value of the memo property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the value of the memo property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setMemo(String value) {
		this.memo = value;
	}

	/**
	 * Gets the value of the externalIdentifier property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getExternalIdentifier() {
		return externalIdentifier;
	}

	/**
	 * Sets the value of the externalIdentifier property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setExternalIdentifier(String value) {
		this.externalIdentifier = value;
	}

	/**
	 * Gets the value of the situation property.
	 *
	 * @return possible object is {@link CustomerSituationCreate }
	 *
	 */
	public CustomerSituationCreate getSituation() {
		return situation;
	}

	/**
	 * Sets the value of the situation property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerSituationCreate }
	 *
	 */
	public void setSituation(CustomerSituationCreate value) {
		this.situation = value;
	}

	/**
	 * Gets the value of the specificInformation property.
	 *
	 * @return possible object is {@link CustomerSpecInfoCreate }
	 *
	 */
	public CustomerSpecInfoCreate getSpecificInformation() {
		return specificInformation;
	}

	/**
	 * Sets the value of the specificInformation property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerSpecInfoCreate }
	 *
	 */
	public void setSpecificInformation(CustomerSpecInfoCreate value) {
		this.specificInformation = value;
	}

	/**
	 * Gets the value of the generalAttributes property.
	 *
	 * @return possible object is {@link CustomerGeneralAttributesCreate }
	 *
	 */
	public CustomerGeneralAttributesCreate getGeneralAttributes() {
		return generalAttributes;
	}

	/**
	 * Sets the value of the generalAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerGeneralAttributesCreate }
	 *
	 */
	public void setGeneralAttributes(CustomerGeneralAttributesCreate value) {
		this.generalAttributes = value;
	}

	/**
	 * Gets the value of the reportingAttributes property.
	 *
	 * @return possible object is {@link CustomerReportingAttributesCreate }
	 *
	 */
	public CustomerReportingAttributesCreate getReportingAttributes() {
		return reportingAttributes;
	}

	/**
	 * Sets the value of the reportingAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerReportingAttributesCreate }
	 *
	 */
	public void setReportingAttributes(CustomerReportingAttributesCreate value) {
		this.reportingAttributes = value;
	}

	/**
	 * Gets the value of the marketingAttributes property.
	 *
	 * @return possible object is {@link CustomerMarketingAttributesCreate }
	 *
	 */
	public CustomerMarketingAttributesCreate getMarketingAttributes() {
		return marketingAttributes;
	}

	/**
	 * Sets the value of the marketingAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerMarketingAttributesCreate }
	 *
	 */
	public void setMarketingAttributes(CustomerMarketingAttributesCreate value) {
		this.marketingAttributes = value;
	}

	/**
	 * Gets the value of the fatcaAttributes property.
	 *
	 * @return possible object is {@link CustomerFatcaAttributesCreate }
	 *
	 */
	public CustomerFatcaAttributesCreate getFatcaAttributes() {
		return fatcaAttributes;
	}

	/**
	 * Sets the value of the fatcaAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerFatcaAttributesCreate }
	 *
	 */
	public void setFatcaAttributes(CustomerFatcaAttributesCreate value) {
		this.fatcaAttributes = value;
	}

	/**
	 * Gets the value of the paymentMethods property.
	 *
	 * @return possible object is {@link CustomerPaymentMethods }
	 *
	 */
	public CustomerPaymentMethods getPaymentMethods() {
		return paymentMethods;
	}

	/**
	 * Sets the value of the paymentMethods property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerPaymentMethods }
	 *
	 */
	public void setPaymentMethods(CustomerPaymentMethods value) {
		this.paymentMethods = value;
	}

	/**
	 * Gets the value of the adressesList property.
	 *
	 * @return possible object is {@link CustomerAddressesCreate }
	 *
	 */
	public CustomerAddressesCreate getAdressesList() {
		return adressesList;
	}

	/**
	 * Sets the value of the adressesList property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerAddressesCreate }
	 *
	 */
	public void setAdressesList(CustomerAddressesCreate value) {
		this.adressesList = value;
	}

	/**
	 * Gets the value of the phoneNumbersList property.
	 *
	 * @return possible object is {@link CustomerPhoneNumbersCreate }
	 *
	 */
	public CustomerPhoneNumbersCreate getPhoneNumbersList() {
		return phoneNumbersList;
	}

	/**
	 * Sets the value of the phoneNumbersList property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerPhoneNumbersCreate }
	 *
	 */
	public void setPhoneNumbersList(CustomerPhoneNumbersCreate value) {
		this.phoneNumbersList = value;
	}

	/**
	 * Gets the value of the emailAdressesList property.
	 *
	 * @return possible object is {@link CustomerEmailAddressesCreate }
	 *
	 */
	public CustomerEmailAddressesCreate getEmailAdressesList() {
		return emailAdressesList;
	}

	/**
	 * Sets the value of the emailAdressesList property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerEmailAddressesCreate }
	 *
	 */
	public void setEmailAdressesList(CustomerEmailAddressesCreate value) {
		this.emailAdressesList = value;
	}

	/**
	 * Gets the value of the contactsList property.
	 *
	 * @return possible object is {@link ModifyCustomerContactsRequest }
	 *
	 */
	public ModifyCustomerContactsRequest getContactsList() {
		return contactsList;
	}

	/**
	 * Sets the value of the contactsList property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerContactsRequest }
	 *
	 */
	public void setContactsList(ModifyCustomerContactsRequest value) {
		this.contactsList = value;
	}

	/**
	 * Gets the value of the customerProfile property.
	 *
	 * @return possible object is {@link CreateCustomerProfile }
	 *
	 */
	public CreateCustomerProfile getCustomerProfile() {
		return customerProfile;
	}

	/**
	 * Sets the value of the customerProfile property.
	 *
	 * @param value
	 *            allowed object is {@link CreateCustomerProfile }
	 *
	 */
	public void setCustomerProfile(CreateCustomerProfile value) {
		this.customerProfile = value;
	}

	/**
	 * Gets the value of the customerAsset property.
	 *
	 * @return possible object is {@link ModifyCustomerAssetRequest }
	 *
	 */
	public ModifyCustomerAssetRequest getCustomerAsset() {
		return customerAsset;
	}

	/**
	 * Sets the value of the customerAsset property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerAssetRequest }
	 *
	 */
	public void setCustomerAsset(ModifyCustomerAssetRequest value) {
		this.customerAsset = value;
	}

	/**
	 * Gets the value of the idPapersList property.
	 *
	 * @return possible object is {@link CustomerIdPapersCreate }
	 *
	 */
	public CustomerIdPapersCreate getIdPapersList() {
		return idPapersList;
	}

	/**
	 * Sets the value of the idPapersList property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerIdPapersCreate }
	 *
	 */
	public void setIdPapersList(CustomerIdPapersCreate value) {
		this.idPapersList = value;
	}

	/**
	 * Gets the value of the documentsList property.
	 *
	 * @return possible object is {@link ModifyCustomerDocumentsRequest }
	 *
	 */
	public ModifyCustomerDocumentsRequest getDocumentsList() {
		return documentsList;
	}

	/**
	 * Sets the value of the documentsList property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerDocumentsRequest }
	 *
	 */
	public void setDocumentsList(ModifyCustomerDocumentsRequest value) {
		this.documentsList = value;
	}

	/**
	 * Gets the value of the internationalOperationsList property.
	 *
	 * @return possible object is
	 *         {@link ModifyCustomerInternationalOperationsRequest }
	 *
	 */
	public ModifyCustomerInternationalOperationsRequest getInternationalOperationsList() {
		return internationalOperationsList;
	}

	/**
	 * Sets the value of the internationalOperationsList property.
	 *
	 * @param value
	 *            allowed object is
	 *            {@link ModifyCustomerInternationalOperationsRequest }
	 *
	 */
	public void setInternationalOperationsList(
			ModifyCustomerInternationalOperationsRequest value) {
		this.internationalOperationsList = value;
	}

	/**
	 * Gets the value of the defaultCustomer property.
	 *
	 * @return possible object is {@link CustomerDefaultCustomer }
	 *
	 */
	public CustomerDefaultCustomer getDefaultCustomer() {
		return defaultCustomer;
	}

	/**
	 * Sets the value of the defaultCustomer property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerDefaultCustomer }
	 *
	 */
	public void setDefaultCustomer(CustomerDefaultCustomer value) {
		this.defaultCustomer = value;
	}

	/**
	 * Gets the value of the kpp property.
	 *
	 * @return possible object is {@link CustomerKpp }
	 *
	 */
	public CustomerKpp getKpp() {
		return kpp;
	}

	/**
	 * Sets the value of the kpp property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerKpp }
	 *
	 */
	public void setKpp(CustomerKpp value) {
		this.kpp = value;
	}

}
