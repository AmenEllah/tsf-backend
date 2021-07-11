package com.attijari.service.impl;

import com.attijari.domain.MonthlyNetIncome;
import com.attijari.service.MonthlyNetIncomeService;
import com.attijari.service.dto.MonthlyNetIncomeDTO;
import com.attijari.repository.MonthlyNetIncomeRepository;
import com.attijari.service.mapper.MonthlyNetIncomeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MonthlyNetIncome}.
 */
@Service
@Transactional
public class MonthlyNetIncomeServiceImpl implements MonthlyNetIncomeService {

    private final Logger log = LoggerFactory.getLogger(MonthlyNetIncomeServiceImpl.class);

    private final MonthlyNetIncomeRepository monthlyNetIncomeRepository;

    private final MonthlyNetIncomeMapper monthlyNetIncomeMapper;

    public MonthlyNetIncomeServiceImpl(MonthlyNetIncomeRepository monthlyNetIncomeRepository, MonthlyNetIncomeMapper monthlyNetIncomeMapper) {
        this.monthlyNetIncomeRepository = monthlyNetIncomeRepository;
        this.monthlyNetIncomeMapper = monthlyNetIncomeMapper;
    }

    @Override
    public MonthlyNetIncomeDTO save(MonthlyNetIncomeDTO monthlyNetIncomeDTO) {
        log.debug("Request to save MonthlyNetIncome : {}", monthlyNetIncomeDTO);
        MonthlyNetIncome monthlyNetIncome = monthlyNetIncomeMapper.toEntity(monthlyNetIncomeDTO);
        monthlyNetIncome = monthlyNetIncomeRepository.save(monthlyNetIncome);
        return monthlyNetIncomeMapper.toDto(monthlyNetIncome);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MonthlyNetIncomeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonthlyNetIncomes");
        return monthlyNetIncomeRepository.findAll(pageable)
            .map(monthlyNetIncomeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MonthlyNetIncomeDTO> findOne(Long id) {
        log.debug("Request to get MonthlyNetIncome : {}", id);
        return monthlyNetIncomeRepository.findById(id)
            .map(monthlyNetIncomeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonthlyNetIncome : {}", id);
        monthlyNetIncomeRepository.deleteById(id);
    }
}
