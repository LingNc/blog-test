package com.blog.entry.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfoVo {
    private Integer id;
    private String nickName;
    private String avatar;
    private String sex;
    private String email;
    private Integer fan;
    private Integer concern;
}
