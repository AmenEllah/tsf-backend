package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerIndividualGeneralInfoCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerIndividualGeneralInfoCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middlename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="thirdname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="familyStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marriageSettlementCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="holderLegalCapacity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationDateOfLegalCapacity" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="realEstateSituationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerIndividualGeneralInfoCreate", propOrder = {
		"firstname", "middlename", "thirdname", "familyStatusCode",
		"marriageSettlementCode", "holderLegalCapacity",
		"applicationDateOfLegalCapacity", "realEstateSituationCode" })
public class CustomerIndividualGeneralInfoCreate {

	protected String firstname;
	protected String middlename;
	protected String thirdname;
	protected String familyStatusCode;
	protected String marriageSettlementCode;
	protected String holderLegalCapacity;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar applicationDateOfLegalCapacity;
	protected String realEstateSituationCode;

	/**
	 * Gets the value of the firstname property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the value of the firstname property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFirstname(String value) {
		this.firstname = value;
	}

	/**
	 * Gets the value of the middlename property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getMiddlename() {
		return middlename;
	}

	/**
	 * Sets the value of the middlename property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setMiddlename(String value) {
		this.middlename = value;
	}

	/**
	 * Gets the value of the thirdname property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getThirdname() {
		return thirdname;
	}

	/**
	 * Sets the value of the thirdname property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setThirdname(String value) {
		this.thirdname = value;
	}

	/**
	 * Gets the value of the familyStatusCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getFamilyStatusCode() {
		return familyStatusCode;
	}

	/**
	 * Sets the value of the familyStatusCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setFamilyStatusCode(String value) {
		this.familyStatusCode = value;
	}

	/**
	 * Gets the value of the marriageSettlementCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getMarriageSettlementCode() {
		return marriageSettlementCode;
	}

	/**
	 * Sets the value of the marriageSettlementCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setMarriageSettlementCode(String value) {
		this.marriageSettlementCode = value;
	}

	/**
	 * Gets the value of the holderLegalCapacity property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getHolderLegalCapacity() {
		return holderLegalCapacity;
	}

	/**
	 * Sets the value of the holderLegalCapacity property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setHolderLegalCapacity(String value) {
		this.holderLegalCapacity = value;
	}

	/**
	 * Gets the value of the applicationDateOfLegalCapacity property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getApplicationDateOfLegalCapacity() {
		return applicationDateOfLegalCapacity;
	}

	/**
	 * Sets the value of the applicationDateOfLegalCapacity property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setApplicationDateOfLegalCapacity(XMLGregorianCalendar value) {
		this.applicationDateOfLegalCapacity = value;
	}

	/**
	 * Gets the value of the realEstateSituationCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getRealEstateSituationCode() {
		return realEstateSituationCode;
	}

	/**
	 * Sets the value of the realEstateSituationCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setRealEstateSituationCode(String value) {
		this.realEstateSituationCode = value;
	}

}
