package com.blog.job;

import com.blog.entry.Article;
import com.blog.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.ArticleService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void updateViewCount() {
        Map<String, Integer> cacheMap = redisCache.getCacheMap("Article:viewCount");
        List<Article> collect = cacheMap.entrySet().stream().map(entry -> {
            return new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue());
        }).toList();
        articleService.updateBatchById(collect);
    }
}
