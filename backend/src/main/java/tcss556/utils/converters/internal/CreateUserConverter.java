package tcss556.utils.converters.internal;

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
    UserEntity entity = entityBuilder.build();
    UpdateUserConverter.setOptionalFields(
        entity,
        resource.getGroup(),
        resource.getPrivilege(),
        resource.getFloor(),
        resource.getLocation_x(),
        resource.getLocation_y());
    return entity;
  }
}
