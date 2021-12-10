package tcss556.services.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tcss556.dao.MeetingRepository;
import tcss556.dao.UserRepository;
import tcss556.entities.MeetingEntity;
import tcss556.services.exceptions.InvalidInputException;
import tcss556.services.exceptions.ResourceNotFoundException;
import tcss556.services.models.requests.create.CreateMeetingData;
import tcss556.services.models.requests.update.UpdateMeetingData;
import tcss556.services.models.responses.MeetingData;
import tcss556.utils.converters.ResourceConverter;

import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/meetings")
public class MeetingServices {

  @Resource private MeetingRepository repository;
  @Resource private UserRepository userRepository;
  @Resource private ResourceConverter<CreateMeetingData, MeetingEntity> createMeetingConverter;
  @Resource private ResourceConverter<MeetingEntity, MeetingData> meetingDataResourceConverter;
  @Resource private ResourceConverter<UpdateMeetingData, MeetingEntity> updateMeetingConverter;
  @Resource private RoomServices roomServices;
  @Resource private UserServices userServices;

  @PostMapping("/")
  @ResponseBody
  public MeetingData createMeeting(@RequestBody CreateMeetingData req) {
    log.info("Receive create meeting request {}", req);
    MeetingEntity internalEntity = createMeetingConverter.convert(req);
    validateMeeting(internalEntity);
    internalEntity = repository.createMeeting(internalEntity);
    return meetingDataResourceConverter.convert(internalEntity);
  }

  @GetMapping("/")
  @ResponseBody
  public List<MeetingData> listMeetings(
      @RequestParam("userId") Optional<Long> userId,
      @RequestParam("roomId") Optional<Long> roomId) {
    log.info("list room with user id {} and room id {}", userId, roomId);
    return repository.listMeetings(userId.orElse(null), roomId.orElse(null)).parallelStream()
        .map(this::loadMeetingData)
        .collect(Collectors.toList());
  }

  @GetMapping("/{meetingId}")
  @ResponseBody
  public MeetingData getMeeting(@PathVariable("meetingId") Long meetingId) {
    return loadMeetingData(
        repository
            .getMeeting(meetingId)
            .orElseThrow(() -> new ResourceNotFoundException("Meeting", meetingId)));
  }

  @DeleteMapping("/{meetingId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMeeting(@PathVariable Long meetingId) {
    if (!repository.deleteMeeting(meetingId)) {
      throw new InvalidParameterException("Invalid meeting id");
    }
  }

  @PutMapping("/{meetingId}")
  @ResponseBody
  public MeetingData updateMeeting(
      @PathVariable Long meetingId, @RequestBody UpdateMeetingData req) {
    MeetingEntity entity =
        repository
            .getMeeting(meetingId)
            .orElseThrow(() -> new InvalidInputException("meeting id:" + meetingId));
    updateMeetingConverter.convertUpdate(entity, req);
    validateMeeting(entity);
    return loadMeetingData(
        repository
            .getMeeting(meetingId)
            .orElseThrow(() -> new ResourceNotFoundException("Meeting", meetingId)));
  }

  /**
   * Valid if the meeting request is valid or not.
   *
   * @param meetingEntity Meeting entity to persist.
   * @throws InvalidInputException when invalid data exists.
   */
  private void validateMeeting(MeetingEntity meetingEntity) {
    if (!userRepository.getUser(meetingEntity.getHostId()).isPresent()) {
      throw new InvalidInputException("host id: " + meetingEntity.getHostId());
    }
    List<Long> missedGuestIds =
        meetingEntity.getGuests().parallelStream()
            .filter(id -> !userRepository.getUser(id).isPresent())
            .collect(Collectors.toList());
    if (!missedGuestIds.isEmpty()) {
      throw new InvalidInputException(
          "guest id: "
              + missedGuestIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
    List<MeetingEntity> hostMeetings =
        repository.listMeetings(meetingEntity.getHostId(), null);
    List<MeetingEntity> roomMeetings = repository.listMeetings(null , meetingEntity.getRoomId());
    if (hostMeetings.stream()
        .anyMatch(
            entity ->
                !Objects.equals(entity.getId(), meetingEntity.getId())
                    && !(entity.getEndTime().before(meetingEntity.getStartTime())
                        || entity.getStartTime().after(meetingEntity.getEndTime())))) {
      throw new InvalidInputException("Host is busy");
    }
    if (roomMeetings.stream()
            .anyMatch(
                    entity ->
                            !Objects.equals(entity.getId(), meetingEntity.getId())
                                    && !(entity.getEndTime().before(meetingEntity.getStartTime())
                                    || entity.getStartTime().after(meetingEntity.getEndTime())))) {
      throw new InvalidInputException("Room is in use.");
    }
  }

  private MeetingData loadMeetingData(MeetingEntity entity) {
    MeetingData data = meetingDataResourceConverter.convert(entity);
    data.setRoom(roomServices.getRoom(entity.getRoomId()));
    data.setHost(userServices.getUser(entity.getHostId()));
    data.setGuests(
        entity.getGuests().stream()
            .map(id -> userServices.getUser(id))
            .collect(Collectors.toList()));
    return data;
  }
}
