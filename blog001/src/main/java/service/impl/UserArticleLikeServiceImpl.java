package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entry.UserArticleLike;
import mapper.UserArticleLikeMapper;
import org.springframework.stereotype.Service;
import service.UserArticleLikeService;

/**
 * (UserArticleLike)表服务实现类
 *
 * @author makejava
 * @since 2024-09-11 14:46:42
 */
@Service("userArticleLikeService")
public class UserArticleLikeServiceImpl extends ServiceImpl<UserArticleLikeMapper, UserArticleLike> implements UserArticleLikeService {

}


