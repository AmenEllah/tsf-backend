package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Municipality;
import com.attijari.domain.Agency;
import com.attijari.domain.Governorate;
import com.attijari.repository.MunicipalityRepository;
import com.attijari.service.MunicipalityService;
import com.attijari.service.dto.MunicipalityDTO;
import com.attijari.service.mapper.MunicipalityMapper;
import com.attijari.service.MunicipalityQueryService;

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
 * Integration tests for the {@link MunicipalityResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MunicipalityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private MunicipalityMapper municipalityMapper;

    @Autowired
    private MunicipalityService municipalityService;

    @Autowired
    private MunicipalityQueryService municipalityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMunicipalityMockMvc;

    private Municipality municipality;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipality createEntity(EntityManager em) {
        Municipality municipality = new Municipality()
            .name(DEFAULT_NAME);
        return municipality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipality createUpdatedEntity(EntityManager em) {
        Municipality municipality = new Municipality()
            .name(UPDATED_NAME);
        return municipality;
    }

    @BeforeEach
    public void initTest() {
        municipality = createEntity(em);
    }

    @Test
    @Transactional
    public void createMunicipality() throws Exception {
        int databaseSizeBeforeCreate = municipalityRepository.findAll().size();
        // Create the Municipality
        MunicipalityDTO municipalityDTO = municipalityMapper.toDto(municipality);
        restMunicipalityMockMvc.perform(post("/api/municipalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(municipalityDTO)))
            .andExpect(status().isCreated());

        // Validate the Municipality in the database
        List<Municipality> municipalityList = municipalityRepository.findAll();
        assertThat(municipalityList).hasSize(databaseSizeBeforeCreate + 1);
        Municipality testMunicipality = municipalityList.get(municipalityList.size() - 1);
        assertThat(testMunicipality.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMunicipalityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = municipalityRepository.findAll().size();

        // Create the Municipality with an existing ID
        municipality.setId(1L);
        MunicipalityDTO municipalityDTO = municipalityMapper.toDto(municipality);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMunicipalityMockMvc.perform(post("/api/municipalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(municipalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Municipality in the database
        List<Municipality> municipalityList = municipalityRepository.findAll();
        assertThat(municipalityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMunicipalities() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList
        restMunicipalityMockMvc.perform(get("/api/municipalities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipality.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getMunicipality() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get the municipality
        restMunicipalityMockMvc.perform(get("/api/municipalities/{id}", municipality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(municipality.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }


    @Test
    @Transactional
    public void getMunicipalitiesByIdFiltering() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        Long id = municipality.getId();

        defaultMunicipalityShouldBeFound("id.equals=" + id);
        defaultMunicipalityShouldNotBeFound("id.notEquals=" + id);

        defaultMunicipalityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultMunicipalityShouldNotBeFound("id.greaterThan=" + id);

        defaultMunicipalityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultMunicipalityShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllMunicipalitiesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList where name equals to DEFAULT_NAME
        defaultMunicipalityShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the municipalityList where name equals to UPDATED_NAME
        defaultMunicipalityShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMunicipalitiesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList where name not equals to DEFAULT_NAME
        defaultMunicipalityShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the municipalityList where name not equals to UPDATED_NAME
        defaultMunicipalityShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMunicipalitiesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList where name in DEFAULT_NAME or UPDATED_NAME
        defaultMunicipalityShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the municipalityList where name equals to UPDATED_NAME
        defaultMunicipalityShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMunicipalitiesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList where name is not null
        defaultMunicipalityShouldBeFound("name.specified=true");

        // Get all the municipalityList where name is null
        defaultMunicipalityShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllMunicipalitiesByNameContainsSomething() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList where name contains DEFAULT_NAME
        defaultMunicipalityShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the municipalityList where name contains UPDATED_NAME
        defaultMunicipalityShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMunicipalitiesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList where name does not contain DEFAULT_NAME
        defaultMunicipalityShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the municipalityList where name does not contain UPDATED_NAME
        defaultMunicipalityShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllMunicipalitiesByAgencyIsEqualToSomething() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);
        Agency agency = AgencyResourceIT.createEntity(em);
        em.persist(agency);
        em.flush();
        municipality.addAgency(agency);
        municipalityRepository.saveAndFlush(municipality);
        Long agencyId = agency.getId();

        // Get all the municipalityList where agency equals to agencyId
        defaultMunicipalityShouldBeFound("agencyId.equals=" + agencyId);

        // Get all the municipalityList where agency equals to agencyId + 1
        defaultMunicipalityShouldNotBeFound("agencyId.equals=" + (agencyId + 1));
    }


    @Test
    @Transactional
    public void getAllMunicipalitiesByGovernorateIsEqualToSomething() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);
        Governorate governorate = GovernorateResourceIT.createEntity(em);
        em.persist(governorate);
        em.flush();
        municipality.setGovernorate(governorate);
        municipalityRepository.saveAndFlush(municipality);
        Long governorateId = governorate.getId();

        // Get all the municipalityList where governorate equals to governorateId
        defaultMunicipalityShouldBeFound("governorateId.equals=" + governorateId);

        // Get all the municipalityList where governorate equals to governorateId + 1
        defaultMunicipalityShouldNotBeFound("governorateId.equals=" + (governorateId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMunicipalityShouldBeFound(String filter) throws Exception {
        restMunicipalityMockMvc.perform(get("/api/municipalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipality.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restMunicipalityMockMvc.perform(get("/api/municipalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMunicipalityShouldNotBeFound(String filter) throws Exception {
        restMunicipalityMockMvc.perform(get("/api/municipalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMunicipalityMockMvc.perform(get("/api/municipalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingMunicipality() throws Exception {
        // Get the municipality
        restMunicipalityMockMvc.perform(get("/api/municipalities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMunicipality() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        int databaseSizeBeforeUpdate = municipalityRepository.findAll().size();

        // Update the municipality
        Municipality updatedMunicipality = municipalityRepository.findById(municipality.getId()).get();
        // Disconnect from session so that the updates on updatedMunicipality are not directly saved in db
        em.detach(updatedMunicipality);
        updatedMunicipality
            .name(UPDATED_NAME);
        MunicipalityDTO municipalityDTO = municipalityMapper.toDto(updatedMunicipality);

        restMunicipalityMockMvc.perform(put("/api/municipalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(municipalityDTO)))
            .andExpect(status().isOk());

        // Validate the Municipality in the database
        List<Municipality> municipalityList = municipalityRepository.findAll();
        assertThat(municipalityList).hasSize(databaseSizeBeforeUpdate);
        Municipality testMunicipality = municipalityList.get(municipalityList.size() - 1);
        assertThat(testMunicipality.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMunicipality() throws Exception {
        int databaseSizeBeforeUpdate = municipalityRepository.findAll().size();

        // Create the Municipality
        MunicipalityDTO municipalityDTO = municipalityMapper.toDto(municipality);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMunicipalityMockMvc.perform(put("/api/municipalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(municipalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Municipality in the database
        List<Municipality> municipalityList = municipalityRepository.findAll();
        assertThat(municipalityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMunicipality() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        int databaseSizeBeforeDelete = municipalityRepository.findAll().size();

        // Delete the municipality
        restMunicipalityMockMvc.perform(delete("/api/municipalities/{id}", municipality.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Municipality> municipalityList = municipalityRepository.findAll();
        assertThat(municipalityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
