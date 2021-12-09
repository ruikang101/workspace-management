package tcss556.services.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tcss556.services.models.wsdl.LoginRequest;
import tcss556.services.models.wsdl.LoginResponse;
import tcss556.services.models.wsdl.LoginStatus;
import tcss556.utils.auth.JWTUtil;

import javax.annotation.Resource;

@Endpoint
public class UserLoginEndpoint {
    private static final String NAMESPACE_URI = "http://tcss556/services/models/wsdl";

    @Resource
    private ObjectMapper mapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginRequest")
    @ResponsePayload
    public LoginResponse tryLogin(@RequestPayload LoginRequest request) throws JsonProcessingException {
        LoginResponse response = new LoginResponse();
        response.setStatus(LoginStatus.SUCCEED);
        response.setToken(JWTUtil.createJWT(mapper.writeValueAsString(request), 36000L));
        return response;
    }
}
