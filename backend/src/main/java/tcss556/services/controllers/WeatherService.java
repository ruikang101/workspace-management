package tcss556.services.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcss556.constants.AppConstants;
import tcss556.services.exceptions.InternalException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weathers")
public class WeatherService {

    @Resource
    private ObjectMapper mapper;
    @Resource
    private InetAddressValidator validator;

    @GetMapping(value = "/", produces = "application/json")
    public String getWeather(HttpServletRequest request){
        String ipAddr = request.getRemoteAddr();
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            log.info("Start looking for geo location of ip {}", ipAddr);
            if ( !validator.isValidInet4Address(ipAddr) || AppConstants.LOCAL_HOST_IP.equals(ipAddr)) {
                log.info("server is running locally. Using public ip instead!");
                HttpGet publicIpLookUpReq = new HttpGet("https://api.ipify.org/?format=json");
                ipAddr = client.execute(publicIpLookUpReq, httpResponse -> {
                    JsonNode node = mapper.readTree(EntityUtils.toString(httpResponse.getEntity()));
                    return node.findValue("ip").asText();
                });
                log.info("find public ip {}", ipAddr);
            }
            HttpGet ipLookUpReq = new HttpGet(String.format("http://api.ipstack.com/%s?access_key=ba80a32de4d69cc7307296984e7a8c51", ipAddr));
            String content = client.execute(ipLookUpReq, httpResponse ->
                EntityUtils.toString(httpResponse.getEntity())
            );
            JsonNode node = mapper.readTree(content);
            Double latitude = node.findValue("latitude").asDouble();
            Double longitude = node.findValue("longitude").asDouble();
            log.info("get the latitude:{} and longitude: {} for ip {}", latitude, longitude,ipAddr);
            String response = Unirest.get("https://weatherapi-com.p.rapidapi.com/current.json?q="+ latitude +"%2C" + longitude)
                    .header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                    .header("x-rapidapi-key", "e27c712db8mshf42864079ea19c1p181995jsn5090240ba462")
                    .asString().getBody();
            log.info("weather info {}", response);
            return response;
        } catch (IOException | UnirestException e) {
           log.error("failed to load weather info", e);
           throw new InternalException();
        }
    }
}
