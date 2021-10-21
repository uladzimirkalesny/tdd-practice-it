package by.uladzimirkalesny.tddpracticeclient.client;

import by.uladzimirkalesny.tddpracticeclient.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public record UserClient(WebClient webClient) {

    public User findUserById(Long userId) {
        return webClient
                .get()
                .uri("/users/{id}", userId)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

}
