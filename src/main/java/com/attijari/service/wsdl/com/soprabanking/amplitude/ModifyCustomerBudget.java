package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyCustomerBudget complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerBudget">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="budgetReferenceYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="averageTaxRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerBudget", propOrder = { "customerCode",
		"budgetReferenceYear", "averageTaxRate" })
public class ModifyCustomerBudget {

	protected String customerCode;
	protected Integer budgetReferenceYear;
	protected BigDecimal averageTaxRate;

	/**
	 * Gets the value of the customerCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Sets the value of the customerCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomerCode(String value) {
		this.customerCode = value;
	}

	/**
	 * Gets the value of the budgetReferenceYear property.
	 *
	 * @return possible object is {@link Integer }
	 *
	 */
	public Integer getBudgetReferenceYear() {
		return budgetReferenceYear;
	}

	/**
	 * Sets the value of the budgetReferenceYear property.
	 *
	 * @param value
	 *            allowed object is {@link Integer }
	 *
	 */
	public void setBudgetReferenceYear(Integer value) {
		this.budgetReferenceYear = value;
	}

	/**
	 * Gets the value of the averageTaxRate property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getAverageTaxRate() {
		return averageTaxRate;
	}

	/**
	 * Sets the value of the averageTaxRate property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setAverageTaxRate(BigDecimal value) {
		this.averageTaxRate = value;
	}

}
