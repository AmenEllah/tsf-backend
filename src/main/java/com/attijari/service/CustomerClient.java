package com.attijari.service;

import com.attijari.domain.enumeration.Civility;
import com.attijari.service.dto.RequestDTO;
import com.attijari.service.dto.SupplyMatrixCriteria;
import com.attijari.service.dto.SupplyMatrixDTO;
import com.attijari.service.wsdl.com.soprabanking.amplitude.*;
import com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws.CreateCustomer;
import com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws.CreateCustomerResponse;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CustomerClient extends WebServiceGatewaySupport {


    private static final Logger log = LoggerFactory.getLogger(CustomerClient.class);

    @Value("${middleware.protocol}")
    private String protocol;

    @Value("${middleware.host}")
    private String host;

    @Value("${middleware.port}")
    private String port;

    @Value("${middleware.uri.aif}")
    private String uri;

    @Autowired
    private SupplyMatrixQueryService supplyMatrixQueryService;

    public CreateCustomerResponse createCustomer(RequestDTO requestDTO) {
        List<SupplyMatrixDTO> supplyMatrixDTOS = findInSupplyMatrix(requestDTO);
        if (!supplyMatrixDTOS.isEmpty()) {
            CreateCustomer customer = InitCustomer(requestDTO, supplyMatrixDTOS);
            JAXBElement<CreateCustomerResponse> response = (JAXBElement<CreateCustomerResponse>) getWebServiceTemplate()
                .marshalSendAndReceive(getUri(), customer);
            return response.getValue();
        } else {
            return new CreateCustomerResponse();
        }


    }

    private CreateCustomer InitCustomer(RequestDTO requestDTO, List<SupplyMatrixDTO> supplyMatrixDTOS) {
        SupplyMatrixDTO supplyMatrix = supplyMatrixDTOS.get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            .withZone(ZoneId.systemDefault());

        CreateCustomer customer = new CreateCustomer();
        customer.setCredentialCode("a46bfba74e680149ec4803bafd11c7f4");
        CreateCustomerRequestFlow createCustomerRequestFlow = createCustomerRequestFlow(requestDTO, supplyMatrix, formatter);

        customer.setCreateCustomerRequestFlow(createCustomerRequestFlow);
        log.info("Creating customer  " + customer);
        return customer;
    }

    private CreateCustomerRequestFlow createCustomerRequestFlow(RequestDTO requestDTO, SupplyMatrixDTO supplyMatrix, DateTimeFormatter formatter) {
        CreateCustomerRequestFlow createCustomerRequestFlow = new CreateCustomerRequestFlow();
        // requestHeader
        RequestHeader requestHeader = createRequestHeader();
        createCustomerRequestFlow.setRequestHeader(requestHeader);

        // createCustomerRequest
        CreateCustomerRequest createCustomerRequest = createCustomerRequest(requestDTO, supplyMatrix, formatter);
        createCustomerRequestFlow.setCreateCustomerRequest(createCustomerRequest);
        return createCustomerRequestFlow;
    }

    private CreateCustomerRequest createCustomerRequest(RequestDTO requestDTO, SupplyMatrixDTO supplyMatrix, DateTimeFormatter formatter) {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setCustomerType("1");
        createCustomerRequest.setLanguage("001");
        createCustomerRequest.setTitleCode("04");
        createCustomerRequest.setLastName(requestDTO.getPersonalInfo().getLastName());
        createCustomerRequest.setFreeFieldCode1("1");
        createCustomerRequest.setFreeFieldCode2("002");
        createCustomerRequest.setFreeFieldCode3(requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "01" : "02");

        CustomerIdPapersCreate customerIdPapersCreate = new CustomerIdPapersCreate();
        CreateCustomerIdPaperRequest createCustomerIdPaperRequest = new CreateCustomerIdPaperRequest();
        createCustomerIdPaperRequest.setIdPaperType(requestDTO.getPersonalInfo().getAbroadResidancyProof());
        createCustomerIdPaperRequest.setIdPaperNumber(requestDTO.getPersonalInfo().getAbroadResidancyNumber());

        try {
            XMLGregorianCalendar result = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(formatter.format(requestDTO.getPersonalInfo().getAbroadResidancyDeliveryDate()));
            createCustomerIdPaperRequest.setDeliveryDate(result);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        try {
            XMLGregorianCalendar result = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(formatter.format(requestDTO.getPersonalInfo().getAbroadResidancyExpirationDate()));
            createCustomerIdPaperRequest.setValidityDateOfIdPaper(result);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        createCustomerIdPaperRequest.setDeliveryPlace("TUNIS");

        customerIdPapersCreate.getIdPaper().add(createCustomerIdPaperRequest);
        createCustomerRequest.setIdPapersList(customerIdPapersCreate);

        CustomerSituationCreate customerSituationCreate = new CustomerSituationCreate();
        customerSituationCreate.setNationalityCode(requestDTO.getPersonalInfo().getNationality().getCode());
        customerSituationCreate.setCountryOfResidence(requestDTO.getPersonalInfo().getAdressInfo().getCountry().getCode());
        createCustomerRequest.setSituation(customerSituationCreate);

        CustomerSpecInfoCreate customerSpecInfoCreate = new CustomerSpecInfoCreate();
        CustomerIndividualSpecInfoCreate customerIndividualSpecInfoCreate = new CustomerIndividualSpecInfoCreate();
        CustomerIndividualGeneralInfoCreate customerIndividualGeneralInfoCreate = new CustomerIndividualGeneralInfoCreate();
        customerIndividualGeneralInfoCreate.setFirstname(requestDTO.getPersonalInfo().getFirstName());
        customerIndividualGeneralInfoCreate.setFirstname(requestDTO.getPersonalInfo().getFirstName());
        switch (requestDTO.getPersonalInfo().getMaritalStatus()) {
            case "Célibataire":
                customerIndividualGeneralInfoCreate.setFamilyStatusCode("C");
                break;
            case "Marié(e)":
                customerIndividualGeneralInfoCreate.setFamilyStatusCode("M");
                break;
            case "Divorcé(e)":
                customerIndividualGeneralInfoCreate.setFamilyStatusCode("D");
                break;
            case "Veuf(ve)":
                customerIndividualGeneralInfoCreate.setFamilyStatusCode("V");
                break;
        }
        if (requestDTO.getPersonalInfo().getMaritalStatus().equals("Marié(e)")) {
            customerIndividualGeneralInfoCreate.setMarriageSettlementCode("I");
        }
        customerIndividualGeneralInfoCreate.setHolderLegalCapacity("A");
        customerIndividualSpecInfoCreate.setIndividualGeneralInfo(customerIndividualGeneralInfoCreate);

        CustomerBirthCreate customerBirthCreate = new CustomerBirthCreate();
        customerBirthCreate.setHolderSex(requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "M" : "F");
        try {
            XMLGregorianCalendar result = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(formatter.format(requestDTO.getPersonalInfo().getBirthday()));
            customerBirthCreate.setBirthDate(result);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        customerBirthCreate.setBirthCountry("788");
        customerIndividualSpecInfoCreate.setBirth(customerBirthCreate);

        CustomerIdPaperCreate customerIdPaperCreate = new CustomerIdPaperCreate();
        customerIdPaperCreate.setType("CIN");
        customerIdPaperCreate.setIdPaperDeliveryPlace("TUNIS");
        customerIdPaperCreate.setIdPaperNumber(requestDTO.getPersonalInfo().getCin());
        try {
            XMLGregorianCalendar result = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(formatter.format(requestDTO.getPersonalInfo().getCinDeliveryDate()));
            customerIdPaperCreate.setIdPaperDeliveryDate(result);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        customerIndividualSpecInfoCreate.setIdPaper(customerIdPaperCreate);

        CustomerTerritorialityCreate customerTerritorialityCreate = new CustomerTerritorialityCreate();
        customerTerritorialityCreate.setTerritorialityCode("2");
        customerIndividualSpecInfoCreate.setTerritoriality(customerTerritorialityCreate);

        CustomerFamily customerFamily = new CustomerFamily();
        customerFamily.setNumberOfChildren(requestDTO.getPersonalInfo().getNbrkids());
        customerIndividualSpecInfoCreate.setFamily(customerFamily);

        ModifyCustomerProfessionsAndIncomesRequest modifyCustomerProfessionsAndIncomesRequest = new ModifyCustomerProfessionsAndIncomesRequest();
        CustomerProfessionAndIncomesInformations customerProfessionAndIncomesInformations = new CustomerProfessionAndIncomesInformations();
        customerProfessionAndIncomesInformations.setProfessionCode(supplyMatrix.getProfessionCode());
        try {
            XMLGregorianCalendar result = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("2017-10-10");
            customerProfessionAndIncomesInformations.setHireDate(result);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        modifyCustomerProfessionsAndIncomesRequest.getProfessionAndIncomes().add(customerProfessionAndIncomesInformations);
        customerIndividualSpecInfoCreate.setProfessionAndIncomesList(modifyCustomerProfessionsAndIncomesRequest);

        customerSpecInfoCreate.setIndividualSpecInfo(customerIndividualSpecInfoCreate);
        createCustomerRequest.setSpecificInformation(customerSpecInfoCreate);

        CustomerGeneralAttributesCreate customerGeneralAttributesCreate = new CustomerGeneralAttributesCreate();
        customerGeneralAttributesCreate.setBranchCode(getAgencyCode(requestDTO.getPersonalInfo().getAgency().getCode()));
        customerGeneralAttributesCreate.setCustomerOfficer(requestDTO.getPersonalInfo().getAgency().getManagerCode());
        customerGeneralAttributesCreate.setTaxableCustomer(true);
        customerGeneralAttributesCreate.setInternalCategoryCode("5");
        customerGeneralAttributesCreate.setSegment(supplyMatrix.getSegmentCode());

        CustomerFreeAttributesCreate customerFreeAttributesCreate = new CustomerFreeAttributesCreate();

        if (requestDTO.getGreenCard() != null) {
            CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestGreen = new CreateCustomerFreeAttributeRequest();
            AdditionalData additionalDataGreen = new AdditionalData();
            additionalDataGreen.setIdentifier("057");
            AdditionalDataValue additionalDataValueGreen = new AdditionalDataValue();
            additionalDataValueGreen.setAlphanumeric(requestDTO.getGreenCard() ? "O" : "N");
            additionalDataGreen.setValue(additionalDataValueGreen);
            createCustomerFreeAttributeRequestGreen.setAdditionalData(additionalDataGreen);
            customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestGreen);
        }

        if (requestDTO.getPersonalInfo().isAmericanIndex() != null) {
            CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestFatca = new CreateCustomerFreeAttributeRequest();
            AdditionalData additionalDataFatca = new AdditionalData();
            additionalDataFatca.setIdentifier("055");
            AdditionalDataValue additionalDataValueFatca = new AdditionalDataValue();
            additionalDataValueFatca.setAlphanumeric(requestDTO.getPersonalInfo().isAmericanIndex() ? "O" : "N");
            additionalDataFatca.setValue(additionalDataValueFatca);
            createCustomerFreeAttributeRequestFatca.setAdditionalData(additionalDataFatca);
            customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestFatca);
        }


        if (requestDTO.getPoliticallyExposed() != null) {
            CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestPoliExpo = new CreateCustomerFreeAttributeRequest();
            AdditionalData additionalDataPoliExpo = new AdditionalData();
            additionalDataPoliExpo.setIdentifier("051");
            AdditionalDataValue additionalDataValuePoliExpo = new AdditionalDataValue();
            additionalDataValuePoliExpo.setAlphanumeric(requestDTO.getPoliticallyExposed() ? "O" : "N");
            additionalDataPoliExpo.setValue(additionalDataValuePoliExpo);
            createCustomerFreeAttributeRequestPoliExpo.setAdditionalData(additionalDataPoliExpo);
            customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestPoliExpo);
        }

        CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestRevType = new CreateCustomerFreeAttributeRequest();
        AdditionalData additionalDataRevType = new AdditionalData();
        additionalDataRevType.setIdentifier("007");
        AdditionalDataValue additionalDataValueRevType = new AdditionalDataValue();
        additionalDataValueRevType.setAlphanumeric(supplyMatrix.getIncomeTypeCode());
        additionalDataRevType.setValue(additionalDataValueRevType);
        createCustomerFreeAttributeRequestRevType.setAdditionalData(additionalDataRevType);
        customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestRevType);

        CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestRevAmount = new CreateCustomerFreeAttributeRequest();
        AdditionalData additionalDataRevAmount = new AdditionalData();
        additionalDataRevAmount.setIdentifier("029");
        AdditionalDataValue additionalDataValueRevAmount = new AdditionalDataValue();
        additionalDataValueRevAmount.setAmountOrRate(moyenne(requestDTO.getPersonalInfo().getFinancialInfo().getMonthlyNetIncome().getIncomesFR()));
        additionalDataRevAmount.setValue(additionalDataValueRevAmount);
        createCustomerFreeAttributeRequestRevAmount.setAdditionalData(additionalDataRevAmount);
        customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestRevAmount);

        CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestjuridic = new CreateCustomerFreeAttributeRequest();
        AdditionalData additionalDatajuridic = new AdditionalData();
        additionalDatajuridic.setIdentifier("038");
        AdditionalDataValue additionalDataValuejuridic = new AdditionalDataValue();
        additionalDataValuejuridic.setAlphanumeric("54");
        additionalDatajuridic.setValue(additionalDataValuejuridic);
        createCustomerFreeAttributeRequestjuridic.setAdditionalData(additionalDatajuridic);
        customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestjuridic);

        if (requestDTO.getPersonalInfo().getSecondNationality() != null) {
            CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestOtherNationality = new CreateCustomerFreeAttributeRequest();
            AdditionalData additionalDataOtherNationality = new AdditionalData();
            additionalDataOtherNationality.setIdentifier("053");
            AdditionalDataValue additionalDataValueOtherNationality = new AdditionalDataValue();
            additionalDataValueOtherNationality.setAlphanumeric(requestDTO.getPersonalInfo().getSecondNationality().getCode());
            additionalDataOtherNationality.setValue(additionalDataValueOtherNationality);
            createCustomerFreeAttributeRequestOtherNationality.setAdditionalData(additionalDataOtherNationality);
            customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestOtherNationality);
        }

        CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestRisk = new CreateCustomerFreeAttributeRequest();
        AdditionalData additionalDataRisk = new AdditionalData();
        additionalDataRisk.setIdentifier("054");
        AdditionalDataValue additionalDataValueRisk = new AdditionalDataValue();
        additionalDataValueRisk.setAlphanumeric("001");
        additionalDataRisk.setValue(additionalDataValueRisk);
        createCustomerFreeAttributeRequestRisk.setAdditionalData(additionalDataRisk);
        customerFreeAttributesCreate.getFreeAttribute().add(createCustomerFreeAttributeRequestRisk);

        customerGeneralAttributesCreate.setFreeAttributes(customerFreeAttributesCreate);
        createCustomerRequest.setGeneralAttributes(customerGeneralAttributesCreate);

        CustomerReportingAttributesCreate customerReportingAttributesCreate = new CustomerReportingAttributesCreate();
        customerReportingAttributesCreate.setEconomicAgentCode("340");
        customerReportingAttributesCreate.setActivityFieldCode(requestDTO.getPersonalInfo().getFinancialInfo().getActivity().getCode());
        customerReportingAttributesCreate.setRelationshipWithTheBank("013");

        CustomerFreeAttributesCreate customerFreeAttributesCreateActivity = new CustomerFreeAttributesCreate();
        CreateCustomerFreeAttributeRequest createCustomerFreeAttributeRequestActivity = new CreateCustomerFreeAttributeRequest();
        AdditionalData additionalDataActivity = new AdditionalData();
        additionalDataActivity.setIdentifier("056");
        AdditionalDataValue additionalDataValueactivity = new AdditionalDataValue();
        additionalDataValueactivity.setAlphanumeric(requestDTO.getPersonalInfo().getFinancialInfo().getActivity().getCode());
        additionalDataActivity.setValue(additionalDataValueactivity);
        createCustomerFreeAttributeRequestActivity.setAdditionalData(additionalDataActivity);
        customerFreeAttributesCreateActivity.getFreeAttribute().add(createCustomerFreeAttributeRequestActivity);
        customerReportingAttributesCreate.setFreeAttributes(customerFreeAttributesCreateActivity);
        createCustomerRequest.setReportingAttributes(customerReportingAttributesCreate);

        CustomerAddressesCreate customerAddressesCreate = new CustomerAddressesCreate();
        CreateCustomerAddressRequest createCustomerAddressRequestC = new CreateCustomerAddressRequest();
        AddressCreateIdentifier addressCreateIdentifierC = new AddressCreateIdentifier();
        addressCreateIdentifierC.setType("C");
        createCustomerAddressRequestC.setIdentifier(addressCreateIdentifierC);
        createCustomerAddressRequestC.setLanguageCode("001");
        createCustomerAddressRequestC.setAddressFormat("GE");
        adressRefactor(requestDTO, createCustomerAddressRequestC);
        createCustomerAddressRequestC.setCity(requestDTO.getPersonalInfo().getAdressInfo().getCity());
        createCustomerAddressRequestC.setPostalCode(requestDTO.getPersonalInfo().getAdressInfo().getZip().toString());
        createCustomerAddressRequestC.setCountryCode(requestDTO.getPersonalInfo().getAdressInfo().getCountry().getCode());
        customerAddressesCreate.getAddress().add(createCustomerAddressRequestC);

        CreateCustomerAddressRequest createCustomerAddressRequestD = new CreateCustomerAddressRequest();
        AddressCreateIdentifier addressCreateIdentifierD = new AddressCreateIdentifier();
        addressCreateIdentifierD.setType("D");
        createCustomerAddressRequestD.setIdentifier(addressCreateIdentifierD);
        createCustomerAddressRequestD.setLanguageCode("001");
        createCustomerAddressRequestD.setAddressFormat("GE");
        adressRefactor(requestDTO, createCustomerAddressRequestD);
        createCustomerAddressRequestD.setCity(requestDTO.getPersonalInfo().getAdressInfo().getCity());
        createCustomerAddressRequestD.setPostalCode(requestDTO.getPersonalInfo().getAdressInfo().getZip().toString());
        createCustomerAddressRequestD.setCountryCode(requestDTO.getPersonalInfo().getAdressInfo().getCountry().getCode());
        customerAddressesCreate.getAddress().add(createCustomerAddressRequestD);

        createCustomerRequest.setAdressesList(customerAddressesCreate);

        CustomerPhoneNumbersCreate customerPhoneNumbersCreate = new CustomerPhoneNumbersCreate();
        CreateCustomerPhoneNumberRequest createCustomerPhoneNumberRequest = new CreateCustomerPhoneNumberRequest();
        PhoneNumberCreateIdentifier phoneNumberCreateIdentifier = new PhoneNumberCreateIdentifier();
        phoneNumberCreateIdentifier.setType("002");
        createCustomerPhoneNumberRequest.setIdentifier(phoneNumberCreateIdentifier);
        createCustomerPhoneNumberRequest.setPhoneNumber(requestDTO.getPersonalInfo().getPhone());
        customerPhoneNumbersCreate.getPhoneNumber().add(createCustomerPhoneNumberRequest);

        createCustomerRequest.setPhoneNumbersList(customerPhoneNumbersCreate);


        CustomerEmailAddressesCreate customeremailAdressesCreate = new CustomerEmailAddressesCreate();
        CreateCustomerEmailAddressRequest createCustomerEmailAddresseRequest = new CreateCustomerEmailAddressRequest();
        EmailCreateIdentifier EmailAddresseCreateIdentifier = new EmailCreateIdentifier();
        EmailAddresseCreateIdentifier.setType("001");
        createCustomerEmailAddresseRequest.setIdentifier(EmailAddresseCreateIdentifier);
        createCustomerEmailAddresseRequest.setEmail(requestDTO.getPersonalInfo().getEmail());
        customeremailAdressesCreate.getEmailAddress().add(createCustomerEmailAddresseRequest);

        createCustomerRequest.setEmailAdressesList(customeremailAdressesCreate);

        return createCustomerRequest;
    }

    private void adressRefactor(RequestDTO requestDTO, CreateCustomerAddressRequest createCustomerAddressRequestC) {
        if (requestDTO.getPersonalInfo().getAdressInfo().getAddress().length() <= 30) {
            createCustomerAddressRequestC.setAddressLine1(requestDTO.getPersonalInfo().getAdressInfo().getAddress());
        } else {
            String subAddress = requestDTO.getPersonalInfo().getAdressInfo().getAddress().substring(0, 30);
            int index = Math.min(subAddress.trim().lastIndexOf(' '), 29);
            createCustomerAddressRequestC.setAddressLine1(requestDTO.getPersonalInfo().getAdressInfo().getAddress().substring(0, index + 1));
            String subAdress2 = requestDTO.getPersonalInfo().getAdressInfo().getAddress().substring(index + 1);
            if (subAdress2.length() < 30) {
                createCustomerAddressRequestC.setAddressLine2(requestDTO.getPersonalInfo().getAdressInfo().getAddress().substring(index + 1));
            } else {
                int index2 = Math.min(subAdress2.trim().lastIndexOf(' '), 29);
                createCustomerAddressRequestC.setAddressLine2(requestDTO.getPersonalInfo().getAdressInfo().getAddress().substring(index + 1, index + index2 + 2));
                createCustomerAddressRequestC.setAddressLine3(requestDTO.getPersonalInfo().getAdressInfo().getAddress().substring(index + index2 + 2, Math.min(requestDTO.getPersonalInfo().getAdressInfo().getAddress().length(), index + index2 + 32)));
            }
        }
    }

    private RequestHeader createRequestHeader() {
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setRequestId("Id-ABT");
        requestHeader.setServiceName("createCustomer");
        SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        try {
            XMLGregorianCalendar result = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(timestampFormatter.format(new Date()));
            requestHeader.setTimestamp(result);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        requestHeader.setUserCode("FLC1");
        return requestHeader;
    }

    private List<SupplyMatrixDTO> findInSupplyMatrix(RequestDTO requestDTO) {
        SupplyMatrixCriteria supplyMatrixCriteria = new SupplyMatrixCriteria();
        supplyMatrixCriteria.setCategoryId((LongFilter) new LongFilter().setEquals(requestDTO.getPersonalInfo().getFinancialInfo().getCategory().getId()));
        supplyMatrixCriteria.setActivityId((LongFilter) new LongFilter().setEquals(requestDTO.getPersonalInfo().getFinancialInfo().getActivity().getId()));
        supplyMatrixCriteria.setMonthlyIncomeId((LongFilter) new LongFilter().setEquals(requestDTO.getPersonalInfo().getFinancialInfo().getMonthlyNetIncome().getId()));
        supplyMatrixCriteria.setMarket((StringFilter) new StringFilter().setEquals("TRE"));

        return supplyMatrixQueryService.findByCriteria(supplyMatrixCriteria);
    }

    private String getAgencyCode(String code) {
        while (code.length() < 5) {
            code = 0 + code;
        }
        return code;
    }

    private BigDecimal moyenne(String income) {
        String[] splitedIncome = income.split("-");
        BigDecimal somme = new BigDecimal(0);
        for (String s : splitedIncome) {
            String numberOnly = s.replaceAll("[^0-9]", "");
            int value = Integer.parseInt(numberOnly);
            somme = somme.add(new BigDecimal(value));
        }
        return somme.divide(new BigDecimal(splitedIncome.length), 0, RoundingMode.CEILING);
    }

    private String getUri() {
        return protocol + host + ":" + port + "/" + uri + "/AIFSOAPWS";
    }

}
