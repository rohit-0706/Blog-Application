package com.rohit.javablogapplication.service;
import com.rohit.javablogapplication.dto.PostDto;
import com.rohit.javablogapplication.entity.Post;
import com.rohit.javablogapplication.utils.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
