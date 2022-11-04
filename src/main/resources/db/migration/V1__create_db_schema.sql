create sequence addresses_id_seq;

alter sequence addresses_id_seq owner to postgres;

create sequence admins_id_seq;

alter sequence admins_id_seq owner to postgres;

create sequence cities_id_seq;

alter sequence cities_id_seq owner to postgres;

create sequence doctors_id_seq;

alter sequence doctors_id_seq owner to postgres;

create sequence patients_id_seq;

alter sequence patients_id_seq owner to postgres;

create sequence phones_id_seq;

alter sequence phones_id_seq owner to postgres;

create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

alter table flyway_schema_history
    owner to postgres;

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists admins
(
    id          bigint not null
        primary key,
    first_name  varchar(255),
    second_name varchar(255),
    user_role   integer
);

alter table admins
    owner to postgres;

create table if not exists cities
(
    id        bigint not null
        primary key,
    city_name varchar(255)
);

alter table cities
    owner to postgres;

create table if not exists doctors
(
    id          bigint not null
        primary key,
    first_name  varchar(255),
    second_name varchar(255),
    user_role   integer
);

alter table doctors
    owner to postgres;

create table if not exists patient_glucose_level
(
    id            bigserial
        primary key,
    level_glucose integer
);

alter table patient_glucose_level
    owner to postgres;

create table if not exists patients
(
    id               bigint not null
        primary key,
    first_name       varchar(255),
    second_name      varchar(255),
    user_role        integer,
    glucose_level_id bigint
        constraint fk_glucose_level_id
            references patient_glucose_level
);

alter table patients
    owner to postgres;

create table if not exists addresses
(
    id           bigint not null
        primary key,
    city_name    varchar(255),
    house_number varchar(255),
    street_name  varchar(255),
    patient_id   bigint
        constraint fk_patients_id
            references patients
);

alter table addresses
    owner to postgres;

create table if not exists doctors_patients
(
    doctor_id  bigint not null
        constraint "FK6eb0g28avpr9fgsy9rarsikky"
            references doctors,
    patient_id bigint not null
        constraint "FK352st8qcsr3qjbe4h7mq5xawk"
            references patients,
    primary key (doctor_id, patient_id)
);

alter table doctors_patients
    owner to postgres;

create table if not exists phones
(
    id           bigint not null
        primary key,
    phone_number varchar(255),
    patients_id  bigint
        constraint fk_patients_id
            references patients
);

alter table phones
    owner to postgres;

