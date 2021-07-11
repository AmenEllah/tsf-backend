package com.attijari.service.impl;

import com.attijari.domain.BotScan;
import com.attijari.repository.BotScanRepository;
import com.attijari.service.BotScanService;
import com.attijari.service.mapper.BotScanMapper;
import com.attijari.service.dto.BotScanDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BotScan}.
 */
@Service
@Transactional
public class BotScanServiceImpl implements BotScanService {

    private final Logger log = LoggerFactory.getLogger(BotScanServiceImpl.class);

    private final BotScanRepository botScanRepository;

    private final BotScanMapper botScanMapper;

    public BotScanServiceImpl(BotScanRepository botScanRepository, BotScanMapper botScanMapper) {
        this.botScanRepository = botScanRepository;
        this.botScanMapper = botScanMapper;
    }

    @Override
    public BotScanDTO save(BotScanDTO botScanDTO) {
        log.debug("Request to save BotScan : {}", botScanDTO);
        BotScan botScan = botScanMapper.toEntity(botScanDTO);
        botScan = botScanRepository.save(botScan);
        return botScanMapper.toDto(botScan);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BotScanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BotScans");
        return botScanRepository.findAll(pageable)
            .map(botScanMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BotScanDTO> findOne(Long id) {
        log.debug("Request to get BotScan : {}", id);
        return botScanRepository.findById(id)
            .map(botScanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BotScan : {}", id);
        botScanRepository.deleteById(id);
    }

    @Override
    public String getAgencyCode(String codeAgence) {
        if (codeAgence.length() == 5) {
            return codeAgence;
        }
        for (int i = 0; i < 5; i++) {
            codeAgence = "0" + codeAgence;
            if (codeAgence.length() == 5) {
                break;
            }
        }
        return codeAgence;
    }
}
