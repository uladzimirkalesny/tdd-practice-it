package by.uladzimirkalesny.tddpracticeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TddPracticeClientApplication {

	@Bean
	public WebClient webClient(WebClient.Builder webClientBuilder) {
		return webClientBuilder.baseUrl("http://localhost:8080").build();
	}

	public static void main(String[] args) {
		SpringApplication.run(TddPracticeClientApplication.class, args);
	}

}
