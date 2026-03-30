INSERT INTO pacientes (nombre, telefono)
VALUES ('Ana Perez', '8888-1111');

INSERT INTO pacientes (nombre, telefono)
VALUES ('Luis Gomez', '8888-2222');

INSERT INTO doctores (nombre, especialidad)
VALUES ('Dr. Rodriguez', 'Medicina General');

INSERT INTO doctores (nombre, especialidad)
VALUES ('Dra. Vargas', 'Pediatria');

INSERT INTO citas (paciente_id, doctor_id, fecha, estado)
VALUES (1, 1, '2026-04-10', 'PENDIENTE');

INSERT INTO citas (paciente_id, doctor_id, fecha, estado)
VALUES (2, 2, '2026-04-11', 'CONFIRMADA');