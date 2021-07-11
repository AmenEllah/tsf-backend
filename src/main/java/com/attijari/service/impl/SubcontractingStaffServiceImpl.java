package com.attijari.service.impl;

import com.attijari.domain.SubcontractingStaff;
import com.attijari.repository.SubcontractingStaffRepository;
import com.attijari.service.SubcontractingStaffService;
import com.attijari.service.dto.SubcontractingStaffDTO;
import com.attijari.service.mapper.SubcontractingStaffMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubcontractingStaff}.
 */
@Service
@Transactional
public class SubcontractingStaffServiceImpl implements SubcontractingStaffService {

    private final Logger log = LoggerFactory.getLogger(SubcontractingStaffServiceImpl.class);

    private final SubcontractingStaffRepository subcontractingStaffRepository;

    private final SubcontractingStaffMapper subcontractingStaffMapper;

    public SubcontractingStaffServiceImpl(SubcontractingStaffRepository subcontractingStaffRepository, SubcontractingStaffMapper subcontractingStaffMapper) {
        this.subcontractingStaffRepository = subcontractingStaffRepository;
        this.subcontractingStaffMapper = subcontractingStaffMapper;
    }

    @Override
    public SubcontractingStaffDTO save(SubcontractingStaffDTO subcontractingStaffDTO) {
        log.debug("Request to save SubcontractingStaff : {}", subcontractingStaffDTO);
        SubcontractingStaff subcontractingStaff = subcontractingStaffMapper.toEntity(subcontractingStaffDTO);
        subcontractingStaff = subcontractingStaffRepository.save(subcontractingStaff);
        return subcontractingStaffMapper.toDto(subcontractingStaff);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubcontractingStaffDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubcontractingStaffs");
        return subcontractingStaffRepository.findAll(pageable)
            .map(subcontractingStaffMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SubcontractingStaffDTO> findOne(Long id) {
        log.debug("Request to get SubcontractingStaff : {}", id);
        return subcontractingStaffRepository.findById(id)
            .map(subcontractingStaffMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubcontractingStaff : {}", id);
        subcontractingStaffRepository.deleteById(id);
    }
}
