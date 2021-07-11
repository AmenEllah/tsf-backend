package com.attijari.service.mapper;


import com.attijari.domain.Attachment;
import com.attijari.service.dto.AttachmentDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attachment} and its DTO {@link AttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {RequestMapper.class})
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {

    @Mapping(source = "request.id", target = "requestId")
    AttachmentDTO toDto(Attachment attachment);

    @Mapping(source = "requestId", target = "request")
    Attachment toEntity(AttachmentDTO attachmentDTO);

    default Attachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attachment attachment = new Attachment();
        attachment.setId(id);
        return attachment;
    }
}
