package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Builder
@Data
public class MeetingData {
    private long meetingId;
    private RoomData room;
    @NotNull
    private UserData host;
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @Positive
    private long RoomId;
    private List<UserData> guests;
}
