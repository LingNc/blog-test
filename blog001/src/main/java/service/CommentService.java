package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.ResponseResult;
import com.blog.entry.Comment;
import com.blog.entry.dto.CommentDto;


/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2024-09-13 14:26:01
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String articleId, Integer pageNum, Integer pageSize);

    ResponseResult sendComment(CommentDto commentDto);
}


