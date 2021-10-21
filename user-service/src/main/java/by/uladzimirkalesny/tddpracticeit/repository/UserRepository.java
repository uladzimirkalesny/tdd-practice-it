package by.uladzimirkalesny.tddpracticeit.repository;

import by.uladzimirkalesny.tddpracticeit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByName(String name);

    @Query(value = "SELECT AVG(age) FROM User WHERE isActive = true")
    Double getAvgAgeForActiveUsers();
}
