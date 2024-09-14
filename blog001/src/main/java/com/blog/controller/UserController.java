package com.blog.controller;


import com.blog.ResponseResult;
import com.blog.entry.Article;
import com.blog.entry.User;
import com.blog.entry.dto.ArticleDto;
import com.blog.entry.dto.ArticleDto2;
import com.blog.entry.dto.ArticleDto3;
import com.blog.entry.dto.UserDto;
import com.blog.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ArticleService;
import service.UserArticleLikeService;
import service.UserArticleService;
import service.UserService;
import service.impl.UserAndUserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserArticleService userArticleService;

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;
    @Autowired
    private UserAndUserServiceImpl userAndUserService;

    // 未测试接口
    @GetMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }
    @GetMapping("/getUserInfo/{id}")
    public ResponseResult getUserInfo(@PathVariable("id") Integer id) {
        return userService.getUserInfo(id);
    }
    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo() {
        return userService.getUserInfoByMe();
    }
    @GetMapping("/identifyCode")
    public ResponseResult identifyCode(@RequestParam("email") String email) {
        return userService.identifyCode(email);
    }
    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }
    @GetMapping("/articleList")
    public ResponseResult UserArticleList(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize) {
        return userService.UserArticleList(pageNum,pageSize);
    }
    @GetMapping("/article/{id}")
    public ResponseResult UserArticle(@PathVariable("id") Long id) {
        return userService.UserArticle(id);
    }
    // 未测试接口
    @PutMapping("/article")
    public ResponseResult updateUserArticle(@RequestBody ArticleDto3 articleDto3) {
        ArticleDto articleDto = BeanCopyUtils.copyBean(articleDto3, ArticleDto.class);
        articleDto.setId(Long.valueOf(articleDto3.getId()));
        return userService.updateUserArticle(articleDto);
    }
    // 未测试接口
    @DeleteMapping("/article/{id}")
    public ResponseResult deleteUserArticle(@PathVariable("id") Long id) {
        return  userService.deleteUserArticle(id);
    }
    @GetMapping("/collections")
    public ResponseResult UserCollections(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
        return userArticleService.userCollections(pageNum,pageSize);
    }
    @GetMapping("/history")
    public ResponseResult history(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
        return userService.history(pageNum,pageSize);
    }
    //未测试接口
    @PostMapping("/sendArticle")
    public ResponseResult sendArticle(@RequestBody ArticleDto2 articleDto2) {
        return articleService.sendArticle(articleDto2);
    }
    @GetMapping("getFans/{id}")
    public ResponseResult getFans(@PathVariable("id") Integer id) {
        return userService.getFans(id);
    }
    @GetMapping("/getConcern/{id}")
    public ResponseResult getConcern(@PathVariable("id") Integer id) {
        return userService.getConcern(id);
    }
    @GetMapping("/concern/{id}")
    public ResponseResult concern(@PathVariable("id") Integer id) {
        return userService.concern(id);
    }
    @GetMapping("/checkConcern/{id}")
    public ResponseResult checkConcern(@PathVariable("id") Integer id) {
        return userAndUserService.checkConcern(id);
    }
    @DeleteMapping("/concern/{id}")
    public ResponseResult deleteConcern(@PathVariable("id") Integer id) {
        return userAndUserService.deleteConcern(id);
    }

    @Resource
    private UserArticleLikeService userArticleLikeService;

    @GetMapping("/like/{id}")
    public ResponseResult like(@PathVariable("id") Long id) {
        return userArticleLikeService.like(id);
    }
    @GetMapping("/checkLike/{id}")
    public ResponseResult checkLike(@PathVariable("id") Long id) {
        return userArticleLikeService.checkLike(id);
    }
    @DeleteMapping("/like/{id}")
    public ResponseResult deleteLike(@PathVariable("id") Long id) {
        return userArticleLikeService.deleteLike(id);
    }
}
