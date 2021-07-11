package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.Agency_;
import com.attijari.domain.Governorate_;
import com.attijari.domain.Municipality_;
import com.attijari.service.dto.MunicipalityCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.Municipality;
import com.attijari.repository.MunicipalityRepository;
import com.attijari.service.dto.MunicipalityDTO;
import com.attijari.service.mapper.MunicipalityMapper;

/**
 * Service for executing complex queries for {@link Municipality} entities in the database.
 * The main input is a {@link MunicipalityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MunicipalityDTO} or a {@link Page} of {@link MunicipalityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MunicipalityQueryService extends QueryService<Municipality> {

    private final Logger log = LoggerFactory.getLogger(MunicipalityQueryService.class);

    private final MunicipalityRepository municipalityRepository;

    private final MunicipalityMapper municipalityMapper;

    public MunicipalityQueryService(MunicipalityRepository municipalityRepository, MunicipalityMapper municipalityMapper) {
        this.municipalityRepository = municipalityRepository;
        this.municipalityMapper = municipalityMapper;
    }

    /**
     * Return a {@link List} of {@link MunicipalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MunicipalityDTO> findByCriteria(MunicipalityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Municipality> specification = createSpecification(criteria);
        return municipalityMapper.toDto(municipalityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MunicipalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MunicipalityDTO> findByCriteria(MunicipalityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Municipality> specification = createSpecification(criteria);
        return municipalityRepository.findAll(specification, page)
            .map(municipalityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MunicipalityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Municipality> specification = createSpecification(criteria);
        return municipalityRepository.count(specification);
    }

    /**
     * Function to convert {@link MunicipalityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Municipality> createSpecification(MunicipalityCriteria criteria) {
        Specification<Municipality> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Municipality_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Municipality_.name));
            }
            if (criteria.getAgencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getAgencyId(),
                    root -> root.join(Municipality_.agencies, JoinType.LEFT).get(Agency_.id)));
            }
            if (criteria.getGovernorateId() != null) {
                specification = specification.and(buildSpecification(criteria.getGovernorateId(),
                    root -> root.join(Municipality_.governorate, JoinType.LEFT).get(Governorate_.id)));
            }
        }
        return specification;
    }
}
