package tcss556.dao.impl;

import com.google.common.collect.Streams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tcss556.constants.AppConstants;
import tcss556.dao.MeetingRepository;
import tcss556.entities.MeetingEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The MeetingDBServices class implements the CRUD related operations for on {@link MeetingEntity}
 */
@Slf4j
@Service
@Profile(AppConstants.PROD_ENV)
public class MeetingDBServices implements MeetingRepository {

  @Resource private RealMeetingRepository repository;

  @Override
  public Optional<MeetingEntity> getMeeting(long id) {
    return repository.findById(id);
  }

  @Override
  public List<MeetingEntity> listMeetings(Long userId, Long roomId) {
    return Streams.stream(repository.findAll())
        .filter(entity -> userId == null || Objects.equals(userId, entity.getHostId()))
        .filter(entity -> roomId == null || Objects.equals(roomId, entity.getRoomId()))
        .collect(Collectors.toList());
  }

  @Override
  public MeetingEntity createMeeting(MeetingEntity entity) {
    return repository.save(entity);
  }

  @Override
  public boolean deleteMeeting(long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      log.error("failed to delete meeting {}", id);
      return false;
    }
  }

  @Override
  public void updateMeeting(MeetingEntity entity) {
    repository.save(entity);
  }
}
