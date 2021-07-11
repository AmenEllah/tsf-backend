package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for errorInformation complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="errorInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="convertedCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errorInformation", propOrder = { "errorCode", "convertedCode",
		"errorMessage" })
public class ErrorInformation {

	protected String errorCode;
	protected String convertedCode;
	protected String errorMessage;

	/**
	 * Gets the value of the errorCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the value of the errorCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setErrorCode(String value) {
		this.errorCode = value;
	}

	/**
	 * Gets the value of the convertedCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getConvertedCode() {
		return convertedCode;
	}

	/**
	 * Sets the value of the convertedCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setConvertedCode(String value) {
		this.convertedCode = value;
	}

	/**
	 * Gets the value of the errorMessage property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the value of the errorMessage property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setErrorMessage(String value) {
		this.errorMessage = value;
	}

}
