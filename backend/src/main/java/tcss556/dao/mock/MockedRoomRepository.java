package tcss556.dao.mock;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.RoomRepository;
import tcss556.entities.RoomEntity;
import tcss556.utils.MockedRepositoryUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Component
@Profile(AppConstants.DEV_ENV)
public class MockedRoomRepository implements RoomRepository {
  private static AtomicLong idCounter = new AtomicLong();
  private final Map<Long, RoomEntity> roomEntityMap;

  public MockedRoomRepository() {
    List<RoomEntity> entities =
        MockedRepositoryUtils.loadJsonData(AppConstants.MOCKED_ROOMS_FILE, RoomEntity.class);
    roomEntityMap = entities.stream().collect(Collectors.toMap(RoomEntity::getId, v -> v));
    idCounter = new AtomicLong(entities.stream().mapToLong(RoomEntity::getId).max().orElse(-1) + 1);
  }

  @Override
  public RoomEntity createRoom(RoomEntity room) {
    room = room.withId(idCounter.getAndIncrement());
    roomEntityMap.put(room.getId(), room);
    return room;
  }

  @Override
  public Optional<RoomEntity> getRoom(Long roomId) {
    return Optional.ofNullable(roomEntityMap.getOrDefault(roomId, null));
  }

  @Override
  public List<RoomEntity> listRooms() {
    return ImmutableList.copyOf(roomEntityMap.values());
  }

  @Override
  public List<RoomEntity> listRoomByFloor(Integer floor) {
    return roomEntityMap.values().stream()
        .filter(entity -> entity.getFloor() != null && Objects.equals(entity.getFloor(), floor))
        .collect(Collectors.toList());
  }

  @Override
  public boolean deleteRoom(Long roomId) {
    if (!roomEntityMap.containsKey(roomId)) {
      log.error("unable to find room: {}", roomId);
      return false;
    }
    roomEntityMap.remove(roomId);
    return true;
  }

  @Override
  public void updateEntity(RoomEntity entity) {
    roomEntityMap.put(entity.getId(), entity);
  }
}
