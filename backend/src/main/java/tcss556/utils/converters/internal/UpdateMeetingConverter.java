package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.MeetingEntity;
import tcss556.utils.converters.ResourceConverter;

@Component("updateMeetingConverter")
public class UpdateMeetingConverter implements ResourceConverter<UpdateMeetingConverter, MeetingEntity> {

    @Override
    public MeetingEntity convert(UpdateMeetingConverter resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}