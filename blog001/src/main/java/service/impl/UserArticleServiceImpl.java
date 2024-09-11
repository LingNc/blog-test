package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entry.UserArticle;
import mapper.UserArticleMapper;
import org.springframework.stereotype.Service;
import service.UserArticleService;

/**
 * (UserArticle)表服务实现类
 *
 * @author makejava
 * @since 2024-09-11 14:44:31
 */
@Service("userArticleService")
public class UserArticleServiceImpl extends ServiceImpl<UserArticleMapper, UserArticle> implements UserArticleService {

}


