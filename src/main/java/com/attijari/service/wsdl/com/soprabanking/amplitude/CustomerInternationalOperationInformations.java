package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerInternationalOperationInformations complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerInternationalOperationInformations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerInternationalOperationType" type="{http://soprabanking.com/amplitude}customerInternationalOperationType"/>
 *         &lt;element name="customerInternationalOperationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerInternationalOperationInformations", propOrder = {
		"customerInternationalOperationType",
		"customerInternationalOperationCode" })
public class CustomerInternationalOperationInformations {

	@XmlElement(required = true)
	protected CustomerInternationalOperationType customerInternationalOperationType;
	@XmlElement(required = true)
	protected String customerInternationalOperationCode;

	/**
	 * Gets the value of the customerInternationalOperationType property.
	 *
	 * @return possible object is {@link CustomerInternationalOperationType }
	 *
	 */
	public CustomerInternationalOperationType getCustomerInternationalOperationType() {
		return customerInternationalOperationType;
	}

	/**
	 * Sets the value of the customerInternationalOperationType property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerInternationalOperationType }
	 *
	 */
	public void setCustomerInternationalOperationType(
			CustomerInternationalOperationType value) {
		this.customerInternationalOperationType = value;
	}

	/**
	 * Gets the value of the customerInternationalOperationCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomerInternationalOperationCode() {
		return customerInternationalOperationCode;
	}

	/**
	 * Sets the value of the customerInternationalOperationCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomerInternationalOperationCode(String value) {
		this.customerInternationalOperationCode = value;
	}

}
