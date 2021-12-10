package tcss556.utils.converters.internal;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tcss556.entities.MeetingEntity;
import tcss556.services.exceptions.InvalidInputException;
import tcss556.services.models.requests.update.UpdateMeetingData;
import tcss556.utils.converters.ResourceConverter;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;

@Slf4j
@Component
public class UpdateMeetingConverter implements ResourceConverter<UpdateMeetingData, MeetingEntity> {
    @Resource
    private DateFormat dateFormat;

    @Override
    public MeetingEntity convert(UpdateMeetingData resource) {
        try {
            MeetingEntity.MeetingEntityBuilder builder = MeetingEntity.builder();
            if (resource.getHostId() != null) {
                builder.hostId(resource.getHostId());
            }
            if (resource.getGuests() != null && !resource.getGuests().isEmpty()) {
                builder.guests(resource.getGuests());
            }
            if (!StringUtils.isBlank(resource.getStartTime())) {
                builder.startTime(dateFormat.parse(resource.getStartTime()));
            }
            if (!StringUtils.isBlank(resource.getEndTime())) {
                builder.endTime(dateFormat.parse(resource.getEndTime()));
            }
            if (resource.getRoomId() != null) {
                builder.roomId(resource.getRoomId());
            }
            return builder.build();
        } catch (ParseException e) {
            log.error("failed to parse date.", e);
            throw new InvalidInputException("invalid date format");
        }

    }
}