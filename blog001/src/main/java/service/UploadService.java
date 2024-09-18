package service;

import com.blog.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public ResponseResult uploadImg(MultipartFile file);
}
