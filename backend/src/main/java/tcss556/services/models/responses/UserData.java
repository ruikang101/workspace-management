package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;
import tcss556.services.models.UserGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserData {
  private Long id;
  @NotNull private String username;
  @NotNull @Email private String email;
  private String department;
  private UserGroup group;
  private Integer privilege;
  private Integer floor;
  private Double location_x;
  private Double location_y;
}
