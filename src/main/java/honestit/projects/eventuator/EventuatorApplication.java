package honestit.projects.eventuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventuatorApplication.class, args);
	}

}
