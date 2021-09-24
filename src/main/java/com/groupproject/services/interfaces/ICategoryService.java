package com.groupproject.services.interfaces;

import com.groupproject.entities.Category;
import com.groupproject.requests.CategoryRequest;

import java.util.List;

public interface ICategoryService {

    // list of all categories
    List<Category> getAll();

    // get category by id
    Category getById(Long id);

    // create new category
    void createNewCategory(CategoryRequest request);

    // delete a category by id
    boolean deleteCategoryById(Long id);

}
