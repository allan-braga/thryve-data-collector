package com.thryve.controller.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataEpochDto {

  private Long startTimestampUnix;
  private Long endTimestampUnix;
  private Long createdAtUnix;
  private Long dynamicValueType;
  private String value;
  private String valueType;
}
