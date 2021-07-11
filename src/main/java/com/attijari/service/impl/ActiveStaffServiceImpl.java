package com.attijari.service.impl;

import com.attijari.service.dto.ActiveStaffDTO;
import com.attijari.service.mapper.ActiveStaffMapper;
import com.attijari.service.ActiveStaffService;
import com.attijari.domain.ActiveStaff;
import com.attijari.repository.ActiveStaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ActiveStaff}.
 */
@Service
@Transactional
public class ActiveStaffServiceImpl implements ActiveStaffService {

    private final Logger log = LoggerFactory.getLogger(ActiveStaffServiceImpl.class);

    private final ActiveStaffRepository activeStaffRepository;

    private final ActiveStaffMapper activeStaffMapper;

    public ActiveStaffServiceImpl(ActiveStaffRepository activeStaffRepository, ActiveStaffMapper activeStaffMapper) {
        this.activeStaffRepository = activeStaffRepository;
        this.activeStaffMapper = activeStaffMapper;
    }

    @Override
    public ActiveStaffDTO save(ActiveStaffDTO activeStaffDTO) {
        log.debug("Request to save ActiveStaff : {}", activeStaffDTO);
        ActiveStaff activeStaff = activeStaffMapper.toEntity(activeStaffDTO);
        activeStaff = activeStaffRepository.save(activeStaff);
        return activeStaffMapper.toDto(activeStaff);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ActiveStaffDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ActiveStaffs");
        return activeStaffRepository.findAll(pageable)
            .map(activeStaffMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ActiveStaffDTO> findOne(Integer id) {
        log.debug("Request to get ActiveStaff : {}", id);
        return activeStaffRepository.findById(id)
            .map(activeStaffMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete ActiveStaff : {}", id);
        activeStaffRepository.deleteById(id);
    }
}
