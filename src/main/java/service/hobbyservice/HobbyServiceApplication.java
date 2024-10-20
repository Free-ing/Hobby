package service.hobbyservice;

import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@Import(OpenAiAutoConfiguration.class)
public class HobbyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HobbyServiceApplication.class, args);
    }

}
