package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerCreditInfoCentreCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerCreditInfoCentreCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerRelationshipRisk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditInfoCentreRegistrationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditInfoCentreCodeToDeclare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditInfoCentreKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerCreditInfoCentreCreate", propOrder = {
		"customerRelationshipRisk", "creditInfoCentreRegistrationNumber",
		"creditInfoCentreCodeToDeclare", "creditInfoCentreKey" })
public class CustomerCreditInfoCentreCreate {

	protected String customerRelationshipRisk;
	protected String creditInfoCentreRegistrationNumber;
	protected String creditInfoCentreCodeToDeclare;
	protected String creditInfoCentreKey;

	/**
	 * Gets the value of the customerRelationshipRisk property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomerRelationshipRisk() {
		return customerRelationshipRisk;
	}

	/**
	 * Sets the value of the customerRelationshipRisk property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomerRelationshipRisk(String value) {
		this.customerRelationshipRisk = value;
	}

	/**
	 * Gets the value of the creditInfoCentreRegistrationNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCreditInfoCentreRegistrationNumber() {
		return creditInfoCentreRegistrationNumber;
	}

	/**
	 * Sets the value of the creditInfoCentreRegistrationNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCreditInfoCentreRegistrationNumber(String value) {
		this.creditInfoCentreRegistrationNumber = value;
	}

	/**
	 * Gets the value of the creditInfoCentreCodeToDeclare property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCreditInfoCentreCodeToDeclare() {
		return creditInfoCentreCodeToDeclare;
	}

	/**
	 * Sets the value of the creditInfoCentreCodeToDeclare property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCreditInfoCentreCodeToDeclare(String value) {
		this.creditInfoCentreCodeToDeclare = value;
	}

	/**
	 * Gets the value of the creditInfoCentreKey property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCreditInfoCentreKey() {
		return creditInfoCentreKey;
	}

	/**
	 * Sets the value of the creditInfoCentreKey property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCreditInfoCentreKey(String value) {
		this.creditInfoCentreKey = value;
	}

}
