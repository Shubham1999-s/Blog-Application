package com.Intellijspringproject.service;

import com.Intellijspringproject.payload.PostDto;
import com.Intellijspringproject.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    //get all posts details
     PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);//here return type is void because here we not back any method after deleting
}
