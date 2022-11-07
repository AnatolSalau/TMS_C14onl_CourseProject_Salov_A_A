CREATE TABLE IF NOT EXISTS user_role (
    id BIGINT NOT NULL PRIMARY KEY,
    role VARCHAR(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS user_role_id_seq
start 1
increment 1;

CREATE TABLE IF NOT EXISTS users (
     id BIGINT NOT NULL PRIMARY KEY,
     first_name   varchar(255) NOT NULL,
     second_name  varchar(255) NOT NULL,
     login varchar(255) NOT NULL,
     password  varchar(255) NOT NULL,
     user_role_id BIGINT NOT NULL,

     CONSTRAINT fk_user_role_id
         FOREIGN KEY(user_role_id)
             REFERENCES user_role(id)
);

CREATE SEQUENCE IF NOT EXISTS users_id_seq
    start 1
    increment 1;

