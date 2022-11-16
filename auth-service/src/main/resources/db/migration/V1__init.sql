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




