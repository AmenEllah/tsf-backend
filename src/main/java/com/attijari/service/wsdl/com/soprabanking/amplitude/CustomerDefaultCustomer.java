package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerDefaultCustomer complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerDefaultCustomer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retail" type="{http://soprabanking.com/amplitude}customerRetail" minOccurs="0"/>
 *         &lt;element name="nonRetail" type="{http://soprabanking.com/amplitude}customerNonRetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerDefaultCustomer", propOrder = { "retail", "nonRetail" })
public class CustomerDefaultCustomer {

	protected CustomerRetail retail;
	protected CustomerNonRetail nonRetail;

	/**
	 * Gets the value of the retail property.
	 *
	 * @return possible object is {@link CustomerRetail }
	 *
	 */
	public CustomerRetail getRetail() {
		return retail;
	}

	/**
	 * Sets the value of the retail property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerRetail }
	 *
	 */
	public void setRetail(CustomerRetail value) {
		this.retail = value;
	}

	/**
	 * Gets the value of the nonRetail property.
	 *
	 * @return possible object is {@link CustomerNonRetail }
	 *
	 */
	public CustomerNonRetail getNonRetail() {
		return nonRetail;
	}

	/**
	 * Sets the value of the nonRetail property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerNonRetail }
	 *
	 */
	public void setNonRetail(CustomerNonRetail value) {
		this.nonRetail = value;
	}

}
