package com.blog.entry.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {
    private Long id;

    private String title;

    private String content;

    private Long categoryId;
    //缩略图
    private String thumbnail;
    //1为置顶
    private String isTop;

    private Date createTime;

    private Long createBy;

    private Date updateTime;
    //0为存在
    private Integer delFlag;

    private Long viewCount;

    private Long like;

    private Long star;
}
