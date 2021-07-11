package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerMarketingAttributesCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerMarketingAttributesCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="freeAttributes" type="{http://soprabanking.com/amplitude}customerFreeAttributesCreate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerMarketingAttributesCreate", propOrder = { "freeAttributes" })
public class CustomerMarketingAttributesCreate {

	protected CustomerFreeAttributesCreate freeAttributes;

	/**
	 * Gets the value of the freeAttributes property.
	 *
	 * @return possible object is {@link CustomerFreeAttributesCreate }
	 *
	 */
	public CustomerFreeAttributesCreate getFreeAttributes() {
		return freeAttributes;
	}

	/**
	 * Sets the value of the freeAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerFreeAttributesCreate }
	 *
	 */
	public void setFreeAttributes(CustomerFreeAttributesCreate value) {
		this.freeAttributes = value;
	}

}
