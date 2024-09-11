package com.blog.entry.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleVo2 {
    private Long id;

    private String title;

    private String content;

    private Long categoryId;
    //缩略图
    private String thumbnail;

    private Date createTime;

    private Long createBy;

    private String createName;

    private Date updateTime;
    //0为存在
    private Integer delFlag;

    private Long viewCount;

    private Long like;

    private Long star;
}
