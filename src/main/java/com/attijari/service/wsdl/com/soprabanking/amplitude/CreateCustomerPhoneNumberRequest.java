package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createCustomerPhoneNumberRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerPhoneNumberRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://soprabanking.com/amplitude}phoneNumberCreateIdentifier"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerPhoneNumberRequest", propOrder = { "identifier",
		"phoneNumber", "format" })
public class CreateCustomerPhoneNumberRequest {

	@XmlElement(required = true)
	protected PhoneNumberCreateIdentifier identifier;
	@XmlElement(required = true)
	protected String phoneNumber;
	protected String format;

	/**
	 * Gets the value of the identifier property.
	 *
	 * @return possible object is {@link PhoneNumberCreateIdentifier }
	 *
	 */
	public PhoneNumberCreateIdentifier getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the value of the identifier property.
	 *
	 * @param value
	 *            allowed object is {@link PhoneNumberCreateIdentifier }
	 *
	 */
	public void setIdentifier(PhoneNumberCreateIdentifier value) {
		this.identifier = value;
	}

	/**
	 * Gets the value of the phoneNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the value of the phoneNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	/**
	 * Gets the value of the format property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the value of the format property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFormat(String value) {
		this.format = value;
	}

}
