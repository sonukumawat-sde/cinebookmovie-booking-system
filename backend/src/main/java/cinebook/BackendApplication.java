package cinebook; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan; // <-- Ye Spring Boot 4 ka naya address hai!
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"cinebook", "com.cinebook"})
@EntityScan(basePackages = {"cinebook", "com.cinebook"})
@EnableJpaRepositories(basePackages = {"cinebook", "com.cinebook"})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}