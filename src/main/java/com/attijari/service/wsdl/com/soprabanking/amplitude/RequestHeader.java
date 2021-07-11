package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for requestHeader complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="requestHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="originalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originalTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="languageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestHeader", propOrder = { "requestId", "serviceName",
		"timestamp", "originalName", "originalId", "originalTimestamp",
		"languageCode", "userCode", "channelCode" })
public class RequestHeader {

	@XmlElement(required = true)
	protected String requestId;
	@XmlElement(required = true)
	protected String serviceName;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar timestamp;
	protected String originalName;
	protected String originalId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar originalTimestamp;
	protected String languageCode;
	@XmlElement(required = true)
	protected String userCode;
	protected String channelCode;

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
	 * Gets the value of the serviceName property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the value of the serviceName property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setServiceName(String value) {
		this.serviceName = value;
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
	 * Gets the value of the originalName property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * Sets the value of the originalName property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setOriginalName(String value) {
		this.originalName = value;
	}

	/**
	 * Gets the value of the originalId property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getOriginalId() {
		return originalId;
	}

	/**
	 * Sets the value of the originalId property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setOriginalId(String value) {
		this.originalId = value;
	}

	/**
	 * Gets the value of the originalTimestamp property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getOriginalTimestamp() {
		return originalTimestamp;
	}

	/**
	 * Sets the value of the originalTimestamp property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setOriginalTimestamp(XMLGregorianCalendar value) {
		this.originalTimestamp = value;
	}

	/**
	 * Gets the value of the languageCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * Sets the value of the languageCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLanguageCode(String value) {
		this.languageCode = value;
	}

	/**
	 * Gets the value of the userCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * Sets the value of the userCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setUserCode(String value) {
		this.userCode = value;
	}

	/**
	 * Gets the value of the channelCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getChannelCode() {
		return channelCode;
	}

	/**
	 * Sets the value of the channelCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setChannelCode(String value) {
		this.channelCode = value;
	}

}
