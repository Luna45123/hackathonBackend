package com.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.domain.Diary;
import com.dto.DiaryDTO;

@Mapper(componentModel="spring")
public interface DiaryMapper {
// map from DTO to Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateDiaryFromDto(DiaryDTO dto, @MappingTarget Diary entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateDiaryFromEntity(Diary entity,@MappingTarget DiaryDTO dto);
}
