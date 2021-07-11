package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerFinancialDataValues complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerFinancialDataValues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="yearValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="yearMinus1Value" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="yearMinus2Value" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="yearMinus3Value" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerFinancialDataValues", propOrder = { "yearValue",
		"yearMinus1Value", "yearMinus2Value", "yearMinus3Value" })
public class CustomerFinancialDataValues {

	protected BigDecimal yearValue;
	protected BigDecimal yearMinus1Value;
	protected BigDecimal yearMinus2Value;
	protected BigDecimal yearMinus3Value;

	/**
	 * Gets the value of the yearValue property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getYearValue() {
		return yearValue;
	}

	/**
	 * Sets the value of the yearValue property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setYearValue(BigDecimal value) {
		this.yearValue = value;
	}

	/**
	 * Gets the value of the yearMinus1Value property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getYearMinus1Value() {
		return yearMinus1Value;
	}

	/**
	 * Sets the value of the yearMinus1Value property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setYearMinus1Value(BigDecimal value) {
		this.yearMinus1Value = value;
	}

	/**
	 * Gets the value of the yearMinus2Value property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getYearMinus2Value() {
		return yearMinus2Value;
	}

	/**
	 * Sets the value of the yearMinus2Value property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setYearMinus2Value(BigDecimal value) {
		this.yearMinus2Value = value;
	}

	/**
	 * Gets the value of the yearMinus3Value property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getYearMinus3Value() {
		return yearMinus3Value;
	}

	/**
	 * Sets the value of the yearMinus3Value property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setYearMinus3Value(BigDecimal value) {
		this.yearMinus3Value = value;
	}

}
