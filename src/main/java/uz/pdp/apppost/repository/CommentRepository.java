package uz.pdp.apppost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apppost.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
