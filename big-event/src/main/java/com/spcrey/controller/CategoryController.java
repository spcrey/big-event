package com.spcrey.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spcrey.pojo.Category;
import com.spcrey.pojo.Result;
import com.spcrey.service.CategoryService;
import com.spcrey.utils.ThreadLocalUtil;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result<String> add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result<String> update(@RequestBody @Validated(Category.Update.class) Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer categoryCreateUserId = categoryService.findById(category.getId()).getCreateUser();
        Integer userId = (Integer) map.get("id");
        if (categoryCreateUserId!=userId){
            return Result.error("not your category");
        }
        categoryService.update(category);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result<String> delete(Integer id) {
        try {
            Category category = categoryService.findById(id);
            Map<String, Object> map = ThreadLocalUtil.get();
            Integer userId = (Integer) map.get("id");
            if (category.getCreateUser()!=userId){
                return Result.error("not your category");
            }
            categoryService.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("not exist category");
        }  
    }
}
