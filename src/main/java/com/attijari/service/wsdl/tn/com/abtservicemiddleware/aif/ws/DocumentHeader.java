package com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DocumentHeader complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DocumentHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReceiveTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SendTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EncryptedConnexion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransmissionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentHeader", propOrder = { "receiveTime", "sendTime",
		"resultCode", "resultMessage", "encryptedConnexion", "transmissionID" })
public class DocumentHeader {

	@XmlElement(name = "ReceiveTime")
	protected String receiveTime;
	@XmlElement(name = "SendTime")
	protected String sendTime;
	@XmlElement(name = "ResultCode")
	protected String resultCode;
	@XmlElement(name = "ResultMessage")
	protected String resultMessage;
	@XmlElement(name = "EncryptedConnexion")
	protected String encryptedConnexion;
	@XmlElement(name = "TransmissionID")
	protected String transmissionID;

	/**
	 * Gets the value of the receiveTime property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getReceiveTime() {
		return receiveTime;
	}

	/**
	 * Sets the value of the receiveTime property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setReceiveTime(String value) {
		this.receiveTime = value;
	}

	/**
	 * Gets the value of the sendTime property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * Sets the value of the sendTime property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSendTime(String value) {
		this.sendTime = value;
	}

	/**
	 * Gets the value of the resultCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * Sets the value of the resultCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setResultCode(String value) {
		this.resultCode = value;
	}

	/**
	 * Gets the value of the resultMessage property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getResultMessage() {
		return resultMessage;
	}

	/**
	 * Sets the value of the resultMessage property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setResultMessage(String value) {
		this.resultMessage = value;
	}

	/**
	 * Gets the value of the encryptedConnexion property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEncryptedConnexion() {
		return encryptedConnexion;
	}

	/**
	 * Sets the value of the encryptedConnexion property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setEncryptedConnexion(String value) {
		this.encryptedConnexion = value;
	}

	/**
	 * Gets the value of the transmissionID property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTransmissionID() {
		return transmissionID;
	}

	/**
	 * Sets the value of the transmissionID property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTransmissionID(String value) {
		this.transmissionID = value;
	}

}
