package com.attijari.web.rest;

import com.attijari.domain.User;
import com.attijari.domain.enumeration.RequestStatus;
import com.attijari.service.*;
import com.attijari.service.dto.PersonalInfoDTO;
import com.attijari.service.dto.RequestCriteria;
import com.attijari.service.dto.RequestDTO;
import com.attijari.service.dto.UserDTO;
import com.attijari.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing anonymous resources.
 */

@RestController
@RequestMapping("/anonymous")
public class AnonymousResource {

    private final Logger log = LoggerFactory.getLogger(RequestResource.class);

    private static final String REQUEST_ENTITY_NAME = "request";

    private final UserService userService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestService requestService;
    private final RequestQueryService requestQueryService;
    private static final String mailVerificationSubject = "Activation de votre Compte";

    private final AppMailingService appMailingService;

    private final CustomerClient customerClient;

    private final RestTemplate soapRestTemplate;

    private SupplyMatrixQueryService supplyMatrixQueryService;

    public AnonymousResource(UserService userService, RequestService requestService, RequestQueryService requestQueryService, AppMailingService appMailingService, CustomerClient customerClient, RestTemplate soapRestTemplate, SupplyMatrixQueryService supplyMatrixQueryService) {
        this.userService = userService;
        this.requestService = requestService;
        this.requestQueryService = requestQueryService;
        this.appMailingService = appMailingService;
        this.customerClient = customerClient;
        this.soapRestTemplate = soapRestTemplate;
        this.supplyMatrixQueryService = supplyMatrixQueryService;
    }

    /**
     * {@code POST  /requests} : Create a new request.
     *
     * @param requestDTO the requestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestDTO, or with status {@code 400 (Bad Request)} if the request has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/requests")
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO requestDTO, HttpServletRequest httpServletRequest) throws URISyntaxException {
        log.debug("REST request to save Request : {}", requestDTO);
        if (requestDTO.getId() != null) {
            throw new BadRequestAlertException("A new request cannot already have an ID", REQUEST_ENTITY_NAME, "idexists");
        }
        String remoteUserIp = httpServletRequest.getHeader("X-Real-IP") != null ? httpServletRequest.getHeader("X-Real-IP") : httpServletRequest.getRemoteAddr();

        checkUsedEmailOrCin(requestDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(requestDTO.getPersonalInfo().getEmail());
        userDTO.setEmail(requestDTO.getPersonalInfo().getEmail());
        userDTO.setFirstName(requestDTO.getPersonalInfo().getFirstName());
        userDTO.setLastName(requestDTO.getPersonalInfo().getLastName());
        userDTO.setCivility(requestDTO.getPersonalInfo().getCivility());
        userDTO.setLangKey("FR");
        String verificationCode = RandomStringUtils.random(6, false, true);

        User user = userService.registerSimpleUser(userDTO, verificationCode);
        requestDTO.setRemoteUserIp(null);
        requestDTO.setRequestStatus(RequestStatus.IN_CREATION);
        requestDTO.setUserId(user.getId());
        requestDTO.setDocument(null);
        requestDTO.setHasCertificate(false);

        RequestDTO result = requestService.save(requestDTO);
        PersonalInfoDTO personalInfoDTO = result.getPersonalInfo();
        if (personalInfoDTO != null && personalInfoDTO.getEmail() != null && Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.SAVED).contains(requestDTO.getRequestStatus())) {
            appMailingService.sendConfirmationEmail(result, mailVerificationSubject);
        }
        return ResponseEntity.created(new URI("/api/requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, REQUEST_ENTITY_NAME, result.getId().toString()))
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
    public ResponseEntity<RequestDTO> updateRequest(@RequestBody RequestDTO requestDTO, HttpServletRequest httpServletRequest) {
        log.debug("REST request to update Request : {}", requestDTO);
        if (requestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", REQUEST_ENTITY_NAME, "idnull");
        }
        checkUsedEmailOrCin(requestDTO);
        String remoteUserIp = httpServletRequest.getHeader("X-Real-IP") != null ? httpServletRequest.getHeader("X-Real-IP") : httpServletRequest.getRemoteAddr();
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setId((LongFilter) new LongFilter().setEquals(requestDTO.getId()));
        requestCriteria.setRemoteUserIp((StringFilter) new StringFilter().setEquals(remoteUserIp));
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.IN_CREATION));
        List<RequestDTO> requestDTOs = requestQueryService.findByCriteria(requestCriteria);
        if (!requestDTOs.isEmpty() && remoteUserIp.equals(requestDTOs.get(0).getRemoteUserIp())) {
            requestDTO.setRequestStatus(requestDTOs.get(0).getRequestStatus());
            requestDTO.setUserId(null);
            RequestDTO result = requestService.save(requestDTO);
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, REQUEST_ENTITY_NAME, requestDTO.getId().toString()))
                .body(result);
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    private void checkUsedEmailOrCin(@RequestBody RequestDTO requestDTO) {
        if (requestDTO.getPersonalInfo().getEmail() != null) {
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
        if (requestDTO.getPersonalInfo().getCin() != null) {
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
     * {@code GET  /requests/:id} : get the "id" request.
     *
     * @param id the id of the requestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/requests/{id}")
    public ResponseEntity<RequestDTO> getRequest(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        log.debug("REST request to get Request : {}", id);
        String remoteUserIp = httpServletRequest.getHeader("X-Real-IP") != null ? httpServletRequest.getHeader("X-Real-IP") : httpServletRequest.getRemoteAddr();
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setId((LongFilter) new LongFilter().setEquals(id));
        requestCriteria.setRemoteUserIp((StringFilter) new StringFilter().setEquals(remoteUserIp));
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.IN_CREATION));
        List<RequestDTO> requestDTOs = requestQueryService.findByCriteria(requestCriteria);
        if (!requestDTOs.isEmpty()) {
            return ResponseUtil.wrapOrNotFound(Optional.of(requestDTOs.get(0)));
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    @GetMapping("/requests/{id}/follow")
    public ResponseEntity<RequestDTO> continueSavedRequest(@PathVariable Long id) {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setId((LongFilter) new LongFilter().setEquals(id));
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setEquals(RequestStatus.SAVED));
        List<RequestDTO> existingRequestDTOs = requestQueryService.findByCriteria(requestCriteria);
        if (!existingRequestDTOs.isEmpty()) {
            appMailingService.followRequest(existingRequestDTOs.get(0), "code d’accès");
            return ResponseUtil.wrapOrNotFound(Optional.of(existingRequestDTOs.get(0)));
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    @GetMapping("/requests/{id}/follow-status")
    public ResponseEntity followRequest(@PathVariable Long id) {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setId((LongFilter) new LongFilter().setEquals(id));
        requestCriteria.setRequestStatus((RequestCriteria.RequestStatusFilter) new RequestCriteria.RequestStatusFilter().setNotIn(Arrays.asList(RequestStatus.IN_CREATION, RequestStatus.SAVED)));
        List<RequestDTO> existingRequestDTOs = requestQueryService.findByCriteria(requestCriteria);
        if (!existingRequestDTOs.isEmpty()) {
            appMailingService.followRequest(existingRequestDTOs.get(0), "code d’accès");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }
}
