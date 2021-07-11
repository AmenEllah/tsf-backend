package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for shareholderCreateIdentifier complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="shareholderCreateIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shareholderCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shareholderType" type="{http://soprabanking.com/amplitude}customerOrThirdPartyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shareholderCreateIdentifier", propOrder = { "customerCode",
		"shareholderCode", "shareholderType" })
public class ShareholderCreateIdentifier {

	protected String customerCode;
	@XmlElement(required = true)
	protected String shareholderCode;
	@XmlElement(required = true)
	protected CustomerOrThirdPartyType shareholderType;

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
	 * Gets the value of the shareholderCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getShareholderCode() {
		return shareholderCode;
	}

	/**
	 * Sets the value of the shareholderCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setShareholderCode(String value) {
		this.shareholderCode = value;
	}

	/**
	 * Gets the value of the shareholderType property.
	 *
	 * @return possible object is {@link CustomerOrThirdPartyType }
	 *
	 */
	public CustomerOrThirdPartyType getShareholderType() {
		return shareholderType;
	}

	/**
	 * Sets the value of the shareholderType property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerOrThirdPartyType }
	 *
	 */
	public void setShareholderType(CustomerOrThirdPartyType value) {
		this.shareholderType = value;
	}

}
