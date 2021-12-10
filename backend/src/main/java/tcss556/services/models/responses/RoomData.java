package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;
import tcss556.services.models.RoomType;

import javax.validation.constraints.Positive;

@Data
@Builder
public class RoomData {
  private Long id;
  private String name;
  @Positive private Integer capacity;
  private Double b_x_coordinate;
  private Double b_y_coordinate;
  private Double t_x_coordinate;
  private Double t_y_coordinate;
  @Positive private Integer floor;
  private RoomType type;
}
