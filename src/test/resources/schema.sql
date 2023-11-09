drop table if exists users;
create table users (
    id bigint not null auto_increment,
    `password` varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

drop table if exists company;
create table company (
    id bigint not null auto_increment,
    company_name varchar(255) not null,
    country varchar(255) not null,
    location varchar(255) not null,
    primary key (id)
);

drop table if exists apply;
create table apply (
    id bigint not null auto_increment,
    post_id bigint, user_id bigint,
    primary key (id)
);

drop table if exists post;
create table post (
    company_id bigint,
    id bigint not null auto_increment,
    `description` varchar(255) not null,
    position varchar(255) not null,
    skill varchar(255) not null,
    primary key (id)
);

drop table if exists post_apply_list;
create table post_apply_list (
    apply_list_id bigint not null,
    post_id bigint not null
);