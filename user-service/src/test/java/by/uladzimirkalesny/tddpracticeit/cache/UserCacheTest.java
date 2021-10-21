package by.uladzimirkalesny.tddpracticeit.cache;

import by.uladzimirkalesny.tddpracticeit.entity.User;
import by.uladzimirkalesny.tddpracticeit.repository.UserRepository;
import by.uladzimirkalesny.tddpracticeit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class UserCacheTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findUserById_forMultipleRequests_isRetrievedFromCache() {

        // given
        Long userId = 123L;

        given(userRepository.findById(userId)).willReturn(Optional.of(new User(userId, "Uladzimir", true, 29)));

        // when
        userService.findUserById(userId);
        userService.findUserById(userId);
        userService.findUserById(userId);

        // then
        then(userRepository).should(times(1)).findById(userId);
    }

}
