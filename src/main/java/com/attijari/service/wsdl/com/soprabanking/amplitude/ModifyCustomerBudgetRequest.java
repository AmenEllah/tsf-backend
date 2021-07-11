package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyCustomerBudgetRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerBudgetRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerBudget" type="{http://soprabanking.com/amplitude}modifyCustomerBudget" minOccurs="0"/>
 *         &lt;element name="customerIncomes" type="{http://soprabanking.com/amplitude}modifyCustomerIncomes" minOccurs="0"/>
 *         &lt;element name="customerCharges" type="{http://soprabanking.com/amplitude}modifyCustomerCharges" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerBudgetRequest", propOrder = { "customerBudget",
		"customerIncomes", "customerCharges" })
public class ModifyCustomerBudgetRequest {

	protected ModifyCustomerBudget customerBudget;
	protected ModifyCustomerIncomes customerIncomes;
	protected ModifyCustomerCharges customerCharges;

	/**
	 * Gets the value of the customerBudget property.
	 *
	 * @return possible object is {@link ModifyCustomerBudget }
	 *
	 */
	public ModifyCustomerBudget getCustomerBudget() {
		return customerBudget;
	}

	/**
	 * Sets the value of the customerBudget property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerBudget }
	 *
	 */
	public void setCustomerBudget(ModifyCustomerBudget value) {
		this.customerBudget = value;
	}

	/**
	 * Gets the value of the customerIncomes property.
	 *
	 * @return possible object is {@link ModifyCustomerIncomes }
	 *
	 */
	public ModifyCustomerIncomes getCustomerIncomes() {
		return customerIncomes;
	}

	/**
	 * Sets the value of the customerIncomes property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerIncomes }
	 *
	 */
	public void setCustomerIncomes(ModifyCustomerIncomes value) {
		this.customerIncomes = value;
	}

	/**
	 * Gets the value of the customerCharges property.
	 *
	 * @return possible object is {@link ModifyCustomerCharges }
	 *
	 */
	public ModifyCustomerCharges getCustomerCharges() {
		return customerCharges;
	}

	/**
	 * Sets the value of the customerCharges property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerCharges }
	 *
	 */
	public void setCustomerCharges(ModifyCustomerCharges value) {
		this.customerCharges = value;
	}

}
