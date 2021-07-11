package com.attijari.web.rest;

import com.attijari.config.optConfig.*;
import com.attijari.domain.ActiveStaff;
import com.attijari.domain.Request;
import com.attijari.domain.enumeration.RejectStatus;
import com.attijari.domain.enumeration.RequestAffectation;
import com.attijari.domain.enumeration.StaffType;
import com.attijari.service.*;
import com.attijari.service.dto.*;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.domain.QrCodeUtils;
import com.attijari.domain.enumeration.RequestStatus;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.security.SecurityUtils;
import com.attijari.service.wsdl.tn.com.abtservicemiddleware.aif.ws.CreateCustomerResponse;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link Request}.
 */
@RestController
@RequestMapping("/api")
public class RequestResource {

    private final Logger log = LoggerFactory.getLogger(RequestResource.class);

    private static final String mailRecapSubject = "Documents à préparer pour finaliser votre demande";

    private static final String saveRequest = "Sauvegarde de la demande";

    private static final String ENTITY_NAME = "request";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestService requestService;

    private final RequestQueryService requestQueryService;

    private final MiddleWareService middleWareService;

    private final AppMailingService appMailingService;

    private final CustomerClient customerClient;

    private final StaffPermissionQueryService staffPermissionQueryService;

    private final ActiveStaffQueryService activeStaffQueryService;


    public RequestResource(RequestService requestService, RequestQueryService requestQueryService, MiddleWareService middleWareService, AppMailingService appMailingService, CustomerClient customerClient, StaffPermissionQueryService staffPermissionQueryService, ActiveStaffQueryService activeStaffQueryService) {
        this.requestService = requestService;
        this.requestQueryService = requestQueryService;
        this.middleWareService = middleWareService;
        this.appMailingService = appMailingService;
        this.customerClient = customerClient;
        this.staffPermissionQueryService = staffPermissionQueryService;
        this.activeStaffQueryService = activeStaffQueryService;
    }

