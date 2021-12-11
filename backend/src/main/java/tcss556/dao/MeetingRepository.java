package tcss556.dao;

import tcss556.entities.MeetingEntity;

import java.util.List;
import java.util.Optional;

/**
 * A group of DB operation on Meeting entity. The user can use this class to implement CRUD on the
 * meeting entities.
 */
public interface MeetingRepository {
  /**
   * Get the meeting by meeting id.
   *
   * @param id meeting id
   * @return Optional meeting entity.
   */
  Optional<MeetingEntity> getMeeting(long id);

  /**
   * List all meetings with optional filters
   *
   * @param userId used to filter against meeting host
   * @param roomId used to filter against meeting room
   * @return List of Meeting entity.
   */
  List<MeetingEntity> listMeetings(Long userId, Long roomId);

  /**
   * Persist meeting entity.
   *
   * @param entity to persist.
   * @return persisted meeting entity.
   */
  MeetingEntity createMeeting(MeetingEntity entity);

  /**
   * Delete a meeting with meeting id;
   *
   * @param id of the meeting
   * @return true if meeting entity is deleted successfully otherwise false.
   */
  boolean deleteMeeting(long id);

  /**
   * Updaet meeting entity.
   *
   * @param entity to update.
   */
  void updateMeeting(MeetingEntity entity);
}
