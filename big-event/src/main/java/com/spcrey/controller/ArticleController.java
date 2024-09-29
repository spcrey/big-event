package com.spcrey.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spcrey.pojo.Article;
import com.spcrey.pojo.PageBean;
import com.spcrey.pojo.Result;
import com.spcrey.service.ArticleService;
import com.spcrey.utils.AliyunOssUtil;
import com.spcrey.utils.ThreadLocalUtil;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String state) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }

    @PostMapping
    public Result<?> add(@RequestBody @Validated(Article.Add.class) Article article) {
        try {
            String base64Image = article.getCoverImg();            
            String url = Base64ImageToUrl(base64Image);
            article.setCoverImg(url);
            articleService.add(article);
            return Result.success();
        } catch (Exception e) {
            return Result.error("image upload failed");
        }
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        Article article = articleService.findById(id);
        return Result.success(article);
    }

    @PutMapping
    public Result<String> update(@RequestBody @Validated(Article.Update.class) Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer articleCreateUserId = articleService.findById(article.getId()).getCreateUser();
        Integer userId = (Integer) map.get("id");
        if (articleCreateUserId!=userId){
            return Result.error("not your article");
        }
        try {
            String base64Image = article.getCoverImg();            
            String url = Base64ImageToUrl(base64Image);
            article.setCoverImg(url);
            articleService.update(article);
            return Result.success();
        } catch (Exception e) {
            return Result.error("image upload failed");
        }
    }

    @PostMapping("/delete")
    public Result<String> delete(Integer id) {
        try {
            Article article = articleService.findById(id);
            Map<String, Object> map = ThreadLocalUtil.get();
            Integer userId = (Integer) map.get("id");
            if (article.getCreateUser()!=userId){
                return Result.error("not your article");
            }
            articleService.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("not exist article");
        }  
    }

    private String Base64ImageToUrl(String base64Image){
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        String filename = UUID.randomUUID().toString() + ".jpg";
        String url;
        try {
            url = AliyunOssUtil.uploadFile(filename, inputStream);
            return url;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
