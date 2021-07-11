package com.attijari.repository;

import com.attijari.domain.MonthlyNetIncome;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MonthlyNetIncome entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthlyNetIncomeRepository extends JpaRepository<MonthlyNetIncome, Long>, JpaSpecificationExecutor<MonthlyNetIncome> {
}
