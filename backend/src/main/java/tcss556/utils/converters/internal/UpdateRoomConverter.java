package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.requests.update.UpdateRoomData;
import tcss556.utils.converters.ResourceConverter;

@Component("updateRoomConverter")
public class UpdateRoomConverter implements ResourceConverter<UpdateRoomData, RoomEntity> {

    @Override
    public RoomEntity convert(UpdateRoomData resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}
