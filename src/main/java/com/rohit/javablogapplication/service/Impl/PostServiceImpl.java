package com.rohit.javablogapplication.service.Impl;

import com.rohit.javablogapplication.dto.PostDto;
import com.rohit.javablogapplication.entity.Category;
import com.rohit.javablogapplication.entity.Post;
import com.rohit.javablogapplication.entity.User;
import com.rohit.javablogapplication.exceptions.ResourceNotFoundException;
import com.rohit.javablogapplication.repository.CategoryRepository;
import com.rohit.javablogapplication.repository.PostRepository;
import com.rohit.javablogapplication.repository.UserRepository;
import com.rohit.javablogapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
