package com.blog.controller;

import com.blog.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service.UploadService;

@RestController
public class UploadController {
    @Resource
    private UploadService uploadService;

    // 未测试接口
    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile file) {
        try {
            return uploadService.uploadImg(file);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }
}
