package tcss556.utils.converters.internal;

import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.requests.create.CreateRoomData;
import tcss556.utils.converters.ResourceConverter;

@Component
public class CreateRoomConverter implements ResourceConverter<CreateRoomData, RoomEntity> {
  @Override
  public RoomEntity convert(CreateRoomData resource) {
    RoomEntity.RoomEntityBuilder builder = RoomEntity.builder();
    builder.name(resource.getName());
    builder.capacity(resource.getCapacity());
    builder.b_x_coordinate(resource.getB_x_coordinate());
    builder.b_y_coordinate(resource.getB_y_coordinate());
    builder.t_x_coordinate(resource.getT_x_coordinate());
    builder.t_y_coordinate(resource.getT_y_coordinate());
    builder.floor(resource.getFloor());
    if (resource.getType() != null) {
      builder.type(resource.getType());
    }
    return builder.build();
  }
}
