package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Category;
import com.attijari.domain.FinancialInfo;
import com.attijari.repository.CategoryRepository;
import com.attijari.service.CategoryService;
import com.attijari.service.dto.CategoryDTO;
import com.attijari.service.mapper.CategoryMapper;
import com.attijari.service.CategoryQueryService;

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
 * Integration tests for the {@link CategoryResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategoryResourceIT {

    private static final String DEFAULT_CATEGORY_NAME_FR = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME_FR = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME_EN = "BBBBBBBBBB";

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryMockMvc;

    private Category category;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category createEntity(EntityManager em) {
        Category category = new Category()
            .categoryNameFR(DEFAULT_CATEGORY_NAME_FR)
            .categoryNameEN(DEFAULT_CATEGORY_NAME_EN);
        return category;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category createUpdatedEntity(EntityManager em) {
        Category category = new Category()
            .categoryNameFR(UPDATED_CATEGORY_NAME_FR)
            .categoryNameEN(UPDATED_CATEGORY_NAME_EN);
        return category;
    }

    @BeforeEach
    public void initTest() {
        category = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategory() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();
        // Create the Category
        CategoryDTO categoryDTO = categoryMapper.toDto(category);
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate + 1);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getCategoryNameFR()).isEqualTo(DEFAULT_CATEGORY_NAME_FR);
        assertThat(testCategory.getCategoryNameEN()).isEqualTo(DEFAULT_CATEGORY_NAME_EN);
    }

    @Test
    @Transactional
    public void createCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        // Create the Category with an existing ID
        category.setId(1L);
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategories() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList
        restCategoryMockMvc.perform(get("/api/categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryNameFR").value(hasItem(DEFAULT_CATEGORY_NAME_FR)))
            .andExpect(jsonPath("$.[*].categoryNameEN").value(hasItem(DEFAULT_CATEGORY_NAME_EN)));
    }

    @Test
    @Transactional
    public void getCategory() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(category.getId().intValue()))
            .andExpect(jsonPath("$.categoryNameFR").value(DEFAULT_CATEGORY_NAME_FR))
            .andExpect(jsonPath("$.categoryNameEN").value(DEFAULT_CATEGORY_NAME_EN));
    }


    @Test
    @Transactional
    public void getCategoriesByIdFiltering() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        Long id = category.getId();

        defaultCategoryShouldBeFound("id.equals=" + id);
        defaultCategoryShouldNotBeFound("id.notEquals=" + id);

        defaultCategoryShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCategoryShouldNotBeFound("id.greaterThan=" + id);

        defaultCategoryShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCategoryShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameFRIsEqualToSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameFR equals to DEFAULT_CATEGORY_NAME_FR
        defaultCategoryShouldBeFound("categoryNameFR.equals=" + DEFAULT_CATEGORY_NAME_FR);

        // Get all the categoryList where categoryNameFR equals to UPDATED_CATEGORY_NAME_FR
        defaultCategoryShouldNotBeFound("categoryNameFR.equals=" + UPDATED_CATEGORY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameFRIsNotEqualToSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameFR not equals to DEFAULT_CATEGORY_NAME_FR
        defaultCategoryShouldNotBeFound("categoryNameFR.notEquals=" + DEFAULT_CATEGORY_NAME_FR);

        // Get all the categoryList where categoryNameFR not equals to UPDATED_CATEGORY_NAME_FR
        defaultCategoryShouldBeFound("categoryNameFR.notEquals=" + UPDATED_CATEGORY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameFRIsInShouldWork() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameFR in DEFAULT_CATEGORY_NAME_FR or UPDATED_CATEGORY_NAME_FR
        defaultCategoryShouldBeFound("categoryNameFR.in=" + DEFAULT_CATEGORY_NAME_FR + "," + UPDATED_CATEGORY_NAME_FR);

        // Get all the categoryList where categoryNameFR equals to UPDATED_CATEGORY_NAME_FR
        defaultCategoryShouldNotBeFound("categoryNameFR.in=" + UPDATED_CATEGORY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameFRIsNullOrNotNull() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameFR is not null
        defaultCategoryShouldBeFound("categoryNameFR.specified=true");

        // Get all the categoryList where categoryNameFR is null
        defaultCategoryShouldNotBeFound("categoryNameFR.specified=false");
    }
                @Test
    @Transactional
    public void getAllCategoriesByCategoryNameFRContainsSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameFR contains DEFAULT_CATEGORY_NAME_FR
        defaultCategoryShouldBeFound("categoryNameFR.contains=" + DEFAULT_CATEGORY_NAME_FR);

        // Get all the categoryList where categoryNameFR contains UPDATED_CATEGORY_NAME_FR
        defaultCategoryShouldNotBeFound("categoryNameFR.contains=" + UPDATED_CATEGORY_NAME_FR);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameFRNotContainsSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameFR does not contain DEFAULT_CATEGORY_NAME_FR
        defaultCategoryShouldNotBeFound("categoryNameFR.doesNotContain=" + DEFAULT_CATEGORY_NAME_FR);

        // Get all the categoryList where categoryNameFR does not contain UPDATED_CATEGORY_NAME_FR
        defaultCategoryShouldBeFound("categoryNameFR.doesNotContain=" + UPDATED_CATEGORY_NAME_FR);
    }


    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameENIsEqualToSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameEN equals to DEFAULT_CATEGORY_NAME_EN
        defaultCategoryShouldBeFound("categoryNameEN.equals=" + DEFAULT_CATEGORY_NAME_EN);

        // Get all the categoryList where categoryNameEN equals to UPDATED_CATEGORY_NAME_EN
        defaultCategoryShouldNotBeFound("categoryNameEN.equals=" + UPDATED_CATEGORY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameENIsNotEqualToSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameEN not equals to DEFAULT_CATEGORY_NAME_EN
        defaultCategoryShouldNotBeFound("categoryNameEN.notEquals=" + DEFAULT_CATEGORY_NAME_EN);

        // Get all the categoryList where categoryNameEN not equals to UPDATED_CATEGORY_NAME_EN
        defaultCategoryShouldBeFound("categoryNameEN.notEquals=" + UPDATED_CATEGORY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameENIsInShouldWork() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameEN in DEFAULT_CATEGORY_NAME_EN or UPDATED_CATEGORY_NAME_EN
        defaultCategoryShouldBeFound("categoryNameEN.in=" + DEFAULT_CATEGORY_NAME_EN + "," + UPDATED_CATEGORY_NAME_EN);

        // Get all the categoryList where categoryNameEN equals to UPDATED_CATEGORY_NAME_EN
        defaultCategoryShouldNotBeFound("categoryNameEN.in=" + UPDATED_CATEGORY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameENIsNullOrNotNull() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameEN is not null
        defaultCategoryShouldBeFound("categoryNameEN.specified=true");

        // Get all the categoryList where categoryNameEN is null
        defaultCategoryShouldNotBeFound("categoryNameEN.specified=false");
    }
                @Test
    @Transactional
    public void getAllCategoriesByCategoryNameENContainsSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameEN contains DEFAULT_CATEGORY_NAME_EN
        defaultCategoryShouldBeFound("categoryNameEN.contains=" + DEFAULT_CATEGORY_NAME_EN);

        // Get all the categoryList where categoryNameEN contains UPDATED_CATEGORY_NAME_EN
        defaultCategoryShouldNotBeFound("categoryNameEN.contains=" + UPDATED_CATEGORY_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllCategoriesByCategoryNameENNotContainsSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        // Get all the categoryList where categoryNameEN does not contain DEFAULT_CATEGORY_NAME_EN
        defaultCategoryShouldNotBeFound("categoryNameEN.doesNotContain=" + DEFAULT_CATEGORY_NAME_EN);

        // Get all the categoryList where categoryNameEN does not contain UPDATED_CATEGORY_NAME_EN
        defaultCategoryShouldBeFound("categoryNameEN.doesNotContain=" + UPDATED_CATEGORY_NAME_EN);
    }


    @Test
    @Transactional
    public void getAllCategoriesByFinancialInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);
        FinancialInfo financialInfo = FinancialInfoResourceIT.createEntity(em);
        em.persist(financialInfo);
        em.flush();
        categoryRepository.saveAndFlush(category);
        Long financialInfoId = financialInfo.getId();

        // Get all the categoryList where financialInfo equals to financialInfoId
        defaultCategoryShouldBeFound("financialInfoId.equals=" + financialInfoId);

        // Get all the categoryList where financialInfo equals to financialInfoId + 1
        defaultCategoryShouldNotBeFound("financialInfoId.equals=" + (financialInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCategoryShouldBeFound(String filter) throws Exception {
        restCategoryMockMvc.perform(get("/api/categories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryNameFR").value(hasItem(DEFAULT_CATEGORY_NAME_FR)))
            .andExpect(jsonPath("$.[*].categoryNameEN").value(hasItem(DEFAULT_CATEGORY_NAME_EN)));

        // Check, that the count call also returns 1
        restCategoryMockMvc.perform(get("/api/categories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCategoryShouldNotBeFound(String filter) throws Exception {
        restCategoryMockMvc.perform(get("/api/categories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCategoryMockMvc.perform(get("/api/categories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCategory() throws Exception {
        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategory() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Update the category
        Category updatedCategory = categoryRepository.findById(category.getId()).get();
        // Disconnect from session so that the updates on updatedCategory are not directly saved in db
        em.detach(updatedCategory);
        updatedCategory
            .categoryNameFR(UPDATED_CATEGORY_NAME_FR)
            .categoryNameEN(UPDATED_CATEGORY_NAME_EN);
        CategoryDTO categoryDTO = categoryMapper.toDto(updatedCategory);

        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isOk());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getCategoryNameFR()).isEqualTo(UPDATED_CATEGORY_NAME_FR);
        assertThat(testCategory.getCategoryNameEN()).isEqualTo(UPDATED_CATEGORY_NAME_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingCategory() throws Exception {
        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Create the Category
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategory() throws Exception {
        // Initialize the database
        categoryRepository.saveAndFlush(category);

        int databaseSizeBeforeDelete = categoryRepository.findAll().size();

        // Delete the category
        restCategoryMockMvc.perform(delete("/api/categories/{id}", category.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
