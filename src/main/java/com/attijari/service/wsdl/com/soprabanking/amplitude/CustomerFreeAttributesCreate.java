package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerFreeAttributesCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerFreeAttributesCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="freeAttribute" type="{http://soprabanking.com/amplitude}createCustomerFreeAttributeRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerFreeAttributesCreate", propOrder = { "freeAttribute" })
public class CustomerFreeAttributesCreate {

	@XmlElement(nillable = true)
	protected List<CreateCustomerFreeAttributeRequest> freeAttribute;

	/**
	 * Gets the value of the freeAttribute property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the freeAttribute property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getFreeAttribute().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CreateCustomerFreeAttributeRequest }
	 *
	 *
	 */
	public List<CreateCustomerFreeAttributeRequest> getFreeAttribute() {
		if (freeAttribute == null) {
			freeAttribute = new ArrayList<CreateCustomerFreeAttributeRequest>();
		}
		return this.freeAttribute;
	}

}
