package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.UserAndUser;
import com.blog.utils.SecurityUtils;
import mapper.UserAndUserMapper;
import org.springframework.stereotype.Service;
import service.UserAndUserService;

/**
 * (UserAndUser)表服务实现类
 *
 * @author makejava
 * @since 2024-09-09 15:47:19
 */
@Service("userAndUserService")
public class UserAndUserServiceImpl extends ServiceImpl<UserAndUserMapper, UserAndUser> implements UserAndUserService {

    @Override
    public ResponseResult checkConcern(Integer id) {
        LambdaQueryWrapper<UserAndUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAndUser::getUid, SecurityUtils.getUserId());
        queryWrapper.eq(UserAndUser::getUid, id);
        if(count(queryWrapper) != 0) {
            return ResponseResult.okResult(1);
        }else {
            return ResponseResult.okResult(0);
        }
    }

    @Override
    public ResponseResult deleteConcern(Integer id) {
        LambdaQueryWrapper<UserAndUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAndUser::getUid, SecurityUtils.getUserId());
        queryWrapper.eq(UserAndUser::getConcernedId, id);
        remove(queryWrapper);
        return ResponseResult.okResult();
    }
}


