package by.uladzimirkalesny.tddpracticeit.controller;

import by.uladzimirkalesny.tddpracticeit.entity.User;
import by.uladzimirkalesny.tddpracticeit.exception.UserNotFoundException;
import by.uladzimirkalesny.tddpracticeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Integration tests help to cover scenarios below:
 * 1. URL mapping - listen to http request, response to certain URLs, HTTP methods and Content-Type
 * 2. Deserialize input - pass the incoming http request and create java object from variables in the URL, http request parameters and request body
 * 3. Validate input - controller check is input not valid
 * 4. Call business logic - after parsing the input transforms it into a business model and parse it into a business logic
 * 5. Serialize output - serialize java object into http response
 * 6. Translate exceptions - translate exception into http status and error message
 *
 * @PostMapping("/users/{id}") // 1
 * public User updateUserById(@PathVariable("id") Long userId, // 2
 *                            @Valid // 3
 *                            @RequestBody User user) { // 2
 *     userService.updateUser(userId, user); // 4
 *     return userService.findUserById(userId); // 5
 * }
 *
 * <p>
 * Use for tests Spring annotations @WebMvcTest or @WebFluxTest test slices
 *
 * @WebMvcTest helps auto-configure MockMvc that directly helps test controller without to start a full http Server.
 * @WebMvcTest not covered @Service, @Repository, @Component beans. For these scenarios use @MockBean annotation to mock collaboration.
 * @WebFluxTest helps auto-configure WebTestClient without http server.
 * <p>
 * For testing controller layer we can use @SpringBootTest annotation that load by default a Mock Web environment
 * It can also configure to run on production environment. Using real dependencies (can be mock).
 * Slow running test.
 */

@RequiredArgsConstructor

@RestController // 1
public class UserRestController {

    private final UserService userService;

    @GetMapping("/users/{id}") // 1
    public User findUserById(@PathVariable("id") Long userId) {
        return userService.findUserById(userId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleUserNotFoundException(UserNotFoundException userNotFoundException) {
    }

}
