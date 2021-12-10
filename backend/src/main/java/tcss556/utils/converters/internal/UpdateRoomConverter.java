package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.requests.update.UpdateRoomData;
import tcss556.utils.converters.ResourceConverter;

@Component
public class UpdateRoomConverter implements ResourceConverter<UpdateRoomData, RoomEntity> {

    @Override
    public RoomEntity convert(UpdateRoomData resource) {
        RoomEntity.RoomEntityBuilder builder = RoomEntity.builder();
        if (!StringUtils.isBlank(resource.getName())) {
            builder.name(resource.getName());
        }
        if (resource.getCapacity() != null) {
            builder.capacity(resource.getCapacity());
        }
        if (resource.getType() != null) {
            builder.type(resource.getType());
        }
        return builder.build();
    }
}
