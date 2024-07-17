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


-- # crear base de datos / ut8mb_general_ci
CREATE DATABASE db_postulantes;

-- # usar base de datos
USE db_postulantes;

-- # crear tabla usuarios
CREATE TABLE usuarios
(
    id INT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    rol ENUM('candidato','reclutador'),
    username VARCHAR(100) NOT NULL,
    pwd VARCHAR(250) NOT NULL,
    estado ENUM('activo','inactivo') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id),
    UNIQUE (username, estado)
);

-- # crear tabla personas
CREATE TABLE personas
(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    tipo_documento INT,
    nrodocumento VARCHAR(30),
    sexo ENUM('F','M'),
    edad VARCHAR(5),
    telefono VARCHAR(30),
    fecha_nacimiento VARCHAR(30),
    estado_civil ENUM('soltero', 'casado', 'viudo') DEFAULT 'soltero',
    estado ENUM('activo','inactivo') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id),
    UNIQUE (tipo_documento, nrodocumento)
);

-- # crear tabla candidatos
CREATE TABLE candidatos
(
    id INT NOT NULL AUTO_INCREMENT,
    id_persona INT NOT NULL,
    id_usuario INT NOT NULL,
    aptitudes VARCHAR(700),
    imagen_perfil VARCHAR(250),
    path_curriculum_vitae VARCHAR(250),
    path_certificado_trabajo VARCHAR(250),
    path_antecendente_policial VARCHAR(250),
    estado ENUM('activo','inactivo') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_persona) REFERENCES personas(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
CREATE INDEX IX_1 ON candidatos (aptitudes);

-- # crear tabla experiencias_laborales
CREATE TABLE experiencias_laborales
(
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(250) NOT NULL,
    descripcion TEXT NOT NULL,
    empresa VARCHAR(100) NOT NULL,
    fecha_inicio VARCHAR(50) NOT NULL,
    fecha_fin VARCHAR(50) NOT NULL,
    estado ENUM('activo','inactivo') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id)
);
CREATE INDEX IX_1 ON experiencias_laborales (titulo);

-- # crear tabla candidatos_experiencias_laborales
CREATE TABLE candidatos_experiencias_laborales
(
    id INT NOT NULL AUTO_INCREMENT,
    id_candidato INT NOT NULL,
    id_experiencia_laboral INT NOT NULL,
    orden INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id_candidato, id_experiencia_laboral),
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_experiencia_laboral) REFERENCES experiencias_laborales(id)
);

-- # crear tabla estudios academicos
CREATE TABLE estudios_academicos
(
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(250) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_inicio VARCHAR(50) NOT NULL,
    fecha_fin VARCHAR(50) NOT NULL,
    grado ENUM('secundaria','tecnico','universitario','maestria','doctorado') DEFAULT 'secundaria',
    estado ENUM('activo','inactivo') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id)
);
CREATE INDEX IX_1 ON estudios_academicos (titulo);

 -- # crear tabla relación candidatos y estudios academicos
CREATE TABLE candidatos_estudios_academicos
(
    id INT NOT NULL AUTO_INCREMENT,
    id_candidato INT NOT NULL,
    id_estudio_academico INT NOT NULL,
    orden INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id_candidato, id_estudio_academico),
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_estudio_academico) REFERENCES estudios_academicos(id)
);

-- # crear tabla reclutadores
CREATE TABLE reclutadores
(
    id INT NOT NULL AUTO_INCREMENT,
    id_persona INT NOT NULL,
    id_usuario INT NOT NULL,
    estado ENUM('activo','inactivo') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
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
    modalidad ENUM('remoto','presencial','hibrido') NOT NULL,
    descripcion TEXT NOT NULL,
    edad_min INT NOT NULL,
    edad_max INT NOT NULL,
    estado ENUM('activo','disponible','indisponible','eliminado','finalizado') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_reclutador) REFERENCES reclutadores(id)
);
CREATE INDEX IX_1 ON empleos (titulo);

-- # crear tabla postulaciones
CREATE TABLE postulaciones
(
    id INT NOT NULL AUTO_INCREMENT,
    id_candidato INT NOT NULL,
    id_empleo INT NOT NULL,
    estado ENUM('postulado','contactado','entrevistado','contratado','cancelado','rechazado','bloqueado') NOT NULL,
    feedback TEXT,
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_empleo) REFERENCES empleos(id)
);
CREATE INDEX IX_1 ON postulaciones (fecha_creado);

CREATE TABLE blacklist
(
    id INT NOT NULL AUTO_INCREMENT,
    id_candidato INT NOT NULL,
    id_reclutador INT NOT NULL,
    estado ENUM('activo','inactivo','eliminado') DEFAULT 'activo',
    fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizado DATETIME,
    fecha_eliminado DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_reclutador) REFERENCES reclutadores(id)
);


