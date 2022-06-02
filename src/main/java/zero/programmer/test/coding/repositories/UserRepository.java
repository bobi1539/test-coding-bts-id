package zero.programmer.test.coding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zero.programmer.test.coding.entitites.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
