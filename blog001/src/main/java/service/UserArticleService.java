package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.ResponseResult;
import com.blog.entry.UserArticle;


/**
 * (UserArticle)表服务接口
 *
 * @author makejava
 * @since 2024-09-11 14:44:31
 */
public interface UserArticleService extends IService<UserArticle> {

    ResponseResult collect(Long id);

    ResponseResult checkCollect(Long id);

    ResponseResult deleteCollect(Long id);

    ResponseResult userCollections(Integer pageNum, Integer pageSize);
}


