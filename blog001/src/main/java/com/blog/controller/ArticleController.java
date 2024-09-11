package com.blog.controller;

import com.blog.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam(value = "title",required = false) String title,
                                      @RequestParam(value = "categoryId",required = false) String categoryId,
                                      @RequestParam(value = "nickName",required = false) String nickName) {
        return articleService.articleList(pageNum,pageSize,title,categoryId,nickName);
    }
}
