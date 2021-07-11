package com.attijari.service;

import java.util.List;

import com.attijari.domain.FAQ_;
import com.attijari.service.dto.FAQCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.FAQ;
import com.attijari.repository.FAQRepository;
import com.attijari.service.dto.FAQDTO;
import com.attijari.service.mapper.FAQMapper;

/**
 * Service for executing complex queries for {@link FAQ} entities in the database.
 * The main input is a {@link FAQCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FAQDTO} or a {@link Page} of {@link FAQDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FAQQueryService extends QueryService<FAQ> {

    private final Logger log = LoggerFactory.getLogger(FAQQueryService.class);

    private final FAQRepository fAQRepository;

    private final FAQMapper fAQMapper;

    public FAQQueryService(FAQRepository fAQRepository, FAQMapper fAQMapper) {
        this.fAQRepository = fAQRepository;
        this.fAQMapper = fAQMapper;
    }

    /**
     * Return a {@link List} of {@link FAQDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FAQDTO> findByCriteria(FAQCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FAQ> specification = createSpecification(criteria);
        return fAQMapper.toDto(fAQRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FAQDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FAQDTO> findByCriteria(FAQCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FAQ> specification = createSpecification(criteria);
        return fAQRepository.findAll(specification, page)
            .map(fAQMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FAQCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FAQ> specification = createSpecification(criteria);
        return fAQRepository.count(specification);
    }

    /**
     * Function to convert {@link FAQCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FAQ> createSpecification(FAQCriteria criteria) {
        Specification<FAQ> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FAQ_.id));
            }
            if (criteria.getQuestion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQuestion(), FAQ_.question));
            }
            if (criteria.getAnswer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAnswer(), FAQ_.answer));
            }
        }
        return specification;
    }
}
