package com.attijari.config.optConfig;

import com.attijari.service.BotScanService;
import com.attijari.service.dto.RequestDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.*;

@Service
public class MiddleWareImpl implements MiddleWareService {

    private static final Logger log = LoggerFactory.getLogger(MiddleWareImpl.class);

    @Value("${middleware.protocol}")
    String middlewareProtocol;

    @Value("${middleware.host}")
    String middlewareHost;

    @Value("${middleware.port}")
    String middlewarePort;

    @Value("${middleware.username}")
    String middlewareUserName;

    @Value("${middleware.password}")
    String middlewarePassword;

    @Value("${middleware.uri.systeme}")
    String middlewareSystemeUri;

    @Value("${middleware.authorization}")
    String authorization;

    @Value("${middleware.uri.delta}")
    String middlewareDeltaUri;

    @Value("${middleware.uri.bct}")
    String middlewareBctUri;

    @Value("${java.naming.factory.initial}")
    String ldapCtxFactory;

    @Value("${java.naming.security.authentication}")
    String ldapAuthentication;

    @Value("${java.naming.referral}")
    String ldapReferral;

    @Value("${java.naming.provider.url}")
    String ldapUrl;

    @Value("${java.naming.security.principal}")
    String ldapPrincipal;

    @Value("${middleware.uri.idc}")
    String middlewareIdcUri;

    private final RestTemplate restTemplate;

    private final BotScanService botScanService;


    public MiddleWareImpl(RestTemplate restTemplate, BotScanService botScanService) {
        this.restTemplate = restTemplate;
        this.botScanService = botScanService;
    }


    @JsonSerialize
    public static class InfoCompteDeltatIn {
        String iden;
    }

    @JsonSerialize
    public static class InfoCompteIn {
        String cli;
    }

    @JsonSerialize
    public static class InfoCompteBctIn {
        String numCin;
        String dateNaiss;
    }

    class Email {
        String to;
        String cc;
        String bcc;
        String subject;
        String content;

        public Email() {

        }

        public Email(String to, String cc, String cci, String subject, String content) {
            this.to = to;
            this.cc = cc;
            this.bcc = cci;
            this.subject = subject;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Email{" +
                "to='" + to + '\'' +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
        }
    }

    private String uriEmail() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareSystemeUri + "/email/sendSimpleEmail";
    }

