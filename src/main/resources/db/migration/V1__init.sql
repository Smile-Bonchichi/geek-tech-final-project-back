create table basket_product
(
    basket_id  bigint not null,
    product_id bigint not null
);
create table baskets
(
    id         bigserial not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    user_id    bigint    not null,
    primary key (id)
);
create table categories
(
    id         bigserial    not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    name       varchar(255) not null,
    primary key (id)
);
create table images
(
    id         bigserial    not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    type       varchar(255) not null,
    url        varchar(255) not null,
    primary key (id)
);
create table product_category
(
    product_id  bigint not null,
    category_id bigint not null
);
create table product_image
(
    product_id bigint not null,
    image_id   bigint not null
);
create table products
(
    id          bigserial      not null,
    created_at  timestamp(6),
    updated_at  timestamp(6),
    description varchar(255)   not null,
    name        varchar(255)   not null,
    price       numeric(38, 2) not null,
    primary key (id)
);
create table promotional_products
(
    id         bigserial      not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    promotion  numeric(38, 2) not null,
    product_id bigint         not null,
    primary key (id)
);
create table users
(
    id         bigserial    not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    email      varchar(255) not null,
    full_name  varchar(255) not null,
    password   varchar(255) not null,
    pin        varchar(14)  not null,
    role       varchar(255) not null,
    primary key (id)
);
alter table if exists categories add constraint UK_t8o6pivur7nn124jehx7cygw5 unique (name);
alter table if exists images add constraint UK_1geq4ru4qgqmdel0mhpejdsfb unique (url);
alter table if exists images add column user_id bigint not null;
alter table if exists images add constraint FK13ljqfrfwbyvnsdhihwta8cpr foreign key (user_id) references users;
alter table if exists products add constraint UK_o61fmio5yukmmiqgnxf8pnavn unique (name);
alter table if exists users add constraint UK_9d3wgtts126kg64e415t9ojl1 unique (pin);
alter table if exists basket_product add constraint FKlid8i5029oacbjo102guik1gr foreign key (product_id) references products;
alter table if exists basket_product add constraint FKlsxht453trkdlehi9vs119jju foreign key (basket_id) references baskets;
alter table if exists baskets add constraint FK87s17cinc4wkx0taas5nh0s8h foreign key (user_id) references users;
alter table if exists product_category add constraint FKdswxvx2nl2032yjv609r29sdr foreign key (category_id) references categories;
alter table if exists product_category add constraint FK5w81wp3eyugvi2lii94iao3fm foreign key (product_id) references products;
alter table if exists product_image add constraint FKanpsonqgb5uwbw83m3m7phnms foreign key (image_id) references images;
alter table if exists product_image add constraint FK1n91c4vdhw8pa4frngs4qbbvs foreign key (product_id) references products;
alter table if exists promotional_products add constraint FKm3fmkl7twkvdfuechohvjvc5x foreign key (product_id) references products;