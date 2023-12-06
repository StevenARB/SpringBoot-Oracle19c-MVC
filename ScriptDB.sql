CREATE USER C##HospitalExpress IDENTIFIED BY hospitalexpress123 
DEFAULT TABLESPACE USERS 
TEMPORARY TABLESPACE TEMP 
QUOTA UNLIMITED ON USERS;

GRANT DBA TO C##HospitalExpress;

--CREACION DE TABLAS
CREATE TABLE C##HospitalExpress.Usuarios (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR2(255) UNIQUE,
    password VARCHAR2(255),
    rol VARCHAR2(25),
    estado VARCHAR2(25)
);

CREATE TABLE C##HospitalExpress.Pacientes (
    id_paciente INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    nombre VARCHAR2(50) NOT NULL,
    direccion VARCHAR2(255) NOT NULL,
    genero VARCHAR2(50) NOT NULL,
    fecha_nac DATE NOT NULL,
    id_usuario INTEGER NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

CREATE TABLE C##HospitalExpress.Facturas (
    id_factura INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    id_paciente INTEGER NOT NULL,
    total DECIMAL(18,2) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente)
);

--CRUD Usuarios
--CREATE
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

--READ
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_USUARIOS (
    p_cursor OUT SYS_REFCURSOR,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    OPEN p_cursor FOR
        SELECT * FROM usuarios;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: No se encontraron usuarios';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_USUARIO (
    p_username IN VARCHAR2,
    p_id_usuario OUT INTEGER,
    p_rol OUT VARCHAR2,
    p_estado OUT VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT id_usuario, rol, estado
    INTO p_id_usuario, p_rol, p_estado
    FROM usuarios
    WHERE username = p_username;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: Usuario no encontrado';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--UPDATE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_USUARIO (
    p_username IN VARCHAR2,
    p_nuevo_password IN VARCHAR2,
    p_nuevo_rol IN VARCHAR2,
    p_nuevo_estado IN VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    UPDATE usuarios
    SET password = p_nuevo_password,
        rol = p_nuevo_rol,
        estado = p_nuevo_estado
    WHERE username = p_username;

    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Usuario actualizado exitosamente';
    ELSE
        p_resultado := 'ERROR: Usuario no encontrado para actualizar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--DELETE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_USUARIO (
    p_username IN VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    DELETE FROM usuarios
    WHERE username = p_username;

    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Usuario eliminado exitosamente';
    ELSE
        p_resultado := 'ERROR: Usuario no encontrado para eliminar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_USUARIO_PACIENTE (
    p_username IN VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
    v_id_usuario INTEGER;
    
    CURSOR c_usuario IS
        SELECT id_usuario
        FROM usuarios
        WHERE username = p_username;
BEGIN
    --Se obtiene el Id del Usuario mediante un Cursor
    OPEN c_usuario;
    FETCH c_usuario INTO v_id_usuario;
    CLOSE c_usuario;

    IF v_id_usuario IS NOT NULL THEN
        DELETE FROM C##HospitalExpress.Pacientes
        WHERE id_usuario = v_id_usuario;

        DELETE FROM usuarios
        WHERE username = p_username;

        p_resultado := 'EXITO: Usuario y paciente asociado eliminados exitosamente';
    ELSE
        p_resultado := 'ERROR: Usuario no encontrado para eliminar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--CRUD Pacientes
--CREATE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_PACIENTE (
    p_nombre IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_genero IN VARCHAR2,
    p_fecha_nac IN DATE,
    p_id_usuario IN INTEGER,
    p_resultado OUT VARCHAR2
)
AS 
BEGIN
    INSERT INTO
        pacientes (nombre, direccion, genero, fecha_nac, id_usuario)
    VALUES
        (
            p_nombre,
            p_direccion,
            p_genero,
            p_fecha_nac,
            p_id_usuario
        );
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--READ
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_PACIENTE (
    p_id_paciente IN VARCHAR2,
    p_nombre OUT VARCHAR2,
    p_direccion OUT VARCHAR2,
    p_genero OUT VARCHAR2,
    p_fecha_nac OUT VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT nombre, direccion, genero, fecha_nac
    INTO p_nombre, p_direccion, p_genero, p_fecha_nac
    FROM pacientes
    WHERE id_paciente = p_id_paciente;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: Paciente no encontrado';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--UPDATE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_PACIENTE (
    p_id_paciente IN INTEGER,
    p_nuevo_nombre IN VARCHAR2,
    p_nuevo_direccion IN VARCHAR2,
    p_nuevo_genero IN VARCHAR2,
    p_nuevo_fecha_nac IN DATE,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    UPDATE pacientes
    SET nombre = p_nuevo_nombre,
        direccion = p_nuevo_direccion,
        genero = p_nuevo_genero,
        fecha_nac = p_nuevo_fecha_nac
    WHERE id_paciente = p_id_paciente;

    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Paciente actualizado exitosamente';
    ELSE
        p_resultado := 'ERROR: Paciente no encontrado para actualizar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--DELETE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_PACIENTE (
    p_id_paciente IN INTEGER,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
        DELETE FROM C##HospitalExpress.Pacientes
        WHERE id_paciente = p_id_paciente;
    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Paciente eliminado exitosamente';
    ELSE
        p_resultado := 'ERROR: Paciente no encontrado para eliminar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

DECLARE
    resultado VARCHAR2(255);
BEGIN
    C##HospitalExpress.SP_ELIMINAR_PACIENTE(4, resultado);
    DBMS_OUTPUT.PUT_LINE(resultado);
END;

--CRUD Facturas
--CREATE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_FACTURA (
    p_id_paciente IN INTEGER,
    p_total IN DECIMAL,
    p_fecha_hora IN TIMESTAMP,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    INSERT INTO
        Facturas (id_paciente, total, fecha_hora)
    VALUES
        (
            p_id_paciente,
            p_total,
            p_fecha_hora
        );
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--READ
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_FACTURA (
    p_id_factura IN INTEGER,
    p_id_paciente OUT INTEGER,
    p_total OUT DECIMAL,
    p_fecha_hora OUT TIMESTAMP,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT id_paciente, total, fecha_hora
    INTO p_id_paciente, p_total, p_fecha_hora
    FROM Facturas
    WHERE id_factura = p_id_factura;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: La factura ' || p_id_factura || ' no fue encontrada.';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--UPDATE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_FACTURA (
    p_id_factura IN INTEGER,
    p_nuevo_total IN DECIMAL,
    p_nueva_fecha_hora IN TIMESTAMP,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    UPDATE Facturas
    SET total = p_nuevo_total,
        fecha_hora = p_nueva_fecha_hora
    WHERE id_factura = p_id_factura;

    p_resultado := 'Factura Actualizada';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: La factura ' || p_id_factura || ' no fue encontrada en el sistema';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--DELETE
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_FACTURA (
    p_id_factura IN INTEGER,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    DELETE FROM Facturas
    WHERE id_factura = p_id_factura;
    
    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Factura eliminada exitosamente';
    ELSE
        p_resultado := 'ERROR: Factura no encontrada para eliminar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_FACTURAS_PACIENTE (
    p_id_paciente IN INTEGER,
    p_resultado OUT VARCHAR2
) 
AS 
    CURSOR c_facturas IS
        SELECT id_factura
        FROM Facturas
        WHERE id_paciente = p_id_paciente;
    v_id_factura INTEGER;
BEGIN
    OPEN c_facturas;
    LOOP
        FETCH c_facturas INTO v_id_factura;
        EXIT WHEN c_facturas%NOTFOUND;

        DELETE FROM Facturas
        WHERE id_factura = v_id_factura;
    END LOOP;
    CLOSE c_facturas;

    DELETE FROM Pacientes
    WHERE id_paciente = p_id_paciente;

    p_resultado := 'Facturas y Paciente eliminados con �xito';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: El paciente con ID ' || p_id_paciente || ' no fue encontrado en el sistema.';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

--VISTAS
CREATE OR REPLACE VIEW C##HospitalExpress.Vista_Usuarios_Pacientes AS
SELECT
    U.id_usuario,
    U.username,
    U.rol,
    U.estado,
    P.id_paciente,
    P.nombre AS paciente_nombre,
    P.direccion AS paciente_direccion,
    P.genero AS paciente_genero,
    P.fecha_nac AS paciente_fecha_nac
FROM
    Usuarios U
JOIN
    Pacientes P ON U.id_usuario = P.id_usuario;

CREATE OR REPLACE VIEW Vista_Pacientes_Facturas AS
SELECT
    p.id_paciente,
    p.nombre,
    f.id_factura,
    f.total
FROM
    Pacientes p
JOIN
    Facturas f ON p.id_paciente = f.id_paciente;

--FUNCIONES
CREATE OR REPLACE FUNCTION C##HospitalExpress.GET_NUMERO_USUARIOS RETURN INTEGER
AS
    v_numero_usuarios INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_numero_usuarios FROM usuarios;
    RETURN v_numero_usuarios;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;

CREATE OR REPLACE FUNCTION C##HospitalExpress.GET_NUMERO_PACIENTES_MAYORES_60 RETURN INTEGER
AS
    v_numero_pacientes INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_numero_pacientes
    FROM C##HospitalExpress.Pacientes
    WHERE MONTHS_BETWEEN(SYSDATE, fecha_nac) / 12 >= 60;
    
    RETURN v_numero_pacientes;
EXCEPTION
    WHEN OTHERS THEN
        RETURN -1;
END;

CREATE OR REPLACE FUNCTION GET_GANANCIONES_TOTALES RETURN DECIMAL
IS
    ganancias_totales DECIMAL(18, 2);
BEGIN
    SELECT
        SUM(total)
    INTO
        ganancias_totales
    FROM
        Facturas;

    RETURN ganancias_totales;
END;

-------------CITA------------
--TABLA CITA
CREATE TABLE C##HospitalExpress.Cita (
    id_cita INTEGER PRIMARY KEY,
    id_doctor INTEGER,
    id_paciente INTEGER,
    tipo VARCHAR(100),
    fecha_hora DATE,
    estado VARCHAR(100),
    FOREIGN KEY (id_doctor) REFERENCES C##HospitalExpress.Doctor(id_doctor)
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
END;


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
    d_estado IN VARCHAR2,
    d_resultado OUT VARCHAR2
) AS
BEGIN
    INSERT INTO Doctor (nombre, direccion, telefono, estado)
    VALUES (d_nombre, d_direccion, d_telefono, d_estado);

    d_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        d_resultado := 'ERROR: ' || SQLERRM;
END;


CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_DOCTOR (
    d_id IN INT,
    d_nombre IN VARCHAR2,
    d_direccion IN VARCHAR2,
    d_telefono IN VARCHAR2,
    d_estado IN VARCHAR2,
    p_resultado OUT VARCHAR2
) AS
BEGIN
    UPDATE Doctor
    SET nombre = d_nombre,
        direccion = d_direccion,
        telefono = d_telefono,
        estado = d_estado
    WHERE id_doctor = d_id;
    
    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Doctor actualizado exitosamente';
    ELSE
        p_resultado := 'ERROR: Doctor no encontrado para actualizar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;



CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_DOCTOR(

    d_id_doctor IN INT,
    d_nombre OUT VARCHAR2,
    d_direccion OUT VARCHAR2,
    d_telefono OUT VARCHAR2,
    d_estado OUT VARCHAR2,
    d_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT nombre, direccion, telefono, estado
    INTO d_nombre, d_direccion, d_telefono, d_estado
    FROM Doctor
    WHERE id_doctor = d_id_doctor;

    d_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        d_resultado := 'ERROR: Doctor no encontrado';
    WHEN OTHERS THEN
        d_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_DOCTOR (
    d_id IN INT,
    p_resultado OUT VARCHAR2
) AS
BEGIN
    DELETE FROM Doctor WHERE id_doctor = d_id;
    
    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Doctor eliminado exitosamente';
    ELSE
        p_resultado := 'ERROR: Doctor no encontrado para eliminar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;


--STORED PROCEDURES DE DOCTORES
CREATE OR REPLACE PROCEDURE SP_OBTENER_DOCTORES_ESTADO (
    d_estado_doctor IN VARCHAR2
) AS
    d_id INT;
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

CREATE OR REPLACE PROCEDURE SP_CAMBIAR_ESTADO_DOCTOR (
    d_id IN INT,
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
    d_cantidad OUT INT
) AS
BEGIN
    SELECT COUNT(*) INTO d_cantidad
    FROM C##HospitalExpress.Doctor
    WHERE estado = d_estado;
END;

--CURSOR DE DOCTORES
CREATE OR REPLACE PROCEDURE SP_OBTENER_DOCTOR_POR_ID(
    d_id IN INT
)
IS
    d_id_doctor INT;
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
        DBMS_OUTPUT.PUT_LINE('No se encontró el doctor con el ID: ' || TO_CHAR(d_id));
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
    -- Mostrar los resultados directamente (puedes realizar otras acciones aqu�)
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
    -- Realizar la eliminaci�n del medicamento
    DELETE FROM Medicamentos
    WHERE id_medicamento = p_id_medicamento;

    -- Comprobar si se elimin� alg�n registro
    IF SQL%ROWCOUNT > 0 THEN
        DBMS_OUTPUT.PUT_LINE('El medicamento con ID ' || p_id_medicamento || ' ha sido eliminado exitosamente.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('No se encontr� ning�n medicamento con el ID ' || p_id_medicamento);
    END IF;
END BorrarMedicamento;
  
--PRODUCTOS 
 CREATE TABLE C##HospitalExpress.Productos (
    id_producto INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(255),
    Cantidad INT,
    Precio DECIMAL(10, 2)
);
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_PRODUCTOS (
    p_nombre IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_cantidad IN NUMBER,
    p_precio IN DECIMAL,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    INSERT INTO Productos ( Nombre, Descripcion, Cantidad, Precio)
    VALUES (p_nombre, p_descripcion, p_cantidad, p_precio);
 
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;
BEGIN 
    C##HospitalExpress.SP_INSERTAR_PRODUCTOS(
    'Fluoxeritan', 'Antidepresivo', 300, 19.99);
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_PRODUCTOS(
    p_id_producto IN INT,
    p_nombre OUT VARCHAR2,
    p_descripcion OUT VARCHAR2,
    p_cantidad OUT VARCHAR2,
    p_precio OUT VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT Nombre, Descripcion, Cantidad, Precio
    INTO p_nombre, p_descripcion, p_cantidad, p_precio
    FROM Productos
    WHERE id_producto = p_id_producto;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: Producto no encontrado';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;
CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_PRODUCTOS (
    p_id_producto IN INT,
    p_nombre IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_cantidad IN INT,
    p_precio IN DECIMAL,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    UPDATE Productos
    SET Nombre = p_nombre,
        Descripcion = p_descripcion,
        Cantidad = p_cantidad,
        Precio = p_precio
    WHERE id_producto = p_id_producto;
    
    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Producto actualizado exitosamente';
    ELSE
        p_resultado := 'ERROR: Producto no encontrado para actualizar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;



CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_PRODUCTOS (
    p_id_producto IN INT,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    DELETE FROM Productos WHERE id_producto = p_id_producto;
    
    IF SQL%ROWCOUNT > 0 THEN
        p_resultado := 'EXITO: Producto eliminado exitosamente';
    ELSE
        p_resultado := 'ERROR: Producto no encontrado para eliminar';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;


--CURSOR
CREATE OR REPLACE PROCEDURE SP_Obtener_Productos_Precio (
    p_precio_maximo IN DECIMAL
)
AS
    p_id_producto Productos.id_producto%TYPE;
    p_nombre VARCHAR2(100);
    p_descripcion VARCHAR2(255);
    p_cantidad INT;
    p_precio Productos.Precio%TYPE;

    CURSOR cursorProductos IS
        SELECT id_producto, Nombre, Descripcion, Cantidad, Precio
        FROM Productos
        WHERE Precio <= p_precio_maximo;

BEGIN
    OPEN cursorProductos;
    DBMS_OUTPUT.PUT_LINE('Productos con precio menor o igual a ' || TO_CHAR(p_precio_maximo) || ':');
    LOOP
        FETCH cursorProductos INTO p_id_producto, p_nombre, p_descripcion, p_cantidad, p_precio;
        EXIT WHEN cursorProductos%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('ID: ' || TO_CHAR(p_id_producto) || ', Nombre: ' || p_nombre ||
                             ', Descripción: ' || p_descripcion || ', Cantidad: ' || TO_CHAR(p_cantidad) ||
                             ', Precio: ' || TO_CHAR(p_precio));
    END LOOP;

    CLOSE cursorProductos;

END;


--STORED PROCEDURE
CREATE OR REPLACE PROCEDURE SP_Buscar_Productos_Nombre(
    p_nombre_in IN productos.nombre%TYPE
)
IS
    p_id_producto INT;
    p_nombre_producto VARCHAR2(255);
    p_descripcion VARCHAR2(255);
    p_cantidad INT;
    p_precio productos.precio%TYPE;

    CURSOR cursorProductos IS
        SELECT id_producto, nombre, descripcion, cantidad, precio
        FROM productos
        WHERE LOWER(nombre) LIKE '%' || LOWER(p_nombre_in) || '%';

BEGIN
    OPEN cursorProductos;

    DBMS_OUTPUT.PUT_LINE('Productos con nombres que contienen "' || p_nombre_in || '":');
    LOOP
        FETCH cursorProductos INTO p_id_producto, p_nombre_producto, p_descripcion, p_cantidad, p_precio;
        EXIT WHEN cursorProductos%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('ID: ' || TO_CHAR(p_id_producto) || ', Nombre: ' || p_nombre_producto ||
                             ', Descripción: ' || p_descripcion || ', Cantidad: ' || TO_CHAR(p_cantidad) ||
                             ', Precio: ' || TO_CHAR(p_precio));
    END LOOP;
    CLOSE cursorProductos;
END;


--VISTA DE PROCUTOS
CREATE OR REPLACE VIEW VISTAS_PRODUCTOS 
AS
SELECT 
    id_producto,
    nombre,
    descripcion,
    cantidad,
    precio
FROM
    PRODUCTOS;
--FUNCION DE PRODUCTOS
CREATE OR REPLACE FUNCTION FN_OBTENER_PRECIO_PROMEDIO 
RETURN DECIMAL IS p_precio_promedio DECIMAL;
BEGIN
    SELECT AVG(precio) INTO p_precio_promedio 
    FROM PRODUCTOS;
    RETURN p_precio_promedio;
END;

-- SE ALTERO LA TABLA DE DOCTOR Y SE AGREGO UN NUEVO DATO 
ALTER TABLE DOCTOR 
ADD fecha_nacimiento DATE;

--FUNCION DE DOCTOR
CREATE OR REPLACE FUNCTION FN_DOCTORES_MAYORES_30 
RETURN SYS_REFCURSOR AS 
d_cursor SYS_REFCURSOR;
BEGIN
    OPEN d_cursor FOR 
        SELECT id_doctor, NOMBRE, FECHA_NACIMIENTO, ESTADO
        FROM DOCTOR
        WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, FECHA_NACIMIENTO) / 12) >=30;
        
    RETURN d_cursor;
END;

CREATE TABLE Inventario (
    id_inventario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(150),
    descripcion VARCHAR2(255),
    cantidad INT,
    id_producto INT,
    FOREIGN KEY (id_producto) REFERENCES C##HospitalExpress.Productos(id_producto)
);


CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_INVENTARIO (
    p_nombre  IN VARCHAR2,
    p_descripcion  IN VARCHAR2,
    p_cantidad  IN INT,
    p_id_producto  IN INT,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    DECLARE
        i_producto_existente NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_producto_existente
        FROM C##HospitalExpress.Productos
        WHERE id_producto = p_id_producto;

        IF i_producto_existente = 0 THEN
            p_resultado := 'ERROR: El producto no existe.';
            RETURN;
        END IF;
    END;

    -- Insertar en el inventario
    INSERT INTO Inventario (nombre, descripcion, cantidad, id_producto)
    VALUES (p_nombre, p_descripcion, p_cantidad, p_id_producto);
    
    COMMIT;
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_INVENTARIO (
    p_id_inventario IN INT,
    p_nombre OUT VARCHAR2,
    p_descripcion OUT VARCHAR2,
    p_cantidad OUT INT,
    p_id_producto OUT INT,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT nombre, descripcion, cantidad, id_producto
    INTO p_nombre, p_descripcion, p_cantidad, p_id_producto
    FROM Inventario
    WHERE id_inventario = p_id_inventario;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: Producto en inventario no encontrado';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_INVENTARIO (
    p_id_inventario IN NUMBER,
    p_nombre IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_cantidad IN NUMBER,
    p_id_producto IN NUMBER,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    -- Validar si el producto existe antes de actualizar en el inventario
    DECLARE
        v_producto_existente NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_producto_existente
        FROM C##HospitalExpress.Productos
        WHERE id_producto = p_id_producto;

        IF v_producto_existente = 0 THEN
            p_resultado := 'ERROR: El producto no existe.';
            RETURN;
        END IF;
    END;

    -- Actualizar en el inventario
    UPDATE Inventario
    SET nombre = p_nombre,
        descripcion = p_descripcion,
        cantidad = p_cantidad,
        id_producto = p_id_producto
    WHERE id_inventario = p_id_inventario;
    
    COMMIT;
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_INVENTARIO (
    p_id_inventario IN NUMBER,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    -- Eliminar del inventario
    DELETE FROM Inventario WHERE id_inventario = p_id_inventario;
    
    COMMIT;
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;


CREATE TABLE Especialidades(
    id_especialidad INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    descripcion VARCHAR2(255)
);


CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_ESPECIALIDAD (
    p_nombre  IN VARCHAR2,
    p_descripcion  IN VARCHAR2,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    INSERT INTO Especialidades (nombre, descripcion)
    VALUES (p_nombre, p_descripcion);
    
    COMMIT;
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_ESPECIALIDAD (
    p_id_especialidad IN INT,
    p_nombre OUT VARCHAR2,
    p_descripcion OUT VARCHAR2,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT nombre, descripcion
    INTO p_nombre, p_descripcion
    FROM Especialidades
    WHERE id_especialidad = p_id_especialidad;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: Especialidad no encontrada';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ACTUALIZAR_ESPECIALIDAD (
    p_id_especialidad IN NUMBER,
    p_nombre IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    UPDATE Especialidades
    SET nombre = p_nombre,
        descripcion = p_descripcion
    WHERE id_especialidad = p_id_especialidad;
    
    COMMIT;
    p_resultado := 'EXITO: Especialidad actualizada exitosamente';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_ESPECIALIDAD (
    p_id_especialidad IN NUMBER,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    -- Eliminar de especialidades
    DELETE FROM Especialidades WHERE id_especialidad = p_id_especialidad;
    
    COMMIT;
    p_resultado := 'EXITO: Especialidad eliminada exitosamente';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE TABLE doctores_especialidades (
    id_doctor INTEGER,
    id_especialidad INTEGER,
    PRIMARY KEY (id_doctor, id_especialidad),
    FOREIGN KEY (id_doctor) REFERENCES C##HospitalExpress.Doctor(id_doctor),
    FOREIGN KEY (id_especialidad) REFERENCES Especialidades(id_especialidad)
);


CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_INSERTAR_DOCTOR_ESPECIALIDAD (
    p_id_doctor  IN INTEGER,
    p_id_especialidad  IN INTEGER,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    -- Insertar en doctores_especialidades
    INSERT INTO doctores_especialidades (id_doctor, id_especialidad)
    VALUES (p_id_doctor, p_id_especialidad);
    
    COMMIT;
    p_resultado := 'EXITO';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_CONSULTAR_DOCTOR_ESPECIALIDAD (
    p_id_doctor IN INTEGER,
    p_id_especialidad IN INTEGER,
    p_resultado OUT VARCHAR2
) 
AS 
BEGIN
    SELECT 1
    INTO p_resultado
    FROM doctores_especialidades
    WHERE id_doctor = p_id_doctor AND id_especialidad = p_id_especialidad;

    p_resultado := 'EXITO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'ERROR: Relación doctor-especialidad no encontrada';
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;

CREATE OR REPLACE PROCEDURE C##HospitalExpress.SP_ELIMINAR_DOCTOR_ESPECIALIDAD (
    p_id_doctor IN INTEGER,
    p_id_especialidad IN INTEGER,
    p_resultado OUT VARCHAR2
)
AS
BEGIN
    DELETE FROM doctores_especialidades
    WHERE id_doctor = p_id_doctor AND id_especialidad = p_id_especialidad;
    
    COMMIT;
    p_resultado := 'EXITO: Doctor-Especialidad eliminada exitosamente';
EXCEPTION
    WHEN OTHERS THEN
        p_resultado := 'ERROR: ' || SQLERRM;
END;


--VISTA DE INVENTARIO Y PRODUCTO
CREATE VIEW Vista_Productos_Inventario AS
SELECT 
    P.ID_PRODUCTO,
    P.NOMBRE AS NOMBRE_PRODUCTO,
    P.DESCRIPCION AS DESCRIPCION_PRODUCTO,
    P.CANTIDAD AS CANTIDAD_PRODUCTO,
    P.PRECIO,
    I.ID_INVENTARIO,
    I.CANTIDAD AS CANTIDAD_INVENTARIO
FROM PRODUCTOS P
JOIN INVENTARIO I ON P.ID_PRODUCTO = I.ID_PRODUCTO;

--SE AGREGO LA FK DE INVNETARIO A PRODUCTOS
ALTER TABLE PRODUCTOS
ADD id_inventario INT;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT FK_PRODUCTOS_INVENTARIO
FOREIGN KEY (id_inventario) REFERENCES Inventario (id_inventario);


--FUNCION DE ESPECIALIDADES

CREATE OR REPLACE FUNCTION FN_CONTAR_DOCTORES_ESPECIALIDAD(
    d_id_especialidad INT
) RETURN INT AS 
    v_cantidad INT;
BEGIN
    SELECT COUNT(*)
    INTO v_cantidad
    FROM doctores_especialidades
    WHERE id_especialidad = d_id_especialidad;
    
    RETURN v_cantidad;
END;