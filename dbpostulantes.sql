-- ****************************** --
-- normalizacion de base de datos --
-- ****************************** --
-- sitio web: https://www.marcossarmiento.com/2017/06/28/normalizacion-de-base-de-datos/
-- 1fn: eliminar datos repetitivos en varias columnas
-- 2fn: eliminar datos redundantes en la tabla principal y ponerlas en otra tabla por pk1 y pk2.
-- 3fn: eliminar columnas que no dependen de la pk en la tabla principal en y ponerlas a otra tabla

-- ALTER TABLE empleos add id_reclutador INT NOT NULL;
-- CREATE INDEX id_reclutador ON empleos (id_reclutador);
-- ALTER TABLE empleos ADD FOREIGN KEY (id_reclutador) REFERENCES reclutadores(id); 

-- # crear base de datos
CREATE DATABASE dbpostulantes; -- dbprueba;

-- # usar base de datos
USE dbpostulantes; -- dbprueba;

-- # crear tabla usuarios
CREATE TABLE usuarios
(
    id INT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    rol ENUM('candidato','reclutador'),
    username VARCHAR(100),
    pwd VARCHAR(250),
    estado ENUM('activo','inactivo'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id)
);

-- # crear tabla personas
CREATE TABLE personas
(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    tipo_documento INT NOT NULL,
    nrodocumento VARCHAR(30) NOT NULL,
    sexo CHAR NOT NULL,
    edad VARCHAR(5),
    telefono VARCHAR(30),
    estado ENUM('activo','inactivo'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id)
);

-- # crear tabla candidatos
CREATE TABLE candidatos
(
    id INT NOT NULL AUTO_INCREMENT,
    id_persona INT NOT NULL,
    id_usuario INT NOT NULL,
    aptitudes TEXT NOT NULL,
    imagen_perfil VARCHAR(250),
    path_curriculum_vitae VARCHAR(250),
    path_certificado_trabajo VARCHAR(250),
    path_antecendente_policial VARCHAR(250),
    estado ENUM('activo','inactivo'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_persona) REFERENCES personas(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- # crear tabla experiencias_laborales
CREATE TABLE experiencias_laborales
(
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(250) NOT NULL,
    descripcion TEXT NOT NULL,
    empresa VARCHAR(100) NOT NULL,
    fecha_inicio VARCHAR(50) NOT NULL,
    fecha_fin VARCHAR(50) NOT NULL,
    estado ENUM('activo','inactivo'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id)
);

-- # crear tabla candidatos_experiencias_laborales
CREATE TABLE candidatos_experiencias_laborales
(
    id_candidato INT NOT NULL,
    id_experiencia_laboral INT NOT NULL,
    orden INT NOT NULL,
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_experiencia_laboral) REFERENCES experiencias_laborales(id)
);

-- # crear tabla reclutadores
CREATE TABLE reclutadores
(
    id INT NOT NULL AUTO_INCREMENT,
    id_persona INT NOT NULL,
    id_usuario INT NOT NULL,
    estado ENUM('activo','inactivo'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_persona) REFERENCES personas(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- # crear tabla empleos
CREATE TABLE empleos
(
    id INT NOT NULL AUTO_INCREMENT,
    id_reclutador INT NOT NULL, -- Deberia ser un id_empresa, pero, lo estamos haciendo sencillo.
    titulo VARCHAR(250) NOT NULL,
    empresa VARCHAR(250) NOT NULL,
    sueldo VARCHAR(30) NOT NULL,
    modalidad ENUM('remoto','presencial'),
    descripcion TEXT NOT NULL,
    estado ENUM('activo','disponible','indisponible','eliminado'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_reclutador) REFERENCES reclutadores(id)
);

-- # crear tabla postulaciones
CREATE TABLE postulaciones
(
    id INT NOT NULL AUTO_INCREMENT,
    id_candidato INT NOT NULL,
    id_empleo INT NOT NULL,
    estado ENUM('postulado','en_proceso','contratado','cancelado','bloqueado'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_empleo) REFERENCES empleos(id)
);

CREATE TABLE blacklist
(
    id_candidato INT NOT NULL,
    id_empleo INT NOT NULL,
    estado ENUM('activo','inactivo','eliminado'),
    fecha_creado DATETIME NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_empleo) REFERENCES empleos(id)
);
-- CREATE INDEX id_candidato ON blacklist (id_candidato);
-- CREATE INDEX id_empleo ON blacklist (id_empleo);

-- usuarios
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado, fecha_creado) 
VALUES ('Alex', 'Quispe', 'candidato', 'alex.quispe@gmail.com', 'candidato2024', 'activo', NOW());
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado, fecha_creado) 
VALUES ('Dante', 'Inigo', 'candidato', 'dante.inigo@gmail.com', 'candidato2024', 'activo', NOW());
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado, fecha_creado) 
VALUES ('Maria', 'Gonzales', 'reclutador', 'maria.gonzales@utp.edu.pe', 'reclutador2024', 'activo', NOW());
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado, fecha_creado) 
VALUES ('Susan', 'Torres', 'reclutador', 'susan.torres@utp.edu.pe', 'reclutador2024', 'activo', NOW());

