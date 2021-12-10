package tcss556.services.models.requests;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateRoomRequest {
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

    @Override
    public String toString() {
        return "CreateRoomRequest{" + "name='" + name + '\'' + ", capacity=" + capacity + ", b_x_coordinate=" + b_x_coordinate + ", b_y_coordinate=" + b_y_coordinate + ", t_x_coordinate=" + t_x_coordinate + ", t_y_coordinate=" + t_y_coordinate + ", floor=" + floor + ", type=" + type + '}';
    }

    enum RoomType {
        CONFERENCE, GYM, CUBICLE, PANTRY, RESTROOM
    }
}
