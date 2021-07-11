package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.SubcontractingStaff;
import com.attijari.repository.SubcontractingStaffRepository;
import com.attijari.service.SubcontractingStaffService;
import com.attijari.service.dto.SubcontractingStaffDTO;
import com.attijari.service.mapper.SubcontractingStaffMapper;
import com.attijari.service.SubcontractingStaffQueryService;

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
 * Integration tests for the {@link SubcontractingStaffResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SubcontractingStaffResourceIT {

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
    private SubcontractingStaffRepository subcontractingStaffRepository;

    @Autowired
    private SubcontractingStaffMapper subcontractingStaffMapper;

    @Autowired
    private SubcontractingStaffService subcontractingStaffService;

    @Autowired
    private SubcontractingStaffQueryService subcontractingStaffQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubcontractingStaffMockMvc;

    private SubcontractingStaff subcontractingStaff;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubcontractingStaff createEntity(EntityManager em) {
        SubcontractingStaff subcontractingStaff = new SubcontractingStaff()
            .matricule(DEFAULT_MATRICULE)
            .idBu(DEFAULT_ID_BU)
            .idRole(DEFAULT_ID_ROLE)
            .email(DEFAULT_EMAIL)
            .idPoste(DEFAULT_ID_POSTE);
        return subcontractingStaff;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubcontractingStaff createUpdatedEntity(EntityManager em) {
        SubcontractingStaff subcontractingStaff = new SubcontractingStaff()
            .matricule(UPDATED_MATRICULE)
            .idBu(UPDATED_ID_BU)
            .idRole(UPDATED_ID_ROLE)
            .email(UPDATED_EMAIL)
            .idPoste(UPDATED_ID_POSTE);
        return subcontractingStaff;
    }

    @BeforeEach
    public void initTest() {
        subcontractingStaff = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubcontractingStaff() throws Exception {
        int databaseSizeBeforeCreate = subcontractingStaffRepository.findAll().size();
        // Create the SubcontractingStaff
        SubcontractingStaffDTO subcontractingStaffDTO = subcontractingStaffMapper.toDto(subcontractingStaff);
        restSubcontractingStaffMockMvc.perform(post("/api/subcontracting-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subcontractingStaffDTO)))
            .andExpect(status().isCreated());

        // Validate the SubcontractingStaff in the database
        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeCreate + 1);
        SubcontractingStaff testSubcontractingStaff = subcontractingStaffList.get(subcontractingStaffList.size() - 1);
        assertThat(testSubcontractingStaff.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testSubcontractingStaff.getIdBu()).isEqualTo(DEFAULT_ID_BU);
        assertThat(testSubcontractingStaff.getIdRole()).isEqualTo(DEFAULT_ID_ROLE);
        assertThat(testSubcontractingStaff.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSubcontractingStaff.getIdPoste()).isEqualTo(DEFAULT_ID_POSTE);
    }

    @Test
    @Transactional
    public void createSubcontractingStaffWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subcontractingStaffRepository.findAll().size();

        // Create the SubcontractingStaff with an existing ID
        subcontractingStaff.setId(1L);
        SubcontractingStaffDTO subcontractingStaffDTO = subcontractingStaffMapper.toDto(subcontractingStaff);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubcontractingStaffMockMvc.perform(post("/api/subcontracting-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subcontractingStaffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubcontractingStaff in the database
        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMatriculeIsRequired() throws Exception {
        int databaseSizeBeforeTest = subcontractingStaffRepository.findAll().size();
        // set the field null
        subcontractingStaff.setMatricule(null);

        // Create the SubcontractingStaff, which fails.
        SubcontractingStaffDTO subcontractingStaffDTO = subcontractingStaffMapper.toDto(subcontractingStaff);


        restSubcontractingStaffMockMvc.perform(post("/api/subcontracting-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subcontractingStaffDTO)))
            .andExpect(status().isBadRequest());

        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdBuIsRequired() throws Exception {
        int databaseSizeBeforeTest = subcontractingStaffRepository.findAll().size();
        // set the field null
        subcontractingStaff.setIdBu(null);

        // Create the SubcontractingStaff, which fails.
        SubcontractingStaffDTO subcontractingStaffDTO = subcontractingStaffMapper.toDto(subcontractingStaff);


        restSubcontractingStaffMockMvc.perform(post("/api/subcontracting-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subcontractingStaffDTO)))
            .andExpect(status().isBadRequest());

        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = subcontractingStaffRepository.findAll().size();
        // set the field null
        subcontractingStaff.setIdRole(null);

        // Create the SubcontractingStaff, which fails.
        SubcontractingStaffDTO subcontractingStaffDTO = subcontractingStaffMapper.toDto(subcontractingStaff);


        restSubcontractingStaffMockMvc.perform(post("/api/subcontracting-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subcontractingStaffDTO)))
            .andExpect(status().isBadRequest());

        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffs() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList
        restSubcontractingStaffMockMvc.perform(get("/api/subcontracting-staffs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subcontractingStaff.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].idBu").value(hasItem(DEFAULT_ID_BU)))
            .andExpect(jsonPath("$.[*].idRole").value(hasItem(DEFAULT_ID_ROLE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].idPoste").value(hasItem(DEFAULT_ID_POSTE)));
    }

    @Test
    @Transactional
    public void getSubcontractingStaff() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get the subcontractingStaff
        restSubcontractingStaffMockMvc.perform(get("/api/subcontracting-staffs/{id}", subcontractingStaff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subcontractingStaff.getId().intValue()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.idBu").value(DEFAULT_ID_BU))
            .andExpect(jsonPath("$.idRole").value(DEFAULT_ID_ROLE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.idPoste").value(DEFAULT_ID_POSTE));
    }


    @Test
    @Transactional
    public void getSubcontractingStaffsByIdFiltering() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        Long id = subcontractingStaff.getId();

        defaultSubcontractingStaffShouldBeFound("id.equals=" + id);
        defaultSubcontractingStaffShouldNotBeFound("id.notEquals=" + id);

        defaultSubcontractingStaffShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSubcontractingStaffShouldNotBeFound("id.greaterThan=" + id);

        defaultSubcontractingStaffShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSubcontractingStaffShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule equals to DEFAULT_MATRICULE
        defaultSubcontractingStaffShouldBeFound("matricule.equals=" + DEFAULT_MATRICULE);

        // Get all the subcontractingStaffList where matricule equals to UPDATED_MATRICULE
        defaultSubcontractingStaffShouldNotBeFound("matricule.equals=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule not equals to DEFAULT_MATRICULE
        defaultSubcontractingStaffShouldNotBeFound("matricule.notEquals=" + DEFAULT_MATRICULE);

        // Get all the subcontractingStaffList where matricule not equals to UPDATED_MATRICULE
        defaultSubcontractingStaffShouldBeFound("matricule.notEquals=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsInShouldWork() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule in DEFAULT_MATRICULE or UPDATED_MATRICULE
        defaultSubcontractingStaffShouldBeFound("matricule.in=" + DEFAULT_MATRICULE + "," + UPDATED_MATRICULE);

        // Get all the subcontractingStaffList where matricule equals to UPDATED_MATRICULE
        defaultSubcontractingStaffShouldNotBeFound("matricule.in=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsNullOrNotNull() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule is not null
        defaultSubcontractingStaffShouldBeFound("matricule.specified=true");

        // Get all the subcontractingStaffList where matricule is null
        defaultSubcontractingStaffShouldNotBeFound("matricule.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule is greater than or equal to DEFAULT_MATRICULE
        defaultSubcontractingStaffShouldBeFound("matricule.greaterThanOrEqual=" + DEFAULT_MATRICULE);

        // Get all the subcontractingStaffList where matricule is greater than or equal to UPDATED_MATRICULE
        defaultSubcontractingStaffShouldNotBeFound("matricule.greaterThanOrEqual=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule is less than or equal to DEFAULT_MATRICULE
        defaultSubcontractingStaffShouldBeFound("matricule.lessThanOrEqual=" + DEFAULT_MATRICULE);

        // Get all the subcontractingStaffList where matricule is less than or equal to SMALLER_MATRICULE
        defaultSubcontractingStaffShouldNotBeFound("matricule.lessThanOrEqual=" + SMALLER_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsLessThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule is less than DEFAULT_MATRICULE
        defaultSubcontractingStaffShouldNotBeFound("matricule.lessThan=" + DEFAULT_MATRICULE);

        // Get all the subcontractingStaffList where matricule is less than UPDATED_MATRICULE
        defaultSubcontractingStaffShouldBeFound("matricule.lessThan=" + UPDATED_MATRICULE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByMatriculeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where matricule is greater than DEFAULT_MATRICULE
        defaultSubcontractingStaffShouldNotBeFound("matricule.greaterThan=" + DEFAULT_MATRICULE);

        // Get all the subcontractingStaffList where matricule is greater than SMALLER_MATRICULE
        defaultSubcontractingStaffShouldBeFound("matricule.greaterThan=" + SMALLER_MATRICULE);
    }


    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu equals to DEFAULT_ID_BU
        defaultSubcontractingStaffShouldBeFound("idBu.equals=" + DEFAULT_ID_BU);

        // Get all the subcontractingStaffList where idBu equals to UPDATED_ID_BU
        defaultSubcontractingStaffShouldNotBeFound("idBu.equals=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu not equals to DEFAULT_ID_BU
        defaultSubcontractingStaffShouldNotBeFound("idBu.notEquals=" + DEFAULT_ID_BU);

        // Get all the subcontractingStaffList where idBu not equals to UPDATED_ID_BU
        defaultSubcontractingStaffShouldBeFound("idBu.notEquals=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsInShouldWork() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu in DEFAULT_ID_BU or UPDATED_ID_BU
        defaultSubcontractingStaffShouldBeFound("idBu.in=" + DEFAULT_ID_BU + "," + UPDATED_ID_BU);

        // Get all the subcontractingStaffList where idBu equals to UPDATED_ID_BU
        defaultSubcontractingStaffShouldNotBeFound("idBu.in=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsNullOrNotNull() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu is not null
        defaultSubcontractingStaffShouldBeFound("idBu.specified=true");

        // Get all the subcontractingStaffList where idBu is null
        defaultSubcontractingStaffShouldNotBeFound("idBu.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu is greater than or equal to DEFAULT_ID_BU
        defaultSubcontractingStaffShouldBeFound("idBu.greaterThanOrEqual=" + DEFAULT_ID_BU);

        // Get all the subcontractingStaffList where idBu is greater than or equal to UPDATED_ID_BU
        defaultSubcontractingStaffShouldNotBeFound("idBu.greaterThanOrEqual=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu is less than or equal to DEFAULT_ID_BU
        defaultSubcontractingStaffShouldBeFound("idBu.lessThanOrEqual=" + DEFAULT_ID_BU);

        // Get all the subcontractingStaffList where idBu is less than or equal to SMALLER_ID_BU
        defaultSubcontractingStaffShouldNotBeFound("idBu.lessThanOrEqual=" + SMALLER_ID_BU);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsLessThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu is less than DEFAULT_ID_BU
        defaultSubcontractingStaffShouldNotBeFound("idBu.lessThan=" + DEFAULT_ID_BU);

        // Get all the subcontractingStaffList where idBu is less than UPDATED_ID_BU
        defaultSubcontractingStaffShouldBeFound("idBu.lessThan=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdBuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idBu is greater than DEFAULT_ID_BU
        defaultSubcontractingStaffShouldNotBeFound("idBu.greaterThan=" + DEFAULT_ID_BU);

        // Get all the subcontractingStaffList where idBu is greater than SMALLER_ID_BU
        defaultSubcontractingStaffShouldBeFound("idBu.greaterThan=" + SMALLER_ID_BU);
    }


    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole equals to DEFAULT_ID_ROLE
        defaultSubcontractingStaffShouldBeFound("idRole.equals=" + DEFAULT_ID_ROLE);

        // Get all the subcontractingStaffList where idRole equals to UPDATED_ID_ROLE
        defaultSubcontractingStaffShouldNotBeFound("idRole.equals=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole not equals to DEFAULT_ID_ROLE
        defaultSubcontractingStaffShouldNotBeFound("idRole.notEquals=" + DEFAULT_ID_ROLE);

        // Get all the subcontractingStaffList where idRole not equals to UPDATED_ID_ROLE
        defaultSubcontractingStaffShouldBeFound("idRole.notEquals=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsInShouldWork() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole in DEFAULT_ID_ROLE or UPDATED_ID_ROLE
        defaultSubcontractingStaffShouldBeFound("idRole.in=" + DEFAULT_ID_ROLE + "," + UPDATED_ID_ROLE);

        // Get all the subcontractingStaffList where idRole equals to UPDATED_ID_ROLE
        defaultSubcontractingStaffShouldNotBeFound("idRole.in=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsNullOrNotNull() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole is not null
        defaultSubcontractingStaffShouldBeFound("idRole.specified=true");

        // Get all the subcontractingStaffList where idRole is null
        defaultSubcontractingStaffShouldNotBeFound("idRole.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole is greater than or equal to DEFAULT_ID_ROLE
        defaultSubcontractingStaffShouldBeFound("idRole.greaterThanOrEqual=" + DEFAULT_ID_ROLE);

        // Get all the subcontractingStaffList where idRole is greater than or equal to UPDATED_ID_ROLE
        defaultSubcontractingStaffShouldNotBeFound("idRole.greaterThanOrEqual=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole is less than or equal to DEFAULT_ID_ROLE
        defaultSubcontractingStaffShouldBeFound("idRole.lessThanOrEqual=" + DEFAULT_ID_ROLE);

        // Get all the subcontractingStaffList where idRole is less than or equal to SMALLER_ID_ROLE
        defaultSubcontractingStaffShouldNotBeFound("idRole.lessThanOrEqual=" + SMALLER_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsLessThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole is less than DEFAULT_ID_ROLE
        defaultSubcontractingStaffShouldNotBeFound("idRole.lessThan=" + DEFAULT_ID_ROLE);

        // Get all the subcontractingStaffList where idRole is less than UPDATED_ID_ROLE
        defaultSubcontractingStaffShouldBeFound("idRole.lessThan=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdRoleIsGreaterThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idRole is greater than DEFAULT_ID_ROLE
        defaultSubcontractingStaffShouldNotBeFound("idRole.greaterThan=" + DEFAULT_ID_ROLE);

        // Get all the subcontractingStaffList where idRole is greater than SMALLER_ID_ROLE
        defaultSubcontractingStaffShouldBeFound("idRole.greaterThan=" + SMALLER_ID_ROLE);
    }


    @Test
    @Transactional
    public void getAllSubcontractingStaffsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where email equals to DEFAULT_EMAIL
        defaultSubcontractingStaffShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the subcontractingStaffList where email equals to UPDATED_EMAIL
        defaultSubcontractingStaffShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where email not equals to DEFAULT_EMAIL
        defaultSubcontractingStaffShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the subcontractingStaffList where email not equals to UPDATED_EMAIL
        defaultSubcontractingStaffShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultSubcontractingStaffShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the subcontractingStaffList where email equals to UPDATED_EMAIL
        defaultSubcontractingStaffShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where email is not null
        defaultSubcontractingStaffShouldBeFound("email.specified=true");

        // Get all the subcontractingStaffList where email is null
        defaultSubcontractingStaffShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllSubcontractingStaffsByEmailContainsSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where email contains DEFAULT_EMAIL
        defaultSubcontractingStaffShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the subcontractingStaffList where email contains UPDATED_EMAIL
        defaultSubcontractingStaffShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where email does not contain DEFAULT_EMAIL
        defaultSubcontractingStaffShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the subcontractingStaffList where email does not contain UPDATED_EMAIL
        defaultSubcontractingStaffShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste equals to DEFAULT_ID_POSTE
        defaultSubcontractingStaffShouldBeFound("idPoste.equals=" + DEFAULT_ID_POSTE);

        // Get all the subcontractingStaffList where idPoste equals to UPDATED_ID_POSTE
        defaultSubcontractingStaffShouldNotBeFound("idPoste.equals=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste not equals to DEFAULT_ID_POSTE
        defaultSubcontractingStaffShouldNotBeFound("idPoste.notEquals=" + DEFAULT_ID_POSTE);

        // Get all the subcontractingStaffList where idPoste not equals to UPDATED_ID_POSTE
        defaultSubcontractingStaffShouldBeFound("idPoste.notEquals=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsInShouldWork() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste in DEFAULT_ID_POSTE or UPDATED_ID_POSTE
        defaultSubcontractingStaffShouldBeFound("idPoste.in=" + DEFAULT_ID_POSTE + "," + UPDATED_ID_POSTE);

        // Get all the subcontractingStaffList where idPoste equals to UPDATED_ID_POSTE
        defaultSubcontractingStaffShouldNotBeFound("idPoste.in=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsNullOrNotNull() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste is not null
        defaultSubcontractingStaffShouldBeFound("idPoste.specified=true");

        // Get all the subcontractingStaffList where idPoste is null
        defaultSubcontractingStaffShouldNotBeFound("idPoste.specified=false");
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste is greater than or equal to DEFAULT_ID_POSTE
        defaultSubcontractingStaffShouldBeFound("idPoste.greaterThanOrEqual=" + DEFAULT_ID_POSTE);

        // Get all the subcontractingStaffList where idPoste is greater than or equal to UPDATED_ID_POSTE
        defaultSubcontractingStaffShouldNotBeFound("idPoste.greaterThanOrEqual=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste is less than or equal to DEFAULT_ID_POSTE
        defaultSubcontractingStaffShouldBeFound("idPoste.lessThanOrEqual=" + DEFAULT_ID_POSTE);

        // Get all the subcontractingStaffList where idPoste is less than or equal to SMALLER_ID_POSTE
        defaultSubcontractingStaffShouldNotBeFound("idPoste.lessThanOrEqual=" + SMALLER_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsLessThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste is less than DEFAULT_ID_POSTE
        defaultSubcontractingStaffShouldNotBeFound("idPoste.lessThan=" + DEFAULT_ID_POSTE);

        // Get all the subcontractingStaffList where idPoste is less than UPDATED_ID_POSTE
        defaultSubcontractingStaffShouldBeFound("idPoste.lessThan=" + UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void getAllSubcontractingStaffsByIdPosteIsGreaterThanSomething() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        // Get all the subcontractingStaffList where idPoste is greater than DEFAULT_ID_POSTE
        defaultSubcontractingStaffShouldNotBeFound("idPoste.greaterThan=" + DEFAULT_ID_POSTE);

        // Get all the subcontractingStaffList where idPoste is greater than SMALLER_ID_POSTE
        defaultSubcontractingStaffShouldBeFound("idPoste.greaterThan=" + SMALLER_ID_POSTE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSubcontractingStaffShouldBeFound(String filter) throws Exception {
        restSubcontractingStaffMockMvc.perform(get("/api/subcontracting-staffs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subcontractingStaff.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].idBu").value(hasItem(DEFAULT_ID_BU)))
            .andExpect(jsonPath("$.[*].idRole").value(hasItem(DEFAULT_ID_ROLE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].idPoste").value(hasItem(DEFAULT_ID_POSTE)));

        // Check, that the count call also returns 1
        restSubcontractingStaffMockMvc.perform(get("/api/subcontracting-staffs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSubcontractingStaffShouldNotBeFound(String filter) throws Exception {
        restSubcontractingStaffMockMvc.perform(get("/api/subcontracting-staffs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSubcontractingStaffMockMvc.perform(get("/api/subcontracting-staffs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingSubcontractingStaff() throws Exception {
        // Get the subcontractingStaff
        restSubcontractingStaffMockMvc.perform(get("/api/subcontracting-staffs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubcontractingStaff() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        int databaseSizeBeforeUpdate = subcontractingStaffRepository.findAll().size();

        // Update the subcontractingStaff
        SubcontractingStaff updatedSubcontractingStaff = subcontractingStaffRepository.findById(subcontractingStaff.getId()).get();
        // Disconnect from session so that the updates on updatedSubcontractingStaff are not directly saved in db
        em.detach(updatedSubcontractingStaff);
        updatedSubcontractingStaff
            .matricule(UPDATED_MATRICULE)
            .idBu(UPDATED_ID_BU)
            .idRole(UPDATED_ID_ROLE)
            .email(UPDATED_EMAIL)
            .idPoste(UPDATED_ID_POSTE);
        SubcontractingStaffDTO subcontractingStaffDTO = subcontractingStaffMapper.toDto(updatedSubcontractingStaff);

        restSubcontractingStaffMockMvc.perform(put("/api/subcontracting-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subcontractingStaffDTO)))
            .andExpect(status().isOk());

        // Validate the SubcontractingStaff in the database
        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeUpdate);
        SubcontractingStaff testSubcontractingStaff = subcontractingStaffList.get(subcontractingStaffList.size() - 1);
        assertThat(testSubcontractingStaff.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testSubcontractingStaff.getIdBu()).isEqualTo(UPDATED_ID_BU);
        assertThat(testSubcontractingStaff.getIdRole()).isEqualTo(UPDATED_ID_ROLE);
        assertThat(testSubcontractingStaff.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSubcontractingStaff.getIdPoste()).isEqualTo(UPDATED_ID_POSTE);
    }

    @Test
    @Transactional
    public void updateNonExistingSubcontractingStaff() throws Exception {
        int databaseSizeBeforeUpdate = subcontractingStaffRepository.findAll().size();

        // Create the SubcontractingStaff
        SubcontractingStaffDTO subcontractingStaffDTO = subcontractingStaffMapper.toDto(subcontractingStaff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubcontractingStaffMockMvc.perform(put("/api/subcontracting-staffs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subcontractingStaffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubcontractingStaff in the database
        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubcontractingStaff() throws Exception {
        // Initialize the database
        subcontractingStaffRepository.saveAndFlush(subcontractingStaff);

        int databaseSizeBeforeDelete = subcontractingStaffRepository.findAll().size();

        // Delete the subcontractingStaff
        restSubcontractingStaffMockMvc.perform(delete("/api/subcontracting-staffs/{id}", subcontractingStaff.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubcontractingStaff> subcontractingStaffList = subcontractingStaffRepository.findAll();
        assertThat(subcontractingStaffList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
