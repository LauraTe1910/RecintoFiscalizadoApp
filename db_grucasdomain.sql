/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.6.38-log : Database - grucas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`grucas` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `grucas`;

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `rol` */

insert  into `rol`(`id`,`nombre`) values (1,'ADMINISTRADOR'),(2,'CLIENTE'),(3,'COMPRAS'),(4,'REPORTES');

/*Table structure for table `sistema` */

DROP TABLE IF EXISTS `sistema`;

CREATE TABLE `sistema` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `clave_sistema` int(10) DEFAULT NULL,
  `nombre_sistema` varchar(150) DEFAULT NULL,
  `ruta_sistema` varchar(250) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sistema` */

insert  into `sistema`(`id`,`clave_sistema`,`nombre_sistema`,`ruta_sistema`,`descripcion`) values (1,1001,'GRUCAS CHEMOURS',NULL,NULL),(2,1002,'RECINTO FISCALIZADO',NULL,NULL),(3,1003,'SISTEMA DE COMPRAS ',NULL,NULL),(4,1004,'INVENTARIO EQUIPOS',NULL,NULL);

/*Table structure for table `unidad_negocio` */

DROP TABLE IF EXISTS `unidad_negocio`;

CREATE TABLE `unidad_negocio` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(150) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(150) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(25) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `nombre` varchar(150) DEFAULT NULL,
  `domicilio` varchar(200) DEFAULT NULL,
  `pais` varchar(150) DEFAULT NULL,
  `estado` varchar(150) DEFAULT NULL,
  `ciudad` varchar(150) DEFAULT NULL,
  `codigo_postal` varchar(10) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `prefijo` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `unidad_negocio` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(150) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(150) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(25) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `nombre` varchar(30) DEFAULT NULL,
  `apellido_paterno` varchar(60) DEFAULT NULL,
  `apellido_materno` varchar(60) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `empleado_id` int(10) DEFAULT NULL,
  `permisos` varchar(50) DEFAULT NULL,
  `rol` varchar(50) DEFAULT NULL,
  `todas_unidades` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`nombre`,`apellido_paterno`,`apellido_materno`,`password`,`username`,`email`,`tipo`,`empleado_id`,`permisos`,`rol`,`todas_unidades`) values (1,'2018-05-24 10:56:38','2018-05-24 10:56:38',1,'GRUCAS LOGISTICA',1,'DAC TAMPICO',1,'pbenavides',1,'PABLO','BENAVIDES','MOLINA','a','a','pbenavides@grucas.com','FULLACCESS',0,'','ADMINISTRADOR',1),(2,'2018-05-24 10:56:46','2018-05-24 10:56:46',1,'GRUCAS LOGISTICA',1,'DAC TAMPICO',1,'pbenavides',1,'ADMINISTRADOR',' ',' ','dac2001','administrador','pbenavides@grucas.com','FULLACCESS',0,'','ADMINISTRADOR',1);

/*Table structure for table `usuario_sistema` */

DROP TABLE IF EXISTS `usuario_sistema`;

CREATE TABLE `usuario_sistema` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(10) DEFAULT NULL,
  `clave_sistema` int(10) DEFAULT NULL,
  `rol_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `usuario_sistema` */

insert  into `usuario_sistema`(`id`,`usuario_id`,`clave_sistema`,`rol_id`) values (1,1,1001,1),(2,1,1002,1);

/*Table structure for table `usuario_unidad_negocio` */

DROP TABLE IF EXISTS `usuario_unidad_negocio`;

CREATE TABLE `usuario_unidad_negocio` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(10) DEFAULT NULL,
  `unidad_negocio_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usuario_unidad_negocio` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
