package tcss556.dao;

import tcss556.entities.MeetingEntity;

import java.util.List;

public interface MeetingRepository {
    MeetingEntity getMeeting(long id);

    List<MeetingEntity> listMeetings();

    List<MeetingEntity> listMeetingsByUserId();

    MeetingEntity createMeeting();
}
