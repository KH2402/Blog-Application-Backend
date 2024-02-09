package com.blogapplication.services.impl;

import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.CategoryDto;
import com.blogapplication.payloads.PostDto;
import com.blogapplication.payloads.PostResponse;
import com.blogapplication.payloads.UserDto;
import com.blogapplication.repositories.CategoryRepo;
import com.blogapplication.repositories.PostRepo;
import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));

        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));


        Post post=this.dtoToPost(postDto,category,user);
        post.setAddedDate(new Date());
        post.setImageName("default.png");


        Post newPost=this.postRepo.save(post);

        PostDto postDto1=this.postToDto(newPost,user,category);

        return postDto1;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        ///
        this.postRepo.save(post);
        return this.postToDto(post,post.getUser(),post.getCategory());
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        //pagination
        // pageSize and pageNumber  (limit and offset)

        // pagination is concept used to for getting records where we will not get all records in single page
        // instead it gets records by pageSize

        Sort sort=null;

        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pagePosts=this.postRepo.findAll(pageable);

        List<Post> posts=pagePosts.getContent();

        List<PostDto> postDtos=posts.stream().map(post -> this.postToDto(post,post.getUser(),post.getCategory())).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post ID",postId));

        return this.postToDto(post,post.getUser(),post.getCategory());
    }
////////////////////
    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));

        Pageable pageable=PageRequest.of(pageNumber,pageSize);

        Page<Post> pagePosts= this.postRepo.findByCategory(category,pageable);

        List<Post> posts=pagePosts.getContent();

        List<PostDto> postDtos=
                posts.stream().map(post ->this.postToDto(post,post.getUser(),category)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));

        Pageable pageable=PageRequest.of(pageNumber,pageSize);

        Page<Post> pagePosts= this.postRepo.findByUser(user,pageable);

        List<Post> posts=pagePosts.getContent();

        List<PostDto> postDtos=posts.stream().map(post->this.postToDto(post,user,post.getCategory())).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keywords) {
        List<Post> posts=this.postRepo.findByTitleContaining(keywords);
        List<PostDto> postDtos=posts.stream().map((post)->this.postToDto(post,post.getUser(), post.getCategory())).collect(Collectors.toList());
        return postDtos;
    }

    public PostDto postToDto(Post post,User user, Category category){
        PostDto postDto=this.modelMapper.map(post,PostDto.class);
        CategoryDto categoryDto=this.modelMapper.map(category, CategoryDto.class);
        UserDto userDto=this.modelMapper.map(user, UserDto.class);

        postDto.setCategoryDto(categoryDto);
        postDto.setUserDto(userDto);

        return postDto;
    }
    public Post dtoToPost(PostDto postDto, Category category, User user){
        Post post=this.modelMapper.map(postDto, Post.class);

        post.setCategory(category);
        post.setUser(user);

        return post;
    }
}
