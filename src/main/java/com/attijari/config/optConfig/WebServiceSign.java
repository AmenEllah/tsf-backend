package com.attijari.config.optConfig;

import com.attijari.service.dto.RequestDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;


@Primary
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WebServiceSign {


    @Value("${sign.address_uri}")
    String address_uri;

    private static String REST_SERVICE_URI_LOGIN = null;
    private static String REST_SERVICE_URI_ADD_DOCUMENT = null;
    private static String REST_SERVICE_URI_GET_DOCUMENT = null;
    private static String REST_SERVICE_URI_GET_SUBSCRIBER_STATUS = null;
    private static Logger logger = LoggerFactory.getLogger(WebServiceSign.class.getName());

    private String getUriLogin() {
        REST_SERVICE_URI_LOGIN = address_uri + "/portalService/corporate/login/anibawajdi@gmail.com";
        return REST_SERVICE_URI_LOGIN;
    }

    private String getUriAddDocument() {
        REST_SERVICE_URI_ADD_DOCUMENT = address_uri + "/portalService/corporate/addDocument/";
        return REST_SERVICE_URI_ADD_DOCUMENT;
    }

    private String getUriGetDocument() {
        REST_SERVICE_URI_GET_DOCUMENT = address_uri + "/portalService/corporate/getDocument/";
        return REST_SERVICE_URI_GET_DOCUMENT;
    }

    private String getUriGetSubsciberStatus() {
        REST_SERVICE_URI_GET_SUBSCRIBER_STATUS = address_uri + "/portalService/corporate/getSubsciberSatus/";
        return REST_SERVICE_URI_GET_SUBSCRIBER_STATUS;
    }

    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "" + MediaType.APPLICATION_JSON);
        return headers;
    }


    public String wsAuthetificationSign() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>("123456", getHeaders());
        try {
            return restTemplate.postForObject(getUriLogin(), request, String.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public String wsAddDocument(String sessionId, String bytes, String fileName, String emailAdress, RequestDTO requestDTO) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        try {
            InfoDocument infoDocument = new InfoDocument();
            infoDocument.label = fileName;
            infoDocument.entrepriseId = "f069295a-8fbe-41ab-8565-516d6e2caf10";
            infoDocument.typeDocument = "PDF";
            infoDocument.ordered = false;
            infoDocument.notificationSignataires = false;
            infoDocument.complete = false;
            infoDocument.commentaire = "tester la signature electronique";
            Files file = new Files();
            file.name = fileName;
            file.bytes = bytes;
            infoDocument.files.add(file);
            ListeSignataires listeSignataire = new ListeSignataires();
            listeSignataire.ordre = 0;
            listeSignataire.email = emailAdress;
            if (requestDTO.getOfferId() == 1) {
                listeSignataire.currentPageNo = 2;
                listeSignataire.signatureOriginX = 370;
                listeSignataire.signatureOriginY = 200;
                listeSignataire.signatureHeight = 150;
                listeSignataire.signatureWidth = 150;
            } else if (requestDTO.getOfferId() == 2) {
                listeSignataire.currentPageNo = 2;
                listeSignataire.signatureOriginX = 350;
                listeSignataire.signatureOriginY = 230;
                listeSignataire.signatureHeight = 150;
                listeSignataire.signatureWidth = 150;
            }
            infoDocument.listeSignataires.add(listeSignataire);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoDocument);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException occurred ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            logger.error("IOException occurred ", e);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(requestJson, getHeaders());
        try {
            return restTemplate.postForObject(getUriAddDocument() + sessionId, request, String.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public String wsGetDocument(String sessionId, String dossierId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> loginResponse = restTemplate.exchange(getUriGetDocument() + sessionId + "/" + dossierId, HttpMethod.GET, null, Map.class);
            System.out.println(loginResponse);
            if (loginResponse.getBody() != null && loginResponse.getStatusCode() == HttpStatus.OK) {
                JSONObject userJson = new JSONObject(loginResponse.getBody());
                JSONArray array = userJson.getJSONArray("files");
                for (int i = 0; i < array.length(); i++) {
                    return array.getJSONObject(i).getString("bytes");
                }
            } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return null;
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @JsonSerialize
    public static class InfoDocument {
        String label;
        String entrepriseId;
        String typeDocument;
        boolean ordered;
        boolean notificationSignataires;
        boolean complete;
        String commentaire;
        ArrayList<Files> files = new ArrayList<>();
        ArrayList<ListeSignataires> listeSignataires = new ArrayList<>();
    }

    @JsonSerialize
    public static class Files {
        String name;
        String size;
        String type;
        String bytes;
    }

    @JsonSerialize
    public static class ListeSignataires {
        int ordre;
        String email;
        int currentPageNo;
        int signatureOriginX;
        int signatureOriginY;
        int signatureHeight;
        int signatureWidth;
    }

    public static RestTemplate restTemplate()
        throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
            .setSSLSocketFactory(csf)
            .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    public SubsciberSatusOut wsGetSubsciberSatus(String sessionId, String email) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SubsciberSatusOut subsciberSatusOut = new SubsciberSatusOut();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate = restTemplate();

        try {
            ResponseEntity<Map> loginResponse = restTemplate.exchange(getUriGetSubsciberStatus() + sessionId + "/" + email, HttpMethod.GET, null, Map.class);
            if (loginResponse.getBody() != null && loginResponse.getStatusCode() == HttpStatus.OK) {
                JSONObject userJson = new JSONObject(loginResponse.getBody());
                subsciberSatusOut.setHasCertificate(userJson.getString("hasCertificate"));
                subsciberSatusOut.setStatus(userJson.getString("status"));
                return subsciberSatusOut;
            } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return null;
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;

    }


    public String wsAddDocu(String sessionId, String bytes, String fileName, String emailAdress) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        try {
            InfoDocument infoDocument = new InfoDocument();
            infoDocument.label = fileName;
            infoDocument.entrepriseId = "f069295a-8fbe-41ab-8565-516d6e2caf10";
            infoDocument.typeDocument = "PDF";
            infoDocument.ordered = false;
            infoDocument.notificationSignataires = false;
            infoDocument.complete = false;
            infoDocument.commentaire = "tester la signature élèctronique";
            Files file = new Files();
            file.name = fileName;
            file.bytes = bytes;
            infoDocument.files.add(file);
            ListeSignataires listeSignataire = new ListeSignataires();
            listeSignataire.ordre = 0;
            listeSignataire.email = emailAdress;
            listeSignataire.currentPageNo = 9;
            listeSignataire.signatureOriginX = 300;
            listeSignataire.signatureOriginY = 420;
            listeSignataire.signatureHeight = 150;
            listeSignataire.signatureWidth = 150;
            infoDocument.listeSignataires.add(listeSignataire);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoDocument);
        } catch (JsonGenerationException e) {

        } catch (JsonMappingException e) {
            logger.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            logger.error("IOException occurred ", e);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<>(requestJson, getHeaders());

        try {
            return restTemplate.postForObject(getUriAddDocument() + sessionId, request, String.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
