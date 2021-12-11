package tcss556.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** Spring boot application class. This is the entry point of the whole application. */
@SpringBootApplication(scanBasePackages = "tcss556")
@EnableJpaRepositories(basePackages = {"tcss556.dao", "tcss556.entities"})
@EntityScan(basePackages = "tcss556.entities")
public class RoomSchedulerApplication {
  public static void main(String[] args) {
    SpringApplication.run(RoomSchedulerApplication.class, args);
  }
}
