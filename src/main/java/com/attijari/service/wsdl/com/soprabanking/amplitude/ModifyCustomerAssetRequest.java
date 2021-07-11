package com.attijari.service.wsdl.com.soprabanking.amplitude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for modifyCustomerAssetRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="modifyCustomerAssetRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assetHeader" type="{http://soprabanking.com/amplitude}modifyCustomerAssetHeader" minOccurs="0"/>
 *         &lt;element name="assetsDetail" type="{http://soprabanking.com/amplitude}modifyCustomerAssets" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCustomerAssetRequest", propOrder = { "assetHeader",
		"assetsDetail" })
public class ModifyCustomerAssetRequest {

	protected ModifyCustomerAssetHeader assetHeader;
	protected ModifyCustomerAssets assetsDetail;

	/**
	 * Gets the value of the assetHeader property.
	 *
	 * @return possible object is {@link ModifyCustomerAssetHeader }
	 *
	 */
	public ModifyCustomerAssetHeader getAssetHeader() {
		return assetHeader;
	}

	/**
	 * Sets the value of the assetHeader property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerAssetHeader }
	 *
	 */
	public void setAssetHeader(ModifyCustomerAssetHeader value) {
		this.assetHeader = value;
	}

	/**
	 * Gets the value of the assetsDetail property.
	 *
	 * @return possible object is {@link ModifyCustomerAssets }
	 *
	 */
	public ModifyCustomerAssets getAssetsDetail() {
		return assetsDetail;
	}

	/**
	 * Sets the value of the assetsDetail property.
	 *
	 * @param value
	 *            allowed object is {@link ModifyCustomerAssets }
	 *
	 */
	public void setAssetsDetail(ModifyCustomerAssets value) {
		this.assetsDetail = value;
	}

}
