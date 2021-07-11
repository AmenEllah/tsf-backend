package com.attijari.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.attijari.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.Request;
import com.attijari.repository.RequestRepository;
import com.attijari.service.dto.RequestCriteria;
import com.attijari.service.dto.RequestDTO;
import com.attijari.service.mapper.RequestMapper;

/**
 * Service for executing complex queries for {@link Request} entities in the database.
 * The main input is a {@link RequestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RequestDTO} or a {@link Page} of {@link RequestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RequestQueryService extends QueryService<Request> {

    private final Logger log = LoggerFactory.getLogger(RequestQueryService.class);

    private final RequestRepository requestRepository;

    private final RequestMapper requestMapper;

    public RequestQueryService(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    /**
     * Return a {@link List} of {@link RequestDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RequestDTO> findByCriteria(RequestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Request> specification = createSpecification(criteria);
        return requestMapper.toDto(requestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RequestDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestDTO> findByCriteria(RequestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Request> specification = createSpecification(criteria);
        return requestRepository.findAll(specification, page)
            .map(requestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RequestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Request> specification = createSpecification(criteria);
        return requestRepository.count(specification);
    }

    /**
     * Function to convert {@link RequestCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Request> createSpecification(RequestCriteria criteria) {
        Specification<Request> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Request_.id));
            }
            if (criteria.getVisioDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVisioDate(), Request_.visioDate));
            }
            if (criteria.getSendingMailDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSendingMailDate(), Request_.sendingMailDate));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildSpecification(criteria.getState(), Request_.state));
            }
            if (criteria.getVisioStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getVisioStatus(), Request_.visioStatus));
            }
            if (criteria.getStep() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStep(), Request_.step));
            }
            if (criteria.getRemoteUserIp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemoteUserIp(), Request_.remoteUserIp));
            }
            if (criteria.getCodeVerification() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeVerification(), Request_.codeVerification));
            }
            if (criteria.getRejectCause() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRejectCause(), Request_.rejectCause));
            }

            if (criteria.getAmplitudeRef() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAmplitudeRef(), Request_.amplitudeRef));
            }
            if (criteria.getRequestState() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestState(), Request_.requestState));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), Request_.requestStatus));
            }
            if (criteria.getOfferId() != null) {
                specification = specification.and(buildSpecification(criteria.getOfferId(),
                    root -> root.join(Request_.offer, JoinType.LEFT).get(Offer_.id)));
            }
            if (criteria.getPersonalInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonalInfoId(),
                    root -> root.join(Request_.personalInfo, JoinType.LEFT).get(PersonalInfo_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Request_.document, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Request_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getUserAccountLogin() != null) {
                specification = specification.and(buildSpecification(criteria.getUserAccountLogin(),
                    root -> root.join(Request_.user, JoinType.LEFT).get(User_.login)));
            }
            if (criteria.getAgencyCode() != null) {
                specification = specification.and(buildSpecification(criteria.getAgencyCode(),
                    root -> root.join(Request_.personalInfo, JoinType.LEFT).get(PersonalInfo_.agency).get(Agency_.code)));
            }
            if (criteria.getHasCertificate() != null) {
                specification = specification.and(buildSpecification(criteria.getHasCertificate(), Request_.hasCertificate));
            }
            if (criteria.getDerogationsId() != null) {
                specification = specification.and(buildSpecification(criteria.getDerogationsId(),
                    root -> root.join(Request_.derogations, JoinType.LEFT).get(Derogation_.id)));
            }
            if (criteria.getPoliticallyExposed() != null) {
                specification = specification.and(buildSpecification(criteria.getPoliticallyExposed(), Request_.politicallyExposed));
            }
            if (criteria.getGreenCard() != null) {
                specification = specification.and(buildSpecification(criteria.getGreenCard(), Request_.greenCard));
            }
            if (criteria.getSubscriberStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getSubscriberStatusId(),
                    root -> root.join(Request_.subscriberStatus, JoinType.LEFT).get(SubscriberStatus_.id)));
            }
            if (criteria.getTokenToSign() != null) {
                specification = specification.and(buildSpecification(criteria.getTokenToSign(), Request_.tokenToSign));
            }
            if (criteria.getCin() != null) {
                specification = specification.and(buildSpecification(criteria.getCin(),
                    root -> root.join(Request_.personalInfo, JoinType.LEFT).get(PersonalInfo_.cin)));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildSpecification(criteria.getEmail(),
                    root -> root.join(Request_.personalInfo, JoinType.LEFT).get(PersonalInfo_.email)));
            }
            if (criteria.getMatricule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatricule(), Request_.matricule));
            }
            if (criteria.getRequestAffectation() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestAffectation(), Request_.requestAffectation));
            }
            if (criteria.getMatDerogation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatDerogation(), Request_.matDerogation));
            }
            if (criteria.getRejectStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRejectStatus(), Request_.rejectStatus));
            }
            if (criteria.getDateAppelVisio() != null) {
                specification = specification.and(buildSpecification(criteria.getDateAppelVisio(),
                    root -> root.join(Request_.subscriberStatus, JoinType.LEFT).get(SubscriberStatus_.DateAppelVisio)));
            }
            if (criteria.getTakenBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTakenBy(), Request_.takenBy));
            }
        }
        return specification;
    }
}
