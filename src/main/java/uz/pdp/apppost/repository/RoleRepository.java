package uz.pdp.apppost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apppost.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

}
