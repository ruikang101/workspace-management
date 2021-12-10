package tcss556.services.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tcss556.dao.MeetingRepository;
import tcss556.entities.MeetingEntity;
import tcss556.services.exceptions.InternalException;
import tcss556.services.exceptions.ResourceNotFoundException;
import tcss556.services.models.requests.create.CreateMeetingData;
import tcss556.services.models.requests.update.UpdateMeetingData;
import tcss556.services.models.responses.MeetingData;
import tcss556.utils.converters.ResourceConverter;

import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/meetings")
public class MeetingServices {

    @Resource
    private MeetingRepository repository;
    @Resource
    private ResourceConverter<CreateMeetingData, MeetingEntity> createMeetingConverter;
    @Resource
    private ResourceConverter<MeetingEntity, MeetingData> meetingDataResourceConverter;
    @Resource
    private ResourceConverter<UpdateMeetingData, MeetingEntity> updateMeetingConverter;

    @PostMapping("/")
    @ResponseBody
    public MeetingData createMeeting(@RequestBody CreateMeetingData req) {
        try {
            log.info("Receive create meeting request {}", req);
            MeetingEntity entity = repository.createMeeting(createMeetingConverter.convert(req));
            return meetingDataResourceConverter.convert(entity);
        } catch (Exception e) {
            log.info("failed to create meeting.", e);
            throw new InternalException();
        }
    }

    @GetMapping("/")
    @ResponseBody
    public List<MeetingData> listMeetings(@RequestParam("userId") Optional<Long> userId, @RequestParam("roomId") Optional<Long> roomId) {
        log.info("list room with user id {} and room id {}", userId, roomId);
        return repository.listMeetings(userId.orElse(null), roomId.orElse(null)).stream().map(meetingDataResourceConverter::convert).collect(Collectors.toList());

    }

    @GetMapping("/{meetingId}")
    @ResponseBody
    public MeetingData getMeeting(@PathVariable("meetingId") Long meetingId) {
        return meetingDataResourceConverter.convert(repository.getMeeting(meetingId).orElseThrow(() -> new ResourceNotFoundException("Meeting", meetingId)));
    }

    @DeleteMapping("/{meetingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeeting(@PathVariable Long meetingId){
        if (!repository.deleteMeeting(meetingId)){
            throw new InvalidParameterException("Invalid meeting id");
        }
    }

    @PutMapping("/{meetingId}")
    @ResponseBody
    public MeetingData updateMeeting(@PathVariable Long meetingId, @RequestBody UpdateMeetingData req) {
        MeetingEntity entity = updateMeetingConverter.convert(req);
        entity.setId(meetingId);
        return meetingDataResourceConverter.convert(repository.updateMeeting(entity));
    }
}
