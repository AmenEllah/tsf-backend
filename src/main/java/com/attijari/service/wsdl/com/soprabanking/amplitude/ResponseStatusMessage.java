package com.attijari.service.wsdl.com.soprabanking.amplitude;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for responseStatusMessage complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="responseStatusMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nature" type="{http://soprabanking.com/amplitude}responseMessageNature"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="line" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="errorInformation" type="{http://soprabanking.com/amplitude}errorInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseStatusMessage", propOrder = { "nature", "code",
		"line", "errorInformation" })
public class ResponseStatusMessage {

	@XmlElement(required = true)
	protected ResponseMessageNature nature;
	@XmlElement(required = true)
	protected String code;
	@XmlElement(nillable = true)
	protected List<String> line;
	protected ErrorInformation errorInformation;

	/**
	 * Gets the value of the nature property.
	 *
	 * @return possible object is {@link ResponseMessageNature }
	 *
	 */
	public ResponseMessageNature getNature() {
		return nature;
	}

	/**
	 * Sets the value of the nature property.
	 *
	 * @param value
	 *            allowed object is {@link ResponseMessageNature }
	 *
	 */
	public void setNature(ResponseMessageNature value) {
		this.nature = value;
	}

	/**
	 * Gets the value of the code property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setCode(String value) {
		this.code = value;
	}

	/**
	 * Gets the value of the line property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the line property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getLine().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getLine() {
		if (line == null) {
			line = new ArrayList<String>();
		}
		return this.line;
	}

	/**
	 * Gets the value of the errorInformation property.
	 *
	 * @return possible object is {@link ErrorInformation }
	 *
	 */
	public ErrorInformation getErrorInformation() {
		return errorInformation;
	}

	/**
	 * Sets the value of the errorInformation property.
	 *
	 * @param value
	 *            allowed object is {@link ErrorInformation }
	 *
	 */
	public void setErrorInformation(ErrorInformation value) {
		this.errorInformation = value;
	}

}
