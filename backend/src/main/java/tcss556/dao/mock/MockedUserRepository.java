package tcss556.dao.mock;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.exceptions.InvalidInputException;
import tcss556.utils.MockedRepositoryUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Component
@Profile(AppConstants.DEV_ENV)
public class MockedUserRepository implements UserRepository {
  private static AtomicLong idGenerator = new AtomicLong();
  private Map<Long, UserEntity> userEntityMap;
  private Map<String, UserEntity> userEntityNameMap;

  @PostConstruct
  public void setup() {
    List<UserEntity> users =
        MockedRepositoryUtils.loadJsonData(AppConstants.MOCKED_USER_FILE, UserEntity.class);
    userEntityMap = users.stream().collect(Collectors.toMap(UserEntity::getId, v -> v));
    userEntityNameMap = users.stream().collect(Collectors.toMap(UserEntity::getUsername, v -> v));
    idGenerator =
        new AtomicLong(userEntityMap.keySet().stream().max(Long::compare).orElse(-1L) + 1);
  }

  @Override
  public UserEntity createUser(UserEntity userEntity) {
    if (userEntityNameMap.containsKey(userEntity.getUsername())) {
      throw new InvalidInputException(
          String.format(" username: %s is occupied.", userEntity.getUsername()));
    }
    long id = idGenerator.getAndIncrement();
    userEntity.setId(id);
    userEntityMap.put(id, userEntity);
    userEntityNameMap.put(userEntity.getUsername(), userEntity);
    return userEntity;
  }

  @Override
  public Optional<UserEntity> getUser(long userId) {
    return Optional.ofNullable(userEntityMap.getOrDefault(userId, null));
  }

  @Override
  public List<UserEntity> listUsers() {
    return ImmutableList.copyOf(userEntityMap.values());
  }

  @Override
  public boolean deleteUser(long userId) {
    if (!userEntityMap.containsKey(userId)) {
      return false;
    }
    UserEntity entity = userEntityMap.remove(userId);
    userEntityNameMap.remove(entity.getUsername());
    return true;
  }

  @Override
  public void updateUser(UserEntity entity) {
    userEntityMap.put(entity.getId(), entity);
  }

  @Override
  public Optional<UserEntity> getUserByUserName(String userName) {
    return Optional.ofNullable(userEntityNameMap.getOrDefault(userName, null));
  }
}
