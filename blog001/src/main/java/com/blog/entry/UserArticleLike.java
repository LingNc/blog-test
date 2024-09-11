package com.blog.entry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (UserArticleLike)表实体类
 *
 * @author makejava
 * @since 2024-09-11 14:46:42
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shiyi_user_article_like")
public class UserArticleLike  {


    private Integer uid;

    private Long articleId;



}


