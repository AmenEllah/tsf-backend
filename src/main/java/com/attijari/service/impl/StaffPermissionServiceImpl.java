package com.attijari.service.impl;

import com.attijari.service.dto.StaffPermissionDTO;
import com.attijari.service.StaffPermissionService;
import com.attijari.domain.StaffPermission;
import com.attijari.repository.StaffPermissionRepository;
import com.attijari.service.mapper.StaffPermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StaffPermission}.
 */
@Service
@Transactional
public class StaffPermissionServiceImpl implements StaffPermissionService {

    private final Logger log = LoggerFactory.getLogger(StaffPermissionServiceImpl.class);

    private final StaffPermissionRepository staffPermissionRepository;

    private final StaffPermissionMapper staffPermissionMapper;

    public StaffPermissionServiceImpl(StaffPermissionRepository staffPermissionRepository, StaffPermissionMapper staffPermissionMapper) {
        this.staffPermissionRepository = staffPermissionRepository;
        this.staffPermissionMapper = staffPermissionMapper;
    }

    @Override
    public StaffPermissionDTO save(StaffPermissionDTO staffPermissionDTO) {
        log.debug("Request to save StaffPermission : {}", staffPermissionDTO);
        StaffPermission staffPermission = staffPermissionMapper.toEntity(staffPermissionDTO);
        staffPermission = staffPermissionRepository.save(staffPermission);
        return staffPermissionMapper.toDto(staffPermission);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StaffPermissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StaffPermissions");
        return staffPermissionRepository.findAll(pageable)
            .map(staffPermissionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<StaffPermissionDTO> findOne(Long id) {
        log.debug("Request to get StaffPermission : {}", id);
        return staffPermissionRepository.findById(id)
            .map(staffPermissionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StaffPermission : {}", id);
        staffPermissionRepository.deleteById(id);
    }
}
