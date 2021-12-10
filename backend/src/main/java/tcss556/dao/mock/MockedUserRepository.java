package tcss556.dao.mock;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.exceptions.InvalidInputException;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Component
@Profile(AppConstants.DEV_ENV)
public class MockedUserRepository implements UserRepository {
    private static AtomicLong idGenerator = new AtomicLong();
    private Map<Long, UserEntity> userEntityMap;
    private Set<String> userNames;

    @PostConstruct
    public void setup() {
        userNames = new HashSet<>();
        userEntityMap = new HashMap<>();

        URL url = getClass().getClassLoader().getResource(".");
        if (url == null) {
            log.error("failed to find classpath ");
        } else {
            File file = new File(url.getFile(), AppConstants.MOCKED_USER_FILE);
            if (file.exists()) {
                try {
                    Type type = new TypeToken<ArrayList<UserEntity>>() {
                    }.getType();
                    List<UserEntity> users = new Gson().fromJson(FileUtils.readFileToString(file, "UTF-8"), type);
                    userEntityMap = users.stream().collect(Collectors.toMap(UserEntity::getId, v -> v));
                    userNames = users.stream().map(UserEntity::getUsername).collect(Collectors.toSet());
                    idGenerator = new AtomicLong(userEntityMap.keySet().stream().max(Long::compare).orElse(0L) + 1);
                } catch (IOException e) {
                    log.error("failed to load {}", AppConstants.MOCKED_USER_FILE, e);
                }
            }
        }
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        if (userNames.contains(userEntity.getUsername())) {
            throw new InvalidInputException(String.format(" username: %s is occupied.", userEntity.getUsername()));
        }
        long id = idGenerator.getAndIncrement();
        userEntity.setId(id);
        userEntityMap.put(id, userEntity);
        userNames.add(userEntity.getUsername());
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
        userNames.remove(entity.getUsername());
        return true;
    }

    @Override
    public UserEntity updateUser(UserEntity entity) {
        if (!userEntityMap.containsKey(entity.getId())) {
            throw new InvalidInputException("Invalid userId :" + entity.getId());
        }
        UserEntity oldEntity = userEntityMap.get(entity.getId());
        if (entity.getPassword() != null) {
            oldEntity.setPassword(entity.getPassword());
        }
        oldEntity.setUserGroup(entity.getUserGroup());
        if(entity.getPrivilege() != null){
            oldEntity.setPrivilege(entity.getPrivilege());
        }
        if (entity.getFloor() != null) {
            oldEntity.setFloor(entity.getFloor());
        }
        if (entity.getLocation_x() != null) {
            oldEntity.setLocation_x(entity.getLocation_x());
        }
        if (entity.getLocation_y() != null) {
            oldEntity.setLocation_y(entity.getLocation_y());
        }

        return oldEntity;
    }
}