    /**
     * {@code POST  /requests} : Create a new request.
     *
     * @param requestDTO the requestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestDTO, or with status {@code 400 (Bad Request)} if the request has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/requests")
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO requestDTO) throws URISyntaxException {
        requestDTO.setDocument(null);
        log.debug("REST request to save Request : {}", requestDTO);
        if (requestDTO.getId() != null) {
            throw new BadRequestAlertException("A new request cannot already have an ID", ENTITY_NAME, "idexists");
        }
        checkIfUsedEmail(requestDTO);
        checkIfUsedCin(requestDTO);
        RequestDTO result = requestService.save(requestDTO);
        return ResponseEntity.created(new URI("/api/requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /requests} : Updates an existing request.
     *
     * @param requestDTO the requestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestDTO,
     * or with status {@code 400 (Bad Request)} if the requestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/requests")
    public ResponseEntity<RequestDTO> updateRequest(@RequestBody RequestDTO requestDTO) {
        log.debug("REST request to update Request : {}", requestDTO);
        if (requestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<RequestDTO> existingRequestDTO = requestService.findOne(requestDTO.getId());
        if (existingRequestDTO.isPresent()) {
            if (existingRequestDTO.get().getPersonalInfo().getEmail() != null && !existingRequestDTO.get().getPersonalInfo().getEmail().equals(requestDTO.getPersonalInfo().getEmail())) {
                checkIfUsedEmail(requestDTO);
            }
            if (existingRequestDTO.get().getPersonalInfo().getCin() != null && !existingRequestDTO.get().getPersonalInfo().getCin().equals(requestDTO.getPersonalInfo().getCin())) {
                checkIfUsedCin(requestDTO);
            }
            if (requestDTO.getRequestStatus().equals(RequestStatus.DEROGATED) && existingRequestDTO.get().getRequestStatus().equals(RequestStatus.PENDING)) {
                setRequestTakenByAndMatricule(requestDTO);
            }

            if (requestDTO.getRequestStatus().equals(RequestStatus.DEROGATED) && requestDTO.getDerogations().size() != existingRequestDTO.get().getDerogations().size()) {
                //send derogation email
                final RequestAffectation lastAffectation = requestDTO.getDerogations().stream().reduce((first, second) -> second).orElse(new DerogationDTO()).getAffectation();
                if (lastAffectation.equals(RequestAffectation.CONFORMITY_UNIT)) {
                    this.sendDerogationEmail(requestDTO, StaffType.CONFORMITY_UNIT);
                } else if (lastAffectation.equals(RequestAffectation.REPOSITORY_UNIT)) {
                    this.sendDerogationEmail(requestDTO, StaffType.REPOSITORY_UNIT);
                }
            }
        }


        if (requestDTO.getRequestStatus().equals(RequestStatus.DEROGATED)) {
            requestDTO.setRequestAffectation(requestDTO.getDerogations().stream().map(DerogationDTO::getAffectation).collect(Collectors.toList()).get(requestDTO.getDerogations().size() - 1));
        } else {
            requestDTO.setRequestAffectation(null);
        }
        if (requestDTO.getPersonalInfo().isClientABT() == null && requestDTO.getPersonalInfo().getCin() != null) {
            List<ClientDelta> clientDeltaList = middleWareService.getInfoClientByIden(requestDTO.getPersonalInfo().getCin());
            if (clientDeltaList != null && !clientDeltaList.isEmpty()) {
                requestDTO.getPersonalInfo().setClientABT(true);
                requestDTO.setAmplitudeRef(clientDeltaList.get(0).getCli());
            } else {
                requestDTO.getPersonalInfo().setClientABT(false);
            }
        }
        if (requestDTO.getHasCertificate() && requestDTO.getRequestStatus() == RequestStatus.TO_SIGN) {
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setHasSigned(false);
            documentDTO.setTypeDocument("PDF");
            if (requestDTO.getOfferId() == 1) {
                documentDTO.setNomFichier("Bulletin de souscription pack BLEDI+.pdf");
            } else if (requestDTO.getOfferId() == 2) {
                documentDTO.setNomFichier("Bulletin de souscription PRIVILEGES BLEDI.pdf");
            } else {
                documentDTO.setNomFichier("Convention de compte.pdf");
            }
            requestDTO.setDocument(documentDTO);
        }
        if (requestDTO.getRequestStatus().equals(RequestStatus.PENDING)) {
            String token = RandomStringUtils.random(30, true, true);
            requestDTO.setTokenToSign(token);
        }
        RequestDTO result = requestService.save(requestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestDTO.getId().toString()))
            .body(result);
    }

    private void checkIfUsedEmail(@RequestBody RequestDTO requestDTO) {
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) && requestDTO.getPersonalInfo().getEmail() != null) {
            RequestCriteria requestCriteria = new RequestCriteria();
            requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.SAVED));
            requestCriteria.setEmail((StringFilter) new StringFilter().setEquals(requestDTO.getPersonalInfo().getEmail()));
            requestCriteria.setId((LongFilter) new LongFilter().setNotEquals(requestDTO.getId()));
            List<RequestDTO> requestDTOSavedList = requestQueryService.findByCriteria(requestCriteria);
            if (!requestDTOSavedList.isEmpty()) {
                throw new BadRequestAlertException("Email already used in an other saved request", "Request", "usedEmailSaved");
            } else {
                requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(null));
                requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.FINISHED, RequestStatus.REFUSED)));
                List<RequestDTO> requestDTOList = requestQueryService.findByCriteria(requestCriteria);
                if (!requestDTOList.isEmpty()) {
                    throw new BadRequestAlertException("Email already used in an other active request", "Request", "usedEmail");
                }
            }
        }
    }

    private void checkIfUsedCin(RequestDTO requestDTO) {
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) && requestDTO.getPersonalInfo().getCin() != null) {
            RequestCriteria requestCriteria = new RequestCriteria();
            requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.SAVED));
            requestCriteria.setCin((StringFilter) new StringFilter().setEquals(requestDTO.getPersonalInfo().getCin()));
            requestCriteria.setId((LongFilter) new LongFilter().setNotEquals(requestDTO.getId()));
            List<RequestDTO> requestDTOSavedList = requestQueryService.findByCriteria(requestCriteria);
            if (!requestDTOSavedList.isEmpty()) {
                throw new BadRequestAlertException("CIN already used in an other saved request", "Request", "usedCINSaved");
            } else {
                requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(null));
                requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.FINISHED, RequestStatus.REFUSED)));
                List<RequestDTO> requestDTOList = requestQueryService.findByCriteria(requestCriteria);
                if (!requestDTOList.isEmpty()) {
                    throw new BadRequestAlertException("CIN already used in an other active request", "Request", "usedCIN");
                }
            }
        }
    }

    /**
     * {@code GET  /requests} : get all the requests.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requests in body.
     */
    @GetMapping("/requests")
    public ResponseEntity<List<RequestDTO>> getAllRequests(RequestCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Requests by criteria: {}", criteria);
        Page<RequestDTO> page = requestQueryService.findByCriteria(updateCriteria(criteria != null ? criteria : new RequestCriteria(), SecurityUtils.getCurrentUserLogin().orElse(null)), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /requests/count} : count all the requests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/requests/count")
    public ResponseEntity<Long> countRequests(RequestCriteria criteria) {
        log.debug("REST request to count Requests by criteria: {}", criteria);
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            return ResponseEntity.ok().body(requestQueryService.countByCriteria(updateCriteria(criteria, SecurityUtils.getCurrentUserLogin().get())));
        } else {
            return ResponseEntity.ok().body(0L);
        }
    }

