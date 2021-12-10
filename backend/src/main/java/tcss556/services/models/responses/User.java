package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private long userId;
    private String username;
    private String email;
    private double location_x;

}
