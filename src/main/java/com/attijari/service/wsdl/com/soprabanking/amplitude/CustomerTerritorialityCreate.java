package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerTerritorialityCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerTerritorialityCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="territorialityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spouseTerritorialityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerTerritorialityCreate", propOrder = {
		"territorialityCode", "spouseTerritorialityCode" })
public class CustomerTerritorialityCreate {

	protected String territorialityCode;
	protected String spouseTerritorialityCode;

	/**
	 * Gets the value of the territorialityCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTerritorialityCode() {
		return territorialityCode;
	}

	/**
	 * Sets the value of the territorialityCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTerritorialityCode(String value) {
		this.territorialityCode = value;
	}

	/**
	 * Gets the value of the spouseTerritorialityCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSpouseTerritorialityCode() {
		return spouseTerritorialityCode;
	}

	/**
	 * Sets the value of the spouseTerritorialityCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSpouseTerritorialityCode(String value) {
		this.spouseTerritorialityCode = value;
	}

}
