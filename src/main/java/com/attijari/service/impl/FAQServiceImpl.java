package com.attijari.service.impl;

import com.attijari.domain.FAQ;
import com.attijari.service.FAQService;
import com.attijari.service.dto.FAQDTO;
import com.attijari.service.mapper.FAQMapper;
import com.attijari.repository.FAQRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FAQ}.
 */
@Service
@Transactional
public class FAQServiceImpl implements FAQService {

    private final Logger log = LoggerFactory.getLogger(FAQServiceImpl.class);

    private final FAQRepository fAQRepository;

    private final FAQMapper fAQMapper;

    public FAQServiceImpl(FAQRepository fAQRepository, FAQMapper fAQMapper) {
        this.fAQRepository = fAQRepository;
        this.fAQMapper = fAQMapper;
    }

    @Override
    public FAQDTO save(FAQDTO fAQDTO) {
        log.debug("Request to save FAQ : {}", fAQDTO);
        FAQ fAQ = fAQMapper.toEntity(fAQDTO);
        fAQ = fAQRepository.save(fAQ);
        return fAQMapper.toDto(fAQ);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FAQDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FAQS");
        return fAQRepository.findAll(pageable)
            .map(fAQMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FAQDTO> findOne(Long id) {
        log.debug("Request to get FAQ : {}", id);
        return fAQRepository.findById(id)
            .map(fAQMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FAQ : {}", id);
        fAQRepository.deleteById(id);
    }
}
