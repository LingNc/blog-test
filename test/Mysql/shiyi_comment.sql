create table shiyi_comment
(
    id                 bigint auto_increment
        primary key,
    article_id         bigint       null,
    root_id            bigint       null comment '根评论',
    content            varchar(512) null,
    to_comment_user_id int          null,
    to_comment_id      int          null,
    create_by          int          null,
    create_time        datetime     null,
    del_flag           int          null comment '0为存在'
);

