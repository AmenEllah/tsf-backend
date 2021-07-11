package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerReportingAttributesCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerReportingAttributesCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="declaredHome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="economicAgentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activityFieldCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationshipWithTheBank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradingAgreement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradingAgreementAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="securityIssuer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="internationalOperationsIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditInfoCentre" type="{http://soprabanking.com/amplitude}customerCreditInfoCentreCreate" minOccurs="0"/>
 *         &lt;element name="freeAttributes" type="{http://soprabanking.com/amplitude}customerFreeAttributesCreate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerReportingAttributesCreate", propOrder = {
		"declaredHome", "economicAgentCode", "activityFieldCode",
		"relationshipWithTheBank", "gradingAgreement",
		"gradingAgreementAmount", "securityIssuer",
		"internationalOperationsIndicator", "creditInfoCentre",
		"freeAttributes" })
public class CustomerReportingAttributesCreate {

	protected String declaredHome;
	protected String economicAgentCode;
	protected String activityFieldCode;
	protected String relationshipWithTheBank;
	protected String gradingAgreement;
	protected BigDecimal gradingAgreementAmount;
	protected String securityIssuer;
	protected String internationalOperationsIndicator;
	protected CustomerCreditInfoCentreCreate creditInfoCentre;
	protected CustomerFreeAttributesCreate freeAttributes;

	/**
	 * Gets the value of the declaredHome property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDeclaredHome() {
		return declaredHome;
	}

	/**
	 * Sets the value of the declaredHome property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDeclaredHome(String value) {
		this.declaredHome = value;
	}

	/**
	 * Gets the value of the economicAgentCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getEconomicAgentCode() {
		return economicAgentCode;
	}

	/**
	 * Sets the value of the economicAgentCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setEconomicAgentCode(String value) {
		this.economicAgentCode = value;
	}

	/**
	 * Gets the value of the activityFieldCode property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getActivityFieldCode() {
		return activityFieldCode;
	}

	/**
	 * Sets the value of the activityFieldCode property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setActivityFieldCode(String value) {
		this.activityFieldCode = value;
	}

	/**
	 * Gets the value of the relationshipWithTheBank property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getRelationshipWithTheBank() {
		return relationshipWithTheBank;
	}

	/**
	 * Sets the value of the relationshipWithTheBank property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setRelationshipWithTheBank(String value) {
		this.relationshipWithTheBank = value;
	}

	/**
	 * Gets the value of the gradingAgreement property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getGradingAgreement() {
		return gradingAgreement;
	}

	/**
	 * Sets the value of the gradingAgreement property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setGradingAgreement(String value) {
		this.gradingAgreement = value;
	}

	/**
	 * Gets the value of the gradingAgreementAmount property.
	 *
	 * @return possible object is {@link BigDecimal }
	 *
	 */
	public BigDecimal getGradingAgreementAmount() {
		return gradingAgreementAmount;
	}

	/**
	 * Sets the value of the gradingAgreementAmount property.
	 *
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 *
	 */
	public void setGradingAgreementAmount(BigDecimal value) {
		this.gradingAgreementAmount = value;
	}

	/**
	 * Gets the value of the securityIssuer property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSecurityIssuer() {
		return securityIssuer;
	}

	/**
	 * Sets the value of the securityIssuer property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSecurityIssuer(String value) {
		this.securityIssuer = value;
	}

	/**
	 * Gets the value of the internationalOperationsIndicator property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getInternationalOperationsIndicator() {
		return internationalOperationsIndicator;
	}

	/**
	 * Sets the value of the internationalOperationsIndicator property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setInternationalOperationsIndicator(String value) {
		this.internationalOperationsIndicator = value;
	}

	/**
	 * Gets the value of the creditInfoCentre property.
	 *
	 * @return possible object is {@link CustomerCreditInfoCentreCreate }
	 *
	 */
	public CustomerCreditInfoCentreCreate getCreditInfoCentre() {
		return creditInfoCentre;
	}

	/**
	 * Sets the value of the creditInfoCentre property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerCreditInfoCentreCreate }
	 *
	 */
	public void setCreditInfoCentre(CustomerCreditInfoCentreCreate value) {
		this.creditInfoCentre = value;
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

}
