CREATE TABLE IF NOT EXISTS roles (
    id BIGINT NOT NULL PRIMARY KEY,
    role VARCHAR(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS role_id_seq
start 1
increment 1;

CREATE TABLE IF NOT EXISTS users (
     id BIGINT NOT NULL PRIMARY KEY,
     first_name   varchar(255) NOT NULL,
     second_name  varchar(255) NOT NULL,
     login varchar(255) NOT NULL,
     password  varchar(255) NOT NULL
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
   id BIGINT NOT NULL PRIMARY KEY,
   user_id BIGINT ,
    CONSTRAINT fk_user_id FOREIGN KEY(id) REFERENCES users(id)
);
CREATE SEQUENCE IF NOT EXISTS doctors_id_seq
start 1
increment 1;