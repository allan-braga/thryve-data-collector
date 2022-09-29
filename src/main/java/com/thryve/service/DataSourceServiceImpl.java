package com.thryve.service;

import com.thryve.domain.DataEpoch;
import com.thryve.domain.DataSource;
import com.thryve.domain.User;
import com.thryve.repository.DataEpochRepository;
import com.thryve.repository.DataSourceRepository;
import com.thryve.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataSourceServiceImpl implements DataSourceService {

  private final DataSourceRepository dataSourceRepository;
  private final DataEpochRepository dataEpochRepository;
  private final UserRepository userRepository;

  @Override
  public List<DataSource> insert(final List<DataSource> dataSources, final String token) {
    dataSources.forEach(
        ds -> {
          DataSource dataSource = validateDataSource(ds, token);
          insertData(dataSource, ds.getData());
        });
    return dataSources;
  }

  @Override
  public List<DataSource> getAllByToken(final String token) {
    return userRepository.findByToken(token).map(dataSourceRepository::findByUser).orElse(null);
  }

  @Override
  public Double getAverageByToken(final String token) {
      return userRepository.findByToken(token)
        .map(dataSourceRepository::findByUser)
        .stream()
        .flatMap(Collection::stream)
        .flatMap(ds -> ds.getData().stream())
        .mapToInt(ep -> Integer.parseInt(ep.getValue()))
        .average().orElse(0);
  }

  private void insertData(final DataSource dataSource, final List<DataEpoch> epochList) {
    dataEpochRepository.saveAll(
        epochList.stream().peek(ep -> ep.setDataSource(dataSource)).collect(Collectors.toList()));
  }

  private DataSource validateDataSource(final DataSource dataSource, final String token) {
    dataSource.setUser(validateUserToken(token));
    return dataSourceRepository
        .findByUserAndDataSourceNumber(dataSource.getUser(), dataSource.getDataSourceNumber())
        .orElseGet(() -> dataSourceRepository.save(dataSource));
  }

  private User validateUserToken(final String token) {
    return userRepository
        .findByToken(token)
        .orElseGet(() -> userRepository.save(User.builder().token(token).build()));
  }
}
