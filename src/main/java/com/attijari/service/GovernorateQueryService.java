package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.Governorate_;
import com.attijari.domain.Municipality_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.Governorate;
import com.attijari.repository.GovernorateRepository;
import com.attijari.service.dto.GovernorateCriteria;
import com.attijari.service.dto.GovernorateDTO;
import com.attijari.service.mapper.GovernorateMapper;

/**
 * Service for executing complex queries for {@link Governorate} entities in the database.
 * The main input is a {@link GovernorateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GovernorateDTO} or a {@link Page} of {@link GovernorateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GovernorateQueryService extends QueryService<Governorate> {

    private final Logger log = LoggerFactory.getLogger(GovernorateQueryService.class);

    private final GovernorateRepository governorateRepository;

    private final GovernorateMapper governorateMapper;

    public GovernorateQueryService(GovernorateRepository governorateRepository, GovernorateMapper governorateMapper) {
        this.governorateRepository = governorateRepository;
        this.governorateMapper = governorateMapper;
    }

    /**
     * Return a {@link List} of {@link GovernorateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GovernorateDTO> findByCriteria(GovernorateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Governorate> specification = createSpecification(criteria);
        return governorateMapper.toDto(governorateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GovernorateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GovernorateDTO> findByCriteria(GovernorateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Governorate> specification = createSpecification(criteria);
        return governorateRepository.findAll(specification, page)
            .map(governorateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GovernorateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Governorate> specification = createSpecification(criteria);
        return governorateRepository.count(specification);
    }

    /**
     * Function to convert {@link GovernorateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Governorate> createSpecification(GovernorateCriteria criteria) {
        Specification<Governorate> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Governorate_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Governorate_.name));
            }
            if (criteria.getMunicipalityId() != null) {
                specification = specification.and(buildSpecification(criteria.getMunicipalityId(),
                    root -> root.join(Governorate_.municipalities, JoinType.LEFT).get(Municipality_.id)));
            }
        }
        return specification;
    }
}
