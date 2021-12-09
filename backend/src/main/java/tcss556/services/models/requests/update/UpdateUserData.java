package tcss556.services.models.requests.update;

import lombok.Data;
import tcss556.services.models.UserGroup;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserData {

    @NotBlank
    private String password;
    private UserGroup group;
    private Integer privilege;
    private Integer floor;
    private Double location_x;
    private Double location_y;
}
