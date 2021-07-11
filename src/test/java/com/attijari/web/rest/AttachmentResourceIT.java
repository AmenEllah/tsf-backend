package com.attijari.web.rest;

import com.attijari.TsfBackendApp;
import com.attijari.domain.Request;
import com.attijari.repository.AttachmentRepository;
import com.attijari.domain.Attachment;
import com.attijari.service.AttachmentService;
import com.attijari.service.dto.AttachmentDTO;
import com.attijari.service.mapper.AttachmentMapper;
import com.attijari.service.AttachmentQueryService;

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

import com.attijari.domain.enumeration.AttachmentType;
import com.attijari.domain.enumeration.AttachmentStatus;
/**
 * Integration tests for the {@link AttachmentResource} REST controller.
 */
@SpringBootTest(classes = TsfBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AttachmentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final AttachmentType DEFAULT_ATTACHMENT_TYPE = AttachmentType.CIN_RECTO;
    private static final AttachmentType UPDATED_ATTACHMENT_TYPE = AttachmentType.CIN_VERSO;

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final AttachmentStatus DEFAULT_STATUS = AttachmentStatus.VALID;
    private static final AttachmentStatus UPDATED_STATUS = AttachmentStatus.INVALID;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachmentQueryService attachmentQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAttachmentMockMvc;

    private Attachment attachment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Attachment createEntity(EntityManager em) {
        Attachment attachment = new Attachment()
            .name(DEFAULT_NAME)
            .path(DEFAULT_PATH)
            .attachmentType(DEFAULT_ATTACHMENT_TYPE)
            .fileName(DEFAULT_FILE_NAME)
            .status(DEFAULT_STATUS);
        return attachment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Attachment createUpdatedEntity(EntityManager em) {
        Attachment attachment = new Attachment()
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .attachmentType(UPDATED_ATTACHMENT_TYPE)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS);
        return attachment;
    }

    @BeforeEach
    public void initTest() {
        attachment = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttachment() throws Exception {
        int databaseSizeBeforeCreate = attachmentRepository.findAll().size();
        // Create the Attachment
        AttachmentDTO attachmentDTO = attachmentMapper.toDto(attachment);
        restAttachmentMockMvc.perform(post("/api/attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attachmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Attachment in the database
        List<Attachment> attachmentList = attachmentRepository.findAll();
        assertThat(attachmentList).hasSize(databaseSizeBeforeCreate + 1);
        Attachment testAttachment = attachmentList.get(attachmentList.size() - 1);
        assertThat(testAttachment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAttachment.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testAttachment.getAttachmentType()).isEqualTo(DEFAULT_ATTACHMENT_TYPE);
        assertThat(testAttachment.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testAttachment.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAttachmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attachmentRepository.findAll().size();

        // Create the Attachment with an existing ID
        attachment.setId(1L);
        AttachmentDTO attachmentDTO = attachmentMapper.toDto(attachment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttachmentMockMvc.perform(post("/api/attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Attachment in the database
        List<Attachment> attachmentList = attachmentRepository.findAll();
        assertThat(attachmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAttachments() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList
        restAttachmentMockMvc.perform(get("/api/attachments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].attachmentType").value(hasItem(DEFAULT_ATTACHMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getAttachment() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get the attachment
        restAttachmentMockMvc.perform(get("/api/attachments/{id}", attachment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(attachment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.attachmentType").value(DEFAULT_ATTACHMENT_TYPE.toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }


    @Test
    @Transactional
    public void getAttachmentsByIdFiltering() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        Long id = attachment.getId();

        defaultAttachmentShouldBeFound("id.equals=" + id);
        defaultAttachmentShouldNotBeFound("id.notEquals=" + id);

        defaultAttachmentShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAttachmentShouldNotBeFound("id.greaterThan=" + id);

        defaultAttachmentShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAttachmentShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAttachmentsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where name equals to DEFAULT_NAME
        defaultAttachmentShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the attachmentList where name equals to UPDATED_NAME
        defaultAttachmentShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where name not equals to DEFAULT_NAME
        defaultAttachmentShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the attachmentList where name not equals to UPDATED_NAME
        defaultAttachmentShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where name in DEFAULT_NAME or UPDATED_NAME
        defaultAttachmentShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the attachmentList where name equals to UPDATED_NAME
        defaultAttachmentShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where name is not null
        defaultAttachmentShouldBeFound("name.specified=true");

        // Get all the attachmentList where name is null
        defaultAttachmentShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllAttachmentsByNameContainsSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where name contains DEFAULT_NAME
        defaultAttachmentShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the attachmentList where name contains UPDATED_NAME
        defaultAttachmentShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where name does not contain DEFAULT_NAME
        defaultAttachmentShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the attachmentList where name does not contain UPDATED_NAME
        defaultAttachmentShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllAttachmentsByPathIsEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where path equals to DEFAULT_PATH
        defaultAttachmentShouldBeFound("path.equals=" + DEFAULT_PATH);

        // Get all the attachmentList where path equals to UPDATED_PATH
        defaultAttachmentShouldNotBeFound("path.equals=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByPathIsNotEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where path not equals to DEFAULT_PATH
        defaultAttachmentShouldNotBeFound("path.notEquals=" + DEFAULT_PATH);

        // Get all the attachmentList where path not equals to UPDATED_PATH
        defaultAttachmentShouldBeFound("path.notEquals=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByPathIsInShouldWork() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where path in DEFAULT_PATH or UPDATED_PATH
        defaultAttachmentShouldBeFound("path.in=" + DEFAULT_PATH + "," + UPDATED_PATH);

        // Get all the attachmentList where path equals to UPDATED_PATH
        defaultAttachmentShouldNotBeFound("path.in=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where path is not null
        defaultAttachmentShouldBeFound("path.specified=true");

        // Get all the attachmentList where path is null
        defaultAttachmentShouldNotBeFound("path.specified=false");
    }
                @Test
    @Transactional
    public void getAllAttachmentsByPathContainsSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where path contains DEFAULT_PATH
        defaultAttachmentShouldBeFound("path.contains=" + DEFAULT_PATH);

        // Get all the attachmentList where path contains UPDATED_PATH
        defaultAttachmentShouldNotBeFound("path.contains=" + UPDATED_PATH);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByPathNotContainsSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where path does not contain DEFAULT_PATH
        defaultAttachmentShouldNotBeFound("path.doesNotContain=" + DEFAULT_PATH);

        // Get all the attachmentList where path does not contain UPDATED_PATH
        defaultAttachmentShouldBeFound("path.doesNotContain=" + UPDATED_PATH);
    }


    @Test
    @Transactional
    public void getAllAttachmentsByAttachmentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where attachmentType equals to DEFAULT_ATTACHMENT_TYPE
        defaultAttachmentShouldBeFound("attachmentType.equals=" + DEFAULT_ATTACHMENT_TYPE);

        // Get all the attachmentList where attachmentType equals to UPDATED_ATTACHMENT_TYPE
        defaultAttachmentShouldNotBeFound("attachmentType.equals=" + UPDATED_ATTACHMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByAttachmentTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where attachmentType not equals to DEFAULT_ATTACHMENT_TYPE
        defaultAttachmentShouldNotBeFound("attachmentType.notEquals=" + DEFAULT_ATTACHMENT_TYPE);

        // Get all the attachmentList where attachmentType not equals to UPDATED_ATTACHMENT_TYPE
        defaultAttachmentShouldBeFound("attachmentType.notEquals=" + UPDATED_ATTACHMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByAttachmentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where attachmentType in DEFAULT_ATTACHMENT_TYPE or UPDATED_ATTACHMENT_TYPE
        defaultAttachmentShouldBeFound("attachmentType.in=" + DEFAULT_ATTACHMENT_TYPE + "," + UPDATED_ATTACHMENT_TYPE);

        // Get all the attachmentList where attachmentType equals to UPDATED_ATTACHMENT_TYPE
        defaultAttachmentShouldNotBeFound("attachmentType.in=" + UPDATED_ATTACHMENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByAttachmentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where attachmentType is not null
        defaultAttachmentShouldBeFound("attachmentType.specified=true");

        // Get all the attachmentList where attachmentType is null
        defaultAttachmentShouldNotBeFound("attachmentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllAttachmentsByFileNameIsEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where fileName equals to DEFAULT_FILE_NAME
        defaultAttachmentShouldBeFound("fileName.equals=" + DEFAULT_FILE_NAME);

        // Get all the attachmentList where fileName equals to UPDATED_FILE_NAME
        defaultAttachmentShouldNotBeFound("fileName.equals=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByFileNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where fileName not equals to DEFAULT_FILE_NAME
        defaultAttachmentShouldNotBeFound("fileName.notEquals=" + DEFAULT_FILE_NAME);

        // Get all the attachmentList where fileName not equals to UPDATED_FILE_NAME
        defaultAttachmentShouldBeFound("fileName.notEquals=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByFileNameIsInShouldWork() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where fileName in DEFAULT_FILE_NAME or UPDATED_FILE_NAME
        defaultAttachmentShouldBeFound("fileName.in=" + DEFAULT_FILE_NAME + "," + UPDATED_FILE_NAME);

        // Get all the attachmentList where fileName equals to UPDATED_FILE_NAME
        defaultAttachmentShouldNotBeFound("fileName.in=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByFileNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where fileName is not null
        defaultAttachmentShouldBeFound("fileName.specified=true");

        // Get all the attachmentList where fileName is null
        defaultAttachmentShouldNotBeFound("fileName.specified=false");
    }
                @Test
    @Transactional
    public void getAllAttachmentsByFileNameContainsSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where fileName contains DEFAULT_FILE_NAME
        defaultAttachmentShouldBeFound("fileName.contains=" + DEFAULT_FILE_NAME);

        // Get all the attachmentList where fileName contains UPDATED_FILE_NAME
        defaultAttachmentShouldNotBeFound("fileName.contains=" + UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByFileNameNotContainsSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where fileName does not contain DEFAULT_FILE_NAME
        defaultAttachmentShouldNotBeFound("fileName.doesNotContain=" + DEFAULT_FILE_NAME);

        // Get all the attachmentList where fileName does not contain UPDATED_FILE_NAME
        defaultAttachmentShouldBeFound("fileName.doesNotContain=" + UPDATED_FILE_NAME);
    }


    @Test
    @Transactional
    public void getAllAttachmentsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where status equals to DEFAULT_STATUS
        defaultAttachmentShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the attachmentList where status equals to UPDATED_STATUS
        defaultAttachmentShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where status not equals to DEFAULT_STATUS
        defaultAttachmentShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the attachmentList where status not equals to UPDATED_STATUS
        defaultAttachmentShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultAttachmentShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the attachmentList where status equals to UPDATED_STATUS
        defaultAttachmentShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllAttachmentsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        // Get all the attachmentList where status is not null
        defaultAttachmentShouldBeFound("status.specified=true");

        // Get all the attachmentList where status is null
        defaultAttachmentShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllAttachmentsByRequestIsEqualToSomething() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);
        Request request = RequestResourceIT.createEntity(em);
        em.persist(request);
        em.flush();
        attachment.setRequest(request);
        attachmentRepository.saveAndFlush(attachment);
        Long requestId = request.getId();

        // Get all the attachmentList where request equals to requestId
        defaultAttachmentShouldBeFound("requestId.equals=" + requestId);

        // Get all the attachmentList where request equals to requestId + 1
        defaultAttachmentShouldNotBeFound("requestId.equals=" + (requestId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAttachmentShouldBeFound(String filter) throws Exception {
        restAttachmentMockMvc.perform(get("/api/attachments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].attachmentType").value(hasItem(DEFAULT_ATTACHMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));

        // Check, that the count call also returns 1
        restAttachmentMockMvc.perform(get("/api/attachments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAttachmentShouldNotBeFound(String filter) throws Exception {
        restAttachmentMockMvc.perform(get("/api/attachments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAttachmentMockMvc.perform(get("/api/attachments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingAttachment() throws Exception {
        // Get the attachment
        restAttachmentMockMvc.perform(get("/api/attachments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttachment() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        int databaseSizeBeforeUpdate = attachmentRepository.findAll().size();

        // Update the attachment
        Attachment updatedAttachment = attachmentRepository.findById(attachment.getId()).get();
        // Disconnect from session so that the updates on updatedAttachment are not directly saved in db
        em.detach(updatedAttachment);
        updatedAttachment
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .attachmentType(UPDATED_ATTACHMENT_TYPE)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS);
        AttachmentDTO attachmentDTO = attachmentMapper.toDto(updatedAttachment);

        restAttachmentMockMvc.perform(put("/api/attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attachmentDTO)))
            .andExpect(status().isOk());

        // Validate the Attachment in the database
        List<Attachment> attachmentList = attachmentRepository.findAll();
        assertThat(attachmentList).hasSize(databaseSizeBeforeUpdate);
        Attachment testAttachment = attachmentList.get(attachmentList.size() - 1);
        assertThat(testAttachment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAttachment.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testAttachment.getAttachmentType()).isEqualTo(UPDATED_ATTACHMENT_TYPE);
        assertThat(testAttachment.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testAttachment.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAttachment() throws Exception {
        int databaseSizeBeforeUpdate = attachmentRepository.findAll().size();

        // Create the Attachment
        AttachmentDTO attachmentDTO = attachmentMapper.toDto(attachment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttachmentMockMvc.perform(put("/api/attachments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attachmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Attachment in the database
        List<Attachment> attachmentList = attachmentRepository.findAll();
        assertThat(attachmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttachment() throws Exception {
        // Initialize the database
        attachmentRepository.saveAndFlush(attachment);

        int databaseSizeBeforeDelete = attachmentRepository.findAll().size();

        // Delete the attachment
        restAttachmentMockMvc.perform(delete("/api/attachments/{id}", attachment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Attachment> attachmentList = attachmentRepository.findAll();
        assertThat(attachmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
