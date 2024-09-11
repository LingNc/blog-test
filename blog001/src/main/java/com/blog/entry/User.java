package com.blog.entry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2024-09-08 15:57:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shiyi_user")
public class User  {
    @TableId
    private Integer id;

    private String userName;

    private String nickName;

    private String password;
//头像
    private String avatar;

    private String email;
//0为女，1为男
    private String sex;



}


