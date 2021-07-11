package com.attijari.service.impl;

import com.attijari.service.AppMailingService;
import com.attijari.config.optConfig.MiddleWareService;
import com.attijari.config.optConfig.Response;
import com.attijari.domain.AccessToken;
import com.attijari.domain.User;
import com.attijari.domain.enumeration.AttachmentStatus;
import com.attijari.domain.enumeration.Civility;
import com.attijari.service.UserService;
import com.attijari.service.dto.RequestDTO;
import com.attijari.service.dto.UserDTO;
import io.github.jhipster.config.JHipsterProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppMailingServiceImpl implements AppMailingService {

    private static final Logger log = LoggerFactory.getLogger(AppMailingServiceImpl.class);

    @Value("${mail.electronic-sign}")
    String electronic_sign;

    @Value("${mail.authentication}")
    String authenticationUrl;

    @Value("${callback}")
    String callback;

    @Value("${sign.address_uri}")
    String signUri;

    @Value("${bo.url}")
    String boUrl;

    private final MiddleWareService middleWareService;

    private final SpringTemplateEngine templateEngine;

    private final JHipsterProperties jHipsterProperties;

    private final UserService userService;

    public AppMailingServiceImpl(MiddleWareService middleWareService, SpringTemplateEngine templateEngine, JHipsterProperties jHipsterProperties, UserService userService) {
        this.middleWareService = middleWareService;
        this.templateEngine = templateEngine;
        this.jHipsterProperties = jHipsterProperties;
        this.userService = userService;
    }


    @Override
    public ResponseEntity<Response> sendVerificationCode(User user, String subject, String verificationCode) {
        log.info("Sending verification mail to [{}] with code {}", user.getEmail(), verificationCode);
        Context context = new Context();
        context.setVariable("user", user.getFirstName() + " " + user.getLastName());
        context.setVariable("verificationCode", verificationCode);
        context.setVariable("civility", user.getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        return middleWareService.sendEmail(user.getEmail(), null, null, subject, createContentFromTemplate("mail/accescode", context));
    }

    @Override
    public ResponseEntity<Response> sendConfirmationEmail(RequestDTO requestDTO, String subject) {
        String accessToken = generateAccessToken(requestDTO);
        String redirectLink = "&redirectTo=" + jHipsterProperties.getMail().getBaseUrl() + "offer/civil-status/" + requestDTO.getId();

        Context context = new Context();
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("personalDTO", requestDTO.getPersonalInfo());
        context.setVariable("title", subject);
        context.setVariable("email", jHipsterProperties.getMail().getBaseUrl() + authenticationUrl + "?access_token=" + accessToken + redirectLink);
        context.setVariable("baseUrl", jHipsterProperties.getMail().getBaseUrl());
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/email_verification", context));
    }

    @Override
    public ResponseEntity<Response> sendAcceptedRequestEmail(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        context.setVariable("name", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("requestId", requestDTO.getId() + ".");
        List<String> products = new ArrayList<>();
        if (requestDTO.getOffer() != null) {
            products.add(requestDTO.getOffer().getName());
        }
        if (requestDTO.getRequestBankAccounts() != null && !requestDTO.getRequestBankAccounts().isEmpty()) {
            requestDTO.getRequestBankAccounts().forEach(requestBankAccountDTO -> {
                products.add(requestBankAccountDTO.getBankAccount().getLibelleFR());
            });
        }
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        context.setVariable("products", products);
        context.setVariable("url", jHipsterProperties.getMail().getBaseUrl());
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/accord", context));
    }

    @Override
    public ResponseEntity<Response> sendRefusedRequestEmail(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        context.setVariable("name", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("requestId", requestDTO.getId() + ".");
        context.setVariable("advisorPhone", "80 101 550");
        context.setVariable("url", jHipsterProperties.getMail().getBaseUrl());
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/rejet", context));

    }

    @Override
    public ResponseEntity<Response> sendMissedDocumentsRequestEmail(RequestDTO requestDTO, String subject) {
        String accessToken = generateAccessToken(requestDTO);
        String redirectLink = "&redirectTo=" + jHipsterProperties.getMail().getBaseUrl() + "offer/documents-manquants/" + requestDTO.getId();
        Context context = new Context();
        context.setVariable("name", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("requestId", requestDTO.getId() + ".");
        context.setVariable("attachments", requestDTO.getAttachments().stream().filter(attachmentDTO -> Arrays.asList(AttachmentStatus.INVALID, AttachmentStatus.TO_COMPLETE).contains(attachmentDTO.getStatus())).collect((Collectors.toList())));
        context.setVariable("url", jHipsterProperties.getMail().getBaseUrl() + authenticationUrl + "?access_token=" + accessToken + redirectLink);
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/complement", context));
    }

    @Override
    public ResponseEntity<Response> sendEmailForElectronicCertificate(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        String accessToken = generateAccessToken(requestDTO);
        String redirectLink = "&redirectTo=" + jHipsterProperties.getMail().getBaseUrl() + "offer/signatureContrat/" + requestDTO.getId();
        context.setVariable("personalDTO", requestDTO.getPersonalInfo());
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        context.setVariable("title", subject);
        context.setVariable("baseUrl", jHipsterProperties.getMail().getBaseUrl() + authenticationUrl + "?access_token=" + accessToken + redirectLink);
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/Certificat electronique", context));
    }

    @Override
    public ResponseEntity<Response> switchMobileForJustificatifsByMail(RequestDTO requestDTO, int section) {
        Context context = new Context();
        String accessToken = generateAccessToken(requestDTO);
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        String redirectLink = "&redirectTo=" + jHipsterProperties.getMail().getBaseUrl() + "offer/justificatif/" + section;
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("url", jHipsterProperties.getMail().getBaseUrl() + authenticationUrl + "?access_token=" + accessToken + redirectLink + "&request_id=" + requestDTO.getId());
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, "Finalisez votre demande sur votre smartphone", createContentFromTemplate("mail/switch_mobile", context));
    }

    @Override
    public String switchMobileForJustificatifsByQrCode(RequestDTO requestDTO, int section) {
        String accessToken = generateAccessToken(requestDTO);
        String redirectLink = "&redirectTo=" + jHipsterProperties.getMail().getBaseUrl() + "offer/justificatif/" + section;
        return jHipsterProperties.getMail().getBaseUrl() + authenticationUrl + "?access_token=" + accessToken + redirectLink+ "&request_id=" + requestDTO.getId();
    }

    @Override
    public ResponseEntity<Response> sendEmailForRTAccount(RequestDTO requestDTO, String subject, String Login) {
        Context context = new Context();
        context.setVariable("title", subject);
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("login", Login);
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        context.setVariable("baseUrl", jHipsterProperties.getMail().getBaseUrl());
        context.setVariable("accounts", requestDTO.getRequestBankAccounts());
        context.setVariable("agency", requestDTO.getPersonalInfo().getAgency().getName());
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/Login RT", context));
    }

    @Override
    public ResponseEntity<Response> sendRequestRegistrationMail(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        String accessToken = generateAccessToken(requestDTO);
        String redirectLink = "&redirectTo=" + jHipsterProperties.getMail().getBaseUrl() + "StepRedirection/" + requestDTO.getId();
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("personalDTO", requestDTO.getPersonalInfo());
        context.setVariable("title", subject);
        context.setVariable("request", requestDTO.getId());
        context.setVariable("url", jHipsterProperties.getMail().getBaseUrl() + authenticationUrl + "?access_token=" + accessToken + redirectLink);
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        context.setVariable("baseUrl", jHipsterProperties.getMail().getBaseUrl());
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/requestRegistration", context));
    }

    @Override
    public ResponseEntity<Response> followRequest(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        String verificationCode = RandomStringUtils.random(6, false, true);
        registerUser(requestDTO, verificationCode);
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("verificationCode", verificationCode);
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/accescode", context));
    }

    @Override
    public ResponseEntity<Response> sendRefusedRequestNonConformmail(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        context.setVariable("name", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("requestId", requestDTO.getId() + ".");
        context.setVariable("url", jHipsterProperties.getMail().getBaseUrl());
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/rejetEmailNonConform", context));
    }


    @Override
    public ResponseEntity<Response> sendDigigoReminder(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        String token = requestDTO.getTokenToSign();
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        context.setVariable("visioLink", signUri + "/pub/registration/internalProfile?token=" + token + "&email=" + requestDTO.getPersonalInfo().getEmail() + "&customTemplate=tsf&callback=" + callback);
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/digigoReminder", context));
    }

    private String createContentFromTemplate(String templateName, Context context) {
        return templateEngine.process(templateName, context);
    }

    private User registerUser(RequestDTO requestDTO, String verificationCode) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(requestDTO.getPersonalInfo().getEmail());
        userDTO.setEmail(requestDTO.getPersonalInfo().getEmail());
        userDTO.setFirstName(requestDTO.getPersonalInfo().getFirstName());
        userDTO.setLastName(requestDTO.getPersonalInfo().getLastName());
        userDTO.setCivility(requestDTO.getPersonalInfo().getCivility());
        userDTO.setLangKey("FR");
        return userService.registerSimpleUser(userDTO, verificationCode);
    }

    private User updateAccessTokenForUser(RequestDTO requestDTO, AccessToken accessToken) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(requestDTO.getPersonalInfo().getEmail());
        userDTO.setEmail(requestDTO.getPersonalInfo().getEmail());
        userDTO.setFirstName(requestDTO.getPersonalInfo().getFirstName());
        userDTO.setLastName(requestDTO.getPersonalInfo().getLastName());
        userDTO.setCivility(requestDTO.getPersonalInfo().getCivility());
        userDTO.setLangKey("FR");
        return userService.updateAccessToken(userDTO, accessToken);
    }

    @Override
    public ResponseEntity<Response> sendRecapEmail(RequestDTO requestDTO, String subject) {
        Context context = new Context();
        List<String> attachments = new ArrayList<>();
        String accessToken = generateAccessToken(requestDTO);
        String redirectLink = "&redirectTo=" + jHipsterProperties.getMail().getBaseUrl() + "offer/justificatif/" + requestDTO.getId();
        context.setVariable("civility", requestDTO.getPersonalInfo().getCivility().equals(Civility.MONSIEUR) ? "Monsieur" : "Madame");
        context.setVariable("user", requestDTO.getPersonalInfo().getFirstName() + " " + requestDTO.getPersonalInfo().getLastName());
        attachments.add("Votre pièce d'identité nationale");
        if (requestDTO.getPersonalInfo().getSecondNationality() != null) {
            attachments.add("Justificatif de la deuxième nationalité");
        }
        attachments.add("Justificatifs de votre résidence à l'étranger");
        attachments.add("Justificatifs de votre/vos revenu(s)");
        if (requestDTO.getPersonalInfo().isAmericanIndex()) {
            attachments.add("Justificatif de vos indices d'américanité");
        }
        context.setVariable("attachments", attachments);
        context.setVariable("title", subject);
        context.setVariable("baseUrl", jHipsterProperties.getMail().getBaseUrl() + authenticationUrl + "?access_token=" + accessToken + redirectLink);
        return middleWareService.sendEmail(requestDTO.getPersonalInfo().getEmail(), null, null, subject, createContentFromTemplate("mail/recap", context));
    }

    @Override
    public ResponseEntity<Response> sendDerogationEmail(RequestDTO requestDTO,String email) {
        String subject = "Demande de dérogation";
        Context context = new Context();
        context.setVariable("ref", requestDTO.getId());
        context.setVariable("nom", requestDTO.getPersonalInfo().getLastName());
        context.setVariable("prenom", requestDTO.getPersonalInfo().getFirstName());
        context.setVariable("link", boUrl);

        return middleWareService.sendEmail(email, null, null, subject, createContentFromTemplate("mail/derogation", context));
    }

    private String generateAccessToken (RequestDTO requestDTO) {
        String token = RandomStringUtils.random(30, true, true);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(token);
        accessToken.setNumberOfUse(0);
        accessToken.setCreationDate(new Date());
        updateAccessTokenForUser(requestDTO, accessToken);
        return token;
    }
}
