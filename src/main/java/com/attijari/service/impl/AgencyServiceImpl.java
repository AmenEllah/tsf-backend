package com.attijari.service.impl;

import com.attijari.domain.Agency;
import com.attijari.service.AgencyService;
import com.attijari.service.PersonalInfoService;
import com.attijari.service.dto.AgencyDTO;
import com.attijari.repository.AgencyRepository;
import com.attijari.service.mapper.AgencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Agency}.
 */
@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

    private final Logger log = LoggerFactory.getLogger(AgencyServiceImpl.class);

    private final AgencyRepository agencyRepository;

    private final AgencyMapper agencyMapper;

    private final PersonalInfoService personalInfoService;

    public AgencyServiceImpl(AgencyRepository agencyRepository, AgencyMapper agencyMapper,
                             PersonalInfoService personalInfoService) {
        this.agencyRepository = agencyRepository;
        this.agencyMapper = agencyMapper;
        this.personalInfoService = personalInfoService;
    }

    @Override
    public AgencyDTO save(AgencyDTO agencyDTO) {
        log.debug("Request to save Agency : {}", agencyDTO);
        Agency agency = agencyMapper.toEntity(agencyDTO);
        agency = agencyRepository.save(agency);
        return agencyMapper.toDto(agency);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AgencyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Agencies");
        return agencyRepository.findAll(pageable)
            .map(agencyMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AgencyDTO> findOne(Long id) {
        log.debug("Request to get Agency : {}", id);
        return agencyRepository.findById(id)
            .map(agencyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agency : {}", id);
        agencyRepository.deleteById(id);
    }
}
