package com.blog.controller;


import com.blog.ResponseResult;
import com.blog.entry.Comment;
import com.blog.entry.dto.CommentDto;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(@RequestParam("articleId") String articleId,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        return commentService.commentList(articleId,pageNum,pageSize);
    }
    @PostMapping("/sendComment")
    public ResponseResult sendComment(@RequestBody CommentDto commentDto) {
        return commentService.sendComment(commentDto);
    }
}
