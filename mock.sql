-- Insertar valores en la tabla conceptos_uso_puntos
ALTER SEQUENCE public.conceptos_uso_puntos_id_seq RESTART WITH 1;
INSERT INTO conceptos_uso_puntos (descripcion, puntos_requeridos) VALUES
('Compra de productos', 50),
('Descuento en tiendas asociadas', 100),
('Regalo de aniversario', 200),
('Canje de puntos por efectivo', 500),
('Saldo insuficiente', 5000000);

-- Insertar 10 clientes adicionales
ALTER SEQUENCE public.cliente_id_seq RESTART WITH 1;
INSERT INTO cliente (tipo_documento, nombre, apellido, numero_documento, nacionalidad, email, telefono, fecha_nacimiento) VALUES
(1, 'Marcela', 'López', 5555840, 'Paraguayo', 'marcela@example.com', '0987654321', '1992-07-15'),
(2, 'Roberto', 'García', 7654321, 'Paraguayo', 'roberto@example.com', '0998765432', '1997-05-20'),
(1, 'Laura', 'Hernández', 5432109, 'Ecuatoriana', 'laura@example.com', '0987654321', '1994-11-30'),
(2, 'Jorge', 'Martínez', 9876543, 'Peruano', 'jorge@example.com', '0998765432', '1983-09-25'),
(1, 'Carolina', 'Pérez', 6543210, 'Chilena', 'carolina@example.com', '0987654321', '1990-03-12'),
(2, 'Felipe', 'Gómez', 8765432, 'Argentino', 'felipe@example.com', '0998765432', '1986-08-10'),
(1, 'María José', 'Ramírez', 4321098, 'Ecuatoriana', 'mariajose@example.com', '0987654321', '1999-01-05'),
(2, 'Diego', 'Fernández', 7654321, 'Colombiano', 'diego@example.com', '0998765432', '1981-06-20'),
(1, 'Gustavo', 'Herrera', 5432109, 'Ecuatoriano', 'gustavo@example.com', '0987654321', '1996-12-30'),
(2, 'Diana', 'Sánchez', 9876543, 'Argentina', 'luisa@example.com', '0998765432', '1984-04-15');

-- Insertar reglas de asignación de puntos adicionales
ALTER SEQUENCE public.reglas_asignacion_puntos_id_seq RESTART WITH 1;
INSERT INTO reglas_asignacion_puntos (monto_equivalencia) VALUES
(1.5),
(2.0),
(1.8),
(2.3);

-- Insertar vencimientos de puntos adicionales
ALTER SEQUENCE public.vencimientos_puntos_id_seq RESTART WITH 1;
INSERT INTO vencimientos_puntos (fecha_inicio_validez, fecha_fin_validez, dias_duracion_puntaje) VALUES
('2024-01-01', '2024-12-31', 365),
('2024-07-01', '2024-12-31', 183),
('2024-05-01', '2024-12-31', 245),
('2024-06-01', '2024-12-31', 214);

-- Insertar bolsa de puntos adicional para cada cliente
ALTER SEQUENCE public.bolsa_puntos_id_seq RESTART WITH 1;
INSERT INTO bolsa_puntos (cliente_id, fecha_asignacion, fecha_caducidad, puntaje_asignado, puntaje_utilizado, saldo_puntos, monto_operacion) VALUES
(1, '2024-01-01', '2024-12-31', 1000, 0, 1000, 500.50),
(2, '2024-01-01', '2024-12-31', 1500, 200, 1300, 700.75),
(3, '2024-01-01', '2024-12-31', 800, 300, 500, 400.25),
(4, '2024-01-01', '2024-12-31', 1200, 100, 1100, 900.50),
(5, '2024-01-01', '2024-12-31', 2000, 500, 1500, 1200.75),
(6, '2024-01-01', '2024-12-31', 900, 200, 700, 600.25),
(7, '2024-01-01', '2024-12-31', 1300, 400, 900, 800.50),
(8, '2024-01-01', '2024-12-31', 1800, 700, 1100, 1000.75),
(9, '2024-01-01', '2024-12-31', 750, 100, 650, 300.25),
(10, '2024-01-01', '2024-12-31', 1100, 200, 900, 500.50);
