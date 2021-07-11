package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createCustomerRequestFlow complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerRequestFlow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestHeader" type="{http://soprabanking.com/amplitude}requestHeader"/>
 *         &lt;element name="createCustomerRequest" type="{http://soprabanking.com/amplitude}createCustomerRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerRequestFlow", propOrder = { "requestHeader",
		"createCustomerRequest" })
public class CreateCustomerRequestFlow {

	@XmlElement(required = true)
	protected RequestHeader requestHeader;
	@XmlElement(required = true)
	protected CreateCustomerRequest createCustomerRequest;

	/**
	 * Gets the value of the requestHeader property.
	 *
	 * @return possible object is {@link RequestHeader }
	 *
	 */
	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	/**
	 * Sets the value of the requestHeader property.
	 *
	 * @param value
	 *            allowed object is {@link RequestHeader }
	 *
	 */
	public void setRequestHeader(RequestHeader value) {
		this.requestHeader = value;
	}

	/**
	 * Gets the value of the createCustomerRequest property.
	 *
	 * @return possible object is {@link CreateCustomerRequest }
	 *
	 */
	public CreateCustomerRequest getCreateCustomerRequest() {
		return createCustomerRequest;
	}

	/**
	 * Sets the value of the createCustomerRequest property.
	 *
	 * @param value
	 *            allowed object is {@link CreateCustomerRequest }
	 *
	 */
	public void setCreateCustomerRequest(CreateCustomerRequest value) {
		this.createCustomerRequest = value;
	}

}
