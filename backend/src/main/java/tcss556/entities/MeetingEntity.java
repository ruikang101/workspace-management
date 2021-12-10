package tcss556.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class MeetingEntity {

  private Long id;
  private Long hostId;
  private Date startTime;
  private Date endTime;
  private Long roomId;
  private List<Long> guests;
}
