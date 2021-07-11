package com.attijari.service.mapper;


import com.attijari.domain.FAQ;
import com.attijari.service.dto.FAQDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link FAQ} and its DTO {@link FAQDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FAQMapper extends EntityMapper<FAQDTO, FAQ> {



    default FAQ fromId(Long id) {
        if (id == null) {
            return null;
        }
        FAQ fAQ = new FAQ();
        fAQ.setId(id);
        return fAQ;
    }
}
