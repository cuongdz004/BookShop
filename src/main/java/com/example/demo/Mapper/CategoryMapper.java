package com.example.demo.Mapper;

import com.example.demo.Dto.Request.CategoryRequest;
import com.example.demo.Dto.Response.CategoryResponse;
import com.example.demo.Entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public List<CategoryResponse> CategoryEntityMapperToResponse(List<Category> listcategory){
        return listcategory.stream()
                .map( c -> {
                        CategoryResponse categoryResponse = new CategoryResponse();
                        categoryResponse.setCategoryName(c.getName());
                        categoryResponse.setCategoryId(c.getId());
                        return categoryResponse;
                })
                .toList();
    }

    public Category CategoryRequestMapperToEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return category;
    }
}
