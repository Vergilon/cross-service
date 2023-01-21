create extension if not exists "uuid-ossp";

create type shoes_type as enum ('DERBY', 'OXFORDS', 'BROGUES', 'LOAFERS', 'MOCCASINS',
                                'UGG', 'BIKER', 'TIMBERLAND', 'SNEAKERS');

create table if not exists clients (
    id uuid primary key not null default uuid_generate_v4(),
    full_name text,
    email text,
    age int
);

create table if not exists shoes (
    id uuid primary key not null default uuid_generate_v4(),
    name text,
    s_type shoes_type,
    client_id uuid not null,
    constraint fk_clients_shoes foreign key(client_id) references clients(id)
);