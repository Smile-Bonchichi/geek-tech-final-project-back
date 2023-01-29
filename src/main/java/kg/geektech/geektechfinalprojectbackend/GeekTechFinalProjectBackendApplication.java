package kg.geektech.geektechfinalprojectbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeekTechFinalProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeekTechFinalProjectBackendApplication.class, args);
	}

}
