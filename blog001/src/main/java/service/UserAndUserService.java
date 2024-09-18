package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.ResponseResult;
import com.blog.entry.UserAndUser;


/**
 * (UserAndUser)表服务接口
 *
 * @author makejava
 * @since 2024-09-09 15:47:19
 */
public interface UserAndUserService extends IService<UserAndUser> {

    ResponseResult checkConcern(Integer id);

    ResponseResult deleteConcern(Integer id);
}


