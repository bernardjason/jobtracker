DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

create table users (
    username varchar(50) not null primary key,
    password varchar(120) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username)
);

insert into users(username, password, enabled)values('bernard','{noop}password',true);
insert into authorities(username,authority)values('bernard','ROLE_USER');

/*
https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-format
*/
insert into users(username, password, enabled)values('jason','{noop}password',true);
insert into authorities(username,authority)values('jason','ROLE_USER');





