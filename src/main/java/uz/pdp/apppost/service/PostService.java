package uz.pdp.apppost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apppost.entity.Post;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.PostDTO;
import uz.pdp.apppost.repository.PostRepository;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public ApiResponse addPost(PostDTO postDTO) {
        if (postRepository.existsByTitle(postDTO.getTitle()))
            return new ApiResponse("Bunday sarlovhali matn bor", false);

        postRepository.save(new Post(
                postDTO.getTitle(),
                postDTO.getText(),
                postDTO.getUrl()
        ));
        return new ApiResponse("Post saqlandi", true);
    }


    public ApiResponse editPosts(Long id, PostDTO postDTO) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (!postOptional.isPresent())
            return new ApiResponse("Bunday post topilmadi", false);

        Post post = postOptional.get();
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setUrl(postDTO.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post edit qilindi", true);
    }

    public ApiResponse deletePost(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new NullPointerException("Post topilmadi");
        }
        return new ApiResponse("Post o`chirib tashlandi", true);
    }
}
