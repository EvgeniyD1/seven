drop table if exists tags_items CASCADE;
drop table if exists tags CASCADE;
drop table if exists comments CASCADE;
drop table if exists type_one CASCADE;
drop table if exists type_two CASCADE;
drop table if exists type_three CASCADE;
drop table if exists type_four CASCADE;
drop table if exists type_five CASCADE;
drop table if exists items CASCADE;
drop table if exists clusters CASCADE;
drop table if exists users CASCADE;


create table users
(
    id       bigint generated by default as identity (maxvalue 2147483647)
        constraint users_pkey
            primary key,
    about    varchar(255),
    email    varchar(50)  not null,
    img_url  varchar(255),
    not_lock boolean      not null,
    password varchar(255) not null,
    role     varchar(20)  not null,
    status   varchar(20)  not null,
    username varchar(20)  not null
);

alter table users
    owner to postgres;

create unique index users_username_uindex
    on users (username);

create table clusters
(
    id          bigint generated by default as identity (maxvalue 2147483647)
        constraint clusters_pkey
            primary key,
    description varchar(255) not null,
    field_one   varchar(30)  not null,
    field_three varchar(30)  not null,
    field_two   varchar(30)  not null,
    fields_type varchar(50)  not null,
    img_url     varchar(255),
    name        varchar(30)  not null,
    topic       varchar(30)  not null,
    user_id     bigint
        constraint fkfk3k2rvkggweqsmcsxxkx3x9b
            references users
);

alter table clusters
    owner to postgres;

create index clusters_name_index
    on clusters (name);

create table items
(
    id          bigint generated by default as identity (maxvalue 2147483647)
        constraint items_pkey
            primary key,
    fields_type varchar(50) not null,
    img_url     varchar(255),
    name        varchar(30) not null,
    cluster_id  bigint
        constraint fksd8bp1oes0kp3r7ekyuxpq49f
            references clusters
            on delete cascade
);

alter table items
    owner to postgres;

create table tags
(
    id   bigint generated by default as identity (maxvalue 2147483647)
        constraint tags_pkey
            primary key,
    name varchar(255) not null
);

alter table tags
    owner to postgres;

create unique index tags_name_uindex
    on tags (name);

create table tags_items
(
    tag_id  bigint not null
        constraint fk2t88ghrum648i367yeoeb9ute
            references tags,
    item_id bigint not null
        constraint fkqxvr024cthxpi86tayh98ss3t
            references items,
    constraint tags_items_pkey
        primary key (tag_id, item_id)
);

alter table tags_items
    owner to postgres;

create table type_one
(
    item_id     bigint not null
        constraint type_one_pkey
            primary key
        constraint fk4ut4wf45jrunmvyo0jp73yb0h
            references items
            on delete cascade,
    field_one   integer,
    field_three integer,
    field_two   integer
);

alter table type_one
    owner to postgres;

create table type_two
(
    item_id     bigint not null
        constraint type_two_pkey
            primary key
        constraint fk705g2ynqbod9gd8mm0trblebs
            references items
            on delete cascade,
    field_one   varchar(30),
    field_three varchar(30),
    field_two   varchar(30)
);

alter table type_two
    owner to postgres;

create table type_three
(
    item_id     bigint not null
        constraint type_three_pkey
            primary key
        constraint fkgefg0ortxm3f4kyjr0u759gvh
            references items
            on delete cascade,
    field_one   varchar(255),
    field_three varchar(255),
    field_two   varchar(255)
);

alter table type_three
    owner to postgres;

create table type_four
(
    item_id     bigint not null
        constraint type_four_pkey
            primary key
        constraint fk5ejnw9x2s9lonqj9x5ba1tox0
            references items
            on delete cascade,
    field_one   boolean,
    field_three boolean,
    field_two   boolean
);

alter table type_four
    owner to postgres;

create table type_five
(
    item_id     bigint not null
        constraint type_five_pkey
            primary key
        constraint fk194qfty3jntbya4coqde0tk3i
            references items
            on delete cascade,
    field_one   timestamp,
    field_three timestamp,
    field_two   timestamp
);

alter table type_five
    owner to postgres;

create table comments
(
    id      bigint generated by default as identity (maxvalue 2147483647)
        constraint comments_pkey
            primary key,
    date    timestamp,
    text    varchar(255) not null,
    item_id bigint
        constraint fkkbkydvf8j8tfuego2iqxntwv2
            references items
            on delete cascade,
    user_id bigint
        constraint fk8omq0tc18jd43bu5tjh6jvraq
            references users
            on delete cascade
);

alter table comments
    owner to postgres;

