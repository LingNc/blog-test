package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.ResponseResult;
import com.blog.entry.User;
import com.blog.entry.dto.ArticleDto;
import com.blog.entry.dto.UserDto;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2024-09-08 15:57:33
 */
public interface UserService extends IService<User> {

    ResponseResult login(User user);

    ResponseResult getUserInfo(int id);

    ResponseResult identifyCode(String email);

    ResponseResult register(UserDto userDto);

    ResponseResult UserArticleList(Integer pageNum, Integer pageSize);

    ResponseResult getUserInfoByMe();

    ResponseResult UserArticle(Long id);

    ResponseResult updateUserArticle(ArticleDto articleDto);

    ResponseResult deleteUserArticle(Long id);
}


