package com.attijari.service.mapper;


import com.attijari.domain.BotScan;

import com.attijari.service.dto.BotScanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BotScan} and its DTO {@link BotScanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BotScanMapper extends EntityMapper<BotScanDTO, BotScan> {



    default BotScan fromId(Long id) {
        if (id == null) {
            return null;
        }
        BotScan botScan = new BotScan();
        botScan.setId(id);
        return botScan;
    }
}
