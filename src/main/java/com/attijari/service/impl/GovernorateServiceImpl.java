package com.attijari.service.impl;

import com.attijari.domain.Governorate;
import com.attijari.service.GovernorateService;
import com.attijari.service.dto.GovernorateDTO;
import com.attijari.service.mapper.GovernorateMapper;
import com.attijari.repository.GovernorateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Governorate}.
 */
@Service
@Transactional
public class GovernorateServiceImpl implements GovernorateService {

    private final Logger log = LoggerFactory.getLogger(GovernorateServiceImpl.class);

    private final GovernorateRepository governorateRepository;

    private final GovernorateMapper governorateMapper;

    public GovernorateServiceImpl(GovernorateRepository governorateRepository, GovernorateMapper governorateMapper) {
        this.governorateRepository = governorateRepository;
        this.governorateMapper = governorateMapper;
    }

    @Override
    public GovernorateDTO save(GovernorateDTO governorateDTO) {
        log.debug("Request to save Governorate : {}", governorateDTO);
        Governorate governorate = governorateMapper.toEntity(governorateDTO);
        governorate = governorateRepository.save(governorate);
        return governorateMapper.toDto(governorate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GovernorateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Governorates");
        return governorateRepository.findAll(pageable)
            .map(governorateMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GovernorateDTO> findOne(Long id) {
        log.debug("Request to get Governorate : {}", id);
        return governorateRepository.findById(id)
            .map(governorateMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Governorate : {}", id);
        governorateRepository.deleteById(id);
    }
}
