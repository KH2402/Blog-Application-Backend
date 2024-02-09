package com.blogapplication.controllers;


import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.CommentDto;
import com.blogapplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // create comment api
    @PostMapping("post/{postId}/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){

        CommentDto comment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }

    @GetMapping("post/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable Integer postId){

        List<CommentDto> comments = this.commentService.getComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);

    }


    // delete comment api
    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<?> deleteComment( @PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully !!",true), HttpStatus.CREATED);

    }

    // update comment api
    @PutMapping("post/{postId}/comment/{commentId}")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId, @PathVariable Integer postId){

        CommentDto comment = this.commentService.updateComment(commentDto,commentId,postId);
        return new ResponseEntity<>(comment, HttpStatus.OK);

    }
}
