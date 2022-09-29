package com.thryve.mapper;

import com.thryve.controller.dtos.DataEpochDto;
import com.thryve.controller.dtos.DataSourceDto;
import com.thryve.domain.DataEpoch;
import com.thryve.domain.DataSource;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DataSourceMapper {

  List<DataSource> dtoToEntity(List<DataSourceDto> dtos);

  List<DataSourceDto> entityToDto(List<DataSource> dataSources);

  @Mapping(source = "dataSourceNumber", target = "dataSource")
  DataSourceDto entityToDto(DataSource dataSources);

  @Mapping(source = "dataSource", target = "dataSourceNumber")
  DataSource dtoToEntity(DataSourceDto dtos);

  default DataEpoch mapToEpoch(DataEpochDto dto) {
    if (dto == null) {
      return null;
    }
    return DataEpoch.builder()
        .createdAtUnix(Instant.ofEpochMilli(dto.getCreatedAtUnix()).atZone(ZoneId.systemDefault()).toLocalDateTime())
        .endTimestampUnix(Instant.ofEpochMilli(dto.getEndTimestampUnix()).atZone(ZoneId.systemDefault()).toLocalDateTime())
        .startTimestampUnix(Instant.ofEpochMilli(dto.getStartTimestampUnix()).atZone(ZoneId.systemDefault()).toLocalDateTime())
        .dynamicValueType(dto.getDynamicValueType())
        .valueType(dto.getValueType())
        .value(dto.getValue())
        .build();
  }

  default DataEpochDto mapToEpochDto(DataEpoch epoch) {
    if (epoch == null) {
      return null;
    }

    return DataEpochDto.builder()
        .createdAtUnix(epoch.getCreatedAtUnix().toInstant(ZoneOffset.UTC).toEpochMilli())
        .endTimestampUnix(epoch.getEndTimestampUnix().toInstant(ZoneOffset.UTC).toEpochMilli())
        .startTimestampUnix(epoch.getStartTimestampUnix().toInstant(ZoneOffset.UTC).toEpochMilli())
        .dynamicValueType(epoch.getDynamicValueType())
        .valueType(epoch.getValueType())
        .value(epoch.getValue())
        .build();
  }
}
