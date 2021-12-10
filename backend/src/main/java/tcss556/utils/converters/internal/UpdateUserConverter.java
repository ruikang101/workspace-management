package tcss556.utils.converters.internal;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tcss556.entities.UserEntity;
import tcss556.services.models.UserGroup;
import tcss556.services.models.requests.update.UpdateUserData;
import tcss556.utils.converters.ResourceConverter;

@Component
public class UpdateUserConverter implements ResourceConverter<UpdateUserData, UserEntity> {
    @Override
    public UserEntity convert(UpdateUserData resource) {
        UserEntity.UserEntityBuilder builder = UserEntity.builder();
        if (!StringUtils.isBlank(resource.getPassword())) {
            builder.password(resource.getPassword());
        }
        setOptionalFields(builder, resource.getGroup(), resource.getPrivilege(), resource.getFloor(), resource.getLocation_x());
        if (resource.getLocation_y() != null) {
            builder.location_y(resource.getLocation_x());
        }
        return builder.build();
    }

    static void setOptionalFields(UserEntity.UserEntityBuilder builder, UserGroup group, Integer privilege, Integer floor, Double location_x) {
        if (group != null) {
            builder.userGroup(group);
        }
        if (privilege != null) {
            builder.privilege(privilege);
        }
        if (floor != null) {
            builder.floor(floor);
        }
        if (location_x != null) {
            builder.location_x(location_x);
        }
    }
}
