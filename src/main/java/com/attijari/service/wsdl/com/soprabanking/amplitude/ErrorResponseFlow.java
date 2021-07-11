package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for errorResponseFlow complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="errorResponseFlow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseHeader" type="{http://soprabanking.com/amplitude}responseHeader"/>
 *         &lt;element name="responseStatus" type="{http://soprabanking.com/amplitude}responseStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errorResponseFlow", propOrder = { "responseHeader",
		"responseStatus" })
public class ErrorResponseFlow {

	@XmlElement(required = true)
	protected ResponseHeader responseHeader;
	@XmlElement(required = true)
	protected ResponseStatus responseStatus;

	/**
	 * Gets the value of the responseHeader property.
	 *
	 * @return possible object is {@link ResponseHeader }
	 *
	 */
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	/**
	 * Sets the value of the responseHeader property.
	 *
	 * @param value
	 *            allowed object is {@link ResponseHeader }
	 *
	 */
	public void setResponseHeader(ResponseHeader value) {
		this.responseHeader = value;
	}

	/**
	 * Gets the value of the responseStatus property.
	 *
	 * @return possible object is {@link ResponseStatus }
	 *
	 */
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	/**
	 * Sets the value of the responseStatus property.
	 *
	 * @param value
	 *            allowed object is {@link ResponseStatus }
	 *
	 */
	public void setResponseStatus(ResponseStatus value) {
		this.responseStatus = value;
	}

}
