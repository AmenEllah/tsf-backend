package com.attijari.web.rest;

import com.attijari.domain.enumeration.ActionPermission;
import com.attijari.domain.enumeration.ScopePermission;
import com.attijari.domain.enumeration.StaffType;
import com.attijari.service.dto.StaffPermissionDTO;
import com.attijari.TsfBackendApp;
import com.attijari.domain.StaffPermission;
import com.attijari.repository.StaffPermissionRepository;
import com.attijari.service.StaffPermissionService;
import com.attijari.service.mapper.StaffPermissionMapper;
import com.attijari.service.StaffPermissionQueryService;

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
 * Integration tests for the {@link StaffPermissionResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StaffPermissionResourceIT {

    private static final Integer DEFAULT_ID_ROLE = 1;
    private static final Integer UPDATED_ID_ROLE = 2;
    private static final Integer SMALLER_ID_ROLE = 1 - 1;

    private static final Integer DEFAULT_ID_BU = 1;
    private static final Integer UPDATED_ID_BU = 2;
    private static final Integer SMALLER_ID_BU = 1 - 1;

    private static final ActionPermission DEFAULT_ACTION = ActionPermission.MODIFYING;
    private static final ActionPermission UPDATED_ACTION = ActionPermission.READING;

    private static final ScopePermission DEFAULT_SCOPE_PERMISSION = ScopePermission.ALL;
    private static final ScopePermission UPDATED_SCOPE_PERMISSION = ScopePermission.IN_AGENCY;

    private static final StaffType DEFAULT_STAFF_TYPE = StaffType.ADVISOR_UNIT;
    private static final StaffType UPDATED_STAFF_TYPE = StaffType.MARKET_UNIT;

    @Autowired
    private StaffPermissionRepository staffPermissionRepository;

    @Autowired
    private StaffPermissionMapper staffPermissionMapper;

    @Autowired
    private StaffPermissionService staffPermissionService;

    @Autowired
    private StaffPermissionQueryService staffPermissionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStaffPermissionMockMvc;

    private StaffPermission staffPermission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StaffPermission createEntity(EntityManager em) {
        StaffPermission staffPermission = new StaffPermission()
            .idRole(DEFAULT_ID_ROLE)
            .idBu(DEFAULT_ID_BU)
            .action(DEFAULT_ACTION)
            .scopePermission(DEFAULT_SCOPE_PERMISSION)
            .staffType(DEFAULT_STAFF_TYPE);
        return staffPermission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StaffPermission createUpdatedEntity(EntityManager em) {
        StaffPermission staffPermission = new StaffPermission()
            .idRole(UPDATED_ID_ROLE)
            .idBu(UPDATED_ID_BU)
            .action(UPDATED_ACTION)
            .scopePermission(UPDATED_SCOPE_PERMISSION)
            .staffType(UPDATED_STAFF_TYPE);
        return staffPermission;
    }

    @BeforeEach
    public void initTest() {
        staffPermission = createEntity(em);
    }

    @Test
    @Transactional
    public void createStaffPermission() throws Exception {
        int databaseSizeBeforeCreate = staffPermissionRepository.findAll().size();
        // Create the StaffPermission
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(staffPermission);
        restStaffPermissionMockMvc.perform(post("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isCreated());

        // Validate the StaffPermission in the database
        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeCreate + 1);
        StaffPermission testStaffPermission = staffPermissionList.get(staffPermissionList.size() - 1);
        assertThat(testStaffPermission.getIdRole()).isEqualTo(DEFAULT_ID_ROLE);
        assertThat(testStaffPermission.getIdBu()).isEqualTo(DEFAULT_ID_BU);
        assertThat(testStaffPermission.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testStaffPermission.getScopePermission()).isEqualTo(DEFAULT_SCOPE_PERMISSION);
        assertThat(testStaffPermission.getStaffType()).isEqualTo(DEFAULT_STAFF_TYPE);
    }

    @Test
    @Transactional
    public void createStaffPermissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = staffPermissionRepository.findAll().size();

        // Create the StaffPermission with an existing ID
        staffPermission.setId(1L);
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(staffPermission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStaffPermissionMockMvc.perform(post("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StaffPermission in the database
        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffPermissionRepository.findAll().size();
        // set the field null
        staffPermission.setIdRole(null);

        // Create the StaffPermission, which fails.
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(staffPermission);


        restStaffPermissionMockMvc.perform(post("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isBadRequest());

        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdBuIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffPermissionRepository.findAll().size();
        // set the field null
        staffPermission.setIdBu(null);

        // Create the StaffPermission, which fails.
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(staffPermission);


        restStaffPermissionMockMvc.perform(post("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isBadRequest());

        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffPermissionRepository.findAll().size();
        // set the field null
        staffPermission.setAction(null);

        // Create the StaffPermission, which fails.
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(staffPermission);


        restStaffPermissionMockMvc.perform(post("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isBadRequest());

        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScopePermissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffPermissionRepository.findAll().size();
        // set the field null
        staffPermission.setScopePermission(null);

        // Create the StaffPermission, which fails.
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(staffPermission);


        restStaffPermissionMockMvc.perform(post("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isBadRequest());

        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStaffPermissions() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList
        restStaffPermissionMockMvc.perform(get("/api/staff-permissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staffPermission.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRole").value(hasItem(DEFAULT_ID_ROLE)))
            .andExpect(jsonPath("$.[*].idBu").value(hasItem(DEFAULT_ID_BU)))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION.toString())))
            .andExpect(jsonPath("$.[*].scopePermission").value(hasItem(DEFAULT_SCOPE_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getStaffPermission() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get the staffPermission
        restStaffPermissionMockMvc.perform(get("/api/staff-permissions/{id}", staffPermission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(staffPermission.getId().intValue()))
            .andExpect(jsonPath("$.idRole").value(DEFAULT_ID_ROLE))
            .andExpect(jsonPath("$.idBu").value(DEFAULT_ID_BU))
            .andExpect(jsonPath("$.action").value(DEFAULT_ACTION.toString()))
            .andExpect(jsonPath("$.scopePermission").value(DEFAULT_SCOPE_PERMISSION.toString()))
            .andExpect(jsonPath("$.staffType").value(DEFAULT_STAFF_TYPE.toString()));
    }


    @Test
    @Transactional
    public void getStaffPermissionsByIdFiltering() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        Long id = staffPermission.getId();

        defaultStaffPermissionShouldBeFound("id.equals=" + id);
        defaultStaffPermissionShouldNotBeFound("id.notEquals=" + id);

        defaultStaffPermissionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultStaffPermissionShouldNotBeFound("id.greaterThan=" + id);

        defaultStaffPermissionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultStaffPermissionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole equals to DEFAULT_ID_ROLE
        defaultStaffPermissionShouldBeFound("idRole.equals=" + DEFAULT_ID_ROLE);

        // Get all the staffPermissionList where idRole equals to UPDATED_ID_ROLE
        defaultStaffPermissionShouldNotBeFound("idRole.equals=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole not equals to DEFAULT_ID_ROLE
        defaultStaffPermissionShouldNotBeFound("idRole.notEquals=" + DEFAULT_ID_ROLE);

        // Get all the staffPermissionList where idRole not equals to UPDATED_ID_ROLE
        defaultStaffPermissionShouldBeFound("idRole.notEquals=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsInShouldWork() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole in DEFAULT_ID_ROLE or UPDATED_ID_ROLE
        defaultStaffPermissionShouldBeFound("idRole.in=" + DEFAULT_ID_ROLE + "," + UPDATED_ID_ROLE);

        // Get all the staffPermissionList where idRole equals to UPDATED_ID_ROLE
        defaultStaffPermissionShouldNotBeFound("idRole.in=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsNullOrNotNull() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole is not null
        defaultStaffPermissionShouldBeFound("idRole.specified=true");

        // Get all the staffPermissionList where idRole is null
        defaultStaffPermissionShouldNotBeFound("idRole.specified=false");
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole is greater than or equal to DEFAULT_ID_ROLE
        defaultStaffPermissionShouldBeFound("idRole.greaterThanOrEqual=" + DEFAULT_ID_ROLE);

        // Get all the staffPermissionList where idRole is greater than or equal to UPDATED_ID_ROLE
        defaultStaffPermissionShouldNotBeFound("idRole.greaterThanOrEqual=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole is less than or equal to DEFAULT_ID_ROLE
        defaultStaffPermissionShouldBeFound("idRole.lessThanOrEqual=" + DEFAULT_ID_ROLE);

        // Get all the staffPermissionList where idRole is less than or equal to SMALLER_ID_ROLE
        defaultStaffPermissionShouldNotBeFound("idRole.lessThanOrEqual=" + SMALLER_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsLessThanSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole is less than DEFAULT_ID_ROLE
        defaultStaffPermissionShouldNotBeFound("idRole.lessThan=" + DEFAULT_ID_ROLE);

        // Get all the staffPermissionList where idRole is less than UPDATED_ID_ROLE
        defaultStaffPermissionShouldBeFound("idRole.lessThan=" + UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdRoleIsGreaterThanSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idRole is greater than DEFAULT_ID_ROLE
        defaultStaffPermissionShouldNotBeFound("idRole.greaterThan=" + DEFAULT_ID_ROLE);

        // Get all the staffPermissionList where idRole is greater than SMALLER_ID_ROLE
        defaultStaffPermissionShouldBeFound("idRole.greaterThan=" + SMALLER_ID_ROLE);
    }


    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu equals to DEFAULT_ID_BU
        defaultStaffPermissionShouldBeFound("idBu.equals=" + DEFAULT_ID_BU);

        // Get all the staffPermissionList where idBu equals to UPDATED_ID_BU
        defaultStaffPermissionShouldNotBeFound("idBu.equals=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu not equals to DEFAULT_ID_BU
        defaultStaffPermissionShouldNotBeFound("idBu.notEquals=" + DEFAULT_ID_BU);

        // Get all the staffPermissionList where idBu not equals to UPDATED_ID_BU
        defaultStaffPermissionShouldBeFound("idBu.notEquals=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsInShouldWork() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu in DEFAULT_ID_BU or UPDATED_ID_BU
        defaultStaffPermissionShouldBeFound("idBu.in=" + DEFAULT_ID_BU + "," + UPDATED_ID_BU);

        // Get all the staffPermissionList where idBu equals to UPDATED_ID_BU
        defaultStaffPermissionShouldNotBeFound("idBu.in=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsNullOrNotNull() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu is not null
        defaultStaffPermissionShouldBeFound("idBu.specified=true");

        // Get all the staffPermissionList where idBu is null
        defaultStaffPermissionShouldNotBeFound("idBu.specified=false");
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu is greater than or equal to DEFAULT_ID_BU
        defaultStaffPermissionShouldBeFound("idBu.greaterThanOrEqual=" + DEFAULT_ID_BU);

        // Get all the staffPermissionList where idBu is greater than or equal to UPDATED_ID_BU
        defaultStaffPermissionShouldNotBeFound("idBu.greaterThanOrEqual=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu is less than or equal to DEFAULT_ID_BU
        defaultStaffPermissionShouldBeFound("idBu.lessThanOrEqual=" + DEFAULT_ID_BU);

        // Get all the staffPermissionList where idBu is less than or equal to SMALLER_ID_BU
        defaultStaffPermissionShouldNotBeFound("idBu.lessThanOrEqual=" + SMALLER_ID_BU);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsLessThanSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu is less than DEFAULT_ID_BU
        defaultStaffPermissionShouldNotBeFound("idBu.lessThan=" + DEFAULT_ID_BU);

        // Get all the staffPermissionList where idBu is less than UPDATED_ID_BU
        defaultStaffPermissionShouldBeFound("idBu.lessThan=" + UPDATED_ID_BU);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByIdBuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where idBu is greater than DEFAULT_ID_BU
        defaultStaffPermissionShouldNotBeFound("idBu.greaterThan=" + DEFAULT_ID_BU);

        // Get all the staffPermissionList where idBu is greater than SMALLER_ID_BU
        defaultStaffPermissionShouldBeFound("idBu.greaterThan=" + SMALLER_ID_BU);
    }


    @Test
    @Transactional
    public void getAllStaffPermissionsByActionIsEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where action equals to DEFAULT_ACTION
        defaultStaffPermissionShouldBeFound("action.equals=" + DEFAULT_ACTION);

        // Get all the staffPermissionList where action equals to UPDATED_ACTION
        defaultStaffPermissionShouldNotBeFound("action.equals=" + UPDATED_ACTION);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByActionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where action not equals to DEFAULT_ACTION
        defaultStaffPermissionShouldNotBeFound("action.notEquals=" + DEFAULT_ACTION);

        // Get all the staffPermissionList where action not equals to UPDATED_ACTION
        defaultStaffPermissionShouldBeFound("action.notEquals=" + UPDATED_ACTION);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByActionIsInShouldWork() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where action in DEFAULT_ACTION or UPDATED_ACTION
        defaultStaffPermissionShouldBeFound("action.in=" + DEFAULT_ACTION + "," + UPDATED_ACTION);

        // Get all the staffPermissionList where action equals to UPDATED_ACTION
        defaultStaffPermissionShouldNotBeFound("action.in=" + UPDATED_ACTION);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByActionIsNullOrNotNull() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where action is not null
        defaultStaffPermissionShouldBeFound("action.specified=true");

        // Get all the staffPermissionList where action is null
        defaultStaffPermissionShouldNotBeFound("action.specified=false");
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByScopePermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where scopePermission equals to DEFAULT_SCOPE_PERMISSION
        defaultStaffPermissionShouldBeFound("scopePermission.equals=" + DEFAULT_SCOPE_PERMISSION);

        // Get all the staffPermissionList where scopePermission equals to UPDATED_SCOPE_PERMISSION
        defaultStaffPermissionShouldNotBeFound("scopePermission.equals=" + UPDATED_SCOPE_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByScopePermissionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where scopePermission not equals to DEFAULT_SCOPE_PERMISSION
        defaultStaffPermissionShouldNotBeFound("scopePermission.notEquals=" + DEFAULT_SCOPE_PERMISSION);

        // Get all the staffPermissionList where scopePermission not equals to UPDATED_SCOPE_PERMISSION
        defaultStaffPermissionShouldBeFound("scopePermission.notEquals=" + UPDATED_SCOPE_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByScopePermissionIsInShouldWork() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where scopePermission in DEFAULT_SCOPE_PERMISSION or UPDATED_SCOPE_PERMISSION
        defaultStaffPermissionShouldBeFound("scopePermission.in=" + DEFAULT_SCOPE_PERMISSION + "," + UPDATED_SCOPE_PERMISSION);

        // Get all the staffPermissionList where scopePermission equals to UPDATED_SCOPE_PERMISSION
        defaultStaffPermissionShouldNotBeFound("scopePermission.in=" + UPDATED_SCOPE_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByScopePermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where scopePermission is not null
        defaultStaffPermissionShouldBeFound("scopePermission.specified=true");

        // Get all the staffPermissionList where scopePermission is null
        defaultStaffPermissionShouldNotBeFound("scopePermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByStaffTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where staffType equals to DEFAULT_STAFF_TYPE
        defaultStaffPermissionShouldBeFound("staffType.equals=" + DEFAULT_STAFF_TYPE);

        // Get all the staffPermissionList where staffType equals to UPDATED_STAFF_TYPE
        defaultStaffPermissionShouldNotBeFound("staffType.equals=" + UPDATED_STAFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByStaffTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where staffType not equals to DEFAULT_STAFF_TYPE
        defaultStaffPermissionShouldNotBeFound("staffType.notEquals=" + DEFAULT_STAFF_TYPE);

        // Get all the staffPermissionList where staffType not equals to UPDATED_STAFF_TYPE
        defaultStaffPermissionShouldBeFound("staffType.notEquals=" + UPDATED_STAFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByStaffTypeIsInShouldWork() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where staffType in DEFAULT_STAFF_TYPE or UPDATED_STAFF_TYPE
        defaultStaffPermissionShouldBeFound("staffType.in=" + DEFAULT_STAFF_TYPE + "," + UPDATED_STAFF_TYPE);

        // Get all the staffPermissionList where staffType equals to UPDATED_STAFF_TYPE
        defaultStaffPermissionShouldNotBeFound("staffType.in=" + UPDATED_STAFF_TYPE);
    }

    @Test
    @Transactional
    public void getAllStaffPermissionsByStaffTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        // Get all the staffPermissionList where staffType is not null
        defaultStaffPermissionShouldBeFound("staffType.specified=true");

        // Get all the staffPermissionList where staffType is null
        defaultStaffPermissionShouldNotBeFound("staffType.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultStaffPermissionShouldBeFound(String filter) throws Exception {
        restStaffPermissionMockMvc.perform(get("/api/staff-permissions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staffPermission.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRole").value(hasItem(DEFAULT_ID_ROLE)))
            .andExpect(jsonPath("$.[*].idBu").value(hasItem(DEFAULT_ID_BU)))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION.toString())))
            .andExpect(jsonPath("$.[*].scopePermission").value(hasItem(DEFAULT_SCOPE_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].staffType").value(hasItem(DEFAULT_STAFF_TYPE.toString())));

        // Check, that the count call also returns 1
        restStaffPermissionMockMvc.perform(get("/api/staff-permissions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultStaffPermissionShouldNotBeFound(String filter) throws Exception {
        restStaffPermissionMockMvc.perform(get("/api/staff-permissions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restStaffPermissionMockMvc.perform(get("/api/staff-permissions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingStaffPermission() throws Exception {
        // Get the staffPermission
        restStaffPermissionMockMvc.perform(get("/api/staff-permissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStaffPermission() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        int databaseSizeBeforeUpdate = staffPermissionRepository.findAll().size();

        // Update the staffPermission
        StaffPermission updatedStaffPermission = staffPermissionRepository.findById(staffPermission.getId()).get();
        // Disconnect from session so that the updates on updatedStaffPermission are not directly saved in db
        em.detach(updatedStaffPermission);
        updatedStaffPermission
            .idRole(UPDATED_ID_ROLE)
            .idBu(UPDATED_ID_BU)
            .action(UPDATED_ACTION)
            .scopePermission(UPDATED_SCOPE_PERMISSION)
            .staffType(UPDATED_STAFF_TYPE);
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(updatedStaffPermission);

        restStaffPermissionMockMvc.perform(put("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isOk());

        // Validate the StaffPermission in the database
        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeUpdate);
        StaffPermission testStaffPermission = staffPermissionList.get(staffPermissionList.size() - 1);
        assertThat(testStaffPermission.getIdRole()).isEqualTo(UPDATED_ID_ROLE);
        assertThat(testStaffPermission.getIdBu()).isEqualTo(UPDATED_ID_BU);
        assertThat(testStaffPermission.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testStaffPermission.getScopePermission()).isEqualTo(UPDATED_SCOPE_PERMISSION);
        assertThat(testStaffPermission.getStaffType()).isEqualTo(UPDATED_STAFF_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingStaffPermission() throws Exception {
        int databaseSizeBeforeUpdate = staffPermissionRepository.findAll().size();

        // Create the StaffPermission
        StaffPermissionDTO staffPermissionDTO = staffPermissionMapper.toDto(staffPermission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStaffPermissionMockMvc.perform(put("/api/staff-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffPermissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StaffPermission in the database
        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStaffPermission() throws Exception {
        // Initialize the database
        staffPermissionRepository.saveAndFlush(staffPermission);

        int databaseSizeBeforeDelete = staffPermissionRepository.findAll().size();

        // Delete the staffPermission
        restStaffPermissionMockMvc.perform(delete("/api/staff-permissions/{id}", staffPermission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StaffPermission> staffPermissionList = staffPermissionRepository.findAll();
        assertThat(staffPermissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
