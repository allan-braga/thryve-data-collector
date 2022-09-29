package com.thryve.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.thryve.domain.DataEpoch;
import com.thryve.domain.DataSource;
import com.thryve.domain.User;
import com.thryve.repository.DataEpochRepository;
import com.thryve.repository.DataSourceRepository;
import com.thryve.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DataSourceServiceTest {

  private static final String TOKEN = "1234556";

  @Mock private DataSourceRepository dataSourceRepository;
  @Mock private DataEpochRepository dataEpochRepository;
  @Mock private UserRepository userRepository;

  @InjectMocks private DataSourceServiceImpl dataSourceService;

  @Test
  void shouldInsertDataSource() {
    List<DataSource> insert = dataSourceService.insert(List.of(buildDataSource()), TOKEN);

    Assertions.assertNotNull(insert);
    verify(dataSourceRepository, times(1)).findByUserAndDataSourceNumber(any(), anyLong());
    verify(userRepository, times(1)).findByToken(anyString());
    verify(dataEpochRepository, times(1)).saveAll(anyCollection());
  }

  @Test
  void shouldGetAverageByToken() {
    when(userRepository.findByToken(TOKEN)).thenReturn(Optional.of(new User()));
    dataSourceService.insert(List.of(buildDataSource()), TOKEN);
    Double averageByToken = dataSourceService.getAverageByToken(TOKEN);

    Assertions.assertNotNull(averageByToken);
    verify(dataSourceRepository, times(1)).findByUser(any());
    verify(userRepository, times(2)).findByToken(anyString());
  }

  @Test
  void shouldGetAllByToken() {
    when(userRepository.findByToken(TOKEN)).thenReturn(Optional.of(new User()));
    when(dataSourceRepository.findByUser(any())).thenReturn(List.of(buildDataSource()));
    List<DataSource> allByToken = dataSourceService.getAllByToken(TOKEN);
    Assertions.assertNotNull(allByToken);
    assertEquals(1, allByToken.size());
  }

  public DataSource buildDataSource() {
    DataSource dataSource = new DataSource();
    dataSource.setDataSourceNumber(Long.MAX_VALUE);
    dataSource.setUser(new User());

    DataEpoch dataEpoch = new DataEpoch();
    dataEpoch.setDataSource(dataSource);
    dataEpoch.setCreatedAtUnix(LocalDateTime.now());
    dataEpoch.setDynamicValueType(Long.MAX_VALUE);
    dataEpoch.setEndTimestampUnix(LocalDateTime.now());
    dataEpoch.setStartTimestampUnix(LocalDateTime.now());
    dataEpoch.setValue("70");
    dataSource.setData(List.of(dataEpoch));
    return dataSource;
  }
}
