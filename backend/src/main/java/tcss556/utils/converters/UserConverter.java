package tcss556.utils.converters;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.UserEntity;
import tcss556.services.models.UserData;

@Component("meetingConverter")
public class UserConverter extends AbstractResourceConverter<UserData, UserEntity> {
    @Override
    public UserEntity toInternalEntity(UserData request) {
        throw new NotImplementedException("No ready. Waiting for entity models");
    }

    @Override
    public UserData toExternalModel(UserEntity internalEntity) {
        throw new NotImplementedException("No ready. Waiting for entity models");
    }
}
