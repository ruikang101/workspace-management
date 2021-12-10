package tcss556.utils.converters.external;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.MeetingEntity;
import tcss556.services.models.responses.MeetingData;
import tcss556.utils.converters.ResourceConverter;

import javax.annotation.Resource;
import java.text.DateFormat;

@Component("meetingConverter")
public class MeetingConverter implements ResourceConverter<MeetingEntity, MeetingData> {
    @Resource
    private DateFormat dateFormat;
    @Override
    public MeetingData convert(MeetingEntity resource) {
        MeetingData.MeetingDataBuilder builder = MeetingData.builder();
        builder.meetingId(resource.getId());
        builder.startTime(dateFormat.format(resource.getStartTime()));
        builder.endTime(dateFormat.format(resource.getEndTime()));
        return builder.build();
    }
}
