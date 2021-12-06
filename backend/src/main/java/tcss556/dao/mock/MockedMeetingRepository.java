package tcss556.dao.mock;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.MeetingRepository;
import tcss556.entities.MeetingEntity;

import java.util.List;

@Component
@Profile(AppConstants.DEV_ENV)
public class MockedMeetingRepository implements MeetingRepository {
    @Override
    public MeetingEntity getMeeting(long id) {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public List<MeetingEntity> listMeetings() {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public List<MeetingEntity> listMeetingsByUserId() {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public MeetingEntity createMeeting() {
        throw new NotImplementedException("not ready!");
    }
}
