SELECT *
FROM patients
LEFT JOIN patient_glucose_level pgl ON patients.glucose_level_id = pgl.id;
