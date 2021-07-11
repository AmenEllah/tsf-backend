package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getStatusRequestFlow complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="getStatusRequestFlow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getStatusRequest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStatusRequestFlow", propOrder = { "getStatusRequest" })
public class GetStatusRequestFlow {

	@XmlElement(required = true)
	protected String getStatusRequest;

	/**
	 * Gets the value of the getStatusRequest property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getGetStatusRequest() {
		return getStatusRequest;
	}

	/**
	 * Sets the value of the getStatusRequest property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setGetStatusRequest(String value) {
		this.getStatusRequest = value;
	}

}
