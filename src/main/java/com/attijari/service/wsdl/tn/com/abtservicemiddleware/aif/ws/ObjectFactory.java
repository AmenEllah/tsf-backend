package com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the tn.com.abtservicemiddleware.aif.ws
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _CreateCustomer_QNAME = new QName(
			"http://ws.AIF.abtServiceMiddleware.com.tn/", "createCustomer");
	private final static QName _CreateCustomerResponse_QNAME = new QName(
			"http://ws.AIF.abtServiceMiddleware.com.tn/",
			"createCustomerResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: tn.com.abtservicemiddleware.aif.ws
	 *
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link DocumentHeader }
	 *
	 */
	public DocumentHeader createDocumentHeader() {
		return new DocumentHeader();
	}

	/**
	 * Create an instance of {@link CreateCustomerAIFResponse }
	 *
	 */
	public CreateCustomerAIFResponse createCreateCustomerAIFResponse() {
		return new CreateCustomerAIFResponse();
	}

	/**
	 * Create an instance of {@link CreateCustomerResponse }
	 *
	 */
	public CreateCustomerResponse createCreateCustomerResponse() {
		return new CreateCustomerResponse();
	}

	/**
	 * Create an instance of {@link CreateCustomer }
	 *
	 */
	public CreateCustomer createCreateCustomer() {
		return new CreateCustomer();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateCustomer }
	 * {@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://ws.AIF.abtServiceMiddleware.com.tn/", name = "createCustomer")
	public JAXBElement<CreateCustomer> createCreateCustomer(CreateCustomer value) {
		return new JAXBElement<CreateCustomer>(_CreateCustomer_QNAME,
				CreateCustomer.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CreateCustomerResponse }{@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://ws.AIF.abtServiceMiddleware.com.tn/", name = "createCustomerResponse")
	public JAXBElement<CreateCustomerResponse> createCreateCustomerResponse(
			CreateCustomerResponse value) {
		return new JAXBElement<CreateCustomerResponse>(
				_CreateCustomerResponse_QNAME, CreateCustomerResponse.class,
				null, value);
	}

}
