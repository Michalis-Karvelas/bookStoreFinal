package com.groupproject.services;

import com.groupproject.entities.Category;
import com.groupproject.repository.BookRepository;
import com.groupproject.repository.CategoryRepository;
import com.groupproject.requests.CategoryRequest;
import java.util.List;

import com.groupproject.services.interfaces.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    // list of the categories
    @Override
    public List<Category> getAll() {
        log.info("Ready to found all the categories");
        return categoryRepository.findAll();
    }

    // get a category by id
    @Override
    public Category getById(Long id) {
        log.info("ready to find the category with the id {}",id);
        return categoryRepository.findById(id).orElse(null);
    }



    @Override
    public void createNewCategory(CategoryRequest request) {
        log.info("Ready to create a category. The request is {}",request);

        log.info("Ready to save the new category");
        Category category = new Category(request.getType());
        categoryRepository.save(category);
        log.info("Saved successfully");
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        log.info("Ready to delete a category");
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            log.info("category deleted successfully");
            return true;
        }
        log.info("category has not deleted successfully");
        return false;
    }
}
