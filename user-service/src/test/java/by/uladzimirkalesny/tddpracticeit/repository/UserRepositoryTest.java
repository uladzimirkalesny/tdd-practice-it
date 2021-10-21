package by.uladzimirkalesny.tddpracticeit.repository;

import by.uladzimirkalesny.tddpracticeit.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetUserByName_shouldReturnUserDetails() {
        // given
        User savedUser = testEntityManager.persistAndFlush(new User(null, "Uladzimir", true, 29));

        // when
        User user = userRepository.getUserByName("Uladzimir");

        // then
        then(user.getId()).isNotNull();
        then(user.getName()).isEqualTo(savedUser.getName());
    }

    @Test
    void testAverageAgeForActiveUsers_shouldReturnAverageDetails() {
        // given
        User savedUser1 = testEntityManager.persistAndFlush(new User(null, "Uladzimir", true, 29));
        User savedUser2 = testEntityManager.persistAndFlush(new User(null, "John", true, 19));
        User savedUser3 = testEntityManager.persistAndFlush(new User(null, "Jack", false, 12));

        Arrays
                .asList(savedUser1, savedUser2, savedUser3)
                .forEach(testEntityManager::persistAndFlush);

        // when
        Double avg = userRepository.getAvgAgeForActiveUsers();

        // then
        then(avg).isNotNull();
        then(avg).isEqualTo(24.0);
    }
}
