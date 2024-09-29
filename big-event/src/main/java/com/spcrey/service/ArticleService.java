package com.spcrey.service;

import com.spcrey.pojo.Article;
import com.spcrey.pojo.PageBean;

public interface ArticleService {

    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article findById(Integer id);

    void update(Article article);

    void deleteById(Integer id);
    
} 