package com.rohit.javablogapplication.repository;

import com.rohit.javablogapplication.entity.Category;
import com.rohit.javablogapplication.entity.Post;
import com.rohit.javablogapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);


}
