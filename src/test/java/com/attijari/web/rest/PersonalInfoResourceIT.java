package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.*;
import com.attijari.repository.PersonalInfoRepository;
import com.attijari.service.PersonalInfoService;
import com.attijari.service.dto.PersonalInfoDTO;
import com.attijari.service.mapper.PersonalInfoMapper;
import com.attijari.service.PersonalInfoQueryService;

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
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.attijari.domain.enumeration.Civility;
/**
 * Integration tests for the {@link PersonalInfoResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonalInfoResourceIT {

    private static final Civility DEFAULT_CIVILITY = Civility.MADAME;
    private static final Civility UPDATED_CIVILITY = Civility.MONSIEUR;

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NATIVE_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_NATIVE_COUNTRY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDAY = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_BIRTHDAY = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_CLIENT_ABT = false;
    private static final Boolean UPDATED_CLIENT_ABT = true;

    private static final String DEFAULT_RIB = "AAAAAAAAAA";
    private static final String UPDATED_RIB = "BBBBBBBBBB";

    private static final Nationality DEFAULT_NATIONALITY = new Nationality();
    private static final Nationality UPDATED_NATIONALITY = new Nationality();

    private static final Nationality DEFAULT_SECOND_NATIONALITY = new Nationality();
    private static final Nationality UPDATED_SECOND_NATIONALITY = new Nationality();

    private static final Integer DEFAULT_NBRKIDS = 1;
    private static final Integer UPDATED_NBRKIDS = 2;
    private static final Integer SMALLER_NBRKIDS = 1 - 1;

    private static final String DEFAULT_MARITAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MARITAL_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_AMERICAN_INDEX = false;
    private static final Boolean UPDATED_AMERICAN_INDEX = true;

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CIN = "AAAAAAAAAA";
    private static final String UPDATED_CIN = "BBBBBBBBBB";

    private static final String DEFAULT_ABROAD_RESIDANCY_PROOF = "AAAAAAAAAA";
    private static final String UPDATED_ABROAD_RESIDANCY_PROOF = "BBBBBBBBBB";

    private static final String DEFAULT_ABROAD_RESIDANCY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ABROAD_RESIDANCY_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CIN_DELIVERY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CIN_DELIVERY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private PersonalInfoMapper personalInfoMapper;

    @Autowired
    private PersonalInfoService personalInfoService;

    @Autowired
    private PersonalInfoQueryService personalInfoQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonalInfoMockMvc;

    private PersonalInfo personalInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalInfo createEntity(EntityManager em) {
        PersonalInfo personalInfo = new PersonalInfo()
            .civility(DEFAULT_CIVILITY)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .nativeCountry(DEFAULT_NATIVE_COUNTRY)
            .birthday(DEFAULT_BIRTHDAY)
            .clientABT(DEFAULT_CLIENT_ABT)
            .rib(DEFAULT_RIB)
            .secondNationality(DEFAULT_SECOND_NATIONALITY)
            .nbrkids(DEFAULT_NBRKIDS)
            .maritalStatus(DEFAULT_MARITAL_STATUS)
            .phone(DEFAULT_PHONE)
            .americanIndex(DEFAULT_AMERICAN_INDEX)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .cin(DEFAULT_CIN)
            .abroadResidancyProof(DEFAULT_ABROAD_RESIDANCY_PROOF)
            .abroadResidancyNumber(DEFAULT_ABROAD_RESIDANCY_NUMBER)
            .cinDeliveryDate(DEFAULT_CIN_DELIVERY_DATE)
            .abroadResidancyDeliveryDate(DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE)
            .abroadResidancyExpirationDate(DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE);
        return personalInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalInfo createUpdatedEntity(EntityManager em) {
        PersonalInfo personalInfo = new PersonalInfo()
            .civility(UPDATED_CIVILITY)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .nativeCountry(UPDATED_NATIVE_COUNTRY)
            .birthday(UPDATED_BIRTHDAY)
            .clientABT(UPDATED_CLIENT_ABT)
            .rib(UPDATED_RIB)
            .secondNationality(UPDATED_SECOND_NATIONALITY)
            .nbrkids(UPDATED_NBRKIDS)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .phone(UPDATED_PHONE)
            .americanIndex(UPDATED_AMERICAN_INDEX)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .cin(UPDATED_CIN)
            .abroadResidancyProof(UPDATED_ABROAD_RESIDANCY_PROOF)
            .abroadResidancyNumber(UPDATED_ABROAD_RESIDANCY_NUMBER)
            .cinDeliveryDate(UPDATED_CIN_DELIVERY_DATE)
            .abroadResidancyDeliveryDate(UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE)
            .abroadResidancyExpirationDate(UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE);
        return personalInfo;
    }

    @BeforeEach
    public void initTest() {
        personalInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonalInfo() throws Exception {
        int databaseSizeBeforeCreate = personalInfoRepository.findAll().size();
        // Create the PersonalInfo
        PersonalInfoDTO personalInfoDTO = personalInfoMapper.toDto(personalInfo);
        restPersonalInfoMockMvc.perform(post("/api/personal-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonalInfo in the database
        List<PersonalInfo> personalInfoList = personalInfoRepository.findAll();
        assertThat(personalInfoList).hasSize(databaseSizeBeforeCreate + 1);
        PersonalInfo testPersonalInfo = personalInfoList.get(personalInfoList.size() - 1);
        assertThat(testPersonalInfo.getCivility()).isEqualTo(DEFAULT_CIVILITY);
        assertThat(testPersonalInfo.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPersonalInfo.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPersonalInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPersonalInfo.getNativeCountry()).isEqualTo(DEFAULT_NATIVE_COUNTRY);
        assertThat(testPersonalInfo.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testPersonalInfo.isClientABT()).isEqualTo(DEFAULT_CLIENT_ABT);
        assertThat(testPersonalInfo.getRib()).isEqualTo(DEFAULT_RIB);
        assertThat(testPersonalInfo.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testPersonalInfo.getSecondNationality()).isEqualTo(DEFAULT_SECOND_NATIONALITY);
        assertThat(testPersonalInfo.getNbrkids()).isEqualTo(DEFAULT_NBRKIDS);
        assertThat(testPersonalInfo.getMaritalStatus()).isEqualTo(DEFAULT_MARITAL_STATUS);
        assertThat(testPersonalInfo.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPersonalInfo.isAmericanIndex()).isEqualTo(DEFAULT_AMERICAN_INDEX);
        assertThat(testPersonalInfo.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testPersonalInfo.getCin()).isEqualTo(DEFAULT_CIN);
        assertThat(testPersonalInfo.getAbroadResidancyProof()).isEqualTo(DEFAULT_ABROAD_RESIDANCY_PROOF);
        assertThat(testPersonalInfo.getAbroadResidancyNumber()).isEqualTo(DEFAULT_ABROAD_RESIDANCY_NUMBER);
        assertThat(testPersonalInfo.getCinDeliveryDate()).isEqualTo(DEFAULT_CIN_DELIVERY_DATE);
        assertThat(testPersonalInfo.getAbroadResidancyDeliveryDate()).isEqualTo(DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE);
        assertThat(testPersonalInfo.getAbroadResidancyExpirationDate()).isEqualTo(DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void createPersonalInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personalInfoRepository.findAll().size();

        // Create the PersonalInfo with an existing ID
        personalInfo.setId(1L);
        PersonalInfoDTO personalInfoDTO = personalInfoMapper.toDto(personalInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonalInfoMockMvc.perform(post("/api/personal-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalInfo in the database
        List<PersonalInfo> personalInfoList = personalInfoRepository.findAll();
        assertThat(personalInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersonalInfos() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList
        restPersonalInfoMockMvc.perform(get("/api/personal-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].civility").value(hasItem(DEFAULT_CIVILITY.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].nativeCountry").value(hasItem(DEFAULT_NATIVE_COUNTRY)))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].clientABT").value(hasItem(DEFAULT_CLIENT_ABT.booleanValue())))
            .andExpect(jsonPath("$.[*].rib").value(hasItem(DEFAULT_RIB)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].secondNationality").value(hasItem(DEFAULT_SECOND_NATIONALITY)))
            .andExpect(jsonPath("$.[*].nbrkids").value(hasItem(DEFAULT_NBRKIDS)))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].americanIndex").value(hasItem(DEFAULT_AMERICAN_INDEX.booleanValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN)))
            .andExpect(jsonPath("$.[*].abroadResidancyProof").value(hasItem(DEFAULT_ABROAD_RESIDANCY_PROOF)))
            .andExpect(jsonPath("$.[*].abroadResidancyNumber").value(hasItem(DEFAULT_ABROAD_RESIDANCY_NUMBER)))
            .andExpect(jsonPath("$.[*].cinDeliveryDate").value(hasItem(DEFAULT_CIN_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].abroadResidancyDeliveryDate").value(hasItem(DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].abroadResidancyExpirationDate").value(hasItem(DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getPersonalInfo() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get the personalInfo
        restPersonalInfoMockMvc.perform(get("/api/personal-infos/{id}", personalInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personalInfo.getId().intValue()))
            .andExpect(jsonPath("$.civility").value(DEFAULT_CIVILITY.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.nativeCountry").value(DEFAULT_NATIVE_COUNTRY))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
            .andExpect(jsonPath("$.clientABT").value(DEFAULT_CLIENT_ABT.booleanValue()))
            .andExpect(jsonPath("$.rib").value(DEFAULT_RIB))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.secondNationality").value(DEFAULT_SECOND_NATIONALITY))
            .andExpect(jsonPath("$.nbrkids").value(DEFAULT_NBRKIDS))
            .andExpect(jsonPath("$.maritalStatus").value(DEFAULT_MARITAL_STATUS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.americanIndex").value(DEFAULT_AMERICAN_INDEX.booleanValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.cin").value(DEFAULT_CIN))
            .andExpect(jsonPath("$.abroadResidancyProof").value(DEFAULT_ABROAD_RESIDANCY_PROOF))
            .andExpect(jsonPath("$.abroadResidancyNumber").value(DEFAULT_ABROAD_RESIDANCY_NUMBER))
            .andExpect(jsonPath("$.cinDeliveryDate").value(DEFAULT_CIN_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.abroadResidancyDeliveryDate").value(DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.abroadResidancyExpirationDate").value(DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE.toString()));
    }


    @Test
    @Transactional
    public void getPersonalInfosByIdFiltering() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        Long id = personalInfo.getId();

        defaultPersonalInfoShouldBeFound("id.equals=" + id);
        defaultPersonalInfoShouldNotBeFound("id.notEquals=" + id);

        defaultPersonalInfoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPersonalInfoShouldNotBeFound("id.greaterThan=" + id);

        defaultPersonalInfoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPersonalInfoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByCivilityIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where civility equals to DEFAULT_CIVILITY
        defaultPersonalInfoShouldBeFound("civility.equals=" + DEFAULT_CIVILITY);

        // Get all the personalInfoList where civility equals to UPDATED_CIVILITY
        defaultPersonalInfoShouldNotBeFound("civility.equals=" + UPDATED_CIVILITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCivilityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where civility not equals to DEFAULT_CIVILITY
        defaultPersonalInfoShouldNotBeFound("civility.notEquals=" + DEFAULT_CIVILITY);

        // Get all the personalInfoList where civility not equals to UPDATED_CIVILITY
        defaultPersonalInfoShouldBeFound("civility.notEquals=" + UPDATED_CIVILITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCivilityIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where civility in DEFAULT_CIVILITY or UPDATED_CIVILITY
        defaultPersonalInfoShouldBeFound("civility.in=" + DEFAULT_CIVILITY + "," + UPDATED_CIVILITY);

        // Get all the personalInfoList where civility equals to UPDATED_CIVILITY
        defaultPersonalInfoShouldNotBeFound("civility.in=" + UPDATED_CIVILITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCivilityIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where civility is not null
        defaultPersonalInfoShouldBeFound("civility.specified=true");

        // Get all the personalInfoList where civility is null
        defaultPersonalInfoShouldNotBeFound("civility.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where firstName equals to DEFAULT_FIRST_NAME
        defaultPersonalInfoShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the personalInfoList where firstName equals to UPDATED_FIRST_NAME
        defaultPersonalInfoShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where firstName not equals to DEFAULT_FIRST_NAME
        defaultPersonalInfoShouldNotBeFound("firstName.notEquals=" + DEFAULT_FIRST_NAME);

        // Get all the personalInfoList where firstName not equals to UPDATED_FIRST_NAME
        defaultPersonalInfoShouldBeFound("firstName.notEquals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultPersonalInfoShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the personalInfoList where firstName equals to UPDATED_FIRST_NAME
        defaultPersonalInfoShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where firstName is not null
        defaultPersonalInfoShouldBeFound("firstName.specified=true");

        // Get all the personalInfoList where firstName is null
        defaultPersonalInfoShouldNotBeFound("firstName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where firstName contains DEFAULT_FIRST_NAME
        defaultPersonalInfoShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the personalInfoList where firstName contains UPDATED_FIRST_NAME
        defaultPersonalInfoShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where firstName does not contain DEFAULT_FIRST_NAME
        defaultPersonalInfoShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the personalInfoList where firstName does not contain UPDATED_FIRST_NAME
        defaultPersonalInfoShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where lastName equals to DEFAULT_LAST_NAME
        defaultPersonalInfoShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the personalInfoList where lastName equals to UPDATED_LAST_NAME
        defaultPersonalInfoShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where lastName not equals to DEFAULT_LAST_NAME
        defaultPersonalInfoShouldNotBeFound("lastName.notEquals=" + DEFAULT_LAST_NAME);

        // Get all the personalInfoList where lastName not equals to UPDATED_LAST_NAME
        defaultPersonalInfoShouldBeFound("lastName.notEquals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultPersonalInfoShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the personalInfoList where lastName equals to UPDATED_LAST_NAME
        defaultPersonalInfoShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where lastName is not null
        defaultPersonalInfoShouldBeFound("lastName.specified=true");

        // Get all the personalInfoList where lastName is null
        defaultPersonalInfoShouldNotBeFound("lastName.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByLastNameContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where lastName contains DEFAULT_LAST_NAME
        defaultPersonalInfoShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the personalInfoList where lastName contains UPDATED_LAST_NAME
        defaultPersonalInfoShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where lastName does not contain DEFAULT_LAST_NAME
        defaultPersonalInfoShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the personalInfoList where lastName does not contain UPDATED_LAST_NAME
        defaultPersonalInfoShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where email equals to DEFAULT_EMAIL
        defaultPersonalInfoShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the personalInfoList where email equals to UPDATED_EMAIL
        defaultPersonalInfoShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where email not equals to DEFAULT_EMAIL
        defaultPersonalInfoShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the personalInfoList where email not equals to UPDATED_EMAIL
        defaultPersonalInfoShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultPersonalInfoShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the personalInfoList where email equals to UPDATED_EMAIL
        defaultPersonalInfoShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where email is not null
        defaultPersonalInfoShouldBeFound("email.specified=true");

        // Get all the personalInfoList where email is null
        defaultPersonalInfoShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByEmailContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where email contains DEFAULT_EMAIL
        defaultPersonalInfoShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the personalInfoList where email contains UPDATED_EMAIL
        defaultPersonalInfoShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where email does not contain DEFAULT_EMAIL
        defaultPersonalInfoShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the personalInfoList where email does not contain UPDATED_EMAIL
        defaultPersonalInfoShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByNativeCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nativeCountry equals to DEFAULT_NATIVE_COUNTRY
        defaultPersonalInfoShouldBeFound("nativeCountry.equals=" + DEFAULT_NATIVE_COUNTRY);

        // Get all the personalInfoList where nativeCountry equals to UPDATED_NATIVE_COUNTRY
        defaultPersonalInfoShouldNotBeFound("nativeCountry.equals=" + UPDATED_NATIVE_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNativeCountryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nativeCountry not equals to DEFAULT_NATIVE_COUNTRY
        defaultPersonalInfoShouldNotBeFound("nativeCountry.notEquals=" + DEFAULT_NATIVE_COUNTRY);

        // Get all the personalInfoList where nativeCountry not equals to UPDATED_NATIVE_COUNTRY
        defaultPersonalInfoShouldBeFound("nativeCountry.notEquals=" + UPDATED_NATIVE_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNativeCountryIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nativeCountry in DEFAULT_NATIVE_COUNTRY or UPDATED_NATIVE_COUNTRY
        defaultPersonalInfoShouldBeFound("nativeCountry.in=" + DEFAULT_NATIVE_COUNTRY + "," + UPDATED_NATIVE_COUNTRY);

        // Get all the personalInfoList where nativeCountry equals to UPDATED_NATIVE_COUNTRY
        defaultPersonalInfoShouldNotBeFound("nativeCountry.in=" + UPDATED_NATIVE_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNativeCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nativeCountry is not null
        defaultPersonalInfoShouldBeFound("nativeCountry.specified=true");

        // Get all the personalInfoList where nativeCountry is null
        defaultPersonalInfoShouldNotBeFound("nativeCountry.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByNativeCountryContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nativeCountry contains DEFAULT_NATIVE_COUNTRY
        defaultPersonalInfoShouldBeFound("nativeCountry.contains=" + DEFAULT_NATIVE_COUNTRY);

        // Get all the personalInfoList where nativeCountry contains UPDATED_NATIVE_COUNTRY
        defaultPersonalInfoShouldNotBeFound("nativeCountry.contains=" + UPDATED_NATIVE_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNativeCountryNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nativeCountry does not contain DEFAULT_NATIVE_COUNTRY
        defaultPersonalInfoShouldNotBeFound("nativeCountry.doesNotContain=" + DEFAULT_NATIVE_COUNTRY);

        // Get all the personalInfoList where nativeCountry does not contain UPDATED_NATIVE_COUNTRY
        defaultPersonalInfoShouldBeFound("nativeCountry.doesNotContain=" + UPDATED_NATIVE_COUNTRY);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday equals to DEFAULT_BIRTHDAY
        defaultPersonalInfoShouldBeFound("birthday.equals=" + DEFAULT_BIRTHDAY);

        // Get all the personalInfoList where birthday equals to UPDATED_BIRTHDAY
        defaultPersonalInfoShouldNotBeFound("birthday.equals=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday not equals to DEFAULT_BIRTHDAY
        defaultPersonalInfoShouldNotBeFound("birthday.notEquals=" + DEFAULT_BIRTHDAY);

        // Get all the personalInfoList where birthday not equals to UPDATED_BIRTHDAY
        defaultPersonalInfoShouldBeFound("birthday.notEquals=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday in DEFAULT_BIRTHDAY or UPDATED_BIRTHDAY
        defaultPersonalInfoShouldBeFound("birthday.in=" + DEFAULT_BIRTHDAY + "," + UPDATED_BIRTHDAY);

        // Get all the personalInfoList where birthday equals to UPDATED_BIRTHDAY
        defaultPersonalInfoShouldNotBeFound("birthday.in=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday is not null
        defaultPersonalInfoShouldBeFound("birthday.specified=true");

        // Get all the personalInfoList where birthday is null
        defaultPersonalInfoShouldNotBeFound("birthday.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday is greater than or equal to DEFAULT_BIRTHDAY
        defaultPersonalInfoShouldBeFound("birthday.greaterThanOrEqual=" + DEFAULT_BIRTHDAY);

        // Get all the personalInfoList where birthday is greater than or equal to UPDATED_BIRTHDAY
        defaultPersonalInfoShouldNotBeFound("birthday.greaterThanOrEqual=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday is less than or equal to DEFAULT_BIRTHDAY
        defaultPersonalInfoShouldBeFound("birthday.lessThanOrEqual=" + DEFAULT_BIRTHDAY);

        // Get all the personalInfoList where birthday is less than or equal to SMALLER_BIRTHDAY
        defaultPersonalInfoShouldNotBeFound("birthday.lessThanOrEqual=" + SMALLER_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsLessThanSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday is less than DEFAULT_BIRTHDAY
        defaultPersonalInfoShouldNotBeFound("birthday.lessThan=" + DEFAULT_BIRTHDAY);

        // Get all the personalInfoList where birthday is less than UPDATED_BIRTHDAY
        defaultPersonalInfoShouldBeFound("birthday.lessThan=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByBirthdayIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where birthday is greater than DEFAULT_BIRTHDAY
        defaultPersonalInfoShouldNotBeFound("birthday.greaterThan=" + DEFAULT_BIRTHDAY);

        // Get all the personalInfoList where birthday is greater than SMALLER_BIRTHDAY
        defaultPersonalInfoShouldBeFound("birthday.greaterThan=" + SMALLER_BIRTHDAY);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByClientABTIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where clientABT equals to DEFAULT_CLIENT_ABT
        defaultPersonalInfoShouldBeFound("clientABT.equals=" + DEFAULT_CLIENT_ABT);

        // Get all the personalInfoList where clientABT equals to UPDATED_CLIENT_ABT
        defaultPersonalInfoShouldNotBeFound("clientABT.equals=" + UPDATED_CLIENT_ABT);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByClientABTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where clientABT not equals to DEFAULT_CLIENT_ABT
        defaultPersonalInfoShouldNotBeFound("clientABT.notEquals=" + DEFAULT_CLIENT_ABT);

        // Get all the personalInfoList where clientABT not equals to UPDATED_CLIENT_ABT
        defaultPersonalInfoShouldBeFound("clientABT.notEquals=" + UPDATED_CLIENT_ABT);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByClientABTIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where clientABT in DEFAULT_CLIENT_ABT or UPDATED_CLIENT_ABT
        defaultPersonalInfoShouldBeFound("clientABT.in=" + DEFAULT_CLIENT_ABT + "," + UPDATED_CLIENT_ABT);

        // Get all the personalInfoList where clientABT equals to UPDATED_CLIENT_ABT
        defaultPersonalInfoShouldNotBeFound("clientABT.in=" + UPDATED_CLIENT_ABT);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByClientABTIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where clientABT is not null
        defaultPersonalInfoShouldBeFound("clientABT.specified=true");

        // Get all the personalInfoList where clientABT is null
        defaultPersonalInfoShouldNotBeFound("clientABT.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByRibIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where rib equals to DEFAULT_RIB
        defaultPersonalInfoShouldBeFound("rib.equals=" + DEFAULT_RIB);

        // Get all the personalInfoList where rib equals to UPDATED_RIB
        defaultPersonalInfoShouldNotBeFound("rib.equals=" + UPDATED_RIB);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByRibIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where rib not equals to DEFAULT_RIB
        defaultPersonalInfoShouldNotBeFound("rib.notEquals=" + DEFAULT_RIB);

        // Get all the personalInfoList where rib not equals to UPDATED_RIB
        defaultPersonalInfoShouldBeFound("rib.notEquals=" + UPDATED_RIB);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByRibIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where rib in DEFAULT_RIB or UPDATED_RIB
        defaultPersonalInfoShouldBeFound("rib.in=" + DEFAULT_RIB + "," + UPDATED_RIB);

        // Get all the personalInfoList where rib equals to UPDATED_RIB
        defaultPersonalInfoShouldNotBeFound("rib.in=" + UPDATED_RIB);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByRibIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where rib is not null
        defaultPersonalInfoShouldBeFound("rib.specified=true");

        // Get all the personalInfoList where rib is null
        defaultPersonalInfoShouldNotBeFound("rib.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByRibContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where rib contains DEFAULT_RIB
        defaultPersonalInfoShouldBeFound("rib.contains=" + DEFAULT_RIB);

        // Get all the personalInfoList where rib contains UPDATED_RIB
        defaultPersonalInfoShouldNotBeFound("rib.contains=" + UPDATED_RIB);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByRibNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where rib does not contain DEFAULT_RIB
        defaultPersonalInfoShouldNotBeFound("rib.doesNotContain=" + DEFAULT_RIB);

        // Get all the personalInfoList where rib does not contain UPDATED_RIB
        defaultPersonalInfoShouldBeFound("rib.doesNotContain=" + UPDATED_RIB);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByNationalityIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nationality equals to DEFAULT_NATIONALITY
        defaultPersonalInfoShouldBeFound("nationality.equals=" + DEFAULT_NATIONALITY);

        // Get all the personalInfoList where nationality equals to UPDATED_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("nationality.equals=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNationalityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nationality not equals to DEFAULT_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("nationality.notEquals=" + DEFAULT_NATIONALITY);

        // Get all the personalInfoList where nationality not equals to UPDATED_NATIONALITY
        defaultPersonalInfoShouldBeFound("nationality.notEquals=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNationalityIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nationality in DEFAULT_NATIONALITY or UPDATED_NATIONALITY
        defaultPersonalInfoShouldBeFound("nationality.in=" + DEFAULT_NATIONALITY + "," + UPDATED_NATIONALITY);

        // Get all the personalInfoList where nationality equals to UPDATED_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("nationality.in=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNationalityIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nationality is not null
        defaultPersonalInfoShouldBeFound("nationality.specified=true");

        // Get all the personalInfoList where nationality is null
        defaultPersonalInfoShouldNotBeFound("nationality.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByNationalityContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nationality contains DEFAULT_NATIONALITY
        defaultPersonalInfoShouldBeFound("nationality.contains=" + DEFAULT_NATIONALITY);

        // Get all the personalInfoList where nationality contains UPDATED_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("nationality.contains=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNationalityNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nationality does not contain DEFAULT_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("nationality.doesNotContain=" + DEFAULT_NATIONALITY);

        // Get all the personalInfoList where nationality does not contain UPDATED_NATIONALITY
        defaultPersonalInfoShouldBeFound("nationality.doesNotContain=" + UPDATED_NATIONALITY);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosBySecondNationalityIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where secondNationality equals to DEFAULT_SECOND_NATIONALITY
        defaultPersonalInfoShouldBeFound("secondNationality.equals=" + DEFAULT_SECOND_NATIONALITY);

        // Get all the personalInfoList where secondNationality equals to UPDATED_SECOND_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("secondNationality.equals=" + UPDATED_SECOND_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosBySecondNationalityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where secondNationality not equals to DEFAULT_SECOND_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("secondNationality.notEquals=" + DEFAULT_SECOND_NATIONALITY);

        // Get all the personalInfoList where secondNationality not equals to UPDATED_SECOND_NATIONALITY
        defaultPersonalInfoShouldBeFound("secondNationality.notEquals=" + UPDATED_SECOND_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosBySecondNationalityIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where secondNationality in DEFAULT_SECOND_NATIONALITY or UPDATED_SECOND_NATIONALITY
        defaultPersonalInfoShouldBeFound("secondNationality.in=" + DEFAULT_SECOND_NATIONALITY + "," + UPDATED_SECOND_NATIONALITY);

        // Get all the personalInfoList where secondNationality equals to UPDATED_SECOND_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("secondNationality.in=" + UPDATED_SECOND_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosBySecondNationalityIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where secondNationality is not null
        defaultPersonalInfoShouldBeFound("secondNationality.specified=true");

        // Get all the personalInfoList where secondNationality is null
        defaultPersonalInfoShouldNotBeFound("secondNationality.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosBySecondNationalityContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where secondNationality contains DEFAULT_SECOND_NATIONALITY
        defaultPersonalInfoShouldBeFound("secondNationality.contains=" + DEFAULT_SECOND_NATIONALITY);

        // Get all the personalInfoList where secondNationality contains UPDATED_SECOND_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("secondNationality.contains=" + UPDATED_SECOND_NATIONALITY);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosBySecondNationalityNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where secondNationality does not contain DEFAULT_SECOND_NATIONALITY
        defaultPersonalInfoShouldNotBeFound("secondNationality.doesNotContain=" + DEFAULT_SECOND_NATIONALITY);

        // Get all the personalInfoList where secondNationality does not contain UPDATED_SECOND_NATIONALITY
        defaultPersonalInfoShouldBeFound("secondNationality.doesNotContain=" + UPDATED_SECOND_NATIONALITY);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids equals to DEFAULT_NBRKIDS
        defaultPersonalInfoShouldBeFound("nbrkids.equals=" + DEFAULT_NBRKIDS);

        // Get all the personalInfoList where nbrkids equals to UPDATED_NBRKIDS
        defaultPersonalInfoShouldNotBeFound("nbrkids.equals=" + UPDATED_NBRKIDS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids not equals to DEFAULT_NBRKIDS
        defaultPersonalInfoShouldNotBeFound("nbrkids.notEquals=" + DEFAULT_NBRKIDS);

        // Get all the personalInfoList where nbrkids not equals to UPDATED_NBRKIDS
        defaultPersonalInfoShouldBeFound("nbrkids.notEquals=" + UPDATED_NBRKIDS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids in DEFAULT_NBRKIDS or UPDATED_NBRKIDS
        defaultPersonalInfoShouldBeFound("nbrkids.in=" + DEFAULT_NBRKIDS + "," + UPDATED_NBRKIDS);

        // Get all the personalInfoList where nbrkids equals to UPDATED_NBRKIDS
        defaultPersonalInfoShouldNotBeFound("nbrkids.in=" + UPDATED_NBRKIDS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids is not null
        defaultPersonalInfoShouldBeFound("nbrkids.specified=true");

        // Get all the personalInfoList where nbrkids is null
        defaultPersonalInfoShouldNotBeFound("nbrkids.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids is greater than or equal to DEFAULT_NBRKIDS
        defaultPersonalInfoShouldBeFound("nbrkids.greaterThanOrEqual=" + DEFAULT_NBRKIDS);

        // Get all the personalInfoList where nbrkids is greater than or equal to UPDATED_NBRKIDS
        defaultPersonalInfoShouldNotBeFound("nbrkids.greaterThanOrEqual=" + UPDATED_NBRKIDS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids is less than or equal to DEFAULT_NBRKIDS
        defaultPersonalInfoShouldBeFound("nbrkids.lessThanOrEqual=" + DEFAULT_NBRKIDS);

        // Get all the personalInfoList where nbrkids is less than or equal to SMALLER_NBRKIDS
        defaultPersonalInfoShouldNotBeFound("nbrkids.lessThanOrEqual=" + SMALLER_NBRKIDS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsLessThanSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids is less than DEFAULT_NBRKIDS
        defaultPersonalInfoShouldNotBeFound("nbrkids.lessThan=" + DEFAULT_NBRKIDS);

        // Get all the personalInfoList where nbrkids is less than UPDATED_NBRKIDS
        defaultPersonalInfoShouldBeFound("nbrkids.lessThan=" + UPDATED_NBRKIDS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByNbrkidsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where nbrkids is greater than DEFAULT_NBRKIDS
        defaultPersonalInfoShouldNotBeFound("nbrkids.greaterThan=" + DEFAULT_NBRKIDS);

        // Get all the personalInfoList where nbrkids is greater than SMALLER_NBRKIDS
        defaultPersonalInfoShouldBeFound("nbrkids.greaterThan=" + SMALLER_NBRKIDS);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByMaritalStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where maritalStatus equals to DEFAULT_MARITAL_STATUS
        defaultPersonalInfoShouldBeFound("maritalStatus.equals=" + DEFAULT_MARITAL_STATUS);

        // Get all the personalInfoList where maritalStatus equals to UPDATED_MARITAL_STATUS
        defaultPersonalInfoShouldNotBeFound("maritalStatus.equals=" + UPDATED_MARITAL_STATUS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByMaritalStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where maritalStatus not equals to DEFAULT_MARITAL_STATUS
        defaultPersonalInfoShouldNotBeFound("maritalStatus.notEquals=" + DEFAULT_MARITAL_STATUS);

        // Get all the personalInfoList where maritalStatus not equals to UPDATED_MARITAL_STATUS
        defaultPersonalInfoShouldBeFound("maritalStatus.notEquals=" + UPDATED_MARITAL_STATUS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByMaritalStatusIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where maritalStatus in DEFAULT_MARITAL_STATUS or UPDATED_MARITAL_STATUS
        defaultPersonalInfoShouldBeFound("maritalStatus.in=" + DEFAULT_MARITAL_STATUS + "," + UPDATED_MARITAL_STATUS);

        // Get all the personalInfoList where maritalStatus equals to UPDATED_MARITAL_STATUS
        defaultPersonalInfoShouldNotBeFound("maritalStatus.in=" + UPDATED_MARITAL_STATUS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByMaritalStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where maritalStatus is not null
        defaultPersonalInfoShouldBeFound("maritalStatus.specified=true");

        // Get all the personalInfoList where maritalStatus is null
        defaultPersonalInfoShouldNotBeFound("maritalStatus.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByMaritalStatusContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where maritalStatus contains DEFAULT_MARITAL_STATUS
        defaultPersonalInfoShouldBeFound("maritalStatus.contains=" + DEFAULT_MARITAL_STATUS);

        // Get all the personalInfoList where maritalStatus contains UPDATED_MARITAL_STATUS
        defaultPersonalInfoShouldNotBeFound("maritalStatus.contains=" + UPDATED_MARITAL_STATUS);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByMaritalStatusNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where maritalStatus does not contain DEFAULT_MARITAL_STATUS
        defaultPersonalInfoShouldNotBeFound("maritalStatus.doesNotContain=" + DEFAULT_MARITAL_STATUS);

        // Get all the personalInfoList where maritalStatus does not contain UPDATED_MARITAL_STATUS
        defaultPersonalInfoShouldBeFound("maritalStatus.doesNotContain=" + UPDATED_MARITAL_STATUS);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where phone equals to DEFAULT_PHONE
        defaultPersonalInfoShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the personalInfoList where phone equals to UPDATED_PHONE
        defaultPersonalInfoShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where phone not equals to DEFAULT_PHONE
        defaultPersonalInfoShouldNotBeFound("phone.notEquals=" + DEFAULT_PHONE);

        // Get all the personalInfoList where phone not equals to UPDATED_PHONE
        defaultPersonalInfoShouldBeFound("phone.notEquals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultPersonalInfoShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the personalInfoList where phone equals to UPDATED_PHONE
        defaultPersonalInfoShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where phone is not null
        defaultPersonalInfoShouldBeFound("phone.specified=true");

        // Get all the personalInfoList where phone is null
        defaultPersonalInfoShouldNotBeFound("phone.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByPhoneContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where phone contains DEFAULT_PHONE
        defaultPersonalInfoShouldBeFound("phone.contains=" + DEFAULT_PHONE);

        // Get all the personalInfoList where phone contains UPDATED_PHONE
        defaultPersonalInfoShouldNotBeFound("phone.contains=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByPhoneNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where phone does not contain DEFAULT_PHONE
        defaultPersonalInfoShouldNotBeFound("phone.doesNotContain=" + DEFAULT_PHONE);

        // Get all the personalInfoList where phone does not contain UPDATED_PHONE
        defaultPersonalInfoShouldBeFound("phone.doesNotContain=" + UPDATED_PHONE);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByAmericanIndexIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where americanIndex equals to DEFAULT_AMERICAN_INDEX
        defaultPersonalInfoShouldBeFound("americanIndex.equals=" + DEFAULT_AMERICAN_INDEX);

        // Get all the personalInfoList where americanIndex equals to UPDATED_AMERICAN_INDEX
        defaultPersonalInfoShouldNotBeFound("americanIndex.equals=" + UPDATED_AMERICAN_INDEX);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAmericanIndexIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where americanIndex not equals to DEFAULT_AMERICAN_INDEX
        defaultPersonalInfoShouldNotBeFound("americanIndex.notEquals=" + DEFAULT_AMERICAN_INDEX);

        // Get all the personalInfoList where americanIndex not equals to UPDATED_AMERICAN_INDEX
        defaultPersonalInfoShouldBeFound("americanIndex.notEquals=" + UPDATED_AMERICAN_INDEX);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAmericanIndexIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where americanIndex in DEFAULT_AMERICAN_INDEX or UPDATED_AMERICAN_INDEX
        defaultPersonalInfoShouldBeFound("americanIndex.in=" + DEFAULT_AMERICAN_INDEX + "," + UPDATED_AMERICAN_INDEX);

        // Get all the personalInfoList where americanIndex equals to UPDATED_AMERICAN_INDEX
        defaultPersonalInfoShouldNotBeFound("americanIndex.in=" + UPDATED_AMERICAN_INDEX);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAmericanIndexIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where americanIndex is not null
        defaultPersonalInfoShouldBeFound("americanIndex.specified=true");

        // Get all the personalInfoList where americanIndex is null
        defaultPersonalInfoShouldNotBeFound("americanIndex.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAccountNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where accountNumber equals to DEFAULT_ACCOUNT_NUMBER
        defaultPersonalInfoShouldBeFound("accountNumber.equals=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the personalInfoList where accountNumber equals to UPDATED_ACCOUNT_NUMBER
        defaultPersonalInfoShouldNotBeFound("accountNumber.equals=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAccountNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where accountNumber not equals to DEFAULT_ACCOUNT_NUMBER
        defaultPersonalInfoShouldNotBeFound("accountNumber.notEquals=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the personalInfoList where accountNumber not equals to UPDATED_ACCOUNT_NUMBER
        defaultPersonalInfoShouldBeFound("accountNumber.notEquals=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAccountNumberIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where accountNumber in DEFAULT_ACCOUNT_NUMBER or UPDATED_ACCOUNT_NUMBER
        defaultPersonalInfoShouldBeFound("accountNumber.in=" + DEFAULT_ACCOUNT_NUMBER + "," + UPDATED_ACCOUNT_NUMBER);

        // Get all the personalInfoList where accountNumber equals to UPDATED_ACCOUNT_NUMBER
        defaultPersonalInfoShouldNotBeFound("accountNumber.in=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAccountNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where accountNumber is not null
        defaultPersonalInfoShouldBeFound("accountNumber.specified=true");

        // Get all the personalInfoList where accountNumber is null
        defaultPersonalInfoShouldNotBeFound("accountNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByAccountNumberContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where accountNumber contains DEFAULT_ACCOUNT_NUMBER
        defaultPersonalInfoShouldBeFound("accountNumber.contains=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the personalInfoList where accountNumber contains UPDATED_ACCOUNT_NUMBER
        defaultPersonalInfoShouldNotBeFound("accountNumber.contains=" + UPDATED_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAccountNumberNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where accountNumber does not contain DEFAULT_ACCOUNT_NUMBER
        defaultPersonalInfoShouldNotBeFound("accountNumber.doesNotContain=" + DEFAULT_ACCOUNT_NUMBER);

        // Get all the personalInfoList where accountNumber does not contain UPDATED_ACCOUNT_NUMBER
        defaultPersonalInfoShouldBeFound("accountNumber.doesNotContain=" + UPDATED_ACCOUNT_NUMBER);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByCinIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cin equals to DEFAULT_CIN
        defaultPersonalInfoShouldBeFound("cin.equals=" + DEFAULT_CIN);

        // Get all the personalInfoList where cin equals to UPDATED_CIN
        defaultPersonalInfoShouldNotBeFound("cin.equals=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCinIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cin not equals to DEFAULT_CIN
        defaultPersonalInfoShouldNotBeFound("cin.notEquals=" + DEFAULT_CIN);

        // Get all the personalInfoList where cin not equals to UPDATED_CIN
        defaultPersonalInfoShouldBeFound("cin.notEquals=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCinIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cin in DEFAULT_CIN or UPDATED_CIN
        defaultPersonalInfoShouldBeFound("cin.in=" + DEFAULT_CIN + "," + UPDATED_CIN);

        // Get all the personalInfoList where cin equals to UPDATED_CIN
        defaultPersonalInfoShouldNotBeFound("cin.in=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCinIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cin is not null
        defaultPersonalInfoShouldBeFound("cin.specified=true");

        // Get all the personalInfoList where cin is null
        defaultPersonalInfoShouldNotBeFound("cin.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByCinContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cin contains DEFAULT_CIN
        defaultPersonalInfoShouldBeFound("cin.contains=" + DEFAULT_CIN);

        // Get all the personalInfoList where cin contains UPDATED_CIN
        defaultPersonalInfoShouldNotBeFound("cin.contains=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCinNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cin does not contain DEFAULT_CIN
        defaultPersonalInfoShouldNotBeFound("cin.doesNotContain=" + DEFAULT_CIN);

        // Get all the personalInfoList where cin does not contain UPDATED_CIN
        defaultPersonalInfoShouldBeFound("cin.doesNotContain=" + UPDATED_CIN);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyProofIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyProof equals to DEFAULT_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldBeFound("abroadResidancyProof.equals=" + DEFAULT_ABROAD_RESIDANCY_PROOF);

        // Get all the personalInfoList where abroadResidancyProof equals to UPDATED_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldNotBeFound("abroadResidancyProof.equals=" + UPDATED_ABROAD_RESIDANCY_PROOF);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyProofIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyProof not equals to DEFAULT_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldNotBeFound("abroadResidancyProof.notEquals=" + DEFAULT_ABROAD_RESIDANCY_PROOF);

        // Get all the personalInfoList where abroadResidancyProof not equals to UPDATED_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldBeFound("abroadResidancyProof.notEquals=" + UPDATED_ABROAD_RESIDANCY_PROOF);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyProofIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyProof in DEFAULT_ABROAD_RESIDANCY_PROOF or UPDATED_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldBeFound("abroadResidancyProof.in=" + DEFAULT_ABROAD_RESIDANCY_PROOF + "," + UPDATED_ABROAD_RESIDANCY_PROOF);

        // Get all the personalInfoList where abroadResidancyProof equals to UPDATED_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldNotBeFound("abroadResidancyProof.in=" + UPDATED_ABROAD_RESIDANCY_PROOF);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyProofIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyProof is not null
        defaultPersonalInfoShouldBeFound("abroadResidancyProof.specified=true");

        // Get all the personalInfoList where abroadResidancyProof is null
        defaultPersonalInfoShouldNotBeFound("abroadResidancyProof.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyProofContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyProof contains DEFAULT_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldBeFound("abroadResidancyProof.contains=" + DEFAULT_ABROAD_RESIDANCY_PROOF);

        // Get all the personalInfoList where abroadResidancyProof contains UPDATED_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldNotBeFound("abroadResidancyProof.contains=" + UPDATED_ABROAD_RESIDANCY_PROOF);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyProofNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyProof does not contain DEFAULT_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldNotBeFound("abroadResidancyProof.doesNotContain=" + DEFAULT_ABROAD_RESIDANCY_PROOF);

        // Get all the personalInfoList where abroadResidancyProof does not contain UPDATED_ABROAD_RESIDANCY_PROOF
        defaultPersonalInfoShouldBeFound("abroadResidancyProof.doesNotContain=" + UPDATED_ABROAD_RESIDANCY_PROOF);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyNumber equals to DEFAULT_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldBeFound("abroadResidancyNumber.equals=" + DEFAULT_ABROAD_RESIDANCY_NUMBER);

        // Get all the personalInfoList where abroadResidancyNumber equals to UPDATED_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldNotBeFound("abroadResidancyNumber.equals=" + UPDATED_ABROAD_RESIDANCY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyNumber not equals to DEFAULT_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldNotBeFound("abroadResidancyNumber.notEquals=" + DEFAULT_ABROAD_RESIDANCY_NUMBER);

        // Get all the personalInfoList where abroadResidancyNumber not equals to UPDATED_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldBeFound("abroadResidancyNumber.notEquals=" + UPDATED_ABROAD_RESIDANCY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyNumberIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyNumber in DEFAULT_ABROAD_RESIDANCY_NUMBER or UPDATED_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldBeFound("abroadResidancyNumber.in=" + DEFAULT_ABROAD_RESIDANCY_NUMBER + "," + UPDATED_ABROAD_RESIDANCY_NUMBER);

        // Get all the personalInfoList where abroadResidancyNumber equals to UPDATED_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldNotBeFound("abroadResidancyNumber.in=" + UPDATED_ABROAD_RESIDANCY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyNumber is not null
        defaultPersonalInfoShouldBeFound("abroadResidancyNumber.specified=true");

        // Get all the personalInfoList where abroadResidancyNumber is null
        defaultPersonalInfoShouldNotBeFound("abroadResidancyNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyNumberContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyNumber contains DEFAULT_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldBeFound("abroadResidancyNumber.contains=" + DEFAULT_ABROAD_RESIDANCY_NUMBER);

        // Get all the personalInfoList where abroadResidancyNumber contains UPDATED_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldNotBeFound("abroadResidancyNumber.contains=" + UPDATED_ABROAD_RESIDANCY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyNumberNotContainsSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyNumber does not contain DEFAULT_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldNotBeFound("abroadResidancyNumber.doesNotContain=" + DEFAULT_ABROAD_RESIDANCY_NUMBER);

        // Get all the personalInfoList where abroadResidancyNumber does not contain UPDATED_ABROAD_RESIDANCY_NUMBER
        defaultPersonalInfoShouldBeFound("abroadResidancyNumber.doesNotContain=" + UPDATED_ABROAD_RESIDANCY_NUMBER);
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByCinDeliveryDateIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cinDeliveryDate equals to DEFAULT_CIN_DELIVERY_DATE
        defaultPersonalInfoShouldBeFound("cinDeliveryDate.equals=" + DEFAULT_CIN_DELIVERY_DATE);

        // Get all the personalInfoList where cinDeliveryDate equals to UPDATED_CIN_DELIVERY_DATE
        defaultPersonalInfoShouldNotBeFound("cinDeliveryDate.equals=" + UPDATED_CIN_DELIVERY_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCinDeliveryDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cinDeliveryDate not equals to DEFAULT_CIN_DELIVERY_DATE
        defaultPersonalInfoShouldNotBeFound("cinDeliveryDate.notEquals=" + DEFAULT_CIN_DELIVERY_DATE);

        // Get all the personalInfoList where cinDeliveryDate not equals to UPDATED_CIN_DELIVERY_DATE
        defaultPersonalInfoShouldBeFound("cinDeliveryDate.notEquals=" + UPDATED_CIN_DELIVERY_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCinDeliveryDateIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cinDeliveryDate in DEFAULT_CIN_DELIVERY_DATE or UPDATED_CIN_DELIVERY_DATE
        defaultPersonalInfoShouldBeFound("cinDeliveryDate.in=" + DEFAULT_CIN_DELIVERY_DATE + "," + UPDATED_CIN_DELIVERY_DATE);

        // Get all the personalInfoList where cinDeliveryDate equals to UPDATED_CIN_DELIVERY_DATE
        defaultPersonalInfoShouldNotBeFound("cinDeliveryDate.in=" + UPDATED_CIN_DELIVERY_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByCinDeliveryDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where cinDeliveryDate is not null
        defaultPersonalInfoShouldBeFound("cinDeliveryDate.specified=true");

        // Get all the personalInfoList where cinDeliveryDate is null
        defaultPersonalInfoShouldNotBeFound("cinDeliveryDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyDeliveryDateIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyDeliveryDate equals to DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE
        defaultPersonalInfoShouldBeFound("abroadResidancyDeliveryDate.equals=" + DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE);

        // Get all the personalInfoList where abroadResidancyDeliveryDate equals to UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE
        defaultPersonalInfoShouldNotBeFound("abroadResidancyDeliveryDate.equals=" + UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyDeliveryDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyDeliveryDate not equals to DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE
        defaultPersonalInfoShouldNotBeFound("abroadResidancyDeliveryDate.notEquals=" + DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE);

        // Get all the personalInfoList where abroadResidancyDeliveryDate not equals to UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE
        defaultPersonalInfoShouldBeFound("abroadResidancyDeliveryDate.notEquals=" + UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyDeliveryDateIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyDeliveryDate in DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE or UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE
        defaultPersonalInfoShouldBeFound("abroadResidancyDeliveryDate.in=" + DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE + "," + UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE);

        // Get all the personalInfoList where abroadResidancyDeliveryDate equals to UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE
        defaultPersonalInfoShouldNotBeFound("abroadResidancyDeliveryDate.in=" + UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyDeliveryDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyDeliveryDate is not null
        defaultPersonalInfoShouldBeFound("abroadResidancyDeliveryDate.specified=true");

        // Get all the personalInfoList where abroadResidancyDeliveryDate is null
        defaultPersonalInfoShouldNotBeFound("abroadResidancyDeliveryDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyExpirationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyExpirationDate equals to DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE
        defaultPersonalInfoShouldBeFound("abroadResidancyExpirationDate.equals=" + DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE);

        // Get all the personalInfoList where abroadResidancyExpirationDate equals to UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE
        defaultPersonalInfoShouldNotBeFound("abroadResidancyExpirationDate.equals=" + UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyExpirationDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyExpirationDate not equals to DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE
        defaultPersonalInfoShouldNotBeFound("abroadResidancyExpirationDate.notEquals=" + DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE);

        // Get all the personalInfoList where abroadResidancyExpirationDate not equals to UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE
        defaultPersonalInfoShouldBeFound("abroadResidancyExpirationDate.notEquals=" + UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyExpirationDateIsInShouldWork() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyExpirationDate in DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE or UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE
        defaultPersonalInfoShouldBeFound("abroadResidancyExpirationDate.in=" + DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE + "," + UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE);

        // Get all the personalInfoList where abroadResidancyExpirationDate equals to UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE
        defaultPersonalInfoShouldNotBeFound("abroadResidancyExpirationDate.in=" + UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByAbroadResidancyExpirationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        // Get all the personalInfoList where abroadResidancyExpirationDate is not null
        defaultPersonalInfoShouldBeFound("abroadResidancyExpirationDate.specified=true");

        // Get all the personalInfoList where abroadResidancyExpirationDate is null
        defaultPersonalInfoShouldNotBeFound("abroadResidancyExpirationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonalInfosByRequestIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);
        Request request = RequestResourceIT.createEntity(em);
        em.persist(request);
        em.flush();
        personalInfoRepository.saveAndFlush(personalInfo);
        Long requestId = request.getId();

        // Get all the personalInfoList where request equals to requestId
        defaultPersonalInfoShouldBeFound("requestId.equals=" + requestId);

        // Get all the personalInfoList where request equals to requestId + 1
        defaultPersonalInfoShouldNotBeFound("requestId.equals=" + (requestId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByAdressInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);
        AdressInfo adressInfo = AdressInfoResourceIT.createEntity(em);
        em.persist(adressInfo);
        em.flush();
        personalInfo.setAdressInfo(adressInfo);
        adressInfo.setPersonalInfo(personalInfo);
        personalInfoRepository.saveAndFlush(personalInfo);
        Long adressInfoId = adressInfo.getId();

        // Get all the personalInfoList where adressInfo equals to adressInfoId
        defaultPersonalInfoShouldBeFound("adressInfoId.equals=" + adressInfoId);

        // Get all the personalInfoList where adressInfo equals to adressInfoId + 1
        defaultPersonalInfoShouldNotBeFound("adressInfoId.equals=" + (adressInfoId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByAgencyIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);
        Agency agency = AgencyResourceIT.createEntity(em);
        em.persist(agency);
        em.flush();
        personalInfo.setAgency(agency);
        personalInfoRepository.saveAndFlush(personalInfo);
        Long agencyId = agency.getId();

        // Get all the personalInfoList where agency equals to agencyId
        defaultPersonalInfoShouldBeFound("agencyId.equals=" + agencyId);

        // Get all the personalInfoList where agency equals to agencyId + 1
        defaultPersonalInfoShouldNotBeFound("agencyId.equals=" + (agencyId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonalInfosByFinancialInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);
        FinancialInfo financialInfo = FinancialInfoResourceIT.createEntity(em);
        em.persist(financialInfo);
        em.flush();
        personalInfo.setFinancialInfo(financialInfo);
        personalInfoRepository.saveAndFlush(personalInfo);
        Long financialInfoId = financialInfo.getId();

        // Get all the personalInfoList where financialInfo equals to financialInfoId
        defaultPersonalInfoShouldBeFound("financialInfoId.equals=" + financialInfoId);

        // Get all the personalInfoList where financialInfo equals to financialInfoId + 1
        defaultPersonalInfoShouldNotBeFound("financialInfoId.equals=" + (financialInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPersonalInfoShouldBeFound(String filter) throws Exception {
        restPersonalInfoMockMvc.perform(get("/api/personal-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].civility").value(hasItem(DEFAULT_CIVILITY.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].nativeCountry").value(hasItem(DEFAULT_NATIVE_COUNTRY)))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].clientABT").value(hasItem(DEFAULT_CLIENT_ABT.booleanValue())))
            .andExpect(jsonPath("$.[*].rib").value(hasItem(DEFAULT_RIB)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].secondNationality").value(hasItem(DEFAULT_SECOND_NATIONALITY)))
            .andExpect(jsonPath("$.[*].nbrkids").value(hasItem(DEFAULT_NBRKIDS)))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].americanIndex").value(hasItem(DEFAULT_AMERICAN_INDEX.booleanValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN)))
            .andExpect(jsonPath("$.[*].abroadResidancyProof").value(hasItem(DEFAULT_ABROAD_RESIDANCY_PROOF)))
            .andExpect(jsonPath("$.[*].abroadResidancyNumber").value(hasItem(DEFAULT_ABROAD_RESIDANCY_NUMBER)))
            .andExpect(jsonPath("$.[*].cinDeliveryDate").value(hasItem(DEFAULT_CIN_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].abroadResidancyDeliveryDate").value(hasItem(DEFAULT_ABROAD_RESIDANCY_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].abroadResidancyExpirationDate").value(hasItem(DEFAULT_ABROAD_RESIDANCY_EXPIRATION_DATE.toString())));

        // Check, that the count call also returns 1
        restPersonalInfoMockMvc.perform(get("/api/personal-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPersonalInfoShouldNotBeFound(String filter) throws Exception {
        restPersonalInfoMockMvc.perform(get("/api/personal-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPersonalInfoMockMvc.perform(get("/api/personal-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPersonalInfo() throws Exception {
        // Get the personalInfo
        restPersonalInfoMockMvc.perform(get("/api/personal-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonalInfo() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        int databaseSizeBeforeUpdate = personalInfoRepository.findAll().size();

        // Update the personalInfo
        PersonalInfo updatedPersonalInfo = personalInfoRepository.findById(personalInfo.getId()).get();
        // Disconnect from session so that the updates on updatedPersonalInfo are not directly saved in db
        em.detach(updatedPersonalInfo);
        updatedPersonalInfo
            .civility(UPDATED_CIVILITY)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .nativeCountry(UPDATED_NATIVE_COUNTRY)
            .birthday(UPDATED_BIRTHDAY)
            .clientABT(UPDATED_CLIENT_ABT)
            .rib(UPDATED_RIB)
            .secondNationality(UPDATED_SECOND_NATIONALITY)
            .nbrkids(UPDATED_NBRKIDS)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .phone(UPDATED_PHONE)
            .americanIndex(UPDATED_AMERICAN_INDEX)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .cin(UPDATED_CIN)
            .abroadResidancyProof(UPDATED_ABROAD_RESIDANCY_PROOF)
            .abroadResidancyNumber(UPDATED_ABROAD_RESIDANCY_NUMBER)
            .cinDeliveryDate(UPDATED_CIN_DELIVERY_DATE)
            .abroadResidancyDeliveryDate(UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE)
            .abroadResidancyExpirationDate(UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE);
        PersonalInfoDTO personalInfoDTO = personalInfoMapper.toDto(updatedPersonalInfo);

        restPersonalInfoMockMvc.perform(put("/api/personal-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalInfoDTO)))
            .andExpect(status().isOk());

        // Validate the PersonalInfo in the database
        List<PersonalInfo> personalInfoList = personalInfoRepository.findAll();
        assertThat(personalInfoList).hasSize(databaseSizeBeforeUpdate);
        PersonalInfo testPersonalInfo = personalInfoList.get(personalInfoList.size() - 1);
        assertThat(testPersonalInfo.getCivility()).isEqualTo(UPDATED_CIVILITY);
        assertThat(testPersonalInfo.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPersonalInfo.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPersonalInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPersonalInfo.getNativeCountry()).isEqualTo(UPDATED_NATIVE_COUNTRY);
        assertThat(testPersonalInfo.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testPersonalInfo.isClientABT()).isEqualTo(UPDATED_CLIENT_ABT);
        assertThat(testPersonalInfo.getRib()).isEqualTo(UPDATED_RIB);
        assertThat(testPersonalInfo.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testPersonalInfo.getSecondNationality()).isEqualTo(UPDATED_SECOND_NATIONALITY);
        assertThat(testPersonalInfo.getNbrkids()).isEqualTo(UPDATED_NBRKIDS);
        assertThat(testPersonalInfo.getMaritalStatus()).isEqualTo(UPDATED_MARITAL_STATUS);
        assertThat(testPersonalInfo.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPersonalInfo.isAmericanIndex()).isEqualTo(UPDATED_AMERICAN_INDEX);
        assertThat(testPersonalInfo.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testPersonalInfo.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testPersonalInfo.getAbroadResidancyProof()).isEqualTo(UPDATED_ABROAD_RESIDANCY_PROOF);
        assertThat(testPersonalInfo.getAbroadResidancyNumber()).isEqualTo(UPDATED_ABROAD_RESIDANCY_NUMBER);
        assertThat(testPersonalInfo.getCinDeliveryDate()).isEqualTo(UPDATED_CIN_DELIVERY_DATE);
        assertThat(testPersonalInfo.getAbroadResidancyDeliveryDate()).isEqualTo(UPDATED_ABROAD_RESIDANCY_DELIVERY_DATE);
        assertThat(testPersonalInfo.getAbroadResidancyExpirationDate()).isEqualTo(UPDATED_ABROAD_RESIDANCY_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonalInfo() throws Exception {
        int databaseSizeBeforeUpdate = personalInfoRepository.findAll().size();

        // Create the PersonalInfo
        PersonalInfoDTO personalInfoDTO = personalInfoMapper.toDto(personalInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonalInfoMockMvc.perform(put("/api/personal-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalInfo in the database
        List<PersonalInfo> personalInfoList = personalInfoRepository.findAll();
        assertThat(personalInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonalInfo() throws Exception {
        // Initialize the database
        personalInfoRepository.saveAndFlush(personalInfo);

        int databaseSizeBeforeDelete = personalInfoRepository.findAll().size();

        // Delete the personalInfo
        restPersonalInfoMockMvc.perform(delete("/api/personal-infos/{id}", personalInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonalInfo> personalInfoList = personalInfoRepository.findAll();
        assertThat(personalInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
