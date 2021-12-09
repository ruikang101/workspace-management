package tcss556.dao.mock;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.MeetingRepository;
import tcss556.entities.MeetingEntity;

import java.util.List;
import java.util.Optional;

@Component
@Profile(AppConstants.DEV_ENV)
public class MockedMeetingRepository implements MeetingRepository {
    @Override
    public Optional<MeetingEntity> getMeeting(long id) {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public List<MeetingEntity> listMeetings(Long userId, Long roomId) {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public MeetingEntity createMeeting(MeetingEntity entity) {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public boolean deleteMeeting(long id) {
        return false;
    }

    @Override
    public MeetingEntity updateMeeting(MeetingEntity entity) {
        return null;
    }
}
