package com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws;

import javax.xml.bind.annotation.*;

import com.attijari.service.wsdl.com.soprabanking.amplitude.CreateCustomerRequestFlow;

/**
 * <p>
 * Java class for createCustomer complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CredentialCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreateCustomerRequestFlow" type="{http://soprabanking.com/amplitude}createCustomerRequestFlow" minOccurs="0"/>
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
@XmlType(name = "createCustomer", propOrder = { "credentialCode",
		"createCustomerRequestFlow" })
public class CreateCustomer {

	@XmlElement(name = "CredentialCode")
	protected String credentialCode;
	@XmlElement(name = "CreateCustomerRequestFlow")
	protected CreateCustomerRequestFlow createCustomerRequestFlow;



	/**
	 * Gets the value of the credentialCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCredentialCode() {
		return credentialCode;
	}

	/**
	 * Sets the value of the credentialCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCredentialCode(String value) {
		this.credentialCode = value;
	}

	/**
	 * Gets the value of the createCustomerRequestFlow property.
	 *
	 * @return possible object is {@link CreateCustomerRequestFlow }
	 *
	 */
	public CreateCustomerRequestFlow getCreateCustomerRequestFlow() {
		return createCustomerRequestFlow;
	}

	/**
	 * Sets the value of the createCustomerRequestFlow property.
	 *
	 * @param value
	 *            allowed object is {@link CreateCustomerRequestFlow }
	 *
	 */
	public void setCreateCustomerRequestFlow(CreateCustomerRequestFlow value) {
		this.createCustomerRequestFlow = value;
	}

}
