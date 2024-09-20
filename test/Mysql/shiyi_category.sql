create table shiyi_category
(
    id       bigint auto_increment
        primary key,
    name     varchar(128) null,
    del_flag int          null comment '0为存在'
);

