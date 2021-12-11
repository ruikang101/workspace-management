package tcss556.services.models.requests.create;

import lombok.Data;
import tcss556.services.models.UserGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserData {

  @NotNull private String username;
  @NotNull @Email private String email;
  @NotNull private String password;
  private String department;
  private UserGroup group;
  private Integer privilege;
  private Integer floor;
  private Double location_x;
  private Double location_y;
}
