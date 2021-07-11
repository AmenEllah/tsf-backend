package com.attijari.service.impl;

import com.attijari.service.dto.DerogationDTO;
import com.attijari.service.DerogationService;
import com.attijari.domain.Derogation;
import com.attijari.repository.DerogationRepository;
import com.attijari.service.mapper.DerogationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Derogation}.
 */
@Service
@Transactional
public class DerogationServiceImpl implements DerogationService {

    private final Logger log = LoggerFactory.getLogger(DerogationServiceImpl.class);

    private final DerogationRepository derogationRepository;

    private final DerogationMapper derogationMapper;

    public DerogationServiceImpl(DerogationRepository derogationRepository, DerogationMapper derogationMapper) {
        this.derogationRepository = derogationRepository;
        this.derogationMapper = derogationMapper;
    }

    @Override
    public DerogationDTO save(DerogationDTO derogationDTO) {
        log.debug("Request to save Derogation : {}", derogationDTO);
        Derogation derogation = derogationMapper.toEntity(derogationDTO);
        derogation = derogationRepository.save(derogation);
        return derogationMapper.toDto(derogation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DerogationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Derogations");
        return derogationRepository.findAll(pageable)
            .map(derogationMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DerogationDTO> findOne(Long id) {
        log.debug("Request to get Derogation : {}", id);
        return derogationRepository.findById(id)
            .map(derogationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Derogation : {}", id);
        derogationRepository.deleteById(id);
    }
}
