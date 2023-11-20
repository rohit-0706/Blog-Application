package com.rohit.javablogapplication.service;
import com.rohit.javablogapplication.dto.PostDto;
import com.rohit.javablogapplication.entity.Post;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
    Post updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<Post> searchPosts(String keyword);

}
