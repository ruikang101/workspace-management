package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.UserEntity;
import tcss556.services.models.requests.update.UpdateUserData;
import tcss556.utils.converters.ResourceConverter;

@Component("updateMeetingConverter")
public class UpdateUserConverter implements ResourceConverter<UpdateUserData, UserEntity> {
    @Override
    public UserEntity convert(UpdateUserData resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}
