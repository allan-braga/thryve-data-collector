package com.thryve.repository;

import com.thryve.domain.DataSource;
import com.thryve.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long> {

  Optional<DataSource> findByUserAndDataSourceNumber(User user, Long id);

  List<DataSource> findByUser(User user);
}
