package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.ResponseResult;
import com.blog.entry.Article;
import com.blog.entry.dto.ArticleDto2;


/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2024-09-10 21:45:14
 */
public interface ArticleService extends IService<Article> {

    ResponseResult articleList(Integer pageNum, Integer pageSize, String title, String categoryId, String nickName);

    ResponseResult getArticleById(Long id);

    ResponseResult sendArticle(ArticleDto2 articleDto2);
}


