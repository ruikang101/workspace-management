package tcss556.services.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.models.wsdl.LoginRequest;
import tcss556.services.models.wsdl.LoginResponse;
import tcss556.services.models.wsdl.LoginStatus;
import tcss556.utils.auth.JWTUtil;

import javax.annotation.Resource;
import java.util.Optional;

@Endpoint
public class UserLoginEndpoint {
  private static final String NAMESPACE_URI = "http://tcss556/services/models/wsdl";

  private final static Long DAY_IN_MILLI = 24 * 60 * 60 * 1000L;

  @Resource private ObjectMapper mapper;

  @Resource private UserRepository repository;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginRequest")
  @ResponsePayload
  public LoginResponse tryLogin(@RequestPayload LoginRequest request)
      throws JsonProcessingException {
    LoginResponse response = new LoginResponse();
    Optional<UserEntity> optionalUserEntity = repository.getUserByUserName(request.getName());
    if (!optionalUserEntity.isPresent()) {
      response.setStatus(LoginStatus.NOT_FOUND);
    } else {
      UserEntity entity = optionalUserEntity.get();
      if (entity.getPassword().equals(request.getPassword())) {
        response.setStatus(LoginStatus.SUCCEED);
        response.setToken(JWTUtil.createJWT(mapper.writeValueAsString(request), DAY_IN_MILLI));

      } else {
        response.setStatus(LoginStatus.BAD_PASSWORD);
      }
    }
    return response;
  }
}
