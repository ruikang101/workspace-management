package tcss556.services.models.requests.update;

import lombok.Data;
import tcss556.services.models.UserGroup;

@Data
public class UpdateUserData {
  private String password;
  private String department;
  private UserGroup group;
  private Integer privilege;
  private Integer floor;
  private Double location_x;
  private Double location_y;
}
