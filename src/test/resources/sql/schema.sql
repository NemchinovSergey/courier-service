create schema public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table couriers
(
    id serial not null
        constraint couriers_pk
            primary key,
    name varchar(100),
    phone varchar(11) not null
);

alter table couriers owner to postgres;

create table addresses
(
    id serial not null
        constraint addresses_pk
            primary key,
    city_name varchar(30),
    street_name varchar(100) not null,
    house varchar(10),
    building varchar(10),
    flat varchar(10)
);

alter table addresses owner to postgres;

create table customers
(
    id serial not null
        constraint customers_pk
            primary key,
    name varchar(100) not null,
    phone varchar(100),
    email varchar(100)
);

alter table customers owner to postgres;

create table order_status
(
    id serial not null
        constraint order_status_pk
            primary key,
    code varchar(10),
    title varchar(50)
);

alter table order_status owner to postgres;

create table orders
(
    id serial not null
        constraint orders_pk
            primary key,
    address_id integer not null
        constraint orders_addresses_id_fk
            references addresses
            on update restrict on delete restrict,
    customer_id integer not null
        constraint orders_customers_id_fk
            references customers
            on update restrict on delete restrict,
    state integer not null
        constraint orders_order_status_id_fk
            references order_status
            on update restrict on delete restrict,
    courier_id integer
        constraint orders_couriers_id_fk
            references couriers
            on update restrict on delete restrict,
    notes varchar
);

alter table orders owner to postgres;

create unique index orders_id_uindex
    on orders (id);

create unique index order_status_code_uindex
    on order_status (code);

create unique index order_status_title_uindex
    on order_status (title);