    private String uriDeltaInfoClientByIden() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareDeltaUri + "/clientsDelta/getInfoClientByIden";
    }

    private String uriBctgetFicheCre() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareBctUri + "/CentraleInformationBct/getFicheCre";
    }

    private String uriBctGetChequeImpaye() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareBctUri + "/CentraleInformationBct/getChequeImpaye";
    }

    private String uriBctGetActifClasse() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareBctUri + "/CentraleInformationBct/getActifClasse";
    }

    private String uriBctGetInfoClientAndComptebyCLI() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareDeltaUri + "/clientsDelta/getInfoClientAndComptebyCLI";
    }

    private String uriGetRTAccount() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareDeltaUri + "/RT/getRTAccount";
    }

    private String uriLeverOpposCompte() {
        return middlewareProtocol + middlewareHost + ":" + middlewarePort + "/" + middlewareDeltaUri + "/comptesDelta/LeverOpposCompte";
    }

    @Override
    public ResponseEntity<Response> sendEmail(String to, String cc, String cci, String subject, String content) {
        Email email = new Email(to, cc, cci, subject, content);
        log.debug("Request to send Email {}", email);
        return restTemplate.exchange(uriEmail(), HttpMethod.POST, createRequest(email), Response.class);
    }


    @Override
    public UserAD authenticationAD(String login, String password) {
        log.debug("Request to login to AD with {}", login);
        return checkLogin(login, password);
    }


    private HttpEntity<Object> createRequest(Email email) {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(email);
        } catch (Exception e) {
            log.error("JsonGenerationException occurred ", e);
        }
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        return request;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((middlewareUserName + ":" + middlewarePassword).getBytes()));
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

    private UserAD checkLogin(String username, String password) {
        Properties properties = new Properties();
        properties.put("java.naming.factory.initial", ldapCtxFactory);
        properties.put("java.naming.security.authentication", ldapAuthentication);
        properties.put("java.naming.referral", ldapReferral);
        properties.put("java.naming.provider.url", ldapUrl);
        properties.put("java.naming.security.principal", ldapPrincipal + username);
        properties.put("java.naming.security.credentials", password);
        try {
            DirContext ctx = new InitialDirContext(properties);
            SearchControls ctls = new SearchControls(2, 0L, 0, UserAD.requestedAttributes, false, false);
            NamingEnumeration<SearchResult> answerSearch = ctx.search("dc=bank-sud,dc=tn", "(&(objectCategory=person)(objectClass=user)(sAMAccountName=" + username + "))", ctls);
            if (answerSearch.hasMore()) {
                return new UserAD(answerSearch.next().getAttributes());
            } else {
                return null;
            }
        } catch (NamingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ClientDelta> getInfoClientByIden(String CIN) {
        log.info("---------- CALL API status Client=  ----------");
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        InfoCompteDeltatIn infoCompte = new InfoCompteDeltatIn();
        try {
            infoCompte.iden = CIN;
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoCompte);
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException occurred ", e);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            log.error("IOException occurred ", e);
        }
        log.error("requestJson" + requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        try {
            ResponseEntity<Response> loginResponse = restTemplate.exchange(uriDeltaInfoClientByIden(), HttpMethod.POST, request, Response.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                Response result = loginResponse.getBody();
                return result.getListClientsDelta();
            }
        } catch (Exception e) {
            log.error("listResultObject not existe in data JSON");
            log.error(e.getMessage());
        }
        return null;
    }


    @Override
    public FicheCreBct getFicheCre(String numCin, String dateNaiss) {
        log.info("---------- CALL API status Client=  ----------");
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        InfoCompteBctIn infoCompte = new InfoCompteBctIn();
        try {
            infoCompte.numCin = numCin;
            infoCompte.dateNaiss = dateNaiss;
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoCompte);
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException occurred ", e);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            log.error("IOException occurred ", e);
        }
        log.error("requestJson" + requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        try {
            ResponseEntity<Response> loginResponse = restTemplate.exchange(uriBctgetFicheCre(), HttpMethod.POST, request, Response.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                Response result = loginResponse.getBody();
                return result.getListFicheCreBct().get(0);
            }
        } catch (Exception e) {
            log.error("listResultObject not existe in data JSON");
            log.error(e.getMessage());
        }
        return null;
    }


    @Override
    public ChequeImpayeBct getChequeImpaye(String numCin, String dateNaiss) {
        log.info("---------- CALL API status Client=  ----------");
        List<Object> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        InfoCompteBctIn infoCompte = new InfoCompteBctIn();
        try {
            infoCompte.numCin = numCin;
            infoCompte.dateNaiss = dateNaiss;
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoCompte);
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException occurred ", e);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            log.error("IOException occurred ", e);
        }
        log.error("requestJson" + requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        try {
            ResponseEntity<Response> loginResponse = restTemplate.exchange(uriBctGetChequeImpaye(), HttpMethod.POST, request, Response.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                Response result = loginResponse.getBody();
                return result.getListChequeImpayeBct().get(0);
            }
        } catch (Exception e) {
            log.error("listResultObject not existe in data JSON");
            log.error(e.getMessage());
        }
        return null;
    }


    @Override
    public List<ActifClasseBct> getActifClasse(String numCin, String dateNaiss) {
        log.info("---------- CALL API status Client=  ----------");
        List<Object> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        InfoCompteBctIn infoCompte = new InfoCompteBctIn();
        try {
            infoCompte.numCin = numCin;
            infoCompte.dateNaiss = dateNaiss;
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoCompte);
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException occurred ", e);

        } catch (JsonMappingException e) {
            log.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            log.error("IOException occurred ", e);
        }
        log.error("requestJson" + requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        try {
            ResponseEntity<Response> loginResponse = restTemplate.exchange(uriBctGetActifClasse(), HttpMethod.POST, request, Response.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                Response result = loginResponse.getBody();
                return result.getListActifClasseBct();
            }
        } catch (Exception e) {
            log.error("listResultObject not existe in data JSON");
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public infoClient getInfoClientAndComptebyCLI(String numCli) {
        log.info("---------- CALL API status Client=  ----------");
        List<Object> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        InfoCompteIn infoCompte = new InfoCompteIn();
        try {
            infoCompte.cli = numCli;
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoCompte);
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException occurred ", e);

        } catch (JsonMappingException e) {
            log.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            log.error("IOException occurred ", e);
        }
        log.error("requestJson" + requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        try {
            ResponseEntity<Response> loginResponse = restTemplate.exchange(uriBctGetInfoClientAndComptebyCLI(), HttpMethod.POST, request, Response.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                Response result = loginResponse.getBody();
                infoClient infoClient = result.getInfoClient().get(0);
                infoClient.setListInfoCompte(result.getListInfoCompte());
                return infoClient;
            }
        } catch (Exception e) {
            log.error("listResultObject not existe in data JSON");
            log.error(e.getMessage());
        }
        return null;
    }


    @Override
    public List<CompteRealTimeDelta> getRTAccount(String numCli) {
        log.info("---------- CALL API status Client=  ----------");
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        InfoCompteIn infoCompte = new InfoCompteIn();
        try {
            infoCompte.cli = numCli;
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(infoCompte);
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException occurred ", e);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            log.error("IOException occurred ", e);
        }
        log.error("requestJson" + requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        try {
            ResponseEntity<Response> loginResponse = restTemplate.exchange(uriGetRTAccount(), HttpMethod.POST, request, Response.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                Response result = loginResponse.getBody();
                return result.getListComptesRealTimeAboDelta();
            }
        } catch (Exception e) {
            log.error("listResultObject not existe in data JSON");
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public DocumentHeader LeverOpposCompte(RequestDTO requestDTO, String accountNumber) {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = null;
        LeverOpposCompte leverOpposCompte = new LeverOpposCompte();
        try {
            leverOpposCompte.setAge(botScanService.getAgencyCode(requestDTO.getAgencyCode()));
            leverOpposCompte.setNcp(accountNumber);
            leverOpposCompte.setMotifOppos("TESTLEV");
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            requestJson = mapper.writeValueAsString(leverOpposCompte);
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException occrurred ", e);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException occurred ", e);
        } catch (IOException e) {
            log.error("IOException occurred ", e);
        }
        log.error("requestJson" + requestJson);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestJson, createHeaders());
        try {
            ResponseEntity<Response> loginResponse = restTemplate.exchange(uriLeverOpposCompte(), HttpMethod.POST, request, Response.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                Response result = loginResponse.getBody();
                return result.getDocumentHeader();
            }
        } catch (Exception e) {
            log.error("listResultObject not existe in data JSON");
            log.error(e.getMessage());
        }
        return null;
    }

}
