package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerCorporateSpecInfoCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerCorporateSpecInfoCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="corporateGeneralInfo" type="{http://soprabanking.com/amplitude}customerCorporateGeneralInfoCreate" minOccurs="0"/>
 *         &lt;element name="corporateId" type="{http://soprabanking.com/amplitude}customerCorporateId" minOccurs="0"/>
 *         &lt;element name="legalInformation" type="{http://soprabanking.com/amplitude}customerLegalInformationCreate" minOccurs="0"/>
 *         &lt;element name="groupAndJob" type="{http://soprabanking.com/amplitude}customerGroupAndJobCreate" minOccurs="0"/>
 *         &lt;element name="leadersList" type="{http://soprabanking.com/amplitude}modifyCustomerLeadersRequest" minOccurs="0"/>
 *         &lt;element name="shareholdersList" type="{http://soprabanking.com/amplitude}customerShareholdersCreate" minOccurs="0"/>
 *         &lt;element name="financialDataList" type="{http://soprabanking.com/amplitude}modifyCustomerFinancialDataRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerCorporateSpecInfoCreate", propOrder = {
		"corporateGeneralInfo", "corporateId", "legalInformation",
		"groupAndJob", "leadersList", "shareholdersList", "financialDataList" })
public class CustomerCorporateSpecInfoCreate {

	protected CustomerCorporateGeneralInfoCreate corporateGeneralInfo;
	protected CustomerCorporateId corporateId;
	protected CustomerLegalInformationCreate legalInformation;
	protected CustomerGroupAndJobCreate groupAndJob;
	protected ModifyCustomerLeadersRequest leadersList;
	protected CustomerShareholdersCreate shareholdersList;
	protected ModifyCustomerFinancialDataRequest financialDataList;

	/**
	 * Gets the value of the corporateGeneralInfo property.
	 *
	 * @return possible object is {@link CustomerCorporateGeneralInfoCreate }
	 *
	 */
	public CustomerCorporateGeneralInfoCreate getCorporateGeneralInfo() {
		return corporateGeneralInfo;
	}

	/**
	 * Sets the value of the corporateGeneralInfo property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerCorporateGeneralInfoCreate }
	 *
	 */
	public void setCorporateGeneralInfo(CustomerCorporateGeneralInfoCreate value) {
		this.corporateGeneralInfo = value;
	}

	/**
	 * Gets the value of the corporateId property.
	 *
	 * @return possible object is {@link CustomerCorporateId }
	 *
	 */
	public CustomerCorporateId getCorporateId() {
		return corporateId;
	}

	/**
	 * Sets the value of the corporateId property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerCorporateId }
	 *
	 */
	public void setCorporateId(CustomerCorporateId value) {
		this.corporateId = value;
	}

	/**
	 * Gets the value of the legalInformation property.
	 *
	 * @return possible object is {@link CustomerLegalInformationCreate }
	 *
	 */
	public CustomerLegalInformationCreate getLegalInformation() {
		return legalInformation;
	}

	/**
	 * Sets the value of the legalInformation property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerLegalInformationCreate }
	 *
	 */
	public void setLegalInformation(CustomerLegalInformationCreate value) {
		this.legalInformation = value;
	}

	/**
	 * Gets the value of the groupAndJob property.
	 *
	 * @return possible object is {@link CustomerGroupAndJobCreate }
	 *
	 */
	public CustomerGroupAndJobCreate getGroupAndJob() {
		return groupAndJob;
	}

	/**
	 * Sets the value of the groupAndJob property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerGroupAndJobCreate }
	 *
	 */
	public void setGroupAndJob(CustomerGroupAndJobCreate value) {
		this.groupAndJob = value;
	}

	/**
	 * Gets the value of the leadersList property.
	 *
	 * @return possible object is {@link ModifyCustomerLeadersRequest }
	 *
	 */
	public ModifyCustomerLeadersRequest getLeadersList() {
		return leadersList;
	}

	/**
	 * Sets the value of the leadersList property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerLeadersRequest }
	 *
	 */
	public void setLeadersList(ModifyCustomerLeadersRequest value) {
		this.leadersList = value;
	}

	/**
	 * Gets the value of the shareholdersList property.
	 *
	 * @return possible object is {@link CustomerShareholdersCreate }
	 *
	 */
	public CustomerShareholdersCreate getShareholdersList() {
		return shareholdersList;
	}

	/**
	 * Sets the value of the shareholdersList property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerShareholdersCreate }
	 *
	 */
	public void setShareholdersList(CustomerShareholdersCreate value) {
		this.shareholdersList = value;
	}

	/**
	 * Gets the value of the financialDataList property.
	 *
	 * @return possible object is {@link ModifyCustomerFinancialDataRequest }
	 *
	 */
	public ModifyCustomerFinancialDataRequest getFinancialDataList() {
		return financialDataList;
	}

	/**
	 * Sets the value of the financialDataList property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerFinancialDataRequest }
	 *
	 */
	public void setFinancialDataList(ModifyCustomerFinancialDataRequest value) {
		this.financialDataList = value;
	}

}
