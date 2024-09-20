create table shiyi_user_article_like
(
    uid         int      not null,
    article_id  bigint   not null,
    create_time datetime null,
    constraint unique_id_article
        unique (uid, article_id)
);

