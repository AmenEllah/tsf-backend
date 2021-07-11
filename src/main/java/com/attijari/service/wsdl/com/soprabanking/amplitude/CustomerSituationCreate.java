package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerSituationCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerSituationCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nationalityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryOfResidence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legalSituation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationDateOfLegalSituation" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerSituationCreate", propOrder = { "nationalityCode",
		"countryOfResidence", "legalSituation",
		"applicationDateOfLegalSituation" })
public class CustomerSituationCreate {

	protected String nationalityCode;
	protected String countryOfResidence;
	protected String legalSituation;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar applicationDateOfLegalSituation;

	/**
	 * Gets the value of the nationalityCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getNationalityCode() {
		return nationalityCode;
	}

	/**
	 * Sets the value of the nationalityCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setNationalityCode(String value) {
		this.nationalityCode = value;
	}

	/**
	 * Gets the value of the countryOfResidence property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	/**
	 * Sets the value of the countryOfResidence property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCountryOfResidence(String value) {
		this.countryOfResidence = value;
	}

	/**
	 * Gets the value of the legalSituation property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLegalSituation() {
		return legalSituation;
	}

	/**
	 * Sets the value of the legalSituation property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLegalSituation(String value) {
		this.legalSituation = value;
	}

	/**
	 * Gets the value of the applicationDateOfLegalSituation property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getApplicationDateOfLegalSituation() {
		return applicationDateOfLegalSituation;
	}

	/**
	 * Sets the value of the applicationDateOfLegalSituation property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setApplicationDateOfLegalSituation(XMLGregorianCalendar value) {
		this.applicationDateOfLegalSituation = value;
	}

}
