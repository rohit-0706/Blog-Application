package com.rohit.javablogapplication.service.Impl;

import com.rohit.javablogapplication.dto.CommentDto;
import com.rohit.javablogapplication.entity.Comment;
import com.rohit.javablogapplication.entity.Post;
import com.rohit.javablogapplication.exceptions.ResourceNotFoundException;
import com.rohit.javablogapplication.repository.CommentRepository;
import com.rohit.javablogapplication.repository.PostRepository;
import com.rohit.javablogapplication.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=commentRepository.findById(commentId).
                orElseThrow(()-> new ResourceNotFoundException("Comment","Comment Id",commentId));
        commentRepository.delete(comment);
    }
}
