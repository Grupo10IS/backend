CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    numero_documento integer NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    fecha_nacimiento DATE
);

CREATE TABLE tipo_documento (
    id SERIAL PRIMARY KEY,
    tipo varchar(20)
);

CREATE TABLE conceptos_uso_puntos (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    puntos_requeridos INT NOT NULL
);

CREATE TABLE reglas_asignacion_puntos (
    id SERIAL PRIMARY KEY,
    limite_inferior NUMERIC,
    limite_superior NUMERIC,
    monto_equivalencia NUMERIC
);

CREATE TABLE vencimientos_puntos (
    id SERIAL PRIMARY KEY,
    fecha_inicio_validez DATE NOT NULL,
    fecha_fin_validez DATE NOT NULL,
    dias_duracion_puntaje INT NOT NULL
);

CREATE TABLE bolsa_puntos (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha_asignacion DATE NOT NULL,
    fecha_caducidad DATE NOT NULL,
    puntaje_asignado INT NOT NULL,
    puntaje_utilizado INT DEFAULT 0,
    saldo_puntos INT,
    monto_operacion NUMERIC
);

CREATE TABLE puntos_usados (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    puntaje_utilizado INT NOT NULL,
    fecha DATE NOT NULL,
    concepto VARCHAR(255)
);

CREATE TABLE detalle_puntos_usados (
    id SERIAL PRIMARY KEY,
    cabecera_id INT NOT NULL,
    puntaje_utilizado INT NOT NULL,
    bolsa_id INT NOT NULL,
    FOREIGN KEY (cabecera_id) REFERENCES puntos_usados(id),
    FOREIGN KEY (bolsa_id) REFERENCES bolsa_puntos(id)
);
