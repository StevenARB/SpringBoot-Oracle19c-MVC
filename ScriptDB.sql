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

-------------CITA------------
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

--PROCEDIMIENTO ALMACENADO DE CITA INSERTtest
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

    p_resultado := 'Cita eliminada con ï¿½xito';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: La cita ' || p_id_cita || ' no fue encontrada.';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--CURSOR CITA
CREATE OR REPLACE PROCEDURE CONSULTAR_CITA(
    c_id IN NUMBER
)
IS
    -- Declarar el cursor antes del bloque BEGIN
    CURSOR cs_cita IS
        SELECT id_cita, id_doctor, id_paciente, tipo, fecha_hora, estado 
        FROM C##HospitalExpress.Cita
        WHERE id_cita = c_id;
    
    c_id_cita      C##HospitalExpress.Cita.ID_CITA%TYPE;
    c_id_doctor    C##HospitalExpress.Cita.ID_DOCTOR%TYPE;
    c_id_paciente  C##HospitalExpress.Cita.ID_PACIENTE%TYPE;
    c_tipo         C##HospitalExpress.Cita.TIPO%TYPE;
    c_fecha_hora   C##HospitalExpress.Cita.FECHA_HORA%TYPE;
    c_estado       C##HospitalExpress.Cita.ESTADO%TYPE;
BEGIN
    -- Utilizar el cursor declarado
    OPEN cs_cita;

    FETCH cs_cita INTO c_id_cita, c_id_doctor, c_id_paciente, c_tipo, c_fecha_hora, c_estado;

    IF cs_cita%FOUND THEN
        DBMS_OUTPUT.PUT_LINE('ID_CITA: ' || TO_CHAR(c_id_cita) || ', ID_DOCTOR: ' || TO_CHAR(c_id_doctor) 
        || ', ID_PACIENTE: ' || TO_CHAR(c_id_paciente) || ', TIPO: ' || c_tipo 
        || ', FECHA_HORA: ' || TO_CHAR(c_fecha_hora) || ', ESTADO: ' || c_estado);
    ELSE
        DBMS_OUTPUT.PUT_LINE('La cita: ' || TO_CHAR(c_id) || ' no fue encontrada.');
    END IF;
    CLOSE cs_cita;
END CONSULTAR_CITA;

--Vista Cita
CREATE OR REPLACE VIEW Vista_Cita AS
SELECT
    id_cita,
    id_doctor,
    id_paciente,
    tipo,
    fecha_hora,
    estado
FROM
    Cita;

--DOCTORES 
--CRUD

CREATE TABLE C##HospitalExpress.Doctor(

    id_doctor INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(250),
    direccion VARCHAR(250),
    telefono VARCHAR(250),
    estado VARCHAR2(25)
);

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_DOCTOR (
    d_nombre IN VARCHAR2,
    d_direccion IN VARCHAR2,
    d_telefono IN VARCHAR2,
    d_estado IN VARCHAR2
) AS
BEGIN
    INSERT INTO Doctor (nombre, direccion, telefono, estado)
    VALUES (d_nombre, d_direccion, d_telefono, d_estado);
END ;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_DOCTOR (
    d_id IN NUMBER,
    d_nombre IN VARCHAR2,
    d_direccion IN VARCHAR2,
    d_telefono IN VARCHAR2,
    d_estado IN VARCHAR2
) AS
BEGIN
    UPDATE Doctor
    SET nombre = d_nombre,
        direccion = d_direccion,
        telefono = d_telefono,
        estado = d_estado
    WHERE id_doctor = d_id;
END;

BEGIN
    C##HospitalExpress.SP_ACTUALIZAR_DOCTOR(d_id => 2, d_nombre => 'Mario', 
    d_direccion => 'Cartago', d_telefono => '6921-9025', d_estado => 'Inactivo');
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_DOCTOR (
    d_id IN NUMBER
) AS
BEGIN
    DELETE FROM Doctor WHERE id_doctor = d_id;
    COMMIT;
END;

--STORED PROCEDURES DE DOCTORES
CREATE OR REPLACE PROCEDURE SP_OBTENER_DOCTORES_ESTADO (
    d_estado_doctor IN VARCHAR2
) AS
    d_id NUMBER;
    d_nombre VARCHAR2(50);
    d_estado VARCHAR2(10);

    CURSOR c_doctores IS
        SELECT ID_DOCTOR, NOMBRE, ESTADO FROM C##HospitalExpress.Doctor
        WHERE estado = d_estado_doctor;
BEGIN
    OPEN c_doctores;
    
    LOOP 
    FETCH c_doctores INTO d_id, d_nombre, d_estado;
    EXIT WHEN c_doctores%NOTFOUND;
    
    DBMS_OUTPUT.PUT_LINE('ID: ' || d_id || ' Nombre' || d_nombre ||
    ' Estado: '|| d_estado);
    END LOOP;
    CLOSE c_doctores;
