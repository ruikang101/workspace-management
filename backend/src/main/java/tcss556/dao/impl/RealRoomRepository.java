package tcss556.dao.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tcss556.entities.RoomEntity;

@Repository
public interface RealRoomRepository extends CrudRepository<RoomEntity, Long> {
}
