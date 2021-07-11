package com.attijari.service.impl;

import com.attijari.domain.DetailOffer;
import com.attijari.service.DetailOfferService;
import com.attijari.service.dto.DetailOfferDTO;
import com.attijari.repository.DetailOfferRepository;
import com.attijari.service.mapper.DetailOfferMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DetailOffer}.
 */
@Service
@Transactional
public class DetailOfferServiceImpl implements DetailOfferService {

    private final Logger log = LoggerFactory.getLogger(DetailOfferServiceImpl.class);

    private final DetailOfferRepository detailOfferRepository;

    private final DetailOfferMapper detailOfferMapper;

    public DetailOfferServiceImpl(DetailOfferRepository detailOfferRepository, DetailOfferMapper detailOfferMapper) {
        this.detailOfferRepository = detailOfferRepository;
        this.detailOfferMapper = detailOfferMapper;
    }

    @Override
    public DetailOfferDTO save(DetailOfferDTO detailOfferDTO) {
        log.debug("Request to save DetailOffer : {}", detailOfferDTO);
        DetailOffer detailOffer = detailOfferMapper.toEntity(detailOfferDTO);
        detailOffer = detailOfferRepository.save(detailOffer);
        return detailOfferMapper.toDto(detailOffer);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetailOfferDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailOffers");
        return detailOfferRepository.findAll(pageable)
            .map(detailOfferMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DetailOfferDTO> findOne(Long id) {
        log.debug("Request to get DetailOffer : {}", id);
        return detailOfferRepository.findById(id)
            .map(detailOfferMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailOffer : {}", id);
        detailOfferRepository.deleteById(id);
    }
}
