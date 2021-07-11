package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerOrThirdPartyCoHolder complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerOrThirdPartyCoHolder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coHolderType" type="{http://soprabanking.com/amplitude}customerOrThirdPartyType"/>
 *         &lt;element name="jointCustomerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerOrThirdPartyCoHolder", propOrder = { "coHolderType",
		"jointCustomerCode" })
public class CustomerOrThirdPartyCoHolder {

	@XmlElement(required = true)
	protected CustomerOrThirdPartyType coHolderType;
	@XmlElement(required = true)
	protected String jointCustomerCode;

	/**
	 * Gets the value of the coHolderType property.
	 *
	 * @return possible object is {@link CustomerOrThirdPartyType }
	 *
	 */
	public CustomerOrThirdPartyType getCoHolderType() {
		return coHolderType;
	}

	/**
	 * Sets the value of the coHolderType property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerOrThirdPartyType }
	 *
	 */
	public void setCoHolderType(CustomerOrThirdPartyType value) {
		this.coHolderType = value;
	}

	/**
	 * Gets the value of the jointCustomerCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getJointCustomerCode() {
		return jointCustomerCode;
	}

	/**
	 * Sets the value of the jointCustomerCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setJointCustomerCode(String value) {
		this.jointCustomerCode = value;
	}

}
