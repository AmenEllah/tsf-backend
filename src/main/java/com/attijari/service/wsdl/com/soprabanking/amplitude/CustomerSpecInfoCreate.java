package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerSpecInfoCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerSpecInfoCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="individualSpecInfo" type="{http://soprabanking.com/amplitude}customerIndividualSpecInfoCreate" minOccurs="0"/>
 *         &lt;element name="corporateSpecInfo" type="{http://soprabanking.com/amplitude}customerCorporateSpecInfoCreate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerSpecInfoCreate", propOrder = { "individualSpecInfo",
		"corporateSpecInfo" })
public class CustomerSpecInfoCreate {

	protected CustomerIndividualSpecInfoCreate individualSpecInfo;
	protected CustomerCorporateSpecInfoCreate corporateSpecInfo;

	/**
	 * Gets the value of the individualSpecInfo property.
	 *
	 * @return possible object is {@link CustomerIndividualSpecInfoCreate }
	 *
	 */
	public CustomerIndividualSpecInfoCreate getIndividualSpecInfo() {
		return individualSpecInfo;
	}

	/**
	 * Sets the value of the individualSpecInfo property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerIndividualSpecInfoCreate }
	 *
	 */
	public void setIndividualSpecInfo(CustomerIndividualSpecInfoCreate value) {
		this.individualSpecInfo = value;
	}

	/**
	 * Gets the value of the corporateSpecInfo property.
	 *
	 * @return possible object is {@link CustomerCorporateSpecInfoCreate }
	 *
	 */
	public CustomerCorporateSpecInfoCreate getCorporateSpecInfo() {
		return corporateSpecInfo;
	}

	/**
	 * Sets the value of the corporateSpecInfo property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerCorporateSpecInfoCreate }
	 *
	 */
	public void setCorporateSpecInfo(CustomerCorporateSpecInfoCreate value) {
		this.corporateSpecInfo = value;
	}

}
