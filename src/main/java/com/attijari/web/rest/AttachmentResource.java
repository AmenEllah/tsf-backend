package com.attijari.web.rest;

import com.attijari.config.Constants;
import com.attijari.domain.Attachment;
import com.attijari.domain.enumeration.RequestStatus;
import com.attijari.security.AuthoritiesConstants;
import com.attijari.security.SecurityUtils;
import com.attijari.service.RequestService;
import com.attijari.service.dto.RequestDTO;
import com.attijari.web.rest.errors.BadRequestAlertException;
import com.attijari.domain.enumeration.AttachmentStatus;
import com.attijari.service.AttachmentService;
import com.attijari.service.dto.AttachmentDTO;
import com.attijari.service.dto.AttachmentCriteria;
import com.attijari.service.AttachmentQueryService;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Attachment}.
 */
@RestController
@RequestMapping("/api")
public class AttachmentResource {

    private final Logger log = LoggerFactory.getLogger(AttachmentResource.class);

    private static final String ENTITY_NAME = "attachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttachmentService attachmentService;

    private final AttachmentQueryService attachmentQueryService;

    private final RequestService requestService;

    public AttachmentResource(AttachmentService attachmentService, AttachmentQueryService attachmentQueryService, RequestService requestService) {
        this.attachmentService = attachmentService;
        this.attachmentQueryService = attachmentQueryService;
        this.requestService = requestService;
    }
    /**
     * {@code POST  /attachments} : Create a new attachment.
     *
     * @param attachmentDTO the attachmentDTO to create.
     * @param file the file to upload
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attachmentDTO, or with status {@code 400 (Bad Request)} if the attachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping(value="/attachments", consumes = "multipart/form-data")
    public ResponseEntity<AttachmentDTO> createAttachment(@ModelAttribute AttachmentDTO attachmentDTO, @RequestParam MultipartFile file) throws URISyntaxException {
        log.debug("REST request to save Attachment : {}", attachmentDTO);
        if (attachmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new attachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String fileContentType = file.getContentType();
        if (!Constants.VALID_EXTENSIONS.contains(fileContentType)) {
            throw new BadRequestAlertException("File extension is invalid", ENTITY_NAME, fileContentType);
        }
        if (attachmentDTO.getRequestId() == null) {
            throw new BadRequestAlertException("File is invalid", ENTITY_NAME, "File should have one FK!");
        }
        if (SecurityUtils.getCurrentUserLogin().isPresent() && attachmentDTO.getRequestId() != null) {
            Optional<RequestDTO> existingRequestDTO = requestService.findOne(attachmentDTO.getRequestId());
            if (existingRequestDTO.isPresent() && ((SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) && SecurityUtils.getCurrentUserLogin().get().equals(existingRequestDTO.get().getUserAccountLogin())) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.STAFF))) {
                if (existingRequestDTO.get().getRequestStatus().equals(RequestStatus.TO_COMPLETE) || existingRequestDTO.get().getRequestStatus().equals(RequestStatus.COMPLETED)) {
                    attachmentDTO.setStatus(AttachmentStatus.COMPLETED);
                } else {
                    attachmentDTO.setStatus(AttachmentStatus.VALID);
                }
                AttachmentDTO result = attachmentService.uploadAttachment(file, attachmentDTO);
                return ResponseEntity.created(new URI("/api/attachments/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                    .body(result);
            } else {
                return ResponseUtil.wrapOrNotFound(Optional.empty());
            }
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    /**
     * {@code PUT  /attachments} : Updates an existing attachment.
     *
     * @param attachmentDTO the attachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attachmentDTO,
     * or with status {@code 400 (Bad Request)} if the attachmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attachments")
    public ResponseEntity<AttachmentDTO> updateAttachment(@ModelAttribute AttachmentDTO attachmentDTO, @RequestParam(name = "file", required = false) MultipartFile file) throws URISyntaxException {
        log.debug("REST request to update Attachment : {}", attachmentDTO);

        if (attachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (attachmentDTO.getRequestId() == null) {
            throw new BadRequestAlertException("File is invalid", ENTITY_NAME, "File should have one FK!");
        }
        Optional<AttachmentDTO> existingAttachmentDTO = attachmentService.findOne(attachmentDTO.getId());

        if (SecurityUtils.getCurrentUserLogin().isPresent() && existingAttachmentDTO.isPresent()) {
            Optional<RequestDTO> existingRequestDTO = requestService.findOne(existingAttachmentDTO.get().getRequestId());
            if (existingRequestDTO.isPresent() && ((SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER) && SecurityUtils.getCurrentUserLogin().get().equals(existingRequestDTO.get().getUserAccountLogin())) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.STAFF))) {
                if (file != null) {
                    if (existingRequestDTO.get().getRequestStatus().equals(RequestStatus.TO_COMPLETE) || existingRequestDTO.get().getRequestStatus().equals(RequestStatus.COMPLETED)) {
                        attachmentDTO.setStatus(AttachmentStatus.COMPLETED);
                    } else {
                        attachmentDTO.setStatus(AttachmentStatus.VALID);
                    }
                    String fileContentType = file.getContentType();
                    if(!Constants.VALID_EXTENSIONS.contains(fileContentType)) {
                        throw new BadRequestAlertException("File extension is invalid", "attachment", fileContentType);
                    }
                    AttachmentDTO result = attachmentService.uploadAttachment(file, attachmentDTO);
                    return ResponseEntity.ok()
                        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attachmentDTO.getId().toString()))
                        .body(result);
                } else {
                    existingAttachmentDTO.get().setStatus(attachmentDTO.getStatus());
                    AttachmentDTO result = attachmentService.save(existingAttachmentDTO.get());
                    return ResponseEntity.ok()
                        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attachmentDTO.getId().toString()))
                        .body(result);
                }
            } else {
                return ResponseUtil.wrapOrNotFound(Optional.empty());
            }

        } else {
            return ResponseUtil.wrapOrNotFound(Optional.empty());
        }
    }

    /**
     * {@code GET  /attachments} : get all the attachments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attachments in body.
     */
    @GetMapping("/attachments")
    public ResponseEntity<List<AttachmentDTO>> getAllAttachments(AttachmentCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Attachments by criteria: {}", criteria);
        if (SecurityUtils.getCurrentUserLogin().isPresent() && SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
            StringFilter userAccountLoginFilter = criteria.getUserAccountLogin() != null? criteria.getUserAccountLogin().copy(): new StringFilter();
            userAccountLoginFilter.setEquals(SecurityUtils.getCurrentUserLogin().get());
            criteria.setUserAccountLogin(userAccountLoginFilter);
        }
        Page<AttachmentDTO> page = attachmentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /attachments/count} : count all the attachments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/attachments/count")
    public ResponseEntity<Long> countAttachments(AttachmentCriteria criteria) {
        if (SecurityUtils.getCurrentUserLogin().isPresent() && SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
            StringFilter userAccountLoginFilter = criteria.getUserAccountLogin() != null? criteria.getUserAccountLogin().copy(): new StringFilter();
            userAccountLoginFilter.setEquals(SecurityUtils.getCurrentUserLogin().get());
            criteria.setUserAccountLogin(userAccountLoginFilter);
        }
        log.debug("REST request to count Attachments by criteria: {}", criteria);
        return ResponseEntity.ok().body(attachmentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /attachments/:id} : get the "id" attachment.
     *
     * @param id the id of the attachmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attachmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attachments/{id}")
    public ResponseEntity<AttachmentDTO> getAttachment(@PathVariable Long id) {
        log.debug("REST request to get Attachment : {}", id);
        AttachmentCriteria criteria = new AttachmentCriteria();
        criteria.setId((LongFilter) new LongFilter().setEquals(id));
        if (SecurityUtils.getCurrentUserLogin().isPresent() && SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
            StringFilter userAccountLoginFilter = criteria.getUserAccountLogin() != null? criteria.getUserAccountLogin().copy(): new StringFilter();
            userAccountLoginFilter.setEquals(SecurityUtils.getCurrentUserLogin().get());
            criteria.setUserAccountLogin(userAccountLoginFilter);
        }
        List<AttachmentDTO> attachmentDTOs = attachmentQueryService.findByCriteria(criteria);
        return attachmentDTOs.isEmpty()?ResponseUtil.wrapOrNotFound(Optional.empty()): ResponseUtil.wrapOrNotFound(Optional.of(attachmentDTOs.get(0)));
    }

    /**
     * {@code DELETE  /attachments/:id} : delete the "id" attachment from the database and FTP server.
     *
     * @param id the id of the attachmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attachments/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        log.debug("REST request to delete Attachment : {}", id);
        AttachmentCriteria criteria = new AttachmentCriteria();
        criteria.setId((LongFilter) new LongFilter().setEquals(id));
        if (SecurityUtils.getCurrentUserLogin().isPresent() && SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
            StringFilter userAccountLoginFilter = criteria.getUserAccountLogin() != null? criteria.getUserAccountLogin().copy(): new StringFilter();
            userAccountLoginFilter.setEquals(SecurityUtils.getCurrentUserLogin().get());
            criteria.setUserAccountLogin(userAccountLoginFilter);
        }
        if (!attachmentQueryService.findByCriteria(criteria).isEmpty()) {
            attachmentService.delete(id);
        }
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/attachments/{id}/download")
    public ResponseEntity downloadAttachment(@PathVariable Long id, HttpServletResponse response){
        AttachmentCriteria criteria = new AttachmentCriteria();
        criteria.setId((LongFilter) new LongFilter().setEquals(id));
        if (SecurityUtils.getCurrentUserLogin().isPresent() && SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
            StringFilter userAccountLoginFilter = criteria.getUserAccountLogin() != null? criteria.getUserAccountLogin().copy(): new StringFilter();
            userAccountLoginFilter.setEquals(SecurityUtils.getCurrentUserLogin().get());
            criteria.setUserAccountLogin(userAccountLoginFilter);
        }
        if (!attachmentQueryService.findByCriteria(criteria).isEmpty()) {
            if (attachmentService.downloadUploadedAttachment(id,response))
            return new ResponseEntity(HttpStatus.OK);
        }
        return ResponseUtil.wrapOrNotFound(Optional.empty());
    }
}
