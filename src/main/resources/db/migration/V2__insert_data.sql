INSERT INTO roles (id, role)
VALUES  (-1, 'ROLE_ADMIN'),
        (-2, 'ROLE_DOCTOR'),
        (-3, 'ROLE_PATIENT'),
        (-4, 'ROLE_USER');

INSERT INTO users (id, first_name, second_name, login, password)
VALUES (-1, 'Admin', 'Admin', 'admin', '$2a$12$5vbzLqZOecJsvMUxpNHfS.De3iG1c6QktE6ve4JQVhze.3J0ul3NO'),
       (-2, 'Doctor', 'Doctor', 'doctor', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (-3, 'Patient', 'Patient', 'patient', '$2a$12$GgOPGmknV4gBvML3IAzXNe6w24R.0w1L/DQl4riluD3QxoQ9R6jD2'),
       (-4, 'User', 'User', 'user', '$2a$12$.hGtJ6AJwK/JzZmv7Leq8OAi1nRcafTzVh063DYIvvwZ1jd8eVnMC');

INSERT INTO users_roles (role_id, user_id)
VALUES (-1,-1),
       (-2,-2),
       (-3,-3),
       (-4,-4);


