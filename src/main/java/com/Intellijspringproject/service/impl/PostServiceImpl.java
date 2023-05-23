package com.Intellijspringproject.service.impl;

import com.Intellijspringproject.entity.Post;
import com.Intellijspringproject.exception.ResourceNotFoundException;
import com.Intellijspringproject.payload.PostDto;
import com.Intellijspringproject.payload.PostResponse;
import com.Intellijspringproject.repository.PostRepository;
import com.Intellijspringproject.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    //bean/dependency injection is done by @autowired or constructor based injection
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    private ModelMapper mapper;




    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=mapToEntity(postDto);//This method convert Dto into Entity
        Post newPost = postRepository.save(post);//here newPost object stores
        // the entity data which save the data in database with help of post Repository but here we want to return again dto object
        PostDto newPostDto = mapToDto(newPost);//this method convert entity data into Dto
        return newPostDto;//newPostDto stores dto data
    }
     // get all post details with pagination
    @Override
    public  PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        //Inabove Page is a return type of data but we want it List<> so we use
        List<Post> contents = posts.getContent();
        //here we convert entity data coming from database into Dto by using java 8 feature stream() with labdas expression and collectors

        List<PostDto> postDtos = contents.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());
        return postResponse;



    }

    @Override
    public PostDto getPostById(long id) {
        //here we use java feature lambdas expression the following method find data with id it print response back to postman
        //or if it not find id number it goes in exception and it return message in postman
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        //here we cant return entity object so we convert into Dto by using mapToDto method
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        //For update and save this updated data into database follow steps
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        //This stored entity data again  converted to dto
        PostDto dto = mapToDto(updatedPost);
        //and return this object dto to PostDto
        return dto;
    }

    @Override
    public void deletePostById(long id) {
        //If here id num is not present in database that we wanted to delete
        //For this we first check that by findById method if not find then it shows Exception message as follows
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(post);

    }

    PostDto mapToDto(Post post) {
       PostDto postDto= mapper.map(post,PostDto.class);
       //PostDto postDto=new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
       return postDto;
    }

    //This method convert Dto to entity
     Post mapToEntity(PostDto postDto) {
        Post post=mapper.map(postDto,Post.class);
        //Post post=new Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}
