package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyCustomerLeadersRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerLeadersRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leader" type="{http://soprabanking.com/amplitude}customerLeaderInformations" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerLeadersRequest", propOrder = { "customerCode",
		"leader" })
public class ModifyCustomerLeadersRequest {

	protected String customerCode;
	@XmlElement(nillable = true)
	protected List<CustomerLeaderInformations> leader;

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
	 * Gets the value of the leader property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the leader property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getLeader().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CustomerLeaderInformations }
	 *
	 *
	 */
	public List<CustomerLeaderInformations> getLeader() {
		if (leader == null) {
			leader = new ArrayList<CustomerLeaderInformations>();
		}
		return this.leader;
	}

}
