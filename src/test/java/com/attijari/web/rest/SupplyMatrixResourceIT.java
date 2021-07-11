package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.SupplyMatrix;
import com.attijari.repository.SupplyMatrixRepository;
import com.attijari.service.SupplyMatrixService;
import com.attijari.service.dto.SupplyMatrixDTO;
import com.attijari.service.mapper.SupplyMatrixMapper;
import com.attijari.service.SupplyMatrixQueryService;

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
 * Integration tests for the {@link SupplyMatrixResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SupplyMatrixResourceIT {

    private static final Long DEFAULT_CATEGORY_ID = 1L;
    private static final Long UPDATED_CATEGORY_ID = 2L;
    private static final Long SMALLER_CATEGORY_ID = 1L - 1L;

    private static final String DEFAULT_INCOME_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INCOME_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_INCOME_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INCOME_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_MONTHLY_INCOME_ID = 1L;
    private static final Long UPDATED_MONTHLY_INCOME_ID = 2L;
    private static final Long SMALLER_MONTHLY_INCOME_ID = 1L - 1L;

    private static final Long DEFAULT_MARKET_CODE = 1L;
    private static final Long UPDATED_MARKET_CODE = 2L;
    private static final Long SMALLER_MARKET_CODE = 1L - 1L;

    private static final String DEFAULT_MARKET = "AAAAAAAAAA";
    private static final String UPDATED_MARKET = "BBBBBBBBBB";

    private static final String DEFAULT_SEGMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SEGMENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SEGMENT = "AAAAAAAAAA";
    private static final String UPDATED_SEGMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_ACTIVITY_ID = 1L;
    private static final Long UPDATED_ACTIVITY_ID = 2L;
    private static final Long SMALLER_ACTIVITY_ID = 1L - 1L;

    private static final String DEFAULT_PROFESSION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    @Autowired
    private SupplyMatrixRepository supplyMatrixRepository;

    @Autowired
    private SupplyMatrixMapper supplyMatrixMapper;

    @Autowired
    private SupplyMatrixService supplyMatrixService;

    @Autowired
    private SupplyMatrixQueryService supplyMatrixQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSupplyMatrixMockMvc;

    private SupplyMatrix supplyMatrix;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SupplyMatrix createEntity(EntityManager em) {
        SupplyMatrix supplyMatrix = new SupplyMatrix()
            .categoryId(DEFAULT_CATEGORY_ID)
            .incomeTypeCode(DEFAULT_INCOME_TYPE_CODE)
            .incomeType(DEFAULT_INCOME_TYPE)
            .monthlyIncomeId(DEFAULT_MONTHLY_INCOME_ID)
            .marketCode(DEFAULT_MARKET_CODE)
            .market(DEFAULT_MARKET)
            .segmentCode(DEFAULT_SEGMENT_CODE)
            .segment(DEFAULT_SEGMENT)
            .activityId(DEFAULT_ACTIVITY_ID)
            .professionCode(DEFAULT_PROFESSION_CODE)
            .profession(DEFAULT_PROFESSION);
        return supplyMatrix;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SupplyMatrix createUpdatedEntity(EntityManager em) {
        SupplyMatrix supplyMatrix = new SupplyMatrix()
            .categoryId(UPDATED_CATEGORY_ID)
            .incomeTypeCode(UPDATED_INCOME_TYPE_CODE)
            .incomeType(UPDATED_INCOME_TYPE)
            .monthlyIncomeId(UPDATED_MONTHLY_INCOME_ID)
            .marketCode(UPDATED_MARKET_CODE)
            .market(UPDATED_MARKET)
            .segmentCode(UPDATED_SEGMENT_CODE)
            .segment(UPDATED_SEGMENT)
            .activityId(UPDATED_ACTIVITY_ID)
            .professionCode(UPDATED_PROFESSION_CODE)
            .profession(UPDATED_PROFESSION);
        return supplyMatrix;
    }

    @BeforeEach
    public void initTest() {
        supplyMatrix = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupplyMatrix() throws Exception {
        int databaseSizeBeforeCreate = supplyMatrixRepository.findAll().size();
        // Create the SupplyMatrix
        SupplyMatrixDTO supplyMatrixDTO = supplyMatrixMapper.toDto(supplyMatrix);
        restSupplyMatrixMockMvc.perform(post("/api/supply-matrices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyMatrixDTO)))
            .andExpect(status().isCreated());

        // Validate the SupplyMatrix in the database
        List<SupplyMatrix> supplyMatrixList = supplyMatrixRepository.findAll();
        assertThat(supplyMatrixList).hasSize(databaseSizeBeforeCreate + 1);
        SupplyMatrix testSupplyMatrix = supplyMatrixList.get(supplyMatrixList.size() - 1);
        assertThat(testSupplyMatrix.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testSupplyMatrix.getIncomeTypeCode()).isEqualTo(DEFAULT_INCOME_TYPE_CODE);
        assertThat(testSupplyMatrix.getIncomeType()).isEqualTo(DEFAULT_INCOME_TYPE);
        assertThat(testSupplyMatrix.getMonthlyIncomeId()).isEqualTo(DEFAULT_MONTHLY_INCOME_ID);
        assertThat(testSupplyMatrix.getMarketCode()).isEqualTo(DEFAULT_MARKET_CODE);
        assertThat(testSupplyMatrix.getMarket()).isEqualTo(DEFAULT_MARKET);
        assertThat(testSupplyMatrix.getSegmentCode()).isEqualTo(DEFAULT_SEGMENT_CODE);
        assertThat(testSupplyMatrix.getSegment()).isEqualTo(DEFAULT_SEGMENT);
        assertThat(testSupplyMatrix.getActivityId()).isEqualTo(DEFAULT_ACTIVITY_ID);
        assertThat(testSupplyMatrix.getProfessionCode()).isEqualTo(DEFAULT_PROFESSION_CODE);
        assertThat(testSupplyMatrix.getProfession()).isEqualTo(DEFAULT_PROFESSION);
    }

    @Test
    @Transactional
    public void createSupplyMatrixWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supplyMatrixRepository.findAll().size();

        // Create the SupplyMatrix with an existing ID
        supplyMatrix.setId(1L);
        SupplyMatrixDTO supplyMatrixDTO = supplyMatrixMapper.toDto(supplyMatrix);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplyMatrixMockMvc.perform(post("/api/supply-matrices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyMatrixDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SupplyMatrix in the database
        List<SupplyMatrix> supplyMatrixList = supplyMatrixRepository.findAll();
        assertThat(supplyMatrixList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSupplyMatrices() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList
        restSupplyMatrixMockMvc.perform(get("/api/supply-matrices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplyMatrix.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID.intValue())))
            .andExpect(jsonPath("$.[*].incomeTypeCode").value(hasItem(DEFAULT_INCOME_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].incomeType").value(hasItem(DEFAULT_INCOME_TYPE)))
            .andExpect(jsonPath("$.[*].monthlyIncomeId").value(hasItem(DEFAULT_MONTHLY_INCOME_ID.intValue())))
            .andExpect(jsonPath("$.[*].marketCode").value(hasItem(DEFAULT_MARKET_CODE.intValue())))
            .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET)))
            .andExpect(jsonPath("$.[*].segmentCode").value(hasItem(DEFAULT_SEGMENT_CODE)))
            .andExpect(jsonPath("$.[*].segment").value(hasItem(DEFAULT_SEGMENT)))
            .andExpect(jsonPath("$.[*].activityId").value(hasItem(DEFAULT_ACTIVITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].professionCode").value(hasItem(DEFAULT_PROFESSION_CODE)))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)));
    }

    @Test
    @Transactional
    public void getSupplyMatrix() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get the supplyMatrix
        restSupplyMatrixMockMvc.perform(get("/api/supply-matrices/{id}", supplyMatrix.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(supplyMatrix.getId().intValue()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID.intValue()))
            .andExpect(jsonPath("$.incomeTypeCode").value(DEFAULT_INCOME_TYPE_CODE))
            .andExpect(jsonPath("$.incomeType").value(DEFAULT_INCOME_TYPE))
            .andExpect(jsonPath("$.monthlyIncomeId").value(DEFAULT_MONTHLY_INCOME_ID.intValue()))
            .andExpect(jsonPath("$.marketCode").value(DEFAULT_MARKET_CODE.intValue()))
            .andExpect(jsonPath("$.market").value(DEFAULT_MARKET))
            .andExpect(jsonPath("$.segmentCode").value(DEFAULT_SEGMENT_CODE))
            .andExpect(jsonPath("$.segment").value(DEFAULT_SEGMENT))
            .andExpect(jsonPath("$.activityId").value(DEFAULT_ACTIVITY_ID.intValue()))
            .andExpect(jsonPath("$.professionCode").value(DEFAULT_PROFESSION_CODE))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION));
    }


    @Test
    @Transactional
    public void getSupplyMatricesByIdFiltering() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        Long id = supplyMatrix.getId();

        defaultSupplyMatrixShouldBeFound("id.equals=" + id);
        defaultSupplyMatrixShouldNotBeFound("id.notEquals=" + id);

        defaultSupplyMatrixShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSupplyMatrixShouldNotBeFound("id.greaterThan=" + id);

        defaultSupplyMatrixShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSupplyMatrixShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId equals to DEFAULT_CATEGORY_ID
        defaultSupplyMatrixShouldBeFound("categoryId.equals=" + DEFAULT_CATEGORY_ID);

        // Get all the supplyMatrixList where categoryId equals to UPDATED_CATEGORY_ID
        defaultSupplyMatrixShouldNotBeFound("categoryId.equals=" + UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId not equals to DEFAULT_CATEGORY_ID
        defaultSupplyMatrixShouldNotBeFound("categoryId.notEquals=" + DEFAULT_CATEGORY_ID);

        // Get all the supplyMatrixList where categoryId not equals to UPDATED_CATEGORY_ID
        defaultSupplyMatrixShouldBeFound("categoryId.notEquals=" + UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId in DEFAULT_CATEGORY_ID or UPDATED_CATEGORY_ID
        defaultSupplyMatrixShouldBeFound("categoryId.in=" + DEFAULT_CATEGORY_ID + "," + UPDATED_CATEGORY_ID);

        // Get all the supplyMatrixList where categoryId equals to UPDATED_CATEGORY_ID
        defaultSupplyMatrixShouldNotBeFound("categoryId.in=" + UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId is not null
        defaultSupplyMatrixShouldBeFound("categoryId.specified=true");

        // Get all the supplyMatrixList where categoryId is null
        defaultSupplyMatrixShouldNotBeFound("categoryId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId is greater than or equal to DEFAULT_CATEGORY_ID
        defaultSupplyMatrixShouldBeFound("categoryId.greaterThanOrEqual=" + DEFAULT_CATEGORY_ID);

        // Get all the supplyMatrixList where categoryId is greater than or equal to UPDATED_CATEGORY_ID
        defaultSupplyMatrixShouldNotBeFound("categoryId.greaterThanOrEqual=" + UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId is less than or equal to DEFAULT_CATEGORY_ID
        defaultSupplyMatrixShouldBeFound("categoryId.lessThanOrEqual=" + DEFAULT_CATEGORY_ID);

        // Get all the supplyMatrixList where categoryId is less than or equal to SMALLER_CATEGORY_ID
        defaultSupplyMatrixShouldNotBeFound("categoryId.lessThanOrEqual=" + SMALLER_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsLessThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId is less than DEFAULT_CATEGORY_ID
        defaultSupplyMatrixShouldNotBeFound("categoryId.lessThan=" + DEFAULT_CATEGORY_ID);

        // Get all the supplyMatrixList where categoryId is less than UPDATED_CATEGORY_ID
        defaultSupplyMatrixShouldBeFound("categoryId.lessThan=" + UPDATED_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByCategoryIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where categoryId is greater than DEFAULT_CATEGORY_ID
        defaultSupplyMatrixShouldNotBeFound("categoryId.greaterThan=" + DEFAULT_CATEGORY_ID);

        // Get all the supplyMatrixList where categoryId is greater than SMALLER_CATEGORY_ID
        defaultSupplyMatrixShouldBeFound("categoryId.greaterThan=" + SMALLER_CATEGORY_ID);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeTypeCode equals to DEFAULT_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldBeFound("incomeTypeCode.equals=" + DEFAULT_INCOME_TYPE_CODE);

        // Get all the supplyMatrixList where incomeTypeCode equals to UPDATED_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldNotBeFound("incomeTypeCode.equals=" + UPDATED_INCOME_TYPE_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeTypeCode not equals to DEFAULT_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldNotBeFound("incomeTypeCode.notEquals=" + DEFAULT_INCOME_TYPE_CODE);

        // Get all the supplyMatrixList where incomeTypeCode not equals to UPDATED_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldBeFound("incomeTypeCode.notEquals=" + UPDATED_INCOME_TYPE_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeCodeIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeTypeCode in DEFAULT_INCOME_TYPE_CODE or UPDATED_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldBeFound("incomeTypeCode.in=" + DEFAULT_INCOME_TYPE_CODE + "," + UPDATED_INCOME_TYPE_CODE);

        // Get all the supplyMatrixList where incomeTypeCode equals to UPDATED_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldNotBeFound("incomeTypeCode.in=" + UPDATED_INCOME_TYPE_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeTypeCode is not null
        defaultSupplyMatrixShouldBeFound("incomeTypeCode.specified=true");

        // Get all the supplyMatrixList where incomeTypeCode is null
        defaultSupplyMatrixShouldNotBeFound("incomeTypeCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeCodeContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeTypeCode contains DEFAULT_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldBeFound("incomeTypeCode.contains=" + DEFAULT_INCOME_TYPE_CODE);

        // Get all the supplyMatrixList where incomeTypeCode contains UPDATED_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldNotBeFound("incomeTypeCode.contains=" + UPDATED_INCOME_TYPE_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeCodeNotContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeTypeCode does not contain DEFAULT_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldNotBeFound("incomeTypeCode.doesNotContain=" + DEFAULT_INCOME_TYPE_CODE);

        // Get all the supplyMatrixList where incomeTypeCode does not contain UPDATED_INCOME_TYPE_CODE
        defaultSupplyMatrixShouldBeFound("incomeTypeCode.doesNotContain=" + UPDATED_INCOME_TYPE_CODE);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeType equals to DEFAULT_INCOME_TYPE
        defaultSupplyMatrixShouldBeFound("incomeType.equals=" + DEFAULT_INCOME_TYPE);

        // Get all the supplyMatrixList where incomeType equals to UPDATED_INCOME_TYPE
        defaultSupplyMatrixShouldNotBeFound("incomeType.equals=" + UPDATED_INCOME_TYPE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeType not equals to DEFAULT_INCOME_TYPE
        defaultSupplyMatrixShouldNotBeFound("incomeType.notEquals=" + DEFAULT_INCOME_TYPE);

        // Get all the supplyMatrixList where incomeType not equals to UPDATED_INCOME_TYPE
        defaultSupplyMatrixShouldBeFound("incomeType.notEquals=" + UPDATED_INCOME_TYPE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeType in DEFAULT_INCOME_TYPE or UPDATED_INCOME_TYPE
        defaultSupplyMatrixShouldBeFound("incomeType.in=" + DEFAULT_INCOME_TYPE + "," + UPDATED_INCOME_TYPE);

        // Get all the supplyMatrixList where incomeType equals to UPDATED_INCOME_TYPE
        defaultSupplyMatrixShouldNotBeFound("incomeType.in=" + UPDATED_INCOME_TYPE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeType is not null
        defaultSupplyMatrixShouldBeFound("incomeType.specified=true");

        // Get all the supplyMatrixList where incomeType is null
        defaultSupplyMatrixShouldNotBeFound("incomeType.specified=false");
    }
                @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeType contains DEFAULT_INCOME_TYPE
        defaultSupplyMatrixShouldBeFound("incomeType.contains=" + DEFAULT_INCOME_TYPE);

        // Get all the supplyMatrixList where incomeType contains UPDATED_INCOME_TYPE
        defaultSupplyMatrixShouldNotBeFound("incomeType.contains=" + UPDATED_INCOME_TYPE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByIncomeTypeNotContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where incomeType does not contain DEFAULT_INCOME_TYPE
        defaultSupplyMatrixShouldNotBeFound("incomeType.doesNotContain=" + DEFAULT_INCOME_TYPE);

        // Get all the supplyMatrixList where incomeType does not contain UPDATED_INCOME_TYPE
        defaultSupplyMatrixShouldBeFound("incomeType.doesNotContain=" + UPDATED_INCOME_TYPE);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId equals to DEFAULT_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.equals=" + DEFAULT_MONTHLY_INCOME_ID);

        // Get all the supplyMatrixList where monthlyIncomeId equals to UPDATED_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.equals=" + UPDATED_MONTHLY_INCOME_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId not equals to DEFAULT_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.notEquals=" + DEFAULT_MONTHLY_INCOME_ID);

        // Get all the supplyMatrixList where monthlyIncomeId not equals to UPDATED_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.notEquals=" + UPDATED_MONTHLY_INCOME_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId in DEFAULT_MONTHLY_INCOME_ID or UPDATED_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.in=" + DEFAULT_MONTHLY_INCOME_ID + "," + UPDATED_MONTHLY_INCOME_ID);

        // Get all the supplyMatrixList where monthlyIncomeId equals to UPDATED_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.in=" + UPDATED_MONTHLY_INCOME_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId is not null
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.specified=true");

        // Get all the supplyMatrixList where monthlyIncomeId is null
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId is greater than or equal to DEFAULT_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.greaterThanOrEqual=" + DEFAULT_MONTHLY_INCOME_ID);

        // Get all the supplyMatrixList where monthlyIncomeId is greater than or equal to UPDATED_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.greaterThanOrEqual=" + UPDATED_MONTHLY_INCOME_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId is less than or equal to DEFAULT_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.lessThanOrEqual=" + DEFAULT_MONTHLY_INCOME_ID);

        // Get all the supplyMatrixList where monthlyIncomeId is less than or equal to SMALLER_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.lessThanOrEqual=" + SMALLER_MONTHLY_INCOME_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsLessThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId is less than DEFAULT_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.lessThan=" + DEFAULT_MONTHLY_INCOME_ID);

        // Get all the supplyMatrixList where monthlyIncomeId is less than UPDATED_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.lessThan=" + UPDATED_MONTHLY_INCOME_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMonthlyIncomeIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where monthlyIncomeId is greater than DEFAULT_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldNotBeFound("monthlyIncomeId.greaterThan=" + DEFAULT_MONTHLY_INCOME_ID);

        // Get all the supplyMatrixList where monthlyIncomeId is greater than SMALLER_MONTHLY_INCOME_ID
        defaultSupplyMatrixShouldBeFound("monthlyIncomeId.greaterThan=" + SMALLER_MONTHLY_INCOME_ID);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode equals to DEFAULT_MARKET_CODE
        defaultSupplyMatrixShouldBeFound("marketCode.equals=" + DEFAULT_MARKET_CODE);

        // Get all the supplyMatrixList where marketCode equals to UPDATED_MARKET_CODE
        defaultSupplyMatrixShouldNotBeFound("marketCode.equals=" + UPDATED_MARKET_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode not equals to DEFAULT_MARKET_CODE
        defaultSupplyMatrixShouldNotBeFound("marketCode.notEquals=" + DEFAULT_MARKET_CODE);

        // Get all the supplyMatrixList where marketCode not equals to UPDATED_MARKET_CODE
        defaultSupplyMatrixShouldBeFound("marketCode.notEquals=" + UPDATED_MARKET_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode in DEFAULT_MARKET_CODE or UPDATED_MARKET_CODE
        defaultSupplyMatrixShouldBeFound("marketCode.in=" + DEFAULT_MARKET_CODE + "," + UPDATED_MARKET_CODE);

        // Get all the supplyMatrixList where marketCode equals to UPDATED_MARKET_CODE
        defaultSupplyMatrixShouldNotBeFound("marketCode.in=" + UPDATED_MARKET_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode is not null
        defaultSupplyMatrixShouldBeFound("marketCode.specified=true");

        // Get all the supplyMatrixList where marketCode is null
        defaultSupplyMatrixShouldNotBeFound("marketCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode is greater than or equal to DEFAULT_MARKET_CODE
        defaultSupplyMatrixShouldBeFound("marketCode.greaterThanOrEqual=" + DEFAULT_MARKET_CODE);

        // Get all the supplyMatrixList where marketCode is greater than or equal to UPDATED_MARKET_CODE
        defaultSupplyMatrixShouldNotBeFound("marketCode.greaterThanOrEqual=" + UPDATED_MARKET_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode is less than or equal to DEFAULT_MARKET_CODE
        defaultSupplyMatrixShouldBeFound("marketCode.lessThanOrEqual=" + DEFAULT_MARKET_CODE);

        // Get all the supplyMatrixList where marketCode is less than or equal to SMALLER_MARKET_CODE
        defaultSupplyMatrixShouldNotBeFound("marketCode.lessThanOrEqual=" + SMALLER_MARKET_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsLessThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode is less than DEFAULT_MARKET_CODE
        defaultSupplyMatrixShouldNotBeFound("marketCode.lessThan=" + DEFAULT_MARKET_CODE);

        // Get all the supplyMatrixList where marketCode is less than UPDATED_MARKET_CODE
        defaultSupplyMatrixShouldBeFound("marketCode.lessThan=" + UPDATED_MARKET_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketCodeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where marketCode is greater than DEFAULT_MARKET_CODE
        defaultSupplyMatrixShouldNotBeFound("marketCode.greaterThan=" + DEFAULT_MARKET_CODE);

        // Get all the supplyMatrixList where marketCode is greater than SMALLER_MARKET_CODE
        defaultSupplyMatrixShouldBeFound("marketCode.greaterThan=" + SMALLER_MARKET_CODE);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where market equals to DEFAULT_MARKET
        defaultSupplyMatrixShouldBeFound("market.equals=" + DEFAULT_MARKET);

        // Get all the supplyMatrixList where market equals to UPDATED_MARKET
        defaultSupplyMatrixShouldNotBeFound("market.equals=" + UPDATED_MARKET);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where market not equals to DEFAULT_MARKET
        defaultSupplyMatrixShouldNotBeFound("market.notEquals=" + DEFAULT_MARKET);

        // Get all the supplyMatrixList where market not equals to UPDATED_MARKET
        defaultSupplyMatrixShouldBeFound("market.notEquals=" + UPDATED_MARKET);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where market in DEFAULT_MARKET or UPDATED_MARKET
        defaultSupplyMatrixShouldBeFound("market.in=" + DEFAULT_MARKET + "," + UPDATED_MARKET);

        // Get all the supplyMatrixList where market equals to UPDATED_MARKET
        defaultSupplyMatrixShouldNotBeFound("market.in=" + UPDATED_MARKET);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where market is not null
        defaultSupplyMatrixShouldBeFound("market.specified=true");

        // Get all the supplyMatrixList where market is null
        defaultSupplyMatrixShouldNotBeFound("market.specified=false");
    }
                @Test
    @Transactional
    public void getAllSupplyMatricesByMarketContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where market contains DEFAULT_MARKET
        defaultSupplyMatrixShouldBeFound("market.contains=" + DEFAULT_MARKET);

        // Get all the supplyMatrixList where market contains UPDATED_MARKET
        defaultSupplyMatrixShouldNotBeFound("market.contains=" + UPDATED_MARKET);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByMarketNotContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where market does not contain DEFAULT_MARKET
        defaultSupplyMatrixShouldNotBeFound("market.doesNotContain=" + DEFAULT_MARKET);

        // Get all the supplyMatrixList where market does not contain UPDATED_MARKET
        defaultSupplyMatrixShouldBeFound("market.doesNotContain=" + UPDATED_MARKET);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segmentCode equals to DEFAULT_SEGMENT_CODE
        defaultSupplyMatrixShouldBeFound("segmentCode.equals=" + DEFAULT_SEGMENT_CODE);

        // Get all the supplyMatrixList where segmentCode equals to UPDATED_SEGMENT_CODE
        defaultSupplyMatrixShouldNotBeFound("segmentCode.equals=" + UPDATED_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segmentCode not equals to DEFAULT_SEGMENT_CODE
        defaultSupplyMatrixShouldNotBeFound("segmentCode.notEquals=" + DEFAULT_SEGMENT_CODE);

        // Get all the supplyMatrixList where segmentCode not equals to UPDATED_SEGMENT_CODE
        defaultSupplyMatrixShouldBeFound("segmentCode.notEquals=" + UPDATED_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segmentCode in DEFAULT_SEGMENT_CODE or UPDATED_SEGMENT_CODE
        defaultSupplyMatrixShouldBeFound("segmentCode.in=" + DEFAULT_SEGMENT_CODE + "," + UPDATED_SEGMENT_CODE);

        // Get all the supplyMatrixList where segmentCode equals to UPDATED_SEGMENT_CODE
        defaultSupplyMatrixShouldNotBeFound("segmentCode.in=" + UPDATED_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segmentCode is not null
        defaultSupplyMatrixShouldBeFound("segmentCode.specified=true");

        // Get all the supplyMatrixList where segmentCode is null
        defaultSupplyMatrixShouldNotBeFound("segmentCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentCodeContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segmentCode contains DEFAULT_SEGMENT_CODE
        defaultSupplyMatrixShouldBeFound("segmentCode.contains=" + DEFAULT_SEGMENT_CODE);

        // Get all the supplyMatrixList where segmentCode contains UPDATED_SEGMENT_CODE
        defaultSupplyMatrixShouldNotBeFound("segmentCode.contains=" + UPDATED_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentCodeNotContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segmentCode does not contain DEFAULT_SEGMENT_CODE
        defaultSupplyMatrixShouldNotBeFound("segmentCode.doesNotContain=" + DEFAULT_SEGMENT_CODE);

        // Get all the supplyMatrixList where segmentCode does not contain UPDATED_SEGMENT_CODE
        defaultSupplyMatrixShouldBeFound("segmentCode.doesNotContain=" + UPDATED_SEGMENT_CODE);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segment equals to DEFAULT_SEGMENT
        defaultSupplyMatrixShouldBeFound("segment.equals=" + DEFAULT_SEGMENT);

        // Get all the supplyMatrixList where segment equals to UPDATED_SEGMENT
        defaultSupplyMatrixShouldNotBeFound("segment.equals=" + UPDATED_SEGMENT);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segment not equals to DEFAULT_SEGMENT
        defaultSupplyMatrixShouldNotBeFound("segment.notEquals=" + DEFAULT_SEGMENT);

        // Get all the supplyMatrixList where segment not equals to UPDATED_SEGMENT
        defaultSupplyMatrixShouldBeFound("segment.notEquals=" + UPDATED_SEGMENT);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segment in DEFAULT_SEGMENT or UPDATED_SEGMENT
        defaultSupplyMatrixShouldBeFound("segment.in=" + DEFAULT_SEGMENT + "," + UPDATED_SEGMENT);

        // Get all the supplyMatrixList where segment equals to UPDATED_SEGMENT
        defaultSupplyMatrixShouldNotBeFound("segment.in=" + UPDATED_SEGMENT);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segment is not null
        defaultSupplyMatrixShouldBeFound("segment.specified=true");

        // Get all the supplyMatrixList where segment is null
        defaultSupplyMatrixShouldNotBeFound("segment.specified=false");
    }
                @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segment contains DEFAULT_SEGMENT
        defaultSupplyMatrixShouldBeFound("segment.contains=" + DEFAULT_SEGMENT);

        // Get all the supplyMatrixList where segment contains UPDATED_SEGMENT
        defaultSupplyMatrixShouldNotBeFound("segment.contains=" + UPDATED_SEGMENT);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesBySegmentNotContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where segment does not contain DEFAULT_SEGMENT
        defaultSupplyMatrixShouldNotBeFound("segment.doesNotContain=" + DEFAULT_SEGMENT);

        // Get all the supplyMatrixList where segment does not contain UPDATED_SEGMENT
        defaultSupplyMatrixShouldBeFound("segment.doesNotContain=" + UPDATED_SEGMENT);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId equals to DEFAULT_ACTIVITY_ID
        defaultSupplyMatrixShouldBeFound("activityId.equals=" + DEFAULT_ACTIVITY_ID);

        // Get all the supplyMatrixList where activityId equals to UPDATED_ACTIVITY_ID
        defaultSupplyMatrixShouldNotBeFound("activityId.equals=" + UPDATED_ACTIVITY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId not equals to DEFAULT_ACTIVITY_ID
        defaultSupplyMatrixShouldNotBeFound("activityId.notEquals=" + DEFAULT_ACTIVITY_ID);

        // Get all the supplyMatrixList where activityId not equals to UPDATED_ACTIVITY_ID
        defaultSupplyMatrixShouldBeFound("activityId.notEquals=" + UPDATED_ACTIVITY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId in DEFAULT_ACTIVITY_ID or UPDATED_ACTIVITY_ID
        defaultSupplyMatrixShouldBeFound("activityId.in=" + DEFAULT_ACTIVITY_ID + "," + UPDATED_ACTIVITY_ID);

        // Get all the supplyMatrixList where activityId equals to UPDATED_ACTIVITY_ID
        defaultSupplyMatrixShouldNotBeFound("activityId.in=" + UPDATED_ACTIVITY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId is not null
        defaultSupplyMatrixShouldBeFound("activityId.specified=true");

        // Get all the supplyMatrixList where activityId is null
        defaultSupplyMatrixShouldNotBeFound("activityId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId is greater than or equal to DEFAULT_ACTIVITY_ID
        defaultSupplyMatrixShouldBeFound("activityId.greaterThanOrEqual=" + DEFAULT_ACTIVITY_ID);

        // Get all the supplyMatrixList where activityId is greater than or equal to UPDATED_ACTIVITY_ID
        defaultSupplyMatrixShouldNotBeFound("activityId.greaterThanOrEqual=" + UPDATED_ACTIVITY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId is less than or equal to DEFAULT_ACTIVITY_ID
        defaultSupplyMatrixShouldBeFound("activityId.lessThanOrEqual=" + DEFAULT_ACTIVITY_ID);

        // Get all the supplyMatrixList where activityId is less than or equal to SMALLER_ACTIVITY_ID
        defaultSupplyMatrixShouldNotBeFound("activityId.lessThanOrEqual=" + SMALLER_ACTIVITY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsLessThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId is less than DEFAULT_ACTIVITY_ID
        defaultSupplyMatrixShouldNotBeFound("activityId.lessThan=" + DEFAULT_ACTIVITY_ID);

        // Get all the supplyMatrixList where activityId is less than UPDATED_ACTIVITY_ID
        defaultSupplyMatrixShouldBeFound("activityId.lessThan=" + UPDATED_ACTIVITY_ID);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByActivityIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where activityId is greater than DEFAULT_ACTIVITY_ID
        defaultSupplyMatrixShouldNotBeFound("activityId.greaterThan=" + DEFAULT_ACTIVITY_ID);

        // Get all the supplyMatrixList where activityId is greater than SMALLER_ACTIVITY_ID
        defaultSupplyMatrixShouldBeFound("activityId.greaterThan=" + SMALLER_ACTIVITY_ID);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where professionCode equals to DEFAULT_PROFESSION_CODE
        defaultSupplyMatrixShouldBeFound("professionCode.equals=" + DEFAULT_PROFESSION_CODE);

        // Get all the supplyMatrixList where professionCode equals to UPDATED_PROFESSION_CODE
        defaultSupplyMatrixShouldNotBeFound("professionCode.equals=" + UPDATED_PROFESSION_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where professionCode not equals to DEFAULT_PROFESSION_CODE
        defaultSupplyMatrixShouldNotBeFound("professionCode.notEquals=" + DEFAULT_PROFESSION_CODE);

        // Get all the supplyMatrixList where professionCode not equals to UPDATED_PROFESSION_CODE
        defaultSupplyMatrixShouldBeFound("professionCode.notEquals=" + UPDATED_PROFESSION_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionCodeIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where professionCode in DEFAULT_PROFESSION_CODE or UPDATED_PROFESSION_CODE
        defaultSupplyMatrixShouldBeFound("professionCode.in=" + DEFAULT_PROFESSION_CODE + "," + UPDATED_PROFESSION_CODE);

        // Get all the supplyMatrixList where professionCode equals to UPDATED_PROFESSION_CODE
        defaultSupplyMatrixShouldNotBeFound("professionCode.in=" + UPDATED_PROFESSION_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where professionCode is not null
        defaultSupplyMatrixShouldBeFound("professionCode.specified=true");

        // Get all the supplyMatrixList where professionCode is null
        defaultSupplyMatrixShouldNotBeFound("professionCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionCodeContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where professionCode contains DEFAULT_PROFESSION_CODE
        defaultSupplyMatrixShouldBeFound("professionCode.contains=" + DEFAULT_PROFESSION_CODE);

        // Get all the supplyMatrixList where professionCode contains UPDATED_PROFESSION_CODE
        defaultSupplyMatrixShouldNotBeFound("professionCode.contains=" + UPDATED_PROFESSION_CODE);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionCodeNotContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where professionCode does not contain DEFAULT_PROFESSION_CODE
        defaultSupplyMatrixShouldNotBeFound("professionCode.doesNotContain=" + DEFAULT_PROFESSION_CODE);

        // Get all the supplyMatrixList where professionCode does not contain UPDATED_PROFESSION_CODE
        defaultSupplyMatrixShouldBeFound("professionCode.doesNotContain=" + UPDATED_PROFESSION_CODE);
    }


    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionIsEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where profession equals to DEFAULT_PROFESSION
        defaultSupplyMatrixShouldBeFound("profession.equals=" + DEFAULT_PROFESSION);

        // Get all the supplyMatrixList where profession equals to UPDATED_PROFESSION
        defaultSupplyMatrixShouldNotBeFound("profession.equals=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where profession not equals to DEFAULT_PROFESSION
        defaultSupplyMatrixShouldNotBeFound("profession.notEquals=" + DEFAULT_PROFESSION);

        // Get all the supplyMatrixList where profession not equals to UPDATED_PROFESSION
        defaultSupplyMatrixShouldBeFound("profession.notEquals=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionIsInShouldWork() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where profession in DEFAULT_PROFESSION or UPDATED_PROFESSION
        defaultSupplyMatrixShouldBeFound("profession.in=" + DEFAULT_PROFESSION + "," + UPDATED_PROFESSION);

        // Get all the supplyMatrixList where profession equals to UPDATED_PROFESSION
        defaultSupplyMatrixShouldNotBeFound("profession.in=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionIsNullOrNotNull() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where profession is not null
        defaultSupplyMatrixShouldBeFound("profession.specified=true");

        // Get all the supplyMatrixList where profession is null
        defaultSupplyMatrixShouldNotBeFound("profession.specified=false");
    }
                @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where profession contains DEFAULT_PROFESSION
        defaultSupplyMatrixShouldBeFound("profession.contains=" + DEFAULT_PROFESSION);

        // Get all the supplyMatrixList where profession contains UPDATED_PROFESSION
        defaultSupplyMatrixShouldNotBeFound("profession.contains=" + UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void getAllSupplyMatricesByProfessionNotContainsSomething() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        // Get all the supplyMatrixList where profession does not contain DEFAULT_PROFESSION
        defaultSupplyMatrixShouldNotBeFound("profession.doesNotContain=" + DEFAULT_PROFESSION);

        // Get all the supplyMatrixList where profession does not contain UPDATED_PROFESSION
        defaultSupplyMatrixShouldBeFound("profession.doesNotContain=" + UPDATED_PROFESSION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSupplyMatrixShouldBeFound(String filter) throws Exception {
        restSupplyMatrixMockMvc.perform(get("/api/supply-matrices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplyMatrix.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID.intValue())))
            .andExpect(jsonPath("$.[*].incomeTypeCode").value(hasItem(DEFAULT_INCOME_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].incomeType").value(hasItem(DEFAULT_INCOME_TYPE)))
            .andExpect(jsonPath("$.[*].monthlyIncomeId").value(hasItem(DEFAULT_MONTHLY_INCOME_ID.intValue())))
            .andExpect(jsonPath("$.[*].marketCode").value(hasItem(DEFAULT_MARKET_CODE.intValue())))
            .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET)))
            .andExpect(jsonPath("$.[*].segmentCode").value(hasItem(DEFAULT_SEGMENT_CODE)))
            .andExpect(jsonPath("$.[*].segment").value(hasItem(DEFAULT_SEGMENT)))
            .andExpect(jsonPath("$.[*].activityId").value(hasItem(DEFAULT_ACTIVITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].professionCode").value(hasItem(DEFAULT_PROFESSION_CODE)))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)));

        // Check, that the count call also returns 1
        restSupplyMatrixMockMvc.perform(get("/api/supply-matrices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSupplyMatrixShouldNotBeFound(String filter) throws Exception {
        restSupplyMatrixMockMvc.perform(get("/api/supply-matrices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSupplyMatrixMockMvc.perform(get("/api/supply-matrices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingSupplyMatrix() throws Exception {
        // Get the supplyMatrix
        restSupplyMatrixMockMvc.perform(get("/api/supply-matrices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupplyMatrix() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        int databaseSizeBeforeUpdate = supplyMatrixRepository.findAll().size();

        // Update the supplyMatrix
        SupplyMatrix updatedSupplyMatrix = supplyMatrixRepository.findById(supplyMatrix.getId()).get();
        // Disconnect from session so that the updates on updatedSupplyMatrix are not directly saved in db
        em.detach(updatedSupplyMatrix);
        updatedSupplyMatrix
            .categoryId(UPDATED_CATEGORY_ID)
            .incomeTypeCode(UPDATED_INCOME_TYPE_CODE)
            .incomeType(UPDATED_INCOME_TYPE)
            .monthlyIncomeId(UPDATED_MONTHLY_INCOME_ID)
            .marketCode(UPDATED_MARKET_CODE)
            .market(UPDATED_MARKET)
            .segmentCode(UPDATED_SEGMENT_CODE)
            .segment(UPDATED_SEGMENT)
            .activityId(UPDATED_ACTIVITY_ID)
            .professionCode(UPDATED_PROFESSION_CODE)
            .profession(UPDATED_PROFESSION);
        SupplyMatrixDTO supplyMatrixDTO = supplyMatrixMapper.toDto(updatedSupplyMatrix);

        restSupplyMatrixMockMvc.perform(put("/api/supply-matrices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyMatrixDTO)))
            .andExpect(status().isOk());

        // Validate the SupplyMatrix in the database
        List<SupplyMatrix> supplyMatrixList = supplyMatrixRepository.findAll();
        assertThat(supplyMatrixList).hasSize(databaseSizeBeforeUpdate);
        SupplyMatrix testSupplyMatrix = supplyMatrixList.get(supplyMatrixList.size() - 1);
        assertThat(testSupplyMatrix.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testSupplyMatrix.getIncomeTypeCode()).isEqualTo(UPDATED_INCOME_TYPE_CODE);
        assertThat(testSupplyMatrix.getIncomeType()).isEqualTo(UPDATED_INCOME_TYPE);
        assertThat(testSupplyMatrix.getMonthlyIncomeId()).isEqualTo(UPDATED_MONTHLY_INCOME_ID);
        assertThat(testSupplyMatrix.getMarketCode()).isEqualTo(UPDATED_MARKET_CODE);
        assertThat(testSupplyMatrix.getMarket()).isEqualTo(UPDATED_MARKET);
        assertThat(testSupplyMatrix.getSegmentCode()).isEqualTo(UPDATED_SEGMENT_CODE);
        assertThat(testSupplyMatrix.getSegment()).isEqualTo(UPDATED_SEGMENT);
        assertThat(testSupplyMatrix.getActivityId()).isEqualTo(UPDATED_ACTIVITY_ID);
        assertThat(testSupplyMatrix.getProfessionCode()).isEqualTo(UPDATED_PROFESSION_CODE);
        assertThat(testSupplyMatrix.getProfession()).isEqualTo(UPDATED_PROFESSION);
    }

    @Test
    @Transactional
    public void updateNonExistingSupplyMatrix() throws Exception {
        int databaseSizeBeforeUpdate = supplyMatrixRepository.findAll().size();

        // Create the SupplyMatrix
        SupplyMatrixDTO supplyMatrixDTO = supplyMatrixMapper.toDto(supplyMatrix);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplyMatrixMockMvc.perform(put("/api/supply-matrices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyMatrixDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SupplyMatrix in the database
        List<SupplyMatrix> supplyMatrixList = supplyMatrixRepository.findAll();
        assertThat(supplyMatrixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSupplyMatrix() throws Exception {
        // Initialize the database
        supplyMatrixRepository.saveAndFlush(supplyMatrix);

        int databaseSizeBeforeDelete = supplyMatrixRepository.findAll().size();

        // Delete the supplyMatrix
        restSupplyMatrixMockMvc.perform(delete("/api/supply-matrices/{id}", supplyMatrix.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SupplyMatrix> supplyMatrixList = supplyMatrixRepository.findAll();
        assertThat(supplyMatrixList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
