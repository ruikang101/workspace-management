package tcss556.services.models.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Meeting {
    private long meetingId;
    private User host;
    private List<User> guests;
    private String startTime;
    private String endTime;
    private Room room;
}
