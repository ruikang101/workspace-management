package tcss556.dao;

import tcss556.entities.UserEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
  UserEntity createUser(UserEntity userEntity);

  Optional<UserEntity> getUser(long userId);

  List<UserEntity> listUsers();

  boolean deleteUser(long userId);

  void updateUser(UserEntity entity);

  Optional<UserEntity> getUserByUserName(@NotBlank String userName);
}
