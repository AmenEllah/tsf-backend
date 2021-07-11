package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.AdressInfo;
import com.attijari.domain.PersonalInfo;
import com.attijari.repository.AdressInfoRepository;
import com.attijari.service.AdressInfoService;
import com.attijari.service.dto.AdressInfoDTO;
import com.attijari.service.mapper.AdressInfoMapper;
import com.attijari.service.AdressInfoQueryService;

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
 * Integration tests for the {@link AdressInfoResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdressInfoResourceIT {

    private static final String DEFAULT_COUNTRY_OF_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_RESIDENCE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_ZIP = 1;
    private static final Integer UPDATED_ZIP = 2;
    private static final Integer SMALLER_ZIP = 1 - 1;

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    @Autowired
    private AdressInfoRepository adressInfoRepository;

    @Autowired
    private AdressInfoMapper adressInfoMapper;

    @Autowired
    private AdressInfoService adressInfoService;

    @Autowired
    private AdressInfoQueryService adressInfoQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdressInfoMockMvc;

    private AdressInfo adressInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdressInfo createEntity(EntityManager em) {
        AdressInfo adressInfo = new AdressInfo()
            .address(DEFAULT_ADDRESS)
            .zip(DEFAULT_ZIP)
            .city(DEFAULT_CITY);
        return adressInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdressInfo createUpdatedEntity(EntityManager em) {
        AdressInfo adressInfo = new AdressInfo()
            .address(UPDATED_ADDRESS)
            .zip(UPDATED_ZIP)
            .city(UPDATED_CITY);
        return adressInfo;
    }

    @BeforeEach
    public void initTest() {
        adressInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdressInfo() throws Exception {
        int databaseSizeBeforeCreate = adressInfoRepository.findAll().size();
        // Create the AdressInfo
        AdressInfoDTO adressInfoDTO = adressInfoMapper.toDto(adressInfo);
        restAdressInfoMockMvc.perform(post("/api/adress-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adressInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the AdressInfo in the database
        List<AdressInfo> adressInfoList = adressInfoRepository.findAll();
        assertThat(adressInfoList).hasSize(databaseSizeBeforeCreate + 1);
        AdressInfo testAdressInfo = adressInfoList.get(adressInfoList.size() - 1);
        assertThat(testAdressInfo.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testAdressInfo.getZip()).isEqualTo(DEFAULT_ZIP);
        assertThat(testAdressInfo.getCity()).isEqualTo(DEFAULT_CITY);
    }

    @Test
    @Transactional
    public void createAdressInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adressInfoRepository.findAll().size();

        // Create the AdressInfo with an existing ID
        adressInfo.setId(1L);
        AdressInfoDTO adressInfoDTO = adressInfoMapper.toDto(adressInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdressInfoMockMvc.perform(post("/api/adress-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adressInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdressInfo in the database
        List<AdressInfo> adressInfoList = adressInfoRepository.findAll();
        assertThat(adressInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdressInfos() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList
        restAdressInfoMockMvc.perform(get("/api/adress-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adressInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryOfResidence").value(hasItem(DEFAULT_COUNTRY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)));
    }

    @Test
    @Transactional
    public void getAdressInfo() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get the adressInfo
        restAdressInfoMockMvc.perform(get("/api/adress-infos/{id}", adressInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adressInfo.getId().intValue()))
            .andExpect(jsonPath("$.countryOfResidence").value(DEFAULT_COUNTRY_OF_RESIDENCE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY));
    }


    @Test
    @Transactional
    public void getAdressInfosByIdFiltering() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        Long id = adressInfo.getId();

        defaultAdressInfoShouldBeFound("id.equals=" + id);
        defaultAdressInfoShouldNotBeFound("id.notEquals=" + id);

        defaultAdressInfoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAdressInfoShouldNotBeFound("id.greaterThan=" + id);

        defaultAdressInfoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAdressInfoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAdressInfosByCountryOfResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where countryOfResidence equals to DEFAULT_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldBeFound("countryOfResidence.equals=" + DEFAULT_COUNTRY_OF_RESIDENCE);

        // Get all the adressInfoList where countryOfResidence equals to UPDATED_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldNotBeFound("countryOfResidence.equals=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCountryOfResidenceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where countryOfResidence not equals to DEFAULT_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldNotBeFound("countryOfResidence.notEquals=" + DEFAULT_COUNTRY_OF_RESIDENCE);

        // Get all the adressInfoList where countryOfResidence not equals to UPDATED_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldBeFound("countryOfResidence.notEquals=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCountryOfResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where countryOfResidence in DEFAULT_COUNTRY_OF_RESIDENCE or UPDATED_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldBeFound("countryOfResidence.in=" + DEFAULT_COUNTRY_OF_RESIDENCE + "," + UPDATED_COUNTRY_OF_RESIDENCE);

        // Get all the adressInfoList where countryOfResidence equals to UPDATED_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldNotBeFound("countryOfResidence.in=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCountryOfResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where countryOfResidence is not null
        defaultAdressInfoShouldBeFound("countryOfResidence.specified=true");

        // Get all the adressInfoList where countryOfResidence is null
        defaultAdressInfoShouldNotBeFound("countryOfResidence.specified=false");
    }
                @Test
    @Transactional
    public void getAllAdressInfosByCountryOfResidenceContainsSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where countryOfResidence contains DEFAULT_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldBeFound("countryOfResidence.contains=" + DEFAULT_COUNTRY_OF_RESIDENCE);

        // Get all the adressInfoList where countryOfResidence contains UPDATED_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldNotBeFound("countryOfResidence.contains=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCountryOfResidenceNotContainsSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where countryOfResidence does not contain DEFAULT_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldNotBeFound("countryOfResidence.doesNotContain=" + DEFAULT_COUNTRY_OF_RESIDENCE);

        // Get all the adressInfoList where countryOfResidence does not contain UPDATED_COUNTRY_OF_RESIDENCE
        defaultAdressInfoShouldBeFound("countryOfResidence.doesNotContain=" + UPDATED_COUNTRY_OF_RESIDENCE);
    }


    @Test
    @Transactional
    public void getAllAdressInfosByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where address equals to DEFAULT_ADDRESS
        defaultAdressInfoShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the adressInfoList where address equals to UPDATED_ADDRESS
        defaultAdressInfoShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByAddressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where address not equals to DEFAULT_ADDRESS
        defaultAdressInfoShouldNotBeFound("address.notEquals=" + DEFAULT_ADDRESS);

        // Get all the adressInfoList where address not equals to UPDATED_ADDRESS
        defaultAdressInfoShouldBeFound("address.notEquals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultAdressInfoShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the adressInfoList where address equals to UPDATED_ADDRESS
        defaultAdressInfoShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where address is not null
        defaultAdressInfoShouldBeFound("address.specified=true");

        // Get all the adressInfoList where address is null
        defaultAdressInfoShouldNotBeFound("address.specified=false");
    }
                @Test
    @Transactional
    public void getAllAdressInfosByAddressContainsSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where address contains DEFAULT_ADDRESS
        defaultAdressInfoShouldBeFound("address.contains=" + DEFAULT_ADDRESS);

        // Get all the adressInfoList where address contains UPDATED_ADDRESS
        defaultAdressInfoShouldNotBeFound("address.contains=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByAddressNotContainsSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where address does not contain DEFAULT_ADDRESS
        defaultAdressInfoShouldNotBeFound("address.doesNotContain=" + DEFAULT_ADDRESS);

        // Get all the adressInfoList where address does not contain UPDATED_ADDRESS
        defaultAdressInfoShouldBeFound("address.doesNotContain=" + UPDATED_ADDRESS);
    }


    @Test
    @Transactional
    public void getAllAdressInfosByZipIsEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip equals to DEFAULT_ZIP
        defaultAdressInfoShouldBeFound("zip.equals=" + DEFAULT_ZIP);

        // Get all the adressInfoList where zip equals to UPDATED_ZIP
        defaultAdressInfoShouldNotBeFound("zip.equals=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByZipIsNotEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip not equals to DEFAULT_ZIP
        defaultAdressInfoShouldNotBeFound("zip.notEquals=" + DEFAULT_ZIP);

        // Get all the adressInfoList where zip not equals to UPDATED_ZIP
        defaultAdressInfoShouldBeFound("zip.notEquals=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByZipIsInShouldWork() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip in DEFAULT_ZIP or UPDATED_ZIP
        defaultAdressInfoShouldBeFound("zip.in=" + DEFAULT_ZIP + "," + UPDATED_ZIP);

        // Get all the adressInfoList where zip equals to UPDATED_ZIP
        defaultAdressInfoShouldNotBeFound("zip.in=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByZipIsNullOrNotNull() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip is not null
        defaultAdressInfoShouldBeFound("zip.specified=true");

        // Get all the adressInfoList where zip is null
        defaultAdressInfoShouldNotBeFound("zip.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdressInfosByZipIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip is greater than or equal to DEFAULT_ZIP
        defaultAdressInfoShouldBeFound("zip.greaterThanOrEqual=" + DEFAULT_ZIP);

        // Get all the adressInfoList where zip is greater than or equal to UPDATED_ZIP
        defaultAdressInfoShouldNotBeFound("zip.greaterThanOrEqual=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByZipIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip is less than or equal to DEFAULT_ZIP
        defaultAdressInfoShouldBeFound("zip.lessThanOrEqual=" + DEFAULT_ZIP);

        // Get all the adressInfoList where zip is less than or equal to SMALLER_ZIP
        defaultAdressInfoShouldNotBeFound("zip.lessThanOrEqual=" + SMALLER_ZIP);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByZipIsLessThanSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip is less than DEFAULT_ZIP
        defaultAdressInfoShouldNotBeFound("zip.lessThan=" + DEFAULT_ZIP);

        // Get all the adressInfoList where zip is less than UPDATED_ZIP
        defaultAdressInfoShouldBeFound("zip.lessThan=" + UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByZipIsGreaterThanSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where zip is greater than DEFAULT_ZIP
        defaultAdressInfoShouldNotBeFound("zip.greaterThan=" + DEFAULT_ZIP);

        // Get all the adressInfoList where zip is greater than SMALLER_ZIP
        defaultAdressInfoShouldBeFound("zip.greaterThan=" + SMALLER_ZIP);
    }


    @Test
    @Transactional
    public void getAllAdressInfosByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where city equals to DEFAULT_CITY
        defaultAdressInfoShouldBeFound("city.equals=" + DEFAULT_CITY);

        // Get all the adressInfoList where city equals to UPDATED_CITY
        defaultAdressInfoShouldNotBeFound("city.equals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where city not equals to DEFAULT_CITY
        defaultAdressInfoShouldNotBeFound("city.notEquals=" + DEFAULT_CITY);

        // Get all the adressInfoList where city not equals to UPDATED_CITY
        defaultAdressInfoShouldBeFound("city.notEquals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCityIsInShouldWork() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where city in DEFAULT_CITY or UPDATED_CITY
        defaultAdressInfoShouldBeFound("city.in=" + DEFAULT_CITY + "," + UPDATED_CITY);

        // Get all the adressInfoList where city equals to UPDATED_CITY
        defaultAdressInfoShouldNotBeFound("city.in=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where city is not null
        defaultAdressInfoShouldBeFound("city.specified=true");

        // Get all the adressInfoList where city is null
        defaultAdressInfoShouldNotBeFound("city.specified=false");
    }
                @Test
    @Transactional
    public void getAllAdressInfosByCityContainsSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where city contains DEFAULT_CITY
        defaultAdressInfoShouldBeFound("city.contains=" + DEFAULT_CITY);

        // Get all the adressInfoList where city contains UPDATED_CITY
        defaultAdressInfoShouldNotBeFound("city.contains=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllAdressInfosByCityNotContainsSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        // Get all the adressInfoList where city does not contain DEFAULT_CITY
        defaultAdressInfoShouldNotBeFound("city.doesNotContain=" + DEFAULT_CITY);

        // Get all the adressInfoList where city does not contain UPDATED_CITY
        defaultAdressInfoShouldBeFound("city.doesNotContain=" + UPDATED_CITY);
    }


    @Test
    @Transactional
    public void getAllAdressInfosByPersonalInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);
        PersonalInfo personalInfo = PersonalInfoResourceIT.createEntity(em);
        em.persist(personalInfo);
        em.flush();
        adressInfo.setPersonalInfo(personalInfo);
        adressInfoRepository.saveAndFlush(adressInfo);
        Long personalInfoId = personalInfo.getId();

        // Get all the adressInfoList where personalInfo equals to personalInfoId
        defaultAdressInfoShouldBeFound("personalInfoId.equals=" + personalInfoId);

        // Get all the adressInfoList where personalInfo equals to personalInfoId + 1
        defaultAdressInfoShouldNotBeFound("personalInfoId.equals=" + (personalInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAdressInfoShouldBeFound(String filter) throws Exception {
        restAdressInfoMockMvc.perform(get("/api/adress-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adressInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryOfResidence").value(hasItem(DEFAULT_COUNTRY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)));

        // Check, that the count call also returns 1
        restAdressInfoMockMvc.perform(get("/api/adress-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAdressInfoShouldNotBeFound(String filter) throws Exception {
        restAdressInfoMockMvc.perform(get("/api/adress-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAdressInfoMockMvc.perform(get("/api/adress-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingAdressInfo() throws Exception {
        // Get the adressInfo
        restAdressInfoMockMvc.perform(get("/api/adress-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdressInfo() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        int databaseSizeBeforeUpdate = adressInfoRepository.findAll().size();

        // Update the adressInfo
        AdressInfo updatedAdressInfo = adressInfoRepository.findById(adressInfo.getId()).get();
        // Disconnect from session so that the updates on updatedAdressInfo are not directly saved in db
        em.detach(updatedAdressInfo);
        updatedAdressInfo
            .address(UPDATED_ADDRESS)
            .zip(UPDATED_ZIP)
            .city(UPDATED_CITY);
        AdressInfoDTO adressInfoDTO = adressInfoMapper.toDto(updatedAdressInfo);

        restAdressInfoMockMvc.perform(put("/api/adress-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adressInfoDTO)))
            .andExpect(status().isOk());

        // Validate the AdressInfo in the database
        List<AdressInfo> adressInfoList = adressInfoRepository.findAll();
        assertThat(adressInfoList).hasSize(databaseSizeBeforeUpdate);
        AdressInfo testAdressInfo = adressInfoList.get(adressInfoList.size() - 1);
        assertThat(testAdressInfo.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAdressInfo.getZip()).isEqualTo(UPDATED_ZIP);
        assertThat(testAdressInfo.getCity()).isEqualTo(UPDATED_CITY);
    }

    @Test
    @Transactional
    public void updateNonExistingAdressInfo() throws Exception {
        int databaseSizeBeforeUpdate = adressInfoRepository.findAll().size();

        // Create the AdressInfo
        AdressInfoDTO adressInfoDTO = adressInfoMapper.toDto(adressInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdressInfoMockMvc.perform(put("/api/adress-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adressInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdressInfo in the database
        List<AdressInfo> adressInfoList = adressInfoRepository.findAll();
        assertThat(adressInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdressInfo() throws Exception {
        // Initialize the database
        adressInfoRepository.saveAndFlush(adressInfo);

        int databaseSizeBeforeDelete = adressInfoRepository.findAll().size();

        // Delete the adressInfo
        restAdressInfoMockMvc.perform(delete("/api/adress-infos/{id}", adressInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdressInfo> adressInfoList = adressInfoRepository.findAll();
        assertThat(adressInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
