create table if not exists users (
    id serial, user_name varchar(256),
    user_pass varchar(256),
    user_email varchar(256),
    primary key (id)
);

create table if not exists messages (
    id serial,
    message varchar(256),
    messageTag varchar(256),
    user_login varchar(256),
    primary key (id)
    );

