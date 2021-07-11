package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for createCustomerIdPaperRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="createCustomerIdPaperRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPaperType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPaperNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="deliveryPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organisationWhichDeliver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validityDateOfIdPaper" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCustomerIdPaperRequest", propOrder = { "customerCode",
		"idPaperType", "idPaperNumber", "deliveryDate", "deliveryPlace",
		"organisationWhichDeliver", "validityDateOfIdPaper" })
public class CreateCustomerIdPaperRequest {

	protected String customerCode;
	protected String idPaperType;
	protected String idPaperNumber;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar deliveryDate;
	protected String deliveryPlace;
	protected String organisationWhichDeliver;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar validityDateOfIdPaper;

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
	 * Gets the value of the idPaperType property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getIdPaperType() {
		return idPaperType;
	}

	/**
	 * Sets the value of the idPaperType property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setIdPaperType(String value) {
		this.idPaperType = value;
	}

	/**
	 * Gets the value of the idPaperNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getIdPaperNumber() {
		return idPaperNumber;
	}

	/**
	 * Sets the value of the idPaperNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setIdPaperNumber(String value) {
		this.idPaperNumber = value;
	}

	/**
	 * Gets the value of the deliveryDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Sets the value of the deliveryDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setDeliveryDate(XMLGregorianCalendar value) {
		this.deliveryDate = value;
	}

	/**
	 * Gets the value of the deliveryPlace property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	/**
	 * Sets the value of the deliveryPlace property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDeliveryPlace(String value) {
		this.deliveryPlace = value;
	}

	/**
	 * Gets the value of the organisationWhichDeliver property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getOrganisationWhichDeliver() {
		return organisationWhichDeliver;
	}

	/**
	 * Sets the value of the organisationWhichDeliver property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setOrganisationWhichDeliver(String value) {
		this.organisationWhichDeliver = value;
	}

	/**
	 * Gets the value of the validityDateOfIdPaper property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getValidityDateOfIdPaper() {
		return validityDateOfIdPaper;
	}

	/**
	 * Sets the value of the validityDateOfIdPaper property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setValidityDateOfIdPaper(XMLGregorianCalendar value) {
		this.validityDateOfIdPaper = value;
	}

}
