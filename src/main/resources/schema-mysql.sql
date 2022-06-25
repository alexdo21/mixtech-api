CREATE DATABASE IF NOT EXISTS `mixtech` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mixtech`;
-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: mixtech
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Creates`
--

DROP TABLE IF EXISTS `Creates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Creates` (
  `uid` bigint(20) NOT NULL,
  `mid` bigint(20) NOT NULL,
  PRIMARY KEY (`uid`,`mid`),
  CONSTRAINT `creates_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `creates_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `matches` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Curates`
--

DROP TABLE IF EXISTS `Curates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Curates` (
  `uid` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  PRIMARY KEY (`uid`,`pid`),
  CONSTRAINT `curates_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `curates_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `playlists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Includes`
--

DROP TABLE IF EXISTS `Includes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Includes` (
  `pid` bigint(20) NOT NULL,
  `sid` varchar(50) NOT NULL,
  PRIMARY KEY (`pid`, `sid`),
  CONSTRAINT `Includes_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `playlists` (`id`),
  CONSTRAINT `Includes_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `songs` (`spotify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Matches`
--

DROP TABLE IF EXISTS `Matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Matches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sid1` varchar(50) DEFAULT NULL,
  `sid2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid1` (`sid1`),
  KEY `sid2` (`sid2`),
  CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`sid1`) REFERENCES `songs` (`spotify_id`),
  CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`sid2`) REFERENCES `songs` (`spotify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Playlists`
--

DROP TABLE IF EXISTS `Playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Playlists` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `spotify_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Songs`
--

DROP TABLE IF EXISTS `Songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Songs` (
  `spotify_id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `album_name` varchar(50) DEFAULT NULL,
  `danceability` double DEFAULT NULL,
  `energy` double DEFAULT NULL,
  `key` int(11) DEFAULT NULL,
  `loudness` double DEFAULT NULL,
  `mode` int(11) DEFAULT NULL,
  `speechiness` double DEFAULT NULL,
  `acousticness` double DEFAULT NULL,
  `instrumentalness` double DEFAULT NULL,
  `liveness` double DEFAULT NULL,
  `valence` double DEFAULT NULL,
  `tempo` double DEFAULT NULL,
  `duration_ms` bigint(20) DEFAULT NULL,
  `time_signature` int(11) DEFAULT NULL,
  `popularity` int(11) DEFAULT NULL,
  `artist_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`spotify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `spotify_id` varchar(45) DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dump completed on 2019-08-15 17:50:45