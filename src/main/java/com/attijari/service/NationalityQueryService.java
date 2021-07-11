package com.attijari.service;

import java.util.List;

import com.attijari.domain.Nationality_;
import com.attijari.repository.NationalityRepository;
import com.attijari.service.mapper.NationalityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.Nationality;
import com.attijari.service.dto.NationalityCriteria;
import com.attijari.service.dto.NationalityDTO;

/**
 * Service for executing complex queries for {@link Nationality} entities in the database.
 * The main input is a {@link NationalityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NationalityDTO} or a {@link Page} of {@link NationalityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NationalityQueryService extends QueryService<Nationality> {

    private final Logger log = LoggerFactory.getLogger(NationalityQueryService.class);

    private final NationalityRepository nationalityRepository;

    private final NationalityMapper nationalityMapper;

    public NationalityQueryService(NationalityRepository nationalityRepository, NationalityMapper nationalityMapper) {
        this.nationalityRepository = nationalityRepository;
        this.nationalityMapper = nationalityMapper;
    }

    /**
     * Return a {@link List} of {@link NationalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NationalityDTO> findByCriteria(NationalityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Nationality> specification = createSpecification(criteria);
        return nationalityMapper.toDto(nationalityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NationalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NationalityDTO> findByCriteria(NationalityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Nationality> specification = createSpecification(criteria);
        return nationalityRepository.findAll(specification, page)
            .map(nationalityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NationalityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Nationality> specification = createSpecification(criteria);
        return nationalityRepository.count(specification);
    }

    /**
     * Function to convert {@link NationalityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Nationality> createSpecification(NationalityCriteria criteria) {
        Specification<Nationality> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Nationality_.id));
            }
            if (criteria.getLibelleFR() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelleFR(), Nationality_.libelleFR));
            }
            if (criteria.getLibelleEN() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelleEN(), Nationality_.libelleEN));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Nationality_.code));
            }
            if (criteria.getFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFlag(), Nationality_.flag));
            }
        }
        return specification;
    }
}
