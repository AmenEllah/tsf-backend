package com.attijari.service.mapper;


import com.attijari.domain.SubscriberStatus;
import com.attijari.service.dto.SubscriberStatusDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link SubscriberStatus} and its DTO {@link SubscriberStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubscriberStatusMapper extends EntityMapper<SubscriberStatusDTO, SubscriberStatus> {



    default SubscriberStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubscriberStatus subscriberStatus = new SubscriberStatus();
        subscriberStatus.setId(id);
        return subscriberStatus;
    }
}
