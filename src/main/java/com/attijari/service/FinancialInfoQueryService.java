package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.Activity_;
import com.attijari.domain.Category_;
import com.attijari.domain.FinancialInfo_;
import com.attijari.domain.MonthlyNetIncome_;
import com.attijari.service.dto.FinancialInfoCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.FinancialInfo;
import com.attijari.repository.FinancialInfoRepository;
import com.attijari.service.dto.FinancialInfoDTO;
import com.attijari.service.mapper.FinancialInfoMapper;

/**
 * Service for executing complex queries for {@link FinancialInfo} entities in the database.
 * The main input is a {@link FinancialInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FinancialInfoDTO} or a {@link Page} of {@link FinancialInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FinancialInfoQueryService extends QueryService<FinancialInfo> {

    private final Logger log = LoggerFactory.getLogger(FinancialInfoQueryService.class);

    private final FinancialInfoRepository financialInfoRepository;

    private final FinancialInfoMapper financialInfoMapper;

    public FinancialInfoQueryService(FinancialInfoRepository financialInfoRepository, FinancialInfoMapper financialInfoMapper) {
        this.financialInfoRepository = financialInfoRepository;
        this.financialInfoMapper = financialInfoMapper;
    }

    /**
     * Return a {@link List} of {@link FinancialInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FinancialInfoDTO> findByCriteria(FinancialInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FinancialInfo> specification = createSpecification(criteria);
        return financialInfoMapper.toDto(financialInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FinancialInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FinancialInfoDTO> findByCriteria(FinancialInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FinancialInfo> specification = createSpecification(criteria);
        return financialInfoRepository.findAll(specification, page)
            .map(financialInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FinancialInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FinancialInfo> specification = createSpecification(criteria);
        return financialInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link FinancialInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FinancialInfo> createSpecification(FinancialInfoCriteria criteria) {
        Specification<FinancialInfo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FinancialInfo_.id));
            }
            if (criteria.getActivityId() != null) {
                specification = specification.and(buildSpecification(criteria.getActivityId(),
                    root -> root.join(FinancialInfo_.activity, JoinType.LEFT).get(Activity_.id)));
            }
            if (criteria.getCategoryId() != null) {
                specification = specification.and(buildSpecification(criteria.getCategoryId(),
                    root -> root.join(FinancialInfo_.category, JoinType.LEFT).get(Category_.id)));
            }
            if (criteria.getMonthlyNetIncomeId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonthlyNetIncomeId(),
                    root -> root.join(FinancialInfo_.monthlyNetIncome, JoinType.LEFT).get(MonthlyNetIncome_.id)));
            }
            if (criteria.getIncomeProof() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIncomeProof(), FinancialInfo_.incomeProof));
            }
        }
        return specification;
    }
}
