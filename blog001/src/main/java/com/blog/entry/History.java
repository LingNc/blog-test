package com.blog.entry;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (History)表实体类
 *
 * @author makejava
 * @since 2024-09-12 18:15:50
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shiyi_history")
public class History  {


    private Integer uid;

    private Long articleId;

    private Date createTime;



}


