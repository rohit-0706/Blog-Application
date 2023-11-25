package com.rohit.javablogapplication.service;

import com.rohit.javablogapplication.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
