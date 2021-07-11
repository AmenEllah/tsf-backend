package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for createCustomerAddressRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerAddressRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://soprabanking.com/amplitude}addressCreateIdentifier"/>
 *         &lt;element name="languageCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressFormat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryOffice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="poBox" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalSector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalSectorDesignation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="branchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locker" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provisionalAddressStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="provisionalAddressEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="geographicalDepartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="county" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerAddressRequest", propOrder = { "identifier",
		"languageCode", "addressFormat", "addressLine1", "addressLine2",
		"addressLine3", "city", "postalCode", "deliveryOffice", "poBox",
		"postalSector", "postalSectorDesignation", "countryCode", "branchCode",
		"locker", "serviceCode", "transportTypeCode", "emailAddress",
		"provisionalAddressStartDate", "provisionalAddressEndDate",
		"geographicalDepartment", "county" })
public class CreateCustomerAddressRequest {

	@XmlElement(required = true)
	protected AddressCreateIdentifier identifier;
	@XmlElement(required = true)
	protected String languageCode;
	@XmlElement(required = true)
	protected String addressFormat;
	protected String addressLine1;
	protected String addressLine2;
	protected String addressLine3;
	protected String city;
	protected String postalCode;
	protected String deliveryOffice;
	protected String poBox;
	protected String postalSector;
	protected String postalSectorDesignation;
	protected String countryCode;
	protected String branchCode;
	protected String locker;
	protected String serviceCode;
	protected String transportTypeCode;
	protected String emailAddress;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar provisionalAddressStartDate;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar provisionalAddressEndDate;
	protected String geographicalDepartment;
	protected String county;

	/**
	 * Gets the value of the identifier property.
	 *
	 * @return possible object is {@link AddressCreateIdentifier }
	 *
	 */
	public AddressCreateIdentifier getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the value of the identifier property.
	 *
	 * @param value
	 *            allowed object is {@link AddressCreateIdentifier }
	 *
	 */
	public void setIdentifier(AddressCreateIdentifier value) {
		this.identifier = value;
	}

	/**
	 * Gets the value of the languageCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * Sets the value of the languageCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLanguageCode(String value) {
		this.languageCode = value;
	}

	/**
	 * Gets the value of the addressFormat property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAddressFormat() {
		return addressFormat;
	}

	/**
	 * Sets the value of the addressFormat property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAddressFormat(String value) {
		this.addressFormat = value;
	}

	/**
	 * Gets the value of the addressLine1 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * Sets the value of the addressLine1 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAddressLine1(String value) {
		this.addressLine1 = value;
	}

	/**
	 * Gets the value of the addressLine2 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * Sets the value of the addressLine2 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAddressLine2(String value) {
		this.addressLine2 = value;
	}

	/**
	 * Gets the value of the addressLine3 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAddressLine3() {
		return addressLine3;
	}

	/**
	 * Sets the value of the addressLine3 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAddressLine3(String value) {
		this.addressLine3 = value;
	}

	/**
	 * Gets the value of the city property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the value of the city property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCity(String value) {
		this.city = value;
	}

	/**
	 * Gets the value of the postalCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the value of the postalCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	/**
	 * Gets the value of the deliveryOffice property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDeliveryOffice() {
		return deliveryOffice;
	}

	/**
	 * Sets the value of the deliveryOffice property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDeliveryOffice(String value) {
		this.deliveryOffice = value;
	}

	/**
	 * Gets the value of the poBox property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPoBox() {
		return poBox;
	}

	/**
	 * Sets the value of the poBox property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPoBox(String value) {
		this.poBox = value;
	}

	/**
	 * Gets the value of the postalSector property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPostalSector() {
		return postalSector;
	}

	/**
	 * Sets the value of the postalSector property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPostalSector(String value) {
		this.postalSector = value;
	}

	/**
	 * Gets the value of the postalSectorDesignation property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPostalSectorDesignation() {
		return postalSectorDesignation;
	}

	/**
	 * Sets the value of the postalSectorDesignation property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPostalSectorDesignation(String value) {
		this.postalSectorDesignation = value;
	}

	/**
	 * Gets the value of the countryCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the value of the countryCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCountryCode(String value) {
		this.countryCode = value;
	}

	/**
	 * Gets the value of the branchCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * Sets the value of the branchCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setBranchCode(String value) {
		this.branchCode = value;
	}

	/**
	 * Gets the value of the locker property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLocker() {
		return locker;
	}

	/**
	 * Sets the value of the locker property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLocker(String value) {
		this.locker = value;
	}

	/**
	 * Gets the value of the serviceCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * Sets the value of the serviceCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setServiceCode(String value) {
		this.serviceCode = value;
	}

	/**
	 * Gets the value of the transportTypeCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTransportTypeCode() {
		return transportTypeCode;
	}

	/**
	 * Sets the value of the transportTypeCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTransportTypeCode(String value) {
		this.transportTypeCode = value;
	}

	/**
	 * Gets the value of the emailAddress property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the value of the emailAddress property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setEmailAddress(String value) {
		this.emailAddress = value;
	}

	/**
	 * Gets the value of the provisionalAddressStartDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getProvisionalAddressStartDate() {
		return provisionalAddressStartDate;
	}

	/**
	 * Sets the value of the provisionalAddressStartDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setProvisionalAddressStartDate(XMLGregorianCalendar value) {
		this.provisionalAddressStartDate = value;
	}

	/**
	 * Gets the value of the provisionalAddressEndDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getProvisionalAddressEndDate() {
		return provisionalAddressEndDate;
	}

	/**
	 * Sets the value of the provisionalAddressEndDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setProvisionalAddressEndDate(XMLGregorianCalendar value) {
		this.provisionalAddressEndDate = value;
	}

	/**
	 * Gets the value of the geographicalDepartment property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getGeographicalDepartment() {
		return geographicalDepartment;
	}

	/**
	 * Sets the value of the geographicalDepartment property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setGeographicalDepartment(String value) {
		this.geographicalDepartment = value;
	}

	/**
	 * Gets the value of the county property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * Sets the value of the county property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCounty(String value) {
		this.county = value;
	}

}
