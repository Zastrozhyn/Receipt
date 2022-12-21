create table products
(
    id          serial
        constraint products_pk
            primary key,
    name        varchar not null,
    price       double precision,
    promotional boolean default false
);

alter table products
    owner to postgres;

create unique index products_id_uindex
    on products (id);

INSERT INTO public.products (id, name, price, promotional) VALUES (2, 'Bread', 2, false);
INSERT INTO public.products (id, name, price, promotional) VALUES (3, 'Butter', 3, false);
INSERT INTO public.products (id, name, price, promotional) VALUES (6, 'Pizza', 20, false);
INSERT INTO public.products (id, name, price, promotional) VALUES (5, 'Water', 2, true);
INSERT INTO public.products (id, name, price, promotional) VALUES (4, 'Cream', 10, true);
INSERT INTO public.products (id, name, price, promotional) VALUES (1, 'Milk', 1, true);
INSERT INTO public.products (id, name, price, promotional) VALUES (8, 'Vine', 25, false);
INSERT INTO public.products (id, name, price, promotional) VALUES (9, 'Coffe', 7, true);
INSERT INTO public.products (id, name, price, promotional) VALUES (7, 'Coca-cola', 5, true);
