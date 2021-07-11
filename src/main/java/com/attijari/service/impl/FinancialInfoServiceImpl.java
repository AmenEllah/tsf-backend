package com.attijari.service.impl;

import com.attijari.domain.FinancialInfo;
import com.attijari.service.FinancialInfoService;
import com.attijari.service.dto.FinancialInfoDTO;
import com.attijari.repository.FinancialInfoRepository;
import com.attijari.service.mapper.FinancialInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FinancialInfo}.
 */
@Service
@Transactional
public class FinancialInfoServiceImpl implements FinancialInfoService {

    private final Logger log = LoggerFactory.getLogger(FinancialInfoServiceImpl.class);

    private final FinancialInfoRepository financialInfoRepository;

    private final FinancialInfoMapper financialInfoMapper;

    public FinancialInfoServiceImpl(FinancialInfoRepository financialInfoRepository, FinancialInfoMapper financialInfoMapper) {
        this.financialInfoRepository = financialInfoRepository;
        this.financialInfoMapper = financialInfoMapper;
    }

    @Override
    public FinancialInfoDTO save(FinancialInfoDTO financialInfoDTO) {
        log.debug("Request to save FinancialInfo : {}", financialInfoDTO);
        FinancialInfo financialInfo = financialInfoMapper.toEntity(financialInfoDTO);
        financialInfo = financialInfoRepository.save(financialInfo);
        return financialInfoMapper.toDto(financialInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FinancialInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinancialInfos");
        return financialInfoRepository.findAll(pageable)
            .map(financialInfoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FinancialInfoDTO> findOne(Long id) {
        log.debug("Request to get FinancialInfo : {}", id);
        return financialInfoRepository.findById(id)
            .map(financialInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinancialInfo : {}", id);
        financialInfoRepository.deleteById(id);
    }

}
