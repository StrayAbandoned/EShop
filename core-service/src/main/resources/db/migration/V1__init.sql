CREATE TABLE products (
    id bigserial primary key,
     title varchar(255) not null ,
      cost decimal(20, 2) not null ,
      created_at timestamp default current_timestamp,
      updated_at timestamp default current_timestamp
                      );

INSERT INTO products (title, cost) values
    ('Milk', 100.50),
    ('Bread', 50.12),
    ('Orange', 30.12),
    ('Kiwi', 107.20),
    ('Potato', 63.10),
    ('Orange', 70.33);


create table orders (
    id bigserial primary key ,
    username varchar(255) not null ,
    total_price decimal(20, 2) not null ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items (
    id bigserial primary key ,
    product_id bigint not null references products(id),
    order_id bigint not null references orders(id) ,
    quantity int not null ,
    cost decimal(20, 2) not null ,
    total_cost decimal(20, 2) not null ,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);



