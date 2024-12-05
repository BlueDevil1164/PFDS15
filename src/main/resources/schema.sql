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
