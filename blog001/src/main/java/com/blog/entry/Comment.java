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
 * (Comment)表实体类
 *
 * @author makejava
 * @since 2024-09-13 14:26:01
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shiyi_comment")
public class Comment  {
@TableId
    private Long id;


    private Long articleId;
//根评论
    private Long rootId;

    private String content;

    private Integer toCommentUserId;

    private Integer toCommentId;

    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
//0为存在
    private Integer delFlag;



}


