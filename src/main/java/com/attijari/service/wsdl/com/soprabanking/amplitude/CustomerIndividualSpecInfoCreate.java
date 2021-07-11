package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerIndividualSpecInfoCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerIndividualSpecInfoCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="individualGeneralInfo" type="{http://soprabanking.com/amplitude}customerIndividualGeneralInfoCreate" minOccurs="0"/>
 *         &lt;element name="birth" type="{http://soprabanking.com/amplitude}customerBirthCreate" minOccurs="0"/>
 *         &lt;element name="idPaper" type="{http://soprabanking.com/amplitude}customerIdPaperCreate" minOccurs="0"/>
 *         &lt;element name="territoriality" type="{http://soprabanking.com/amplitude}customerTerritorialityCreate" minOccurs="0"/>
 *         &lt;element name="family" type="{http://soprabanking.com/amplitude}customerFamily" minOccurs="0"/>
 *         &lt;element name="otherAttributes" type="{http://soprabanking.com/amplitude}customerOtherAttributes" minOccurs="0"/>
 *         &lt;element name="jointAccountsList" type="{http://soprabanking.com/amplitude}modifyCustomerJointAccountsRequest" minOccurs="0"/>
 *         &lt;element name="childrenList" type="{http://soprabanking.com/amplitude}modifyCustomerChildrenRequest" minOccurs="0"/>
 *         &lt;element name="customerBudget" type="{http://soprabanking.com/amplitude}modifyCustomerBudgetRequest" minOccurs="0"/>
 *         &lt;element name="professionAndIncomesList" type="{http://soprabanking.com/amplitude}modifyCustomerProfessionsAndIncomesRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerIndividualSpecInfoCreate", propOrder = {
		"individualGeneralInfo", "birth", "idPaper", "territoriality",
		"family", "otherAttributes", "jointAccountsList", "childrenList",
		"customerBudget", "professionAndIncomesList" })
public class CustomerIndividualSpecInfoCreate {

	protected CustomerIndividualGeneralInfoCreate individualGeneralInfo;
	protected CustomerBirthCreate birth;
	protected CustomerIdPaperCreate idPaper;
	protected CustomerTerritorialityCreate territoriality;
	protected CustomerFamily family;
	protected CustomerOtherAttributes otherAttributes;
	protected ModifyCustomerJointAccountsRequest jointAccountsList;
	protected ModifyCustomerChildrenRequest childrenList;
	protected ModifyCustomerBudgetRequest customerBudget;
	protected ModifyCustomerProfessionsAndIncomesRequest professionAndIncomesList;

	/**
	 * Gets the value of the individualGeneralInfo property.
	 *
	 * @return possible object is {@link CustomerIndividualGeneralInfoCreate }
	 *
	 */
	public CustomerIndividualGeneralInfoCreate getIndividualGeneralInfo() {
		return individualGeneralInfo;
	}

	/**
	 * Sets the value of the individualGeneralInfo property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerIndividualGeneralInfoCreate }
	 *
	 */
	public void setIndividualGeneralInfo(
			CustomerIndividualGeneralInfoCreate value) {
		this.individualGeneralInfo = value;
	}

	/**
	 * Gets the value of the birth property.
	 *
	 * @return possible object is {@link CustomerBirthCreate }
	 *
	 */
	public CustomerBirthCreate getBirth() {
		return birth;
	}

	/**
	 * Sets the value of the birth property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerBirthCreate }
	 *
	 */
	public void setBirth(CustomerBirthCreate value) {
		this.birth = value;
	}

	/**
	 * Gets the value of the idPaper property.
	 *
	 * @return possible object is {@link CustomerIdPaperCreate }
	 *
	 */
	public CustomerIdPaperCreate getIdPaper() {
		return idPaper;
	}

	/**
	 * Sets the value of the idPaper property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerIdPaperCreate }
	 *
	 */
	public void setIdPaper(CustomerIdPaperCreate value) {
		this.idPaper = value;
	}

	/**
	 * Gets the value of the territoriality property.
	 *
	 * @return possible object is {@link CustomerTerritorialityCreate }
	 *
	 */
	public CustomerTerritorialityCreate getTerritoriality() {
		return territoriality;
	}

	/**
	 * Sets the value of the territoriality property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerTerritorialityCreate }
	 *
	 */
	public void setTerritoriality(CustomerTerritorialityCreate value) {
		this.territoriality = value;
	}

	/**
	 * Gets the value of the family property.
	 *
	 * @return possible object is {@link CustomerFamily }
	 *
	 */
	public CustomerFamily getFamily() {
		return family;
	}

	/**
	 * Sets the value of the family property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerFamily }
	 *
	 */
	public void setFamily(CustomerFamily value) {
		this.family = value;
	}

	/**
	 * Gets the value of the otherAttributes property.
	 *
	 * @return possible object is {@link CustomerOtherAttributes }
	 *
	 */
	public CustomerOtherAttributes getOtherAttributes() {
		return otherAttributes;
	}

	/**
	 * Sets the value of the otherAttributes property.
	 *
	 * @param value
	 *            allowed object is {@link CustomerOtherAttributes }
	 *
	 */
	public void setOtherAttributes(CustomerOtherAttributes value) {
		this.otherAttributes = value;
	}

	/**
	 * Gets the value of the jointAccountsList property.
	 *
	 * @return possible object is {@link ModifyCustomerJointAccountsRequest }
	 *
	 */
	public ModifyCustomerJointAccountsRequest getJointAccountsList() {
		return jointAccountsList;
	}

	/**
	 * Sets the value of the jointAccountsList property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerJointAccountsRequest }
	 *
	 */
	public void setJointAccountsList(ModifyCustomerJointAccountsRequest value) {
		this.jointAccountsList = value;
	}

	/**
	 * Gets the value of the childrenList property.
	 *
	 * @return possible object is {@link ModifyCustomerChildrenRequest }
	 *
	 */
	public ModifyCustomerChildrenRequest getChildrenList() {
		return childrenList;
	}

	/**
	 * Sets the value of the childrenList property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerChildrenRequest }
	 *
	 */
	public void setChildrenList(ModifyCustomerChildrenRequest value) {
		this.childrenList = value;
	}

	/**
	 * Gets the value of the customerBudget property.
	 *
	 * @return possible object is {@link ModifyCustomerBudgetRequest }
	 *
	 */
	public ModifyCustomerBudgetRequest getCustomerBudget() {
		return customerBudget;
	}

	/**
	 * Sets the value of the customerBudget property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerBudgetRequest }
	 *
	 */
	public void setCustomerBudget(ModifyCustomerBudgetRequest value) {
		this.customerBudget = value;
	}

	/**
	 * Gets the value of the professionAndIncomesList property.
	 *
	 * @return possible object is
	 *         {@link ModifyCustomerProfessionsAndIncomesRequest }
	 *
	 */
	public ModifyCustomerProfessionsAndIncomesRequest getProfessionAndIncomesList() {
		return professionAndIncomesList;
	}

	/**
	 * Sets the value of the professionAndIncomesList property.
	 *
	 * @param value
	 *            allowed object is
	 *            {@link ModifyCustomerProfessionsAndIncomesRequest }
	 *
	 */
	public void setProfessionAndIncomesList(
			ModifyCustomerProfessionsAndIncomesRequest value) {
		this.professionAndIncomesList = value;
	}

}
