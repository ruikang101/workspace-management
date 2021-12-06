package tcss556.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"tcss556"})
@RestController
public class RoomSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoomSchedulerApplication.class, args);
    }


}
