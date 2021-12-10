package tcss556.utils.converters.internal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tcss556.entities.UserEntity;
import tcss556.services.models.UserGroup;
import tcss556.services.models.requests.update.UpdateUserData;
import tcss556.utils.converters.ResourceConverter;

@Component
public class UpdateUserConverter implements ResourceConverter<UpdateUserData, UserEntity> {
  static void setOptionalFields(
      UserEntity entity,
      UserGroup group,
      Integer privilege,
      Integer floor,
      Double location_x,
      Double location_y) {
    if (group != null) {
      entity.setUserGroup(group);
    }
    if (privilege != null) {
      entity.setPrivilege(privilege);
    }
    if (floor != null) {
      entity.setFloor(floor);
    }
    if (location_x != null) {
      entity.setLocation_x(location_x);
    }
    if (location_y != null) {
      entity.setLocation_y(location_y);
    }
  }

  @Override
  public void convertUpdate(UserEntity entity, UpdateUserData resource) {
    if (!StringUtils.isBlank(resource.getPassword())) {
      entity.setPassword(resource.getPassword());
    }
    setOptionalFields(
        entity,
        resource.getGroup(),
        resource.getPrivilege(),
        resource.getFloor(),
        resource.getLocation_x(),
        resource.getLocation_y());
  }
}
