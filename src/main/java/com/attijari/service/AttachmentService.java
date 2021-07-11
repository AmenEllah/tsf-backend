package com.attijari.service;

import com.attijari.domain.Attachment;
import com.attijari.service.dto.AttachmentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Service Interface for managing {@link Attachment}.
 */
public interface AttachmentService {

    /**
     * Save a attachment.
     *
     * @param attachmentDTO the entity to save.
     * @return the persisted entity.
     */
    AttachmentDTO save(AttachmentDTO attachmentDTO);

    /**
     * Get all the attachments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AttachmentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" attachment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AttachmentDTO> findOne(Long id);

    /**
     * Delete the "id" attachment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Upload and save an attachment to database and FTP server.
     *
     * @param attachmentDTO the entity to save.
     * @param file the file to upload
     * @return the persisted entity.
     */
    AttachmentDTO uploadAttachment(MultipartFile file, AttachmentDTO attachmentDTO);

    /**
     * Upload and save an attachment.
     *
     * @param id of the entity to download.
     * @param response of the request that will contain the stream data.
     * @return the file attached to the entity in the response.
     */
    boolean downloadUploadedAttachment(Long id, HttpServletResponse response);


    byte[] downloadUploadedAttachmentAsBytes(Long id);


    AttachmentDTO uploadAttachment(byte[] file, AttachmentDTO attachmentDTO);

}
