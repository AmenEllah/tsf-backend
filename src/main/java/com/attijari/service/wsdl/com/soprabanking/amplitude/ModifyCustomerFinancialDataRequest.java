package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyCustomerFinancialDataRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerFinancialDataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expressionUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="financialData" type="{http://soprabanking.com/amplitude}customerFinancialDataInformations" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerFinancialDataRequest", propOrder = {
		"customerCode", "currency", "expressionUnit", "financialData" })
public class ModifyCustomerFinancialDataRequest {

	protected String customerCode;
	protected String currency;
	@XmlElement(required = true)
	protected String expressionUnit;
	@XmlElement(nillable = true)
	protected List<CustomerFinancialDataInformations> financialData;

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
	 * Gets the value of the expressionUnit property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getExpressionUnit() {
		return expressionUnit;
	}

	/**
	 * Sets the value of the expressionUnit property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setExpressionUnit(String value) {
		this.expressionUnit = value;
	}

	/**
	 * Gets the value of the financialData property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the financialData property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getFinancialData().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CustomerFinancialDataInformations }
	 *
	 *
	 */
	public List<CustomerFinancialDataInformations> getFinancialData() {
		if (financialData == null) {
			financialData = new ArrayList<CustomerFinancialDataInformations>();
		}
		return this.financialData;
	}

}
