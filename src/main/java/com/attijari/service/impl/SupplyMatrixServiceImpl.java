package com.attijari.service.impl;

import com.attijari.service.SupplyMatrixService;
import com.attijari.domain.SupplyMatrix;
import com.attijari.repository.SupplyMatrixRepository;
import com.attijari.service.dto.SupplyMatrixDTO;
import com.attijari.service.mapper.SupplyMatrixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SupplyMatrix}.
 */
@Service
@Transactional
public class SupplyMatrixServiceImpl implements SupplyMatrixService {

    private final Logger log = LoggerFactory.getLogger(SupplyMatrixServiceImpl.class);

    private final SupplyMatrixRepository supplyMatrixRepository;

    private final SupplyMatrixMapper supplyMatrixMapper;

    public SupplyMatrixServiceImpl(SupplyMatrixRepository supplyMatrixRepository, SupplyMatrixMapper supplyMatrixMapper) {
        this.supplyMatrixRepository = supplyMatrixRepository;
        this.supplyMatrixMapper = supplyMatrixMapper;
    }

    @Override
    public SupplyMatrixDTO save(SupplyMatrixDTO supplyMatrixDTO) {
        log.debug("Request to save SupplyMatrix : {}", supplyMatrixDTO);
        SupplyMatrix supplyMatrix = supplyMatrixMapper.toEntity(supplyMatrixDTO);
        supplyMatrix = supplyMatrixRepository.save(supplyMatrix);
        return supplyMatrixMapper.toDto(supplyMatrix);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SupplyMatrixDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SupplyMatrices");
        return supplyMatrixRepository.findAll(pageable)
            .map(supplyMatrixMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SupplyMatrixDTO> findOne(Long id) {
        log.debug("Request to get SupplyMatrix : {}", id);
        return supplyMatrixRepository.findById(id)
            .map(supplyMatrixMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SupplyMatrix : {}", id);
        supplyMatrixRepository.deleteById(id);
    }
}
