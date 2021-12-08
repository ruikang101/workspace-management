package tcss556.services.models.requests.create;

import lombok.Data;
import tcss556.services.models.UserGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserData {

    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    private UserGroup group;
    private int privilege;
    private int floor;
    private double location_x;
    private double location_y;

}
