INSERT INTO user_role (id, role)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_DOCTOR'),
       (3, 'ROLE_USER');

INSERT INTO users (id, first_name, second_name, password, user_role_id)
VALUES (1, 'firs_name1', 'second_name1','$2a$12$5vbzLqZOecJsvMUxpNHfS.De3iG1c6QktE6ve4JQVhze.3J0ul3NO', 1),
       (2, 'firs_name2', 'second_name2','$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW', 2),
       (3, 'firs_name3', 'second_name3','$2a$12$.hGtJ6AJwK/JzZmv7Leq8OAi1nRcafTzVh063DYIvvwZ1jd8eVnMC', 3);

