package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;
import tcss556.services.models.UserGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserData {
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    private String password;
    private UserGroup group;
    private int privilege;
    private int floor;
    private double location_x;
    private double location_y;


}
