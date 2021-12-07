package tcss556.utils.converters.external;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.responses.RoomData;
import tcss556.utils.converters.ResourceConverter;

@Component("roomConverter")
public class RoomConverter implements ResourceConverter<RoomEntity, RoomData> {

    @Override
    public RoomData convert(RoomEntity resource) {
        throw new NotImplementedException("Not implemented yet! Waiting for the entity to settle!");
    }
}
