package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for customerGroupAndJobCreate complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="customerGroupAndJobCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customersGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subgroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="job" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjob" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerGroupAndJobCreate", propOrder = { "customersGroup",
		"subgroup", "job", "subjob" })
public class CustomerGroupAndJobCreate {

	protected String customersGroup;
	protected String subgroup;
	protected String job;
	protected String subjob;

	/**
	 * Gets the value of the customersGroup property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCustomersGroup() {
		return customersGroup;
	}

	/**
	 * Sets the value of the customersGroup property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCustomersGroup(String value) {
		this.customersGroup = value;
	}

	/**
	 * Gets the value of the subgroup property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSubgroup() {
		return subgroup;
	}

	/**
	 * Sets the value of the subgroup property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSubgroup(String value) {
		this.subgroup = value;
	}

	/**
	 * Gets the value of the job property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Sets the value of the job property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setJob(String value) {
		this.job = value;
	}

	/**
	 * Gets the value of the subjob property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSubjob() {
		return subjob;
	}

	/**
	 * Sets the value of the subjob property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSubjob(String value) {
		this.subjob = value;
	}

}
