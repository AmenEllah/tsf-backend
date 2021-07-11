package com.attijari.service.impl;

import com.attijari.domain.Attachment;
import com.attijari.repository.AttachmentRepository;
import com.attijari.service.AttachmentService;
import com.attijari.service.dto.AttachmentDTO;
import com.attijari.service.mapper.AttachmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Attachment}.
 */
@Service
@Transactional
@Profile("dev")
public class AttachmentServiceImpl implements AttachmentService {

    private final Logger log = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    private static final String TSF_UPLOAD_DIR = "/TSF_UPLOAD_DIR_RECETTE";

    private final AttachmentRepository attachmentRepository;

    private final AttachmentMapper attachmentMapper;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository, AttachmentMapper attachmentMapper) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentMapper = attachmentMapper;
    }

    @Override
    public AttachmentDTO save(AttachmentDTO attachmentDTO) {
        log.debug("Request to save Attachment : {}", attachmentDTO);
        Attachment attachment = attachmentMapper.toEntity(attachmentDTO);
        attachment = attachmentRepository.save(attachment);
        return attachmentMapper.toDto(attachment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AttachmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Attachments");
        return attachmentRepository.findAll(pageable)
            .map(attachmentMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AttachmentDTO> findOne(Long id) {
        log.debug("Request to get Attachment : {}", id);
        return attachmentRepository.findById(id)
            .map(attachmentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Attachment : {}", id);
        Optional<Attachment> existingAttachment = attachmentRepository.findById(id);
        if (existingAttachment.isPresent() && existingAttachment.get().getPath() != null) {
            try {
                Files.delete(Paths.get(existingAttachment.get().getPath()));
                attachmentRepository.delete(existingAttachment.get());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public AttachmentDTO uploadAttachment(MultipartFile file, AttachmentDTO attachmentDTO) {
        log.debug("Request to save Attachment : {}", attachmentDTO);
        if (attachmentDTO.getId() != null && !file.isEmpty()) {
            Attachment existingAttachment = attachmentRepository.getOne(attachmentDTO.getId());
            if (existingAttachment.getPath() != null && existingAttachment.getPath().startsWith(existingAttachment.getId().toString())) {
                try {
                    Files.delete(Paths.get(existingAttachment.getPath()));
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        Attachment result = attachmentRepository.save(attachmentMapper.toEntity(attachmentDTO));
        if (!file.isEmpty()) {
            result.setName(file.getOriginalFilename());
            result.setFileName(file.getOriginalFilename());

            String dirName = TSF_UPLOAD_DIR + "/" + result.getRequest().getId().toString();
            String attachmentFileName = dirName + "/" + result.getId().toString() + result.getName().substring(result.getName().lastIndexOf("."));
            try {
                java.io.File directory = new java.io.File(dirName);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                byte[] byteArr;
                byteArr = file.getBytes();
                InputStream inputStream = new ByteArrayInputStream(byteArr);
                try (OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(attachmentFileName))) {
                    int token = -1;
                    while ((token = inputStream.read()) != -1) {
                        bufferedOutputStream.write(token);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.setPath(attachmentFileName);
                result = attachmentRepository.save(result);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return attachmentMapper.toDto(result);
    }

    @Override
    public boolean downloadUploadedAttachment(Long id, HttpServletResponse response) {
        Optional<AttachmentDTO> existingAttachmentDTO = findOne(id);
        if (existingAttachmentDTO.isPresent()) {
            AttachmentDTO attachmentDTO = existingAttachmentDTO.get();
            try {
                java.io.File fileToDownload = new java.io.File(attachmentDTO.getPath());
                InputStream inputStreamToDownload = new FileInputStream(fileToDownload);
                org.apache.commons.io.IOUtils.copy(inputStreamToDownload, response.getOutputStream());
                response.setHeader("Content-Disposition", "attachment; filename=" +  attachmentDTO.getName());
                response.setContentType(URLConnection.guessContentTypeFromName(attachmentDTO.getName()));
                return true;
            } catch (IOException e) {
                log.error(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public byte[] downloadUploadedAttachmentAsBytes(Long id) {
        Optional<AttachmentDTO> existingAttachmentDTO = findOne(id);
        if (existingAttachmentDTO.isPresent()) {
            try {
                java.io.File fileToDownload = new java.io.File(existingAttachmentDTO.get().getPath());
                InputStream inputStreamToDownload = new FileInputStream(fileToDownload);
                byte[] buf = new byte[8192];
                int length = 1;
                while (length > 0) {
                    length = inputStreamToDownload.read(buf);
                }
                return buf;
            } catch (IOException e) {
                log.error(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public AttachmentDTO uploadAttachment(byte[] file, AttachmentDTO attachmentDTO) {
        log.debug("Request to save Attachment : {}", attachmentDTO);
        if (attachmentDTO.getId() != null && file != null && file.length > 0) {
            Attachment existingAttachment = attachmentRepository.getOne(attachmentDTO.getId());
            if (existingAttachment.getPath() != null && existingAttachment.getPath().startsWith(existingAttachment.getId().toString())) {
                try {
                    Files.delete(Paths.get(existingAttachment.getPath()));
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        Attachment result = attachmentRepository.save(attachmentMapper.toEntity(attachmentDTO));
        if (file != null && file.length > 0) {
            String dirName = TSF_UPLOAD_DIR + "/" + result.getRequest().getId().toString();
            String attachmentFileName = dirName + "/" + result.getId().toString() + result.getName().substring(result.getName().lastIndexOf("."));
            File directory = new File(dirName);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            byte[] byteArr;
            byteArr = file;
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            try (OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(attachmentFileName))) {
                int token = -1;
                while ((token = inputStream.read()) != -1) {
                    bufferedOutputStream.write(token);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.setPath(attachmentFileName);
            result = attachmentRepository.save(result);
        }
        return attachmentMapper.toDto(result);
    }
}
