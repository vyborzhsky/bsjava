
create table if not exists person
(
    uid VARCHAR(36) not null
        constraint person_pk
            primary key,
    full_name text NOT NULL
);

create table if not exists contact
(
    id serial not null
        constraint contact_pk
            primary key,
    person_uid char(36) not null references person on update restrict on delete cascade,
    type text not null,
    value text not null
);

create index if not exists contact_uid_type_index
    on contact (person_uid, type);
