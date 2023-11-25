package com.rohit.javablogapplication.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {

    private int id;
    private String content;
}
