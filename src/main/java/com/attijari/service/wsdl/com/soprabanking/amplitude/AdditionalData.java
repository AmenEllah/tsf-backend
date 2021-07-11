package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for additionalData complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="additionalData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="value" type="{http://soprabanking.com/amplitude}additionalDataValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "additionalData", propOrder = { "identifier", "value" })
public class AdditionalData {

	@XmlElement(required = true)
	protected String identifier;
	protected AdditionalDataValue value;

	/**
	 * Gets the value of the identifier property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the value of the identifier property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setIdentifier(String value) {
		this.identifier = value;
	}

	/**
	 * Gets the value of the value property.
	 *
	 * @return possible object is {@link AdditionalDataValue }
	 *
	 */
	public AdditionalDataValue getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 *
	 * @param value
	 *            allowed object is {@link AdditionalDataValue }
	 *
	 */
	public void setValue(AdditionalDataValue value) {
		this.value = value;
	}

}
