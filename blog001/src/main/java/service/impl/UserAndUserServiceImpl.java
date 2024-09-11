package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entry.UserAndUser;
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

}


