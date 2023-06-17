CREATE DATABASE  IF NOT EXISTS `bookstore`;
USE `bookstore`;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `title` VARCHAR(45) DEFAULT NULL,
                        `author` VARCHAR(45) DEFAULT NULL,
                        `price` DECIMAL(10, 2) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

LOCK TABLES `book` WRITE;

INSERT INTO `book` VALUES
                       (1,'The Midnight Moon','J.K. Rowling','12.99'),
                       (2,'The Lighthouse Keeper','Stephen King','19.99'),
                       (3,'The Secret Garden','Margaret Atwood','9.99'),
                       (4,'The Lost City','Neil Gaiman','7.99'),
                       (5,'The Red Queens Gambit','Ernest Hemingway','14.99');

UNLOCK TABLES;