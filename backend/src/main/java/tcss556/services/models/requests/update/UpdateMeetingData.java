package tcss556.services.models.requests.update;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMeetingData {
    private long hostId;
    private List<Long> guests;
    private String startTime;
    private String endTime;
    private long roomId;
}
