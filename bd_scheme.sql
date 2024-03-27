 CREATE TABLE IF NOT EXISTS tipo_documento (
    id SERIAL PRIMARY KEY,
    tipo varchar(20)
);

CREATE TABLE IF NOT EXISTS conceptos_uso_puntos (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    puntos_requeridos INT NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    tipo_documento integer NOT NULL,

    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    numero_documento integer NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    fecha_nacimiento DATE,

    CONSTRAINT fk_tipo_documento FOREIGN KEY(tipo_documento) REFERENCES tipo_documento(id)
);

CREATE TABLE IF NOT EXISTS reglas_asignacion_puntos (
    id SERIAL PRIMARY KEY,
    limite_inferior NUMERIC,
    limite_superior NUMERIC,
    monto_equivalencia NUMERIC
);

CREATE TABLE IF NOT EXISTS vencimientos_puntos (
    id SERIAL PRIMARY KEY,
    fecha_inicio_validez DATE NOT NULL,
    fecha_fin_validez DATE NOT NULL,
    dias_duracion_puntaje INT NOT NULL
);

CREATE TABLE IF NOT EXISTS bolsa_puntos (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,

    fecha_asignacion DATE NOT NULL,
    fecha_caducidad DATE NOT NULL,
    puntaje_asignado INT NOT NULL,
    puntaje_utilizado INT DEFAULT 0,
    saldo_puntos NUMERIC,
    monto_operacion NUMERIC,

    CONSTRAINT fk_bolsa_cliente FOREIGN KEY(cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS puntos_usados (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,

    puntaje_utilizado INT NOT NULL,
    fecha DATE NOT NULL,
    concepto VARCHAR(255),

    CONSTRAINT fk_puntos_cliente FOREIGN KEY(cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS detalle_puntos_usados (
    id SERIAL PRIMARY KEY,
    cabecera_id INT NOT NULL,
    bolsa_id INT NOT NULL,

    puntaje_utilizado INT NOT NULL,

    CONSTRAINT fk_cabecera FOREIGN KEY(cabecera_id) REFERENCES puntos_usados(id),
    CONSTRAINT fk_bolsa FOREIGN KEY(bolsa_id) REFERENCES bolsa_puntos(id)
);
