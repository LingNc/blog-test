package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.UserArticleLike;
import com.blog.utils.SecurityUtils;
import mapper.UserArticleLikeMapper;
import org.springframework.stereotype.Service;
import service.UserArticleLikeService;

import java.util.Date;

/**
 * (UserArticleLike)表服务实现类
 *
 * @author makejava
 * @since 2024-09-11 14:46:42
 */
@Service("userArticleLikeService")
public class UserArticleLikeServiceImpl extends ServiceImpl<UserArticleLikeMapper, UserArticleLike> implements UserArticleLikeService {

    @Override
    public ResponseResult like(Long id) {
        UserArticleLike userArticleLike = new UserArticleLike(SecurityUtils.getUserId(),id,new Date());
        save(userArticleLike);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult checkLike(Long id) {
        LambdaQueryWrapper<UserArticleLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticleLike::getArticleId,id);
        queryWrapper.eq(UserArticleLike::getUid,SecurityUtils.getUserId());
        if(count(queryWrapper) != 0){
            return ResponseResult.okResult(1);
        }else {
            return ResponseResult.okResult(0);
        }
    }

    @Override
    public ResponseResult deleteLike(Long id) {
        LambdaQueryWrapper<UserArticleLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticleLike::getArticleId,id);
        queryWrapper.eq(UserArticleLike::getUid,SecurityUtils.getUserId());
        remove(queryWrapper);
        return ResponseResult.okResult();
    }
}


