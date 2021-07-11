package com.attijari.service;

import java.util.List;

import com.attijari.domain.SupplyMatrix_;
import com.attijari.service.dto.SupplyMatrixCriteria;
import com.attijari.service.dto.SupplyMatrixDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.SupplyMatrix;

import com.attijari.repository.SupplyMatrixRepository;
import com.attijari.service.mapper.SupplyMatrixMapper;

/**
 * Service for executing complex queries for {@link SupplyMatrix} entities in the database.
 * The main input is a {@link SupplyMatrixCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SupplyMatrixDTO} or a {@link Page} of {@link SupplyMatrixDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SupplyMatrixQueryService extends QueryService<SupplyMatrix> {

    private final Logger log = LoggerFactory.getLogger(SupplyMatrixQueryService.class);

    private final SupplyMatrixRepository supplyMatrixRepository;

    private final SupplyMatrixMapper supplyMatrixMapper;

    public SupplyMatrixQueryService(SupplyMatrixRepository supplyMatrixRepository, SupplyMatrixMapper supplyMatrixMapper) {
        this.supplyMatrixRepository = supplyMatrixRepository;
        this.supplyMatrixMapper = supplyMatrixMapper;
    }

    /**
     * Return a {@link List} of {@link SupplyMatrixDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SupplyMatrixDTO> findByCriteria(SupplyMatrixCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SupplyMatrix> specification = createSpecification(criteria);
        return supplyMatrixMapper.toDto(supplyMatrixRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SupplyMatrixDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SupplyMatrixDTO> findByCriteria(SupplyMatrixCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SupplyMatrix> specification = createSpecification(criteria);
        return supplyMatrixRepository.findAll(specification, page)
            .map(supplyMatrixMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SupplyMatrixCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SupplyMatrix> specification = createSpecification(criteria);
        return supplyMatrixRepository.count(specification);
    }

    /**
     * Function to convert {@link SupplyMatrixCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SupplyMatrix> createSpecification(SupplyMatrixCriteria criteria) {
        Specification<SupplyMatrix> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SupplyMatrix_.id));
            }
            if (criteria.getCategoryId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCategoryId(), SupplyMatrix_.categoryId));
            }
            if (criteria.getIncomeTypeCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIncomeTypeCode(), SupplyMatrix_.incomeTypeCode));
            }
            if (criteria.getIncomeType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIncomeType(), SupplyMatrix_.incomeType));
            }
            if (criteria.getMonthlyIncomeId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonthlyIncomeId(), SupplyMatrix_.monthlyIncomeId));
            }
            if (criteria.getMarketCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMarketCode(), SupplyMatrix_.marketCode));
            }
            if (criteria.getMarket() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMarket(), SupplyMatrix_.market));
            }
            if (criteria.getSegmentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSegmentCode(), SupplyMatrix_.segmentCode));
            }
            if (criteria.getSegment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSegment(), SupplyMatrix_.segment));
            }
            if (criteria.getActivityId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActivityId(), SupplyMatrix_.activityId));
            }
            if (criteria.getProfessionCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProfessionCode(), SupplyMatrix_.professionCode));
            }
            if (criteria.getProfession() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProfession(), SupplyMatrix_.profession));
            }
        }
        return specification;
    }
}
