package tcss556.services.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tcss556.services.models.wsdl.LoginRequest;
import tcss556.services.models.wsdl.LoginResponse;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Endpoint
public class UserLoginEndpoint {
    private static final String NAMESPACE_URI = "http://tcss556/services/models/wsdl";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginRequest")
    @ResponsePayload
    public LoginResponse tryLogin(@RequestPayload LoginRequest request){
        return LoginResponse.SUCCEED;
    }
}
