create table discount_card
(
    id       serial
        constraint discount_card_pk
            primary key,
    discount double precision
);

alter table discount_card
    owner to postgres;

create unique index discount_card_id_uindex
    on discount_card (id);

INSERT INTO public.discount_card (id, discount) VALUES (1, 0.1);
INSERT INTO public.discount_card (id, discount) VALUES (2, 0.05);
