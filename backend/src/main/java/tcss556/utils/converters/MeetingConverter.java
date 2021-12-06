package tcss556.utils.converters;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.MeetingEntity;
import tcss556.services.models.MeetingData;

@Component("meetingConverter")
public class MeetingConverter extends AbstractResourceConverter<MeetingData, MeetingEntity> {
    @Override
    public MeetingEntity toInternalEntity(MeetingData request) {
        throw new NotImplementedException("No ready. Waiting for entity models");
    }

    @Override
    public MeetingData toExternalModel(MeetingEntity internalEntity) {
        throw new NotImplementedException("No ready. Waiting for entity models");
    }
}
