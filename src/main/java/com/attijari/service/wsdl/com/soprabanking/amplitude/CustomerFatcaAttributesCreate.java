package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerFatcaAttributesCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerFatcaAttributesCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fatcaStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatcaStatusDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="crsStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crsStatusDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="freeAttributes" type="{http://soprabanking.com/amplitude}customerFreeAttributesCreate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerFatcaAttributesCreate", propOrder = { "fatcaStatus",
		"fatcaStatusDate", "crsStatus", "crsStatusDate", "freeAttributes" })
public class CustomerFatcaAttributesCreate {

	protected String fatcaStatus;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar fatcaStatusDate;
	protected String crsStatus;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar crsStatusDate;
	protected CustomerFreeAttributesCreate freeAttributes;

	/**
	 * Gets the value of the fatcaStatus property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFatcaStatus() {
		return fatcaStatus;
	}

	/**
	 * Sets the value of the fatcaStatus property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFatcaStatus(String value) {
		this.fatcaStatus = value;
	}

	/**
	 * Gets the value of the fatcaStatusDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getFatcaStatusDate() {
		return fatcaStatusDate;
	}

	/**
	 * Sets the value of the fatcaStatusDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setFatcaStatusDate(XMLGregorianCalendar value) {
		this.fatcaStatusDate = value;
	}

	/**
	 * Gets the value of the crsStatus property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCrsStatus() {
		return crsStatus;
	}

	/**
	 * Sets the value of the crsStatus property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCrsStatus(String value) {
		this.crsStatus = value;
	}

	/**
	 * Gets the value of the crsStatusDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getCrsStatusDate() {
		return crsStatusDate;
	}

	/**
	 * Sets the value of the crsStatusDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setCrsStatusDate(XMLGregorianCalendar value) {
		this.crsStatusDate = value;
	}

	/**
	 * Gets the value of the freeAttributes property.
	 *
	 * @return possible object is {@link CustomerFreeAttributesCreate }
	 *
	 */
	public CustomerFreeAttributesCreate getFreeAttributes() {
		return freeAttributes;
	}

	/**
	 * Sets the value of the freeAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerFreeAttributesCreate }
	 *
	 */
	public void setFreeAttributes(CustomerFreeAttributesCreate value) {
		this.freeAttributes = value;
	}

}
