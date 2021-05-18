package uz.pdp.apppost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.PostDTO;
import uz.pdp.apppost.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;


    @PreAuthorize("hasAnyAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> createPost(@Valid @RequestBody PostDTO postDTO) {
        ApiResponse apiResponse = postService.addPost(postDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PreAuthorize("hasAnyAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    public HttpEntity<?> editPost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO) {
        ApiResponse apiResponse = postService.editPosts(id, postDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @PreAuthorize("hasAnyAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id) {
        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }




}
