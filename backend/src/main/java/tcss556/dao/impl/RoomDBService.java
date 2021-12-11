package tcss556.dao.impl;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tcss556.constants.AppConstants;
import tcss556.dao.RoomRepository;
import tcss556.entities.RoomEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/** The RoomDBService class implements the CRUD related operations for on {@link RoomEntity} */
@Slf4j
@Service
@Profile(AppConstants.PROD_ENV)
public class RoomDBService implements RoomRepository {
  @Resource private RealRoomRepository repository;

  @Override
  public RoomEntity createRoom(RoomEntity room) {
    return repository.save(room);
  }

  @Override
  public Optional<RoomEntity> getRoom(Long roomId) {
    return repository.findById(roomId);
  }

  @Override
  public List<RoomEntity> listRooms() {
    return ImmutableList.copyOf(repository.findAll());
  }

  @Override
  public List<RoomEntity> listRoomByFloor(Integer floor) {
    return ImmutableList.copyOf(
        listRooms().stream()
            .filter(entity -> Objects.equals(entity.getFloor(), floor))
            .collect(Collectors.toList()));
  }

  @Override
  public boolean deleteRoom(Long roomId) {
    try {
      repository.deleteById(roomId);
      return true;
    } catch (EmptyResultDataAccessException e) {
      log.error("failed to delete room {}", roomId);
      return false;
    }
  }

  @Override
  public void updateEntity(RoomEntity entity) {
    repository.save(entity);
  }
}
