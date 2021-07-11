package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerCorporateId complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerCorporateId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nationalIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialIdentityNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxIdentityNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="abbreviation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerCorporateId", propOrder = { "nationalIdentifier",
		"socialIdentityNumber", "taxIdentityNumber", "abbreviation" })
public class CustomerCorporateId {

	protected String nationalIdentifier;
	protected String socialIdentityNumber;
	protected String taxIdentityNumber;
	protected String abbreviation;

	/**
	 * Gets the value of the nationalIdentifier property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNationalIdentifier() {
		return nationalIdentifier;
	}

	/**
	 * Sets the value of the nationalIdentifier property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setNationalIdentifier(String value) {
		this.nationalIdentifier = value;
	}

	/**
	 * Gets the value of the socialIdentityNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSocialIdentityNumber() {
		return socialIdentityNumber;
	}

	/**
	 * Sets the value of the socialIdentityNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSocialIdentityNumber(String value) {
		this.socialIdentityNumber = value;
	}

	/**
	 * Gets the value of the taxIdentityNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTaxIdentityNumber() {
		return taxIdentityNumber;
	}

	/**
	 * Sets the value of the taxIdentityNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTaxIdentityNumber(String value) {
		this.taxIdentityNumber = value;
	}

	/**
	 * Gets the value of the abbreviation property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * Sets the value of the abbreviation property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAbbreviation(String value) {
		this.abbreviation = value;
	}

}
