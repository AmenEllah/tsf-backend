package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createCustomerFreeAttributeRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerFreeAttributeRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalData" type="{http://soprabanking.com/amplitude}additionalData"/>
 *         &lt;element name="freeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerFreeAttributeRequest", propOrder = {
		"customerCode", "additionalData", "freeText" })
public class CreateCustomerFreeAttributeRequest {

	protected String customerCode;
	@XmlElement(required = true)
	protected AdditionalData additionalData;
	protected String freeText;

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
	 * Gets the value of the additionalData property.
	 *
	 * @return possible object is {@link AdditionalData }
	 *
	 */
	public AdditionalData getAdditionalData() {
		return additionalData;
	}

	/**
	 * Sets the value of the additionalData property.
	 *
	 * @param value
	 *            allowed object is {@link AdditionalData }
	 *
	 */
	public void setAdditionalData(AdditionalData value) {
		this.additionalData = value;
	}

	/**
	 * Gets the value of the freeText property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFreeText() {
		return freeText;
	}

	/**
	 * Sets the value of the freeText property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFreeText(String value) {
		this.freeText = value;
	}

}
