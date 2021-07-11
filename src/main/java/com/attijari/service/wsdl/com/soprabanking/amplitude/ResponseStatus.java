package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for responseStatus complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="responseStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="messages" type="{http://soprabanking.com/amplitude}responseStatusMessages" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseStatus", propOrder = { "statusCode", "messages" })
public class ResponseStatus {

	@XmlElement(required = true)
	protected String statusCode;
	protected ResponseStatusMessages messages;

	/**
	 * Gets the value of the statusCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the value of the statusCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setStatusCode(String value) {
		this.statusCode = value;
	}

	/**
	 * Gets the value of the messages property.
	 *
	 * @return possible object is {@link ResponseStatusMessages }
	 *
	 */
	public ResponseStatusMessages getMessages() {
		return messages;
	}

	/**
	 * Sets the value of the messages property.
	 *
	 * @param value
	 *            allowed object is {@link ResponseStatusMessages }
	 *
	 */
	public void setMessages(ResponseStatusMessages value) {
		this.messages = value;
	}

}
