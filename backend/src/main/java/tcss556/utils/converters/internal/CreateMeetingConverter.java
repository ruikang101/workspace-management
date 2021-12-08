package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.MeetingEntity;
import tcss556.services.models.requests.create.CreateMeetingData;
import tcss556.utils.converters.ResourceConverter;

@Component("createMeetingConverter")
public class CreateMeetingConverter implements ResourceConverter<CreateMeetingData, MeetingEntity> {
    @Override
    public MeetingEntity convert(CreateMeetingData resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}
