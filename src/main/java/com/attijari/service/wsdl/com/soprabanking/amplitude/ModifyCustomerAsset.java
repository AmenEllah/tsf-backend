package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for modifyCustomerAsset complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerAsset">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assetCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estimation" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="remainingCapital" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="competition" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="maturityDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerAsset", propOrder = { "assetCode", "estimation",
		"remainingCapital", "currency", "competition", "maturityDate" })
public class ModifyCustomerAsset {

	protected String assetCode;
	protected BigDecimal estimation;
	protected BigDecimal remainingCapital;
	protected String currency;
	protected Boolean competition;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar maturityDate;

	/**
	 * Gets the value of the assetCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAssetCode() {
		return assetCode;
	}

	/**
	 * Sets the value of the assetCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAssetCode(String value) {
		this.assetCode = value;
	}

	/**
	 * Gets the value of the estimation property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getEstimation() {
		return estimation;
	}

	/**
	 * Sets the value of the estimation property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setEstimation(BigDecimal value) {
		this.estimation = value;
	}

	/**
	 * Gets the value of the remainingCapital property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getRemainingCapital() {
		return remainingCapital;
	}

	/**
	 * Sets the value of the remainingCapital property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setRemainingCapital(BigDecimal value) {
		this.remainingCapital = value;
	}

	/**
	 * Gets the value of the currency property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the value of the currency property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCurrency(String value) {
		this.currency = value;
	}

	/**
	 * Gets the value of the competition property.
	 *
	 * @return possible object is {@link Boolean }
	 *
	 */
	public Boolean isCompetition() {
		return competition;
	}

	/**
	 * Sets the value of the competition property.
	 *
	 * @param value
	 *            allowed object is {@link Boolean }
	 *
	 */
	public void setCompetition(Boolean value) {
		this.competition = value;
	}

	/**
	 * Gets the value of the maturityDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getMaturityDate() {
		return maturityDate;
	}

	/**
	 * Sets the value of the maturityDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setMaturityDate(XMLGregorianCalendar value) {
		this.maturityDate = value;
	}

}
