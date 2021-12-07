package tcss556.services.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tcss556.dao.MeetingRepository;
import tcss556.dao.RoomRepository;
import tcss556.entities.MeetingEntity;
import tcss556.entities.RoomEntity;
import tcss556.services.exceptions.ResourceNotFoundException;
import tcss556.services.models.requests.create.CreateRoomData;
import tcss556.services.models.requests.update.UpdateRoomData;
import tcss556.services.models.responses.MeetingData;
import tcss556.services.models.responses.RoomData;
import tcss556.utils.converters.ResourceConverter;

import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomServices.class);
    @Resource
    private RoomRepository repository;
    @Resource
    private MeetingRepository meetingRepository;
    @Resource(name = "createRoomConverter")
    private ResourceConverter<CreateRoomData, RoomEntity> createRoomConverter;
    @Resource(name = "updateRoomConverter")
    private ResourceConverter<UpdateRoomData, RoomEntity> updateRoomConverter;
    @Resource(name = "meetingConverter")
    private ResourceConverter<MeetingEntity, MeetingData> meetingConverter;
    @Resource(name = "roomConverter")
    private ResourceConverter<RoomEntity, RoomData> roomConverter;

    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public RoomData createRoom(@RequestBody CreateRoomData request) {
        LOGGER.debug("received create room request {}", request);
        try {
            RoomEntity entity = repository.createRoom(createRoomConverter.convert(request));
            LOGGER.info("Successfully created room id: {}", entity.getId());
            return roomConverter.convert(entity);
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @GetMapping(value = "/", produces = {"application/json"})
    @ResponseBody
    public List<RoomData> listRooms(@RequestParam(value = "floor") Optional<Integer> floor) {
        LOGGER.debug("received list room request with floor {}", floor.orElse(-1));
        List<RoomEntity> entities;
        if (floor.isPresent()) {
            entities = repository.listRoomByFloor(floor.get());
        } else {
            entities = repository.listRooms();
        }
        return entities.stream().map(roomConverter::convert).collect(Collectors.toList());
    }

    @GetMapping(value = "/{roomId}", produces = "application/json")
    @ResponseBody
    public RoomData getRoom(@PathVariable("roomId") long roomId) {
        Optional<RoomEntity> optionalRoomEntity = repository.getRoom(roomId);
        if (!optionalRoomEntity.isPresent()) {
            throw new ResourceNotFoundException("room " + roomId);
        }
        return roomConverter.convert(optionalRoomEntity.get());
    }

    @PutMapping(value = "/{roomId}", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public RoomData updateRoom(@PathVariable("roomId") long roomId, @RequestBody UpdateRoomData request) {
        try {
            RoomEntity optionalRoomEntity = repository.updateEntity(roomId, updateRoomConverter.convert(request));
            return roomConverter.convert(optionalRoomEntity);
        } catch (Exception e) {
            LOGGER.error("Failed to update room {}, with request {}", roomId, request);
            throw new InvalidParameterException("Failed to update room " + roomId);
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{roomId}")
    public void deleteRoom(@PathVariable("roomId") long roomId) {
        if (!repository.deleteRoom(roomId)) {
            LOGGER.error("Failed to delete room {}", roomId);
            throw new InvalidParameterException("Unable to delete room " + roomId);
        }
    }


}