END;

CREATE OR REPLACE PROCEDURE SP_INCREMENTAR_PACIENTES_ATENDIDOS (
    d_id IN NUMBER,
    d_cantidad IN NUMBER
) AS
BEGIN
    UPDATE doctores
    SET pacientes_atendidos = pacientes_atendidos + d_cantidad
    WHERE id_doctor = d_id;
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE SP_CAMBIAR_ESTADO_DOCTOR (
    d_id IN NUMBER,
    d_nuevo_estado IN VARCHAR2
) AS
BEGIN
    UPDATE C##HospitalExpress.Doctor
    SET estado = d_nuevo_estado
    WHERE id_doctor = d_id;
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE SP_OBTENER_CANTIDAD_DOCTORES_POR_ESTADO (
    d_estado IN VARCHAR2,
    d_cantidad OUT NUMBER
) AS
BEGIN
    SELECT COUNT(*) INTO d_cantidad
    FROM C##HospitalExpress.Doctor
    WHERE estado = d_estado;
END;

--CURSOR DE DOCTORES
CREATE OR REPLACE PROCEDURE SP_OBTENER_DOCTOR_POR_ID(
    d_id IN NUMBER
)
IS
    d_id_doctor NUMBER;
    d_nombre VARCHAR2(50);
    d_telefono VARCHAR2(55);
    d_estado VARCHAR2(50);

    -- Declarar el cursor
    CURSOR cursor_Doctor IS
        SELECT id_doctor, nombre, telefono, estado 
        FROM C##HospitalExpress.Doctor
        WHERE id_doctor = d_id;
BEGIN
    OPEN cursor_Doctor;

    FETCH cursor_Doctor INTO d_id_doctor, d_nombre, d_telefono, d_estado;

    IF cursor_Doctor%FOUND THEN
        DBMS_OUTPUT.PUT_LINE('ID: ' || TO_CHAR(d_id_doctor) || ', Nombre: ' || d_nombre 
        || ', Telefono: ' || d_telefono || ', Estado: ' || d_estado);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No se encontrÃ³ el doctor con el ID: ' || TO_CHAR(d_id));
    END IF;
    CLOSE cursor_Doctor;
END;
    
--VIWE DE DOCTORES
CREATE OR REPLACE VIEW Vista_Doctores AS
SELECT
    id_doctor,
    nombre,
    direccion,
    telefono,
    estado
FROM
    doctor;
       
CREATE TABLE C##HospitalExpress.Medicamentos (
    id_medicamento INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Dosis VARCHAR(50),
    Cantidad INT,
    Precio DECIMAL(10, 2)
);

CREATE OR REPLACE PROCEDURE C##HospitalExpress.InsertarNuevoMedicamento (
    p_nombre IN VARCHAR2,
    p_dosis IN VARCHAR2,
    p_cantidad IN INT,
    p_precio IN DECIMAL
) AS
BEGIN
    INSERT INTO Medicamentos (Nombre, Dosis, Cantidad, Precio)
    VALUES (p_nombre, p_dosis, p_cantidad, p_precio);
END InsertarNuevoMedicamento;


--Procedimiento almacenado con cursor incluido
CREATE OR REPLACE PROCEDURE C##HospitalExpress.ObtenerMedicamentosPorNombre (
    p_nombre IN VARCHAR2,
    p_dosis IN VARCHAR2,
    p_cantidad IN INT,
    p_precio IN DECIMAL
) AS
BEGIN
    -- Mostrar los resultados directamente (puedes realizar otras acciones aquí)
    FOR medicamento IN (SELECT id_medicamento, Nombre, Dosis, Cantidad, Precio
                        FROM Medicamentos
                        WHERE UPPER(Nombre) = UPPER(p_nombre)
                          AND Dosis = p_dosis
                          AND Cantidad = p_cantidad
                          AND Precio = p_precio) 
    LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || medicamento.id_medicamento || ', Nombre: ' || medicamento.Nombre ||
                             ', Dosis: ' || medicamento.Dosis || ', Cantidad: ' || medicamento.Cantidad ||
                             ', Precio: ' || medicamento.Precio);
    END LOOP;

END ObtenerMedicamentosPorNombre;


CREATE OR REPLACE PROCEDURE C##HospitalExpress.BorrarMedicamento (
    p_id_medicamento IN INT
) AS
BEGIN
    -- Realizar la eliminación del medicamento
    DELETE FROM Medicamentos
    WHERE id_medicamento = p_id_medicamento;

    -- Comprobar si se eliminó algún registro
    IF SQL%ROWCOUNT > 0 THEN
        DBMS_OUTPUT.PUT_LINE('El medicamento con ID ' || p_id_medicamento || ' ha sido eliminado exitosamente.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('No se encontró ningún medicamento con el ID ' || p_id_medicamento);
    END IF;
END BorrarMedicamento;
  