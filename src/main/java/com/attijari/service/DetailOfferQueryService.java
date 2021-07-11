package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.DetailOffer_;
import com.attijari.domain.Offer_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.DetailOffer;
import com.attijari.repository.DetailOfferRepository;
import com.attijari.service.dto.DetailOfferCriteria;
import com.attijari.service.dto.DetailOfferDTO;
import com.attijari.service.mapper.DetailOfferMapper;

/**
 * Service for executing complex queries for {@link DetailOffer} entities in the database.
 * The main input is a {@link DetailOfferCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DetailOfferDTO} or a {@link Page} of {@link DetailOfferDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DetailOfferQueryService extends QueryService<DetailOffer> {

    private final Logger log = LoggerFactory.getLogger(DetailOfferQueryService.class);

    private final DetailOfferRepository detailOfferRepository;

    private final DetailOfferMapper detailOfferMapper;

    public DetailOfferQueryService(DetailOfferRepository detailOfferRepository, DetailOfferMapper detailOfferMapper) {
        this.detailOfferRepository = detailOfferRepository;
        this.detailOfferMapper = detailOfferMapper;
    }

    /**
     * Return a {@link List} of {@link DetailOfferDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DetailOfferDTO> findByCriteria(DetailOfferCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DetailOffer> specification = createSpecification(criteria);
        return detailOfferMapper.toDto(detailOfferRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DetailOfferDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DetailOfferDTO> findByCriteria(DetailOfferCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DetailOffer> specification = createSpecification(criteria);
        return detailOfferRepository.findAll(specification, page)
            .map(detailOfferMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DetailOfferCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DetailOffer> specification = createSpecification(criteria);
        return detailOfferRepository.count(specification);
    }

    /**
     * Function to convert {@link DetailOfferCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DetailOffer> createSpecification(DetailOfferCriteria criteria) {
        Specification<DetailOffer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DetailOffer_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), DetailOffer_.description));
            }
            if (criteria.getOfferId() != null) {
                specification = specification.and(buildSpecification(criteria.getOfferId(),
                    root -> root.join(DetailOffer_.offers, JoinType.LEFT).get(Offer_.id)));
            }
            if (criteria.getDescriptionEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescriptionEn(), DetailOffer_.descriptionEn));
            }
        }
        return specification;
    }
}
