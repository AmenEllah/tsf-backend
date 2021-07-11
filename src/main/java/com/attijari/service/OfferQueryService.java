package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.DetailOffer_;
import com.attijari.domain.Offer_;
import com.attijari.domain.Request_;
import com.attijari.service.dto.OfferCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.Offer;
import com.attijari.repository.OfferRepository;
import com.attijari.service.dto.OfferDTO;
import com.attijari.service.mapper.OfferMapper;

/**
 * Service for executing complex queries for {@link Offer} entities in the database.
 * The main input is a {@link OfferCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OfferDTO} or a {@link Page} of {@link OfferDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OfferQueryService extends QueryService<Offer> {

    private final Logger log = LoggerFactory.getLogger(OfferQueryService.class);

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;

    public OfferQueryService(OfferRepository offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

    /**
     * Return a {@link List} of {@link OfferDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OfferDTO> findByCriteria(OfferCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Offer> specification = createSpecification(criteria);
        return offerMapper.toDto(offerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OfferDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OfferDTO> findByCriteria(OfferCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Offer> specification = createSpecification(criteria);
        return offerRepository.findAll(specification, page)
            .map(offerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OfferCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Offer> specification = createSpecification(criteria);
        return offerRepository.count(specification);
    }

    /**
     * Function to convert {@link OfferCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Offer> createSpecification(OfferCriteria criteria) {
        Specification<Offer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Offer_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Offer_.name));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Offer_.price));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), Offer_.url));
            }
            if (criteria.getRequestId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestId(),
                    root -> root.join(Offer_.requests, JoinType.LEFT).get(Request_.id)));
            }
            if (criteria.getDetailOfferId() != null) {
                specification = specification.and(buildSpecification(criteria.getDetailOfferId(),
                    root -> root.join(Offer_.detailOffers, JoinType.LEFT).get(DetailOffer_.id)));
            }
        }
        return specification;
    }
}
