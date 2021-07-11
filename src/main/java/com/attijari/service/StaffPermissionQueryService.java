package com.attijari.service;

import java.util.List;

import com.attijari.domain.StaffPermission_;
import com.attijari.service.dto.StaffPermissionCriteria;
import com.attijari.service.dto.StaffPermissionDTO;
import com.attijari.service.mapper.StaffPermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.StaffPermission;
import com.attijari.repository.StaffPermissionRepository;

/**
 * Service for executing complex queries for {@link StaffPermission} entities in the database.
 * The main input is a {@link StaffPermissionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link StaffPermissionDTO} or a {@link Page} of {@link StaffPermissionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class StaffPermissionQueryService extends QueryService<StaffPermission> {

    private final Logger log = LoggerFactory.getLogger(StaffPermissionQueryService.class);

    private final StaffPermissionRepository staffPermissionRepository;

    private final StaffPermissionMapper staffPermissionMapper;

    public StaffPermissionQueryService(StaffPermissionRepository staffPermissionRepository, StaffPermissionMapper staffPermissionMapper) {
        this.staffPermissionRepository = staffPermissionRepository;
        this.staffPermissionMapper = staffPermissionMapper;
    }

    /**
     * Return a {@link List} of {@link StaffPermissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StaffPermissionDTO> findByCriteria(StaffPermissionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<StaffPermission> specification = createSpecification(criteria);
        return staffPermissionMapper.toDto(staffPermissionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link StaffPermissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StaffPermissionDTO> findByCriteria(StaffPermissionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<StaffPermission> specification = createSpecification(criteria);
        return staffPermissionRepository.findAll(specification, page)
            .map(staffPermissionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StaffPermissionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<StaffPermission> specification = createSpecification(criteria);
        return staffPermissionRepository.count(specification);
    }

    /**
     * Function to convert {@link StaffPermissionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<StaffPermission> createSpecification(StaffPermissionCriteria criteria) {
        Specification<StaffPermission> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), StaffPermission_.id));
            }
            if (criteria.getIdRole() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdRole(), StaffPermission_.idRole));
            }
            if (criteria.getIdBu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdBu(), StaffPermission_.idBu));
            }
            if (criteria.getAction() != null) {
                specification = specification.and(buildSpecification(criteria.getAction(), StaffPermission_.action));
            }
            if (criteria.getScopePermission() != null) {
                specification = specification.and(buildSpecification(criteria.getScopePermission(), StaffPermission_.scopePermission));
            }
            if (criteria.getStaffType() != null) {
                specification = specification.and(buildSpecification(criteria.getStaffType(), StaffPermission_.staffType));
            }
        }
        return specification;
    }
}
