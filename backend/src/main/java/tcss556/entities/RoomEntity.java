package tcss556.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.With;
import tcss556.services.models.RoomType;

@Data
@Builder
public class RoomEntity {
  @With private Long id;
  @NonNull private String name;
  @NonNull private Integer capacity;
  @NonNull private Double b_x_coordinate;
  @NonNull private Double b_y_coordinate;
  @NonNull private Double t_x_coordinate;
  @NonNull private Double t_y_coordinate;
  private Integer floor;
  @Builder.Default private RoomType type = RoomType.CONFERENCE;
}
