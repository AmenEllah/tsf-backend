package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Document;
import com.attijari.domain.Request;
import com.attijari.repository.DocumentRepository;
import com.attijari.service.DocumentService;
import com.attijari.service.dto.DocumentDTO;
import com.attijari.service.mapper.DocumentMapper;
import com.attijari.service.DocumentQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DocumentResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DocumentResourceIT {

    private static final String DEFAULT_TYPE_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_FICHIER = "AAAAAAAAAA";
    private static final String UPDATED_NOM_FICHIER = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLACEMENT = "AAAAAAAAAA";
    private static final String UPDATED_EMPLACEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ID_DOSSIER_SIGNED = "AAAAAAAAAA";
    private static final String UPDATED_ID_DOSSIER_SIGNED = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HAS_SIGNED = false;
    private static final Boolean UPDATED_HAS_SIGNED = true;

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_CREATION = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_UPDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_UPDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_UPDATE = LocalDate.ofEpochDay(-1L);

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentQueryService documentQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentMockMvc;

    private Document document;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Document createEntity(EntityManager em) {
        Document document = new Document()
            .typeDocument(DEFAULT_TYPE_DOCUMENT)
            .nomFichier(DEFAULT_NOM_FICHIER)
            .emplacement(DEFAULT_EMPLACEMENT)
            .idDossierSigned(DEFAULT_ID_DOSSIER_SIGNED)
            .hasSigned(DEFAULT_HAS_SIGNED)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateUpdate(DEFAULT_DATE_UPDATE);
        return document;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Document createUpdatedEntity(EntityManager em) {
        Document document = new Document()
            .typeDocument(UPDATED_TYPE_DOCUMENT)
            .nomFichier(UPDATED_NOM_FICHIER)
            .emplacement(UPDATED_EMPLACEMENT)
            .idDossierSigned(UPDATED_ID_DOSSIER_SIGNED)
            .hasSigned(UPDATED_HAS_SIGNED)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateUpdate(UPDATED_DATE_UPDATE);
        return document;
    }

    @BeforeEach
    public void initTest() {
        document = createEntity(em);
    }

    @Test
    @Transactional
    public void createDocument() throws Exception {
        int databaseSizeBeforeCreate = documentRepository.findAll().size();
        // Create the Document
        DocumentDTO documentDTO = documentMapper.toDto(document);
        restDocumentMockMvc.perform(post("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentDTO)))
            .andExpect(status().isCreated());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeCreate + 1);
        Document testDocument = documentList.get(documentList.size() - 1);
        assertThat(testDocument.getTypeDocument()).isEqualTo(DEFAULT_TYPE_DOCUMENT);
        assertThat(testDocument.getNomFichier()).isEqualTo(DEFAULT_NOM_FICHIER);
        assertThat(testDocument.getEmplacement()).isEqualTo(DEFAULT_EMPLACEMENT);
        assertThat(testDocument.getIdDossierSigned()).isEqualTo(DEFAULT_ID_DOSSIER_SIGNED);
        assertThat(testDocument.isHasSigned()).isEqualTo(DEFAULT_HAS_SIGNED);
        assertThat(testDocument.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testDocument.getDateUpdate()).isEqualTo(DEFAULT_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void createDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = documentRepository.findAll().size();

        // Create the Document with an existing ID
        document.setId(1L);
        DocumentDTO documentDTO = documentMapper.toDto(document);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentMockMvc.perform(post("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDocuments() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList
        restDocumentMockMvc.perform(get("/api/documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeDocument").value(hasItem(DEFAULT_TYPE_DOCUMENT)))
            .andExpect(jsonPath("$.[*].nomFichier").value(hasItem(DEFAULT_NOM_FICHIER)))
            .andExpect(jsonPath("$.[*].emplacement").value(hasItem(DEFAULT_EMPLACEMENT)))
            .andExpect(jsonPath("$.[*].idDossierSigned").value(hasItem(DEFAULT_ID_DOSSIER_SIGNED)))
            .andExpect(jsonPath("$.[*].hasSigned").value(hasItem(DEFAULT_HAS_SIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].dateUpdate").value(hasItem(DEFAULT_DATE_UPDATE.toString())));
    }

    @Test
    @Transactional
    public void getDocument() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get the document
        restDocumentMockMvc.perform(get("/api/documents/{id}", document.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(document.getId().intValue()))
            .andExpect(jsonPath("$.typeDocument").value(DEFAULT_TYPE_DOCUMENT))
            .andExpect(jsonPath("$.nomFichier").value(DEFAULT_NOM_FICHIER))
            .andExpect(jsonPath("$.emplacement").value(DEFAULT_EMPLACEMENT))
            .andExpect(jsonPath("$.idDossierSigned").value(DEFAULT_ID_DOSSIER_SIGNED))
            .andExpect(jsonPath("$.hasSigned").value(DEFAULT_HAS_SIGNED.booleanValue()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.dateUpdate").value(DEFAULT_DATE_UPDATE.toString()));
    }


    @Test
    @Transactional
    public void getDocumentsByIdFiltering() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        Long id = document.getId();

        defaultDocumentShouldBeFound("id.equals=" + id);
        defaultDocumentShouldNotBeFound("id.notEquals=" + id);

        defaultDocumentShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDocumentShouldNotBeFound("id.greaterThan=" + id);

        defaultDocumentShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDocumentShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDocumentsByTypeDocumentIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where typeDocument equals to DEFAULT_TYPE_DOCUMENT
        defaultDocumentShouldBeFound("typeDocument.equals=" + DEFAULT_TYPE_DOCUMENT);

        // Get all the documentList where typeDocument equals to UPDATED_TYPE_DOCUMENT
        defaultDocumentShouldNotBeFound("typeDocument.equals=" + UPDATED_TYPE_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByTypeDocumentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where typeDocument not equals to DEFAULT_TYPE_DOCUMENT
        defaultDocumentShouldNotBeFound("typeDocument.notEquals=" + DEFAULT_TYPE_DOCUMENT);

        // Get all the documentList where typeDocument not equals to UPDATED_TYPE_DOCUMENT
        defaultDocumentShouldBeFound("typeDocument.notEquals=" + UPDATED_TYPE_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByTypeDocumentIsInShouldWork() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where typeDocument in DEFAULT_TYPE_DOCUMENT or UPDATED_TYPE_DOCUMENT
        defaultDocumentShouldBeFound("typeDocument.in=" + DEFAULT_TYPE_DOCUMENT + "," + UPDATED_TYPE_DOCUMENT);

        // Get all the documentList where typeDocument equals to UPDATED_TYPE_DOCUMENT
        defaultDocumentShouldNotBeFound("typeDocument.in=" + UPDATED_TYPE_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByTypeDocumentIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where typeDocument is not null
        defaultDocumentShouldBeFound("typeDocument.specified=true");

        // Get all the documentList where typeDocument is null
        defaultDocumentShouldNotBeFound("typeDocument.specified=false");
    }
                @Test
    @Transactional
    public void getAllDocumentsByTypeDocumentContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where typeDocument contains DEFAULT_TYPE_DOCUMENT
        defaultDocumentShouldBeFound("typeDocument.contains=" + DEFAULT_TYPE_DOCUMENT);

        // Get all the documentList where typeDocument contains UPDATED_TYPE_DOCUMENT
        defaultDocumentShouldNotBeFound("typeDocument.contains=" + UPDATED_TYPE_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByTypeDocumentNotContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where typeDocument does not contain DEFAULT_TYPE_DOCUMENT
        defaultDocumentShouldNotBeFound("typeDocument.doesNotContain=" + DEFAULT_TYPE_DOCUMENT);

        // Get all the documentList where typeDocument does not contain UPDATED_TYPE_DOCUMENT
        defaultDocumentShouldBeFound("typeDocument.doesNotContain=" + UPDATED_TYPE_DOCUMENT);
    }


    @Test
    @Transactional
    public void getAllDocumentsByNomFichierIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where nomFichier equals to DEFAULT_NOM_FICHIER
        defaultDocumentShouldBeFound("nomFichier.equals=" + DEFAULT_NOM_FICHIER);

        // Get all the documentList where nomFichier equals to UPDATED_NOM_FICHIER
        defaultDocumentShouldNotBeFound("nomFichier.equals=" + UPDATED_NOM_FICHIER);
    }

    @Test
    @Transactional
    public void getAllDocumentsByNomFichierIsNotEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where nomFichier not equals to DEFAULT_NOM_FICHIER
        defaultDocumentShouldNotBeFound("nomFichier.notEquals=" + DEFAULT_NOM_FICHIER);

        // Get all the documentList where nomFichier not equals to UPDATED_NOM_FICHIER
        defaultDocumentShouldBeFound("nomFichier.notEquals=" + UPDATED_NOM_FICHIER);
    }

    @Test
    @Transactional
    public void getAllDocumentsByNomFichierIsInShouldWork() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where nomFichier in DEFAULT_NOM_FICHIER or UPDATED_NOM_FICHIER
        defaultDocumentShouldBeFound("nomFichier.in=" + DEFAULT_NOM_FICHIER + "," + UPDATED_NOM_FICHIER);

        // Get all the documentList where nomFichier equals to UPDATED_NOM_FICHIER
        defaultDocumentShouldNotBeFound("nomFichier.in=" + UPDATED_NOM_FICHIER);
    }

    @Test
    @Transactional
    public void getAllDocumentsByNomFichierIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where nomFichier is not null
        defaultDocumentShouldBeFound("nomFichier.specified=true");

        // Get all the documentList where nomFichier is null
        defaultDocumentShouldNotBeFound("nomFichier.specified=false");
    }
                @Test
    @Transactional
    public void getAllDocumentsByNomFichierContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where nomFichier contains DEFAULT_NOM_FICHIER
        defaultDocumentShouldBeFound("nomFichier.contains=" + DEFAULT_NOM_FICHIER);

        // Get all the documentList where nomFichier contains UPDATED_NOM_FICHIER
        defaultDocumentShouldNotBeFound("nomFichier.contains=" + UPDATED_NOM_FICHIER);
    }

    @Test
    @Transactional
    public void getAllDocumentsByNomFichierNotContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where nomFichier does not contain DEFAULT_NOM_FICHIER
        defaultDocumentShouldNotBeFound("nomFichier.doesNotContain=" + DEFAULT_NOM_FICHIER);

        // Get all the documentList where nomFichier does not contain UPDATED_NOM_FICHIER
        defaultDocumentShouldBeFound("nomFichier.doesNotContain=" + UPDATED_NOM_FICHIER);
    }


    @Test
    @Transactional
    public void getAllDocumentsByEmplacementIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where emplacement equals to DEFAULT_EMPLACEMENT
        defaultDocumentShouldBeFound("emplacement.equals=" + DEFAULT_EMPLACEMENT);

        // Get all the documentList where emplacement equals to UPDATED_EMPLACEMENT
        defaultDocumentShouldNotBeFound("emplacement.equals=" + UPDATED_EMPLACEMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByEmplacementIsNotEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where emplacement not equals to DEFAULT_EMPLACEMENT
        defaultDocumentShouldNotBeFound("emplacement.notEquals=" + DEFAULT_EMPLACEMENT);

        // Get all the documentList where emplacement not equals to UPDATED_EMPLACEMENT
        defaultDocumentShouldBeFound("emplacement.notEquals=" + UPDATED_EMPLACEMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByEmplacementIsInShouldWork() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where emplacement in DEFAULT_EMPLACEMENT or UPDATED_EMPLACEMENT
        defaultDocumentShouldBeFound("emplacement.in=" + DEFAULT_EMPLACEMENT + "," + UPDATED_EMPLACEMENT);

        // Get all the documentList where emplacement equals to UPDATED_EMPLACEMENT
        defaultDocumentShouldNotBeFound("emplacement.in=" + UPDATED_EMPLACEMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByEmplacementIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where emplacement is not null
        defaultDocumentShouldBeFound("emplacement.specified=true");

        // Get all the documentList where emplacement is null
        defaultDocumentShouldNotBeFound("emplacement.specified=false");
    }
                @Test
    @Transactional
    public void getAllDocumentsByEmplacementContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where emplacement contains DEFAULT_EMPLACEMENT
        defaultDocumentShouldBeFound("emplacement.contains=" + DEFAULT_EMPLACEMENT);

        // Get all the documentList where emplacement contains UPDATED_EMPLACEMENT
        defaultDocumentShouldNotBeFound("emplacement.contains=" + UPDATED_EMPLACEMENT);
    }

    @Test
    @Transactional
    public void getAllDocumentsByEmplacementNotContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where emplacement does not contain DEFAULT_EMPLACEMENT
        defaultDocumentShouldNotBeFound("emplacement.doesNotContain=" + DEFAULT_EMPLACEMENT);

        // Get all the documentList where emplacement does not contain UPDATED_EMPLACEMENT
        defaultDocumentShouldBeFound("emplacement.doesNotContain=" + UPDATED_EMPLACEMENT);
    }


    @Test
    @Transactional
    public void getAllDocumentsByIdDossierSignedIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where idDossierSigned equals to DEFAULT_ID_DOSSIER_SIGNED
        defaultDocumentShouldBeFound("idDossierSigned.equals=" + DEFAULT_ID_DOSSIER_SIGNED);

        // Get all the documentList where idDossierSigned equals to UPDATED_ID_DOSSIER_SIGNED
        defaultDocumentShouldNotBeFound("idDossierSigned.equals=" + UPDATED_ID_DOSSIER_SIGNED);
    }

    @Test
    @Transactional
    public void getAllDocumentsByIdDossierSignedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where idDossierSigned not equals to DEFAULT_ID_DOSSIER_SIGNED
        defaultDocumentShouldNotBeFound("idDossierSigned.notEquals=" + DEFAULT_ID_DOSSIER_SIGNED);

        // Get all the documentList where idDossierSigned not equals to UPDATED_ID_DOSSIER_SIGNED
        defaultDocumentShouldBeFound("idDossierSigned.notEquals=" + UPDATED_ID_DOSSIER_SIGNED);
    }

    @Test
    @Transactional
    public void getAllDocumentsByIdDossierSignedIsInShouldWork() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where idDossierSigned in DEFAULT_ID_DOSSIER_SIGNED or UPDATED_ID_DOSSIER_SIGNED
        defaultDocumentShouldBeFound("idDossierSigned.in=" + DEFAULT_ID_DOSSIER_SIGNED + "," + UPDATED_ID_DOSSIER_SIGNED);

        // Get all the documentList where idDossierSigned equals to UPDATED_ID_DOSSIER_SIGNED
        defaultDocumentShouldNotBeFound("idDossierSigned.in=" + UPDATED_ID_DOSSIER_SIGNED);
    }

    @Test
    @Transactional
    public void getAllDocumentsByIdDossierSignedIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where idDossierSigned is not null
        defaultDocumentShouldBeFound("idDossierSigned.specified=true");

        // Get all the documentList where idDossierSigned is null
        defaultDocumentShouldNotBeFound("idDossierSigned.specified=false");
    }
                @Test
    @Transactional
    public void getAllDocumentsByIdDossierSignedContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where idDossierSigned contains DEFAULT_ID_DOSSIER_SIGNED
        defaultDocumentShouldBeFound("idDossierSigned.contains=" + DEFAULT_ID_DOSSIER_SIGNED);

        // Get all the documentList where idDossierSigned contains UPDATED_ID_DOSSIER_SIGNED
        defaultDocumentShouldNotBeFound("idDossierSigned.contains=" + UPDATED_ID_DOSSIER_SIGNED);
    }

    @Test
    @Transactional
    public void getAllDocumentsByIdDossierSignedNotContainsSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where idDossierSigned does not contain DEFAULT_ID_DOSSIER_SIGNED
        defaultDocumentShouldNotBeFound("idDossierSigned.doesNotContain=" + DEFAULT_ID_DOSSIER_SIGNED);

        // Get all the documentList where idDossierSigned does not contain UPDATED_ID_DOSSIER_SIGNED
        defaultDocumentShouldBeFound("idDossierSigned.doesNotContain=" + UPDATED_ID_DOSSIER_SIGNED);
    }


    @Test
    @Transactional
    public void getAllDocumentsByHasSignedIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where hasSigned equals to DEFAULT_HAS_SIGNED
        defaultDocumentShouldBeFound("hasSigned.equals=" + DEFAULT_HAS_SIGNED);

        // Get all the documentList where hasSigned equals to UPDATED_HAS_SIGNED
        defaultDocumentShouldNotBeFound("hasSigned.equals=" + UPDATED_HAS_SIGNED);
    }

    @Test
    @Transactional
    public void getAllDocumentsByHasSignedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where hasSigned not equals to DEFAULT_HAS_SIGNED
        defaultDocumentShouldNotBeFound("hasSigned.notEquals=" + DEFAULT_HAS_SIGNED);

        // Get all the documentList where hasSigned not equals to UPDATED_HAS_SIGNED
        defaultDocumentShouldBeFound("hasSigned.notEquals=" + UPDATED_HAS_SIGNED);
    }

    @Test
    @Transactional
    public void getAllDocumentsByHasSignedIsInShouldWork() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where hasSigned in DEFAULT_HAS_SIGNED or UPDATED_HAS_SIGNED
        defaultDocumentShouldBeFound("hasSigned.in=" + DEFAULT_HAS_SIGNED + "," + UPDATED_HAS_SIGNED);

        // Get all the documentList where hasSigned equals to UPDATED_HAS_SIGNED
        defaultDocumentShouldNotBeFound("hasSigned.in=" + UPDATED_HAS_SIGNED);
    }

    @Test
    @Transactional
    public void getAllDocumentsByHasSignedIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where hasSigned is not null
        defaultDocumentShouldBeFound("hasSigned.specified=true");

        // Get all the documentList where hasSigned is null
        defaultDocumentShouldNotBeFound("hasSigned.specified=false");
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation equals to DEFAULT_DATE_CREATION
        defaultDocumentShouldBeFound("dateCreation.equals=" + DEFAULT_DATE_CREATION);

        // Get all the documentList where dateCreation equals to UPDATED_DATE_CREATION
        defaultDocumentShouldNotBeFound("dateCreation.equals=" + UPDATED_DATE_CREATION);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation not equals to DEFAULT_DATE_CREATION
        defaultDocumentShouldNotBeFound("dateCreation.notEquals=" + DEFAULT_DATE_CREATION);

        // Get all the documentList where dateCreation not equals to UPDATED_DATE_CREATION
        defaultDocumentShouldBeFound("dateCreation.notEquals=" + UPDATED_DATE_CREATION);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsInShouldWork() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation in DEFAULT_DATE_CREATION or UPDATED_DATE_CREATION
        defaultDocumentShouldBeFound("dateCreation.in=" + DEFAULT_DATE_CREATION + "," + UPDATED_DATE_CREATION);

        // Get all the documentList where dateCreation equals to UPDATED_DATE_CREATION
        defaultDocumentShouldNotBeFound("dateCreation.in=" + UPDATED_DATE_CREATION);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation is not null
        defaultDocumentShouldBeFound("dateCreation.specified=true");

        // Get all the documentList where dateCreation is null
        defaultDocumentShouldNotBeFound("dateCreation.specified=false");
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation is greater than or equal to DEFAULT_DATE_CREATION
        defaultDocumentShouldBeFound("dateCreation.greaterThanOrEqual=" + DEFAULT_DATE_CREATION);

        // Get all the documentList where dateCreation is greater than or equal to UPDATED_DATE_CREATION
        defaultDocumentShouldNotBeFound("dateCreation.greaterThanOrEqual=" + UPDATED_DATE_CREATION);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation is less than or equal to DEFAULT_DATE_CREATION
        defaultDocumentShouldBeFound("dateCreation.lessThanOrEqual=" + DEFAULT_DATE_CREATION);

        // Get all the documentList where dateCreation is less than or equal to SMALLER_DATE_CREATION
        defaultDocumentShouldNotBeFound("dateCreation.lessThanOrEqual=" + SMALLER_DATE_CREATION);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsLessThanSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation is less than DEFAULT_DATE_CREATION
        defaultDocumentShouldNotBeFound("dateCreation.lessThan=" + DEFAULT_DATE_CREATION);

        // Get all the documentList where dateCreation is less than UPDATED_DATE_CREATION
        defaultDocumentShouldBeFound("dateCreation.lessThan=" + UPDATED_DATE_CREATION);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateCreationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateCreation is greater than DEFAULT_DATE_CREATION
        defaultDocumentShouldNotBeFound("dateCreation.greaterThan=" + DEFAULT_DATE_CREATION);

        // Get all the documentList where dateCreation is greater than SMALLER_DATE_CREATION
        defaultDocumentShouldBeFound("dateCreation.greaterThan=" + SMALLER_DATE_CREATION);
    }


    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate equals to DEFAULT_DATE_UPDATE
        defaultDocumentShouldBeFound("dateUpdate.equals=" + DEFAULT_DATE_UPDATE);

        // Get all the documentList where dateUpdate equals to UPDATED_DATE_UPDATE
        defaultDocumentShouldNotBeFound("dateUpdate.equals=" + UPDATED_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate not equals to DEFAULT_DATE_UPDATE
        defaultDocumentShouldNotBeFound("dateUpdate.notEquals=" + DEFAULT_DATE_UPDATE);

        // Get all the documentList where dateUpdate not equals to UPDATED_DATE_UPDATE
        defaultDocumentShouldBeFound("dateUpdate.notEquals=" + UPDATED_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsInShouldWork() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate in DEFAULT_DATE_UPDATE or UPDATED_DATE_UPDATE
        defaultDocumentShouldBeFound("dateUpdate.in=" + DEFAULT_DATE_UPDATE + "," + UPDATED_DATE_UPDATE);

        // Get all the documentList where dateUpdate equals to UPDATED_DATE_UPDATE
        defaultDocumentShouldNotBeFound("dateUpdate.in=" + UPDATED_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate is not null
        defaultDocumentShouldBeFound("dateUpdate.specified=true");

        // Get all the documentList where dateUpdate is null
        defaultDocumentShouldNotBeFound("dateUpdate.specified=false");
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate is greater than or equal to DEFAULT_DATE_UPDATE
        defaultDocumentShouldBeFound("dateUpdate.greaterThanOrEqual=" + DEFAULT_DATE_UPDATE);

        // Get all the documentList where dateUpdate is greater than or equal to UPDATED_DATE_UPDATE
        defaultDocumentShouldNotBeFound("dateUpdate.greaterThanOrEqual=" + UPDATED_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate is less than or equal to DEFAULT_DATE_UPDATE
        defaultDocumentShouldBeFound("dateUpdate.lessThanOrEqual=" + DEFAULT_DATE_UPDATE);

        // Get all the documentList where dateUpdate is less than or equal to SMALLER_DATE_UPDATE
        defaultDocumentShouldNotBeFound("dateUpdate.lessThanOrEqual=" + SMALLER_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsLessThanSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate is less than DEFAULT_DATE_UPDATE
        defaultDocumentShouldNotBeFound("dateUpdate.lessThan=" + DEFAULT_DATE_UPDATE);

        // Get all the documentList where dateUpdate is less than UPDATED_DATE_UPDATE
        defaultDocumentShouldBeFound("dateUpdate.lessThan=" + UPDATED_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void getAllDocumentsByDateUpdateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        // Get all the documentList where dateUpdate is greater than DEFAULT_DATE_UPDATE
        defaultDocumentShouldNotBeFound("dateUpdate.greaterThan=" + DEFAULT_DATE_UPDATE);

        // Get all the documentList where dateUpdate is greater than SMALLER_DATE_UPDATE
        defaultDocumentShouldBeFound("dateUpdate.greaterThan=" + SMALLER_DATE_UPDATE);
    }


    @Test
    @Transactional
    public void getAllDocumentsByRequestIsEqualToSomething() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);
        Request request = RequestResourceIT.createEntity(em);
        em.persist(request);
        em.flush();
        documentRepository.saveAndFlush(document);
        Long requestId = request.getId();

        // Get all the documentList where request equals to requestId
        defaultDocumentShouldBeFound("requestId.equals=" + requestId);

        // Get all the documentList where request equals to requestId + 1
        defaultDocumentShouldNotBeFound("requestId.equals=" + (requestId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDocumentShouldBeFound(String filter) throws Exception {
        restDocumentMockMvc.perform(get("/api/documents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(document.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeDocument").value(hasItem(DEFAULT_TYPE_DOCUMENT)))
            .andExpect(jsonPath("$.[*].nomFichier").value(hasItem(DEFAULT_NOM_FICHIER)))
            .andExpect(jsonPath("$.[*].emplacement").value(hasItem(DEFAULT_EMPLACEMENT)))
            .andExpect(jsonPath("$.[*].idDossierSigned").value(hasItem(DEFAULT_ID_DOSSIER_SIGNED)))
            .andExpect(jsonPath("$.[*].hasSigned").value(hasItem(DEFAULT_HAS_SIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].dateUpdate").value(hasItem(DEFAULT_DATE_UPDATE.toString())));

        // Check, that the count call also returns 1
        restDocumentMockMvc.perform(get("/api/documents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDocumentShouldNotBeFound(String filter) throws Exception {
        restDocumentMockMvc.perform(get("/api/documents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDocumentMockMvc.perform(get("/api/documents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingDocument() throws Exception {
        // Get the document
        restDocumentMockMvc.perform(get("/api/documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDocument() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        int databaseSizeBeforeUpdate = documentRepository.findAll().size();

        // Update the document
        Document updatedDocument = documentRepository.findById(document.getId()).get();
        // Disconnect from session so that the updates on updatedDocument are not directly saved in db
        em.detach(updatedDocument);
        updatedDocument
            .typeDocument(UPDATED_TYPE_DOCUMENT)
            .nomFichier(UPDATED_NOM_FICHIER)
            .emplacement(UPDATED_EMPLACEMENT)
            .idDossierSigned(UPDATED_ID_DOSSIER_SIGNED)
            .hasSigned(UPDATED_HAS_SIGNED)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateUpdate(UPDATED_DATE_UPDATE);
        DocumentDTO documentDTO = documentMapper.toDto(updatedDocument);

        restDocumentMockMvc.perform(put("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentDTO)))
            .andExpect(status().isOk());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeUpdate);
        Document testDocument = documentList.get(documentList.size() - 1);
        assertThat(testDocument.getTypeDocument()).isEqualTo(UPDATED_TYPE_DOCUMENT);
        assertThat(testDocument.getNomFichier()).isEqualTo(UPDATED_NOM_FICHIER);
        assertThat(testDocument.getEmplacement()).isEqualTo(UPDATED_EMPLACEMENT);
        assertThat(testDocument.getIdDossierSigned()).isEqualTo(UPDATED_ID_DOSSIER_SIGNED);
        assertThat(testDocument.isHasSigned()).isEqualTo(UPDATED_HAS_SIGNED);
        assertThat(testDocument.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testDocument.getDateUpdate()).isEqualTo(UPDATED_DATE_UPDATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDocument() throws Exception {
        int databaseSizeBeforeUpdate = documentRepository.findAll().size();

        // Create the Document
        DocumentDTO documentDTO = documentMapper.toDto(document);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentMockMvc.perform(put("/api/documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Document in the database
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDocument() throws Exception {
        // Initialize the database
        documentRepository.saveAndFlush(document);

        int databaseSizeBeforeDelete = documentRepository.findAll().size();

        // Delete the document
        restDocumentMockMvc.perform(delete("/api/documents/{id}", document.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Document> documentList = documentRepository.findAll();
        assertThat(documentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
