package com.attijari.web.rest;

import com.attijari.service.dto.SubscriberStatusDTO;
import com.attijari.TsfBackendApp;
import com.attijari.domain.SubscriberStatus;
import com.attijari.repository.SubscriberStatusRepository;
import com.attijari.service.SubscriberStatusService;
import com.attijari.service.mapper.SubscriberStatusMapper;
import com.attijari.service.SubscriberStatusQueryService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.attijari.domain.enumeration.appelVisioStatus;
import com.attijari.domain.enumeration.withCertifStatus;
import com.attijari.domain.enumeration.withSignatureStatus;
/**
 * Integration tests for the {@link SubscriberStatusResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SubscriberStatusResourceIT {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_CIN = "AAAAAAAAAA";
    private static final String UPDATED_NUM_CIN = "BBBBBBBBBB";

    private static final appelVisioStatus DEFAULT_WITH_APPEL_VISIO = appelVisioStatus.O;
    private static final appelVisioStatus UPDATED_WITH_APPEL_VISIO = appelVisioStatus.N;

    private static final withCertifStatus DEFAULT_WITH_CERTIF = withCertifStatus.O;
    private static final withCertifStatus UPDATED_WITH_CERTIF = withCertifStatus.N;

    private static final withSignatureStatus DEFAULT_WITH_SIGNATURE = withSignatureStatus.O;
    private static final withSignatureStatus UPDATED_WITH_SIGNATURE = withSignatureStatus.N;

    private static final String DEFAULT_DATE_APPEL_VISIO = "LocalDate.ofEpochDay(0L)";
    private static final String UPDATED_DATE_APPEL_VISIO = "LocalDate.now(ZoneId.systemDefault())";
    private static final String SMALLER_DATE_APPEL_VISIO = "LocalDate.ofEpochDay(-1L)";

    @Autowired
    private SubscriberStatusRepository subscriberStatusRepository;

    @Autowired
    private SubscriberStatusMapper subscriberStatusMapper;

    @Autowired
    private SubscriberStatusService subscriberStatusService;

    @Autowired
    private SubscriberStatusQueryService subscriberStatusQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubscriberStatusMockMvc;

    private SubscriberStatus subscriberStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriberStatus createEntity(EntityManager em) {
        SubscriberStatus subscriberStatus = new SubscriberStatus();
        return subscriberStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriberStatus createUpdatedEntity(EntityManager em) {
        SubscriberStatus subscriberStatus = new SubscriberStatus();
        return subscriberStatus;
    }

    @BeforeEach
    public void initTest() {
        subscriberStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubscriberStatus() throws Exception {
        int databaseSizeBeforeCreate = subscriberStatusRepository.findAll().size();
        // Create the SubscriberStatus
        SubscriberStatusDTO subscriberStatusDTO = subscriberStatusMapper.toDto(subscriberStatus);
        restSubscriberStatusMockMvc.perform(post("/api/subscriber-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriberStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the SubscriberStatus in the database
        List<SubscriberStatus> subscriberStatusList = subscriberStatusRepository.findAll();
        assertThat(subscriberStatusList).hasSize(databaseSizeBeforeCreate + 1);
        SubscriberStatus testSubscriberStatus = subscriberStatusList.get(subscriberStatusList.size() - 1);
        assertThat(testSubscriberStatus.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSubscriberStatus.getNumCin()).isEqualTo(DEFAULT_NUM_CIN);
        assertThat(testSubscriberStatus.getWithAppelVisio()).isEqualTo(DEFAULT_WITH_APPEL_VISIO);
        assertThat(testSubscriberStatus.getWithCertif()).isEqualTo(DEFAULT_WITH_CERTIF);
        assertThat(testSubscriberStatus.getWithSignature()).isEqualTo(DEFAULT_WITH_SIGNATURE);
        assertThat(testSubscriberStatus.getDateAppelVisio()).isEqualTo(DEFAULT_DATE_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void createSubscriberStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subscriberStatusRepository.findAll().size();

        // Create the SubscriberStatus with an existing ID
        subscriberStatus.setId(1L);
        SubscriberStatusDTO subscriberStatusDTO = subscriberStatusMapper.toDto(subscriberStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubscriberStatusMockMvc.perform(post("/api/subscriber-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriberStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriberStatus in the database
        List<SubscriberStatus> subscriberStatusList = subscriberStatusRepository.findAll();
        assertThat(subscriberStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSubscriberStatuses() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList
        restSubscriberStatusMockMvc.perform(get("/api/subscriber-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscriberStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].numCin").value(hasItem(DEFAULT_NUM_CIN)))
            .andExpect(jsonPath("$.[*].withAppelVisio").value(hasItem(DEFAULT_WITH_APPEL_VISIO.toString())))
            .andExpect(jsonPath("$.[*].withCertif").value(hasItem(DEFAULT_WITH_CERTIF.toString())))
            .andExpect(jsonPath("$.[*].withSignature").value(hasItem(DEFAULT_WITH_SIGNATURE.toString())))
            .andExpect(jsonPath("$.[*].dateAppelVisio").value(hasItem(DEFAULT_DATE_APPEL_VISIO.toString())));
    }

    @Test
    @Transactional
    public void getSubscriberStatus() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get the subscriberStatus
        restSubscriberStatusMockMvc.perform(get("/api/subscriber-statuses/{id}", subscriberStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subscriberStatus.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.numCin").value(DEFAULT_NUM_CIN))
            .andExpect(jsonPath("$.withAppelVisio").value(DEFAULT_WITH_APPEL_VISIO.toString()))
            .andExpect(jsonPath("$.withCertif").value(DEFAULT_WITH_CERTIF.toString()))
            .andExpect(jsonPath("$.withSignature").value(DEFAULT_WITH_SIGNATURE.toString()))
            .andExpect(jsonPath("$.dateAppelVisio").value(DEFAULT_DATE_APPEL_VISIO.toString()));
    }


    @Test
    @Transactional
    public void getSubscriberStatusesByIdFiltering() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        Long id = subscriberStatus.getId();

        defaultSubscriberStatusShouldBeFound("id.equals=" + id);
        defaultSubscriberStatusShouldNotBeFound("id.notEquals=" + id);

        defaultSubscriberStatusShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSubscriberStatusShouldNotBeFound("id.greaterThan=" + id);

        defaultSubscriberStatusShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSubscriberStatusShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllSubscriberStatusesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where email equals to DEFAULT_EMAIL
        defaultSubscriberStatusShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the subscriberStatusList where email equals to UPDATED_EMAIL
        defaultSubscriberStatusShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where email not equals to DEFAULT_EMAIL
        defaultSubscriberStatusShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the subscriberStatusList where email not equals to UPDATED_EMAIL
        defaultSubscriberStatusShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultSubscriberStatusShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the subscriberStatusList where email equals to UPDATED_EMAIL
        defaultSubscriberStatusShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where email is not null
        defaultSubscriberStatusShouldBeFound("email.specified=true");

        // Get all the subscriberStatusList where email is null
        defaultSubscriberStatusShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllSubscriberStatusesByEmailContainsSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where email contains DEFAULT_EMAIL
        defaultSubscriberStatusShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the subscriberStatusList where email contains UPDATED_EMAIL
        defaultSubscriberStatusShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where email does not contain DEFAULT_EMAIL
        defaultSubscriberStatusShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the subscriberStatusList where email does not contain UPDATED_EMAIL
        defaultSubscriberStatusShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllSubscriberStatusesByNumCinIsEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where numCin equals to DEFAULT_NUM_CIN
        defaultSubscriberStatusShouldBeFound("numCin.equals=" + DEFAULT_NUM_CIN);

        // Get all the subscriberStatusList where numCin equals to UPDATED_NUM_CIN
        defaultSubscriberStatusShouldNotBeFound("numCin.equals=" + UPDATED_NUM_CIN);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByNumCinIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where numCin not equals to DEFAULT_NUM_CIN
        defaultSubscriberStatusShouldNotBeFound("numCin.notEquals=" + DEFAULT_NUM_CIN);

        // Get all the subscriberStatusList where numCin not equals to UPDATED_NUM_CIN
        defaultSubscriberStatusShouldBeFound("numCin.notEquals=" + UPDATED_NUM_CIN);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByNumCinIsInShouldWork() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where numCin in DEFAULT_NUM_CIN or UPDATED_NUM_CIN
        defaultSubscriberStatusShouldBeFound("numCin.in=" + DEFAULT_NUM_CIN + "," + UPDATED_NUM_CIN);

        // Get all the subscriberStatusList where numCin equals to UPDATED_NUM_CIN
        defaultSubscriberStatusShouldNotBeFound("numCin.in=" + UPDATED_NUM_CIN);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByNumCinIsNullOrNotNull() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where numCin is not null
        defaultSubscriberStatusShouldBeFound("numCin.specified=true");

        // Get all the subscriberStatusList where numCin is null
        defaultSubscriberStatusShouldNotBeFound("numCin.specified=false");
    }
                @Test
    @Transactional
    public void getAllSubscriberStatusesByNumCinContainsSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where numCin contains DEFAULT_NUM_CIN
        defaultSubscriberStatusShouldBeFound("numCin.contains=" + DEFAULT_NUM_CIN);

        // Get all the subscriberStatusList where numCin contains UPDATED_NUM_CIN
        defaultSubscriberStatusShouldNotBeFound("numCin.contains=" + UPDATED_NUM_CIN);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByNumCinNotContainsSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where numCin does not contain DEFAULT_NUM_CIN
        defaultSubscriberStatusShouldNotBeFound("numCin.doesNotContain=" + DEFAULT_NUM_CIN);

        // Get all the subscriberStatusList where numCin does not contain UPDATED_NUM_CIN
        defaultSubscriberStatusShouldBeFound("numCin.doesNotContain=" + UPDATED_NUM_CIN);
    }


    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithAppelVisioIsEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withAppelVisio equals to DEFAULT_WITH_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("withAppelVisio.equals=" + DEFAULT_WITH_APPEL_VISIO);

        // Get all the subscriberStatusList where withAppelVisio equals to UPDATED_WITH_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("withAppelVisio.equals=" + UPDATED_WITH_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithAppelVisioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withAppelVisio not equals to DEFAULT_WITH_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("withAppelVisio.notEquals=" + DEFAULT_WITH_APPEL_VISIO);

        // Get all the subscriberStatusList where withAppelVisio not equals to UPDATED_WITH_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("withAppelVisio.notEquals=" + UPDATED_WITH_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithAppelVisioIsInShouldWork() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withAppelVisio in DEFAULT_WITH_APPEL_VISIO or UPDATED_WITH_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("withAppelVisio.in=" + DEFAULT_WITH_APPEL_VISIO + "," + UPDATED_WITH_APPEL_VISIO);

        // Get all the subscriberStatusList where withAppelVisio equals to UPDATED_WITH_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("withAppelVisio.in=" + UPDATED_WITH_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithAppelVisioIsNullOrNotNull() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withAppelVisio is not null
        defaultSubscriberStatusShouldBeFound("withAppelVisio.specified=true");

        // Get all the subscriberStatusList where withAppelVisio is null
        defaultSubscriberStatusShouldNotBeFound("withAppelVisio.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithCertifIsEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withCertif equals to DEFAULT_WITH_CERTIF
        defaultSubscriberStatusShouldBeFound("withCertif.equals=" + DEFAULT_WITH_CERTIF);

        // Get all the subscriberStatusList where withCertif equals to UPDATED_WITH_CERTIF
        defaultSubscriberStatusShouldNotBeFound("withCertif.equals=" + UPDATED_WITH_CERTIF);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithCertifIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withCertif not equals to DEFAULT_WITH_CERTIF
        defaultSubscriberStatusShouldNotBeFound("withCertif.notEquals=" + DEFAULT_WITH_CERTIF);

        // Get all the subscriberStatusList where withCertif not equals to UPDATED_WITH_CERTIF
        defaultSubscriberStatusShouldBeFound("withCertif.notEquals=" + UPDATED_WITH_CERTIF);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithCertifIsInShouldWork() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withCertif in DEFAULT_WITH_CERTIF or UPDATED_WITH_CERTIF
        defaultSubscriberStatusShouldBeFound("withCertif.in=" + DEFAULT_WITH_CERTIF + "," + UPDATED_WITH_CERTIF);

        // Get all the subscriberStatusList where withCertif equals to UPDATED_WITH_CERTIF
        defaultSubscriberStatusShouldNotBeFound("withCertif.in=" + UPDATED_WITH_CERTIF);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithCertifIsNullOrNotNull() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withCertif is not null
        defaultSubscriberStatusShouldBeFound("withCertif.specified=true");

        // Get all the subscriberStatusList where withCertif is null
        defaultSubscriberStatusShouldNotBeFound("withCertif.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithSignatureIsEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withSignature equals to DEFAULT_WITH_SIGNATURE
        defaultSubscriberStatusShouldBeFound("withSignature.equals=" + DEFAULT_WITH_SIGNATURE);

        // Get all the subscriberStatusList where withSignature equals to UPDATED_WITH_SIGNATURE
        defaultSubscriberStatusShouldNotBeFound("withSignature.equals=" + UPDATED_WITH_SIGNATURE);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithSignatureIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withSignature not equals to DEFAULT_WITH_SIGNATURE
        defaultSubscriberStatusShouldNotBeFound("withSignature.notEquals=" + DEFAULT_WITH_SIGNATURE);

        // Get all the subscriberStatusList where withSignature not equals to UPDATED_WITH_SIGNATURE
        defaultSubscriberStatusShouldBeFound("withSignature.notEquals=" + UPDATED_WITH_SIGNATURE);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithSignatureIsInShouldWork() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withSignature in DEFAULT_WITH_SIGNATURE or UPDATED_WITH_SIGNATURE
        defaultSubscriberStatusShouldBeFound("withSignature.in=" + DEFAULT_WITH_SIGNATURE + "," + UPDATED_WITH_SIGNATURE);

        // Get all the subscriberStatusList where withSignature equals to UPDATED_WITH_SIGNATURE
        defaultSubscriberStatusShouldNotBeFound("withSignature.in=" + UPDATED_WITH_SIGNATURE);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByWithSignatureIsNullOrNotNull() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where withSignature is not null
        defaultSubscriberStatusShouldBeFound("withSignature.specified=true");

        // Get all the subscriberStatusList where withSignature is null
        defaultSubscriberStatusShouldNotBeFound("withSignature.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio equals to DEFAULT_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.equals=" + DEFAULT_DATE_APPEL_VISIO);

        // Get all the subscriberStatusList where dateAppelVisio equals to UPDATED_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.equals=" + UPDATED_DATE_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio not equals to DEFAULT_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.notEquals=" + DEFAULT_DATE_APPEL_VISIO);

        // Get all the subscriberStatusList where dateAppelVisio not equals to UPDATED_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.notEquals=" + UPDATED_DATE_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsInShouldWork() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio in DEFAULT_DATE_APPEL_VISIO or UPDATED_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.in=" + DEFAULT_DATE_APPEL_VISIO + "," + UPDATED_DATE_APPEL_VISIO);

        // Get all the subscriberStatusList where dateAppelVisio equals to UPDATED_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.in=" + UPDATED_DATE_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsNullOrNotNull() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio is not null
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.specified=true");

        // Get all the subscriberStatusList where dateAppelVisio is null
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio is greater than or equal to DEFAULT_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.greaterThanOrEqual=" + DEFAULT_DATE_APPEL_VISIO);

        // Get all the subscriberStatusList where dateAppelVisio is greater than or equal to UPDATED_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.greaterThanOrEqual=" + UPDATED_DATE_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio is less than or equal to DEFAULT_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.lessThanOrEqual=" + DEFAULT_DATE_APPEL_VISIO);

        // Get all the subscriberStatusList where dateAppelVisio is less than or equal to SMALLER_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.lessThanOrEqual=" + SMALLER_DATE_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsLessThanSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio is less than DEFAULT_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.lessThan=" + DEFAULT_DATE_APPEL_VISIO);

        // Get all the subscriberStatusList where dateAppelVisio is less than UPDATED_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.lessThan=" + UPDATED_DATE_APPEL_VISIO);
    }

    @Test
    @Transactional
    public void getAllSubscriberStatusesByDateAppelVisioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        // Get all the subscriberStatusList where dateAppelVisio is greater than DEFAULT_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldNotBeFound("dateAppelVisio.greaterThan=" + DEFAULT_DATE_APPEL_VISIO);

        // Get all the subscriberStatusList where dateAppelVisio is greater than SMALLER_DATE_APPEL_VISIO
        defaultSubscriberStatusShouldBeFound("dateAppelVisio.greaterThan=" + SMALLER_DATE_APPEL_VISIO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSubscriberStatusShouldBeFound(String filter) throws Exception {
        restSubscriberStatusMockMvc.perform(get("/api/subscriber-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscriberStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].numCin").value(hasItem(DEFAULT_NUM_CIN)))
            .andExpect(jsonPath("$.[*].withAppelVisio").value(hasItem(DEFAULT_WITH_APPEL_VISIO.toString())))
            .andExpect(jsonPath("$.[*].withCertif").value(hasItem(DEFAULT_WITH_CERTIF.toString())))
            .andExpect(jsonPath("$.[*].withSignature").value(hasItem(DEFAULT_WITH_SIGNATURE.toString())))
            .andExpect(jsonPath("$.[*].dateAppelVisio").value(hasItem(DEFAULT_DATE_APPEL_VISIO.toString())));

        // Check, that the count call also returns 1
        restSubscriberStatusMockMvc.perform(get("/api/subscriber-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSubscriberStatusShouldNotBeFound(String filter) throws Exception {
        restSubscriberStatusMockMvc.perform(get("/api/subscriber-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSubscriberStatusMockMvc.perform(get("/api/subscriber-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingSubscriberStatus() throws Exception {
        // Get the subscriberStatus
        restSubscriberStatusMockMvc.perform(get("/api/subscriber-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNonExistingSubscriberStatus() throws Exception {
        int databaseSizeBeforeUpdate = subscriberStatusRepository.findAll().size();

        // Create the SubscriberStatus
        SubscriberStatusDTO subscriberStatusDTO = subscriberStatusMapper.toDto(subscriberStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubscriberStatusMockMvc.perform(put("/api/subscriber-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriberStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriberStatus in the database
        List<SubscriberStatus> subscriberStatusList = subscriberStatusRepository.findAll();
        assertThat(subscriberStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubscriberStatus() throws Exception {
        // Initialize the database
        subscriberStatusRepository.saveAndFlush(subscriberStatus);

        int databaseSizeBeforeDelete = subscriberStatusRepository.findAll().size();

        // Delete the subscriberStatus
        restSubscriberStatusMockMvc.perform(delete("/api/subscriber-statuses/{id}", subscriberStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubscriberStatus> subscriberStatusList = subscriberStatusRepository.findAll();
        assertThat(subscriberStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
