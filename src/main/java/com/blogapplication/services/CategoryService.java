package com.blogapplication.services;


import com.blogapplication.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create  category
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    //get category
    CategoryDto getCategory(Integer categoryId);

    //get all category
    List<CategoryDto> getCategories();

}
