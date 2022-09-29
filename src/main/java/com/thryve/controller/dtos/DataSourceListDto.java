package com.thryve.controller.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataSourceListDto {

  private String authenticationToken;
  private List<DataSourceDto> dataSources;

}
