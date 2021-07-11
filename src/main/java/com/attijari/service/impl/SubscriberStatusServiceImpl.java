package com.attijari.service.impl;

import com.attijari.service.dto.SubscriberStatusDTO;
import com.attijari.service.mapper.SubscriberStatusMapper;
import com.attijari.service.SubscriberStatusService;
import com.attijari.domain.SubscriberStatus;
import com.attijari.repository.SubscriberStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubscriberStatus}.
 */
@Service
@Transactional
public class SubscriberStatusServiceImpl implements SubscriberStatusService {

    private final Logger log = LoggerFactory.getLogger(SubscriberStatusServiceImpl.class);

    private final SubscriberStatusRepository subscriberStatusRepository;

    private final SubscriberStatusMapper subscriberStatusMapper;

    public SubscriberStatusServiceImpl(SubscriberStatusRepository subscriberStatusRepository, SubscriberStatusMapper subscriberStatusMapper) {
        this.subscriberStatusRepository = subscriberStatusRepository;
        this.subscriberStatusMapper = subscriberStatusMapper;
    }

    @Override
    public SubscriberStatusDTO save(SubscriberStatusDTO subscriberStatusDTO) {
        log.debug("Request to save SubscriberStatus : {}", subscriberStatusDTO);
        SubscriberStatus subscriberStatus = subscriberStatusMapper.toEntity(subscriberStatusDTO);
        subscriberStatus = subscriberStatusRepository.save(subscriberStatus);
        return subscriberStatusMapper.toDto(subscriberStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubscriberStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubscriberStatuses");
        return subscriberStatusRepository.findAll(pageable)
            .map(subscriberStatusMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SubscriberStatusDTO> findOne(Long id) {
        log.debug("Request to get SubscriberStatus : {}", id);
        return subscriberStatusRepository.findById(id)
            .map(subscriberStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubscriberStatus : {}", id);
        subscriberStatusRepository.deleteById(id);
    }
}
