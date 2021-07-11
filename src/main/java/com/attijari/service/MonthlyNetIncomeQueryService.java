package com.attijari.service;

import java.util.List;

import com.attijari.domain.MonthlyNetIncome_;
import com.attijari.service.dto.MonthlyNetIncomeCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.MonthlyNetIncome;
import com.attijari.repository.MonthlyNetIncomeRepository;
import com.attijari.service.dto.MonthlyNetIncomeDTO;
import com.attijari.service.mapper.MonthlyNetIncomeMapper;

/**
 * Service for executing complex queries for {@link MonthlyNetIncome} entities in the database.
 * The main input is a {@link MonthlyNetIncomeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonthlyNetIncomeDTO} or a {@link Page} of {@link MonthlyNetIncomeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonthlyNetIncomeQueryService extends QueryService<MonthlyNetIncome> {

    private final Logger log = LoggerFactory.getLogger(MonthlyNetIncomeQueryService.class);

    private final MonthlyNetIncomeRepository monthlyNetIncomeRepository;

    private final MonthlyNetIncomeMapper monthlyNetIncomeMapper;

    public MonthlyNetIncomeQueryService(MonthlyNetIncomeRepository monthlyNetIncomeRepository, MonthlyNetIncomeMapper monthlyNetIncomeMapper) {
        this.monthlyNetIncomeRepository = monthlyNetIncomeRepository;
        this.monthlyNetIncomeMapper = monthlyNetIncomeMapper;
    }

    /**
     * Return a {@link List} of {@link MonthlyNetIncomeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonthlyNetIncomeDTO> findByCriteria(MonthlyNetIncomeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonthlyNetIncome> specification = createSpecification(criteria);
        return monthlyNetIncomeMapper.toDto(monthlyNetIncomeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonthlyNetIncomeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyNetIncomeDTO> findByCriteria(MonthlyNetIncomeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonthlyNetIncome> specification = createSpecification(criteria);
        return monthlyNetIncomeRepository.findAll(specification, page)
            .map(monthlyNetIncomeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonthlyNetIncomeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonthlyNetIncome> specification = createSpecification(criteria);
        return monthlyNetIncomeRepository.count(specification);
    }

    /**
     * Function to convert {@link MonthlyNetIncomeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MonthlyNetIncome> createSpecification(MonthlyNetIncomeCriteria criteria) {
        Specification<MonthlyNetIncome> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MonthlyNetIncome_.id));
            }
            if (criteria.getIncomesFR() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIncomesFR(), MonthlyNetIncome_.incomesFR));
            }
            if (criteria.getIncomesEN() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIncomesEN(), MonthlyNetIncome_.incomesEN));
            }
        }
        return specification;
    }
}
