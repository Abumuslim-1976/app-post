package uz.pdp.apppost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apppost.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    boolean existsByTitle(String title);


}
