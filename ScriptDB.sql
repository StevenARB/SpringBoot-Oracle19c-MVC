CREATE USER C##HospitalExpress IDENTIFIED BY hospitalexpress123 
DEFAULT TABLESPACE USERS 
TEMPORARY TABLESPACE TEMP 
QUOTA UNLIMITED ON USERS;

GRANT DBA TO C##HospitalExpress;

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