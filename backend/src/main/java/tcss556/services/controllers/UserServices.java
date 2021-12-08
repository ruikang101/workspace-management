package tcss556.services.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tcss556.dao.MeetingRepository;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.exceptions.ResourceNotFoundException;
import tcss556.services.models.requests.create.CreateUserData;
import tcss556.services.models.requests.update.UpdateUserData;
import tcss556.services.models.responses.UserData;
import tcss556.utils.converters.ResourceConverter;
import tcss556.utils.converters.external.UserConverter;
import tcss556.utils.converters.internal.CreateUserConverter;

import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController()
@RequestMapping("/users")
public class UserServices {
    @Resource
    private UserRepository repository;
    @Resource
    private MeetingRepository meetingRepository;
    @Resource(name = "createUserConverter")
    private ResourceConverter<CreateUserData, UserEntity> createUserConverter;
    @Resource(name = "userConverter")
    private ResourceConverter<UserEntity, UserData> userConverter;
    @Resource
    private ResourceConverter<UpdateUserData, UserEntity> updateUserConverter;

    @PostMapping(value = "/", consumes = "application/json")
    @ResponseBody
    public UserData createUser(@RequestBody CreateUserData request) {
        try {
            UserEntity entity = repository.createUser(createUserConverter.convert(request));
            log.info("Successfully created user {}", entity);
            return userConverter.convert(entity);
        } catch (Exception e){
            log.error("Failed to create user {}", request);
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

    @PutMapping(value = "/{userId}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public UserData updateUser(@PathVariable("userId") long userId, @RequestBody UpdateUserData request) {
        UserEntity entity = updateUserConverter.convert(request);
        entity.setId(userId);
        return userConverter.convert(repository.updateUser(entity));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable("userId")long userId) {
        log.info("received delete request on user {}", userId);
        if (repository.deleteUser(userId)) {
            throw new InvalidParameterException("Invalid userId.");
        }
    }

}
