package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    private long id;
    private String name;
    private int cacpatiy;
    private double b_x_coordinate;
    private double b_y_coordinate;
    private double t_x_coordinate;
    private double t_y_coordinate;
    private int floor;
    private RoomType type;

    enum RoomType {
        CONFERENCE,
        GYM,
        CUBICLE,
        PANTRY,
        RESTROOM
    }
}
