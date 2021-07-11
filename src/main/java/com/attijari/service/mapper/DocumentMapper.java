package com.attijari.service.mapper;


import com.attijari.domain.Document;
import com.attijari.service.dto.DocumentDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Document} and its DTO {@link DocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {RequestMapper.class})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    DocumentDTO toDto(Document document);

    Document toEntity(DocumentDTO documentDTO);

    default Document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setId(id);
        return document;
    }
}
