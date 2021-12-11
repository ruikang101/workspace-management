package tcss556.dao.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tcss556.entities.UserEntity;

import java.util.List;

/** JPA DAO interface for user entity. */
@Repository("sqliteUser")
public interface RealUserRepository extends CrudRepository<UserEntity, Long> {

  /**
   * Find user by name
   *
   * @param name of target entity
   * @return List of user entity matches requirement.
   */
  @Query("select u from UserEntity u where u.username = ?1")
  List<UserEntity> findByName(String name);
}
