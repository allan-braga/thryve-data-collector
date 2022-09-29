package com.thryve.controller;

import com.thryve.controller.dtos.DataSourceListDto;
import com.thryve.domain.DataSource;
import com.thryve.mapper.DataSourceMapper;
import com.thryve.service.DataSourceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("datasources")
@RequiredArgsConstructor
public class DataSourceController {

  private final DataSourceMapper mapper;
  private final DataSourceService service;

  @PostMapping
  public DataSourceListDto insertData(@RequestBody DataSourceListDto dto) {
    final List<DataSource> entities =
        service.insert(mapper.dtoToEntity(dto.getDataSources()), dto.getAuthenticationToken());
    return DataSourceListDto.builder()
        .authenticationToken(dto.getAuthenticationToken())
        .dataSources(mapper.entityToDto(entities))
        .build();
  }

  @GetMapping("/{token}")
  public DataSourceListDto getAllDataSourceByUserToken(@PathVariable String token) {
    final List<DataSource> entities = service.getAllByToken(token);
    return DataSourceListDto.builder()
        .authenticationToken(token)
        .dataSources(mapper.entityToDto(entities))
        .build();
  }

  @GetMapping("/average/{token}")
  public Double getAverageByUserToken(@PathVariable String token) {
    return service.getAverageByToken(token);
  }
}
