package com.blogapplication.services;


import com.blogapplication.payloads.PostDto;
import com.blogapplication.payloads.PostResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    // create post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update post(edit)
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete post
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post within category
    PostResponse getPostsByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);

    //get all post by user
    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);

    //search posts
    List<PostDto> searchPosts(String keywords);

}
