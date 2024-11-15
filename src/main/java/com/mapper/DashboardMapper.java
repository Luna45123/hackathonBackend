package com.mapper;

import com.domain.Dashboard;
import com.dto.DashboardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DashboardMapper {

    DashboardMapper INSTANCE = Mappers.getMapper(DashboardMapper.class);

    DashboardDTO toDashboardDTO(Dashboard dashboard);

    Dashboard toDashboard(DashboardDTO dashboardDTO);

    void updateDashboardFromDto(DashboardDTO dto, @MappingTarget Dashboard entity);
}
