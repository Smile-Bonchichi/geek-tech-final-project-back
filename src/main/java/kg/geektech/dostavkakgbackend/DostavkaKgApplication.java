package kg.geektech.dostavkakgbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DostavkaKgApplication {

    public static void main(String[] args) {
        SpringApplication.run(DostavkaKgApplication.class, args);
    }

}
