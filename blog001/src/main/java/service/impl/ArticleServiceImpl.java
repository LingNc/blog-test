package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.Article;
import com.blog.entry.User;
import com.blog.entry.dto.ArticleDto2;
import com.blog.entry.vo.ArticleVo2;
import com.blog.entry.vo.PageVo;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.RedisCache;
import jakarta.annotation.Resource;
import mapper.ArticleMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import service.ArticleService;
import service.HistoryService;
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
        queryWrapper.eq(Objects.nonNull(categoryId),Article::getCategoryId, categoryId);
        queryWrapper.in(userIds.size()>0,Article::getCreateBy,userIds);
        queryWrapper.like(Objects.nonNull(title),Article::getTitle, title);
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> records = page.getRecords();
        List<ArticleVo2> articleVo2s = BeanCopyUtils.copyBeanList(records, ArticleVo2.class);
        List<ArticleVo2> list = new java.util.ArrayList<>(articleVo2s.stream().map(a -> {
            a.setLike(userServiceImpl.findLikeCount(a.getId()));
            a.setStar(userServiceImpl.findStarCount(a.getId()));
            a.setCreateName(findCreateNameByArticleId(a.getId()));
            Integer cacheMapValue = redisCache.getCacheMapValue("Article:viewCount", a.getId().toString());
            a.setViewCount(Long.valueOf(cacheMapValue));
            return a;
        }).toList());
        list.sort((a1,a2)->{
            Double index1 = a1.getLike()*0.4+a1.getStar()*0.3+1/(System.currentTimeMillis()-a1.getCreateTime().getTime());
            Double index2 = a2.getLike()*0.4+a2.getStar()*0.3+1/(System.currentTimeMillis()-a2.getCreateTime().getTime());
            return Double.compare(index1,index2);
        });
        return ResponseResult.okResult(new PageVo(list, page.getTotal()));
    }

    @Resource
    private HistoryService historyService;

    @Resource
    private RedisCache redisCache;
    @Override
    public ResponseResult getArticleById(Long id) {
        Article article = getById(id);
        ArticleVo2 articleVo2 = BeanCopyUtils.copyBean(article, ArticleVo2.class);
        articleVo2.setLike(userServiceImpl.findLikeCount(article.getId()));
        articleVo2.setStar(userServiceImpl.findStarCount(article.getId()));
        articleVo2.setCreateName(findCreateNameByArticleId(article.getId()));
        Integer cacheMapValue = redisCache.getCacheMapValue("Article:viewCount", id.toString());
        articleVo2.setViewCount(Long.valueOf(cacheMapValue));
        historyService.saveHistoryById(id);
        return ResponseResult.okResult(articleVo2);
    }


    @Override
    public ResponseResult sendArticle(ArticleDto2 articleDto2) {
        Article article = BeanCopyUtils.copyBean(articleDto2, Article.class);
        article.setViewCount(0L);
        article.setDelFlag(0);
        save(article);
        return ResponseResult.okResult();
    }

    public String findCreateNameByArticleId(Long articleId) {
        Article article = getById(articleId);
        User byId = userService.getById(article.getCreateBy());
        return byId.getNickName();
    }
}


