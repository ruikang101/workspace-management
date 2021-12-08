package tcss556.services.models.requests.create;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class CreateMeetingData {
    @NotNull
    private long host;
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @Positive
    private long RoomId;
    private List<Long> guests;
}
