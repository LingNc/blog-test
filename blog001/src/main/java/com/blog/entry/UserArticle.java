package com.blog.entry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (UserArticle)表实体类
 *
 * @author makejava
 * @since 2024-09-11 14:44:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shiyi_user_article")
public class UserArticle  {

//用户id
    private Integer uid;

    private Long articleId;



}


