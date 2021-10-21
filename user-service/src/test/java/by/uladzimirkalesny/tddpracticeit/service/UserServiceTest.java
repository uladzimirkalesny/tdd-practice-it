package by.uladzimirkalesny.tddpracticeit.service;

import by.uladzimirkalesny.tddpracticeit.entity.User;
import by.uladzimirkalesny.tddpracticeit.exception.UserNotFoundException;
import by.uladzimirkalesny.tddpracticeit.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @DisplayName("Returning saved user from service layer")
    @Test
    void testFindUserById_forSaveUser_isReturned() {

        // given
        User savedUser = userRepository.save(User.builder().id(null).name("Uladzimir").isActive(true).age(29).build());

        // when
        User user = userService.findUserById(savedUser.getId());

        // then
        then(user.getId()).isEqualTo(savedUser.getId());

    }

    @DisplayName("Throwing UserNotFoundException from service layer when user not exist by specifying id using non-BDD style")
    @Test
    void testFindUserById_forSaveUser_throwUserNotFoundException_nonBddStyle() {

        // given
        Long userId = 213L;

        // first solution
        assertThatExceptionOfType(UserNotFoundException.class) // then
                .isThrownBy(() -> userService.findUserById(userId)) // when
                .withMessage("User not found"); // then

        // second solution
        assertThatThrownBy(() -> userService.findUserById(userId)) // when
                .isInstanceOf(UserNotFoundException.class) // then
                .hasMessageContaining("User not found"); // then

    }

    @DisplayName("Throwing UserNotFoundException from service layer when user not exist by specifying id using BDD style")
    @Test
    void testFindUserById_forSaveUser_throwUserNotFoundException_bddStyle() {

        // given
        Long userId = 213L;

        // when
        Throwable throwable = catchThrowable(() -> userService.findUserById(userId));

        // then
        then(throwable).isInstanceOf(UserNotFoundException.class);

    }
}
