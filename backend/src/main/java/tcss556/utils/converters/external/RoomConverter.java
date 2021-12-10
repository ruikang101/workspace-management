package tcss556.utils.converters.external;

import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.responses.RoomData;
import tcss556.utils.converters.ResourceConverter;

@Component("roomConverter")
public class RoomConverter implements ResourceConverter<RoomEntity, RoomData> {

    @Override
    public RoomData convert(RoomEntity resource) {
        RoomData.RoomDataBuilder builder = RoomData.builder();
        builder.id(resource.getId());
        builder.name(resource.getName());
        builder.capacity(resource.getCapacity());
        builder.b_x_coordinate(resource.getB_x_coordinate());
        builder.b_y_coordinate(resource.getB_y_coordinate());
        builder.t_x_coordinate(resource.getT_x_coordinate());
        builder.t_y_coordinate(resource.getT_y_coordinate());
        builder.floor(resource.getFloor());
        builder.type(resource.getType());
        return builder.build();
    }
}
