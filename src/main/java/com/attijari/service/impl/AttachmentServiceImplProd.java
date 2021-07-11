package com.attijari.service.impl;

import com.attijari.domain.Attachment;
import com.attijari.repository.AttachmentRepository;
import com.attijari.service.AttachmentService;
import com.attijari.service.dto.AttachmentDTO;
import com.attijari.service.mapper.AttachmentMapper;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;

@Service
@Transactional
@Profile(value = {"prod", "recette"})
public class AttachmentServiceImplProd implements AttachmentService {

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.user}")
    private String userName;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.account}")
    private String account;

    private final String FTP_USER_DIR = "/" + "ftptsf" + "/" + "ftptsf";

    private final Logger log = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    private final AttachmentRepository attachmentRepository;

    private final AttachmentMapper attachmentMapper;

    private final FTPClient ftpClient;

    public AttachmentServiceImplProd(AttachmentRepository attachmentRepository, AttachmentMapper attachmentMapper, FTPClient ftpClient) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentMapper = attachmentMapper;
        this.ftpClient = ftpClient;
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
        Attachment attachment = attachmentRepository.getOne(id);
        try {
            ftpClient.connect(host, port);
            ftpClient.enterLocalPassiveMode();
            log.info(ftpClient.getReplyString());
            ftpClient.login(userName, password, account);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            log.info(ftpClient.getReplyString());
            ftpClient.deleteFile(attachment.getPath());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        attachmentRepository.deleteById(id);
    }

    @Override
    public AttachmentDTO uploadAttachment(MultipartFile file, AttachmentDTO attachmentDTO) {
        log.debug("Request to save Attachment : {}", attachmentDTO);
        if (attachmentDTO.getId() != null && !file.isEmpty() && attachmentDTO.getRequestId() != null) {
            Attachment existingAttachment = attachmentRepository.getOne(attachmentDTO.getId());
            if (existingAttachment.getPath() != null && existingAttachment.getPath().startsWith(existingAttachment.getId().toString())) {
                try {
                    ftpClient.connect(host, port);
                    ftpClient.enterLocalPassiveMode();
                    log.info(ftpClient.getReplyString());
                    ftpClient.login(userName, password, account);
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    log.info(ftpClient.getReplyString());
                    ftpClient.deleteFile(existingAttachment.getPath());
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        Attachment result = attachmentRepository.save(attachmentMapper.toEntity(attachmentDTO));
        if (!file.isEmpty()) {
            attachmentDTO.setName(attachmentDTO.getName() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            attachmentDTO.setFileName(attachmentDTO.getName());
            String attachmentFileName = result.getId().toString() + attachmentDTO.getName().substring(attachmentDTO.getName().lastIndexOf("."));
            try {
                ftpClient.connect(host, port);
                ftpClient.enterLocalPassiveMode();
                log.info(ftpClient.getReplyString());
                ftpClient.login(userName, password, account);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                log.info(ftpClient.getReplyString());
                InputStream inputStream = file.getInputStream();
                ftpClient.makeDirectory(attachmentDTO.getRequestId().toString());
                ftpClient.changeWorkingDirectory(attachmentDTO.getRequestId().toString());
                ftpClient.storeFile(attachmentFileName, inputStream);
                log.info(ftpClient.getReplyString());
                inputStream.close();
                result.setPath(FTP_USER_DIR + "/" + attachmentDTO.getRequestId().toString() + "/" + attachmentFileName);
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
            try {
                ftpClient.connect(host, port);
                ftpClient.enterLocalPassiveMode();
                log.info(ftpClient.getReplyString());
                ftpClient.login(userName, password, account);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                log.info(ftpClient.getReplyString());
                ftpClient.changeWorkingDirectory(existingAttachmentDTO.get().getRequestId().toString());
                InputStream inputStream = ftpClient.retrieveFileStream(existingAttachmentDTO.get().getPath());
                copy(inputStream, response.getOutputStream());
                log.info(ftpClient.getReplyString());
                response.setHeader("Content-Disposition", "attachment; filename=" + existingAttachmentDTO.get().getName());
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
                ftpClient.connect(host, port);
                ftpClient.enterLocalPassiveMode();
                log.info(ftpClient.getReplyString());
                ftpClient.login(userName, password, account);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                log.info(ftpClient.getReplyString());
                InputStream inputStream = ftpClient.retrieveFileStream(existingAttachmentDTO.get().getPath());
                byte[] buffer = new byte[8192];
                int bytesRead;
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                return output.toByteArray();
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
        if (attachmentDTO.getId() != null && file != null && file.length > 0 && attachmentDTO.getRequestId() != null) {
            Attachment existingAttachment = attachmentRepository.getOne(attachmentDTO.getId());
            if (existingAttachment.getPath() != null && existingAttachment.getPath().startsWith(existingAttachment.getId().toString())) {
                try {
                    ftpClient.connect(host, port);
                    ftpClient.enterLocalPassiveMode();
                    log.info(ftpClient.getReplyString());
                    ftpClient.login(userName, password, account);
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    log.info(ftpClient.getReplyString());
                    ftpClient.deleteFile(existingAttachment.getPath());
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        Attachment result = attachmentRepository.save(attachmentMapper.toEntity(attachmentDTO));
        if (file != null && file.length > 0) {
            String attachmentFileName = result.getId().toString() + attachmentDTO.getName().substring(attachmentDTO.getName().lastIndexOf("."));
            try {
                ftpClient.connect(host, port);
                ftpClient.enterLocalPassiveMode();
                log.info(ftpClient.getReplyString());
                ftpClient.login(userName, password, account);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                log.info(ftpClient.getReplyString());
                InputStream inputStream = new ByteArrayInputStream(file);
                ftpClient.makeDirectory(attachmentDTO.getRequestId().toString());
                ftpClient.changeWorkingDirectory(attachmentDTO.getRequestId().toString());
                ftpClient.storeFile(attachmentFileName, inputStream);
                log.info(ftpClient.getReplyString());
                inputStream.close();
                result.setPath(FTP_USER_DIR + "/" + attachmentDTO.getRequestId().toString() + "/" + attachmentFileName);
                result.setFileName(attachmentDTO.getName());
                result = attachmentRepository.save(result);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return attachmentMapper.toDto(result);
    }

    void copy(InputStream source, OutputStream target) throws IOException {
        byte[] buf = new byte[8192];
        int length;
        while ((length = source.read(buf)) > 0) {
            target.write(buf, 0, length);
        }
    }
}
