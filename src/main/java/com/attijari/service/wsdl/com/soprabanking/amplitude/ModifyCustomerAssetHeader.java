package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyCustomerAssetHeader complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerAssetHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referenceYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="reliabilityLevel" type="{http://soprabanking.com/amplitude}reliabilityLevel" minOccurs="0"/>
 *         &lt;element name="exhaustiveInformation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerAssetHeader", propOrder = { "customerCode",
		"referenceYear", "reliabilityLevel", "exhaustiveInformation" })
public class ModifyCustomerAssetHeader {

	protected String customerCode;
	protected Integer referenceYear;
	protected ReliabilityLevel reliabilityLevel;
	protected Boolean exhaustiveInformation;

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
	 * Gets the value of the referenceYear property.
	 *
	 * @return possible object is {@link Integer }
	 *
	 */
	public Integer getReferenceYear() {
		return referenceYear;
	}

	/**
	 * Sets the value of the referenceYear property.
	 *
	 * @param value
	 *            allowed object is {@link Integer }
	 *
	 */
	public void setReferenceYear(Integer value) {
		this.referenceYear = value;
	}

	/**
	 * Gets the value of the reliabilityLevel property.
	 *
	 * @return possible object is {@link ReliabilityLevel }
	 *
	 */
	public ReliabilityLevel getReliabilityLevel() {
		return reliabilityLevel;
	}

	/**
	 * Sets the value of the reliabilityLevel property.
	 *
	 * @param value
	 *            allowed object is {@link ReliabilityLevel }
	 *
	 */
	public void setReliabilityLevel(ReliabilityLevel value) {
		this.reliabilityLevel = value;
	}

	/**
	 * Gets the value of the exhaustiveInformation property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public Boolean isExhaustiveInformation() {
		return exhaustiveInformation;
	}

	/**
	 * Sets the value of the exhaustiveInformation property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setExhaustiveInformation(Boolean value) {
		this.exhaustiveInformation = value;
	}

}
