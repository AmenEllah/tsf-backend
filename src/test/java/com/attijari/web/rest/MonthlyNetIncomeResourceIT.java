package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.MonthlyNetIncome;
import com.attijari.repository.MonthlyNetIncomeRepository;
import com.attijari.service.MonthlyNetIncomeService;
import com.attijari.service.dto.MonthlyNetIncomeDTO;
import com.attijari.service.mapper.MonthlyNetIncomeMapper;
import com.attijari.service.MonthlyNetIncomeQueryService;

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
 * Integration tests for the {@link MonthlyNetIncomeResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MonthlyNetIncomeResourceIT {

    private static final String DEFAULT_INCOMES_FR = "AAAAAAAAAA";
    private static final String UPDATED_INCOMES_FR = "BBBBBBBBBB";

    private static final String DEFAULT_INCOMES_EN = "AAAAAAAAAA";
    private static final String UPDATED_INCOMES_EN = "BBBBBBBBBB";

    @Autowired
    private MonthlyNetIncomeRepository monthlyNetIncomeRepository;

    @Autowired
    private MonthlyNetIncomeMapper monthlyNetIncomeMapper;

    @Autowired
    private MonthlyNetIncomeService monthlyNetIncomeService;

    @Autowired
    private MonthlyNetIncomeQueryService monthlyNetIncomeQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMonthlyNetIncomeMockMvc;

    private MonthlyNetIncome monthlyNetIncome;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MonthlyNetIncome createEntity(EntityManager em) {
        MonthlyNetIncome monthlyNetIncome = new MonthlyNetIncome()
            .incomesFR(DEFAULT_INCOMES_FR)
            .incomesEN(DEFAULT_INCOMES_EN);
        return monthlyNetIncome;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MonthlyNetIncome createUpdatedEntity(EntityManager em) {
        MonthlyNetIncome monthlyNetIncome = new MonthlyNetIncome()
            .incomesFR(UPDATED_INCOMES_FR)
            .incomesEN(UPDATED_INCOMES_EN);
        return monthlyNetIncome;
    }

    @BeforeEach
    public void initTest() {
        monthlyNetIncome = createEntity(em);
    }

    @Test
    @Transactional
    public void createMonthlyNetIncome() throws Exception {
        int databaseSizeBeforeCreate = monthlyNetIncomeRepository.findAll().size();
        // Create the MonthlyNetIncome
        MonthlyNetIncomeDTO monthlyNetIncomeDTO = monthlyNetIncomeMapper.toDto(monthlyNetIncome);
        restMonthlyNetIncomeMockMvc.perform(post("/api/monthly-net-incomes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(monthlyNetIncomeDTO)))
            .andExpect(status().isCreated());

        // Validate the MonthlyNetIncome in the database
        List<MonthlyNetIncome> monthlyNetIncomeList = monthlyNetIncomeRepository.findAll();
        assertThat(monthlyNetIncomeList).hasSize(databaseSizeBeforeCreate + 1);
        MonthlyNetIncome testMonthlyNetIncome = monthlyNetIncomeList.get(monthlyNetIncomeList.size() - 1);
        assertThat(testMonthlyNetIncome.getIncomesFR()).isEqualTo(DEFAULT_INCOMES_FR);
        assertThat(testMonthlyNetIncome.getIncomesEN()).isEqualTo(DEFAULT_INCOMES_EN);
    }

    @Test
    @Transactional
    public void createMonthlyNetIncomeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = monthlyNetIncomeRepository.findAll().size();

        // Create the MonthlyNetIncome with an existing ID
        monthlyNetIncome.setId(1L);
        MonthlyNetIncomeDTO monthlyNetIncomeDTO = monthlyNetIncomeMapper.toDto(monthlyNetIncome);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMonthlyNetIncomeMockMvc.perform(post("/api/monthly-net-incomes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(monthlyNetIncomeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MonthlyNetIncome in the database
        List<MonthlyNetIncome> monthlyNetIncomeList = monthlyNetIncomeRepository.findAll();
        assertThat(monthlyNetIncomeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMonthlyNetIncomes() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList
        restMonthlyNetIncomeMockMvc.perform(get("/api/monthly-net-incomes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monthlyNetIncome.getId().intValue())))
            .andExpect(jsonPath("$.[*].incomesFR").value(hasItem(DEFAULT_INCOMES_FR)))
            .andExpect(jsonPath("$.[*].incomesEN").value(hasItem(DEFAULT_INCOMES_EN)));
    }

    @Test
    @Transactional
    public void getMonthlyNetIncome() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get the monthlyNetIncome
        restMonthlyNetIncomeMockMvc.perform(get("/api/monthly-net-incomes/{id}", monthlyNetIncome.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(monthlyNetIncome.getId().intValue()))
            .andExpect(jsonPath("$.incomesFR").value(DEFAULT_INCOMES_FR))
            .andExpect(jsonPath("$.incomesEN").value(DEFAULT_INCOMES_EN));
    }


    @Test
    @Transactional
    public void getMonthlyNetIncomesByIdFiltering() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        Long id = monthlyNetIncome.getId();

        defaultMonthlyNetIncomeShouldBeFound("id.equals=" + id);
        defaultMonthlyNetIncomeShouldNotBeFound("id.notEquals=" + id);

        defaultMonthlyNetIncomeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultMonthlyNetIncomeShouldNotBeFound("id.greaterThan=" + id);

        defaultMonthlyNetIncomeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultMonthlyNetIncomeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesFRIsEqualToSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesFR equals to DEFAULT_INCOMES_FR
        defaultMonthlyNetIncomeShouldBeFound("incomesFR.equals=" + DEFAULT_INCOMES_FR);

        // Get all the monthlyNetIncomeList where incomesFR equals to UPDATED_INCOMES_FR
        defaultMonthlyNetIncomeShouldNotBeFound("incomesFR.equals=" + UPDATED_INCOMES_FR);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesFRIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesFR not equals to DEFAULT_INCOMES_FR
        defaultMonthlyNetIncomeShouldNotBeFound("incomesFR.notEquals=" + DEFAULT_INCOMES_FR);

        // Get all the monthlyNetIncomeList where incomesFR not equals to UPDATED_INCOMES_FR
        defaultMonthlyNetIncomeShouldBeFound("incomesFR.notEquals=" + UPDATED_INCOMES_FR);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesFRIsInShouldWork() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesFR in DEFAULT_INCOMES_FR or UPDATED_INCOMES_FR
        defaultMonthlyNetIncomeShouldBeFound("incomesFR.in=" + DEFAULT_INCOMES_FR + "," + UPDATED_INCOMES_FR);

        // Get all the monthlyNetIncomeList where incomesFR equals to UPDATED_INCOMES_FR
        defaultMonthlyNetIncomeShouldNotBeFound("incomesFR.in=" + UPDATED_INCOMES_FR);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesFRIsNullOrNotNull() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesFR is not null
        defaultMonthlyNetIncomeShouldBeFound("incomesFR.specified=true");

        // Get all the monthlyNetIncomeList where incomesFR is null
        defaultMonthlyNetIncomeShouldNotBeFound("incomesFR.specified=false");
    }
                @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesFRContainsSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesFR contains DEFAULT_INCOMES_FR
        defaultMonthlyNetIncomeShouldBeFound("incomesFR.contains=" + DEFAULT_INCOMES_FR);

        // Get all the monthlyNetIncomeList where incomesFR contains UPDATED_INCOMES_FR
        defaultMonthlyNetIncomeShouldNotBeFound("incomesFR.contains=" + UPDATED_INCOMES_FR);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesFRNotContainsSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesFR does not contain DEFAULT_INCOMES_FR
        defaultMonthlyNetIncomeShouldNotBeFound("incomesFR.doesNotContain=" + DEFAULT_INCOMES_FR);

        // Get all the monthlyNetIncomeList where incomesFR does not contain UPDATED_INCOMES_FR
        defaultMonthlyNetIncomeShouldBeFound("incomesFR.doesNotContain=" + UPDATED_INCOMES_FR);
    }


    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesENIsEqualToSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesEN equals to DEFAULT_INCOMES_EN
        defaultMonthlyNetIncomeShouldBeFound("incomesEN.equals=" + DEFAULT_INCOMES_EN);

        // Get all the monthlyNetIncomeList where incomesEN equals to UPDATED_INCOMES_EN
        defaultMonthlyNetIncomeShouldNotBeFound("incomesEN.equals=" + UPDATED_INCOMES_EN);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesENIsNotEqualToSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesEN not equals to DEFAULT_INCOMES_EN
        defaultMonthlyNetIncomeShouldNotBeFound("incomesEN.notEquals=" + DEFAULT_INCOMES_EN);

        // Get all the monthlyNetIncomeList where incomesEN not equals to UPDATED_INCOMES_EN
        defaultMonthlyNetIncomeShouldBeFound("incomesEN.notEquals=" + UPDATED_INCOMES_EN);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesENIsInShouldWork() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesEN in DEFAULT_INCOMES_EN or UPDATED_INCOMES_EN
        defaultMonthlyNetIncomeShouldBeFound("incomesEN.in=" + DEFAULT_INCOMES_EN + "," + UPDATED_INCOMES_EN);

        // Get all the monthlyNetIncomeList where incomesEN equals to UPDATED_INCOMES_EN
        defaultMonthlyNetIncomeShouldNotBeFound("incomesEN.in=" + UPDATED_INCOMES_EN);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesENIsNullOrNotNull() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesEN is not null
        defaultMonthlyNetIncomeShouldBeFound("incomesEN.specified=true");

        // Get all the monthlyNetIncomeList where incomesEN is null
        defaultMonthlyNetIncomeShouldNotBeFound("incomesEN.specified=false");
    }
                @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesENContainsSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesEN contains DEFAULT_INCOMES_EN
        defaultMonthlyNetIncomeShouldBeFound("incomesEN.contains=" + DEFAULT_INCOMES_EN);

        // Get all the monthlyNetIncomeList where incomesEN contains UPDATED_INCOMES_EN
        defaultMonthlyNetIncomeShouldNotBeFound("incomesEN.contains=" + UPDATED_INCOMES_EN);
    }

    @Test
    @Transactional
    public void getAllMonthlyNetIncomesByIncomesENNotContainsSomething() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        // Get all the monthlyNetIncomeList where incomesEN does not contain DEFAULT_INCOMES_EN
        defaultMonthlyNetIncomeShouldNotBeFound("incomesEN.doesNotContain=" + DEFAULT_INCOMES_EN);

        // Get all the monthlyNetIncomeList where incomesEN does not contain UPDATED_INCOMES_EN
        defaultMonthlyNetIncomeShouldBeFound("incomesEN.doesNotContain=" + UPDATED_INCOMES_EN);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMonthlyNetIncomeShouldBeFound(String filter) throws Exception {
        restMonthlyNetIncomeMockMvc.perform(get("/api/monthly-net-incomes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monthlyNetIncome.getId().intValue())))
            .andExpect(jsonPath("$.[*].incomesFR").value(hasItem(DEFAULT_INCOMES_FR)))
            .andExpect(jsonPath("$.[*].incomesEN").value(hasItem(DEFAULT_INCOMES_EN)));

        // Check, that the count call also returns 1
        restMonthlyNetIncomeMockMvc.perform(get("/api/monthly-net-incomes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMonthlyNetIncomeShouldNotBeFound(String filter) throws Exception {
        restMonthlyNetIncomeMockMvc.perform(get("/api/monthly-net-incomes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMonthlyNetIncomeMockMvc.perform(get("/api/monthly-net-incomes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingMonthlyNetIncome() throws Exception {
        // Get the monthlyNetIncome
        restMonthlyNetIncomeMockMvc.perform(get("/api/monthly-net-incomes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMonthlyNetIncome() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        int databaseSizeBeforeUpdate = monthlyNetIncomeRepository.findAll().size();

        // Update the monthlyNetIncome
        MonthlyNetIncome updatedMonthlyNetIncome = monthlyNetIncomeRepository.findById(monthlyNetIncome.getId()).get();
        // Disconnect from session so that the updates on updatedMonthlyNetIncome are not directly saved in db
        em.detach(updatedMonthlyNetIncome);
        updatedMonthlyNetIncome
            .incomesFR(UPDATED_INCOMES_FR)
            .incomesEN(UPDATED_INCOMES_EN);
        MonthlyNetIncomeDTO monthlyNetIncomeDTO = monthlyNetIncomeMapper.toDto(updatedMonthlyNetIncome);

        restMonthlyNetIncomeMockMvc.perform(put("/api/monthly-net-incomes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(monthlyNetIncomeDTO)))
            .andExpect(status().isOk());

        // Validate the MonthlyNetIncome in the database
        List<MonthlyNetIncome> monthlyNetIncomeList = monthlyNetIncomeRepository.findAll();
        assertThat(monthlyNetIncomeList).hasSize(databaseSizeBeforeUpdate);
        MonthlyNetIncome testMonthlyNetIncome = monthlyNetIncomeList.get(monthlyNetIncomeList.size() - 1);
        assertThat(testMonthlyNetIncome.getIncomesFR()).isEqualTo(UPDATED_INCOMES_FR);
        assertThat(testMonthlyNetIncome.getIncomesEN()).isEqualTo(UPDATED_INCOMES_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingMonthlyNetIncome() throws Exception {
        int databaseSizeBeforeUpdate = monthlyNetIncomeRepository.findAll().size();

        // Create the MonthlyNetIncome
        MonthlyNetIncomeDTO monthlyNetIncomeDTO = monthlyNetIncomeMapper.toDto(monthlyNetIncome);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMonthlyNetIncomeMockMvc.perform(put("/api/monthly-net-incomes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(monthlyNetIncomeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MonthlyNetIncome in the database
        List<MonthlyNetIncome> monthlyNetIncomeList = monthlyNetIncomeRepository.findAll();
        assertThat(monthlyNetIncomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMonthlyNetIncome() throws Exception {
        // Initialize the database
        monthlyNetIncomeRepository.saveAndFlush(monthlyNetIncome);

        int databaseSizeBeforeDelete = monthlyNetIncomeRepository.findAll().size();

        // Delete the monthlyNetIncome
        restMonthlyNetIncomeMockMvc.perform(delete("/api/monthly-net-incomes/{id}", monthlyNetIncome.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MonthlyNetIncome> monthlyNetIncomeList = monthlyNetIncomeRepository.findAll();
        assertThat(monthlyNetIncomeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
