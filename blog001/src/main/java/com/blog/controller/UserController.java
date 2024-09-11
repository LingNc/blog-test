package com.blog.controller;


import com.blog.ResponseResult;
import com.blog.entry.Article;
import com.blog.entry.User;
import com.blog.entry.dto.ArticleDto;
import com.blog.entry.dto.UserDto;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    // 未测试接口
    @GetMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }
    @GetMapping("/getUserInfo/{id}")
    public ResponseResult getUserInfo(@PathVariable("id") int id) {
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
    public ResponseResult updateUserArticle(@RequestBody ArticleDto articleDto) {
        return userService.updateUserArticle(articleDto);
    }
    // 未测试接口
    @DeleteMapping("/article/{id}")
    public ResponseResult deleteUserArticle(@PathVariable("id") Long id) {
        return  userService.deleteUserArticle(id);
    }
}
