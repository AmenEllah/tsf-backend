package com.attijari.service.mapper;


import com.attijari.domain.MonthlyNetIncome;
import com.attijari.service.dto.MonthlyNetIncomeDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link MonthlyNetIncome} and its DTO {@link MonthlyNetIncomeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MonthlyNetIncomeMapper extends EntityMapper<MonthlyNetIncomeDTO, MonthlyNetIncome> {



    default MonthlyNetIncome fromId(Long id) {
        if (id == null) {
            return null;
        }
        MonthlyNetIncome monthlyNetIncome = new MonthlyNetIncome();
        monthlyNetIncome.setId(id);
        return monthlyNetIncome;
    }
}
