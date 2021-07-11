package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createCustomerEmailAddressRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerEmailAddressRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://soprabanking.com/amplitude}emailCreateIdentifier"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerEmailAddressRequest", propOrder = {
		"identifier", "email" })
public class CreateCustomerEmailAddressRequest {

	@XmlElement(required = true)
	protected EmailCreateIdentifier identifier;
	@XmlElement(required = true)
	protected String email;

	/**
	 * Gets the value of the identifier property.
	 *
	 * @return possible object is {@link EmailCreateIdentifier }
	 *
	 */
	public EmailCreateIdentifier getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the value of the identifier property.
	 *
	 * @param value
	 *            allowed object is {@link EmailCreateIdentifier }
	 *
	 */
	public void setIdentifier(EmailCreateIdentifier value) {
		this.identifier = value;
	}

	/**
	 * Gets the value of the email property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setEmail(String value) {
		this.email = value;
	}

}
