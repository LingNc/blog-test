package com.blog.controller;

import com.blog.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import service.ArticleService;
import service.UserArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserArticleService userArticleService;

    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam(value = "title",required = false) String title,
                                      @RequestParam(value = "categoryId",required = false) String categoryId,
                                      @RequestParam(value = "nickName",required = false) String nickName) {
            return articleService.articleList(pageNum,pageSize,title, categoryId,nickName);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/collect/{id}")
    public ResponseResult collect(@PathVariable("id") Long id) {
        return userArticleService.collect(id);
    }
    @GetMapping("/checkCollect/{id}")
    public ResponseResult checkCollect(@PathVariable("id") Long id) {
        return userArticleService.checkCollect(id);
    }
    @DeleteMapping("/collect/{id}")
    public ResponseResult deleteCollect(@PathVariable("id") Long id) {
        return userArticleService.deleteCollect(id);
    }
}
