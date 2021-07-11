package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for responseHeader complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="responseHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responseId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="serviceVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language" type="{http://soprabanking.com/amplitude}language" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseHeader", propOrder = { "requestId", "responseId",
		"timestamp", "serviceVersion", "language" })
public class ResponseHeader {

	@XmlElement(required = true)
	protected String requestId;
	@XmlElement(required = true)
	protected String responseId;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar timestamp;
	@XmlElement(required = true)
	protected String serviceVersion;
	protected Language language;

	/**
	 * Gets the value of the requestId property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * Sets the value of the requestId property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setRequestId(String value) {
		this.requestId = value;
	}

	/**
	 * Gets the value of the responseId property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getResponseId() {
		return responseId;
	}

	/**
	 * Sets the value of the responseId property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setResponseId(String value) {
		this.responseId = value;
	}

	/**
	 * Gets the value of the timestamp property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the value of the timestamp property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setTimestamp(XMLGregorianCalendar value) {
		this.timestamp = value;
	}

	/**
	 * Gets the value of the serviceVersion property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getServiceVersion() {
		return serviceVersion;
	}

	/**
	 * Sets the value of the serviceVersion property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setServiceVersion(String value) {
		this.serviceVersion = value;
	}

	/**
	 * Gets the value of the language property.
	 *
	 * @return possible object is {@link Language }
	 *
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Sets the value of the language property.
	 *
	 * @param value
	 *            allowed object is {@link Language }
	 *
	 */
	public void setLanguage(Language value) {
		this.language = value;
	}

}
