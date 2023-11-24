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
import com.rohit.javablogapplication.utils.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post= postRepository.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> allPosts = postPage.getContent();


        List<PostDto> postDtos =  allPosts.stream().map((post) ->modelMapper.
                map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map((post) -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map((post) -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
