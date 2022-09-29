package com.thryve.service;

import com.thryve.domain.DataSource;
import java.util.List;

public interface DataSourceService {

  List<DataSource> insert(List<DataSource> dataSource, final String token);

  List<DataSource> getAllByToken(String token);

  Double getAverageByToken(String token);

}
