package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyCustomerIncome complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerIncome">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="incomeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incomePeriodicityCode" type="{http://soprabanking.com/amplitude}periodicity" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerIncome", propOrder = { "incomeCode",
		"incomePeriodicityCode", "amount", "currency" })
public class ModifyCustomerIncome {

	protected String incomeCode;
	protected Periodicity incomePeriodicityCode;
	protected BigDecimal amount;
	protected String currency;

	/**
	 * Gets the value of the incomeCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getIncomeCode() {
		return incomeCode;
	}

	/**
	 * Sets the value of the incomeCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setIncomeCode(String value) {
		this.incomeCode = value;
	}

	/**
	 * Gets the value of the incomePeriodicityCode property.
	 *
	 * @return possible object is {@link Periodicity }
	 *
	 */
	public Periodicity getIncomePeriodicityCode() {
		return incomePeriodicityCode;
	}

	/**
	 * Sets the value of the incomePeriodicityCode property.
	 *
	 * @param value
	 *            allowed object is {@link Periodicity }
	 *
	 */
	public void setIncomePeriodicityCode(Periodicity value) {
		this.incomePeriodicityCode = value;
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

}
