package tcss556.utils.converters.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tcss556.entities.MeetingEntity;
import tcss556.services.exceptions.InvalidInputException;
import tcss556.services.models.requests.create.CreateMeetingData;
import tcss556.utils.converters.ResourceConverter;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;

@Slf4j
@Component
public class CreateMeetingConverter implements ResourceConverter<CreateMeetingData, MeetingEntity> {

  @Resource private DateFormat dateFormat;

  @Override
  public MeetingEntity convert(CreateMeetingData resource) {
    try {
      MeetingEntity.MeetingEntityBuilder builder = MeetingEntity.builder();
      builder.hostId(resource.getHost());
      builder.roomId(resource.getRoomId());
      builder.startTime(dateFormat.parse(resource.getStartTime()));
      builder.endTime(dateFormat.parse(resource.getEndTime()));
      builder.guests(resource.getGuests());
      return builder.build();
    } catch (ParseException e) {
      log.error("failed to parse date.", e);
      throw new InvalidInputException("bad time format");
    }
  }
}
