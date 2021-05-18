package uz.pdp.apppost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apppost.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}
