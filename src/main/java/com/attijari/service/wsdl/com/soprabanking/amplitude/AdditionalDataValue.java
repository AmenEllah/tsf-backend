package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for additionalDataValue complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="additionalDataValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alphanumeric" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amountOrRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "additionalDataValue", propOrder = { "alphanumeric",
		"amountOrRate", "date" })
public class AdditionalDataValue {

	protected String alphanumeric;
	protected BigDecimal amountOrRate;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar date;

	/**
	 * Gets the value of the alphanumeric property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAlphanumeric() {
		return alphanumeric;
	}

	/**
	 * Sets the value of the alphanumeric property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAlphanumeric(String value) {
		this.alphanumeric = value;
	}

	/**
	 * Gets the value of the amountOrRate property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getAmountOrRate() {
		return amountOrRate;
	}

	/**
	 * Sets the value of the amountOrRate property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setAmountOrRate(BigDecimal value) {
		this.amountOrRate = value;
	}

	/**
	 * Gets the value of the date property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getDate() {
		return date;
	}

	/**
	 * Sets the value of the date property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setDate(XMLGregorianCalendar value) {
		this.date = value;
	}

}
