package by.uladzimirkalesny.tddpracticeit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching

@SpringBootApplication
public class TddPracticeItApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddPracticeItApplication.class, args);
    }

}
