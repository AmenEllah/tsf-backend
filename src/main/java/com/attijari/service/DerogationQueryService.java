package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.Derogation;
import com.attijari.domain.Derogation_;
import com.attijari.domain.Request_;
import com.attijari.service.dto.DerogationCriteria;
import com.attijari.service.dto.DerogationDTO;
import com.attijari.service.mapper.DerogationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.repository.DerogationRepository;

/**
 * Service for executing complex queries for {@link Derogation} entities in the database.
 * The main input is a {@link DerogationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DerogationDTO} or a {@link Page} of {@link DerogationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DerogationQueryService extends QueryService<Derogation> {

    private final Logger log = LoggerFactory.getLogger(DerogationQueryService.class);

    private final DerogationRepository derogationRepository;

    private final DerogationMapper derogationMapper;

    public DerogationQueryService(DerogationRepository derogationRepository, DerogationMapper derogationMapper) {
        this.derogationRepository = derogationRepository;
        this.derogationMapper = derogationMapper;
    }

    /**
     * Return a {@link List} of {@link DerogationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DerogationDTO> findByCriteria(DerogationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Derogation> specification = createSpecification(criteria);
        return derogationMapper.toDto(derogationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DerogationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DerogationDTO> findByCriteria(DerogationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Derogation> specification = createSpecification(criteria);
        return derogationRepository.findAll(specification, page)
            .map(derogationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DerogationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Derogation> specification = createSpecification(criteria);
        return derogationRepository.count(specification);
    }

    /**
     * Function to convert {@link DerogationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Derogation> createSpecification(DerogationCriteria criteria) {
        Specification<Derogation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Derogation_.id));
            }
            if (criteria.getMessage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessage(), Derogation_.message));
            }
            if (criteria.getAffectation() != null) {
                specification = specification.and(buildSpecification(criteria.getAffectation(), Derogation_.affectation));
            }
            if (criteria.getMatricule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatricule(), Derogation_.matricule));
            }
            if (criteria.getRequestId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestId(),
                    root -> root.join(Derogation_.request, JoinType.LEFT).get(Request_.id)));
            }
            if (criteria.getAdvisorMessage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdvisorMessage(), Derogation_.advisorMessage));
            }
        }
        return specification;
    }
}
