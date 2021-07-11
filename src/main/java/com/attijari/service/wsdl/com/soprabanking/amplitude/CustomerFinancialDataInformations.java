package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerFinancialDataInformations complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerFinancialDataInformations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="values" type="{http://soprabanking.com/amplitude}customerFinancialDataValues"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerFinancialDataInformations", propOrder = { "dataType",
		"values" })
public class CustomerFinancialDataInformations {

	@XmlElement(required = true)
	protected String dataType;
	@XmlElement(required = true)
	protected CustomerFinancialDataValues values;

	/**
	 * Gets the value of the dataType property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * Sets the value of the dataType property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDataType(String value) {
		this.dataType = value;
	}

	/**
	 * Gets the value of the values property.
	 *
	 * @return possible object is {@link CustomerFinancialDataValues }
	 *
	 */
	public CustomerFinancialDataValues getValues() {
		return values;
	}

	/**
	 * Sets the value of the values property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerFinancialDataValues }
	 *
	 */
	public void setValues(CustomerFinancialDataValues value) {
		this.values = value;
	}

}
