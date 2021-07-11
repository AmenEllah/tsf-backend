package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerNonRetail complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerNonRetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="default" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="defaultEntry" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerNonRetail", propOrder = { "_default", "defaultEntry" })
public class CustomerNonRetail {

	@XmlElement(name = "default")
	protected boolean _default;
	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar defaultEntry;

	/**
	 * Gets the value of the default property.
	 *
	 */
	public boolean isDefault() {
		return _default;
	}

	/**
	 * Sets the value of the default property.
	 *
	 */
	public void setDefault(boolean value) {
		this._default = value;
	}

	/**
	 * Gets the value of the defaultEntry property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getDefaultEntry() {
		return defaultEntry;
	}

	/**
	 * Sets the value of the defaultEntry property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setDefaultEntry(XMLGregorianCalendar value) {
		this.defaultEntry = value;
	}

}
