package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerDocumentInformations complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerDocumentInformations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="supportingDocumentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="validityDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="deliveryPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organisationWhichDeliver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateOnWhichSupportingDocumentWasProvided" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerDocumentInformations", propOrder = { "documentType",
		"supportingDocumentCode", "documentReference", "deliveryDate",
		"validityDate", "deliveryPlace", "organisationWhichDeliver",
		"dateOnWhichSupportingDocumentWasProvided" })
public class CustomerDocumentInformations {

	@XmlElement(required = true)
	protected String documentType;
	@XmlElement(required = true)
	protected String supportingDocumentCode;
	protected String documentReference;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar deliveryDate;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar validityDate;
	protected String deliveryPlace;
	protected String organisationWhichDeliver;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar dateOnWhichSupportingDocumentWasProvided;

	/**
	 * Gets the value of the documentType property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * Sets the value of the documentType property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDocumentType(String value) {
		this.documentType = value;
	}

	/**
	 * Gets the value of the supportingDocumentCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSupportingDocumentCode() {
		return supportingDocumentCode;
	}

	/**
	 * Sets the value of the supportingDocumentCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSupportingDocumentCode(String value) {
		this.supportingDocumentCode = value;
	}

	/**
	 * Gets the value of the documentReference property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDocumentReference() {
		return documentReference;
	}

	/**
	 * Sets the value of the documentReference property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDocumentReference(String value) {
		this.documentReference = value;
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
	 * Gets the value of the validityDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getValidityDate() {
		return validityDate;
	}

	/**
	 * Sets the value of the validityDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setValidityDate(XMLGregorianCalendar value) {
		this.validityDate = value;
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
	 * Gets the value of the dateOnWhichSupportingDocumentWasProvided property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getDateOnWhichSupportingDocumentWasProvided() {
		return dateOnWhichSupportingDocumentWasProvided;
	}

	/**
	 * Sets the value of the dateOnWhichSupportingDocumentWasProvided property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setDateOnWhichSupportingDocumentWasProvided(
			XMLGregorianCalendar value) {
		this.dateOnWhichSupportingDocumentWasProvided = value;
	}

}
