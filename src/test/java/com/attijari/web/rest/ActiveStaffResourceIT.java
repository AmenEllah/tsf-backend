package com.attijari.web.rest;

import com.attijari.service.dto.ActiveStaffDTO;
import com.attijari.TsfBackendApp;
import com.attijari.domain.ActiveStaff;
import com.attijari.repository.ActiveStaffRepository;
import com.attijari.service.ActiveStaffService;
import com.attijari.service.mapper.ActiveStaffMapper;
import com.attijari.service.ActiveStaffQueryService;

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

/**
 * Integration tests for the {@link ActiveStaffResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ActiveStaffResourceIT {

    private static final Integer DEFAULT_MATRICULE = 1;
    private static final Integer UPDATED_MATRICULE = 2;
    private static final Integer SMALLER_MATRICULE = 1 - 1;

    private static final Integer DEFAULT_ID_BU = 1;
    private static final Integer UPDATED_ID_BU = 2;
    private static final Integer SMALLER_ID_BU = 1 - 1;

    private static final Integer DEFAULT_ID_ROLE = 1;
    private static final Integer UPDATED_ID_ROLE = 2;
    private static final Integer SMALLER_ID_ROLE = 1 - 1;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_POSTE = 1;
    private static final Integer UPDATED_ID_POSTE = 2;
    private static final Integer SMALLER_ID_POSTE = 1 - 1;

    @Autowired
    private ActiveStaffRepository activeStaffRepository;

    @Autowired
    private ActiveStaffMapper activeStaffMapper;

    @Autowired
    private ActiveStaffService activeStaffService;

    @Autowired
    private ActiveStaffQueryService activeStaffQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restActiveStaffMockMvc;

    private ActiveStaff activeStaff;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActiveStaff createEntity(EntityManager em) {
        ActiveStaff activeStaff = new ActiveStaff()
            .matricule(DEFAULT_MATRICULE)
            .idBu(DEFAULT_ID_BU)
            .idRole(DEFAULT_ID_ROLE)
            .email(DEFAULT_EMAIL)
            .idPoste(DEFAULT_ID_POSTE);
        return activeStaff;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActiveStaff createUpdatedEntity(EntityManager em) {
        ActiveStaff activeStaff = new ActiveStaff()
            .matricule(UPDATED_MATRICULE)
            .idBu(UPDATED_ID_BU)
            .idRole(UPDATED_ID_ROLE)
            .email(UPDATED_EMAIL)
            .idPoste(UPDATED_ID_POSTE);
        return activeStaff;
    }

    @BeforeEach
    public void initTest() {
        activeStaff = createEntity(em);
    }

    @Test
    @Transactional
    public void createActiveStaff() throws Exception {
        int databaseSizeBeforeCreate = activeStaffRepository.findAll().size();
        // Create the ActiveStaff
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(activeStaff);
        restActiveStaffMockMvc.perform(post("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isCreated());

        // Validate the ActiveStaff in the database
        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeCreate + 1);
        ActiveStaff testActiveStaff = activeStaffList.get(activeStaffList.size() - 1);
        assertThat(testActiveStaff.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testActiveStaff.getIdBu()).isEqualTo(DEFAULT_ID_BU);
        assertThat(testActiveStaff.getIdRole()).isEqualTo(DEFAULT_ID_ROLE);
        assertThat(testActiveStaff.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testActiveStaff.getIdPoste()).isEqualTo(DEFAULT_ID_POSTE);
    }

    @Test
    @Transactional
    public void createActiveStaffWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activeStaffRepository.findAll().size();

        // Create the ActiveStaff with an existing ID
        activeStaff.setMatricule(1);
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(activeStaff);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActiveStaffMockMvc.perform(post("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActiveStaff in the database
        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMatriculeIsRequired() throws Exception {
        int databaseSizeBeforeTest = activeStaffRepository.findAll().size();
        // set the field null
        activeStaff.setMatricule(null);

        // Create the ActiveStaff, which fails.
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(activeStaff);


        restActiveStaffMockMvc.perform(post("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isBadRequest());

        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdBuIsRequired() throws Exception {
        int databaseSizeBeforeTest = activeStaffRepository.findAll().size();
        // set the field null
        activeStaff.setIdBu(null);

        // Create the ActiveStaff, which fails.
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(activeStaff);


        restActiveStaffMockMvc.perform(post("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isBadRequest());

        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = activeStaffRepository.findAll().size();
        // set the field null
        activeStaff.setIdRole(null);

        // Create the ActiveStaff, which fails.
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(activeStaff);


        restActiveStaffMockMvc.perform(post("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isBadRequest());

        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdPosteIsRequired() throws Exception {
        int databaseSizeBeforeTest = activeStaffRepository.findAll().size();
        // set the field null
        activeStaff.setIdPoste(null);

        // Create the ActiveStaff, which fails.
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(activeStaff);


        restActiveStaffMockMvc.perform(post("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isBadRequest());

        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllActiveStaffs() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList
        restActiveStaffMockMvc.perform(get("/api/active-staffs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].idBu").value(hasItem(DEFAULT_ID_BU)))
            .andExpect(jsonPath("$.[*].idRole").value(hasItem(DEFAULT_ID_ROLE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].idPoste").value(hasItem(DEFAULT_ID_POSTE)));
    }

    @Test
    @Transactional
    public void getActiveStaff() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get the activeStaff
        restActiveStaffMockMvc.perform(get("/api/active-staffs/{id}", activeStaff.getMatricule()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.idBu").value(DEFAULT_ID_BU))
            .andExpect(jsonPath("$.idRole").value(DEFAULT_ID_ROLE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.idPoste").value(DEFAULT_ID_POSTE));
    }


    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule equals to DEFAULT_MATRICULE
        defaultActiveStaffShouldBeFound("matricule.equals=" + DEFAULT_MATRICULE);

        // Get all the activeStaffList where matricule equals to UPDATED_MATRICULE
        defaultActiveStaffShouldNotBeFound("matricule.equals=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule not equals to DEFAULT_MATRICULE
        defaultActiveStaffShouldNotBeFound("matricule.notEquals=" + DEFAULT_MATRICULE);

        // Get all the activeStaffList where matricule not equals to UPDATED_MATRICULE
        defaultActiveStaffShouldBeFound("matricule.notEquals=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsInShouldWork() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule in DEFAULT_MATRICULE or UPDATED_MATRICULE
        defaultActiveStaffShouldBeFound("matricule.in=" + DEFAULT_MATRICULE + "," + UPDATED_MATRICULE);

        // Get all the activeStaffList where matricule equals to UPDATED_MATRICULE
        defaultActiveStaffShouldNotBeFound("matricule.in=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsNullOrNotNull() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule is not null
        defaultActiveStaffShouldBeFound("matricule.specified=true");

        // Get all the activeStaffList where matricule is null
        defaultActiveStaffShouldNotBeFound("matricule.specified=false");
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule is greater than or equal to DEFAULT_MATRICULE
        defaultActiveStaffShouldBeFound("matricule.greaterThanOrEqual=" + DEFAULT_MATRICULE);

        // Get all the activeStaffList where matricule is greater than or equal to UPDATED_MATRICULE
        defaultActiveStaffShouldNotBeFound("matricule.greaterThanOrEqual=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule is less than or equal to DEFAULT_MATRICULE
        defaultActiveStaffShouldBeFound("matricule.lessThanOrEqual=" + DEFAULT_MATRICULE);

        // Get all the activeStaffList where matricule is less than or equal to SMALLER_MATRICULE
        defaultActiveStaffShouldNotBeFound("matricule.lessThanOrEqual=" + SMALLER_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsLessThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule is less than DEFAULT_MATRICULE
        defaultActiveStaffShouldNotBeFound("matricule.lessThan=" + DEFAULT_MATRICULE);

        // Get all the activeStaffList where matricule is less than UPDATED_MATRICULE
        defaultActiveStaffShouldBeFound("matricule.lessThan=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByMatriculeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where matricule is greater than DEFAULT_MATRICULE
        defaultActiveStaffShouldNotBeFound("matricule.greaterThan=" + DEFAULT_MATRICULE);

        // Get all the activeStaffList where matricule is greater than SMALLER_MATRICULE
        defaultActiveStaffShouldBeFound("matricule.greaterThan=" + SMALLER_MATRICULE);
    }


    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu equals to DEFAULT_ID_BU
        defaultActiveStaffShouldBeFound("idBu.equals=" + DEFAULT_ID_BU);

        // Get all the activeStaffList where idBu equals to UPDATED_ID_BU
        defaultActiveStaffShouldNotBeFound("idBu.equals=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu not equals to DEFAULT_ID_BU
        defaultActiveStaffShouldNotBeFound("idBu.notEquals=" + DEFAULT_ID_BU);

        // Get all the activeStaffList where idBu not equals to UPDATED_ID_BU
        defaultActiveStaffShouldBeFound("idBu.notEquals=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsInShouldWork() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu in DEFAULT_ID_BU or UPDATED_ID_BU
        defaultActiveStaffShouldBeFound("idBu.in=" + DEFAULT_ID_BU + "," + UPDATED_ID_BU);

        // Get all the activeStaffList where idBu equals to UPDATED_ID_BU
        defaultActiveStaffShouldNotBeFound("idBu.in=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsNullOrNotNull() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu is not null
        defaultActiveStaffShouldBeFound("idBu.specified=true");

        // Get all the activeStaffList where idBu is null
        defaultActiveStaffShouldNotBeFound("idBu.specified=false");
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu is greater than or equal to DEFAULT_ID_BU
        defaultActiveStaffShouldBeFound("idBu.greaterThanOrEqual=" + DEFAULT_ID_BU);

        // Get all the activeStaffList where idBu is greater than or equal to UPDATED_ID_BU
        defaultActiveStaffShouldNotBeFound("idBu.greaterThanOrEqual=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu is less than or equal to DEFAULT_ID_BU
        defaultActiveStaffShouldBeFound("idBu.lessThanOrEqual=" + DEFAULT_ID_BU);

        // Get all the activeStaffList where idBu is less than or equal to SMALLER_ID_BU
        defaultActiveStaffShouldNotBeFound("idBu.lessThanOrEqual=" + SMALLER_ID_BU);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsLessThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu is less than DEFAULT_ID_BU
        defaultActiveStaffShouldNotBeFound("idBu.lessThan=" + DEFAULT_ID_BU);

        // Get all the activeStaffList where idBu is less than UPDATED_ID_BU
        defaultActiveStaffShouldBeFound("idBu.lessThan=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdBuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idBu is greater than DEFAULT_ID_BU
        defaultActiveStaffShouldNotBeFound("idBu.greaterThan=" + DEFAULT_ID_BU);

        // Get all the activeStaffList where idBu is greater than SMALLER_ID_BU
        defaultActiveStaffShouldBeFound("idBu.greaterThan=" + SMALLER_ID_BU);
    }


    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole equals to DEFAULT_ID_ROLE
        defaultActiveStaffShouldBeFound("idRole.equals=" + DEFAULT_ID_ROLE);

        // Get all the activeStaffList where idRole equals to UPDATED_ID_ROLE
        defaultActiveStaffShouldNotBeFound("idRole.equals=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole not equals to DEFAULT_ID_ROLE
        defaultActiveStaffShouldNotBeFound("idRole.notEquals=" + DEFAULT_ID_ROLE);

        // Get all the activeStaffList where idRole not equals to UPDATED_ID_ROLE
        defaultActiveStaffShouldBeFound("idRole.notEquals=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsInShouldWork() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole in DEFAULT_ID_ROLE or UPDATED_ID_ROLE
        defaultActiveStaffShouldBeFound("idRole.in=" + DEFAULT_ID_ROLE + "," + UPDATED_ID_ROLE);

        // Get all the activeStaffList where idRole equals to UPDATED_ID_ROLE
        defaultActiveStaffShouldNotBeFound("idRole.in=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsNullOrNotNull() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole is not null
        defaultActiveStaffShouldBeFound("idRole.specified=true");

        // Get all the activeStaffList where idRole is null
        defaultActiveStaffShouldNotBeFound("idRole.specified=false");
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole is greater than or equal to DEFAULT_ID_ROLE
        defaultActiveStaffShouldBeFound("idRole.greaterThanOrEqual=" + DEFAULT_ID_ROLE);

        // Get all the activeStaffList where idRole is greater than or equal to UPDATED_ID_ROLE
        defaultActiveStaffShouldNotBeFound("idRole.greaterThanOrEqual=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole is less than or equal to DEFAULT_ID_ROLE
        defaultActiveStaffShouldBeFound("idRole.lessThanOrEqual=" + DEFAULT_ID_ROLE);

        // Get all the activeStaffList where idRole is less than or equal to SMALLER_ID_ROLE
        defaultActiveStaffShouldNotBeFound("idRole.lessThanOrEqual=" + SMALLER_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsLessThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole is less than DEFAULT_ID_ROLE
        defaultActiveStaffShouldNotBeFound("idRole.lessThan=" + DEFAULT_ID_ROLE);

        // Get all the activeStaffList where idRole is less than UPDATED_ID_ROLE
        defaultActiveStaffShouldBeFound("idRole.lessThan=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdRoleIsGreaterThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idRole is greater than DEFAULT_ID_ROLE
        defaultActiveStaffShouldNotBeFound("idRole.greaterThan=" + DEFAULT_ID_ROLE);

        // Get all the activeStaffList where idRole is greater than SMALLER_ID_ROLE
        defaultActiveStaffShouldBeFound("idRole.greaterThan=" + SMALLER_ID_ROLE);
    }


    @Test
    @Transactional
    public void getAllActiveStaffsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where email equals to DEFAULT_EMAIL
        defaultActiveStaffShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the activeStaffList where email equals to UPDATED_EMAIL
        defaultActiveStaffShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where email not equals to DEFAULT_EMAIL
        defaultActiveStaffShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the activeStaffList where email not equals to UPDATED_EMAIL
        defaultActiveStaffShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultActiveStaffShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the activeStaffList where email equals to UPDATED_EMAIL
        defaultActiveStaffShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where email is not null
        defaultActiveStaffShouldBeFound("email.specified=true");

        // Get all the activeStaffList where email is null
        defaultActiveStaffShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllActiveStaffsByEmailContainsSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where email contains DEFAULT_EMAIL
        defaultActiveStaffShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the activeStaffList where email contains UPDATED_EMAIL
        defaultActiveStaffShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where email does not contain DEFAULT_EMAIL
        defaultActiveStaffShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the activeStaffList where email does not contain UPDATED_EMAIL
        defaultActiveStaffShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste equals to DEFAULT_ID_POSTE
        defaultActiveStaffShouldBeFound("idPoste.equals=" + DEFAULT_ID_POSTE);

        // Get all the activeStaffList where idPoste equals to UPDATED_ID_POSTE
        defaultActiveStaffShouldNotBeFound("idPoste.equals=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste not equals to DEFAULT_ID_POSTE
        defaultActiveStaffShouldNotBeFound("idPoste.notEquals=" + DEFAULT_ID_POSTE);

        // Get all the activeStaffList where idPoste not equals to UPDATED_ID_POSTE
        defaultActiveStaffShouldBeFound("idPoste.notEquals=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsInShouldWork() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste in DEFAULT_ID_POSTE or UPDATED_ID_POSTE
        defaultActiveStaffShouldBeFound("idPoste.in=" + DEFAULT_ID_POSTE + "," + UPDATED_ID_POSTE);

        // Get all the activeStaffList where idPoste equals to UPDATED_ID_POSTE
        defaultActiveStaffShouldNotBeFound("idPoste.in=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsNullOrNotNull() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste is not null
        defaultActiveStaffShouldBeFound("idPoste.specified=true");

        // Get all the activeStaffList where idPoste is null
        defaultActiveStaffShouldNotBeFound("idPoste.specified=false");
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste is greater than or equal to DEFAULT_ID_POSTE
        defaultActiveStaffShouldBeFound("idPoste.greaterThanOrEqual=" + DEFAULT_ID_POSTE);

        // Get all the activeStaffList where idPoste is greater than or equal to UPDATED_ID_POSTE
        defaultActiveStaffShouldNotBeFound("idPoste.greaterThanOrEqual=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste is less than or equal to DEFAULT_ID_POSTE
        defaultActiveStaffShouldBeFound("idPoste.lessThanOrEqual=" + DEFAULT_ID_POSTE);

        // Get all the activeStaffList where idPoste is less than or equal to SMALLER_ID_POSTE
        defaultActiveStaffShouldNotBeFound("idPoste.lessThanOrEqual=" + SMALLER_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsLessThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste is less than DEFAULT_ID_POSTE
        defaultActiveStaffShouldNotBeFound("idPoste.lessThan=" + DEFAULT_ID_POSTE);

        // Get all the activeStaffList where idPoste is less than UPDATED_ID_POSTE
        defaultActiveStaffShouldBeFound("idPoste.lessThan=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllActiveStaffsByIdPosteIsGreaterThanSomething() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        // Get all the activeStaffList where idPoste is greater than DEFAULT_ID_POSTE
        defaultActiveStaffShouldNotBeFound("idPoste.greaterThan=" + DEFAULT_ID_POSTE);

        // Get all the activeStaffList where idPoste is greater than SMALLER_ID_POSTE
        defaultActiveStaffShouldBeFound("idPoste.greaterThan=" + SMALLER_ID_POSTE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultActiveStaffShouldBeFound(String filter) throws Exception {
        restActiveStaffMockMvc.perform(get("/api/active-staffs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].idBu").value(hasItem(DEFAULT_ID_BU)))
            .andExpect(jsonPath("$.[*].idRole").value(hasItem(DEFAULT_ID_ROLE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].idPoste").value(hasItem(DEFAULT_ID_POSTE)));

        // Check, that the count call also returns 1
        restActiveStaffMockMvc.perform(get("/api/active-staffs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultActiveStaffShouldNotBeFound(String filter) throws Exception {
        restActiveStaffMockMvc.perform(get("/api/active-staffs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restActiveStaffMockMvc.perform(get("/api/active-staffs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingActiveStaff() throws Exception {
        // Get the activeStaff
        restActiveStaffMockMvc.perform(get("/api/active-staffs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActiveStaff() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        int databaseSizeBeforeUpdate = activeStaffRepository.findAll().size();

        // Update the activeStaff
        ActiveStaff updatedActiveStaff = activeStaffRepository.findById(activeStaff.getMatricule()).get();
        // Disconnect from session so that the updates on updatedActiveStaff are not directly saved in db
        em.detach(updatedActiveStaff);
        updatedActiveStaff
            .matricule(UPDATED_MATRICULE)
            .idBu(UPDATED_ID_BU)
            .idRole(UPDATED_ID_ROLE)
            .email(UPDATED_EMAIL)
            .idPoste(UPDATED_ID_POSTE);
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(updatedActiveStaff);

        restActiveStaffMockMvc.perform(put("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isOk());

        // Validate the ActiveStaff in the database
        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeUpdate);
        ActiveStaff testActiveStaff = activeStaffList.get(activeStaffList.size() - 1);
        assertThat(testActiveStaff.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testActiveStaff.getIdBu()).isEqualTo(UPDATED_ID_BU);
        assertThat(testActiveStaff.getIdRole()).isEqualTo(UPDATED_ID_ROLE);
        assertThat(testActiveStaff.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testActiveStaff.getIdPoste()).isEqualTo(UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void updateNonExistingActiveStaff() throws Exception {
        int databaseSizeBeforeUpdate = activeStaffRepository.findAll().size();

        // Create the ActiveStaff
        ActiveStaffDTO activeStaffDTO = activeStaffMapper.toDto(activeStaff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActiveStaffMockMvc.perform(put("/api/active-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activeStaffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActiveStaff in the database
        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActiveStaff() throws Exception {
        // Initialize the database
        activeStaffRepository.saveAndFlush(activeStaff);

        int databaseSizeBeforeDelete = activeStaffRepository.findAll().size();

        // Delete the activeStaff
        restActiveStaffMockMvc.perform(delete("/api/active-staffs/{id}", activeStaff.getMatricule())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ActiveStaff> activeStaffList = activeStaffRepository.findAll();
        assertThat(activeStaffList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
