CREATE TABLE IF NOT EXISTS roles (
    id BIGINT NOT NULL PRIMARY KEY,
    role VARCHAR(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS role_id_seq
start 1
increment 1;

CREATE TABLE IF NOT EXISTS users (
     id BIGINT NOT NULL PRIMARY KEY ,
     first_name   varchar(255) NOT NULL,
     second_name  varchar(255) NOT NULL,
     login varchar(255) NOT NULL UNIQUE,
     password  varchar(255) NOT NULL,
     icon        bytea
);
CREATE SEQUENCE IF NOT EXISTS users_id_seq
    start 1
    increment 1;


CREATE TABLE IF NOT EXISTS users_roles (
   role_id BIGINT NOT NULL,
   user_id BIGINT NOT NULL,
   PRIMARY KEY (role_id,user_id),
    CONSTRAINT fk_role_id FOREIGN KEY(role_id) REFERENCES roles(id),
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS doctors (
   id BIGINT  PRIMARY KEY,
   user_id BIGINT,
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);
CREATE SEQUENCE IF NOT EXISTS doctors_id_seq
start 1
increment 1;

CREATE TABLE IF NOT EXISTS patients (
                                       id BIGINT PRIMARY KEY,
                                       user_id BIGINT,
                                       CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);
CREATE SEQUENCE IF NOT EXISTS patients_id_seq
    start 1
    increment 1;

CREATE TABLE IF NOT EXISTS doctors_patients (
                                           patient_id BIGINT NOT NULL,
                                           doctor_id BIGINT NOT NULL,
                                           PRIMARY KEY (patient_id,doctor_id),
                                           CONSTRAINT fk_patient_id FOREIGN KEY(patient_id) REFERENCES patients(id),
                                           CONSTRAINT fk_doctor_id FOREIGN KEY(doctor_id) REFERENCES doctors(id)
);

create table IF NOT EXISTS sent_messages
(
    id bigint not null primary key,
    text varchar(255) not null,
    author_id bigint constraint fk_author_id references users
);
CREATE SEQUENCE IF NOT EXISTS sent_messages_id_seq
    start 1
    increment 1;

  create table IF NOT EXISTS received_messages
(
    id bigint not null primary key,
    text varchar(255) not null,
    reader_id bigint constraint fk_reader_id references users
);
CREATE SEQUENCE IF NOT EXISTS received_messages_id_seq
    start 1
    increment 1;