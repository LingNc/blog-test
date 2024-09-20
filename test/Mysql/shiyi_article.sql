create table shiyi_article
(
    id          bigint auto_increment
        primary key,
    title       varchar(255) null,
    content     longtext     null,
    category_id bigint       null,
    thumbnail   varchar(255) null comment '缩略图',
    is_top      char         null comment '1为置顶',
    create_time datetime     null,
    create_by   bigint       null,
    update_time datetime     null,
    del_flag    int          null comment '0为存在',
    view_count  bigint       null
);

