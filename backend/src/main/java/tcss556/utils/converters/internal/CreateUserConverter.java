package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.UserEntity;
import tcss556.services.models.requests.create.CreateUserData;
import tcss556.utils.converters.ResourceConverter;

@Component
public class CreateUserConverter implements ResourceConverter<CreateUserData, UserEntity> {
    @Override
    public UserEntity convert(CreateUserData resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");

    }
}
