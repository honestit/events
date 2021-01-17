package honestit.projects.eventuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Clock;

@SpringBootApplication
@EnableAsync
public class EventuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventuatorApplication.class, args);
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}

}
