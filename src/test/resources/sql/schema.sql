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
    code varchar(11) not null,
    title varchar(50) not null
);

alter table order_status owner to postgres;

create unique index order_status_title_uindex
    on order_status (title);

create unique index order_status_code_uindex
    on order_status (code);

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
    state_id integer not null
        constraint orders_order_status_id_fk
            references order_status
            on update restrict on delete restrict,
    courier_id integer
        constraint orders_couriers_id_fk
            references couriers
            on update restrict on delete restrict,
    created timestamp with time zone default now() not null,
    updated timestamp with time zone,
    delivery_time timestamp with time zone
);

alter table orders owner to postgres;

create unique index orders_id_uindex
    on orders (id);

create index orders_courier_id_index
    on orders (courier_id);

create index orders_customer_id_index
    on orders (customer_id);

create index orders_state_id_index
    on orders (state_id);

create unique index order_status_code_uindex
    on order_status (code);

create unique index order_status_title_uindex
    on order_status (title);

create table call_center_tasks
(
    id serial not null
        constraint call_center_tasks_pk
            primary key,
    order_id integer not null
        constraint call_center_tasks_orders_id_fk
            references orders,
    created timestamp with time zone default now() not null,
    updated timestamp with time zone,
    done boolean default false not null
);

alter table call_center_tasks owner to postgres;

create function refresh_updated() returns trigger
    language plpgsql
as $$
BEGIN
    NEW.updated := now();
    RETURN NEW;
END;
$$;

alter function refresh_updated() owner to postgres;

create trigger orders_updated_trigger
    before update
    on orders
    for each row
execute procedure refresh_updated();

create trigger call_center_tasks_updated_trigger
    before update
    on call_center_tasks
    for each row
execute procedure refresh_updated();

