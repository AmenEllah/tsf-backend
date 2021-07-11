package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerCorporateGeneralInfoCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerCorporateGeneralInfoCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeNameToDeclare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondTradeNameToDeclare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyCreationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="legalFormCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statutoryAuditor1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statutoryAuditor2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerCorporateGeneralInfoCreate", propOrder = {
		"tradeNameToDeclare", "secondTradeNameToDeclare",
		"companyCreationDate", "legalFormCode", "statutoryAuditor1",
		"statutoryAuditor2" })
public class CustomerCorporateGeneralInfoCreate {

	protected String tradeNameToDeclare;
	protected String secondTradeNameToDeclare;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar companyCreationDate;
	protected String legalFormCode;
	protected String statutoryAuditor1;
	protected String statutoryAuditor2;

	/**
	 * Gets the value of the tradeNameToDeclare property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTradeNameToDeclare() {
		return tradeNameToDeclare;
	}

	/**
	 * Sets the value of the tradeNameToDeclare property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTradeNameToDeclare(String value) {
		this.tradeNameToDeclare = value;
	}

	/**
	 * Gets the value of the secondTradeNameToDeclare property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSecondTradeNameToDeclare() {
		return secondTradeNameToDeclare;
	}

	/**
	 * Sets the value of the secondTradeNameToDeclare property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSecondTradeNameToDeclare(String value) {
		this.secondTradeNameToDeclare = value;
	}

	/**
	 * Gets the value of the companyCreationDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getCompanyCreationDate() {
		return companyCreationDate;
	}

	/**
	 * Sets the value of the companyCreationDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setCompanyCreationDate(XMLGregorianCalendar value) {
		this.companyCreationDate = value;
	}

	/**
	 * Gets the value of the legalFormCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getLegalFormCode() {
		return legalFormCode;
	}

	/**
	 * Sets the value of the legalFormCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setLegalFormCode(String value) {
		this.legalFormCode = value;
	}

	/**
	 * Gets the value of the statutoryAuditor1 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getStatutoryAuditor1() {
		return statutoryAuditor1;
	}

	/**
	 * Sets the value of the statutoryAuditor1 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setStatutoryAuditor1(String value) {
		this.statutoryAuditor1 = value;
	}

	/**
	 * Gets the value of the statutoryAuditor2 property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getStatutoryAuditor2() {
		return statutoryAuditor2;
	}

	/**
	 * Sets the value of the statutoryAuditor2 property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setStatutoryAuditor2(String value) {
		this.statutoryAuditor2 = value;
	}

}
