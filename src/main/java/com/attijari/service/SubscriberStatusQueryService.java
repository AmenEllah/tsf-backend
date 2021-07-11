package com.attijari.service;

import java.util.List;

import com.attijari.domain.SubscriberStatus;
import com.attijari.domain.SubscriberStatus_;
import com.attijari.repository.SubscriberStatusRepository;
import com.attijari.service.dto.SubscriberStatusCriteria;
import com.attijari.service.dto.SubscriberStatusDTO;
import com.attijari.service.mapper.SubscriberStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;




/**
 * Service for executing complex queries for {@link SubscriberStatus} entities in the database.
 * The main input is a {@link SubscriberStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SubscriberStatusDTO} or a {@link Page} of {@link SubscriberStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SubscriberStatusQueryService extends QueryService<SubscriberStatus> {

    private final Logger log = LoggerFactory.getLogger(SubscriberStatusQueryService.class);

    private final SubscriberStatusRepository subscriberStatusRepository;

    private final SubscriberStatusMapper subscriberStatusMapper;

    public SubscriberStatusQueryService(SubscriberStatusRepository subscriberStatusRepository, SubscriberStatusMapper subscriberStatusMapper) {
        this.subscriberStatusRepository = subscriberStatusRepository;
        this.subscriberStatusMapper = subscriberStatusMapper;
    }

    /**
     * Return a {@link List} of {@link SubscriberStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SubscriberStatusDTO> findByCriteria(SubscriberStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SubscriberStatus> specification = createSpecification(criteria);
        return subscriberStatusMapper.toDto(subscriberStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SubscriberStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SubscriberStatusDTO> findByCriteria(SubscriberStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SubscriberStatus> specification = createSpecification(criteria);
        return subscriberStatusRepository.findAll(specification, page)
            .map(subscriberStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SubscriberStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SubscriberStatus> specification = createSpecification(criteria);
        return subscriberStatusRepository.count(specification);
    }

    /**
     * Function to convert {@link SubscriberStatusCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SubscriberStatus> createSpecification(SubscriberStatusCriteria criteria) {
        Specification<SubscriberStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SubscriberStatus_.id));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), SubscriberStatus_.email));
            }
            if (criteria.getNumCin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumCin(), SubscriberStatus_.NumCin));
            }
            if (criteria.getWithAppelVisio() != null) {
                specification = specification.and(buildSpecification(criteria.getWithAppelVisio(), SubscriberStatus_.WithAppelVisio));
            }
            if (criteria.getWithCertif() != null) {
                specification = specification.and(buildSpecification(criteria.getWithCertif(), SubscriberStatus_.WithCertif));
            }
            if (criteria.getWithSignature() != null) {
                specification = specification.and(buildSpecification(criteria.getWithSignature(), SubscriberStatus_.WithSignature));
            }
            if (criteria.getDateAppelVisio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateAppelVisio(), SubscriberStatus_.DateAppelVisio));
            }
        }
        return specification;
    }
}
