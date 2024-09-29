package com.spcrey.service;

import java.util.List;

import com.spcrey.pojo.Category;

public interface CategoryService {

    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);
    
    void deleteById(Integer id);
}
