package tcss556.dao;

import tcss556.entities.MeetingEntity;

import java.util.List;


public interface MeetingRepository {
    MeetingEntity getMeeting(long id);

    List<MeetingEntity> listMeetings();

    List<MeetingEntity> listMeetingsByUserId();

    MeetingEntity createMeeting();

import java.util.Optional;

public interface MeetingRepository {
    Optional<MeetingEntity> getMeeting(long id);

    List<MeetingEntity> listMeetings(Long userId, Long roomId);

    MeetingEntity createMeeting(MeetingEntity entity);

    boolean deleteMeeting(long id);

    MeetingEntity updateMeeting(MeetingEntity entity);

}
