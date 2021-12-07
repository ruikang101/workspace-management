package tcss556.utils.converters.external;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.UserEntity;
import tcss556.services.models.responses.UserData;
import tcss556.utils.converters.ResourceConverter;

@Component("userConverter")
public class UserConverter implements ResourceConverter<UserEntity, UserData> {

    @Override
    public UserData convert(UserEntity resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}
