package com.blog.entry;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2024-09-10 21:45:14
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shiyi_article")
public class Article  {
@TableId
    private Long id;


    private String title;

    private String content;

    private Long categoryId;
//缩略图
    private String thumbnail;
//1为置顶
    private String isTop;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
//0为存在
    private Integer delFlag;

    private Long viewCount;



}


