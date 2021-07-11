package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Request;
import com.attijari.domain.Offer;
import com.attijari.domain.PersonalInfo;
import com.attijari.domain.Document;
import com.attijari.repository.RequestRepository;
import com.attijari.service.RequestService;
import com.attijari.service.dto.RequestDTO;
import com.attijari.service.mapper.RequestMapper;
import com.attijari.service.RequestQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.attijari.domain.enumeration.StateRequest;
import com.attijari.domain.enumeration.RequestStatus;
/**
 * Integration tests for the {@link RequestResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequestResourceIT {

    private static final LocalDate DEFAULT_VISIO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VISIO_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_VISIO_DATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_SENDING_MAIL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SENDING_MAIL_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_SENDING_MAIL_DATE = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_STATE = false;
    private static final Boolean UPDATED_STATE = true;

    private static final String DEFAULT_STEP = "AAAAAAAAAA";
    private static final String UPDATED_STEP = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_VERIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_VERIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_AMPLITUDE_REF = "AAAAAAAAAA";
    private static final String UPDATED_AMPLITUDE_REF = "BBBBBBBBBB";
    private static final String SMALLER_AMPLITUDE_REF = "AAAAAAAAAA";

    private static final StateRequest DEFAULT_REQUEST_STATE = StateRequest.VISIO;
    private static final StateRequest UPDATED_REQUEST_STATE = StateRequest.TREATMENT;

    private static final RequestStatus DEFAULT_REQUEST_STATUS = RequestStatus.PENDING;
    private static final RequestStatus UPDATED_REQUEST_STATUS = RequestStatus.ACCEPTED;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestQueryService requestQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestMockMvc;

    private Request request;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createEntity(EntityManager em) {
        Request request = new Request()
            .visioDate(DEFAULT_VISIO_DATE)
            .sendingMailDate(DEFAULT_SENDING_MAIL_DATE)
            .state(DEFAULT_STATE)
            .step(DEFAULT_STEP)
            .codeVerification(DEFAULT_CODE_VERIFICATION)
            .amplitudeRef(DEFAULT_AMPLITUDE_REF)
            .requestState(DEFAULT_REQUEST_STATE)
            .requestStatus(DEFAULT_REQUEST_STATUS);
        return request;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createUpdatedEntity(EntityManager em) {
        Request request = new Request()
            .visioDate(UPDATED_VISIO_DATE)
            .sendingMailDate(UPDATED_SENDING_MAIL_DATE)
            .state(UPDATED_STATE)
            .step(UPDATED_STEP)
            .codeVerification(UPDATED_CODE_VERIFICATION)
            .amplitudeRef(UPDATED_AMPLITUDE_REF)
            .requestState(UPDATED_REQUEST_STATE)
            .requestStatus(UPDATED_REQUEST_STATUS);
        return request;
    }

    @BeforeEach
    public void initTest() {
        request = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequest() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();
        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);
        restRequestMockMvc.perform(post("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isCreated());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate + 1);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getVisioDate()).isEqualTo(DEFAULT_VISIO_DATE);
        assertThat(testRequest.getSendingMailDate()).isEqualTo(DEFAULT_SENDING_MAIL_DATE);
        assertThat(testRequest.isState()).isEqualTo(DEFAULT_STATE);
        assertThat(testRequest.getStep()).isEqualTo(DEFAULT_STEP);
        assertThat(testRequest.getCodeVerification()).isEqualTo(DEFAULT_CODE_VERIFICATION);
        assertThat(testRequest.getAmplitudeRef()).isEqualTo(DEFAULT_AMPLITUDE_REF);
        assertThat(testRequest.getRequestState()).isEqualTo(DEFAULT_REQUEST_STATE);
        assertThat(testRequest.getRequestStatus()).isEqualTo(DEFAULT_REQUEST_STATUS);
    }

    @Test
    @Transactional
    public void createRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // Create the Request with an existing ID
        request.setId(1L);
        RequestDTO requestDTO = requestMapper.toDto(request);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestMockMvc.perform(post("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRequests() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList
        restRequestMockMvc.perform(get("/api/requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getId().intValue())))
            .andExpect(jsonPath("$.[*].visioDate").value(hasItem(DEFAULT_VISIO_DATE.toString())))
            .andExpect(jsonPath("$.[*].sendingMailDate").value(hasItem(DEFAULT_SENDING_MAIL_DATE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.booleanValue())))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].codeVerification").value(hasItem(DEFAULT_CODE_VERIFICATION)))
            .andExpect(jsonPath("$.[*].amplitudeRef").value(hasItem(DEFAULT_AMPLITUDE_REF)))
            .andExpect(jsonPath("$.[*].requestState").value(hasItem(DEFAULT_REQUEST_STATE.toString())))
            .andExpect(jsonPath("$.[*].requestStatus").value(hasItem(DEFAULT_REQUEST_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", request.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(request.getId().intValue()))
            .andExpect(jsonPath("$.visioDate").value(DEFAULT_VISIO_DATE.toString()))
            .andExpect(jsonPath("$.sendingMailDate").value(DEFAULT_SENDING_MAIL_DATE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.booleanValue()))
            .andExpect(jsonPath("$.step").value(DEFAULT_STEP))
            .andExpect(jsonPath("$.codeVerification").value(DEFAULT_CODE_VERIFICATION))
            .andExpect(jsonPath("$.amplitudeRef").value(DEFAULT_AMPLITUDE_REF))
            .andExpect(jsonPath("$.requestState").value(DEFAULT_REQUEST_STATE.toString()))
            .andExpect(jsonPath("$.requestStatus").value(DEFAULT_REQUEST_STATUS.toString()));
    }


    @Test
    @Transactional
    public void getRequestsByIdFiltering() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        Long id = request.getId();

        defaultRequestShouldBeFound("id.equals=" + id);
        defaultRequestShouldNotBeFound("id.notEquals=" + id);

        defaultRequestShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRequestShouldNotBeFound("id.greaterThan=" + id);

        defaultRequestShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRequestShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate equals to DEFAULT_VISIO_DATE
        defaultRequestShouldBeFound("visioDate.equals=" + DEFAULT_VISIO_DATE);

        // Get all the requestList where visioDate equals to UPDATED_VISIO_DATE
        defaultRequestShouldNotBeFound("visioDate.equals=" + UPDATED_VISIO_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate not equals to DEFAULT_VISIO_DATE
        defaultRequestShouldNotBeFound("visioDate.notEquals=" + DEFAULT_VISIO_DATE);

        // Get all the requestList where visioDate not equals to UPDATED_VISIO_DATE
        defaultRequestShouldBeFound("visioDate.notEquals=" + UPDATED_VISIO_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate in DEFAULT_VISIO_DATE or UPDATED_VISIO_DATE
        defaultRequestShouldBeFound("visioDate.in=" + DEFAULT_VISIO_DATE + "," + UPDATED_VISIO_DATE);

        // Get all the requestList where visioDate equals to UPDATED_VISIO_DATE
        defaultRequestShouldNotBeFound("visioDate.in=" + UPDATED_VISIO_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate is not null
        defaultRequestShouldBeFound("visioDate.specified=true");

        // Get all the requestList where visioDate is null
        defaultRequestShouldNotBeFound("visioDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate is greater than or equal to DEFAULT_VISIO_DATE
        defaultRequestShouldBeFound("visioDate.greaterThanOrEqual=" + DEFAULT_VISIO_DATE);

        // Get all the requestList where visioDate is greater than or equal to UPDATED_VISIO_DATE
        defaultRequestShouldNotBeFound("visioDate.greaterThanOrEqual=" + UPDATED_VISIO_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate is less than or equal to DEFAULT_VISIO_DATE
        defaultRequestShouldBeFound("visioDate.lessThanOrEqual=" + DEFAULT_VISIO_DATE);

        // Get all the requestList where visioDate is less than or equal to SMALLER_VISIO_DATE
        defaultRequestShouldNotBeFound("visioDate.lessThanOrEqual=" + SMALLER_VISIO_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsLessThanSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate is less than DEFAULT_VISIO_DATE
        defaultRequestShouldNotBeFound("visioDate.lessThan=" + DEFAULT_VISIO_DATE);

        // Get all the requestList where visioDate is less than UPDATED_VISIO_DATE
        defaultRequestShouldBeFound("visioDate.lessThan=" + UPDATED_VISIO_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByVisioDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where visioDate is greater than DEFAULT_VISIO_DATE
        defaultRequestShouldNotBeFound("visioDate.greaterThan=" + DEFAULT_VISIO_DATE);

        // Get all the requestList where visioDate is greater than SMALLER_VISIO_DATE
        defaultRequestShouldBeFound("visioDate.greaterThan=" + SMALLER_VISIO_DATE);
    }


    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate equals to DEFAULT_SENDING_MAIL_DATE
        defaultRequestShouldBeFound("sendingMailDate.equals=" + DEFAULT_SENDING_MAIL_DATE);

        // Get all the requestList where sendingMailDate equals to UPDATED_SENDING_MAIL_DATE
        defaultRequestShouldNotBeFound("sendingMailDate.equals=" + UPDATED_SENDING_MAIL_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate not equals to DEFAULT_SENDING_MAIL_DATE
        defaultRequestShouldNotBeFound("sendingMailDate.notEquals=" + DEFAULT_SENDING_MAIL_DATE);

        // Get all the requestList where sendingMailDate not equals to UPDATED_SENDING_MAIL_DATE
        defaultRequestShouldBeFound("sendingMailDate.notEquals=" + UPDATED_SENDING_MAIL_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate in DEFAULT_SENDING_MAIL_DATE or UPDATED_SENDING_MAIL_DATE
        defaultRequestShouldBeFound("sendingMailDate.in=" + DEFAULT_SENDING_MAIL_DATE + "," + UPDATED_SENDING_MAIL_DATE);

        // Get all the requestList where sendingMailDate equals to UPDATED_SENDING_MAIL_DATE
        defaultRequestShouldNotBeFound("sendingMailDate.in=" + UPDATED_SENDING_MAIL_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate is not null
        defaultRequestShouldBeFound("sendingMailDate.specified=true");

        // Get all the requestList where sendingMailDate is null
        defaultRequestShouldNotBeFound("sendingMailDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate is greater than or equal to DEFAULT_SENDING_MAIL_DATE
        defaultRequestShouldBeFound("sendingMailDate.greaterThanOrEqual=" + DEFAULT_SENDING_MAIL_DATE);

        // Get all the requestList where sendingMailDate is greater than or equal to UPDATED_SENDING_MAIL_DATE
        defaultRequestShouldNotBeFound("sendingMailDate.greaterThanOrEqual=" + UPDATED_SENDING_MAIL_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate is less than or equal to DEFAULT_SENDING_MAIL_DATE
        defaultRequestShouldBeFound("sendingMailDate.lessThanOrEqual=" + DEFAULT_SENDING_MAIL_DATE);

        // Get all the requestList where sendingMailDate is less than or equal to SMALLER_SENDING_MAIL_DATE
        defaultRequestShouldNotBeFound("sendingMailDate.lessThanOrEqual=" + SMALLER_SENDING_MAIL_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsLessThanSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate is less than DEFAULT_SENDING_MAIL_DATE
        defaultRequestShouldNotBeFound("sendingMailDate.lessThan=" + DEFAULT_SENDING_MAIL_DATE);

        // Get all the requestList where sendingMailDate is less than UPDATED_SENDING_MAIL_DATE
        defaultRequestShouldBeFound("sendingMailDate.lessThan=" + UPDATED_SENDING_MAIL_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestsBySendingMailDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where sendingMailDate is greater than DEFAULT_SENDING_MAIL_DATE
        defaultRequestShouldNotBeFound("sendingMailDate.greaterThan=" + DEFAULT_SENDING_MAIL_DATE);

        // Get all the requestList where sendingMailDate is greater than SMALLER_SENDING_MAIL_DATE
        defaultRequestShouldBeFound("sendingMailDate.greaterThan=" + SMALLER_SENDING_MAIL_DATE);
    }


    @Test
    @Transactional
    public void getAllRequestsByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where state equals to DEFAULT_STATE
        defaultRequestShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the requestList where state equals to UPDATED_STATE
        defaultRequestShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByStateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where state not equals to DEFAULT_STATE
        defaultRequestShouldNotBeFound("state.notEquals=" + DEFAULT_STATE);

        // Get all the requestList where state not equals to UPDATED_STATE
        defaultRequestShouldBeFound("state.notEquals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByStateIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where state in DEFAULT_STATE or UPDATED_STATE
        defaultRequestShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the requestList where state equals to UPDATED_STATE
        defaultRequestShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where state is not null
        defaultRequestShouldBeFound("state.specified=true");

        // Get all the requestList where state is null
        defaultRequestShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    public void getAllRequestsByStepIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where step equals to DEFAULT_STEP
        defaultRequestShouldBeFound("step.equals=" + DEFAULT_STEP);

        // Get all the requestList where step equals to UPDATED_STEP
        defaultRequestShouldNotBeFound("step.equals=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllRequestsByStepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where step not equals to DEFAULT_STEP
        defaultRequestShouldNotBeFound("step.notEquals=" + DEFAULT_STEP);

        // Get all the requestList where step not equals to UPDATED_STEP
        defaultRequestShouldBeFound("step.notEquals=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllRequestsByStepIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where step in DEFAULT_STEP or UPDATED_STEP
        defaultRequestShouldBeFound("step.in=" + DEFAULT_STEP + "," + UPDATED_STEP);

        // Get all the requestList where step equals to UPDATED_STEP
        defaultRequestShouldNotBeFound("step.in=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllRequestsByStepIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where step is not null
        defaultRequestShouldBeFound("step.specified=true");

        // Get all the requestList where step is null
        defaultRequestShouldNotBeFound("step.specified=false");
    }
                @Test
    @Transactional
    public void getAllRequestsByStepContainsSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where step contains DEFAULT_STEP
        defaultRequestShouldBeFound("step.contains=" + DEFAULT_STEP);

        // Get all the requestList where step contains UPDATED_STEP
        defaultRequestShouldNotBeFound("step.contains=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllRequestsByStepNotContainsSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where step does not contain DEFAULT_STEP
        defaultRequestShouldNotBeFound("step.doesNotContain=" + DEFAULT_STEP);

        // Get all the requestList where step does not contain UPDATED_STEP
        defaultRequestShouldBeFound("step.doesNotContain=" + UPDATED_STEP);
    }


    @Test
    @Transactional
    public void getAllRequestsByCodeVerificationIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where codeVerification equals to DEFAULT_CODE_VERIFICATION
        defaultRequestShouldBeFound("codeVerification.equals=" + DEFAULT_CODE_VERIFICATION);

        // Get all the requestList where codeVerification equals to UPDATED_CODE_VERIFICATION
        defaultRequestShouldNotBeFound("codeVerification.equals=" + UPDATED_CODE_VERIFICATION);
    }

    @Test
    @Transactional
    public void getAllRequestsByCodeVerificationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where codeVerification not equals to DEFAULT_CODE_VERIFICATION
        defaultRequestShouldNotBeFound("codeVerification.notEquals=" + DEFAULT_CODE_VERIFICATION);

        // Get all the requestList where codeVerification not equals to UPDATED_CODE_VERIFICATION
        defaultRequestShouldBeFound("codeVerification.notEquals=" + UPDATED_CODE_VERIFICATION);
    }

    @Test
    @Transactional
    public void getAllRequestsByCodeVerificationIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where codeVerification in DEFAULT_CODE_VERIFICATION or UPDATED_CODE_VERIFICATION
        defaultRequestShouldBeFound("codeVerification.in=" + DEFAULT_CODE_VERIFICATION + "," + UPDATED_CODE_VERIFICATION);

        // Get all the requestList where codeVerification equals to UPDATED_CODE_VERIFICATION
        defaultRequestShouldNotBeFound("codeVerification.in=" + UPDATED_CODE_VERIFICATION);
    }

    @Test
    @Transactional
    public void getAllRequestsByCodeVerificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where codeVerification is not null
        defaultRequestShouldBeFound("codeVerification.specified=true");

        // Get all the requestList where codeVerification is null
        defaultRequestShouldNotBeFound("codeVerification.specified=false");
    }
                @Test
    @Transactional
    public void getAllRequestsByCodeVerificationContainsSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where codeVerification contains DEFAULT_CODE_VERIFICATION
        defaultRequestShouldBeFound("codeVerification.contains=" + DEFAULT_CODE_VERIFICATION);

        // Get all the requestList where codeVerification contains UPDATED_CODE_VERIFICATION
        defaultRequestShouldNotBeFound("codeVerification.contains=" + UPDATED_CODE_VERIFICATION);
    }

    @Test
    @Transactional
    public void getAllRequestsByCodeVerificationNotContainsSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where codeVerification does not contain DEFAULT_CODE_VERIFICATION
        defaultRequestShouldNotBeFound("codeVerification.doesNotContain=" + DEFAULT_CODE_VERIFICATION);

        // Get all the requestList where codeVerification does not contain UPDATED_CODE_VERIFICATION
        defaultRequestShouldBeFound("codeVerification.doesNotContain=" + UPDATED_CODE_VERIFICATION);
    }


    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef equals to DEFAULT_AMPLITUDE_REF
        defaultRequestShouldBeFound("amplitudeRef.equals=" + DEFAULT_AMPLITUDE_REF);

        // Get all the requestList where amplitudeRef equals to UPDATED_AMPLITUDE_REF
        defaultRequestShouldNotBeFound("amplitudeRef.equals=" + UPDATED_AMPLITUDE_REF);
    }

    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef not equals to DEFAULT_AMPLITUDE_REF
        defaultRequestShouldNotBeFound("amplitudeRef.notEquals=" + DEFAULT_AMPLITUDE_REF);

        // Get all the requestList where amplitudeRef not equals to UPDATED_AMPLITUDE_REF
        defaultRequestShouldBeFound("amplitudeRef.notEquals=" + UPDATED_AMPLITUDE_REF);
    }

    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef in DEFAULT_AMPLITUDE_REF or UPDATED_AMPLITUDE_REF
        defaultRequestShouldBeFound("amplitudeRef.in=" + DEFAULT_AMPLITUDE_REF + "," + UPDATED_AMPLITUDE_REF);

        // Get all the requestList where amplitudeRef equals to UPDATED_AMPLITUDE_REF
        defaultRequestShouldNotBeFound("amplitudeRef.in=" + UPDATED_AMPLITUDE_REF);
    }

    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef is not null
        defaultRequestShouldBeFound("amplitudeRef.specified=true");

        // Get all the requestList where amplitudeRef is null
        defaultRequestShouldNotBeFound("amplitudeRef.specified=false");
    }

    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef is greater than or equal to DEFAULT_AMPLITUDE_REF
        defaultRequestShouldBeFound("amplitudeRef.greaterThanOrEqual=" + DEFAULT_AMPLITUDE_REF);

        // Get all the requestList where amplitudeRef is greater than or equal to UPDATED_AMPLITUDE_REF
        defaultRequestShouldNotBeFound("amplitudeRef.greaterThanOrEqual=" + UPDATED_AMPLITUDE_REF);
    }

    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef is less than or equal to DEFAULT_AMPLITUDE_REF
        defaultRequestShouldBeFound("amplitudeRef.lessThanOrEqual=" + DEFAULT_AMPLITUDE_REF);

        // Get all the requestList where amplitudeRef is less than or equal to SMALLER_AMPLITUDE_REF
        defaultRequestShouldNotBeFound("amplitudeRef.lessThanOrEqual=" + SMALLER_AMPLITUDE_REF);
    }

    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsLessThanSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef is less than DEFAULT_AMPLITUDE_REF
        defaultRequestShouldNotBeFound("amplitudeRef.lessThan=" + DEFAULT_AMPLITUDE_REF);

        // Get all the requestList where amplitudeRef is less than UPDATED_AMPLITUDE_REF
        defaultRequestShouldBeFound("amplitudeRef.lessThan=" + UPDATED_AMPLITUDE_REF);
    }

    @Test
    @Transactional
    public void getAllRequestsByAmplitudeRefIsGreaterThanSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where amplitudeRef is greater than DEFAULT_AMPLITUDE_REF
        defaultRequestShouldNotBeFound("amplitudeRef.greaterThan=" + DEFAULT_AMPLITUDE_REF);

        // Get all the requestList where amplitudeRef is greater than SMALLER_AMPLITUDE_REF
        defaultRequestShouldBeFound("amplitudeRef.greaterThan=" + SMALLER_AMPLITUDE_REF);
    }


    @Test
    @Transactional
    public void getAllRequestsByRequestStateIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestState equals to DEFAULT_REQUEST_STATE
        defaultRequestShouldBeFound("requestState.equals=" + DEFAULT_REQUEST_STATE);

        // Get all the requestList where requestState equals to UPDATED_REQUEST_STATE
        defaultRequestShouldNotBeFound("requestState.equals=" + UPDATED_REQUEST_STATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByRequestStateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestState not equals to DEFAULT_REQUEST_STATE
        defaultRequestShouldNotBeFound("requestState.notEquals=" + DEFAULT_REQUEST_STATE);

        // Get all the requestList where requestState not equals to UPDATED_REQUEST_STATE
        defaultRequestShouldBeFound("requestState.notEquals=" + UPDATED_REQUEST_STATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByRequestStateIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestState in DEFAULT_REQUEST_STATE or UPDATED_REQUEST_STATE
        defaultRequestShouldBeFound("requestState.in=" + DEFAULT_REQUEST_STATE + "," + UPDATED_REQUEST_STATE);

        // Get all the requestList where requestState equals to UPDATED_REQUEST_STATE
        defaultRequestShouldNotBeFound("requestState.in=" + UPDATED_REQUEST_STATE);
    }

    @Test
    @Transactional
    public void getAllRequestsByRequestStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestState is not null
        defaultRequestShouldBeFound("requestState.specified=true");

        // Get all the requestList where requestState is null
        defaultRequestShouldNotBeFound("requestState.specified=false");
    }

    @Test
    @Transactional
    public void getAllRequestsByRequestStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestStatus equals to DEFAULT_REQUEST_STATUS
        defaultRequestShouldBeFound("requestStatus.equals=" + DEFAULT_REQUEST_STATUS);

        // Get all the requestList where requestStatus equals to UPDATED_REQUEST_STATUS
        defaultRequestShouldNotBeFound("requestStatus.equals=" + UPDATED_REQUEST_STATUS);
    }

    @Test
    @Transactional
    public void getAllRequestsByRequestStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestStatus not equals to DEFAULT_REQUEST_STATUS
        defaultRequestShouldNotBeFound("requestStatus.notEquals=" + DEFAULT_REQUEST_STATUS);

        // Get all the requestList where requestStatus not equals to UPDATED_REQUEST_STATUS
        defaultRequestShouldBeFound("requestStatus.notEquals=" + UPDATED_REQUEST_STATUS);
    }

    @Test
    @Transactional
    public void getAllRequestsByRequestStatusIsInShouldWork() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestStatus in DEFAULT_REQUEST_STATUS or UPDATED_REQUEST_STATUS
        defaultRequestShouldBeFound("requestStatus.in=" + DEFAULT_REQUEST_STATUS + "," + UPDATED_REQUEST_STATUS);

        // Get all the requestList where requestStatus equals to UPDATED_REQUEST_STATUS
        defaultRequestShouldNotBeFound("requestStatus.in=" + UPDATED_REQUEST_STATUS);
    }

    @Test
    @Transactional
    public void getAllRequestsByRequestStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList where requestStatus is not null
        defaultRequestShouldBeFound("requestStatus.specified=true");

        // Get all the requestList where requestStatus is null
        defaultRequestShouldNotBeFound("requestStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllRequestsByOfferIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);
        Offer offer = OfferResourceIT.createEntity(em);
        em.persist(offer);
        em.flush();
        request.setOffer(offer);
        requestRepository.saveAndFlush(request);
        Long offerId = offer.getId();

        // Get all the requestList where offer equals to offerId
        defaultRequestShouldBeFound("offerId.equals=" + offerId);

        // Get all the requestList where offer equals to offerId + 1
        defaultRequestShouldNotBeFound("offerId.equals=" + (offerId + 1));
    }


    @Test
    @Transactional
    public void getAllRequestsByPersonalInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);
        PersonalInfo personalInfo = PersonalInfoResourceIT.createEntity(em);
        em.persist(personalInfo);
        em.flush();
        request.setPersonalInfo(personalInfo);
        requestRepository.saveAndFlush(request);
        Long personalInfoId = personalInfo.getId();

        // Get all the requestList where personalInfo equals to personalInfoId
        defaultRequestShouldBeFound("personalInfoId.equals=" + personalInfoId);

        // Get all the requestList where personalInfo equals to personalInfoId + 1
        defaultRequestShouldNotBeFound("personalInfoId.equals=" + (personalInfoId + 1));
    }


    @Test
    @Transactional
    public void getAllRequestsByDocumentIsEqualToSomething() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);
        Document document = DocumentResourceIT.createEntity(em);
        em.persist(document);
        em.flush();
        request.setDocument(document);
        requestRepository.saveAndFlush(request);
        Long documentId = document.getId();

        // Get all the requestList where document equals to documentId
        defaultRequestShouldBeFound("documentId.equals=" + documentId);

        // Get all the requestList where document equals to documentId + 1
        defaultRequestShouldNotBeFound("documentId.equals=" + (documentId + 1));
    }


    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRequestShouldBeFound(String filter) throws Exception {
        restRequestMockMvc.perform(get("/api/requests?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getId().intValue())))
            .andExpect(jsonPath("$.[*].visioDate").value(hasItem(DEFAULT_VISIO_DATE.toString())))
            .andExpect(jsonPath("$.[*].sendingMailDate").value(hasItem(DEFAULT_SENDING_MAIL_DATE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.booleanValue())))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].codeVerification").value(hasItem(DEFAULT_CODE_VERIFICATION)))
            .andExpect(jsonPath("$.[*].amplitudeRef").value(hasItem(DEFAULT_AMPLITUDE_REF)))
            .andExpect(jsonPath("$.[*].requestState").value(hasItem(DEFAULT_REQUEST_STATE.toString())))
            .andExpect(jsonPath("$.[*].requestStatus").value(hasItem(DEFAULT_REQUEST_STATUS.toString())));

        // Check, that the count call also returns 1
        restRequestMockMvc.perform(get("/api/requests/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRequestShouldNotBeFound(String filter) throws Exception {
        restRequestMockMvc.perform(get("/api/requests?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRequestMockMvc.perform(get("/api/requests/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRequest() throws Exception {
        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request
        Request updatedRequest = requestRepository.findById(request.getId()).get();
        // Disconnect from session so that the updates on updatedRequest are not directly saved in db
        em.detach(updatedRequest);
        updatedRequest
            .visioDate(UPDATED_VISIO_DATE)
            .sendingMailDate(UPDATED_SENDING_MAIL_DATE)
            .state(UPDATED_STATE)
            .step(UPDATED_STEP)
            .codeVerification(UPDATED_CODE_VERIFICATION)
            .amplitudeRef(UPDATED_AMPLITUDE_REF)
            .requestState(UPDATED_REQUEST_STATE)
            .requestStatus(UPDATED_REQUEST_STATUS);
        RequestDTO requestDTO = requestMapper.toDto(updatedRequest);

        restRequestMockMvc.perform(put("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getVisioDate()).isEqualTo(UPDATED_VISIO_DATE);
        assertThat(testRequest.getSendingMailDate()).isEqualTo(UPDATED_SENDING_MAIL_DATE);
        assertThat(testRequest.isState()).isEqualTo(UPDATED_STATE);
        assertThat(testRequest.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testRequest.getCodeVerification()).isEqualTo(UPDATED_CODE_VERIFICATION);
        assertThat(testRequest.getAmplitudeRef()).isEqualTo(UPDATED_AMPLITUDE_REF);
        assertThat(testRequest.getRequestState()).isEqualTo(UPDATED_REQUEST_STATE);
        assertThat(testRequest.getRequestStatus()).isEqualTo(UPDATED_REQUEST_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestMockMvc.perform(put("/api/requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeDelete = requestRepository.findAll().size();

        // Delete the request
        restRequestMockMvc.perform(delete("/api/requests/{id}", request.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
