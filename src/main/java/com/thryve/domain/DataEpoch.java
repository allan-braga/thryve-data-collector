package com.thryve.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataEpoch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "datasource_id", nullable = false)
  private DataSource dataSource;

  private LocalDateTime startTimestampUnix;
  private LocalDateTime endTimestampUnix;
  private LocalDateTime createdAtUnix;
  private Long dynamicValueType;
  @Column(name = "`value`")
  private String value;
  private String valueType;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    DataEpoch dataEpoch = (DataEpoch) o;
    return id != null && Objects.equals(id, dataEpoch.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
