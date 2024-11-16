package com.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.domain.Diary;
import com.dto.DiaryDTO;

@Mapper(componentModel = "spring")
public interface DiaryMapper {
    // Map single Diary to DiaryDTO
    DiaryDTO toDiaryDTO(Diary diary);

    // Map single DiaryDTO to Diary
    Diary toDiary(DiaryDTO dto);

    // map from DTO to Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateDiaryFromDto(DiaryDTO dto, @MappingTarget Diary entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateDiaryFromEntity(Diary entity, @MappingTarget DiaryDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateDiaryFromEntity(List<Diary> entities, @MappingTarget List<DiaryDTO> dtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateDiaryFromEntity(Collection<Diary> diaries, @MappingTarget List<DiaryDTO> diaryDTOs);

    // Map list of Diary entities to list of DiaryDTOs
    List<DiaryDTO> toDiaryDTOList(List<Diary> entities);

    // Map list of DiaryDTOs to list of Diary entities
    List<Diary> toDiaryList(List<DiaryDTO> dtos);
}