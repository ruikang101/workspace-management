package tcss556.utils.converters.external;

import org.springframework.stereotype.Component;
import tcss556.entities.UserEntity;
import tcss556.services.models.responses.UserData;
import tcss556.utils.converters.ResourceConverter;

@Component("userConverter")
public class UserConverter implements ResourceConverter<UserEntity, UserData> {

  @Override
  public UserData convert(UserEntity resource) {
    UserData.UserDataBuilder builder = UserData.builder();
    builder.id(resource.getId());
    builder.username(resource.getUsername());
    builder.email(resource.getEmail());
    builder.group(resource.getUserGroup());
    builder.privilege(resource.getPrivilege());
    builder.floor(resource.getFloor());
    builder.location_x(resource.getLocation_x());
    builder.location_y(resource.getLocation_y());
    builder.department(resource.getDepartment());
    return builder.build();
  }
}
