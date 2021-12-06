package tcss556.dao;

import tcss556.entities.UserEntity;

import java.util.List;

public interface UserRepository {
    UserEntity createUser(UserEntity userEntity);

    UserEntity getUser(long userId);

    List<UserEntity> listUsers();

    boolean deleteUser(long userId);

    UserEntity updateUser(UserEntity entity);
}
