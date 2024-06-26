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
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    fecha_nacimiento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS reglas_asignacion_puntos (
    id SERIAL PRIMARY KEY,

    monto_equivalencia NUMERIC NOT NULL
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
    saldo_puntos NUMERIC DEFAULT 0,
    monto_operacion NUMERIC,

    CONSTRAINT fk_bolsa_cliente FOREIGN KEY(cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS puntos_usados (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,

    puntaje_utilizado INT NOT NULL,
    fecha DATE NOT NULL,
    concepto INT NOT NULL,

    CONSTRAINT fk_puntos_cliente FOREIGN KEY(cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_puntos_concepto FOREIGN KEY(concepto) REFERENCES conceptos_uso_puntos(id)
);

CREATE TABLE IF NOT EXISTS detalle_puntos_usados (
    id SERIAL PRIMARY KEY,

    puntos_usados_id INT NOT NULL,
    bolsa_id INT NOT NULL,

    CONSTRAINT fk_puntos_usados FOREIGN KEY(puntos_usados_id) REFERENCES puntos_usados(id),
    CONSTRAINT fk_bolsa FOREIGN KEY(bolsa_id) REFERENCES bolsa_puntos(id)
);
