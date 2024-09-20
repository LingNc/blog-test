create table shiyi_user
(
    id        int auto_increment
        primary key,
    user_name varchar(255) not null,
    nick_name varchar(255) not null,
    password  varchar(255) not null,
    avatar    varchar(255) null comment '头像',
    email     varchar(64)  null,
    sex       char         null comment '0为女，1为男'
);

