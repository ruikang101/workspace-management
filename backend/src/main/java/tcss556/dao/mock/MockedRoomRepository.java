package tcss556.dao.mock;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.RoomRepository;
import tcss556.entities.RoomEntity;
import tcss556.entities.RoomSortType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Profile(AppConstants.DEV_ENV)
public class MockedRoomRepository implements RoomRepository {
    private final static AtomicLong idCounter = new AtomicLong();
    private final Map<Long, RoomEntity> roomEntityMap;

    public MockedRoomRepository() {
        roomEntityMap = new HashMap<>();
    }

    @Override
    public RoomEntity createRoom(RoomEntity room) {
        room = room.withId(idCounter.getAndIncrement());
        roomEntityMap.put(room.getId(), room);
        return room;
    }

    @Override
    public Optional<RoomEntity> getRoom(long roomId) {
        return Optional.ofNullable(roomEntityMap.getOrDefault(roomId, null));
    }

    @Override
    public List<RoomEntity> listRooms() {
        return ImmutableList.copyOf(roomEntityMap.values());
    }

    @Override
    public List<RoomEntity> listRoomByFloor(int floor) {
        throw new NotImplementedException("not ready!");

    }

    @Override
    public List<RoomEntity> listRoomsWithSort(RoomSortType sortType) {
        throw new NotImplementedException("not ready!");

    }

    @Override
    public List<RoomEntity> listRoomsWithSortWithFloor(int floor, RoomSortType sortType) {
        throw new NotImplementedException("not ready!");

    }

    @Override
    public boolean deleteRoom(long roomId) {
        throw new NotImplementedException("not ready!");

    }

    @Override
    public RoomEntity updateEntity(RoomEntity entity) {
        throw new NotImplementedException("not ready!");

    }
}
