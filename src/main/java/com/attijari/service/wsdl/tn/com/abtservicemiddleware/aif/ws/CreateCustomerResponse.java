package com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws;

import javax.xml.bind.annotation.*;

/**
 * <p>
 * Java class for createCustomerResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResponseDocument" type="{http://ws.AIF.abtServiceMiddleware.com.tn/}CreateCustomerAIFResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerResponse", propOrder = { "responseDocument" })
public class CreateCustomerResponse {

	@XmlElement(name = "ResponseDocument")
	protected CreateCustomerAIFResponse responseDocument;

	/**
	 * Gets the value of the responseDocument property.
	 *
	 * @return possible object is {@link CreateCustomerAIFResponse }
	 *
	 */
	public CreateCustomerAIFResponse getResponseDocument() {
		return responseDocument;
	}

	/**
	 * Sets the value of the responseDocument property.
	 *
	 * @param value
	 *            allowed object is {@link CreateCustomerAIFResponse }
	 *
	 */
	public void setResponseDocument(CreateCustomerAIFResponse value) {
		this.responseDocument = value;
	}

}
