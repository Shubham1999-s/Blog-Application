package com.Intellijspringproject.controller;

import com.Intellijspringproject.payload.CommentDto;
import com.Intellijspringproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")

public class CommentController {
    @Autowired
    private CommentService commentService;


    //localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Object> createComment(@Valid @PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto,
                                                    BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    //localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(
            @PathVariable("postId") long postId) {
         return commentService.getCommentsByPostId(postId);

    }
    //localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable("postId") long postId,@PathVariable("id") long commentId
    ){
        CommentDto dto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
   // localhost:8080/api/posts/1/comments/1
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto>
    updateComment(@PathVariable("postId") long postId,

                  @PathVariable("id") long id,@RequestBody CommentDto commentDto){
        CommentDto dto=commentService.updateComment(postId,id,commentDto);
        return  new ResponseEntity<>(dto,HttpStatus.OK);

    }
    //localhost:8080/api/posts/1/comments/5
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}
