package tcss556.services.models.requests.create;

import lombok.Data;
import tcss556.services.models.RoomType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateRoomData {
  @NotNull private String name;
  @Positive private int capacity;
  @NotNull private Double b_x_coordinate;
  @NotNull private Double b_y_coordinate;
  @NotNull private Double t_x_coordinate;
  @NotNull private Double t_y_coordinate;
  @Positive private Integer floor;
  private RoomType type;
}
