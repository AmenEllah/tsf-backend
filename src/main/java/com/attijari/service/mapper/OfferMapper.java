package com.attijari.service.mapper;


import com.attijari.domain.Offer;
import com.attijari.service.dto.OfferDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Offer} and its DTO {@link OfferDTO}.
 */
@Mapper(componentModel = "spring", uses = {DetailOfferMapper.class})
public interface OfferMapper extends EntityMapper<OfferDTO, Offer> {


    @Mapping(target = "requests", ignore = true)
    @Mapping(target = "removeRequest", ignore = true)
    @Mapping(target = "removeDetailOffer", ignore = true)
    Offer toEntity(OfferDTO offerDTO);

    default Offer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Offer offer = new Offer();
        offer.setId(id);
        return offer;
    }
}
