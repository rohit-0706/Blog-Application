package com.rohit.javablogapplication.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String user, String id, Integer userId) {
    }
}
