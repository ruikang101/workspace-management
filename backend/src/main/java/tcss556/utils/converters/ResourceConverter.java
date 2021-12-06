package tcss556.utils.converters;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;
import tcss556.entities.RoomEntity;
import tcss556.services.models.RoomData;

@Component("roomConverter")
public class ResourceConverter extends AbstractResourceConverter<RoomData, RoomEntity> {

    @Override
    public RoomEntity toInternalEntity(RoomData request) {
        throw new NotImplementedException("No ready. Waiting for entity models");

    }

    @Override
    public RoomData toExternalModel(RoomEntity internalEntity) {
        throw new NotImplementedException("No ready. Waiting for entity models");

    }
}
