package com.attijari.service;

import java.util.List;

import com.attijari.domain.AppSettings;
import com.attijari.domain.AppSettings_;
import com.attijari.repository.AppSettingsRepository;
import com.attijari.service.dto.AppSettingsCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;



/**
 * Service for executing complex queries for {@link AppSettings} entities in the database.
 * The main input is a {@link AppSettingsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AppSettings} or a {@link Page} of {@link AppSettings} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AppSettingsQueryService extends QueryService<AppSettings> {

    private final Logger log = LoggerFactory.getLogger(AppSettingsQueryService.class);

    private final AppSettingsRepository appSettingsRepository;

    public AppSettingsQueryService(AppSettingsRepository appSettingsRepository) {
        this.appSettingsRepository = appSettingsRepository;
    }

    /**
     * Return a {@link List} of {@link AppSettings} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AppSettings> findByCriteria(AppSettingsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AppSettings> specification = createSpecification(criteria);
        return appSettingsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link AppSettings} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AppSettings> findByCriteria(AppSettingsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AppSettings> specification = createSpecification(criteria);
        return appSettingsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AppSettingsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AppSettings> specification = createSpecification(criteria);
        return appSettingsRepository.count(specification);
    }

    /**
     * Function to convert {@link AppSettingsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AppSettings> createSpecification(AppSettingsCriteria criteria) {
        Specification<AppSettings> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AppSettings_.id));
            }
            if (criteria.getMailSenderProvider() != null) {
                specification = specification.and(buildSpecification(criteria.getMailSenderProvider(), AppSettings_.mailSenderProvider));
            }
            if (criteria.getSignerProvider() != null) {
                specification = specification.and(buildSpecification(criteria.getSignerProvider(), AppSettings_.signerProvider));
            }
        }
        return specification;
    }
}
