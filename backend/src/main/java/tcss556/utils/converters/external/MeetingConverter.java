package tcss556.utils.converters.external;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.MeetingEntity;
import tcss556.services.models.responses.MeetingData;
import tcss556.utils.converters.ResourceConverter;

@Component("meetingConverter")
public class MeetingConverter implements ResourceConverter<MeetingEntity, MeetingData> {
    @Override
    public MeetingData convert(MeetingEntity resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}
