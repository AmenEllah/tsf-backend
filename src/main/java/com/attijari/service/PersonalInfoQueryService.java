package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.*;
import com.attijari.service.dto.PersonalInfoCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.PersonalInfo;
import com.attijari.repository.PersonalInfoRepository;
import com.attijari.service.dto.PersonalInfoDTO;
import com.attijari.service.mapper.PersonalInfoMapper;

/**
 * Service for executing complex queries for {@link PersonalInfo} entities in the database.
 * The main input is a {@link PersonalInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PersonalInfoDTO} or a {@link Page} of {@link PersonalInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PersonalInfoQueryService extends QueryService<PersonalInfo> {

    private final Logger log = LoggerFactory.getLogger(PersonalInfoQueryService.class);

    private final PersonalInfoRepository personalInfoRepository;

    private final PersonalInfoMapper personalInfoMapper;

    public PersonalInfoQueryService(PersonalInfoRepository personalInfoRepository, PersonalInfoMapper personalInfoMapper) {
        this.personalInfoRepository = personalInfoRepository;
        this.personalInfoMapper = personalInfoMapper;
    }

    /**
     * Return a {@link List} of {@link PersonalInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PersonalInfoDTO> findByCriteria(PersonalInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PersonalInfo> specification = createSpecification(criteria);
        return personalInfoMapper.toDto(personalInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PersonalInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonalInfoDTO> findByCriteria(PersonalInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PersonalInfo> specification = createSpecification(criteria);
        return personalInfoRepository.findAll(specification, page)
            .map(personalInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PersonalInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PersonalInfo> specification = createSpecification(criteria);
        return personalInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link PersonalInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PersonalInfo> createSpecification(PersonalInfoCriteria criteria) {
        Specification<PersonalInfo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PersonalInfo_.id));
            }
            if (criteria.getCivility() != null) {
                specification = specification.and(buildSpecification(criteria.getCivility(), PersonalInfo_.civility));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), PersonalInfo_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), PersonalInfo_.lastName));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), PersonalInfo_.email));
            }
            if (criteria.getNativeCountry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNativeCountry(), PersonalInfo_.nativeCountry));
            }
            if (criteria.getBirthday() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthday(), PersonalInfo_.birthday));
            }
            if (criteria.getClientABT() != null) {
                specification = specification.and(buildSpecification(criteria.getClientABT(), PersonalInfo_.clientABT));
            }
            if (criteria.getRib() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRib(), PersonalInfo_.rib));
            }
            if (criteria.getNbrkids() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNbrkids(), PersonalInfo_.nbrkids));
            }
            if (criteria.getMaritalStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaritalStatus(), PersonalInfo_.maritalStatus));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), PersonalInfo_.phone));
            }
            if (criteria.getAmericanIndex() != null) {
                specification = specification.and(buildSpecification(criteria.getAmericanIndex(), PersonalInfo_.americanIndex));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), PersonalInfo_.accountNumber));
            }
            if (criteria.getCin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCin(), PersonalInfo_.cin));
            }
            if (criteria.getAbroadResidancyProof() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAbroadResidancyProof(), PersonalInfo_.abroadResidancyProof));
            }
            if (criteria.getAbroadResidancyNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAbroadResidancyNumber(), PersonalInfo_.abroadResidancyNumber));
            }
            if (criteria.getCinDeliveryDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCinDeliveryDate(), PersonalInfo_.cinDeliveryDate));
            }
            if (criteria.getAbroadResidancyDeliveryDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAbroadResidancyDeliveryDate(), PersonalInfo_.abroadResidancyDeliveryDate));
            }
            if (criteria.getAbroadResidancyExpirationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAbroadResidancyExpirationDate(), PersonalInfo_.abroadResidancyExpirationDate));
            }
            if (criteria.getAdressInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getAdressInfoId(),
                    root -> root.join(PersonalInfo_.adressInfo, JoinType.LEFT).get(AdressInfo_.id)));
            }
            if (criteria.getAgencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getAgencyId(),
                    root -> root.join(PersonalInfo_.agency, JoinType.LEFT).get(Agency_.id)));
            }
            if (criteria.getFinancialInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinancialInfoId(),
                    root -> root.join(PersonalInfo_.financialInfo, JoinType.LEFT).get(FinancialInfo_.id)));
            }
            if (criteria.getCountryId() != null) {
                specification = specification.and(buildSpecification(criteria.getCountryId(),
                    root -> root.join(PersonalInfo_.country, JoinType.LEFT).get(Country_.id)));
            }
            if (criteria.getNationalityId() != null) {
                specification = specification.and(buildSpecification(criteria.getNationalityId(),
                    root -> root.join(PersonalInfo_.nationality, JoinType.LEFT).get(Nationality_.id)));
            }
            if (criteria.getSecondNationalityId() != null) {
                specification = specification.and(buildSpecification(criteria.getSecondNationalityId(),
                    root -> root.join(PersonalInfo_.secondNationality, JoinType.LEFT).get(Nationality_.id)));
            }

        }
        return specification;
    }
}
