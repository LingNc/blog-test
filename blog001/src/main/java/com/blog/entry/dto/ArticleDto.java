package com.blog.entry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Integer deFlag;
    private String isTop;
    private String thumbnail;
    private String categoryId;
}
