/*
SQLyog Ultimate v12.4.0 (64 bit)
MySQL - 8.0.30 : Database - dbpostulantes
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dbpostulantes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dbpostulantes`;

/*Table structure for table `blacklist` */

DROP TABLE IF EXISTS `blacklist`;

CREATE TABLE `blacklist` (
  `id_candidato` int NOT NULL,
  `id_empleo` int NOT NULL,
  `estado` enum('activo','inactivo','eliminado') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  `fecha_eliminado` datetime DEFAULT NULL,
  KEY `id_candidato` (`id_candidato`),
  KEY `id_empleo` (`id_empleo`),
  CONSTRAINT `blacklist_ibfk_1` FOREIGN KEY (`id_candidato`) REFERENCES `candidatos` (`id`),
  CONSTRAINT `blacklist_ibfk_2` FOREIGN KEY (`id_empleo`) REFERENCES `empleos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `blacklist` */

insert  into `blacklist`(`id_candidato`,`id_empleo`,`estado`,`fecha_creado`,`fecha_actualizado`,`fecha_eliminado`) values 
(1,1,'activo','2024-05-12 12:02:24',NULL,NULL);

/*Table structure for table `candidatos` */

DROP TABLE IF EXISTS `candidatos`;

CREATE TABLE `candidatos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_persona` int NOT NULL,
  `id_usuario` int NOT NULL,
  `aptitudes` text NOT NULL,
  `imagen_perfil` varchar(250) DEFAULT NULL,
  `path_curriculum_vitae` varchar(250) DEFAULT NULL,
  `path_certificado_trabajo` varchar(250) DEFAULT NULL,
  `path_antecendente_policial` varchar(250) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `candidatos_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`),
  CONSTRAINT `candidatos_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `candidatos` */

insert  into `candidatos`(`id`,`id_persona`,`id_usuario`,`aptitudes`,`imagen_perfil`,`path_curriculum_vitae`,`path_certificado_trabajo`,`path_antecendente_policial`,`estado`,`fecha_creado`,`fecha_actualizado`) values 
(1,1,1,'java, javascript, php, laravel, docker, aws, vue, git, bash','upload/1_perfil.jpg','upload/1_cv.pdf','upload/1_certif.pdf','upload/1_antec.pdf','activo','2024-05-12 12:02:24',NULL),
(2,2,2,'javascript, angular, react','upload/2_perfil.jpg','upload/2_cv.pdf','upload/2_certif.pdf','upload/2_antec.pdf','activo','2024-05-12 12:02:24',NULL);

/*Table structure for table `candidatos_experiencias_laborales` */

DROP TABLE IF EXISTS `candidatos_experiencias_laborales`;

CREATE TABLE `candidatos_experiencias_laborales` (
  `id_candidato` int NOT NULL,
  `id_experiencia_laboral` int NOT NULL,
  `orden` int NOT NULL,
  KEY `id_candidato` (`id_candidato`),
  KEY `id_experiencia_laboral` (`id_experiencia_laboral`),
  CONSTRAINT `candidatos_experiencias_laborales_ibfk_1` FOREIGN KEY (`id_candidato`) REFERENCES `candidatos` (`id`),
  CONSTRAINT `candidatos_experiencias_laborales_ibfk_2` FOREIGN KEY (`id_experiencia_laboral`) REFERENCES `experiencias_laborales` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `candidatos_experiencias_laborales` */

insert  into `candidatos_experiencias_laborales`(`id_candidato`,`id_experiencia_laboral`,`orden`) values 
(1,1,1);

/*Table structure for table `empleos` */

DROP TABLE IF EXISTS `empleos`;

CREATE TABLE `empleos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_reclutador` int NOT NULL,
  `titulo` varchar(250) NOT NULL,
  `empresa` varchar(250) NOT NULL,
  `sueldo` varchar(30) NOT NULL,
  `modalidad` enum('remoto','presencial') DEFAULT NULL,
  `descripcion` text NOT NULL,
  `estado` enum('activo','disponible','indisponible','eliminado') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  `fecha_eliminado` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_reclutador` (`id_reclutador`),
  CONSTRAINT `empleos_ibfk_1` FOREIGN KEY (`id_reclutador`) REFERENCES `reclutadores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `empleos` */

insert  into `empleos`(`id`,`id_reclutador`,`titulo`,`empresa`,`sueldo`,`modalidad`,`descripcion`,`estado`,`fecha_creado`,`fecha_actualizado`,`fecha_eliminado`) values 
(1,1,'SR Frontend REACT JS','UTP','5500','remoto','Encargado de las tareas de frontend con React JS','activo','2024-05-12 12:02:24',NULL,NULL),
(2,2,'SR Frontend Angular','UTP','7500','presencial','Encargado de las tareas de frontend con Angular v15','activo','2024-05-12 12:02:24',NULL,NULL);

/*Table structure for table `experiencias_laborales` */

DROP TABLE IF EXISTS `experiencias_laborales`;

CREATE TABLE `experiencias_laborales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(250) NOT NULL,
  `descripcion` text NOT NULL,
  `empresa` varchar(100) NOT NULL,
  `fecha_inicio` varchar(50) NOT NULL,
  `fecha_fin` varchar(50) NOT NULL,
  `estado` enum('activo','inactivo') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  `fecha_eliminado` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `experiencias_laborales` */

insert  into `experiencias_laborales`(`id`,`titulo`,`descripcion`,`empresa`,`fecha_inicio`,`fecha_fin`,`estado`,`fecha_creado`,`fecha_actualizado`,`fecha_eliminado`) values 
(1,'Desarrollador Backend (NodeJs - AWS)','Diseñar, desarrollar e implementar soluciones backend utilizando Node.js.','RIMAC','01/2023','05/2024','activo','2024-05-12 12:02:24',NULL,NULL);

/*Table structure for table `personas` */

DROP TABLE IF EXISTS `personas`;

CREATE TABLE `personas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `tipo_documento` int NOT NULL,
  `nrodocumento` varchar(30) NOT NULL,
  `sexo` char(1) NOT NULL,
  `edad` varchar(5) DEFAULT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nrodocumento` (`nrodocumento`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `personas` */

insert  into `personas`(`id`,`nombre`,`apellido`,`tipo_documento`,`nrodocumento`,`sexo`,`edad`,`telefono`,`estado`,`fecha_creado`,`fecha_actualizado`) values 
(1,'Alex','Quispe',1,'74567890','M','30',NULL,NULL,'2024-05-12 12:02:24',NULL),
(2,'Dante','Inigo',1,'87653612','M','24',NULL,NULL,'2024-05-12 12:02:24',NULL),
(3,'Maria','Gonzales',1,'12345678','F','30',NULL,NULL,'2024-05-12 12:02:24',NULL),
(4,'Susan','Torres',1,'93885532','F','27',NULL,NULL,'2024-05-12 12:02:24',NULL);

/*Table structure for table `postulaciones` */

DROP TABLE IF EXISTS `postulaciones`;

CREATE TABLE `postulaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_candidato` int NOT NULL,
  `id_empleo` int NOT NULL,
  `estado` enum('postulado','en_proceso','contratado','cancelado','bloqueado') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  `fecha_eliminado` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_candidato` (`id_candidato`),
  KEY `id_empleo` (`id_empleo`),
  CONSTRAINT `postulaciones_ibfk_1` FOREIGN KEY (`id_candidato`) REFERENCES `candidatos` (`id`),
  CONSTRAINT `postulaciones_ibfk_2` FOREIGN KEY (`id_empleo`) REFERENCES `empleos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `postulaciones` */

insert  into `postulaciones`(`id`,`id_candidato`,`id_empleo`,`estado`,`fecha_creado`,`fecha_actualizado`,`fecha_eliminado`) values 
(1,1,1,'postulado','2024-05-12 12:02:24',NULL,NULL),
(2,2,1,'postulado','2024-05-12 12:02:24',NULL,NULL),
(3,1,2,'postulado','2024-05-14 18:30:07',NULL,NULL);

/*Table structure for table `reclutadores` */

DROP TABLE IF EXISTS `reclutadores`;

CREATE TABLE `reclutadores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_persona` int NOT NULL,
  `id_usuario` int NOT NULL,
  `estado` enum('activo','inactivo') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  `fecha_eliminado` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `reclutadores_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`),
  CONSTRAINT `reclutadores_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `reclutadores` */

insert  into `reclutadores`(`id`,`id_persona`,`id_usuario`,`estado`,`fecha_creado`,`fecha_actualizado`,`fecha_eliminado`) values 
(1,3,3,'activo','2024-05-12 12:02:24',NULL,NULL),
(2,4,4,'activo','2024-05-12 12:02:24',NULL,NULL);

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `rol` enum('candidato','reclutador') DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `pwd` varchar(250) DEFAULT NULL,
  `estado` enum('activo','inactivo') DEFAULT NULL,
  `fecha_creado` datetime NOT NULL,
  `fecha_actualizado` datetime DEFAULT NULL,
  `fecha_eliminado` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `usuarios` */

insert  into `usuarios`(`id`,`nombres`,`apellidos`,`rol`,`username`,`pwd`,`estado`,`fecha_creado`,`fecha_actualizado`,`fecha_eliminado`) values 
(1,'Alex','Quispe','candidato','alex.quispe@gmail.com','candidato2024','activo','2024-05-12 12:02:24',NULL,NULL),
(2,'Dante','Inigo','candidato','dante.inigo@gmail.com','candidato2024','activo','2024-05-12 12:02:24',NULL,NULL),
(3,'Maria','Gonzales','reclutador','maria.gonzales@utp.edu.pe','reclutador2024','activo','2024-05-12 12:02:24',NULL,NULL),
(4,'Susan','Torres','reclutador','susan.torres@utp.edu.pe','reclutador2024','activo','2024-05-12 12:02:24',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
