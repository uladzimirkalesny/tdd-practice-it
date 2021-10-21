package by.uladzimirkalesny.tddpracticeit.controller;

import by.uladzimirkalesny.tddpracticeit.entity.User;
import by.uladzimirkalesny.tddpracticeit.exception.UserNotFoundException;
import by.uladzimirkalesny.tddpracticeit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testFindUser_ForSavedUser_isReturned() throws Exception {
        // given
        given(userService.findUserById(anyLong()))
                .willReturn(User.builder().id(1L).name("Uladzimir").age(29).isActive(true).build());

        // when - then
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Uladzimir"))
                .andExpect(jsonPath("isActive").value(true))
                .andExpect(jsonPath("age").value(29));
    }

    @Test
    void testFindUser_ForMissingUser_errorStatus404() throws Exception {
        // given
        given(userService.findUserById(anyLong()))
                .willThrow(UserNotFoundException.class);

        // when - then
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }
}
