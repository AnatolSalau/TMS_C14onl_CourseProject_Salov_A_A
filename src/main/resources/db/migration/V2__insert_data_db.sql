INSERT INTO user_role (id, role)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_DOCTOR'),
       (3, 'ROLE_USER');

INSERT INTO users (id, first_name, second_name, user_role_id)
VALUES (1, 'firs_name1', 'second_name1', 1),
       (2, 'firs_name2', 'second_name2', 2),
       (3, 'firs_name3', 'second_name3', 3);

