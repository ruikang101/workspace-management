package tcss556.dao.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tcss556.entities.MeetingEntity;
/** JPA DAO interface for meeting entity. */
@Repository
public interface RealMeetingRepository extends CrudRepository<MeetingEntity, Long> {}
