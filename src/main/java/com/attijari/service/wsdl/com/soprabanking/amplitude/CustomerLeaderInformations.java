package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerLeaderInformations complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerLeaderInformations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="leaderCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leaderType" type="{http://soprabanking.com/amplitude}customerOrThirdPartyType"/>
 *         &lt;element name="leaderPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeOfCorporateExecutiveDuty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerLeaderInformations", propOrder = { "leaderCode",
		"leaderType", "leaderPosition", "typeOfCorporateExecutiveDuty" })
public class CustomerLeaderInformations {

	@XmlElement(required = true)
	protected String leaderCode;
	@XmlElement(required = true)
	protected CustomerOrThirdPartyType leaderType;
	protected String leaderPosition;
	protected String typeOfCorporateExecutiveDuty;

	/**
	 * Gets the value of the leaderCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLeaderCode() {
		return leaderCode;
	}

	/**
	 * Sets the value of the leaderCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLeaderCode(String value) {
		this.leaderCode = value;
	}

	/**
	 * Gets the value of the leaderType property.
	 *
	 * @return possible object is {@link CustomerOrThirdPartyType }
	 *
	 */
	public CustomerOrThirdPartyType getLeaderType() {
		return leaderType;
	}

	/**
	 * Sets the value of the leaderType property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerOrThirdPartyType }
	 *
	 */
	public void setLeaderType(CustomerOrThirdPartyType value) {
		this.leaderType = value;
	}

	/**
	 * Gets the value of the leaderPosition property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLeaderPosition() {
		return leaderPosition;
	}

	/**
	 * Sets the value of the leaderPosition property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLeaderPosition(String value) {
		this.leaderPosition = value;
	}

	/**
	 * Gets the value of the typeOfCorporateExecutiveDuty property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTypeOfCorporateExecutiveDuty() {
		return typeOfCorporateExecutiveDuty;
	}

	/**
	 * Sets the value of the typeOfCorporateExecutiveDuty property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTypeOfCorporateExecutiveDuty(String value) {
		this.typeOfCorporateExecutiveDuty = value;
	}

}
