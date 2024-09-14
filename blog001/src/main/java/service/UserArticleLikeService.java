package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.ResponseResult;
import com.blog.entry.UserArticleLike;


/**
 * (UserArticleLike)表服务接口
 *
 * @author makejava
 * @since 2024-09-11 14:46:42
 */
public interface UserArticleLikeService extends IService<UserArticleLike> {

    ResponseResult like(Long id);

    ResponseResult checkLike(Long id);

    ResponseResult deleteLike(Long id);
}


