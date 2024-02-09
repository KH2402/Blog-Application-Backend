package com.blogapplication.controllers;

import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.CategoryDto;
import com.blogapplication.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //create category api
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    // update category api
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto categoryDto1=this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }
    // delete category api
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory( @PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully!!",HttpStatus.OK);
    }

    // get category by id api
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory( @PathVariable  Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.FOUND);
    }

    // get all category api
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<CategoryDto> categoryDtos=this.categoryService.getCategories();
        return new ResponseEntity<>(categoryDtos,HttpStatus.FOUND);
    }
}
