package tcss556.dao;

import tcss556.entities.UserEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

/**
 * A group of DB operation on Meeting entity. The user can use this class to implement CRUD on the
 * meeting entities.
 */
public interface UserRepository {
  /**
   * Persist a user entity
   *
   * @param userEntity to persist
   * @return Persisted entity.
   */
  UserEntity createUser(UserEntity userEntity);

  /**
   * Find a user by id.
   *
   * @param userId of the target entity.
   * @return Optional target entity.
   */
  Optional<UserEntity> getUser(long userId);

  /**
   * List all user entities.
   *
   * @return List of users.
   */
  List<UserEntity> listUsers();

  /**
   * Delete a user entity
   *
   * @param userId of target entity
   * @return true if entity is deleted otherwise false.
   */
  boolean deleteUser(long userId);

  /**
   * Update a user entity
   *
   * @param entity to update.
   */
  void updateUser(UserEntity entity);

  /**
   * Find a user entity by username.
   *
   * @param userName of the target entity.
   * @return Optional target entity.
   */
  Optional<UserEntity> getUserByUserName(@NotBlank String userName);
}
