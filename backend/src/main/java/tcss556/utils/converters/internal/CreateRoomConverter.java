package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.requests.create.CreateRoomData;
import tcss556.utils.converters.ResourceConverter;

@Component
public class CreateRoomConverter implements ResourceConverter<CreateRoomData, RoomEntity> {
    @Override
    public RoomEntity convert(CreateRoomData resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}
