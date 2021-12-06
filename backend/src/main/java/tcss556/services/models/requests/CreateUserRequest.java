package tcss556.services.models.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {

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

    enum UserGroup {
        ADMIN, USER
    }
}
