package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;
import tcss556.services.models.RoomType;

import javax.validation.constraints.Positive;

@Data
@Builder
public class RoomData {
    private long id;
    private String name;
    @Positive
    private int capacity;
    private double b_x_coordinate;
    private double b_y_coordinate;
    private double t_x_coordinate;
    private double t_y_coordinate;
    @Positive
    private int floor;
    private RoomType type;

}
