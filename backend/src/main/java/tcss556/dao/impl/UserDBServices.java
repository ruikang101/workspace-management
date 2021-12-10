package tcss556.dao.impl;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tcss556.constants.AppConstants;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.exceptions.InvalidInputException;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Profile(AppConstants.PROD_ENV)
@Service
public class UserDBServices implements UserRepository {
  @Resource(name = "sqliteUser")
  private RealUserRepository repository;

  @Override
  public UserEntity createUser(UserEntity userEntity) {
    return repository.save(userEntity);
  }

  @Override
  public Optional<UserEntity> getUser(long userId) {
    return repository.findById(userId);
  }

  @Override
  public List<UserEntity> listUsers() {
    return ImmutableList.copyOf(repository.findAll());
  }

  @Override
  public boolean deleteUser(long userId) {
    try {
      repository.deleteById(userId);
      return true;
    }catch (EmptyResultDataAccessException e){
      log.error("failed to delete user {}", userId, e);
      throw new InvalidInputException("user:" + userId);
    }
  }

  @Override
  public void updateUser(UserEntity entity) {
    repository.save(entity);
  }


  @Override
  public Optional<UserEntity> getUserByUserName(String userName) {
    List<UserEntity> userEntities = repository.findByName(userName);
    if (userEntities.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(userEntities.get(0));
    }
  }
}
