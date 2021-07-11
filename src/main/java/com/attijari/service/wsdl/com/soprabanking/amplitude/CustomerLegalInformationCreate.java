package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerLegalInformationCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerLegalInformationCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeRegisterNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryDateOfTradeRegister" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="tradeRegisterDeliveryPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validityDateOfTradeRegister" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="chamberOfCommerceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licenseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validityDateOfLicense" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerLegalInformationCreate", propOrder = {
		"tradeRegisterNumber", "deliveryDateOfTradeRegister",
		"tradeRegisterDeliveryPlace", "validityDateOfTradeRegister",
		"chamberOfCommerceCode", "licenseNumber", "validityDateOfLicense" })
public class CustomerLegalInformationCreate {

	protected String tradeRegisterNumber;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar deliveryDateOfTradeRegister;
	protected String tradeRegisterDeliveryPlace;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar validityDateOfTradeRegister;
	protected String chamberOfCommerceCode;
	protected String licenseNumber;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar validityDateOfLicense;

	/**
	 * Gets the value of the tradeRegisterNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTradeRegisterNumber() {
		return tradeRegisterNumber;
	}

	/**
	 * Sets the value of the tradeRegisterNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTradeRegisterNumber(String value) {
		this.tradeRegisterNumber = value;
	}

	/**
	 * Gets the value of the deliveryDateOfTradeRegister property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getDeliveryDateOfTradeRegister() {
		return deliveryDateOfTradeRegister;
	}

	/**
	 * Sets the value of the deliveryDateOfTradeRegister property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setDeliveryDateOfTradeRegister(XMLGregorianCalendar value) {
		this.deliveryDateOfTradeRegister = value;
	}

	/**
	 * Gets the value of the tradeRegisterDeliveryPlace property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTradeRegisterDeliveryPlace() {
		return tradeRegisterDeliveryPlace;
	}

	/**
	 * Sets the value of the tradeRegisterDeliveryPlace property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTradeRegisterDeliveryPlace(String value) {
		this.tradeRegisterDeliveryPlace = value;
	}

	/**
	 * Gets the value of the validityDateOfTradeRegister property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getValidityDateOfTradeRegister() {
		return validityDateOfTradeRegister;
	}

	/**
	 * Sets the value of the validityDateOfTradeRegister property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setValidityDateOfTradeRegister(XMLGregorianCalendar value) {
		this.validityDateOfTradeRegister = value;
	}

	/**
	 * Gets the value of the chamberOfCommerceCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getChamberOfCommerceCode() {
		return chamberOfCommerceCode;
	}

	/**
	 * Sets the value of the chamberOfCommerceCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setChamberOfCommerceCode(String value) {
		this.chamberOfCommerceCode = value;
	}

	/**
	 * Gets the value of the licenseNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/**
	 * Sets the value of the licenseNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLicenseNumber(String value) {
		this.licenseNumber = value;
	}

	/**
	 * Gets the value of the validityDateOfLicense property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getValidityDateOfLicense() {
		return validityDateOfLicense;
	}

	/**
	 * Sets the value of the validityDateOfLicense property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setValidityDateOfLicense(XMLGregorianCalendar value) {
		this.validityDateOfLicense = value;
	}

}
