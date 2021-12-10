package tcss556.services.models.requests;

import lombok.Data;
import tcss556.services.models.responses.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class CreateMeetingRequest {
    @NotNull
    private User host;
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @Positive
    private long RoomId;
    private List<User> guests;
}
