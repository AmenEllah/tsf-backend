package com.attijari.service;

import java.util.List;

import com.attijari.domain.BotScan_;
import com.attijari.service.mapper.BotScanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.attijari.domain.BotScan;
import com.attijari.repository.BotScanRepository;
import com.attijari.service.dto.BotScanCriteria;
import com.attijari.service.dto.BotScanDTO;

/**
 * Service for executing complex queries for {@link BotScan} entities in the database.
 * The main input is a {@link BotScanCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BotScanDTO} or a {@link Page} of {@link BotScanDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BotScanQueryService extends QueryService<BotScan> {

    private final Logger log = LoggerFactory.getLogger(BotScanQueryService.class);

    private final BotScanRepository botScanRepository;

    private final BotScanMapper botScanMapper;

    public BotScanQueryService(BotScanRepository botScanRepository, BotScanMapper botScanMapper) {
        this.botScanRepository = botScanRepository;
        this.botScanMapper = botScanMapper;
    }

    /**
     * Return a {@link List} of {@link BotScanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BotScanDTO> findByCriteria(BotScanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BotScan> specification = createSpecification(criteria);
        return botScanMapper.toDto(botScanRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BotScanDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BotScanDTO> findByCriteria(BotScanCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BotScan> specification = createSpecification(criteria);
        return botScanRepository.findAll(specification, page)
            .map(botScanMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BotScanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BotScan> specification = createSpecification(criteria);
        return botScanRepository.count(specification);
    }

    /**
     * Function to convert {@link BotScanCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BotScan> createSpecification(BotScanCriteria criteria) {
        Specification<BotScan> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BotScan_.id));
            }
            if (criteria.getRef_demande() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRef_demande(), BotScan_.d_ref_demande));
            }
            if (criteria.getCliDelta() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCliDelta(), BotScan_.a_cliDelta));
            }
            if (criteria.getSignature() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSignature(), BotScan_.b_signature));
            }
            if (criteria.getCompte() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompte(), BotScan_.c_compte));
            }
        }
        return specification;
    }
}
