package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerCoHolderInformations complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerCoHolderInformations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerOrThirdParty" type="{http://soprabanking.com/amplitude}customerOrThirdPartyCoHolder" minOccurs="0"/>
 *         &lt;element name="otherCoHolderType" type="{http://soprabanking.com/amplitude}otherCoHolderType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerCoHolderInformations", propOrder = {
		"customerOrThirdParty", "otherCoHolderType" })
public class CustomerCoHolderInformations {

	protected CustomerOrThirdPartyCoHolder customerOrThirdParty;
	protected OtherCoHolderType otherCoHolderType;

	/**
	 * Gets the value of the customerOrThirdParty property.
	 *
	 * @return possible object is {@link CustomerOrThirdPartyCoHolder }
	 *
	 */
	public CustomerOrThirdPartyCoHolder getCustomerOrThirdParty() {
		return customerOrThirdParty;
	}

	/**
	 * Sets the value of the customerOrThirdParty property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerOrThirdPartyCoHolder }
	 *
	 */
	public void setCustomerOrThirdParty(CustomerOrThirdPartyCoHolder value) {
		this.customerOrThirdParty = value;
	}

	/**
	 * Gets the value of the otherCoHolderType property.
	 *
	 * @return possible object is {@link OtherCoHolderType }
	 *
	 */
	public OtherCoHolderType getOtherCoHolderType() {
		return otherCoHolderType;
	}

	/**
	 * Sets the value of the otherCoHolderType property.
	 *
	 * @param value
	 *            allowed object is {@link OtherCoHolderType }
	 *
	 */
	public void setOtherCoHolderType(OtherCoHolderType value) {
		this.otherCoHolderType = value;
	}

}
