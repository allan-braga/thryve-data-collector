package com.thryve.controller.dtos;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataSourceDto {

  @NotNull
  private Long dataSource;
  private List<DataEpochDto> data;

}
