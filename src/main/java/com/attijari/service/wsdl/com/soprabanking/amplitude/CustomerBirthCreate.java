package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerBirthCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerBirthCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="holderSex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maidenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="birthCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthCounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerBirthCreate", propOrder = { "holderSex", "maidenName",
		"birthDate", "birthCity", "birthCounty", "birthRegion", "birthCountry" })
public class CustomerBirthCreate {

	protected String holderSex;
	protected String maidenName;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar birthDate;
	protected String birthCity;
	protected String birthCounty;
	protected String birthRegion;
	protected String birthCountry;

	/**
	 * Gets the value of the holderSex property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getHolderSex() {
		return holderSex;
	}

	/**
	 * Sets the value of the holderSex property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setHolderSex(String value) {
		this.holderSex = value;
	}

	/**
	 * Gets the value of the maidenName property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getMaidenName() {
		return maidenName;
	}

	/**
	 * Sets the value of the maidenName property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setMaidenName(String value) {
		this.maidenName = value;
	}

	/**
	 * Gets the value of the birthDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the value of the birthDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setBirthDate(XMLGregorianCalendar value) {
		this.birthDate = value;
	}

	/**
	 * Gets the value of the birthCity property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getBirthCity() {
		return birthCity;
	}

	/**
	 * Sets the value of the birthCity property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setBirthCity(String value) {
		this.birthCity = value;
	}

	/**
	 * Gets the value of the birthCounty property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getBirthCounty() {
		return birthCounty;
	}

	/**
	 * Sets the value of the birthCounty property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setBirthCounty(String value) {
		this.birthCounty = value;
	}

	/**
	 * Gets the value of the birthRegion property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getBirthRegion() {
		return birthRegion;
	}

	/**
	 * Sets the value of the birthRegion property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setBirthRegion(String value) {
		this.birthRegion = value;
	}

	/**
	 * Gets the value of the birthCountry property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getBirthCountry() {
		return birthCountry;
	}

	/**
	 * Sets the value of the birthCountry property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setBirthCountry(String value) {
		this.birthCountry = value;
	}

}
