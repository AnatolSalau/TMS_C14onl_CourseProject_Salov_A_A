INSERT INTO patient_glucose_level (id, level_glucose)
VALUES (nextval('patient_glucose_level_id_seq'), 100),
       (nextval('patient_glucose_level_id_seq'), 200),
       (nextval('patient_glucose_level_id_seq'), 300);

INSERT INTO patients (id, first_name, second_name, user_role, glucose_level_id)
VALUES (nextval('patients_id_seq'), 'first_name_1', 'second_name_1', null, 1),
       (nextval('patients_id_seq'), 'first_name_2', 'second_name_2', null, 1),
       (nextval('patients_id_seq'), 'first_name_3', 'second_name_3', null, 2),
       (nextval('patients_id_seq'), 'first_name_4', 'second_name_4', null, 3),
       (nextval('patients_id_seq'), 'first_name_5', 'second_name_5', null, null),
       (nextval('patients_id_seq'), 'first_name_6', 'second_name_6', null, null);

INSERT INTO phones (id, phone_number, patients_id)
VALUES (nextval('phones_id_seq'),'111111111',1);

INSERT INTO doctors (id, first_name, second_name, user_role)
VALUES (nextval('doctors_id_seq'), 'first_name_1', 'second_name_1', null),
       (nextval('doctors_id_seq'), 'first_name_2', 'second_name_2', null),
       (nextval('doctors_id_seq'), 'first_name_3', 'second_name_3', null),
       (nextval('doctors_id_seq'), 'first_name_4', 'second_name_4', null),
       (nextval('doctors_id_seq'), 'first_name_5', 'second_name_5', null),
       (nextval('doctors_id_seq'), 'first_name_6', 'second_name_6', null);


INSERT INTO doctors_patients (doctor_id, patient_id)
VALUES (1,1);