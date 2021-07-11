package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.FinancialInfo;
import com.attijari.domain.Activity;
import com.attijari.domain.Category;
import com.attijari.domain.PersonalInfo;
import com.attijari.domain.MonthlyNetIncome;
import com.attijari.repository.FinancialInfoRepository;
import com.attijari.service.FinancialInfoService;
import com.attijari.service.dto.FinancialInfoDTO;
import com.attijari.service.mapper.FinancialInfoMapper;
import com.attijari.service.FinancialInfoQueryService;

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
 * Integration tests for the {@link FinancialInfoResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FinancialInfoResourceIT {

    @Autowired
    private FinancialInfoRepository financialInfoRepository;

    @Autowired
    private FinancialInfoMapper financialInfoMapper;

    @Autowired
    private FinancialInfoService financialInfoService;

    @Autowired
    private FinancialInfoQueryService financialInfoQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFinancialInfoMockMvc;

    private FinancialInfo financialInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancialInfo createEntity(EntityManager em) {
        FinancialInfo financialInfo = new FinancialInfo();
        return financialInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancialInfo createUpdatedEntity(EntityManager em) {
        FinancialInfo financialInfo = new FinancialInfo();
        return financialInfo;
    }

    @BeforeEach
    public void initTest() {
        financialInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinancialInfo() throws Exception {
        int databaseSizeBeforeCreate = financialInfoRepository.findAll().size();
        // Create the FinancialInfo
        FinancialInfoDTO financialInfoDTO = financialInfoMapper.toDto(financialInfo);
        restFinancialInfoMockMvc.perform(post("/api/financial-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(financialInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the FinancialInfo in the database
        List<FinancialInfo> financialInfoList = financialInfoRepository.findAll();
        assertThat(financialInfoList).hasSize(databaseSizeBeforeCreate + 1);
        FinancialInfo testFinancialInfo = financialInfoList.get(financialInfoList.size() - 1);
    }

    @Test
    @Transactional
    public void createFinancialInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = financialInfoRepository.findAll().size();

        // Create the FinancialInfo with an existing ID
        financialInfo.setId(1L);
        FinancialInfoDTO financialInfoDTO = financialInfoMapper.toDto(financialInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinancialInfoMockMvc.perform(post("/api/financial-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(financialInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinancialInfo in the database
        List<FinancialInfo> financialInfoList = financialInfoRepository.findAll();
        assertThat(financialInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFinancialInfos() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);

        // Get all the financialInfoList
        restFinancialInfoMockMvc.perform(get("/api/financial-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(financialInfo.getId().intValue())));
    }

    @Test
    @Transactional
    public void getFinancialInfo() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);

        // Get the financialInfo
        restFinancialInfoMockMvc.perform(get("/api/financial-infos/{id}", financialInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(financialInfo.getId().intValue()));
    }


    @Test
    @Transactional
    public void getFinancialInfosByIdFiltering() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);

        Long id = financialInfo.getId();

        defaultFinancialInfoShouldBeFound("id.equals=" + id);
        defaultFinancialInfoShouldNotBeFound("id.notEquals=" + id);

        defaultFinancialInfoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFinancialInfoShouldNotBeFound("id.greaterThan=" + id);

        defaultFinancialInfoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFinancialInfoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFinancialInfosByActivityIsEqualToSomething() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);
        Activity activity = ActivityResourceIT.createEntity(em);
        em.persist(activity);
        em.flush();
        financialInfo.setActivity(activity);
        financialInfoRepository.saveAndFlush(financialInfo);
        Long activityId = activity.getId();

        // Get all the financialInfoList where activity equals to activityId
        defaultFinancialInfoShouldBeFound("activityId.equals=" + activityId);

        // Get all the financialInfoList where activity equals to activityId + 1
        defaultFinancialInfoShouldNotBeFound("activityId.equals=" + (activityId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialInfosByCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);
        Category category = CategoryResourceIT.createEntity(em);
        em.persist(category);
        em.flush();
        financialInfo.setCategory(category);
        financialInfoRepository.saveAndFlush(financialInfo);
        Long categoryId = category.getId();

        // Get all the financialInfoList where category equals to categoryId
        defaultFinancialInfoShouldBeFound("categoryId.equals=" + categoryId);

        // Get all the financialInfoList where category equals to categoryId + 1
        defaultFinancialInfoShouldNotBeFound("categoryId.equals=" + (categoryId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialInfosByPersonalInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);
        PersonalInfo personalInfo = PersonalInfoResourceIT.createEntity(em);
        em.persist(personalInfo);
        em.flush();
        financialInfoRepository.saveAndFlush(financialInfo);
        Long personalInfoId = personalInfo.getId();

        // Get all the financialInfoList where personalInfo equals to personalInfoId
        defaultFinancialInfoShouldBeFound("personalInfoId.equals=" + personalInfoId);

        // Get all the financialInfoList where personalInfo equals to personalInfoId + 1
        defaultFinancialInfoShouldNotBeFound("personalInfoId.equals=" + (personalInfoId + 1));
    }


    @Test
    @Transactional
    public void getAllFinancialInfosByMonthlyNetIncomeIsEqualToSomething() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);
        MonthlyNetIncome monthlyNetIncome = MonthlyNetIncomeResourceIT.createEntity(em);
        em.persist(monthlyNetIncome);
        em.flush();
        financialInfo.setMonthlyNetIncome(monthlyNetIncome);
        financialInfoRepository.saveAndFlush(financialInfo);
        Long monthlyNetIncomeId = monthlyNetIncome.getId();

        // Get all the financialInfoList where monthlyNetIncome equals to monthlyNetIncomeId
        defaultFinancialInfoShouldBeFound("monthlyNetIncomeId.equals=" + monthlyNetIncomeId);

        // Get all the financialInfoList where monthlyNetIncome equals to monthlyNetIncomeId + 1
        defaultFinancialInfoShouldNotBeFound("monthlyNetIncomeId.equals=" + (monthlyNetIncomeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFinancialInfoShouldBeFound(String filter) throws Exception {
        restFinancialInfoMockMvc.perform(get("/api/financial-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(financialInfo.getId().intValue())));

        // Check, that the count call also returns 1
        restFinancialInfoMockMvc.perform(get("/api/financial-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFinancialInfoShouldNotBeFound(String filter) throws Exception {
        restFinancialInfoMockMvc.perform(get("/api/financial-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFinancialInfoMockMvc.perform(get("/api/financial-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFinancialInfo() throws Exception {
        // Get the financialInfo
        restFinancialInfoMockMvc.perform(get("/api/financial-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinancialInfo() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);

        int databaseSizeBeforeUpdate = financialInfoRepository.findAll().size();

        // Update the financialInfo
        FinancialInfo updatedFinancialInfo = financialInfoRepository.findById(financialInfo.getId()).get();
        // Disconnect from session so that the updates on updatedFinancialInfo are not directly saved in db
        em.detach(updatedFinancialInfo);
        FinancialInfoDTO financialInfoDTO = financialInfoMapper.toDto(updatedFinancialInfo);

        restFinancialInfoMockMvc.perform(put("/api/financial-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(financialInfoDTO)))
            .andExpect(status().isOk());

        // Validate the FinancialInfo in the database
        List<FinancialInfo> financialInfoList = financialInfoRepository.findAll();
        assertThat(financialInfoList).hasSize(databaseSizeBeforeUpdate);
        FinancialInfo testFinancialInfo = financialInfoList.get(financialInfoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingFinancialInfo() throws Exception {
        int databaseSizeBeforeUpdate = financialInfoRepository.findAll().size();

        // Create the FinancialInfo
        FinancialInfoDTO financialInfoDTO = financialInfoMapper.toDto(financialInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinancialInfoMockMvc.perform(put("/api/financial-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(financialInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinancialInfo in the database
        List<FinancialInfo> financialInfoList = financialInfoRepository.findAll();
        assertThat(financialInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinancialInfo() throws Exception {
        // Initialize the database
        financialInfoRepository.saveAndFlush(financialInfo);

        int databaseSizeBeforeDelete = financialInfoRepository.findAll().size();

        // Delete the financialInfo
        restFinancialInfoMockMvc.perform(delete("/api/financial-infos/{id}", financialInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FinancialInfo> financialInfoList = financialInfoRepository.findAll();
        assertThat(financialInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
