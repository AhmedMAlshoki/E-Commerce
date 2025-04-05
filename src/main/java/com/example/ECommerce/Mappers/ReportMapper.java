package com.example.ECommerce.Mappers;


import com.example.ECommerce.DTOs.ReportDTO;
import com.example.ECommerce.Entities.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CustomerMapper.class,ProductMapper.class})
public interface ReportMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "reportedUser", source = "reportedUser")
    @Mapping(target = "id", ignore = false)

    ReportDTO reportToReportDTO(Report report);
    @Mapping(target = "user", source = "user")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "reportedUser", source = "reportedUser")
    @Mapping(target = "reportCategory", source = "reportCategory.value")
    Report reportDTOToReport(ReportDTO reportDTO);
}