-- personas / candidatos
-- 001
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad, fecha_creado) 
VALUES ('Alex', 'Quispe', 1, '74567890', 'M', '30', NOW());
INSERT INTO candidatos (id_persona, id_usuario, aptitudes, imagen_perfil, path_curriculum_vitae, path_certificado_trabajo, path_antecendente_policial, estado, fecha_creado) 
VALUES (1, 1, 'java, javascript, php, laravel, docker, aws, vue, git, bash', 'upload/1_perfil.jpg', 'upload/1_cv.pdf', 'upload/1_certif.pdf', 'upload/1_antec.pdf', 'activo', NOW());

-- 002
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad, fecha_creado) 
VALUES ('Dante', 'Inigo', 1, '87653612', 'M', '24', NOW());
INSERT INTO candidatos (id_persona, id_usuario, aptitudes, imagen_perfil, path_curriculum_vitae, path_certificado_trabajo, path_antecendente_policial, estado, fecha_creado) 
VALUES (2, 2, 'javascript, angular, react', 'upload/2_perfil.jpg', 'upload/2_cv.pdf', 'upload/2_certif.pdf', 'upload/2_antec.pdf', 'activo', NOW());

-- personas / reclutadores
-- 001
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad, fecha_creado) 
VALUES ('Maria', 'Gonzales', 1, '12345678', 'F', '30', NOW());
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad, fecha_creado) 
VALUES ('Susan', 'Torres', 1, '93885532', 'F', '27', NOW());

-- 002
INSERT INTO reclutadores (id_persona, id_usuario, estado, fecha_creado) 
VALUES (3, 3, 'activo', NOW());
INSERT INTO reclutadores (id_persona, id_usuario, estado, fecha_creado) 
VALUES (4, 4, 'activo', NOW());

-- experiencias_laborales
INSERT INTO experiencias_laborales (titulo, descripcion, empresa, fecha_inicio, fecha_fin, estado, fecha_creado) 
VALUES ('Desarrollador Backend (NodeJs - AWS)', 'Diseñar, desarrollar e implementar soluciones backend utilizando Node.js.', 'RIMAC', '01/2023', '05/2024', 'activo', NOW());

-- candidatos_experiencias_laborales
INSERT INTO candidatos_experiencias_laborales (id_candidato, id_experiencia_laboral, orden) 
VALUES (1,1,1);

-- empleos
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, estado, fecha_creado)
VALUES (1, 'SR Frontend REACT JS', 'UTP', '5500', 'remoto', 'Encargado de las tareas de frontend con React JS', 'activo', NOW());
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, estado, fecha_creado)
VALUES (2, 'SR Frontend Angular', 'UTP', '7500', 'presencial', 'Encargado de las tareas de frontend con Angular v15', 'activo', NOW());

-- postulaciones
INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado)
VALUES (1, 1, 'postulado', NOW());
INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado)
VALUES (1, 2, 'postulado', NOW());
INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado)
VALUES (2, 1, 'postulado', NOW());


-- blacklist
INSERT INTO blacklist (id_candidato, id_empleo, estado, fecha_creado)
VALUES (1, 1, 'activo', NOW());


