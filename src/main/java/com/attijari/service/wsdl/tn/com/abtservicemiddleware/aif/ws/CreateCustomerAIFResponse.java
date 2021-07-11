package com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws;

import com.attijari.service.wsdl.com.soprabanking.amplitude.CreateCustomerResponseFlow;

import javax.xml.bind.annotation.*;

/**
 * <p>
 * Java class for CreateCustomerAIFResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CreateCustomerAIFResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocumentHeader" type="{http://ws.AIF.abtServiceMiddleware.com.tn/}DocumentHeader" minOccurs="0"/>
 *         &lt;element name="createCustomerResponseFlow" type="{http://soprabanking.com/amplitude}createCustomerResponseFlow" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateCustomerAIFResponse", propOrder = { "documentHeader",
		"createCustomerResponseFlow" })
public class CreateCustomerAIFResponse {

	@XmlElement(name = "DocumentHeader")
	protected DocumentHeader documentHeader;
	protected CreateCustomerResponseFlow createCustomerResponseFlow;

	/**
	 * Gets the value of the documentHeader property.
	 *
	 * @return possible object is {@link DocumentHeader }
	 *
	 */
	public DocumentHeader getDocumentHeader() {
		return documentHeader;
	}

	/**
	 * Sets the value of the documentHeader property.
	 *
	 * @param value
	 *            allowed object is {@link DocumentHeader }
	 *
	 */
	public void setDocumentHeader(DocumentHeader value) {
		this.documentHeader = value;
	}

	/**
	 * Gets the value of the createCustomerResponseFlow property.
	 *
	 * @return possible object is {@link CreateCustomerResponseFlow }
	 *
	 */
	public CreateCustomerResponseFlow getCreateCustomerResponseFlow() {
		return createCustomerResponseFlow;
	}

	/**
	 * Sets the value of the createCustomerResponseFlow property.
	 *
	 * @param value
	 *            allowed object is {@link CreateCustomerResponseFlow }
	 *
	 */
	public void setCreateCustomerResponseFlow(CreateCustomerResponseFlow value) {
		this.createCustomerResponseFlow = value;
	}

}
