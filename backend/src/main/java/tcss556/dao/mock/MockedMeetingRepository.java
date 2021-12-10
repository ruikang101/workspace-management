package tcss556.dao.mock;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.MeetingRepository;
import tcss556.entities.MeetingEntity;
import tcss556.utils.MockedRepositoryUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@Profile(AppConstants.DEV_ENV)
public class MockedMeetingRepository implements MeetingRepository {
  private static AtomicLong idGenerator = new AtomicLong();
  private Map<Long, MeetingEntity> meetingEntityMap;

  @PostConstruct
  public void setup() {
    List<MeetingEntity> entities =
        MockedRepositoryUtils.loadJsonData(AppConstants.MOCKED_MEETING_FILE, MeetingEntity.class);
    meetingEntityMap = entities.stream().collect(Collectors.toMap(MeetingEntity::getId, v -> v));
    idGenerator =
        new AtomicLong(entities.stream().mapToLong(MeetingEntity::getId).max().orElse(-1) + 1);
  }

  @Override
  public Optional<MeetingEntity> getMeeting(long id) {
    return Optional.ofNullable(meetingEntityMap.getOrDefault(id, null));
  }

  @Override
  public List<MeetingEntity> listMeetings(Long userId, Long roomId) {
    return meetingEntityMap.values().stream()
        .filter(entity -> userId == null || entity.getHostId().equals(userId))
        .filter(entity -> roomId == null || roomId.equals(entity.getRoomId()))
        .collect(Collectors.toList());
  }

  @Override
  public MeetingEntity createMeeting(MeetingEntity entity) {
    Long id = idGenerator.getAndIncrement();
    entity.setId(id);
    meetingEntityMap.put(id, entity);
    return entity;
  }

  @Override
  public boolean deleteMeeting(long id) {
    if (meetingEntityMap.containsKey(id)) {
      meetingEntityMap.remove(id);
      return true;
    }
    return false;
  }

  @Override
  public MeetingEntity updateMeeting(MeetingEntity entity) {
    meetingEntityMap.put(entity.getId(), entity);
    return entity;
  }
}
