package by.uladzimirkalesny.tddpracticeclient;

import by.uladzimirkalesny.tddpracticeclient.client.UserClient;
import by.uladzimirkalesny.tddpracticeclient.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest
//@AutoConfigureWireMock
@AutoConfigureStubRunner(ids = "by.uladzimirkalesny:user-service:+:8080", stubsMode = LOCAL)
public class UserClientTest {

    @Autowired
    private UserClient userClient;

    @Test
    void findUser_forGivenUser_isReturned() {

        // given
        Long userId = 1L;

//        stubFor(get("/users/" + userId).willReturn(okJson(
//                """
//                        {
//                            "id": 1,
//                            "userName": "Uladzimir",
//                            "isActive": true,
//                            "age": 29
//                        }
//                        """)));

        // when
        User foundUser = userClient.findUserById(userId);

        // then
        then(foundUser.getId()).isNotNull();
        then(foundUser.getName()).isEqualTo("Uladzimir");
        then(foundUser.getAge()).isEqualTo(29);

    }

}