-- usuarios
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado) 
VALUES ('Alex', 'Quispe', 'candidato', 'alex.quispe@gmail.com', 'candidato2024', 'activo');
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado) 
VALUES ('Dante', 'Inigo', 'candidato', 'dante.inigo@gmail.com', 'candidato2024', 'activo');
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado) 
VALUES ('Maria', 'Gonzales', 'reclutador', 'maria.gonzales@utp.edu.pe', 'reclutador2024', 'activo');
INSERT INTO usuarios (nombres, apellidos, rol, username, pwd, estado) 
VALUES ('Susan', 'Torres', 'reclutador', 'susan.torres@utp.edu.pe', 'reclutador2024', 'activo');

-- personas / candidatos
-- 001
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad) 
VALUES ('Alex', 'Quispe', 1, '74567890', 'M', '30');
INSERT INTO candidatos (id_persona, id_usuario, aptitudes, imagen_perfil, path_curriculum_vitae, path_certificado_trabajo, path_antecendente_policial, estado) 
VALUES (1, 1, 'java, javascript, php, laravel, docker, aws, vue, git, bash''java, javascript, php, laravel, docker, aws, vue, git, bash', 'upload/1_perfil.jpg', 'upload/1_cv.pdf', 'upload/1_certif.pdf', 'upload/1_antec.pdf', 'activo');

-- 002
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad) 
VALUES ('Dante', 'Inigo', 1, '87653612', 'M', '24');
INSERT INTO candidatos (id_persona, id_usuario, aptitudes, imagen_perfil, path_curriculum_vitae, path_certificado_trabajo, path_antecendente_policial, estado) 
VALUES (2, 2, 'javascript, angular, react', 'upload/2_perfil.jpg', 'upload/2_cv.pdf', 'upload/2_certif.pdf', 'upload/2_antec.pdf', 'activo');

-- personas / reclutadores
-- 001
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad) 
VALUES ('Maria', 'Gonzales', 1, '12345678', 'F', '30');
INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad) 
VALUES ('Susan', 'Torres', 1, '93885532', 'F', '27');

-- 002
INSERT INTO reclutadores (id_persona, id_usuario, estado) 
VALUES (3, 3, 'activo');
INSERT INTO reclutadores (id_persona, id_usuario, estado) 
VALUES (4, 4, 'activo');

-- experiencias_laborales
INSERT INTO experiencias_laborales (titulo, descripcion, empresa, fecha_inicio, fecha_fin, estado) 
VALUES ('Desarrollador Backend (NodeJs - AWS)', 'Diseñar, desarrollar e implementar soluciones backend utilizando Node.js.', 'RIMAC', '01/2023', '05/2024', 'activo');

-- candidatos_experiencias_laborales
INSERT INTO candidatos_experiencias_laborales (id_candidato, id_experiencia_laboral, orden) 
VALUES (1,1,1);

-- estudios_academicos
INSERT INTO estudios_academicos (titulo, descripcion, fecha_inicio, fecha_fin, grado, estado) 
VALUES ('Saco Oliveros', 'Colegio de primaria y secundaria', '01/2010', '06/2015', 'secundaria', 'activo');

-- candidatos_estudios_academicos
INSERT INTO candidatos_estudios_academicos (id_candidato, id_estudio_academico, orden) 
VALUES (1,1,1);

-- empleos
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado)
VALUES (1, 'SR Frontend REACT JS', 'UTP', '5500', 'remoto', 'Encargado de las tareas de frontend con React JS', 18, 35, 'disponible');
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado)
VALUES (2, 'SR Frontend Angular', 'UTP', '7500', 'presencial', 'Encargado de las tareas de frontend con Angular v15', 25, 40, 'disponible');
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado)
VALUES (2, 'Desarrollador Full Stack Node.js', 'UTP', '8000', 'remoto', 'Responsable del desarrollo full stack utilizando Node.js y frameworks relacionados', 26, 45, 'disponible');
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado)
VALUES (2, 'Ingeniero de Software Java Senior', 'UTP', '9000', 'hibrido', 'Encargado del desarrollo de software en Java, liderando proyectos críticos para la empresa', 30, 50, 'disponible');
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado)
VALUES (1, 'Analista de Datos SQL', 'UTP', '7000', 'remoto', 'Responsable del análisis de datos utilizando SQL y herramientas de BI para generar informes y análisis estratégicos', 28, 45, 'disponible');
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado)
VALUES (1, 'Diseñador UI/UX Remoto', 'UTP', '6000', 'hibrido', 'Encargado del diseño de interfaces de usuario y experiencia de usuario, colaborando con equipos de desarrollo', 25, 40, 'disponible');
INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado)
VALUES (1, 'Project Manager IT', 'UTP', '10000', 'presencial', 'Responsable de la gestión de proyectos de TI, asegurando la entrega oportuna y dentro del presupuesto', 30, 50, 'disponible');

-- postulaciones
INSERT INTO postulaciones (id_candidato, id_empleo, estado)
VALUES (1, 1, 'postulado');
INSERT INTO postulaciones (id_candidato, id_empleo, estado)
VALUES (1, 2, 'postulado');
INSERT INTO postulaciones (id_candidato, id_empleo, estado)
VALUES (2, 1, 'postulado');

-- blacklist
INSERT INTO blacklist (id_candidato, id_reclutador, estado)
VALUES (1, 1, 'activo');


