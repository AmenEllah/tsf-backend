package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for customerPaymentMethods complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerPaymentMethods">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chequeBookFacilitySuspension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chequeBookFacilitySuspensionDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="withdrawalOfCreditCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateOfWithdrawalOfCreditCard" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerPaymentMethods", propOrder = {
		"chequeBookFacilitySuspension", "chequeBookFacilitySuspensionDate",
		"withdrawalOfCreditCard", "dateOfWithdrawalOfCreditCard" })
public class CustomerPaymentMethods {

	protected String chequeBookFacilitySuspension;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar chequeBookFacilitySuspensionDate;
	protected String withdrawalOfCreditCard;
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar dateOfWithdrawalOfCreditCard;

	/**
	 * Gets the value of the chequeBookFacilitySuspension property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getChequeBookFacilitySuspension() {
		return chequeBookFacilitySuspension;
	}

	/**
	 * Sets the value of the chequeBookFacilitySuspension property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setChequeBookFacilitySuspension(String value) {
		this.chequeBookFacilitySuspension = value;
	}

	/**
	 * Gets the value of the chequeBookFacilitySuspensionDate property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getChequeBookFacilitySuspensionDate() {
		return chequeBookFacilitySuspensionDate;
	}

	/**
	 * Sets the value of the chequeBookFacilitySuspensionDate property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setChequeBookFacilitySuspensionDate(XMLGregorianCalendar value) {
		this.chequeBookFacilitySuspensionDate = value;
	}

	/**
	 * Gets the value of the withdrawalOfCreditCard property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getWithdrawalOfCreditCard() {
		return withdrawalOfCreditCard;
	}

	/**
	 * Sets the value of the withdrawalOfCreditCard property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setWithdrawalOfCreditCard(String value) {
		this.withdrawalOfCreditCard = value;
	}

	/**
	 * Gets the value of the dateOfWithdrawalOfCreditCard property.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getDateOfWithdrawalOfCreditCard() {
		return dateOfWithdrawalOfCreditCard;
	}

	/**
	 * Sets the value of the dateOfWithdrawalOfCreditCard property.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setDateOfWithdrawalOfCreditCard(XMLGregorianCalendar value) {
		this.dateOfWithdrawalOfCreditCard = value;
	}

}
