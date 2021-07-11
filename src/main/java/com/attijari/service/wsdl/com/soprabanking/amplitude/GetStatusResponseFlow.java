package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getStatusResponseFlow complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="getStatusResponseFlow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getStatusResponse" type="{http://soprabanking.com/amplitude}getStatusResponse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStatusResponseFlow", propOrder = { "getStatusResponse" })
public class GetStatusResponseFlow {

	@XmlElement(required = true)
	protected GetStatusResponse getStatusResponse;

	/**
	 * Gets the value of the getStatusResponse property.
	 *
	 * @return possible object is {@link GetStatusResponse }
	 *
	 */
	public GetStatusResponse getGetStatusResponse() {
		return getStatusResponse;
	}

	/**
	 * Sets the value of the getStatusResponse property.
	 *
	 * @param value
	 *            allowed object is {@link GetStatusResponse }
	 *
	 */
	public void setGetStatusResponse(GetStatusResponse value) {
		this.getStatusResponse = value;
	}

}
