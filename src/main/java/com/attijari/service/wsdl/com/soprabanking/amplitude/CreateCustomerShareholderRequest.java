package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for createCustomerShareholderRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerShareholderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://soprabanking.com/amplitude}shareholderCreateIdentifier"/>
 *         &lt;element name="shares" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="marketType" type="{http://soprabanking.com/amplitude}marketType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerShareholderRequest", propOrder = { "identifier",
		"shares", "marketType" })
public class CreateCustomerShareholderRequest {

	@XmlElement(required = true)
	protected ShareholderCreateIdentifier identifier;
	protected BigDecimal shares;
	protected MarketType marketType;

	/**
	 * Gets the value of the identifier property.
	 *
	 * @return possible object is {@link ShareholderCreateIdentifier }
	 *
	 */
	public ShareholderCreateIdentifier getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the value of the identifier property.
	 *
	 * @param value
	 *            allowed object is {@link ShareholderCreateIdentifier }
	 *
	 */
	public void setIdentifier(ShareholderCreateIdentifier value) {
		this.identifier = value;
	}

	/**
	 * Gets the value of the shares property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getShares() {
		return shares;
	}

	/**
	 * Sets the value of the shares property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setShares(BigDecimal value) {
		this.shares = value;
	}

	/**
	 * Gets the value of the marketType property.
	 *
	 * @return possible object is {@link MarketType }
	 *
	 */
	public MarketType getMarketType() {
		return marketType;
	}

	/**
	 * Sets the value of the marketType property.
	 *
	 * @param value
	 *            allowed object is {@link MarketType }
	 *
	 */
	public void setMarketType(MarketType value) {
		this.marketType = value;
	}

}
