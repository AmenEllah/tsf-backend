package com.attijari.service;

import java.util.List;

import com.attijari.domain.ActiveStaff_;
import com.attijari.service.dto.ActiveStaffCriteria;
import com.attijari.service.dto.ActiveStaffDTO;
import com.attijari.service.mapper.ActiveStaffMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.ActiveStaff;
import com.attijari.repository.ActiveStaffRepository;

/**
 * Service for executing complex queries for {@link ActiveStaff} entities in the database.
 * The main input is a {@link ActiveStaffCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActiveStaffDTO} or a {@link Page} of {@link ActiveStaffDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActiveStaffQueryService extends QueryService<ActiveStaff> {

    private final Logger log = LoggerFactory.getLogger(ActiveStaffQueryService.class);

    private final ActiveStaffRepository activeStaffRepository;

    private final ActiveStaffMapper activeStaffMapper;

    public ActiveStaffQueryService(ActiveStaffRepository activeStaffRepository, ActiveStaffMapper activeStaffMapper) {
        this.activeStaffRepository = activeStaffRepository;
        this.activeStaffMapper = activeStaffMapper;
    }

    /**
     * Return a {@link List} of {@link ActiveStaffDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActiveStaffDTO> findByCriteria(ActiveStaffCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ActiveStaff> specification = createSpecification(criteria);
        return activeStaffMapper.toDto(activeStaffRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActiveStaffDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActiveStaffDTO> findByCriteria(ActiveStaffCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ActiveStaff> specification = createSpecification(criteria);
        return activeStaffRepository.findAll(specification, page)
            .map(activeStaffMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActiveStaffCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ActiveStaff> specification = createSpecification(criteria);
        return activeStaffRepository.count(specification);
    }

    /**
     * Function to convert {@link ActiveStaffCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ActiveStaff> createSpecification(ActiveStaffCriteria criteria) {
        Specification<ActiveStaff> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getMatricule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatricule(), ActiveStaff_.matricule));
            }
            if (criteria.getIdBu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdBu(), ActiveStaff_.idBu));
            }
            if (criteria.getIdRole() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdRole(), ActiveStaff_.idRole));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), ActiveStaff_.email));
            }
            if (criteria.getIdPoste() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdPoste(), ActiveStaff_.idPoste));
            }
        }
        return specification;
    }
}
