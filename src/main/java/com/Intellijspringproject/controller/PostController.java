package com.Intellijspringproject.controller;

import com.Intellijspringproject.payload.PostDto;
import com.Intellijspringproject.payload.PostResponse;
import com.Intellijspringproject.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    public PostController(PostService postService) {
        this.postService = postService;
    }

    private PostService postService;
    //localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto,
                                              BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
     //localhost:8080/api/posts?pageNo=0&pageSize=3
     @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    //get all posts details
    //here we also does pagination and sorting as follwos
    public PostResponse getAllPosts
    (@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,@RequestParam(value="pageSize",defaultValue = "10", required = false)int pageSize,
                                     @RequestParam(value ="sortBy",defaultValue = "id",required = false)
                                         String sortBy,
     @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
         PostResponse postDto = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return postDto;
    }
    //Get posts details by id number
    //localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);

    }
    //localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto updatePost= postService.updatePost(postDto, id);
        return ResponseEntity.ok(updatePost);

    }
    //localhost:8080/api/posts/9
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id){//here Path variable provide no of id that will delete to postService
        //here we return string with message to postman
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted succesfully",HttpStatus.OK);
    }
}
