CREATE USER C##HospitalExpress IDENTIFIED BY hospitalexpress123 
DEFAULT TABLESPACE USERS 
TEMPORARY TABLESPACE TEMP 
QUOTA UNLIMITED ON USERS;

GRANT DBA TO C##HospitalExpress;

--CREACION DE TABLAS
CREATE TABLE C##HospitalExpress.Usuarios (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR2(255),
    password VARCHAR2(255),
    rol VARCHAR2(25),
    estado VARCHAR2(25)
);


CREATE TABLE C##HospitalExpress.Ejemplo (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR2(255),
    password VARCHAR2(255),
    rol VARCHAR2(25),
    estado VARCHAR2(25)
);

--TABLA CITA
CREATE TABLE C##HospitalExpress.Cita (
    id_cita INTEGER PRIMARY KEY,
    id_doctor INTEGER,
    id_paciente INTEGER,
    tipo VARCHAR(100),
    fecha_hora DATE,
    estado VARCHAR(100)
);

--CREACION DE PROCEDIMIENTOS
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_USUARIO (
    p_username IN VARCHAR2,
    p_password IN VARCHAR2,
    p_rol IN VARCHAR2,
    p_estado IN VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    INSERT INTO
        usuarios (username, password, rol, estado)
    VALUES
        (
            p_username,
            p_password,
            p_rol,
            p_estado
        );
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--PROCEDIMIENTO ALMACENADO DE CITA INSERT
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_CITA (
    p_id_cita IN INTEGER,
    p_id_doctor IN INTEGER,
    p_id_paciente IN INTEGER,
    p_tipo IN VARCHAR2,
    p_fecha_hora IN DATE,
    p_estado IN VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    INSERT INTO
        Cita (id_cita, id_doctor, id_paciente, tipo, fecha_hora, estado)
    VALUES
        (
            p_id_cita,
            p_id_doctor,
            p_id_paciente,
            p_tipo,
            p_fecha_hora,
            p_estado
        );
    p_resultado := 'Nueva Cita Ingresada';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--PROCEDIMIENTO ALMACENADO DE CITA UPDATE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_CITA (
    p_id_cita IN INTEGER,
    p_nueva_fecha_hora IN VARCHAR2,
    p_nuevo_estado IN VARCHAR2,
    p_resultado OUT VARCHAR2 /**parametro de salida**/
) 
AS 
BEGIN
    UPDATE Cita
    SET fecha_hora = p_nueva_fecha_hora,
        estado = p_nuevo_estado
    WHERE id_cita = p_id_cita;

    p_resultado := 'Cita Actualizada';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: La cita ' || p_id_cita || ' no fue encontrada en el sistema.';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;

--PROCEDIMIENTO ALMACENADO DE CITA READ
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_CITA (
    p_id_cita IN INTEGER,
    p_tipo OUT VARCHAR2,
    p_fecha_hora OUT VARCHAR2,
    p_estado OUT VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT tipo, fecha_hora, estado
    INTO p_tipo, p_fecha_hora, p_estado
    FROM Cita
    WHERE id_cita = p_id_cita;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: La cita ' || p_id_cita || ' no fue encontrada.';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--PROCEDIMIENTO ALMACENADO DE CITA DELETE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_CITA (
    p_id_cita IN INTEGER,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    DELETE FROM Cita
    WHERE id_cita = p_id_cita;

    p_resultado := 'Cita eliminada con �xito';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: La cita ' || p_id_cita || ' no fue encontrada.';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;