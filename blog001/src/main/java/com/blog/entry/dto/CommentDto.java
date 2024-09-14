package com.blog.entry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String articleId;
    private Integer rootId;
    private Integer toCommentId;
    private Integer toCommentUserId;
    private String content;
}
