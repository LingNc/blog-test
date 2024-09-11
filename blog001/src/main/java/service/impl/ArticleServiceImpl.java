package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.Article;
import com.blog.entry.User;
import com.blog.entry.vo.ArticleVo2;
import com.blog.entry.vo.PageVo;
import com.blog.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import mapper.ArticleMapper;
import mapper.UserMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import service.ArticleService;
import service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2024-09-10 21:45:14
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Lazy
    @Resource
    private UserServiceImpl userServiceImpl;
    @Lazy
    @Resource
    private UserService userService;
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, String title, String categoryId, String nickName) {
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.like(Objects.nonNull(nickName),User::getNickName, nickName);
        List<User> list1 = userService.list(queryWrapper1);
        List<Integer> userIds = list1.stream().map(User::getId).toList();
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.hasText(categoryId)){
            queryWrapper.eq(Objects.nonNull(categoryId),Article::getCategoryId, Long.valueOf(categoryId));
        }
        queryWrapper.in(userIds.size()>0,Article::getCreateBy,userIds);
        queryWrapper.like(Objects.nonNull(title),Article::getTitle, title);
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> records = page.getRecords();
        List<ArticleVo2> articleVo2s = BeanCopyUtils.copyBeanList(records, ArticleVo2.class);
        List<ArticleVo2> list = new java.util.ArrayList<>(articleVo2s.stream().map(a -> {
            a.setLike(userServiceImpl.findLikeCount(a.getId()));
            a.setStar(userServiceImpl.findStarCount(a.getId()));
            return a;
        }).toList());
        list.sort((a1,a2)->{
            Double index1 = a1.getLike()*0.4+a1.getStar()*0.3+1/(System.currentTimeMillis()-a1.getCreateTime().getTime());
            Double index2 = a2.getLike()*0.4+a2.getStar()*0.3+1/(System.currentTimeMillis()-a2.getCreateTime().getTime());
            return Double.compare(index1,index2);
        });
        List<ArticleVo2> list2 = list.stream().map(a -> a.setCreateName(findCreateNameByArticleId(a.getId()))).toList();
        return ResponseResult.okResult(new PageVo(list2, page.getTotal()));
    }
    public String findCreateNameByArticleId(Long articleId) {
        Article article = getById(articleId);
        User byId = userService.getById(article.getCreateBy());
        return byId.getNickName();
    }
}


