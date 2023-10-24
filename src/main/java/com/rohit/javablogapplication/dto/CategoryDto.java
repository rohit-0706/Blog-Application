package com.rohit.javablogapplication.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private String categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
