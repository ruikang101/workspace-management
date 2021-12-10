package tcss556.utils.converters.internal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.requests.update.UpdateRoomData;
import tcss556.utils.converters.ResourceConverter;

@Component
public class UpdateRoomConverter implements ResourceConverter<UpdateRoomData, RoomEntity> {

  @Override
  public void convertUpdate(RoomEntity entity, UpdateRoomData resource) {
    if (!StringUtils.isBlank(resource.getName())) {
      entity.setName(resource.getName());
    }
    if (resource.getCapacity() != null) {
      entity.setCapacity(resource.getCapacity());
    }
    if (resource.getType() != null) {
      entity.setType(resource.getType());
    }
  }
}
