package com.rohit.javablogapplication.service.Impl;

import com.rohit.javablogapplication.dto.CategoryDto;
import com.rohit.javablogapplication.entity.Category;
import com.rohit.javablogapplication.exceptions.ResourceNotFoundException;
import com.rohit.javablogapplication.repository.CategoryRepository;
import com.rohit.javablogapplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category addedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(addedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map((category)-> this.modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
    }
}
