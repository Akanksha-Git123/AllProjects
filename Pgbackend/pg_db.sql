-- MySQL dump 10.13  Distrib 9.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: pg_db
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `amenities`
--

DROP TABLE IF EXISTS `amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amenities` (
  `amenity_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`amenity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenities`
--

LOCK TABLES `amenities` WRITE;
/*!40000 ALTER TABLE `amenities` DISABLE KEYS */;
INSERT INTO `amenities` VALUES (1,'WiFi',NULL),(2,'Power Backup',NULL),(3,'Parking',NULL),(4,'TV',NULL),(5,'Heater',NULL),(6,'Water Purefier',NULL),(11,'Bed',NULL),(12,'Shared Bathroom',NULL),(13,'Room Cleaning',NULL),(14,'Wardrobe',NULL),(15,'Study Table',NULL),(16,'Chair',NULL),(17,'Air Conditioning (AC)',NULL),(18,'Attached Bathroom',NULL),(19,'Fan',NULL),(20,'Induction / Stove',NULL),(21,'Refrigerator',NULL),(22,'Electricity Included',NULL),(23,'Washing Machine',NULL),(24,'Ironing Facility',NULL),(25,'Breakfast Included',NULL),(26,'Dinner Included',NULL),(27,'Shared Kitchen',NULL),(28,'Microwave',NULL),(29,'CCTV Surveillance',NULL),(30,'Biometric Access',NULL);
/*!40000 ALTER TABLE `amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `pg_id` int NOT NULL,
  `tenant_id` int NOT NULL,
  `move_in_date` date NOT NULL,
  `move_out_date` date DEFAULT NULL,
  `status` enum('PENDING','CONFIRMED','CANCELLED','COMPLETED') DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `room_type_id` int NOT NULL,
  `payment_id` int DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `pg_id` (`pg_id`),
  KEY `tenant_id` (`tenant_id`),
  KEY `room_type_id` (`room_type_id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`pg_id`) REFERENCES `pg_listings` (`pg_id`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`room_type_id`),
  CONSTRAINT `bookings_ibfk_4` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_notifications`
--

DROP TABLE IF EXISTS `email_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_notifications` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `email_subject` varchar(255) NOT NULL,
  `email_body` text NOT NULL,
  `sent_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('SENT','FAILED') DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `email_notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_notifications`
--

LOCK TABLES `email_notifications` WRITE;
/*!40000 ALTER TABLE `email_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `tenant_id` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_status` enum('PENDING','SUCCESS','FAILED','CANCELED') DEFAULT NULL,
  `payment_method` enum('UPI','CARD','NETBANKING','CASH') DEFAULT NULL,
  `paid_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `tenant_id` (`tenant_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pg_amenities`
--

DROP TABLE IF EXISTS `pg_amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pg_amenities` (
  `pg_id` int NOT NULL,
  `amenity_id` int NOT NULL,
  PRIMARY KEY (`pg_id`,`amenity_id`),
  KEY `amenity_id` (`amenity_id`),
  CONSTRAINT `pg_amenities_ibfk_1` FOREIGN KEY (`pg_id`) REFERENCES `pg_listings` (`pg_id`),
  CONSTRAINT `pg_amenities_ibfk_2` FOREIGN KEY (`amenity_id`) REFERENCES `amenities` (`amenity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pg_amenities`
--

LOCK TABLES `pg_amenities` WRITE;
/*!40000 ALTER TABLE `pg_amenities` DISABLE KEYS */;
/*!40000 ALTER TABLE `pg_amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pg_images`
--

DROP TABLE IF EXISTS `pg_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pg_images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `pg_id` int NOT NULL,
  `image_url` text NOT NULL,
  `uploaded_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `room_type_id` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `pg_id` (`pg_id`),
  CONSTRAINT `pg_images_ibfk_1` FOREIGN KEY (`pg_id`) REFERENCES `pg_listings` (`pg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pg_images`
--

LOCK TABLES `pg_images` WRITE;
/*!40000 ALTER TABLE `pg_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `pg_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pg_listings`
--

DROP TABLE IF EXISTS `pg_listings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pg_listings` (
  `pg_id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int NOT NULL,
  `pg_name` varchar(255) NOT NULL,
  `description` text,
  `address` text NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pincode` varchar(10) NOT NULL,
  `gender_allowed` enum('MALE','FEMALE','ANY') DEFAULT NULL,
  `total_rooms` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pg_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `pg_listings_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pg_listings`
--

LOCK TABLES `pg_listings` WRITE;
/*!40000 ALTER TABLE `pg_listings` DISABLE KEYS */;
INSERT INTO `pg_listings` VALUES (1,6,'Sunshine Residency','A clean and comfortable PG with all modern amenities.','123 MG Road, Near Metro Station','Pune','Maharastra','411009','FEMALE',10,'2025-07-31 10:04:32',NULL),(2,6,'Green Leaf Stay','Affordable and peaceful PG with garden view and Wi-Fi.','45 Baner Road, Opp. Orchid School','Pune','Maharashtra','411045','MALE',15,'2025-07-31 11:01:24',NULL),(3,6,'Sahyadri Pg','Affordable and peaceful PG with garden view and Wi-Fi.','45 Baner Road, Opp. Orchid School','Pune','Maharashtra','411045','FEMALE',15,'2025-07-31 17:21:03',NULL);
/*!40000 ALTER TABLE `pg_listings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `pg_id` int NOT NULL,
  `tenant_id` int NOT NULL,
  `rating` int DEFAULT NULL,
  `review_text` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `pg_id` (`pg_id`),
  KEY `tenant_id` (`tenant_id`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`pg_id`) REFERENCES `pg_listings` (`pg_id`),
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `reviews_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_types`
--

DROP TABLE IF EXISTS `room_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_types` (
  `room_type_id` int NOT NULL AUTO_INCREMENT,
  `pg_id` int NOT NULL,
  `room_category` enum('SINGLE','DOUBLE','TRIPLE','QUAD') NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `total_beds` int NOT NULL,
  `available_beds` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`room_type_id`),
  KEY `pg_id` (`pg_id`),
  CONSTRAINT `room_types_ibfk_1` FOREIGN KEY (`pg_id`) REFERENCES `pg_listings` (`pg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_types`
--

LOCK TABLES `room_types` WRITE;
/*!40000 ALTER TABLE `room_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` text NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `user_type` enum('ADMIN','OWNER','TENANT') NOT NULL,
  `profile_picture` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `notes` text,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Atharva Koli','atharva@gmail.com','$2a$10$M68is3OknSPr6YEH8Uov3ufgH5ygKlIQEBRoZr4vvBPEXIR5keFuq','8805942916','TENANT',NULL,'2025-07-30 03:59:45',1,NULL,NULL),(3,'Priyanka Adhav','adhavpriyanka44@gmail.com','$2a$10$SDnA7IMJb1y/glnwhN/r0O5IvHYU8T9jZelDUpCKGSmcGxi1TVFlq','9322764168','TENANT',NULL,'2025-07-30 04:40:03',1,NULL,NULL),(5,'Sona Shekhawat','shekhawat.sona@gmail.com','$2a$10$RmHKNBnqG/L.ITRyswkg8.l9cw7fUCh1aeJnhlPp4s7m6Q6ThqiJa','9876543210','TENANT',NULL,'2025-07-30 15:38:12',1,NULL,NULL),(6,'Sandhya Koli','sandhya@gmail.com','$2a$10$c5ODTeZayi9ubi9RYWIq1uQAnkrOw/AugMQBViaZwRqtUC7zg6V9K','9876543210','OWNER',NULL,'2025-07-31 09:05:11',1,NULL,NULL),(7,'Samruddhi Koli','samruddhikoli300@gmail.com','$2a$10$gOW6eJtzGmRM4QXGr6Gbd.ggCE1dj4GAqhA2UAZiVt.sbgYwDBvjO','8805942916','ADMIN',NULL,'2025-07-31 10:46:06',1,NULL,NULL),(8,'Akashya Sangitrao','akashya@gmail.com','$2a$10$TiCW1vpKMamnVjiDkkY1I.ObG3vOI9TxcnKhZKb/JVm4da6n3wXS6','8805942916','ADMIN',NULL,'2025-07-31 11:00:12',1,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-01 15:46:56
