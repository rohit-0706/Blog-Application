package com.rohit.javablogapplication.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

}
