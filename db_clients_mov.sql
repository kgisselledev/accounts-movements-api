CREATE DATABASE  IF NOT EXISTS `db_clients_mov` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_clients_mov`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: db_clients_mov
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auditoria_movimientos`
--

DROP TABLE IF EXISTS `auditoria_movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditoria_movimientos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `id_cuenta` int DEFAULT NULL,
  `monto` decimal(38,2) DEFAULT NULL,
  `saldo_actual` decimal(38,2) DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria_movimientos`
--

LOCK TABLES `auditoria_movimientos` WRITE;
/*!40000 ALTER TABLE `auditoria_movimientos` DISABLE KEYS */;
INSERT INTO `auditoria_movimientos` VALUES (1,'2023-11-24',1,575.00,1425.00,'Ahorro'),(2,'2023-11-25',2,0.00,0.00,'Ahorro'),(3,'2023-11-25',2,0.00,0.00,'Ahorro'),(4,'2023-11-25',2,0.00,0.00,'Ahorro'),(5,'2023-11-25',1,575.00,850.00,'Ahorro'),(6,'2023-11-27',3,100.00,200.00,'Corriente'),(7,'2023-11-27',3,100.00,300.00,'Corriente'),(8,'2023-11-27',3,100.00,400.00,'Corriente'),(9,'2023-11-28',4,2000.00,7000.00,'Corriente'),(10,'2023-11-01',5,2000.00,2000.00,'Corriente'),(11,'2023-11-01',5,0.00,2000.00,'Corriente'),(12,'2023-11-15',5,0.00,2000.00,'Corriente'),(13,'2023-11-15',5,5000.00,7000.00,'Corriente'),(14,'2023-11-15',5,5000.00,12000.00,'Corriente'),(15,'2023-11-15',5,5000.00,7000.00,'Ahorro'),(16,'2023-11-15',6,5000.00,5000.00,'Corriente'),(17,'2023-11-15',6,0.00,5000.00,'Corriente'),(18,'2023-11-15',6,0.00,5000.00,'Corriente'),(19,'2023-11-30',7,0.00,0.00,'Ahorro');
/*!40000 ALTER TABLE `auditoria_movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `contraseña` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Otavalo sn y principal',36,'masculino','1785154','Jose Lema','098254785','1234','activa'),(2,'13 junio y Equinoccial',28,'masculino','098874587','Juan Osorio','098254785','1245','activa'),(3,'Amazonas y NNUU',26,'femenino','100002','Marianela Montalvo','098874587','5678','activa'),(4,'Bogota',28,'femenino','100003','Karol Ramirez','5467642313','21545','activa'),(5,'Bogota',28,'femenino','100004','Test','54573','21545','activa'),(6,'Bogota',28,'femenino','100005','Test','54573','21545','activa'),(7,'Bogota',28,'femenino','100006','Test','54573','21545','activa');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_actual` decimal(38,2) DEFAULT NULL,
  `saldo_inicial` decimal(38,2) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (1,'Activo','478758',850.00,2000.00,'Ahorro'),(2,'Activo','495878',150.00,150.00,'Ahorro'),(3,'Activo','225487',400.00,100.00,'Corriente'),(4,'Activo','5467642313',7000.00,5000.00,'Corriente'),(5,'Activo','100004',7000.00,0.00,'Corriente'),(6,'Activo','100005',5000.00,0.00,'Corriente'),(7,'Activo','100005',0.00,0.00,'Ahorros');
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `saldo` decimal(38,2) DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  `valor` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,'2023-11-25',850.00,'Ahorro',575.00),(2,'2023-11-27',200.00,'Corriente',100.00),(4,'2023-11-27',400.00,'Corriente',100.00),(5,'2023-11-28',7000.00,'Corriente',2000.00),(6,'2023-11-01',2000.00,'Corriente',2000.00),(7,'2023-11-01',2000.00,'Corriente',0.00),(8,'2023-11-15',2000.00,'Corriente',0.00),(9,'2023-11-15',7000.00,'Corriente',5000.00),(10,'2023-11-15',12000.00,'Corriente',5000.00),(11,'2023-11-15',7000.00,'Ahorro',5000.00),(12,'2023-11-15',5000.00,'Corriente',5000.00),(13,'2023-11-15',5000.00,'Corriente',0.00),(14,'2023-11-15',5000.00,'Corriente',0.00),(15,'2023-11-30',0.00,'Ahorro',0.00);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_clients_mov'
--

--
-- Dumping routines for database 'db_clients_mov'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-27 15:19:18
