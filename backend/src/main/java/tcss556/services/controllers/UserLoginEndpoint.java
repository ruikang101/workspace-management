package tcss556.services.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tcss556.dao.UserRepository;
import tcss556.entities.UserEntity;
import tcss556.services.exceptions.InternalException;
import tcss556.services.models.wsdl.LoginRequest;
import tcss556.services.models.wsdl.LoginResponse;
import tcss556.services.models.wsdl.LoginStatus;
import tcss556.utils.auth.JWTUtil;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * SOAP endpoint used to server user login functionality. The response contains JWT token used to
 * authorize if username and password are valid.
 */
@Slf4j
@Endpoint
public class UserLoginEndpoint {
  private static final String NAMESPACE_URI = "http://tcss556/services/models/wsdl";

  private static final Long DAY_IN_MILLI = 24 * 60 * 60 * 1000L;

  @Resource private ObjectMapper mapper;

  @Resource private UserRepository repository;

  /**
   * User login
   *
   * @param request includes username and password
   * @return Soap response includes login result and token (optional)
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginRequest")
  @ResponsePayload
  public LoginResponse tryLogin(@RequestPayload LoginRequest request) {
    LoginResponse response = new LoginResponse();
    Optional<UserEntity> optionalUserEntity = repository.getUserByUserName(request.getName());
    if (!optionalUserEntity.isPresent()) {
      response.setStatus(LoginStatus.NOT_FOUND);
    } else {
      UserEntity entity = optionalUserEntity.get();
      if (entity.getPassword().equals(request.getPassword())) {
        response.setStatus(LoginStatus.SUCCEED);
        try {
          response.setToken(JWTUtil.createJWT(mapper.writeValueAsString(entity), DAY_IN_MILLI));
        } catch (JsonProcessingException e) {
          log.error("failed to serialize user entity body", e);
          throw new InternalException();
        }
      } else {
        response.setStatus(LoginStatus.BAD_PASSWORD);
      }
    }
    return response;
  }
}
