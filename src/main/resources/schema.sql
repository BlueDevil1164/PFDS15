DROP TABLE IF EXISTS Electronicos;
create table Electronicos (matricula varchar(10), nombre varchar(25), codigo varchar(25), ffac date, precio decimal(10,2), id_categoria int NULL, PRIMARY KEY (matricula)
) AUTO_INCREMENT=90601998;

DROP TABLE IF EXISTS Marcas;
create table Marcas (id int not null AUTO_INCREMENT primary key, nombre varchar(30), rate integer, electronicos_matricula varchar(10)
) AUTO_INCREMENT=80601998;

DROP TABLES IF EXISTS Categorias;
CREATE TABLE Categorias (
  id_categoria int NOT NULL,
  categoria varchar(50) DEFAULT NULL,
  abreviatura varchar(5) DEFAULT NULL,
  PRIMARY KEY (id_categoria),
  UNIQUE KEY estado_UNIQUE (categoria),
  UNIQUE KEY abreviatura_UNIQUE (abreviatura)
) AUTO_INCREMENT=10601998;

DROP TABLES IF EXISTS Proveedores;
CREATE TABLE Proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,  -- ID único para cada proveedor
    proveedor VARCHAR(255) NOT NULL               -- Nombre del proveedor
) AUTO_INCREMENT=20601998;

DROP TABLES IF EXISTS Electronicos_Proveedores;
CREATE TABLE Electronicos_Proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula varchar(10),
    id_proveedor INT,
    anio INT
) AUTO_INCREMENT=30601998;

-- Crear la tabla Compradores
CREATE TABLE Compradores (
    id_comprador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    genero VARCHAR(10) NOT NULL
) AUTO_INCREMENT=40601998;

-- Crear la tabla Vendedores
CREATE TABLE Vendedores (
    id_vendedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    genero VARCHAR(10) NOT NULL
);


-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE `springsecurity`;

-- Volcando estructura para tabla agenda.c_tipo_contacto
DROP TABLE IF EXISTS `cat_contact_type`;
CREATE TABLE IF NOT EXISTS `cat_contact_type` (
  `cct_contact_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `cct_name` varchar(50) NOT NULL,
  `cct_status` varchar(50) NOT NULL,
  PRIMARY KEY (`cct_contact_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla agenda.c_tipo_contacto: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cat_contact_type` DISABLE KEYS */;
INSERT INTO `cat_contact_type` (`cct_contact_type_id`, `cct_name`, `cct_status`) VALUES
	(1, 'Administrador', 'ACTIVO'),
	(2, 'Comprador', 'ACTIVO'),
	(3, 'Vendedor', 'ACTIVO');
/*!40000 ALTER TABLE `cat_contact_type` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
