package tcss556.application;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"tcss556"})
@RestController

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tcss556.services.controllers.RoomServices;
import tcss556.services.controllers.UserServices;
import tcss556.services.controllers.WeatherService;

@SpringBootApplication(scanBasePackageClasses = {RoomServices.class, UserServices.class, WeatherService.class} )
@ComponentScan(basePackages = "tcss556")

public class RoomSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoomSchedulerApplication.class, args);
    }


}
