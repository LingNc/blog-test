package com.blog.entry.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private String content;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    //缩略图
    private String thumbnail;
    //1为置顶
    private String isTop;

    private Date createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;

    private Date updateTime;
    //0为存在
    private Integer delFlag;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long viewCount;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long like;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long star;
}
