package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerGeneralAttributesCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerGeneralAttributesCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="branchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerOfficer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qualityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxableCustomer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="internalCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statisticNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponsorCustomerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeAttributes" type="{http://soprabanking.com/amplitude}customerFreeAttributesCreate" minOccurs="0"/>
 *         &lt;element name="specificFreeAttributes" type="{http://soprabanking.com/amplitude}customerFreeAttributesCreate" minOccurs="0"/>
 *         &lt;element name="centralBankRiskEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerGeneralAttributesCreate", propOrder = { "branchCode",
		"customerOfficer", "qualityCode", "taxableCustomer",
		"internalCategoryCode", "segment", "statisticNumber",
		"sponsorCustomerCode", "freeAttributes", "specificFreeAttributes",
		"centralBankRiskEffectiveDate" })
public class CustomerGeneralAttributesCreate {

	protected String branchCode;
	protected String customerOfficer;
	protected String qualityCode;
	protected Boolean taxableCustomer;
	protected String internalCategoryCode;
	protected String segment;
	protected String statisticNumber;
	protected String sponsorCustomerCode;
	protected CustomerFreeAttributesCreate freeAttributes;
	protected CustomerFreeAttributesCreate specificFreeAttributes;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar centralBankRiskEffectiveDate;

	/**
	 * Gets the value of the branchCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * Sets the value of the branchCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setBranchCode(String value) {
		this.branchCode = value;
	}

	/**
	 * Gets the value of the customerOfficer property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomerOfficer() {
		return customerOfficer;
	}

	/**
	 * Sets the value of the customerOfficer property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomerOfficer(String value) {
		this.customerOfficer = value;
	}

	/**
	 * Gets the value of the qualityCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getQualityCode() {
		return qualityCode;
	}

	/**
	 * Sets the value of the qualityCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setQualityCode(String value) {
		this.qualityCode = value;
	}

	/**
	 * Gets the value of the taxableCustomer property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public Boolean isTaxableCustomer() {
		return taxableCustomer;
	}

	/**
	 * Sets the value of the taxableCustomer property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setTaxableCustomer(Boolean value) {
		this.taxableCustomer = value;
	}

	/**
	 * Gets the value of the internalCategoryCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getInternalCategoryCode() {
		return internalCategoryCode;
	}

	/**
	 * Sets the value of the internalCategoryCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setInternalCategoryCode(String value) {
		this.internalCategoryCode = value;
	}

	/**
	 * Gets the value of the segment property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSegment() {
		return segment;
	}

	/**
	 * Sets the value of the segment property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSegment(String value) {
		this.segment = value;
	}

	/**
	 * Gets the value of the statisticNumber property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getStatisticNumber() {
		return statisticNumber;
	}

	/**
	 * Sets the value of the statisticNumber property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setStatisticNumber(String value) {
		this.statisticNumber = value;
	}

	/**
	 * Gets the value of the sponsorCustomerCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSponsorCustomerCode() {
		return sponsorCustomerCode;
	}

	/**
	 * Sets the value of the sponsorCustomerCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSponsorCustomerCode(String value) {
		this.sponsorCustomerCode = value;
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

	/**
	 * Gets the value of the specificFreeAttributes property.
	 *
	 * @return possible object is {@link CustomerFreeAttributesCreate }
	 *
	 */
	public CustomerFreeAttributesCreate getSpecificFreeAttributes() {
		return specificFreeAttributes;
	}

	/**
	 * Sets the value of the specificFreeAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerFreeAttributesCreate }
	 *
	 */
	public void setSpecificFreeAttributes(CustomerFreeAttributesCreate value) {
		this.specificFreeAttributes = value;
	}

	/**
	 * Gets the value of the centralBankRiskEffectiveDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getCentralBankRiskEffectiveDate() {
		return centralBankRiskEffectiveDate;
	}

	/**
	 * Sets the value of the centralBankRiskEffectiveDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setCentralBankRiskEffectiveDate(XMLGregorianCalendar value) {
		this.centralBankRiskEffectiveDate = value;
	}

}
