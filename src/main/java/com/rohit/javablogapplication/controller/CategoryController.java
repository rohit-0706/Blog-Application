package com.rohit.javablogapplication.controller;

import com.rohit.javablogapplication.dto.CategoryDto;
import com.rohit.javablogapplication.service.CategoryService;
import com.rohit.javablogapplication.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @PatchMapping("/{category_Id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                      @PathVariable Integer category_Id){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,category_Id);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{category_Id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer category_Id){
        this.categoryService.deleteCategory(category_Id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true),HttpStatus.OK);
    }

    @GetMapping("/{category_Id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer category_Id){
        CategoryDto categoryDto = this.categoryService.getCategory(category_Id);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = this.categoryService.getCategories();
        return ResponseEntity.ok(categoryDtoList);
    }
}
