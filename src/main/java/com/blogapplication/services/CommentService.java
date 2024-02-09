package com.blogapplication.services;

import com.blogapplication.payloads.CommentDto;

import java.util.List;

public interface CommentService {

    // create comment
    CommentDto createComment(CommentDto commentDto, Integer postId);

    // delete comment
    void deleteComment(Integer commentId);

    //update comment
    CommentDto updateComment(CommentDto commentDto, Integer commentId, Integer postId);

    List<CommentDto> getComments(Integer postId);
}
