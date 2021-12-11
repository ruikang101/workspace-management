package tcss556.services.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tcss556.dao.MeetingRepository;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.exceptions.InvalidInputException;
import tcss556.services.exceptions.ResourceNotFoundException;
import tcss556.services.models.requests.create.CreateUserData;
import tcss556.services.models.requests.update.UpdateUserData;
import tcss556.services.models.responses.UserData;
import tcss556.utils.converters.ResourceConverter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Room services class serves all the requests related to user entity. */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserServices {
  @Resource private UserRepository repository;
  @Resource private MeetingRepository meetingRepository;

  @Resource(name = "createUserConverter")
  private ResourceConverter<CreateUserData, UserEntity> createUserConverter;

  @Resource(name = "userConverter")
  private ResourceConverter<UserEntity, UserData> userConverter;

  @Resource private ResourceConverter<UpdateUserData, UserEntity> updateUserConverter;

  /**
   * Create a user
   *
   * @param request Create user request.
   * @return generated entity.
   */
  @PostMapping(value = "/", consumes = "application/json")
  @ResponseBody
  public UserData createUser(@RequestBody CreateUserData request) {
    UserEntity entity = repository.createUser(createUserConverter.convert(request));
    log.info("Successfully created user {}", entity);
    return userConverter.convert(entity);
  }

  /**
   * List users.
   *
   * @return List of user.
   */
  @GetMapping("/")
  @ResponseBody
  public List<UserData> listUsers() {
    return repository.listUsers().stream().map(userConverter::convert).collect(Collectors.toList());
  }

  /**
   * Get a user by id.
   *
   * @param userId of target entity.
   * @return target entity.
   */
  @GetMapping("/{userId}")
  @ResponseBody
  public UserData getUser(@PathVariable("userId") long userId) {
    Optional<UserEntity> optionalUserEntity = repository.getUser(userId);
    return userConverter.convert(
        optionalUserEntity.orElseThrow(() -> new ResourceNotFoundException("user " + userId)));
  }

  /**
   * Update a user entity.
   *
   * @param userId of target entity.
   * @param request Update user request.
   * @return update user entity
   */
  @PutMapping(value = "/{userId}", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public UserData updateUser(
      @PathVariable("userId") long userId, @RequestBody UpdateUserData request) {
    UserEntity entity =
        repository.getUser(userId).orElseThrow(() -> new ResourceNotFoundException("user", userId));
    updateUserConverter.convertUpdate(entity, request);
    repository.updateUser(entity);
    return userConverter.convert(entity);
  }

  /**
   * Delete a user
   *
   * @param userId of target entity.
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{userId}")
  public void deleteUser(@PathVariable("userId") long userId) {
    log.info("received delete request on user {}", userId);
    if (!repository.deleteUser(userId)) {
      throw new InvalidInputException("Invalid userId.");
    }
  }
}
