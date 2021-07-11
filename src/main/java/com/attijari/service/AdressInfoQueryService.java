package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.AdressInfo_;
import com.attijari.domain.Country_;
import com.attijari.domain.PersonalInfo_;
import com.attijari.service.dto.AdressInfoCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.AdressInfo;
import com.attijari.repository.AdressInfoRepository;
import com.attijari.service.dto.AdressInfoDTO;
import com.attijari.service.mapper.AdressInfoMapper;

/**
 * Service for executing complex queries for {@link AdressInfo} entities in the database.
 * The main input is a {@link AdressInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AdressInfoDTO} or a {@link Page} of {@link AdressInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AdressInfoQueryService extends QueryService<AdressInfo> {

    private final Logger log = LoggerFactory.getLogger(AdressInfoQueryService.class);

    private final AdressInfoRepository adressInfoRepository;

    private final AdressInfoMapper adressInfoMapper;

    public AdressInfoQueryService(AdressInfoRepository adressInfoRepository, AdressInfoMapper adressInfoMapper) {
        this.adressInfoRepository = adressInfoRepository;
        this.adressInfoMapper = adressInfoMapper;
    }

    /**
     * Return a {@link List} of {@link AdressInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdressInfoDTO> findByCriteria(AdressInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AdressInfo> specification = createSpecification(criteria);
        return adressInfoMapper.toDto(adressInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AdressInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdressInfoDTO> findByCriteria(AdressInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AdressInfo> specification = createSpecification(criteria);
        return adressInfoRepository.findAll(specification, page)
            .map(adressInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AdressInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AdressInfo> specification = createSpecification(criteria);
        return adressInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link AdressInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AdressInfo> createSpecification(AdressInfoCriteria criteria) {
        Specification<AdressInfo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AdressInfo_.id));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), AdressInfo_.address));
            }
            if (criteria.getZip() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getZip(), AdressInfo_.zip));
            }
            if (criteria.getCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCity(), AdressInfo_.city));
            }
            if (criteria.getPersonalInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonalInfoId(),
                    root -> root.join(AdressInfo_.personalInfo, JoinType.LEFT).get(PersonalInfo_.id)));
            }
            if (criteria.getCountryId() != null) {
                specification = specification.and(buildSpecification(criteria.getCountryId(),
                    root -> root.join(AdressInfo_.country, JoinType.LEFT).get(Country_.id)));
            }
        }
        return specification;
    }
}
