package tcss556.entities;

import lombok.Builder;
import lombok.Data;
import tcss556.services.models.UserGroup;

@Data
@Builder
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Integer privilege;
    @Builder.Default
    private UserGroup userGroup = UserGroup.USER;
    private Integer floor;
    private Double location_x;
    private Double location_y;

}
