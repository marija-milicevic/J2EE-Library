-- phpMyAdmin SQL Dump
-- version 3.4.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 16, 2013 at 11:48 PM
-- Server version: 5.1.59
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `film_library`
--

-- --------------------------------------------------------

--
-- Table structure for table `director`
--

CREATE TABLE IF NOT EXISTS `director` (
  `director_id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dt_of_bth` date DEFAULT NULL,
  `dt_create` date NOT NULL,
  `create_usr_id` int(4) NOT NULL,
  `updt_usr_id` int(4) DEFAULT NULL,
  `dt_updt` date DEFAULT NULL,
  PRIMARY KEY (`director_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `director`
--

INSERT INTO `director` (`director_id`, `name`, `dt_of_bth`, `dt_create`, `create_usr_id`, `updt_usr_id`, `dt_updt`) VALUES
(1, 'Frank Darabont', '1959-09-10', '2013-09-15', 1, 1, '2013-09-16'),
(4, 'Francis Ford Coppola', '1939-05-17', '2013-09-16', 1, NULL, NULL),
(5, 'Sergio Leone', '1929-09-19', '2013-09-16', 1, NULL, NULL),
(6, 'Christopher Nolan', '1970-09-23', '2013-09-16', 1, NULL, NULL),
(7, 'Quentin Tarantino', '1960-07-07', '2013-09-16', 1, NULL, NULL),
(8, 'Billy Wilder', '1905-09-13', '2013-09-16', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE IF NOT EXISTS `film` (
  `film_id` int(4) NOT NULL AUTO_INCREMENT,
  `titl` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cnt` int(3) NOT NULL,
  `director_id` int(4) DEFAULT NULL,
  `dt_creat` date NOT NULL,
  `dt_updt` date DEFAULT NULL,
  `creat_user_id` int(4) NOT NULL,
  `updt_user_id` int(4) DEFAULT NULL,
  `img` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `genre` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `star` int(2) NOT NULL,
  PRIMARY KEY (`film_id`),
  KEY `creat_user_id` (`creat_user_id`),
  KEY `updt_user_id` (`updt_user_id`),
  KEY `director_id` (`director_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`film_id`, `titl`, `cnt`, `director_id`, `dt_creat`, `dt_updt`, `creat_user_id`, `updt_user_id`, `img`, `genre`, `star`) VALUES
(1, 'The Shawshank Redemption', 7, 1, '2013-09-09', NULL, 1, NULL, 'imgFilms/shawshank.jpg', 'Drama', 9),
(2, 'The Green Mile', 12, 4, '2013-09-07', '2013-09-16', 1, 1, 'imgFilms/greenMile.jpg', 'Drama', 8),
(3, 'The Godfather', 5, 4, '2013-09-16', '2013-09-16', 1, 1, 'imgFilms/theGodfather.jpg', 'Crime', 9),
(4, 'The Good, the Bad and the Ugly', 7, 5, '2013-09-16', NULL, 1, NULL, 'imgFilms/goodBadUgly.jpg', 'Western', 9),
(5, 'Memento', 6, 6, '2013-09-16', NULL, 1, NULL, 'imgFilms/memento.jpg', 'Mystery', 8),
(6, 'Django Unchained', 2, 7, '2013-09-16', NULL, 1, NULL, 'imgFilms/djangoFilm.jpg', 'Adventure', 8);

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE IF NOT EXISTS `rent` (
  `film_id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `dt_of_rnt` date NOT NULL,
  `dt_of_ret` date DEFAULT NULL,
  PRIMARY KEY (`film_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(4) NOT NULL AUTO_INCREMENT,
  `role_nm` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_nm`) VALUES
(1, 'administrator'),
(2, 'client');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(4) NOT NULL AUTO_INCREMENT,
  `frst_nm` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `last_nm` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_nm` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `pswd` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sts` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `nbr_of_allow_fm` int(11) DEFAULT NULL,
  `MD5_pswd` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `frst_nm`, `last_nm`, `user_nm`, `pswd`, `sts`, `role_id`, `nbr_of_allow_fm`, `MD5_pswd`, `email`) VALUES
(1, 'Marija', 'Milicevic', 'marija', 'marija', 1, 1, 3, 'cb74c183402afe708a490f0048af6e41', 'marija@gmail.com'),
(2, 'Novak', 'Djokovic', 'nole', 'nole', 1, 2, 5, '76fb0155da47a15975b3211fdcb3fa32', 'nole@gmail.com'),
(3, 'carlos', 'sanchez', 'carl', 'carl', 1, 1, 3, 'a0df931e7a7f9b608c165504bde9b620', 'carl@gmail.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `film`
--
ALTER TABLE `film`
  ADD CONSTRAINT `film_ibfk_1` FOREIGN KEY (`director_id`) REFERENCES `director` (`director_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `film_ibfk_2` FOREIGN KEY (`creat_user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `film_ibfk_3` FOREIGN KEY (`updt_user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE;

--
-- Constraints for table `rent`
--
ALTER TABLE `rent`
  ADD CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`) ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
