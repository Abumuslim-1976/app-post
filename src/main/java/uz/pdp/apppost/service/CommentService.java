package uz.pdp.apppost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.apppost.entity.Comment;
import uz.pdp.apppost.entity.Post;
import uz.pdp.apppost.entity.User;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.CommentDTO;
import uz.pdp.apppost.repository.CommentRepository;
import uz.pdp.apppost.repository.PostRepository;
import uz.pdp.apppost.repository.UserRepository;
import uz.pdp.apppost.utils.AppConst;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {


    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;


    /*
     *Commentlar tarixi.Buni hamma ko`ra oladi.
     * */
    public ApiResponse viewComment() {
        List<Comment> commentList = commentRepository.findAll();
        return new ApiResponse("Commentlar listi", true, commentList);
    }


    /*
     * Comment add qilish.Commentni hamma yoza oladi.
     * */
    public ApiResponse addComment(CommentDTO commentDTO) {
        Optional<Post> postOptional = postRepository.findById(commentDTO.getPostId());
        if (!postOptional.isPresent())
            return new ApiResponse("Bunday ID lik post yo`q", false);

        commentRepository.save(new Comment(
                commentDTO.getText(),
                postOptional.get()
        ));
        return new ApiResponse("Comment saqlandi", true);
    }


    /*
     * Commentni edit qilish.Mas`ul xodim o`zini commentini ham
     * boshqalarni commentini ham edit qila oladi
     * */
    public ApiResponse editedComment(Long id, CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent())
            return new ApiResponse("Bunday ID lik comment yo`q", false);

        Optional<Post> postOptional = postRepository.findById(commentDTO.getPostId());
        if (!postOptional.isPresent())
            return new ApiResponse("Bunday ID lik post yo`q", false);

        Comment comment = commentOptional.get();
        comment.setText(commentDTO.getText());
        comment.setPost(postOptional.get());
        commentRepository.save(comment);
        return new ApiResponse("Comment tahrirlandi", true);
    }


    /*
     * Foydalanuvchi o`z kommentini tahrirlashi
     * */
    public ApiResponse editMyComment(Long id, CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent())
            return new ApiResponse("Bunday ID lik comment yo`q", false);

        Optional<Post> postOptional = postRepository.findById(commentDTO.getPostId());
        if (!postOptional.isPresent())
            return new ApiResponse("Bunday ID lik post yo`q", false);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getId().equals(commentOptional.get().getCreatedBy())) {
            Comment comment = commentOptional.get();
            comment.setText(commentDTO.getText());
            comment.setPost(postOptional.get());
            commentRepository.save(comment);
            return new ApiResponse("Foydalanuvchi o`z commentini tahrirladi", true);
        } else {
            return new ApiResponse("Foydalanuvchi commentni tahrirlay olmaydi", false);
        }
    }


    /*
     * Commentni delete qilish.Mas`ul xodim o`zini commentini ham
     * boshqalarni commentini ham delete qila oladi
     * */
    public ApiResponse deleteComment(Long id) {
        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            throw new NullPointerException("Comment topilmadi");
        }
        return new ApiResponse("Comment o`chirildi", true);
    }


    /*
     * User o`z commentini o`chirishi
     * */
    public ApiResponse deleteMyComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent())
            return new ApiResponse("Bunday ID lik comment topilmadi", false);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getId().equals(commentOptional.get().getCreatedBy())) {
            commentRepository.deleteById(id);
            return new ApiResponse("User commentini o`chirdi", true);
        }
        return new ApiResponse("User bu commentni o`chira olmaydi", false);

    }

}




