package tcss556.dao;

import tcss556.entities.RoomEntity;

import java.util.List;
import java.util.Optional;

/**
 * A group of DB operation on room entity. The user can use this class to implement CRUD on the room
 * entities.
 */
public interface RoomRepository {
  /**
   * Persist a room entity
   *
   * @param room entity to persist
   * @return Persisted room entity.
   */
  RoomEntity createRoom(RoomEntity room);

  /**
   * Get a room by roomId.
   *
   * @param roomId to lookup
   * @return room entity
   */
  Optional<RoomEntity> getRoom(Long roomId);

  /**
   * List all room entities.
   *
   * @return List of room entities.
   */
  List<RoomEntity> listRooms();

  /**
   * List rooms on the specific floor
   *
   * @param floor used to filter room entities.
   * @return List of room entities.
   */
  List<RoomEntity> listRoomByFloor(Integer floor);

  /**
   * Delete a room entity
   *
   * @param roomId of target room entity
   * @return true if entity deleted successfully otherwise false.
   */
  boolean deleteRoom(Long roomId);

  /**
   * Update a room entity
   *
   * @param entity to update.
   */
  void updateEntity(RoomEntity entity);
}
