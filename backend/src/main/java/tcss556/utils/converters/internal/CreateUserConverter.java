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
        UserEntity.UserEntityBuilder entityBuilder = UserEntity.builder();
        entityBuilder.username(resource.getUsername());
        entityBuilder.email(resource.getEmail());
        entityBuilder.password(resource.getPassword());
        UpdateUserConverter.setOptionalFields(entityBuilder, resource.getGroup(), resource.getPrivilege(), resource.getFloor(), resource.getLocation_x());
        if (resource.getLocation_y() != null) {
            entityBuilder.location_y(resource.getLocation_y());
        }
        return entityBuilder.build();
    }
}
