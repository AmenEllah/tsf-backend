package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Municipality;
import com.attijari.domain.Agency;
import com.attijari.domain.PersonalInfo;
import com.attijari.repository.AgencyRepository;
import com.attijari.service.AgencyService;
import com.attijari.service.dto.AgencyDTO;
import com.attijari.service.mapper.AgencyMapper;
import com.attijari.service.AgencyQueryService;

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
 * Integration tests for the {@link AgencyResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AgencyResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_ZIP = 1;
    private static final Integer UPDATED_ZIP = 2;
    private static final Integer SMALLER_ZIP = 1 - 1;

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private AgencyMapper agencyMapper;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private AgencyQueryService agencyQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAgencyMockMvc;

    private Agency agency;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agency createEntity(EntityManager em) {
        Agency agency = new Agency()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .zip(DEFAULT_ZIP)
            .city(DEFAULT_CITY);
        return agency;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agency createUpdatedEntity(EntityManager em) {
        Agency agency = new Agency()
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .zip(UPDATED_ZIP)
            .city(UPDATED_CITY);
        return agency;
    }

    @BeforeEach
    public void initTest() {
        agency = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgency() throws Exception {
        int databaseSizeBeforeCreate = agencyRepository.findAll().size();
        // Create the Agency
        AgencyDTO agencyDTO = agencyMapper.toDto(agency);
        restAgencyMockMvc.perform(post("/api/agencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agencyDTO)))
            .andExpect(status().isCreated());

        // Validate the Agency in the database
        List<Agency> agencyList = agencyRepository.findAll();
        assertThat(agencyList).hasSize(databaseSizeBeforeCreate + 1);
        Agency testAgency = agencyList.get(agencyList.size() - 1);
        assertThat(testAgency.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAgency.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testAgency.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testAgency.getCity()).isEqualTo(DEFAULT_CITY);
    }

    @Test
    @Transactional
    public void createAgencyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agencyRepository.findAll().size();

        // Create the Agency with an existing ID
        agency.setId(1L);
        AgencyDTO agencyDTO = agencyMapper.toDto(agency);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgencyMockMvc.perform(post("/api/agencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agencyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agency in the database
        List<Agency> agencyList = agencyRepository.findAll();
        assertThat(agencyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAgencies() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList
        restAgencyMockMvc.perform(get("/api/agencies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agency.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)));
    }

    @Test
    @Transactional
    public void getAgency() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get the agency
        restAgencyMockMvc.perform(get("/api/agencies/{id}", agency.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(agency.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY));
    }


    @Test
    @Transactional
    public void getAgenciesByIdFiltering() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        Long id = agency.getId();

        defaultAgencyShouldBeFound("id.equals=" + id);
        defaultAgencyShouldNotBeFound("id.notEquals=" + id);

        defaultAgencyShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAgencyShouldNotBeFound("id.greaterThan=" + id);

        defaultAgencyShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAgencyShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAgenciesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where name equals to DEFAULT_NAME
        defaultAgencyShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the agencyList where name equals to UPDATED_NAME
        defaultAgencyShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAgenciesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where name not equals to DEFAULT_NAME
        defaultAgencyShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the agencyList where name not equals to UPDATED_NAME
        defaultAgencyShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAgenciesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where name in DEFAULT_NAME or UPDATED_NAME
        defaultAgencyShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the agencyList where name equals to UPDATED_NAME
        defaultAgencyShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAgenciesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where name is not null
        defaultAgencyShouldBeFound("name.specified=true");

        // Get all the agencyList where name is null
        defaultAgencyShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgenciesByNameContainsSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where name contains DEFAULT_NAME
        defaultAgencyShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the agencyList where name contains UPDATED_NAME
        defaultAgencyShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAgenciesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where name does not contain DEFAULT_NAME
        defaultAgencyShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the agencyList where name does not contain UPDATED_NAME
        defaultAgencyShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllAgenciesByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where address equals to DEFAULT_ADDRESS
        defaultAgencyShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the agencyList where address equals to UPDATED_ADDRESS
        defaultAgencyShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAgenciesByAddressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where address not equals to DEFAULT_ADDRESS
        defaultAgencyShouldNotBeFound("address.notEquals=" + DEFAULT_ADDRESS);

        // Get all the agencyList where address not equals to UPDATED_ADDRESS
        defaultAgencyShouldBeFound("address.notEquals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAgenciesByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultAgencyShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the agencyList where address equals to UPDATED_ADDRESS
        defaultAgencyShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAgenciesByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where address is not null
        defaultAgencyShouldBeFound("address.specified=true");

        // Get all the agencyList where address is null
        defaultAgencyShouldNotBeFound("address.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgenciesByAddressContainsSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where address contains DEFAULT_ADDRESS
        defaultAgencyShouldBeFound("address.contains=" + DEFAULT_ADDRESS);

        // Get all the agencyList where address contains UPDATED_ADDRESS
        defaultAgencyShouldNotBeFound("address.contains=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAgenciesByAddressNotContainsSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where address does not contain DEFAULT_ADDRESS
        defaultAgencyShouldNotBeFound("address.doesNotContain=" + DEFAULT_ADDRESS);

        // Get all the agencyList where address does not contain UPDATED_ADDRESS
        defaultAgencyShouldBeFound("address.doesNotContain=" + UPDATED_ADDRESS);
    }


    @Test
    @Transactional
    public void getAllAgenciesByZipIsEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip equals to DEFAULT_ZIP
        defaultAgencyShouldBeFound("zip.equals=" + DEFAULT_ZIP);

        // Get all the agencyList where zip equals to UPDATED_ZIP
        defaultAgencyShouldNotBeFound("zip.equals=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAgenciesByZipIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip not equals to DEFAULT_ZIP
        defaultAgencyShouldNotBeFound("zip.notEquals=" + DEFAULT_ZIP);

        // Get all the agencyList where zip not equals to UPDATED_ZIP
        defaultAgencyShouldBeFound("zip.notEquals=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAgenciesByZipIsInShouldWork() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip in DEFAULT_ZIP or UPDATED_ZIP
        defaultAgencyShouldBeFound("zip.in=" + DEFAULT_ZIP + "," + UPDATED_ZIP);

        // Get all the agencyList where zip equals to UPDATED_ZIP
        defaultAgencyShouldNotBeFound("zip.in=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAgenciesByZipIsNullOrNotNull() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip is not null
        defaultAgencyShouldBeFound("zip.specified=true");

        // Get all the agencyList where zip is null
        defaultAgencyShouldNotBeFound("zip.specified=false");
    }

    @Test
    @Transactional
    public void getAllAgenciesByZipIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip is greater than or equal to DEFAULT_ZIP
        defaultAgencyShouldBeFound("zip.greaterThanOrEqual=" + DEFAULT_ZIP);

        // Get all the agencyList where zip is greater than or equal to UPDATED_ZIP
        defaultAgencyShouldNotBeFound("zip.greaterThanOrEqual=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAgenciesByZipIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip is less than or equal to DEFAULT_ZIP
        defaultAgencyShouldBeFound("zip.lessThanOrEqual=" + DEFAULT_ZIP);

        // Get all the agencyList where zip is less than or equal to SMALLER_ZIP
        defaultAgencyShouldNotBeFound("zip.lessThanOrEqual=" + SMALLER_ZIP);
    }

    @Test
    @Transactional
    public void getAllAgenciesByZipIsLessThanSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip is less than DEFAULT_ZIP
        defaultAgencyShouldNotBeFound("zip.lessThan=" + DEFAULT_ZIP);

        // Get all the agencyList where zip is less than UPDATED_ZIP
        defaultAgencyShouldBeFound("zip.lessThan=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAgenciesByZipIsGreaterThanSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where zip is greater than DEFAULT_ZIP
        defaultAgencyShouldNotBeFound("zip.greaterThan=" + DEFAULT_ZIP);

        // Get all the agencyList where zip is greater than SMALLER_ZIP
        defaultAgencyShouldBeFound("zip.greaterThan=" + SMALLER_ZIP);
    }


    @Test
    @Transactional
    public void getAllAgenciesByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where city equals to DEFAULT_CITY
        defaultAgencyShouldBeFound("city.equals=" + DEFAULT_CITY);

        // Get all the agencyList where city equals to UPDATED_CITY
        defaultAgencyShouldNotBeFound("city.equals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAgenciesByCityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where city not equals to DEFAULT_CITY
        defaultAgencyShouldNotBeFound("city.notEquals=" + DEFAULT_CITY);

        // Get all the agencyList where city not equals to UPDATED_CITY
        defaultAgencyShouldBeFound("city.notEquals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAgenciesByCityIsInShouldWork() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where city in DEFAULT_CITY or UPDATED_CITY
        defaultAgencyShouldBeFound("city.in=" + DEFAULT_CITY + "," + UPDATED_CITY);

        // Get all the agencyList where city equals to UPDATED_CITY
        defaultAgencyShouldNotBeFound("city.in=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAgenciesByCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where city is not null
        defaultAgencyShouldBeFound("city.specified=true");

        // Get all the agencyList where city is null
        defaultAgencyShouldNotBeFound("city.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgenciesByCityContainsSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where city contains DEFAULT_CITY
        defaultAgencyShouldBeFound("city.contains=" + DEFAULT_CITY);

        // Get all the agencyList where city contains UPDATED_CITY
        defaultAgencyShouldNotBeFound("city.contains=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAgenciesByCityNotContainsSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        // Get all the agencyList where city does not contain DEFAULT_CITY
        defaultAgencyShouldNotBeFound("city.doesNotContain=" + DEFAULT_CITY);

        // Get all the agencyList where city does not contain UPDATED_CITY
        defaultAgencyShouldBeFound("city.doesNotContain=" + UPDATED_CITY);
    }


    @Test
    @Transactional
    public void getAllAgenciesByMunicipalityIsEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);
        Municipality municipality = MunicipalityResourceIT.createEntity(em);
        em.persist(municipality);
        em.flush();
        agency.setMunicipality(municipality);
        agencyRepository.saveAndFlush(agency);
        Long municipalityId = municipality.getId();

        // Get all the agencyList where municipality equals to municipalityId
        defaultAgencyShouldBeFound("municipalityId.equals=" + municipalityId);

        // Get all the agencyList where municipality equals to municipalityId + 1
        defaultAgencyShouldNotBeFound("municipalityId.equals=" + (municipalityId + 1));
    }


    @Test
    @Transactional
    public void getAllAgenciesByPersonalInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);
        PersonalInfo personalInfo = PersonalInfoResourceIT.createEntity(em);
        em.persist(personalInfo);
        em.flush();
        agencyRepository.saveAndFlush(agency);
        Long personalInfoId = personalInfo.getId();

        // Get all the agencyList where personalInfo equals to personalInfoId
        defaultAgencyShouldBeFound("personalInfoId.equals=" + personalInfoId);

        // Get all the agencyList where personalInfo equals to personalInfoId + 1
        defaultAgencyShouldNotBeFound("personalInfoId.equals=" + (personalInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAgencyShouldBeFound(String filter) throws Exception {
        restAgencyMockMvc.perform(get("/api/agencies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agency.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)));

        // Check, that the count call also returns 1
        restAgencyMockMvc.perform(get("/api/agencies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAgencyShouldNotBeFound(String filter) throws Exception {
        restAgencyMockMvc.perform(get("/api/agencies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAgencyMockMvc.perform(get("/api/agencies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingAgency() throws Exception {
        // Get the agency
        restAgencyMockMvc.perform(get("/api/agencies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgency() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        int databaseSizeBeforeUpdate = agencyRepository.findAll().size();

        // Update the agency
        Agency updatedAgency = agencyRepository.findById(agency.getId()).get();
        // Disconnect from session so that the updates on updatedAgency are not directly saved in db
        em.detach(updatedAgency);
        updatedAgency
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .zip(UPDATED_ZIP)
            .city(UPDATED_CITY);
        AgencyDTO agencyDTO = agencyMapper.toDto(updatedAgency);

        restAgencyMockMvc.perform(put("/api/agencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agencyDTO)))
            .andExpect(status().isOk());

        // Validate the Agency in the database
        List<Agency> agencyList = agencyRepository.findAll();
        assertThat(agencyList).hasSize(databaseSizeBeforeUpdate);
        Agency testAgency = agencyList.get(agencyList.size() - 1);
        assertThat(testAgency.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAgency.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAgency.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testAgency.getCity()).isEqualTo(UPDATED_CITY);
    }

    @Test
    @Transactional
    public void updateNonExistingAgency() throws Exception {
        int databaseSizeBeforeUpdate = agencyRepository.findAll().size();

        // Create the Agency
        AgencyDTO agencyDTO = agencyMapper.toDto(agency);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgencyMockMvc.perform(put("/api/agencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agencyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agency in the database
        List<Agency> agencyList = agencyRepository.findAll();
        assertThat(agencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAgency() throws Exception {
        // Initialize the database
        agencyRepository.saveAndFlush(agency);

        int databaseSizeBeforeDelete = agencyRepository.findAll().size();

        // Delete the agency
        restAgencyMockMvc.perform(delete("/api/agencies/{id}", agency.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Agency> agencyList = agencyRepository.findAll();
        assertThat(agencyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
