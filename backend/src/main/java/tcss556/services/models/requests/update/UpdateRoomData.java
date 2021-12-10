package tcss556.services.models.requests.update;

import lombok.Data;
import tcss556.services.models.RoomType;

@Data
public class UpdateRoomData {
  private String name;
  private Integer capacity;
  private RoomType type;
}
