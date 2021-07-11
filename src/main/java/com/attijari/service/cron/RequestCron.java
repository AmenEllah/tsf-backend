package com.attijari.service.cron;

import com.attijari.config.optConfig.*;
import com.attijari.service.*;
import com.attijari.service.dto.*;
import com.attijari.domain.enumeration.AttachmentType;
import com.attijari.domain.enumeration.RequestStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Profile(value = {"prod", "recette"})
public class RequestCron {

    private static final String mailSubject = "Certificat éléctronique";
    private static final String mailRTSubject = "Login Attijari Realtime / RIB(s)";
    private static final String mailVerificationSubject = "Activation de votre Compte";

    private final RequestQueryService requestQueryService;
    private final AppMailingService appMailingService;
    private final RequestService requestService;
    private final WebServiceSign webServiceSign;
    private final MiddleWareService middleWareService;

    private final AttachmentQueryService attachmentQueryService;

    private final BotScanService botScanService;

    public RequestCron(RequestQueryService requestQueryService, AppMailingService appMailingService, RequestService requestService, WebServiceSign webServiceSign, MiddleWareService middleWareService, AttachmentQueryService attachmentQueryService, BotScanService botScanService) {
        this.requestQueryService = requestQueryService;
        this.appMailingService = appMailingService;
        this.requestService = requestService;
        this.webServiceSign = webServiceSign;
        this.middleWareService = middleWareService;
        this.attachmentQueryService = attachmentQueryService;
        this.botScanService = botScanService;
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void verificationSign() {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.SAVED, RequestStatus.REFUSED, RequestStatus.TO_SIGN, RequestStatus.FINISHED)));
        List<RequestDTO> requests = requestQueryService.findByCriteria(requestCriteria);
        if (!requests.isEmpty()) {
            requests.forEach(requestDTO -> {
                if (requestDTO.getHasCertificate() && requestDTO.getRequestStatus().equals(RequestStatus.SOLD)) {
                    PersonalInfoDTO personalInfoDTO = requestDTO.getPersonalInfo();
                    if (personalInfoDTO != null && personalInfoDTO.getEmail() != null) {
                        appMailingService.sendEmailForElectronicCertificate(requestDTO, mailSubject);
                        requestDTO.setRequestStatus(RequestStatus.TO_SIGN);
                        requestService.save(requestDTO);
                    }
                } else if (!requestDTO.getHasCertificate()) {
                    PersonalInfoDTO personalInfoDTO = requestDTO.getPersonalInfo();
                    if (personalInfoDTO != null && personalInfoDTO.getEmail() != null) {
                        try {
                            String sessionId = webServiceSign.wsAuthetificationSign();
                            SubsciberSatusOut subsciberSatusOut = webServiceSign.wsGetSubsciberSatus(sessionId, personalInfoDTO.getEmail());
                            if (subsciberSatusOut != null && subsciberSatusOut.getHasCertificate().equals("true")) {
                                requestDTO.setHasCertificate(true);
                                requestService.save(requestDTO);
                                if (requestDTO.getRequestStatus().equals(RequestStatus.SOLD)) {
                                    appMailingService.sendEmailForElectronicCertificate(requestDTO, mailSubject);
                                    requestDTO.setRequestStatus(RequestStatus.TO_SIGN);
                                    requestService.save(requestDTO);
                                }
                            }
                        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (requestDTO.getRequestStatus().equals(RequestStatus.UNBLOCKED) && requestDTO.getAmplitudeRef() != null) {
                    List<CompteRealTimeDelta> compteRealTimeDeltas = middleWareService.getRTAccount(requestDTO.getAmplitudeRef());
                    if (!compteRealTimeDeltas.isEmpty()) {
                        String login = compteRealTimeDeltas.get(0).getLogin();
                        appMailingService.sendEmailForRTAccount(requestDTO, mailRTSubject, login);
                        requestDTO.setRequestStatus(RequestStatus.FINISHED);
                        requestService.save(requestDTO);
                    }
                }
            });
        }
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.SIGNED));
        List<RequestDTO> requestDTOS = requestQueryService.findByCriteria(requestCriteria);
        requestDTOS.forEach(requestDTO -> {
            requestDTO.getRequestBankAccounts().forEach(requestBankAccountDTO -> {
                if (requestBankAccountDTO.getAccountNumber() != null && !requestBankAccountDTO.getOppositionStatus()) {
                    DocumentHeader documentHeader = middleWareService.LeverOpposCompte(requestDTO, requestBankAccountDTO.getAccountNumber());
                    if (documentHeader != null && documentHeader.getResultMessage().equals("SUCCESS")) {
                        requestBankAccountDTO.setOppositionStatus(true);
                    }
                }
                BotScanDTO botScanDTO = new BotScanDTO();
                botScanDTO.setRef_demande(requestDTO.getId().toString());
                botScanDTO.setCliDelta(requestDTO.getAmplitudeRef());
                AttachmentCriteria attachmentCriteria = new AttachmentCriteria();
                attachmentCriteria.setRequestId((LongFilter) new LongFilter().setEquals(requestDTO.getId()));
                attachmentCriteria.setAttachmentType((AttachmentCriteria.AttachmentTypeFilter) new AttachmentCriteria.AttachmentTypeFilter().setEquals(AttachmentType.SIGNATURE));
                List<AttachmentDTO> attachmentDTOS = attachmentQueryService.findByCriteria(attachmentCriteria);
                if (!attachmentDTOS.isEmpty()) {
                    botScanDTO.setSignature(attachmentDTOS.get(0).getPath());
                }
                botScanDTO.setCompte(botScanService.getAgencyCode(requestDTO.getPersonalInfo().getAgency().getCode()) + requestBankAccountDTO.getAccountNumber());
                botScanService.save(botScanDTO);
            });
            RequestDTO request = requestService.save(requestDTO);
            AtomicBoolean allDeblocked = new AtomicBoolean(true);
            request.getRequestBankAccounts().forEach(requestBankAccountDTO -> {
                if (!requestBankAccountDTO.getOppositionStatus()) {
                    allDeblocked.set(false);
                }
            });
            if (allDeblocked.get()) {
                request.setRequestStatus(RequestStatus.UNBLOCKED);
                requestService.save(request);
                if (request.getAmplitudeRef() != null) {
                    List<CompteRealTimeDelta> compteRealTimeDeltas = middleWareService.getRTAccount(request.getAmplitudeRef());
                    if (!compteRealTimeDeltas.isEmpty()) {
                        String login = compteRealTimeDeltas.get(0).getLogin();
                        ResponseEntity<Response> responseEntity = appMailingService.sendEmailForRTAccount(request, mailRTSubject, login);
                        if (Objects.requireNonNull(responseEntity.getBody()).getDocumentHeader().getResultMessage().equals("SUCCESS")) {
                            request.setRequestStatus(RequestStatus.FINISHED);
                            requestService.save(request);
                        }
                    }
                }
            }
        });
    }

    @Scheduled(cron = "0 0 6 */1 * ?")
    public void raiseForSignatureOrToCompleteRequest() {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.TO_SIGN));
        List<RequestDTO> requestDTOS = requestQueryService.findByCriteria(requestCriteria);
        if (!requestDTOS.isEmpty()) {
            requestDTOS.forEach(requestDTO -> {
                appMailingService.sendEmailForElectronicCertificate(requestDTO, mailSubject);
            });
        }

        RequestCriteria requestCriteria1 = new RequestCriteria();
        requestCriteria1.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.IN_CREATION));
        List<RequestDTO> requestDTOLIST = requestQueryService.findByCriteria(requestCriteria1);
        if (!requestDTOLIST.isEmpty()) {
            requestDTOLIST.forEach(requestDTO -> {
                appMailingService.sendConfirmationEmail(requestDTO, mailVerificationSubject);
            });
        }
    }

    @Scheduled(cron = "0 0 6 */1 * ?")
    public void visioReminderJob() {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.PENDING));
        requestCriteria.setDateAppelVisio((StringFilter) new StringFilter().setSpecified(false));
        requestCriteria.setHasCertificate((BooleanFilter) new BooleanFilter().setEquals(false));
        requestCriteria.setVisioStatus((BooleanFilter) new BooleanFilter().setEquals(false));
        List<RequestDTO> requestDTOS = requestQueryService.findByCriteria(requestCriteria);
        requestDTOS.forEach(request -> {
            appMailingService.sendDigigoReminder(request, mailSubject);
        });
    }


}
