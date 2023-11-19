package com.rohit.javablogapplication.dto;

import com.rohit.javablogapplication.entity.Category;
import com.rohit.javablogapplication.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;

}
