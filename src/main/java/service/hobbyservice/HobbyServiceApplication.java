package service.hobbyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class HobbyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HobbyServiceApplication.class, args);
    }

}
