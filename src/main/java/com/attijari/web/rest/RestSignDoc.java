package com.attijari.web.rest;

import com.attijari.config.optConfig.SubsciberSatusOut;
import com.attijari.service.*;
import com.attijari.service.dto.*;
import com.attijari.config.optConfig.WebServiceSign;
import com.attijari.domain.enumeration.AttachmentStatus;
import com.attijari.domain.enumeration.AttachmentType;
import com.attijari.domain.enumeration.RequestStatus;
import com.attijari.web.Utils.BusinessResourceException;
import com.attijari.web.Utils.StorageService;
import com.attijari.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class RestSignDoc {

    private final RequestService requestService;

    private final StorageService storageService;

    private final DocumentService documentService;

    private final AttachmentService attachmentService;

    private final RequestQueryService requestQueryService;

    private final SubscriberStatusService subscriberStatusService;

    private final AttachmentQueryService attachmentQueryService;

    private final WebServiceSign webServiceSign;

    private static final Logger logger = LogManager.getLogger(RestSignDoc.class);


    public RestSignDoc(RequestService requestService, StorageService storageService, DocumentService documentService, AttachmentService attachmentService, RequestQueryService requestQueryService, SubscriberStatusService subscriberStatusService, AttachmentQueryService attachmentQueryService, WebServiceSign webServiceSign) {
        this.requestService = requestService;
        this.storageService = storageService;
        this.documentService = documentService;
        this.attachmentService = attachmentService;
        this.requestQueryService = requestQueryService;
        this.subscriberStatusService = subscriberStatusService;
        this.attachmentQueryService = attachmentQueryService;
        this.webServiceSign = webServiceSign;
    }

    @ApiOperation(value = "Authentication", hidden = true)
    @PostMapping("/api/login")
    public HashMap<String, String> login() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HashMap<String, String> hashMap = new HashMap<>();
        String sessionId = webServiceSign.wsAuthetificationSign();
        hashMap.put("sessionId", sessionId);
        return hashMap;
    }

    @ApiOperation(value = "addDocument", hidden = true)
    @PostMapping("/api/document/{RequestId}")
    public ResponseEntity<HashMap<String, String>> addDocument(@PathVariable("RequestId") Long RequestId, @RequestBody String pdfbase64) {
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            Optional<RequestDTO> requestDTO = requestService.findOne(RequestId);
            if (requestDTO.isPresent()) {
                String fileName = null;
                if (requestDTO.get().getPersonalInfo().getEmail() != null) {
                    if (requestDTO.get().getOfferId() == 1) {
                        fileName = "Bulletin de souscription pack BLEDI+.pdf";
                    } else if (requestDTO.get().getOfferId() == 2) {
                        fileName = "Bulletin de souscription PRIVILEGES BLEDI.pdf";
                    }
                    String sessionId = webServiceSign.wsAuthetificationSign();
                    String dossierId = webServiceSign.wsAddDocument(sessionId, pdfbase64, fileName, requestDTO.get().getPersonalInfo().getEmail(), requestDTO.get());
                    if (dossierId != null) {
                        DocumentDTO documentDTO = requestDTO.get().getDocument();
                        documentDTO.setIdDossierSigned(dossierId);
                        documentDTO.setNomFichier(fileName);
                        documentService.save(documentDTO);
                    }
                    hashMap.put("dossierId", dossierId);
                    hashMap.put("emailAdress", requestDTO.get().getPersonalInfo().getEmail());
                    return ResponseEntity.ok(hashMap);
                }
            } else
                hashMap.put("error", "document does not exist");
            return ResponseEntity.ok(hashMap);

        } catch (Exception e) {
            logger.info("exeption -> addDocument" + e.getMessage());
            throw new BusinessResourceException("technical Error", "technical Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "getDocument", hidden = true)
    @GetMapping("/api/document/{idRequest}")
    public ResponseEntity<HashMap<String, String>> getDocument(@PathVariable("idRequest") Long idRequest) throws IOException {
        Optional<RequestDTO> requestDTO = requestService.findOne(idRequest);
        if (requestDTO.isPresent()) {
            try {
                String sessionId = webServiceSign.wsAuthetificationSign();
                HashMap<String, String> hashMap = new HashMap<>();
                String bytes = webServiceSign.wsGetDocument(sessionId, requestDTO.get().getDocument().getIdDossierSigned());
                if (bytes != null) {
                    Optional<DocumentDTO> deblocageEntity = documentService.findOne(requestDTO.get().getDocument().getId());
                    if (deblocageEntity.isPresent() && deblocageEntity.get().getNomFichier() != null && deblocageEntity.get().getEmplacement() != null) {
                        String path = deblocageEntity.get().getEmplacement() + deblocageEntity.get().getNomFichier();
                        this.storageService.storeFileDeblocage(bytes, path);
                        deblocageEntity.get().setHasSigned(true);
                        documentService.save(deblocageEntity.get());
                        AttachmentDTO attachmentDTO = new AttachmentDTO();
                        attachmentDTO.setFileName("Contrat.pdf");
                        attachmentDTO.setName("Contrat.pdf");
                        attachmentDTO.setAttachmentType(AttachmentType.CONTRACT);
                        attachmentDTO.setStatus(AttachmentStatus.SIGNED);
                        attachmentDTO.setRequestId(requestDTO.get().getId());
                        byte[] file = java.util.Base64.getDecoder().decode(bytes);
                        attachmentService.uploadAttachment(file, attachmentDTO);
                        requestDTO.get().setRequestStatus(RequestStatus.SIGNED);
                        requestService.save(requestDTO.get());
                    }
                }
                hashMap.put("Status", "SUCCESS");
                return ResponseEntity.ok(hashMap);
            } catch (Exception e) {
                throw new BusinessResourceException("technical Error", "technical Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }


    @RequestMapping(value = "/api/downloadFile", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> downloadFileToSign(@RequestParam(required = false) Long idOffer, HttpServletRequest request) {
        try {
            Resource resource = null;
            if (idOffer == 1) {
                resource = storageService.loadFile("Bulletin de souscription pack BLEDI+.pdf");
            } else if (idOffer == 2) {
                resource = storageService.loadFile("Bulletin de souscription PRIVILEGES BLEDI.pdf");
            }
            String contentType = null;
            try {
                if (resource != null) {
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                }
            } catch (IOException ex) {
                logger.info("Could not determine file type.");
            }
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } catch (Exception e) {
            throw new BusinessResourceException("technical Error", "technical Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/download")
    @ResponseBody
    public ResponseEntity<Resource> addDoc(HttpServletRequest request) {
        try {
            Resource resource = null;
            resource = storageService.loadFile("Bulletin de souscription PRIVILEGES BLEDI.pdf");
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                logger.info("Could not determine file type.");
            }
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } catch (Exception e) {
            throw new BusinessResourceException("technical Error", "technical Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/kyc/getSubscriberInfo")
    public ResponseEntity<SubscriberInfoOutputDTO> getSubscriberInfo(@RequestParam("token") String token, @RequestParam("email") String email) throws IOException {
        SubscriberInfoOutputDTO subscriberInfoOutputDTO = new SubscriberInfoOutputDTO();
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setTokenToSign((StringFilter) new StringFilter().setEquals(token));
        List<RequestDTO> requestDTOS = requestQueryService.findByCriteria(requestCriteria);
        if (requestDTOS != null && requestDTOS.get(0).getPersonalInfo() != null) {
            SubscriberInfoOutputDTO.Data data = new SubscriberInfoOutputDTO.Data();
            data.setEmail(email);
            data.setBirthday(requestDTOS.get(0).getPersonalInfo().getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            data.setFirst_name(requestDTOS.get(0).getPersonalInfo().getFirstName());
            data.setLast_name(requestDTOS.get(0).getPersonalInfo().getLastName());
            data.setPhone(requestDTOS.get(0).getPersonalInfo().getPhone());
            data.setCin(requestDTOS.get(0).getPersonalInfo().getCin());
            AttachmentCriteria attachmentCriteria = new AttachmentCriteria();
            attachmentCriteria.setRequestId((LongFilter) new LongFilter().setEquals(requestDTOS.get(0).getId()));
            attachmentCriteria.setAttachmentType((AttachmentCriteria.AttachmentTypeFilter) new AttachmentCriteria.AttachmentTypeFilter().setEquals(AttachmentType.CIN_RECTO));
            List<AttachmentDTO> attachmentDTOS = attachmentQueryService.findByCriteria(attachmentCriteria);
            if (!attachmentDTOS.isEmpty()) {
                data.setCin_recto(attachmentService.downloadUploadedAttachmentAsBytes(attachmentDTOS.get(0).getId()));
            }
            AttachmentCriteria attachmentCriteria1 = new AttachmentCriteria();
            attachmentCriteria1.setRequestId((LongFilter) new LongFilter().setEquals(requestDTOS.get(0).getId()));
            attachmentCriteria1.setAttachmentType((AttachmentCriteria.AttachmentTypeFilter) new AttachmentCriteria.AttachmentTypeFilter().setEquals(AttachmentType.CIN_VERSO));
            List<AttachmentDTO> attachmentDTOS1 = attachmentQueryService.findByCriteria(attachmentCriteria1);
            if (!attachmentDTOS1.isEmpty()) {
                data.setCin_verso(attachmentService.downloadUploadedAttachmentAsBytes(attachmentDTOS1.get(0).getId()));
            }
            subscriberInfoOutputDTO.setData(data);
            subscriberInfoOutputDTO.setStatus(true);
            return ResponseEntity.ok(subscriberInfoOutputDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/api/subscriber-status/{idRequest}")
    public ResponseEntity<SubsciberSatusOut> getSubsciberSatus(@PathVariable Long idRequest) {
        try {
            Optional<RequestDTO> requestDTO = requestService.findOne(idRequest);
            if (requestDTO.isPresent() && requestDTO.get().getPersonalInfo() != null && requestDTO.get().getPersonalInfo().getEmail() != null) {
                String sessionId = webServiceSign.wsAuthetificationSign();
                SubsciberSatusOut subsciberSatusOut = webServiceSign.wsGetSubsciberSatus(sessionId, requestDTO.get().getPersonalInfo().getEmail());
                return ResponseUtil.wrapOrNotFound(Optional.of(subsciberSatusOut));
            } else {
                return ResponseUtil.wrapOrNotFound(Optional.empty());
            }
        } catch (Exception e) {
            throw new BusinessResourceException("technical Error", "technical Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "addDocument", hidden = true)
    @PostMapping("/api/document-account/{idRequest}")
    public ResponseEntity<HashMap<String, String>> addDocu(@PathVariable("idRequest") Long idRequest, @RequestBody String pdfbase64) {
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            Optional<RequestDTO> requestDTO = requestService.findOne(idRequest);
            if (requestDTO.isPresent()) {
                PersonalInfoDTO personalInfoDTO = requestDTO.get().getPersonalInfo();
                if (personalInfoDTO == null) {
                    throw new BadRequestAlertException("incompleted personal informations", "PersonalInfos", "personalinfosnull");
                }
                String emailAdress = personalInfoDTO.getEmail();
                if (emailAdress != null) {
                    String fileName = null;
                    fileName = "Convention de compte.pdf";
                    String sessionId = webServiceSign.wsAuthetificationSign();
                    String dossierId = webServiceSign.wsAddDocu(sessionId, pdfbase64, fileName, emailAdress);
                    if (dossierId != null) {
                        DocumentDTO documentDTO = requestDTO.get().getDocument();
                        documentDTO.setIdDossierSigned(dossierId);
                        documentDTO.setNomFichier(fileName);
                        documentService.save(documentDTO);
                    }
                    hashMap.put("dossierId", dossierId);
                    hashMap.put("emailAdress", emailAdress);
                    return ResponseEntity.ok(hashMap);
                } else
                    hashMap.put("error", "document does not exist");
                return ResponseEntity.ok(hashMap);
            }
        } catch (Exception e) {
            logger.info("exeption -> addDocument" + e.getMessage());
            throw new BusinessResourceException("technical Error", "technical Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @RequestMapping(value = "/kyc/updateStatusSubscribe", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public void updateStatusSubscribe(@ModelAttribute SubscriberStatusDTO subscriberStatusDTO) {
        System.out.println("************************  Appel Web Service KYC 0 ************");
        if (subscriberStatusDTO != null) {
            RequestCriteria requestCriteria = new RequestCriteria();
            requestCriteria.setCin((StringFilter) new StringFilter().setEquals(subscriberStatusDTO.getNumCin()));
            requestCriteria.setEmail((StringFilter) new StringFilter().setEquals(subscriberStatusDTO.getEmail()));
            requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.REFUSED, RequestStatus.FINISHED)));
            List<RequestDTO> requestDTOS = requestQueryService.findByCriteria(requestCriteria);
            System.out.println("************************  Appel Web Service KYC 1 ************");
            System.out.println("************************  subscriberStatusDTO ************" + subscriberStatusDTO);
            if (!requestDTOS.isEmpty()) {
                AttachmentDTO attachmentDTO = new AttachmentDTO();
                attachmentDTO.setName("signature.png");
                attachmentDTO.setFileName("signature.png");
                attachmentDTO.setStatus(AttachmentStatus.VALID);
                attachmentDTO.setAttachmentType(AttachmentType.SIGNATURE);
                attachmentDTO.setRequestId(requestDTOS.get(0).getId());
                System.out.println("************************  Appel Web Service KYC 2 ************");
                if (subscriberStatusDTO.getSignature() != null) {
                    System.out.println("************************  Appel Web Service KYC 3 ************");
                    byte[] file = java.util.Base64.getDecoder().decode(subscriberStatusDTO.getSignature());
                    attachmentService.uploadAttachment(file, attachmentDTO);
                }
                if (requestDTOS.get(0).getSubscriberStatus() != null) {
                    subscriberStatusDTO.setId(requestDTOS.get(0).getSubscriberStatus().getId());
                }
                subscriberStatusDTO = subscriberStatusService.save(subscriberStatusDTO);
                requestDTOS.get(0).setSubscriberStatus(subscriberStatusDTO);
                requestService.save(requestDTOS.get(0));

            }
        }
    }
}

