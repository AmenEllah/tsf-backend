package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerProfessionAndIncomesInformations complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerProfessionAndIncomesInformations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hireDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="professionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incomesBracketCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employerDepartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerProfessionAndIncomesInformations", propOrder = {
		"hireDate", "professionCode", "employerCode", "incomesBracketCode",
		"employerDepartment" })
public class CustomerProfessionAndIncomesInformations {

	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar hireDate;
	protected String professionCode;
	protected String employerCode;
	protected String incomesBracketCode;
	protected String employerDepartment;

	/**
	 * Gets the value of the hireDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getHireDate() {
		return hireDate;
	}

	/**
	 * Sets the value of the hireDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setHireDate(XMLGregorianCalendar value) {
		this.hireDate = value;
	}

	/**
	 * Gets the value of the professionCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getProfessionCode() {
		return professionCode;
	}

	/**
	 * Sets the value of the professionCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setProfessionCode(String value) {
		this.professionCode = value;
	}

	/**
	 * Gets the value of the employerCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEmployerCode() {
		return employerCode;
	}

	/**
	 * Sets the value of the employerCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setEmployerCode(String value) {
		this.employerCode = value;
	}

	/**
	 * Gets the value of the incomesBracketCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getIncomesBracketCode() {
		return incomesBracketCode;
	}

	/**
	 * Sets the value of the incomesBracketCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setIncomesBracketCode(String value) {
		this.incomesBracketCode = value;
	}

	/**
	 * Gets the value of the employerDepartment property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEmployerDepartment() {
		return employerDepartment;
	}

	/**
	 * Sets the value of the employerDepartment property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setEmployerDepartment(String value) {
		this.employerDepartment = value;
	}

}
