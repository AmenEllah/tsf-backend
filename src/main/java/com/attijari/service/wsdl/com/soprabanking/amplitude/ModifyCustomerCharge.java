package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for modifyCustomerCharge complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerCharge">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodicityCode" type="{http://soprabanking.com/amplitude}periodicity" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="initialAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maturityDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="organisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerCharge", propOrder = { "chargeCode",
		"periodicityCode", "amount", "initialAmount", "currency",
		"maturityDate", "organisation" })
public class ModifyCustomerCharge {

	protected String chargeCode;
	protected Periodicity periodicityCode;
	protected BigDecimal amount;
	protected BigDecimal initialAmount;
	protected String currency;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar maturityDate;
	protected String organisation;

	/**
	 * Gets the value of the chargeCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getChargeCode() {
		return chargeCode;
	}

	/**
	 * Sets the value of the chargeCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setChargeCode(String value) {
		this.chargeCode = value;
	}

	/**
	 * Gets the value of the periodicityCode property.
	 *
	 * @return possible object is {@link Periodicity }
	 *
	 */
	public Periodicity getPeriodicityCode() {
		return periodicityCode;
	}

	/**
	 * Sets the value of the periodicityCode property.
	 *
	 * @param value
	 *            allowed object is {@link Periodicity }
	 *
	 */
	public void setPeriodicityCode(Periodicity value) {
		this.periodicityCode = value;
	}

	/**
	 * Gets the value of the amount property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the value of the amount property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setAmount(BigDecimal value) {
		this.amount = value;
	}

	/**
	 * Gets the value of the initialAmount property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getInitialAmount() {
		return initialAmount;
	}

	/**
	 * Sets the value of the initialAmount property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setInitialAmount(BigDecimal value) {
		this.initialAmount = value;
	}

	/**
	 * Gets the value of the currency property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the value of the currency property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCurrency(String value) {
		this.currency = value;
	}

	/**
	 * Gets the value of the maturityDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getMaturityDate() {
		return maturityDate;
	}

	/**
	 * Sets the value of the maturityDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setMaturityDate(XMLGregorianCalendar value) {
		this.maturityDate = value;
	}

	/**
	 * Gets the value of the organisation property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getOrganisation() {
		return organisation;
	}

	/**
	 * Sets the value of the organisation property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setOrganisation(String value) {
		this.organisation = value;
	}

}
