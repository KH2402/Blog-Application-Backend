package com.blogapplication.services.impl;

import com.blogapplication.entities.Comment;
import com.blogapplication.entities.Post;

import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.CommentDto;
import com.blogapplication.repositories.CommentRepo;
import com.blogapplication.repositories.PostRepo;
import com.blogapplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);

        Comment savedComment=this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment",commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment",commentId));
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        Comment updatedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getComments(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        List<Comment> comments=post.getComments();
        List<CommentDto> commentDtos=comments.stream().map(comment -> this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }
}
