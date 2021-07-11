package com.attijari.service.impl;

import com.attijari.service.mapper.NationalityMapper;
import com.attijari.service.NationalityService;
import com.attijari.domain.Nationality;
import com.attijari.repository.NationalityRepository;
import com.attijari.service.dto.NationalityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Nationality}.
 */
@Service
@Transactional
public class NationalityServiceImpl implements NationalityService {

    private final Logger log = LoggerFactory.getLogger(NationalityServiceImpl.class);

    private final NationalityRepository nationalityRepository;

    private final NationalityMapper nationalityMapper;

    public NationalityServiceImpl(NationalityRepository nationalityRepository, NationalityMapper nationalityMapper) {
        this.nationalityRepository = nationalityRepository;
        this.nationalityMapper = nationalityMapper;
    }

    @Override
    public NationalityDTO save(NationalityDTO nationalityDTO) {
        log.debug("Request to save Nationality : {}", nationalityDTO);
        Nationality nationality = nationalityMapper.toEntity(nationalityDTO);
        nationality = nationalityRepository.save(nationality);
        return nationalityMapper.toDto(nationality);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NationalityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Nationalities");
        return nationalityRepository.findAll(pageable)
            .map(nationalityMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NationalityDTO> findOne(Long id) {
        log.debug("Request to get Nationality : {}", id);
        return nationalityRepository.findById(id)
            .map(nationalityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nationality : {}", id);
        nationalityRepository.deleteById(id);
    }
}
