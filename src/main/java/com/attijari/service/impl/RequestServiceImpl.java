package com.attijari.service.impl;

import com.attijari.config.optConfig.WebServiceSign;
import com.attijari.domain.Request;
import com.attijari.domain.enumeration.RequestStatus;
import com.attijari.service.DocumentService;
import com.attijari.service.PersonalInfoService;
import com.attijari.service.RequestService;
import com.attijari.service.dto.DerogationDTO;
import com.attijari.service.dto.DocumentDTO;
import com.attijari.service.dto.PersonalInfoDTO;
import com.attijari.service.dto.RequestDTO;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.repository.RequestRepository;
import com.attijari.service.mapper.RequestMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


/**
 * Service Implementation for managing {@link Request}.
 */
@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

    private static final String ENTITY_NAME = "request";

    private final RequestRepository requestRepository;

    private final RequestMapper requestMapper;

    private final PersonalInfoService personalInfoService;

    private final DocumentService documentService;

    private final WebServiceSign webServiceSign;


    public RequestServiceImpl(RequestRepository requestRepository, RequestMapper requestMapper, PersonalInfoService personalInfoService, DocumentService documentService, WebServiceSign webServiceSign) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
        this.personalInfoService = personalInfoService;
        this.documentService = documentService;
        this.webServiceSign = webServiceSign;

    }

    @Override
    public RequestDTO save(RequestDTO requestDTO) {
        log.debug("Request to save Request : {}", requestDTO);
        if (requestDTO.getPersonalInfo() != null && requestDTO.getPersonalInfo().getBirthday() != null && LocalDate.now().minusYears(18).isBefore(requestDTO.getPersonalInfo().getBirthday())) {
            throw new BadRequestAlertException("Allowed age should be greater then or equal to 18 years old", ENTITY_NAME, "invalidage");
        }
        requestDTO.setId(requestRepository.save(requestMapper.toEntity(requestDTO).requestBankAccounts(new HashSet<>()).derogations(new HashSet<>())).getId());
        if (requestDTO.getRequestBankAccounts() != null) {
            requestDTO.getRequestBankAccounts().forEach(requestBankAccountDTO -> {
                requestBankAccountDTO.getId().setRequestId(requestDTO.getId());
                if (requestBankAccountDTO.getOppositionStatus() == null) {
                    requestBankAccountDTO.setOppositionStatus(false);
                }
            });
        }
        if (requestDTO.getDerogations() != null) {
            requestDTO.getDerogations()
                .forEach(derogationDTO -> derogationDTO.setRequestId(requestDTO.getId()));
        }
        if (requestDTO.getDerogations() != null && requestDTO.getDerogations().size() > 0 && requestDTO.getRequestStatus().equals(RequestStatus.DEROGATED)) {
            DerogationDTO derogationDTO = new ArrayList<>(requestDTO.getDerogations()).get(requestDTO.getDerogations().size() - 1);
            String matDerogation = derogationDTO.getMatricule() != null ? derogationDTO.getMatricule() : "N/A";
            requestDTO.setMatDerogation(matDerogation);
        } else {
            requestDTO.setMatDerogation("N/A");
        }
        if (requestDTO.getMatricule() == null) {
            requestDTO.setMatricule("N/A");
        }
        if(requestDTO.getVisioStatus() == null){
            requestDTO.setVisioStatus(false);
        }
        return requestMapper.toDto(requestRepository.save(requestMapper.toEntity(requestDTO)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Requests");
        return requestRepository.findAll(pageable)
            .map(requestMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RequestDTO> findOne(Long id) {
        log.debug("Request to get Request : {}", id);
        return requestRepository.findById(id)
            .map(requestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Request : {}", id);
        Optional<RequestDTO> existingRequestDTO = requestRepository.findById(id).map(requestMapper::toDto);
        if (existingRequestDTO.isPresent()) {
            if (existingRequestDTO.get().getPersonalInfo() != null) {
                personalInfoService.delete(existingRequestDTO.get().getPersonalInfo().getId());
            }
            if (existingRequestDTO.get().getDocument() != null) {
                documentService.delete(existingRequestDTO.get().getDocument().getId());
            }
        }
        requestRepository.deleteById(id);
    }


    @Override
    public Optional<RequestDTO> createDocumentsForRequest(Long requestId) {
        Optional<RequestDTO> existingRequestDTO = findOne(requestId);
        if (existingRequestDTO.isPresent()) {
            RequestDTO requestDTO = existingRequestDTO.get();
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setHasSigned(false);
            documentDTO.setEmplacement("C:/deblocage/");
            if (requestDTO.getOfferId() == 1) {
                documentDTO.setNomFichier("Bulletin de souscription pack BLEDI+.pdf");
            } else if (requestDTO.getOfferId() == 2) {
                documentDTO.setNomFichier("Bulletin de souscription PRIVILEGES BLEDI.pdf");
            } else {
                documentDTO.setNomFichier("Convention de compte.pdf");
            }
            documentDTO.setTypeDocument("PDF");
            requestDTO.setDocument(documentDTO);
            return Optional.of(save(requestDTO));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Boolean hasVisio(Long requestId) {
        try {
            Optional<RequestDTO> optionalRequestDTO = findOne(requestId);
            if (optionalRequestDTO.isPresent()) {
                if (!optionalRequestDTO.get().getVisioStatus()) {
                    PersonalInfoDTO personalInfoDTO = optionalRequestDTO.get().getPersonalInfo();
                    String sessionId = webServiceSign.wsAuthetificationSign();
                    if (Boolean.getBoolean(Objects.requireNonNull(webServiceSign.wsGetSubsciberSatus(sessionId, personalInfoDTO.getEmail())).getStatus())) {
                        RequestDTO requestDTO = optionalRequestDTO.get();
                        requestDTO.setVisioStatus(true);
                        save(requestDTO);
                        return true;
                    }
                } else
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
