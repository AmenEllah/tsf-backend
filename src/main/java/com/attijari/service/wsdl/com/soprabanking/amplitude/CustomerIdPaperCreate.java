package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerIdPaperCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerIdPaperCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPaperNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPaperDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="idPaperDeliveryPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organisationWhichDeliver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPaperValidityDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="nationalIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerIdPaperCreate", propOrder = { "type", "idPaperNumber",
		"idPaperDeliveryDate", "idPaperDeliveryPlace",
		"organisationWhichDeliver", "idPaperValidityDate", "nationalIdentifier" })
public class CustomerIdPaperCreate {

	protected String type;
	protected String idPaperNumber;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar idPaperDeliveryDate;
	protected String idPaperDeliveryPlace;
	protected String organisationWhichDeliver;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar idPaperValidityDate;
	protected String nationalIdentifier;

	/**
	 * Gets the value of the type property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setType(String value) {
		this.type = value;
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
	 * Gets the value of the idPaperDeliveryDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getIdPaperDeliveryDate() {
		return idPaperDeliveryDate;
	}

	/**
	 * Sets the value of the idPaperDeliveryDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setIdPaperDeliveryDate(XMLGregorianCalendar value) {
		this.idPaperDeliveryDate = value;
	}

	/**
	 * Gets the value of the idPaperDeliveryPlace property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getIdPaperDeliveryPlace() {
		return idPaperDeliveryPlace;
	}

	/**
	 * Sets the value of the idPaperDeliveryPlace property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setIdPaperDeliveryPlace(String value) {
		this.idPaperDeliveryPlace = value;
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
	 * Gets the value of the idPaperValidityDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getIdPaperValidityDate() {
		return idPaperValidityDate;
	}

	/**
	 * Sets the value of the idPaperValidityDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setIdPaperValidityDate(XMLGregorianCalendar value) {
		this.idPaperValidityDate = value;
	}

	/**
	 * Gets the value of the nationalIdentifier property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNationalIdentifier() {
		return nationalIdentifier;
	}

	/**
	 * Sets the value of the nationalIdentifier property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setNationalIdentifier(String value) {
		this.nationalIdentifier = value;
	}

}
