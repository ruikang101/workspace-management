package tcss556.dao;

import tcss556.entities.RoomEntity;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
  RoomEntity createRoom(RoomEntity room) throws Exception;

  Optional<RoomEntity> getRoom(long roomId);

  List<RoomEntity> listRooms();

  List<RoomEntity> listRoomByFloor(int floor);

  boolean deleteRoom(long roomId);

  void updateEntity(RoomEntity entity);
}
