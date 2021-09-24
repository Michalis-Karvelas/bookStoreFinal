package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Category;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse extends Response{

    private List<Category> categories;
    private Category category;

    public CategoryResponse(String msg, List<Category> categories){

        super(msg);
        this.categories=categories;
    }

    public CategoryResponse(String msg, Category category){

        super(msg);
        this.category=category;
    }
}
