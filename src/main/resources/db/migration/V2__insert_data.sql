INSERT INTO roles (id, role)
VALUES  (-1, 'ROLE_ADMIN'),
        (-2, 'ROLE_DOCTOR'),
        (-3, 'ROLE_USER');


INSERT INTO roles (id, role)
VALUES  (1, 'ROLE_ADMIN'),
       (2, 'ROLE_DOCTOR'),
       (3, 'ROLE_USER');

INSERT INTO users (id, first_name, second_name, login, password)
VALUES (1, 'admin1', 'admin1', 'admin', '$2a$12$5vbzLqZOecJsvMUxpNHfS.De3iG1c6QktE6ve4JQVhze.3J0ul3NO'),
       (2, 'doctor2', 'doctor2', 'doctor', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (3, 'doctor3', 'doctor3', 'doctor', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (4, 'patient4', 'patient4', 'doctor', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (5, 'patient5', 'patient5', 'doctor', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (6, 'user6', 'user6', 'user', '$2a$12$.hGtJ6AJwK/JzZmv7Leq8OAi1nRcafTzVh063DYIvvwZ1jd8eVnMC');

INSERT INTO users_roles (role_id, user_id)
VALUES (1,1),
       (2,2),
       (2,3),
       (3,4),
       (3,5),
       (3,6);

INSERT INTO doctors (id, user_id)
VALUES (1,2),
       (2,3);

INSERT INTO patients (id, user_id)
VALUES (1,4),
       (2,5);

INSERT  INTO doctors_patients (patient_id, doctor_id)
VALUES (1,1),
       (2,1);




