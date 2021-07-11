package com.attijari.service.impl;

import com.attijari.domain.AdressInfo;
import com.attijari.service.AdressInfoService;
import com.attijari.service.dto.AdressInfoDTO;
import com.attijari.repository.AdressInfoRepository;
import com.attijari.service.mapper.AdressInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdressInfo}.
 */
@Service
@Transactional
public class AdressInfoServiceImpl implements AdressInfoService {

    private final Logger log = LoggerFactory.getLogger(AdressInfoServiceImpl.class);

    private final AdressInfoRepository adressInfoRepository;

    private final AdressInfoMapper adressInfoMapper;

    public AdressInfoServiceImpl(AdressInfoRepository adressInfoRepository, AdressInfoMapper adressInfoMapper) {
        this.adressInfoRepository = adressInfoRepository;
        this.adressInfoMapper = adressInfoMapper;
    }

    @Override
    public AdressInfoDTO save(AdressInfoDTO adressInfoDTO) {
        log.debug("Request to save AdressInfo : {}", adressInfoDTO);
        AdressInfo adressInfo = adressInfoMapper.toEntity(adressInfoDTO);
        adressInfo = adressInfoRepository.save(adressInfo);
        return adressInfoMapper.toDto(adressInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdressInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdressInfos");
        return adressInfoRepository.findAll(pageable)
            .map(adressInfoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AdressInfoDTO> findOne(Long id) {
        log.debug("Request to get AdressInfo : {}", id);
        return adressInfoRepository.findById(id)
            .map(adressInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdressInfo : {}", id);
        adressInfoRepository.deleteById(id);
    }
}
