create table shiyi_history
(
    uid         int      not null,
    article_id  int      not null,
    create_time datetime null,
    constraint unique_history
        unique (uid, article_id)
);

