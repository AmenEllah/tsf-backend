package com.attijari.service;

import com.attijari.config.optConfig.Response;
import com.attijari.domain.User;
import com.attijari.service.dto.RequestDTO;
import org.springframework.http.ResponseEntity;

public interface AppMailingService {

    ResponseEntity<Response> sendVerificationCode(User user, String subject, String verificationCode);

    ResponseEntity<Response> sendConfirmationEmail(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendAcceptedRequestEmail(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendRefusedRequestEmail(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendMissedDocumentsRequestEmail(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendEmailForElectronicCertificate(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendEmailForRTAccount(RequestDTO requestDTO, String subject,String Login);

    ResponseEntity<Response> sendRequestRegistrationMail(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> followRequest(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> switchMobileForJustificatifsByMail(RequestDTO requestDTO, int section);

    String switchMobileForJustificatifsByQrCode(RequestDTO requestDTO, int section);

    ResponseEntity<Response> sendRefusedRequestNonConformmail(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendDigigoReminder(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendRecapEmail(RequestDTO requestDTO, String subject);

    ResponseEntity<Response> sendDerogationEmail(RequestDTO requestDTO,String to);
}
