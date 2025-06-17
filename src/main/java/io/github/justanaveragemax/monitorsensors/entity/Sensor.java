package io.github.justanaveragemax.monitorsensors.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "sensors")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Size(max = 30)
  @NotNull
  @Column(name = "name", nullable = false, length = 30)
  private String name;

  @Size(max = 15)
  @NotNull
  @Column(name = "model", nullable = false, length = 15)
  private String model;

  @NotNull
  @Column(name = "range_from", nullable = false)
  private Integer rangeFrom;

  @NotNull
  @Column(name = "range_to", nullable = false)
  private Integer rangeTo;

  @Size(max = 40)
  @Column(name = "location", length = 40)
  private String location;

  @Size(max = 200)
  @Column(name = "description", length = 200)
  private String description;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "type_id", nullable = false)
  private SensorType type;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "unit_id", nullable = false)
  private SensorUnit unit;

}
