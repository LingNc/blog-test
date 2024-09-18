package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.Article;
import com.blog.entry.UserArticle;
import com.blog.entry.vo.ArticleVo2;
import com.blog.entry.vo.PageVo;
import com.blog.enums.AppHttpCodeEnum;
import com.blog.excption.SystemException;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.SecurityUtils;
import jakarta.annotation.Resource;
import mapper.UserArticleMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import service.ArticleService;
import service.UserArticleService;
import service.UserService;

import java.util.Date;
import java.util.List;

/**
 * (UserArticle)表服务实现类
 *
 * @author makejava
 * @since 2024-09-11 14:44:31
 */
@Service("userArticleService")
public class UserArticleServiceImpl extends ServiceImpl<UserArticleMapper, UserArticle> implements UserArticleService {
    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleServiceImpl articleServiceImpl;

    @Lazy
    @Resource
    private UserServiceImpl userService;

    @Override
    public ResponseResult collect(Long id) {
        if (id > 0){
            UserArticle userArticle = new UserArticle(SecurityUtils.getUserId(),id,new Date());
            save(userArticle);
            return ResponseResult.okResult();
        }else {
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult checkCollect(Long id) {
        LambdaQueryWrapper<UserArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticle::getArticleId,id);
        queryWrapper.eq(UserArticle::getUid,SecurityUtils.getUserId());
        if (count(queryWrapper)>0){
            return ResponseResult.okResult(1);
        }else {
            return ResponseResult.okResult(0);
        }
    }

    @Override
    public ResponseResult deleteCollect(Long id) {
        LambdaQueryWrapper<UserArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticle::getArticleId,id);
        queryWrapper.eq(UserArticle::getUid,SecurityUtils.getUserId());
        remove(queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userCollections(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<UserArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticle::getUid,1);
        queryWrapper.orderByDesc(UserArticle::getCreateTime);
        List<UserArticle> list = list(queryWrapper);
        List<Long> articleIds = list.stream().map(UserArticle::getArticleId).toList();
        LambdaQueryWrapper<Article> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(articleIds.size()>0,Article::getId,articleIds);
        Page<Article> page = new Page<>(pageNum,pageSize);
        articleService.page(page,queryWrapper1);
        List<ArticleVo2> articleVo2s = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleVo2.class);
        List<ArticleVo2> list1 = articleVo2s.stream().map(a -> {
            a.setLike(userService.findLikeCount(a.getId()));
            a.setStar(userService.findStarCount(a.getId()));
            return a;
        }).toList();
        List<ArticleVo2> list2 = list1.stream().map(a -> a.setCreateName(articleServiceImpl.findCreateNameByArticleId(a.getId()))).toList();
        return ResponseResult.okResult(new PageVo(list2,page.getTotal()));
    }
}


