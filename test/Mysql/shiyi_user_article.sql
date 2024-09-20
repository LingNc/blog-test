create table shiyi_user_article
(
    uid         int      not null comment '用户id',
    article_id  bigint   not null,
    create_time datetime null,
    constraint unique_user_article
        unique (uid, article_id)
);

