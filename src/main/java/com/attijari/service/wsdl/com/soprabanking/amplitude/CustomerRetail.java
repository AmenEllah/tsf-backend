package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerRetail complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerRetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pmlDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="pmlDefaultEntry" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="nonPmlDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nonPmlDefaultEntry" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerRetail", propOrder = { "pmlDefault",
		"pmlDefaultEntry", "nonPmlDefault", "nonPmlDefaultEntry" })
public class CustomerRetail {

	protected Boolean pmlDefault;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar pmlDefaultEntry;
	protected Boolean nonPmlDefault;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar nonPmlDefaultEntry;

	/**
	 * Gets the value of the pmlDefault property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public Boolean isPmlDefault() {
		return pmlDefault;
	}

	/**
	 * Sets the value of the pmlDefault property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setPmlDefault(Boolean value) {
		this.pmlDefault = value;
	}

	/**
	 * Gets the value of the pmlDefaultEntry property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getPmlDefaultEntry() {
		return pmlDefaultEntry;
	}

	/**
	 * Sets the value of the pmlDefaultEntry property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setPmlDefaultEntry(XMLGregorianCalendar value) {
		this.pmlDefaultEntry = value;
	}

	/**
	 * Gets the value of the nonPmlDefault property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public Boolean isNonPmlDefault() {
		return nonPmlDefault;
	}

	/**
	 * Sets the value of the nonPmlDefault property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setNonPmlDefault(Boolean value) {
		this.nonPmlDefault = value;
	}

	/**
	 * Gets the value of the nonPmlDefaultEntry property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getNonPmlDefaultEntry() {
		return nonPmlDefaultEntry;
	}

	/**
	 * Sets the value of the nonPmlDefaultEntry property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setNonPmlDefaultEntry(XMLGregorianCalendar value) {
		this.nonPmlDefaultEntry = value;
	}

}
