package com.attijari.service.mapper;


import com.attijari.domain.DetailOffer;
import com.attijari.service.dto.DetailOfferDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailOffer} and its DTO {@link DetailOfferDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DetailOfferMapper extends EntityMapper<DetailOfferDTO, DetailOffer> {

    DetailOffer toEntity(DetailOfferDTO detailOfferDTO);

    default DetailOffer fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetailOffer detailOffer = new DetailOffer();
        detailOffer.setId(id);
        return detailOffer;
    }
}
