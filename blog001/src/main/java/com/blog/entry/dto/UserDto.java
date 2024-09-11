package com.blog.entry.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String password;
    private String nickName;
    private Integer identifyCode;
    private String email;
    private Integer sex;
}
