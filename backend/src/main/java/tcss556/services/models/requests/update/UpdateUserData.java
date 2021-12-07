package tcss556.services.models.requests.update;

import lombok.Data;
import tcss556.services.models.UserGroup;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserData {

    @NotBlank
    private String password;
    private UserGroup group;
    private int privilege;
    private int floor;
    private double location_x;
    private double location_y;
}
