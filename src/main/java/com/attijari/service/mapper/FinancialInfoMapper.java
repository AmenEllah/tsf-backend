package com.attijari.service.mapper;


import com.attijari.domain.FinancialInfo;
import com.attijari.service.dto.FinancialInfoDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinancialInfo} and its DTO {@link FinancialInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {ActivityMapper.class, CategoryMapper.class, MonthlyNetIncomeMapper.class})
public interface FinancialInfoMapper extends EntityMapper<FinancialInfoDTO, FinancialInfo> {

    @Mapping(source = "activity.id", target = "activityId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "monthlyNetIncome.id", target = "monthlyNetIncomeId")
    FinancialInfoDTO toDto(FinancialInfo financialInfo);

    @Mapping(target = "activity", source = "activityId")
    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "monthlyNetIncome", source = "monthlyNetIncomeId")
    FinancialInfo toEntity(FinancialInfoDTO financialInfoDTO);

    default FinancialInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinancialInfo financialInfo = new FinancialInfo();
        financialInfo.setId(id);
        return financialInfo;
    }
}
