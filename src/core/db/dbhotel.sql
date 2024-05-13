-- ****************************** --
-- normalizacion de base de datos --
-- ****************************** --
-- sitio web: https://www.marcossarmiento.com/2017/06/28/normalizacion-de-base-de-datos/
-- 1fn: eliminar datos repetitivos en varias columnas
-- 2fn: eliminar datos redundantes en la tabla principal y ponerlas en otra tabla por pk1 y pk2.
-- 3fn: eliminar columnas que no dependen de la pk en la tabla principal en y ponerlas a otra tabla

-- # crear base de datos
CREATE DATABASE dbhotel

-- # usar base de datos
USE dbhotel

-- # crear tabla personas
CREATE TABLE personas
(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    tipo_documento INT NOT NULL,
    nrodocumento VARCHAR(25) NOT NULL,
    sexo CHAR NOT NULL,
    edad VARCHAR(5),
    telefono VARCHAR(25),
    estado VARCHAR(50),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id)
)

-- # crear tabla clientes
CREATE TABLE clientes
(
    id INT NOT NULL AUTO_INCREMENT,
    id_persona INT NOT NULL,
    empresa VARCHAR(250),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_persona) REFERENCES personas(id)
)

-- # crear tabla perfiles
CREATE TABLE perfiles
(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    permisos VARCHAR(250) NOT NULL,
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id)
)

-- # crear tabla empleado
CREATE TABLE empleados
(
    id INT NOT NULL AUTO_INCREMENT,
    id_persona INT NOT NULL,
    id_perfil INT NOT NULL,
    sueldo DECIMAL NOT NULL,
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_persona) REFERENCES personas(id),
    FOREIGN KEY (id_perfil) REFERENCES perfiles(id)
)

-- # crear tabla tipo habitación
CREATE TABLE tipo_habitacion
(
    id INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(250) NOT NULL,
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id)
)

-- # crear tabla habitación
CREATE TABLE habitaciones
(
    id INT NOT NULL AUTO_INCREMENT,
    id_tipohabitacion INT NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    nivel VARCHAR(25) NOT NULL,
    numero_piso VARCHAR(25) NOT NULL,
    precio DECIMAL NOT NULL,
    cantidad_camas INT NOT NULL,
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_tipohabitacion) REFERENCES tipo_habitacion(id)
)

-- # crear tabla producto
CREATE TABLE productos
(
    id INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(250) NOT NULL,
    precio DECIMAL NOT NULL,
    cantidad_stock INT NOT NULL,
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id)
)

-- # crear tabla reservaconsumo
CREATE TABLE reserva_consumos
(
    id_reserva INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(2) NOT NULL,
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME
)

-- # crear tabla reserva
CREATE TABLE reservas
(
    id INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_habitacion INT NOT NULL,
    id_empleado INT NOT NULL,
    monto_total DECIMAL(2) NOT NULL,
    fecha_reserva DATETIME,
    fecha_entrada DATETIME,
    fecha_salida DATETIME,
    estado VARCHAR(50) NOT NULL, -- activo, pendiente pago, pagado, cancelado
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_habitacion) REFERENCES habitaciones(id),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id)
)

-- # Crear tabla comprobante de pago
CREATE TABLE comprobantes
(
    id INT NOT NULL AUTO_INCREMENT,
    id_reserva INT NOT NULL,
    id_empleado INT NOT NULL,
    tipo_comprobante INT NOT NULL, -- 1: factura, 2: boleta
    estado VARCHAR(50) NOT NULL, -- activo, pendiente pago, pagado, cancelado
    fecha_creado DATETIME NOT NULL,
    fecha_pagado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_reserva) REFERENCES reservas(id),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id)
)

-- perfiles
insert into perfiles (id, nombre, permisos, fecha_creado) values (1, 'administrador', '{}', '2023-11-10 18:45:29')
insert into perfiles (id, nombre, permisos, fecha_creado) values (2, 'recepcionista', '{}', '2023-11-09 10:23:51')
insert into perfiles (id, nombre, permisos, fecha_creado) values (3, 'portero', '{}', '2023-11-08 08:51:49')
insert into perfiles (id, nombre, permisos, fecha_creado) values (4, 'conserje', '{}', '2023-11-07 11:01:36')
insert into perfiles (id, nombre, permisos, fecha_creado) values (5, 'personal de limpieza', '{}', '2023-11-06 16:06:02')
insert into perfiles (id, nombre, permisos, fecha_creado) values (6, 'personal de mantenimiento', '{}', '2023-11-05 12:01:20')

-- personas empleado
insert into personas (id, nombre, apellido, tipo_documento, nrodocumento, sexo, edad, fecha_creado) values (1, 'luis', 'torres', 1, '12345678', 'm', '30', '2023-11-10 18:45:29')
insert into empleado (id, id_persona, id_perfil, sueldo, fecha_creado) values (1, 1, 1, 1250.69, '2023-11-10 18:45:29')

-- personas clientes
insert into personas (id, nombre, apellido, tipo_documento, nrodocumento, sexo, edad, fecha_creado) values (2, 'javier', 'retamoso', 1, '74567890', 'm', '30', '2023-11-10 18:45:29')
insert into clientes (id, id_persona, empresa, fecha_creado) values (1, 2, null, '2023-11-10 18:45:29')

-- tipo habitacion
insert into tipo_habitacion (id, descripcion, fecha_creado) values (1, 'clasico', '2023-11-10 18:45:29')

-- habitacion
insert into habitaciones (id, id_tipohabitacion, descripcion, nivel, numero_piso, precio, cantidad_camas, fecha_creado) values (1, 1, 'habitación con agua caliente + tv', '1', '101', 49.50, 1, '2023-11-10 18:45:29')

-- reserva
insert into reservas (id, id_cliente, id_habitacion, monto_total, fecha_reserva, fecha_entrada, fecha_salida, fecha_creado)
  values (1, 1, 1, 89.90, '2023-11-10 18:45:29', '2023-11-10 18:45:29', '2023-11-10 18:45:29', '2023-11-10 18:45:29')

-- producto
insert into productos (id, descripcion, precio, cantidad_stock, fecha_creado) values (1, 'botella de agua cielo', 5, 100, '2023-11-10 18:45:29')

-- reserva consumo
insert into reserva_consumos (id, id_producto, cantidad, precio, fecha_creado) values (1, 1, 3, 5, '2023-11-10 18:45:29')

-- comprobante pago
insert into comprobantes (id, id_reserva, id_empleado, tipo_comprobante, fecha_creado, fecha_pagado, estado) values (1, 1, 1, '1', '2023-11-10 18:45:29', '2023-11-10 18:45:29', 'pagado')

create or alter function dbo.fnclientetienedescuento
    (
    @clienteid
    ) returns boolean
    as
begin
  -- select * from
end

create or alter function sppagarreserva
    (
    @reservaid int
    )
    as
begin
  -- select * from
end