    /**
     * {@code GET  /requests/:id} : get the "id" request.
     *
     * @param id the id of the requestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/requests/{id}")
    public ResponseEntity<RequestDTO> getRequest(@PathVariable Long id) {
        log.debug("REST request to get Request : {}", id);
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setId((LongFilter) new LongFilter().setEquals(id));
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            List<RequestDTO> requestDTOS = requestQueryService.findByCriteria(updateCriteria(requestCriteria, SecurityUtils.getCurrentUserLogin().get()));
            if (!requestDTOS.isEmpty()) {
                return ResponseUtil.wrapOrNotFound(Optional.of(requestDTOS.get(0)));
            } else {
                return ResponseUtil.wrapOrNotFound(Optional.empty());
            }
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    /**
     * {@code DELETE  /requests/:id} : delete the "id" request.
     *
     * @param id the id of the requestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        log.debug("REST request to delete Request : {}", id);
        requestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/requests/{id}/visio")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.STAFF + "\")")
    public ResponseEntity<Boolean> getVisioStatusByRequest(@PathVariable Long id) {
        return ResponseEntity.ok().body(requestService.hasVisio(id));
    }

    @PostMapping("/requests/{id}/documents")
    public ResponseEntity<RequestDTO> createDocumentRequest(@PathVariable Long id) {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setId((LongFilter) new LongFilter().setEquals(id));
        List<RequestDTO> requestDTOS = requestQueryService.findByCriteria(updateCriteria(requestCriteria, SecurityUtils.getCurrentUserLogin().orElse(null)));
        if (!requestDTOS.isEmpty()) {
            return ResponseUtil.wrapOrNotFound(requestService.createDocumentsForRequest(id));
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    @GetMapping("/requests/{id}/unpaid-cheque")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.STAFF + "\")")
    public ResponseEntity<ChequeImpayeBct> getChequeImpaye(@PathVariable Long id) {
        log.debug("REST request to get ChequeImpaye : {}", id);
        Optional<RequestDTO> requestDTO = requestService.findOne(id);
        if (requestDTO.isPresent()) {
            String cin = requestDTO.get().getPersonalInfo().getCin();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern("dd/MM/yyyy")
                .withZone(ZoneId.systemDefault());
            String birthday = dateTimeFormatter.format(requestDTO.get().getPersonalInfo().getBirthday());
            ChequeImpayeBct chequeImpaye = middleWareService.getChequeImpaye(cin, birthday);
            return ResponseEntity.ok().body(chequeImpaye);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/requests/{id}/active-class")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.STAFF + "\")")
    public ResponseEntity<ActifClasseBct> getActifClasse(@PathVariable Long id) {
        log.debug("REST request to get ActifClasse : {}", id);
        Optional<RequestDTO> requestDTO = requestService.findOne(id);
        if (requestDTO.isPresent()) {
            String cin = requestDTO.get().getPersonalInfo().getCin();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern("dd/MM/yyyy")
                .withZone(ZoneId.systemDefault());
            String birthday = dateTimeFormatter.format(requestDTO.get().getPersonalInfo().getBirthday());
            List<ActifClasseBct> actifClasseList = middleWareService.getActifClasse(cin, birthday);
            return ResponseEntity.ok().body(actifClasseList != null && !actifClasseList.isEmpty() ? actifClasseList.get(actifClasseList.size() - 1) : null);
        }
        return ResponseEntity.notFound().build();
    }

    private RequestCriteria updateCriteria(RequestCriteria requestCriteria, String userAccountLogin) {
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) && userAccountLogin != null) {
            requestCriteria.setUserAccountLogin((StringFilter) new StringFilter().setEquals(userAccountLogin));
            requestCriteria.setRemoteUserIp((StringFilter) new StringFilter().setSpecified(false));
        }
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.STAFF)) {
            Optional<ActiveStaff> activeStaff = SecurityUtils.getStaffDetails();
            if (activeStaff.isPresent() && SecurityUtils.getStaffType().isPresent()) {
                    switch (SecurityUtils.getStaffType().get()) {
                        case MARKET_UNIT:
                            requestCriteria.setRemoteUserIp((StringFilter) new StringFilter().setSpecified(false));
                            RequestCriteria.RequestStatusFilter requestStatusFilterForMarket = requestCriteria.getRequestStatus() != null ? requestCriteria.getRequestStatus().copy() : new RequestCriteria.RequestStatusFilter();
                            requestStatusFilterForMarket.setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.SAVED));
                            requestCriteria.setRequestStatus(requestStatusFilterForMarket);
                            break;
                        case AGENCY_UNIT:
                            requestCriteria.setRemoteUserIp((StringFilter) new StringFilter().setSpecified(false));
                            requestCriteria.setAgencyCode(new StringFilter().setContains(activeStaff.get().getIdBu().toString()));
                            RequestCriteria.RequestStatusFilter requestStatusFilterForAgency = requestCriteria.getRequestStatus() != null ? requestCriteria.getRequestStatus().copy() : new RequestCriteria.RequestStatusFilter();
                            requestStatusFilterForAgency.setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.SAVED));
                            requestCriteria.setRequestStatus(requestStatusFilterForAgency);
                            break;
                        case ADVISOR_UNIT:
                            requestCriteria.setRemoteUserIp((StringFilter) new StringFilter().setSpecified(false));
                            RequestCriteria.RequestStatusFilter requestStatusFilterForAdvisor = requestCriteria.getRequestStatus() != null ? requestCriteria.getRequestStatus().copy() : new RequestCriteria.RequestStatusFilter();
                            requestStatusFilterForAdvisor.setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.SAVED));
                            requestCriteria.setRequestStatus(requestStatusFilterForAdvisor);
                            StringFilter matFilterForAdvisor = new StringFilter();
                            matFilterForAdvisor.setIn(Arrays.asList(activeStaff.get().getMatricule().toString(), "N/A"));
                            requestCriteria.setMatricule(matFilterForAdvisor);
                            break;
                        case CONFORMITY_UNIT:
                        case REPOSITORY_UNIT:
                            requestCriteria.setRemoteUserIp((StringFilter) new StringFilter().setSpecified(false));
                            RequestCriteria.RequestStatusFilter requestStatusFilterForDerogation = requestCriteria.getRequestStatus() != null ? requestCriteria.getRequestStatus().copy() : new RequestCriteria.RequestStatusFilter();
                            requestStatusFilterForDerogation.setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.SAVED));
                            requestCriteria.setRequestStatus(requestStatusFilterForDerogation);
                            StringFilter matFilterForDerogation = new StringFilter();
                            matFilterForDerogation.setIn(Arrays.asList(activeStaff.get().getMatricule().toString(), "N/A"));
                            requestCriteria.setMatDerogation(matFilterForDerogation);
                            RequestCriteria.RequestAffectationFilter requestAffectationFilter = new RequestCriteria.RequestAffectationFilter();
                            requestAffectationFilter.setEquals(RequestAffectation.valueOf(SecurityUtils.getStaffType().get().name()));
                            requestCriteria.setRequestAffectation(requestAffectationFilter);
                            break;
                    }

            }else {
                requestCriteria.setId((LongFilter) new LongFilter().setSpecified(false));
            }
        }
        return requestCriteria;
    }

    @GetMapping("/requests/{id}/info-client-and-compte")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.STAFF + "\")")
    public ResponseEntity<infoClient> getInfoClientAndComptebyCLI(@PathVariable Long id) {
        log.debug("REST request to get infoClient : {}", id);
        Optional<RequestDTO> requestDTO = requestService.findOne(id);
        if (requestDTO.isPresent()) {
            String numCLi = requestDTO.get().getAmplitudeRef();
            infoClient infoClient = middleWareService.getInfoClientAndComptebyCLI(numCLi);
            return ResponseEntity.ok().body(infoClient);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/requests/{id}/invalidate-documents")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.STAFF + "\")")
    public ResponseEntity<RequestDTO> invalidateDocumentsRequest(@PathVariable Long id) {
        Optional<RequestDTO> existingRequestDTO = requestService.findOne(id);
        if (existingRequestDTO.isPresent()) {
            existingRequestDTO.get().setRequestStatus(RequestStatus.TO_COMPLETE);
            setRequestTakenByAndMatricule(existingRequestDTO.get());
            appMailingService.sendMissedDocumentsRequestEmail(requestService.save(existingRequestDTO.get()), "Complément d'informations");
            return ResponseUtil.wrapOrNotFound(existingRequestDTO);
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    @PatchMapping("/requests/{id}/validate")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.STAFF + "\")")
    public ResponseEntity<RequestDTO> validateDocumentsRequest(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        Optional<RequestDTO> existingRequestDTO = requestService.findOne(id);
        if (existingRequestDTO.isPresent()) {
            if (existingRequestDTO.get().getHasCertificate() ||
                (existingRequestDTO.get().getVisioStatus() != null && existingRequestDTO.get().getVisioStatus())) {
                requestDTO.setRequestStatus(RequestStatus.ACCEPTED);
                requestDTO.setUserId(existingRequestDTO.get().getUserId());
                requestDTO.setVisioStatus(existingRequestDTO.get().getVisioStatus());
                requestDTO.setRequestState(existingRequestDTO.get().getRequestState());
                setRequestTakenByAndMatricule(existingRequestDTO.get());
                if (requestDTO.getAmplitudeRef() == null) {
                    List<ClientDelta> clientDeltaList = middleWareService.getInfoClientByIden(requestDTO.getPersonalInfo().getCin());
                    if (clientDeltaList == null || clientDeltaList.isEmpty()) {
                        CreateCustomerResponse response = this.customerClient.createCustomer(requestDTO);
                        if (response.getResponseDocument() != null &&
                            response.getResponseDocument().getCreateCustomerResponseFlow() != null
                            && response.getResponseDocument().getCreateCustomerResponseFlow().getCreateCustomerResponse() != null
                            && response.getResponseDocument().getCreateCustomerResponseFlow().getCreateCustomerResponse().getCustomerCode() != null) {
                            requestDTO.setAmplitudeRef(response.getResponseDocument().getCreateCustomerResponseFlow().getCreateCustomerResponse().getCustomerCode());
                            requestDTO = requestService.save(requestDTO);
                            appMailingService.sendAcceptedRequestEmail(requestDTO, "Demande acceptée");
                            return ResponseUtil.wrapOrNotFound(Optional.of(requestDTO));
                        } else {
                            throw new BadRequestAlertException("Erreur lors de la création de la fiche client amplitude", "Request", "createCustomerError");
                        }
                    } else {
                        requestDTO.setAmplitudeRef(clientDeltaList.get(0).getCli());
                        requestDTO = requestService.save(requestDTO);
                        appMailingService.sendAcceptedRequestEmail(requestDTO, "Demande acceptée");
                        return ResponseUtil.wrapOrNotFound(Optional.of(requestDTO));
                    }

                } else {
                    requestDTO = requestService.save(requestDTO);
                    appMailingService.sendAcceptedRequestEmail(requestDTO, "Demande acceptée");
                    return ResponseUtil.wrapOrNotFound(Optional.of(requestDTO));
                }


            } else {
                throw new BadRequestAlertException("Le client n'a pas encore fait son entretien visio", "Request", "noVisio");
            }
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    private void setRequestTakenByAndMatricule(RequestDTO existingRequestDTO) {
        if (existingRequestDTO.getTakenBy() == null && SecurityUtils.getCurrentUserLogin().isPresent()) {
            existingRequestDTO.setTakenBy(SecurityUtils.getCurrentUserLogin().get());
        }
        if ((existingRequestDTO.getMatricule() == null || existingRequestDTO.getMatricule().equals("N/A")) && SecurityUtils.getStaffDetails().isPresent()) {
            existingRequestDTO.setMatricule(SecurityUtils.getStaffDetails().get().getMatricule().toString());
        }
    }

    @PatchMapping("/requests/{id}/reject")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.STAFF + "\")")
    public ResponseEntity<RequestDTO> rejectDocumentsRequest(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        Optional<RequestDTO> existingRequestDTO = requestService.findOne(id);
        if (existingRequestDTO.isPresent()) {
            existingRequestDTO.get().setRejectCause(requestDTO.getRejectCause());
            existingRequestDTO.get().setRequestStatus(RequestStatus.REFUSED);
            existingRequestDTO.get().setRejectStatus(requestDTO.getRejectStatus());
            setRequestTakenByAndMatricule(existingRequestDTO.get());
            existingRequestDTO = Optional.of(requestService.save(existingRequestDTO.get()));
            if (existingRequestDTO.get().getRejectStatus().equals(RejectStatus.WRONG_EMAIL)) {
                appMailingService.sendRefusedRequestNonConformmail(existingRequestDTO.get(), "Rejet du demande");
            } else {
                appMailingService.sendRefusedRequestEmail(existingRequestDTO.get(), "Rejet du demande");
            }
            return ResponseUtil.wrapOrNotFound(existingRequestDTO);
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    @PatchMapping("/requests/{id}/save")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<RequestDTO> saveRequest(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        Optional<RequestDTO> existingRequestDTO = requestService.findOne(id);
        if (existingRequestDTO.isPresent()) {
            existingRequestDTO.get().setStep(requestDTO.getStep());
            existingRequestDTO.get().setRequestStatus(RequestStatus.SAVED);
            existingRequestDTO = Optional.of(requestService.save(existingRequestDTO.get()));
            appMailingService.sendRequestRegistrationMail(existingRequestDTO.get(), saveRequest);
            return ResponseUtil.wrapOrNotFound(existingRequestDTO);
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    @GetMapping("/requests/{id}/switch-smartphone/{section}/mail")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<RequestDTO> switchSmartPhone(@PathVariable Long id, @PathVariable int section) {
        Optional<RequestDTO> optionalRequestDTO = requestService.findOne(id);
        if (optionalRequestDTO.isPresent()) {
            appMailingService.switchMobileForJustificatifsByMail(optionalRequestDTO.get(), section);
            return ResponseUtil.wrapOrNotFound(optionalRequestDTO);
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    @GetMapping("/requests/{id}/switch-smartphone/{section}/qrcode")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public QrCodeUtils switchSmartPhoneByQrCode(@PathVariable Long id, @PathVariable int section) {
        Optional<RequestDTO> optionalRequestDTO = requestService.findOne(id);
        if (optionalRequestDTO.isPresent()) {
            return new QrCodeUtils(appMailingService.switchMobileForJustificatifsByQrCode(optionalRequestDTO.get(), section));
        } else {
            throw new BadRequestAlertException("No request with id " + id, "Request", "invalid id");
        }
    }

    @PostMapping("/requests/recap-email")
    public void sendRecapEmail(@RequestBody RequestDTO requestDTO) {
        appMailingService.sendRecapEmail(requestDTO, mailRecapSubject);
    }

    public void sendDerogationEmail( RequestDTO requestDTO, StaffType staffType) {
        StaffPermissionCriteria staffPermissionCriteria = new StaffPermissionCriteria();
        staffPermissionCriteria.setStaffType((StaffPermissionCriteria.StaffTypeFilter) new StaffPermissionCriteria.StaffTypeFilter().setEquals(staffType));
        List<StaffPermissionDTO> staffPermissionDTOS = this.staffPermissionQueryService.findByCriteria(staffPermissionCriteria);
        List<Integer> listBu = new ArrayList<>();
        List<Integer> listRole = new ArrayList<>();
        staffPermissionDTOS.forEach(staffPermissionDTO -> {
            listBu.add(staffPermissionDTO.getIdBu());
            listRole.add(staffPermissionDTO.getIdRole());
        });

        ActiveStaffCriteria activeStaffCriteria = new ActiveStaffCriteria();
        activeStaffCriteria.setIdBu((IntegerFilter) new IntegerFilter().setIn(listBu));
        activeStaffCriteria.setIdRole((IntegerFilter) new IntegerFilter().setIn(listRole));
        List<ActiveStaffDTO> activeStaffDTOS = this.activeStaffQueryService.findByCriteria(activeStaffCriteria);
        activeStaffDTOS.forEach(activeStaffDTO -> {
            appMailingService.sendDerogationEmail(requestDTO, activeStaffDTO.getEmail());
        });
    }
}
