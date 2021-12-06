package tcss556.dao.mock;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tcss556.constants.AppConstants;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;

import java.util.List;

@Component
@Profile(AppConstants.DEV_ENV)
public class MockedUserRepository implements UserRepository {

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public UserEntity getUser(long userId) {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public List<UserEntity> listUsers() {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public boolean deleteUser(long userId) {
        throw new NotImplementedException("not ready!");
    }

    @Override
    public UserEntity updateUser(UserEntity entity) {
        throw new NotImplementedException("not ready!");
    }
}
