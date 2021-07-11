package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.repository.NationalityRepository;
import com.attijari.service.mapper.NationalityMapper;
import com.attijari.domain.Nationality;
import com.attijari.service.NationalityService;
import com.attijari.service.dto.NationalityDTO;
import com.attijari.service.NationalityQueryService;

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
 * Integration tests for the {@link NationalityResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NationalityResourceIT {

    private static final String DEFAULT_LIBELLE_FR = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_FR = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_EN = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_EN = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_FLAG = "BBBBBBBBBB";

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private NationalityMapper nationalityMapper;

    @Autowired
    private NationalityService nationalityService;

    @Autowired
    private NationalityQueryService nationalityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNationalityMockMvc;

    private Nationality nationality;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nationality createEntity(EntityManager em) {
        Nationality nationality = new Nationality()
            .libelleFR(DEFAULT_LIBELLE_FR)
            .libelleEN(DEFAULT_LIBELLE_EN)
            .code(DEFAULT_CODE)
            .flag(DEFAULT_FLAG);
        return nationality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nationality createUpdatedEntity(EntityManager em) {
        Nationality nationality = new Nationality()
            .libelleFR(UPDATED_LIBELLE_FR)
            .libelleEN(UPDATED_LIBELLE_EN)
            .code(UPDATED_CODE)
            .flag(UPDATED_FLAG);
        return nationality;
    }

    @BeforeEach
    public void initTest() {
        nationality = createEntity(em);
    }

    @Test
    @Transactional
    public void createNationality() throws Exception {
        int databaseSizeBeforeCreate = nationalityRepository.findAll().size();
        // Create the Nationality
        NationalityDTO nationalityDTO = nationalityMapper.toDto(nationality);
        restNationalityMockMvc.perform(post("/api/nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nationalityDTO)))
            .andExpect(status().isCreated());

        // Validate the Nationality in the database
        List<Nationality> nationalityList = nationalityRepository.findAll();
        assertThat(nationalityList).hasSize(databaseSizeBeforeCreate + 1);
        Nationality testNationality = nationalityList.get(nationalityList.size() - 1);
        assertThat(testNationality.getLibelleFR()).isEqualTo(DEFAULT_LIBELLE_FR);
        assertThat(testNationality.getLibelleEN()).isEqualTo(DEFAULT_LIBELLE_EN);
        assertThat(testNationality.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testNationality.getFlag()).isEqualTo(DEFAULT_FLAG);
    }

    @Test
    @Transactional
    public void createNationalityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nationalityRepository.findAll().size();

        // Create the Nationality with an existing ID
        nationality.setId(1L);
        NationalityDTO nationalityDTO = nationalityMapper.toDto(nationality);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNationalityMockMvc.perform(post("/api/nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nationalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nationality in the database
        List<Nationality> nationalityList = nationalityRepository.findAll();
        assertThat(nationalityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNationalities() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList
        restNationalityMockMvc.perform(get("/api/nationalities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nationality.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleFR").value(hasItem(DEFAULT_LIBELLE_FR)))
            .andExpect(jsonPath("$.[*].libelleEN").value(hasItem(DEFAULT_LIBELLE_EN)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].flag").value(hasItem(DEFAULT_FLAG)));
    }

    @Test
    @Transactional
    public void getNationality() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get the nationality
        restNationalityMockMvc.perform(get("/api/nationalities/{id}", nationality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nationality.getId().intValue()))
            .andExpect(jsonPath("$.libelleFR").value(DEFAULT_LIBELLE_FR))
            .andExpect(jsonPath("$.libelleEN").value(DEFAULT_LIBELLE_EN))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.flag").value(DEFAULT_FLAG));
    }


    @Test
    @Transactional
    public void getNationalitiesByIdFiltering() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        Long id = nationality.getId();

        defaultNationalityShouldBeFound("id.equals=" + id);
        defaultNationalityShouldNotBeFound("id.notEquals=" + id);

        defaultNationalityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNationalityShouldNotBeFound("id.greaterThan=" + id);

        defaultNationalityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNationalityShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNationalitiesByLibelleFRIsEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleFR equals to DEFAULT_LIBELLE_FR
        defaultNationalityShouldBeFound("libelleFR.equals=" + DEFAULT_LIBELLE_FR);

        // Get all the nationalityList where libelleFR equals to UPDATED_LIBELLE_FR
        defaultNationalityShouldNotBeFound("libelleFR.equals=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleFRIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleFR not equals to DEFAULT_LIBELLE_FR
        defaultNationalityShouldNotBeFound("libelleFR.notEquals=" + DEFAULT_LIBELLE_FR);

        // Get all the nationalityList where libelleFR not equals to UPDATED_LIBELLE_FR
        defaultNationalityShouldBeFound("libelleFR.notEquals=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleFRIsInShouldWork() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleFR in DEFAULT_LIBELLE_FR or UPDATED_LIBELLE_FR
        defaultNationalityShouldBeFound("libelleFR.in=" + DEFAULT_LIBELLE_FR + "," + UPDATED_LIBELLE_FR);

        // Get all the nationalityList where libelleFR equals to UPDATED_LIBELLE_FR
        defaultNationalityShouldNotBeFound("libelleFR.in=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleFRIsNullOrNotNull() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleFR is not null
        defaultNationalityShouldBeFound("libelleFR.specified=true");

        // Get all the nationalityList where libelleFR is null
        defaultNationalityShouldNotBeFound("libelleFR.specified=false");
    }
                @Test
    @Transactional
    public void getAllNationalitiesByLibelleFRContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleFR contains DEFAULT_LIBELLE_FR
        defaultNationalityShouldBeFound("libelleFR.contains=" + DEFAULT_LIBELLE_FR);

        // Get all the nationalityList where libelleFR contains UPDATED_LIBELLE_FR
        defaultNationalityShouldNotBeFound("libelleFR.contains=" + UPDATED_LIBELLE_FR);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleFRNotContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleFR does not contain DEFAULT_LIBELLE_FR
        defaultNationalityShouldNotBeFound("libelleFR.doesNotContain=" + DEFAULT_LIBELLE_FR);

        // Get all the nationalityList where libelleFR does not contain UPDATED_LIBELLE_FR
        defaultNationalityShouldBeFound("libelleFR.doesNotContain=" + UPDATED_LIBELLE_FR);
    }


    @Test
    @Transactional
    public void getAllNationalitiesByLibelleENIsEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleEN equals to DEFAULT_LIBELLE_EN
        defaultNationalityShouldBeFound("libelleEN.equals=" + DEFAULT_LIBELLE_EN);

        // Get all the nationalityList where libelleEN equals to UPDATED_LIBELLE_EN
        defaultNationalityShouldNotBeFound("libelleEN.equals=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleENIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleEN not equals to DEFAULT_LIBELLE_EN
        defaultNationalityShouldNotBeFound("libelleEN.notEquals=" + DEFAULT_LIBELLE_EN);

        // Get all the nationalityList where libelleEN not equals to UPDATED_LIBELLE_EN
        defaultNationalityShouldBeFound("libelleEN.notEquals=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleENIsInShouldWork() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleEN in DEFAULT_LIBELLE_EN or UPDATED_LIBELLE_EN
        defaultNationalityShouldBeFound("libelleEN.in=" + DEFAULT_LIBELLE_EN + "," + UPDATED_LIBELLE_EN);

        // Get all the nationalityList where libelleEN equals to UPDATED_LIBELLE_EN
        defaultNationalityShouldNotBeFound("libelleEN.in=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleENIsNullOrNotNull() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleEN is not null
        defaultNationalityShouldBeFound("libelleEN.specified=true");

        // Get all the nationalityList where libelleEN is null
        defaultNationalityShouldNotBeFound("libelleEN.specified=false");
    }
                @Test
    @Transactional
    public void getAllNationalitiesByLibelleENContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleEN contains DEFAULT_LIBELLE_EN
        defaultNationalityShouldBeFound("libelleEN.contains=" + DEFAULT_LIBELLE_EN);

        // Get all the nationalityList where libelleEN contains UPDATED_LIBELLE_EN
        defaultNationalityShouldNotBeFound("libelleEN.contains=" + UPDATED_LIBELLE_EN);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByLibelleENNotContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where libelleEN does not contain DEFAULT_LIBELLE_EN
        defaultNationalityShouldNotBeFound("libelleEN.doesNotContain=" + DEFAULT_LIBELLE_EN);

        // Get all the nationalityList where libelleEN does not contain UPDATED_LIBELLE_EN
        defaultNationalityShouldBeFound("libelleEN.doesNotContain=" + UPDATED_LIBELLE_EN);
    }


    @Test
    @Transactional
    public void getAllNationalitiesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where code equals to DEFAULT_CODE
        defaultNationalityShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the nationalityList where code equals to UPDATED_CODE
        defaultNationalityShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where code not equals to DEFAULT_CODE
        defaultNationalityShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the nationalityList where code not equals to UPDATED_CODE
        defaultNationalityShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where code in DEFAULT_CODE or UPDATED_CODE
        defaultNationalityShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the nationalityList where code equals to UPDATED_CODE
        defaultNationalityShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where code is not null
        defaultNationalityShouldBeFound("code.specified=true");

        // Get all the nationalityList where code is null
        defaultNationalityShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllNationalitiesByCodeContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where code contains DEFAULT_CODE
        defaultNationalityShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the nationalityList where code contains UPDATED_CODE
        defaultNationalityShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where code does not contain DEFAULT_CODE
        defaultNationalityShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the nationalityList where code does not contain UPDATED_CODE
        defaultNationalityShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllNationalitiesByFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where flag equals to DEFAULT_FLAG
        defaultNationalityShouldBeFound("flag.equals=" + DEFAULT_FLAG);

        // Get all the nationalityList where flag equals to UPDATED_FLAG
        defaultNationalityShouldNotBeFound("flag.equals=" + UPDATED_FLAG);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByFlagIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where flag not equals to DEFAULT_FLAG
        defaultNationalityShouldNotBeFound("flag.notEquals=" + DEFAULT_FLAG);

        // Get all the nationalityList where flag not equals to UPDATED_FLAG
        defaultNationalityShouldBeFound("flag.notEquals=" + UPDATED_FLAG);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByFlagIsInShouldWork() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where flag in DEFAULT_FLAG or UPDATED_FLAG
        defaultNationalityShouldBeFound("flag.in=" + DEFAULT_FLAG + "," + UPDATED_FLAG);

        // Get all the nationalityList where flag equals to UPDATED_FLAG
        defaultNationalityShouldNotBeFound("flag.in=" + UPDATED_FLAG);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where flag is not null
        defaultNationalityShouldBeFound("flag.specified=true");

        // Get all the nationalityList where flag is null
        defaultNationalityShouldNotBeFound("flag.specified=false");
    }
                @Test
    @Transactional
    public void getAllNationalitiesByFlagContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where flag contains DEFAULT_FLAG
        defaultNationalityShouldBeFound("flag.contains=" + DEFAULT_FLAG);

        // Get all the nationalityList where flag contains UPDATED_FLAG
        defaultNationalityShouldNotBeFound("flag.contains=" + UPDATED_FLAG);
    }

    @Test
    @Transactional
    public void getAllNationalitiesByFlagNotContainsSomething() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        // Get all the nationalityList where flag does not contain DEFAULT_FLAG
        defaultNationalityShouldNotBeFound("flag.doesNotContain=" + DEFAULT_FLAG);

        // Get all the nationalityList where flag does not contain UPDATED_FLAG
        defaultNationalityShouldBeFound("flag.doesNotContain=" + UPDATED_FLAG);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNationalityShouldBeFound(String filter) throws Exception {
        restNationalityMockMvc.perform(get("/api/nationalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nationality.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleFR").value(hasItem(DEFAULT_LIBELLE_FR)))
            .andExpect(jsonPath("$.[*].libelleEN").value(hasItem(DEFAULT_LIBELLE_EN)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].flag").value(hasItem(DEFAULT_FLAG)));

        // Check, that the count call also returns 1
        restNationalityMockMvc.perform(get("/api/nationalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNationalityShouldNotBeFound(String filter) throws Exception {
        restNationalityMockMvc.perform(get("/api/nationalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNationalityMockMvc.perform(get("/api/nationalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingNationality() throws Exception {
        // Get the nationality
        restNationalityMockMvc.perform(get("/api/nationalities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNationality() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        int databaseSizeBeforeUpdate = nationalityRepository.findAll().size();

        // Update the nationality
        Nationality updatedNationality = nationalityRepository.findById(nationality.getId()).get();
        // Disconnect from session so that the updates on updatedNationality are not directly saved in db
        em.detach(updatedNationality);
        updatedNationality
            .libelleFR(UPDATED_LIBELLE_FR)
            .libelleEN(UPDATED_LIBELLE_EN)
            .code(UPDATED_CODE)
            .flag(UPDATED_FLAG);
        NationalityDTO nationalityDTO = nationalityMapper.toDto(updatedNationality);

        restNationalityMockMvc.perform(put("/api/nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nationalityDTO)))
            .andExpect(status().isOk());

        // Validate the Nationality in the database
        List<Nationality> nationalityList = nationalityRepository.findAll();
        assertThat(nationalityList).hasSize(databaseSizeBeforeUpdate);
        Nationality testNationality = nationalityList.get(nationalityList.size() - 1);
        assertThat(testNationality.getLibelleFR()).isEqualTo(UPDATED_LIBELLE_FR);
        assertThat(testNationality.getLibelleEN()).isEqualTo(UPDATED_LIBELLE_EN);
        assertThat(testNationality.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testNationality.getFlag()).isEqualTo(UPDATED_FLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingNationality() throws Exception {
        int databaseSizeBeforeUpdate = nationalityRepository.findAll().size();

        // Create the Nationality
        NationalityDTO nationalityDTO = nationalityMapper.toDto(nationality);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNationalityMockMvc.perform(put("/api/nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nationalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nationality in the database
        List<Nationality> nationalityList = nationalityRepository.findAll();
        assertThat(nationalityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNationality() throws Exception {
        // Initialize the database
        nationalityRepository.saveAndFlush(nationality);

        int databaseSizeBeforeDelete = nationalityRepository.findAll().size();

        // Delete the nationality
        restNationalityMockMvc.perform(delete("/api/nationalities/{id}", nationality.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nationality> nationalityList = nationalityRepository.findAll();
        assertThat(nationalityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
