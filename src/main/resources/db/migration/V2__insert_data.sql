/*INSERT INTO roles (id, role)
VALUES  (-1, 'ROLE_ADMIN'),
        (-2, 'ROLE_DOCTOR'),
        (-3, 'ROLE_PATIENT'),
        (-4, 'ROLE_USER');

INSERT INTO users (id, first_name, second_name, login, password)
VALUES (-1, 'admin1', 'admin1', 'admin', '$2a$12$5vbzLqZOecJsvMUxpNHfS.De3iG1c6QktE6ve4JQVhze.3J0ul3NO'),
       (-2, 'doctor1', 'doctor1', 'doctor', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (-3, 'patient1', 'patient1', 'patient', '$2a$12$GgOPGmknV4gBvML3IAzXNe6w24R.0w1L/DQl4riluD3QxoQ9R6jD2'),
       (-4, 'user1', 'user1', 'user', '$2a$12$.hGtJ6AJwK/JzZmv7Leq8OAi1nRcafTzVh063DYIvvwZ1jd8eVnMC');

INSERT INTO users_roles (role_id, user_id)
VALUES (-1,-1),
       (-2,-2),
       (-3,-3),
       (-4,-4);*/

/*
INSERT INTO roles (id, role)
VALUES  (-1, 'ROLE_ADMIN'),
        (-2, 'ROLE_DOCTOR'),
        (-3, 'ROLE_USER');

INSERT INTO roles (id, role)
VALUES  (nextval('role_id_seq'), 'ROLE_ADMIN'),
       (nextval('role_id_seq'), 'ROLE_DOCTOR'),
       (nextval('role_id_seq'), 'ROLE_USER');

INSERT INTO users (id, first_name, second_name, login, password)
VALUES (nextval('users_id_seq'), 'admin1', 'admin1', 'admin', '$2a$12$5vbzLqZOecJsvMUxpNHfS.De3iG1c6QktE6ve4JQVhze.3J0ul3NO'),
       (nextval('users_id_seq'), 'admin1', 'admin1', 'admin1', '$2a$12$5vbzLqZOecJsvMUxpNHfS.De3iG1c6QktE6ve4JQVhze.3J0ul3NO'),
       (nextval('users_id_seq'), 'doctor2', 'doctor2', 'doctor2', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (nextval('users_id_seq'), 'doctor3', 'doctor3', 'doctor3', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (nextval('users_id_seq'), 'patient4', 'patient4', 'patient4', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (nextval('users_id_seq'), 'patient5', 'patient5', 'patient5', '$2a$12$.VvvF0pCn1ofMu6CdGMNjOkcaCl3AkmDjaDYhx6QXCaxvKx2Z7crW'),
       (nextval('users_id_seq'), 'user6', 'user6', 'user6', '$2a$12$.hGtJ6AJwK/JzZmv7Leq8OAi1nRcafTzVh063DYIvvwZ1jd8eVnMC');

INSERT INTO users_roles (role_id, user_id)
VALUES (1,1),
       (2,2),
       (2,3),
       (3,4),
       (3,5),
       (3,6);

INSERT INTO doctors (id, user_id)
VALUES (nextval('doctors_id_seq'),2),
       (nextval('doctors_id_seq'),3);

INSERT INTO patients (id, user_id)
VALUES (nextval('patients_id_seq'),4),
       (nextval('patients_id_seq'),5);

INSERT  INTO doctors_patients (patient_id, doctor_id)
VALUES (1,1),
       (2,1);

*/


