package com.blog.entry;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (UserAndUser)表实体类
 *
 * @author makejava
 * @since 2024-09-09 15:47:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shiyi_user_and_user")
public class UserAndUser  {
//用户id(粉丝)
    @TableId
    private Integer uid;

//被关注的用户
    private Integer concernedId;



}


