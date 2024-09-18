package com.blog.entry.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;
    //根评论
    @JsonSerialize(using = ToStringSerializer.class)
    private Long rootId;

    private String content;

    private Integer toCommentUserId;

    private Integer toCommentId;

    private Integer createBy;

    private Date createTime;

    private String toCommentUserName;

    private String nickname;

    private List<CommentVo> children;
}
