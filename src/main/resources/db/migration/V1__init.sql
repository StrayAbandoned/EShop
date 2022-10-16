CREATE TABLE products (
    id bigserial primary key,
     title varchar(255),
      cost int,
      created_at timestamp default current_timestamp,
      updated_at timestamp default current_timestamp
                      );

INSERT INTO products (title, cost) values
    ('Milk', 100),
    ('Bread', 50),
    ('Orange', 70);

CREATE TABLE users
(
    id bigserial primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

CREATE TABLE roles
(
    id bigserial primary key,
    name varchar(255) not null
);

CREATE TABLE user_roles
(
    user_id bigint not null references users(id),
    role_id bigint not null references roles(id),
    primary key (user_id, role_id)
);

insert into roles (name) values ( 'ROLE_CUSTOMER' ), ('ROLE_ADMIN');

insert into users (username, password) values
( 'Anastasia' ,  '$2a$12$gtyVLup5SNru7vpScun/RuPC3G0Pw4D6HoBMThXqit0gZn8NEiKtK'),
( 'Ivan' ,  '$2a$12$jCFSIbdS4h.B/8YAC/DMzu9/nIA2MDRMMp4wEFD3GwQ4LdUIS39Ty');

insert into user_roles (user_id, role_id) values ( 1,1 ), (2,2);

create table orders (
    id bigserial primary key ,
    user_id bigint not null references users(id),
    total_price int not null ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items (
    id bigserial primary key ,
    product_id bigint not null references products(id),
    order_id bigint not null references orders(id) ,
    quantity int not null ,
    cost int not null ,
    total_cost int not null ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);



