package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerJointAccountInformations complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerJointAccountInformations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coHolderInformations" type="{http://soprabanking.com/amplitude}customerCoHolderInformations"/>
 *         &lt;element name="linkTyp" type="{http://soprabanking.com/amplitude}linkTyp"/>
 *         &lt;element name="toBePrintedInTheAddress" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerJointAccountInformations", propOrder = {
		"coHolderInformations", "linkTyp", "toBePrintedInTheAddress" })
public class CustomerJointAccountInformations {

	@XmlElement(required = true)
	protected CustomerCoHolderInformations coHolderInformations;
	@XmlElement(required = true)
	protected LinkTyp linkTyp;
	protected boolean toBePrintedInTheAddress;

	/**
	 * Gets the value of the coHolderInformations property.
	 *
	 * @return possible object is {@link CustomerCoHolderInformations }
	 *
	 */
	public CustomerCoHolderInformations getCoHolderInformations() {
		return coHolderInformations;
	}

	/**
	 * Sets the value of the coHolderInformations property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerCoHolderInformations }
	 *
	 */
	public void setCoHolderInformations(CustomerCoHolderInformations value) {
		this.coHolderInformations = value;
	}

	/**
	 * Gets the value of the linkTyp property.
	 *
	 * @return possible object is {@link LinkTyp }
	 *
	 */
	public LinkTyp getLinkTyp() {
		return linkTyp;
	}

	/**
	 * Sets the value of the linkTyp property.
	 *
	 * @param value
	 *            allowed object is {@link LinkTyp }
	 *
	 */
	public void setLinkTyp(LinkTyp value) {
		this.linkTyp = value;
	}

	/**
	 * Gets the value of the toBePrintedInTheAddress property.
	 *
	 */
	public boolean isToBePrintedInTheAddress() {
		return toBePrintedInTheAddress;
	}

	/**
	 * Sets the value of the toBePrintedInTheAddress property.
	 *
	 */
	public void setToBePrintedInTheAddress(boolean value) {
		this.toBePrintedInTheAddress = value;
	}

}
