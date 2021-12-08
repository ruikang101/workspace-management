package tcss556.services.models.requests.create;


import lombok.Data;
import tcss556.services.models.RoomType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateRoomData {
    @NotNull
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
