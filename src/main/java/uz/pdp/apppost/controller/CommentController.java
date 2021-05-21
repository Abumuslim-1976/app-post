package uz.pdp.apppost.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.CommentDTO;
import uz.pdp.apppost.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    @Autowired
    CommentService commentService;


    @GetMapping
    public HttpEntity<?> getCommentList() {
        ApiResponse apiResponse = commentService.viewComment();
        return ResponseEntity.ok(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        ApiResponse apiResponse = commentService.addComment(commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('EDIT_COMMENT')")
    @PutMapping("/editComment/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {
        ApiResponse apiResponse = commentService.editedComment(id, commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMENT')")
    @DeleteMapping("/deleteComment/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
