package by.uladzimirkalesny.tddpracticeit.service;

import by.uladzimirkalesny.tddpracticeit.entity.User;
import by.uladzimirkalesny.tddpracticeit.exception.UserNotFoundException;
import by.uladzimirkalesny.tddpracticeit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository userRepository;

    @Cacheable("users")
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
