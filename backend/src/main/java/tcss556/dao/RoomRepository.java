package tcss556.dao;

import tcss556.entities.RoomEntity;
import tcss556.entities.RoomSortType;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    RoomEntity createRoom(RoomEntity room);

    Optional<RoomEntity> getRoom(long roomId);

    List<RoomEntity> listRooms();

    List<RoomEntity> listRoomByFloor(int floor);

    List<RoomEntity> listRoomsWithSort(RoomSortType sortType);

    List<RoomEntity> listRoomsWithSortWithFloor(int floor, RoomSortType sortType);

    boolean deleteRoom(long roomId);

    RoomEntity updateEntity(RoomEntity entity);
}
