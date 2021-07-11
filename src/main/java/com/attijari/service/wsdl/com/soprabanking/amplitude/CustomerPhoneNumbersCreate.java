package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerPhoneNumbersCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerPhoneNumbersCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phoneNumber" type="{http://soprabanking.com/amplitude}createCustomerPhoneNumberRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerPhoneNumbersCreate", propOrder = { "phoneNumber" })
public class CustomerPhoneNumbersCreate {

	@XmlElement(nillable = true)
	protected List<CreateCustomerPhoneNumberRequest> phoneNumber;

	/**
	 * Gets the value of the phoneNumber property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the phoneNumber property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getPhoneNumber().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CreateCustomerPhoneNumberRequest }
	 *
	 *
	 */
	public List<CreateCustomerPhoneNumberRequest> getPhoneNumber() {
		if (phoneNumber == null) {
			phoneNumber = new ArrayList<CreateCustomerPhoneNumberRequest>();
		}
		return this.phoneNumber;
	}

}
