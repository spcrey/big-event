use big_event;

drop table if exists article;
drop table if exists category;
drop table if exists `user`;

create table `user` (
    id int unsigned primary key auto_increment comment "ID",
    username varchar(20) not null unique comment "username",
    `password` varchar(32) comment "password",
    nickname varchar(10) default "" comment "nickname",
    email varchar(128) default "" comment "email",
    user_pic varchar(128) default "" comment "user_pic",
    create_time datetime not null comment "create_time",
    update_time datetime not null comment "update time"
) comment "user_table";

create table category (
    id int unsigned primary key auto_increment comment "ID",
    category_name varchar(32) not null comment "category name",
    category_alias varchar(32) not null comment "category alias",
    create_user int unsigned not null comment "create user",
    create_time datetime not null comment "create time",
    update_time datetime not null comment "update time",
    constraint fk_category_user foreign key (create_user) references `user`(id)
);

create table article (
    id int unsigned primary key auto_increment comment "ID",
    title varchar(30) not null comment "title",
    content varchar(10000) not null comment "content",
    cover_img varchar(128) not null comment "cover img",
    `state` varchar(10) default "draft" comment "state",
    category_id int unsigned comment "category ID",
    create_user int unsigned not null comment "create user",
    create_time datetime not null comment "create time",
    update_time datetime not null comment "update time",
    constraint fk_article_category foreign key (category_id) references category(id),
    constraint fk_article_user foreign key (create_user) references `user`(id)
);
