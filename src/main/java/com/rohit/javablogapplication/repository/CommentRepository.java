package com.rohit.javablogapplication.repository;

import com.rohit.javablogapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
