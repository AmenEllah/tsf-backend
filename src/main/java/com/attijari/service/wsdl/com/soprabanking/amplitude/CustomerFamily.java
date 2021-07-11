package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerFamily complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerFamily">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spouseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spouseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfChildren" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="customerFamilyRelationshipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerFamily", propOrder = { "spouseType", "spouseCode",
		"numberOfChildren", "customerFamilyRelationshipCode" })
public class CustomerFamily {

	protected String spouseType;
	protected String spouseCode;
	protected Integer numberOfChildren;
	protected String customerFamilyRelationshipCode;

	/**
	 * Gets the value of the spouseType property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSpouseType() {
		return spouseType;
	}

	/**
	 * Sets the value of the spouseType property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSpouseType(String value) {
		this.spouseType = value;
	}

	/**
	 * Gets the value of the spouseCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSpouseCode() {
		return spouseCode;
	}

	/**
	 * Sets the value of the spouseCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSpouseCode(String value) {
		this.spouseCode = value;
	}

	/**
	 * Gets the value of the numberOfChildren property.
	 *
	 * @return possible object is {@link Integer }
	 *
	 */
	public Integer getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * Sets the value of the numberOfChildren property.
	 *
	 * @param value
	 *            allowed object is {@link Integer }
	 *
	 */
	public void setNumberOfChildren(Integer value) {
		this.numberOfChildren = value;
	}

	/**
	 * Gets the value of the customerFamilyRelationshipCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomerFamilyRelationshipCode() {
		return customerFamilyRelationshipCode;
	}

	/**
	 * Sets the value of the customerFamilyRelationshipCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomerFamilyRelationshipCode(String value) {
		this.customerFamilyRelationshipCode = value;
	}

}
