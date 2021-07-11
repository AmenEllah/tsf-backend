package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Governorate;
import com.attijari.domain.Municipality;
import com.attijari.repository.GovernorateRepository;
import com.attijari.service.GovernorateService;
import com.attijari.service.dto.GovernorateDTO;
import com.attijari.service.mapper.GovernorateMapper;
import com.attijari.service.GovernorateQueryService;

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
 * Integration tests for the {@link GovernorateResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GovernorateResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private GovernorateRepository governorateRepository;

    @Autowired
    private GovernorateMapper governorateMapper;

    @Autowired
    private GovernorateService governorateService;

    @Autowired
    private GovernorateQueryService governorateQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGovernorateMockMvc;

    private Governorate governorate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Governorate createEntity(EntityManager em) {
        Governorate governorate = new Governorate()
            .name(DEFAULT_NAME);
        return governorate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Governorate createUpdatedEntity(EntityManager em) {
        Governorate governorate = new Governorate()
            .name(UPDATED_NAME);
        return governorate;
    }

    @BeforeEach
    public void initTest() {
        governorate = createEntity(em);
    }

    @Test
    @Transactional
    public void createGovernorate() throws Exception {
        int databaseSizeBeforeCreate = governorateRepository.findAll().size();
        // Create the Governorate
        GovernorateDTO governorateDTO = governorateMapper.toDto(governorate);
        restGovernorateMockMvc.perform(post("/api/governorates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(governorateDTO)))
            .andExpect(status().isCreated());

        // Validate the Governorate in the database
        List<Governorate> governorateList = governorateRepository.findAll();
        assertThat(governorateList).hasSize(databaseSizeBeforeCreate + 1);
        Governorate testGovernorate = governorateList.get(governorateList.size() - 1);
        assertThat(testGovernorate.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createGovernorateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = governorateRepository.findAll().size();

        // Create the Governorate with an existing ID
        governorate.setId(1L);
        GovernorateDTO governorateDTO = governorateMapper.toDto(governorate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGovernorateMockMvc.perform(post("/api/governorates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(governorateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Governorate in the database
        List<Governorate> governorateList = governorateRepository.findAll();
        assertThat(governorateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGovernorates() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList
        restGovernorateMockMvc.perform(get("/api/governorates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(governorate.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getGovernorate() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get the governorate
        restGovernorateMockMvc.perform(get("/api/governorates/{id}", governorate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(governorate.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }


    @Test
    @Transactional
    public void getGovernoratesByIdFiltering() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        Long id = governorate.getId();

        defaultGovernorateShouldBeFound("id.equals=" + id);
        defaultGovernorateShouldNotBeFound("id.notEquals=" + id);

        defaultGovernorateShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultGovernorateShouldNotBeFound("id.greaterThan=" + id);

        defaultGovernorateShouldBeFound("id.lessThanOrEqual=" + id);
        defaultGovernorateShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllGovernoratesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList where name equals to DEFAULT_NAME
        defaultGovernorateShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the governorateList where name equals to UPDATED_NAME
        defaultGovernorateShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGovernoratesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList where name not equals to DEFAULT_NAME
        defaultGovernorateShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the governorateList where name not equals to UPDATED_NAME
        defaultGovernorateShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGovernoratesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList where name in DEFAULT_NAME or UPDATED_NAME
        defaultGovernorateShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the governorateList where name equals to UPDATED_NAME
        defaultGovernorateShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGovernoratesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList where name is not null
        defaultGovernorateShouldBeFound("name.specified=true");

        // Get all the governorateList where name is null
        defaultGovernorateShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllGovernoratesByNameContainsSomething() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList where name contains DEFAULT_NAME
        defaultGovernorateShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the governorateList where name contains UPDATED_NAME
        defaultGovernorateShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGovernoratesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList where name does not contain DEFAULT_NAME
        defaultGovernorateShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the governorateList where name does not contain UPDATED_NAME
        defaultGovernorateShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllGovernoratesByMunicipalityIsEqualToSomething() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);
        Municipality municipality = MunicipalityResourceIT.createEntity(em);
        em.persist(municipality);
        em.flush();
        governorateRepository.saveAndFlush(governorate);
        Long municipalityId = municipality.getId();

        // Get all the governorateList where municipality equals to municipalityId
        defaultGovernorateShouldBeFound("municipalityId.equals=" + municipalityId);

        // Get all the governorateList where municipality equals to municipalityId + 1
        defaultGovernorateShouldNotBeFound("municipalityId.equals=" + (municipalityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultGovernorateShouldBeFound(String filter) throws Exception {
        restGovernorateMockMvc.perform(get("/api/governorates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(governorate.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restGovernorateMockMvc.perform(get("/api/governorates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultGovernorateShouldNotBeFound(String filter) throws Exception {
        restGovernorateMockMvc.perform(get("/api/governorates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restGovernorateMockMvc.perform(get("/api/governorates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingGovernorate() throws Exception {
        // Get the governorate
        restGovernorateMockMvc.perform(get("/api/governorates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGovernorate() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        int databaseSizeBeforeUpdate = governorateRepository.findAll().size();

        // Update the governorate
        Governorate updatedGovernorate = governorateRepository.findById(governorate.getId()).get();
        // Disconnect from session so that the updates on updatedGovernorate are not directly saved in db
        em.detach(updatedGovernorate);
        updatedGovernorate
            .name(UPDATED_NAME);
        GovernorateDTO governorateDTO = governorateMapper.toDto(updatedGovernorate);

        restGovernorateMockMvc.perform(put("/api/governorates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(governorateDTO)))
            .andExpect(status().isOk());

        // Validate the Governorate in the database
        List<Governorate> governorateList = governorateRepository.findAll();
        assertThat(governorateList).hasSize(databaseSizeBeforeUpdate);
        Governorate testGovernorate = governorateList.get(governorateList.size() - 1);
        assertThat(testGovernorate.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingGovernorate() throws Exception {
        int databaseSizeBeforeUpdate = governorateRepository.findAll().size();

        // Create the Governorate
        GovernorateDTO governorateDTO = governorateMapper.toDto(governorate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGovernorateMockMvc.perform(put("/api/governorates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(governorateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Governorate in the database
        List<Governorate> governorateList = governorateRepository.findAll();
        assertThat(governorateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGovernorate() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        int databaseSizeBeforeDelete = governorateRepository.findAll().size();

        // Delete the governorate
        restGovernorateMockMvc.perform(delete("/api/governorates/{id}", governorate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Governorate> governorateList = governorateRepository.findAll();
        assertThat(governorateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
