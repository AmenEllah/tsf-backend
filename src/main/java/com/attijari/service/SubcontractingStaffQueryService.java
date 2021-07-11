package com.attijari.service;

import java.util.List;

import com.attijari.domain.SubcontractingStaff;
import com.attijari.domain.SubcontractingStaff_;
import com.attijari.repository.SubcontractingStaffRepository;
import com.attijari.service.dto.SubcontractingStaffCriteria;
import com.attijari.service.dto.SubcontractingStaffDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.service.mapper.SubcontractingStaffMapper;

/**
 * Service for executing complex queries for {@link SubcontractingStaff} entities in the database.
 * The main input is a {@link SubcontractingStaffCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SubcontractingStaffDTO} or a {@link Page} of {@link SubcontractingStaffDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SubcontractingStaffQueryService extends QueryService<SubcontractingStaff> {

    private final Logger log = LoggerFactory.getLogger(SubcontractingStaffQueryService.class);

    private final SubcontractingStaffRepository subcontractingStaffRepository;

    private final SubcontractingStaffMapper subcontractingStaffMapper;

    public SubcontractingStaffQueryService(SubcontractingStaffRepository subcontractingStaffRepository, SubcontractingStaffMapper subcontractingStaffMapper) {
        this.subcontractingStaffRepository = subcontractingStaffRepository;
        this.subcontractingStaffMapper = subcontractingStaffMapper;
    }

    /**
     * Return a {@link List} of {@link SubcontractingStaffDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SubcontractingStaffDTO> findByCriteria(SubcontractingStaffCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SubcontractingStaff> specification = createSpecification(criteria);
        return subcontractingStaffMapper.toDto(subcontractingStaffRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SubcontractingStaffDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SubcontractingStaffDTO> findByCriteria(SubcontractingStaffCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SubcontractingStaff> specification = createSpecification(criteria);
        return subcontractingStaffRepository.findAll(specification, page)
            .map(subcontractingStaffMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SubcontractingStaffCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SubcontractingStaff> specification = createSpecification(criteria);
        return subcontractingStaffRepository.count(specification);
    }

    /**
     * Function to convert {@link SubcontractingStaffCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SubcontractingStaff> createSpecification(SubcontractingStaffCriteria criteria) {
        Specification<SubcontractingStaff> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SubcontractingStaff_.id));
            }
            if (criteria.getMatricule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatricule(), SubcontractingStaff_.matricule));
            }
            if (criteria.getIdBu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdBu(), SubcontractingStaff_.idBu));
            }
            if (criteria.getIdRole() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdRole(), SubcontractingStaff_.idRole));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), SubcontractingStaff_.email));
            }
            if (criteria.getIdPoste() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdPoste(), SubcontractingStaff_.idPoste));
            }
        }
        return specification;
    }
}
