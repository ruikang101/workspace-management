package tcss556.entities;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tcss556.services.models.RoomType;

import javax.persistence.*;

/** Room entity model. */
@Builder
@Entity
@Table
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  @With
  private Long id;

  @Getter @Setter @NonNull private String name;
  @Getter @Setter @NonNull private Integer capacity;
  @Getter @Setter @NonNull private Double b_x_coordinate;
  @Getter @Setter @NonNull private Double b_y_coordinate;
  @Getter @Setter @NonNull private Double t_x_coordinate;
  @Getter @Setter @NonNull private Double t_y_coordinate;
  @Getter @Setter private Integer floor;

  @Enumerated(EnumType.STRING)
  @Getter
  @Setter
  @Builder.Default
  private RoomType type = RoomType.CONFERENCE;
}
