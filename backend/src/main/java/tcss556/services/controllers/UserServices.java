package tcss556.services.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import tcss556.dao.MeetingRepository;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.exceptions.ResourceNotFoundException;
import tcss556.services.models.requests.create.CreateUserData;
import tcss556.services.models.responses.UserData;
import tcss556.utils.converters.external.UserConverter;
import tcss556.utils.converters.internal.CreateUserConverter;

import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/users")
public class UserServices {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServices.class);

    @Resource
    private UserRepository repository;
    @Resource
    private MeetingRepository meetingRepository;
    @Resource(name = "createUserConverter")
    private CreateUserConverter createUserConverter;
    @Resource(name = "userConverter")
    private UserConverter userConverter;

    @PostMapping(value = "/", consumes = "application/json")
    @ResponseBody
    public UserData createUser(@RequestBody CreateUserData request) {
        try {
            UserEntity entity = repository.createUser(createUserConverter.convert(request));
            LOGGER.info("Successfully created user {}", entity);
            return userConverter.convert(entity);
        } catch (Exception e){
            LOGGER.error("Failed to create user {}", request);
            throw new InvalidParameterException("Failed to create user.");
        }
    }

    @GetMapping("/")
    @ResponseBody
    public List<UserData> listUsers(){
        return repository.listUsers().stream().map(userConverter::convert).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public UserData getUser(@PathVariable("userId") long userId){
        Optional<UserEntity> optionalUserEntity = repository.getUser(userId);
        return userConverter.convert(optionalUserEntity.orElseThrow(() -> new ResourceNotFoundException("user " + userId)));
    }

}
