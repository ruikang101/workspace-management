package tcss556.services.models.requests.update;

import lombok.Data;
import tcss556.services.models.RoomType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class UpdateRoomData {
    @NotBlank
    private String name;
    @Positive
    private int capacity;
    private RoomType type;
}
