package com.mapper;

import com.domain.EncourageMessage;
import com.dto.EncourageMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EncourageMessageMapper {

    EncourageMessageDTO toEncourageMessageDTO(EncourageMessage entity);

    EncourageMessage toEncourageMessage(EncourageMessageDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEncourageMessageFromDto(EncourageMessageDTO dto, @MappingTarget EncourageMessage entity);

    List<EncourageMessageDTO> toEncourageMessageDTOList(List<EncourageMessage> entities);
}
