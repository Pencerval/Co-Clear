-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 16-11-2011 a las 10:04:32
-- Versión del servidor: 5.5.8
-- Versión de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `CoClear`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `id_answer` int(11) NOT NULL AUTO_INCREMENT,
  `id_answer_group` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `value_name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `example` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id_answer`),
  KEY `answer_fk_id_answer_group` (`id_answer_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `answer`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `answer_group`
--

CREATE TABLE IF NOT EXISTS `answer_group` (
  `id_answer_group` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_answer_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `answer_group`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `default_group_task`
--

CREATE TABLE IF NOT EXISTS `default_group_task` (
  `id_default_group_task` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `id_user_group` int(11) NOT NULL,
  PRIMARY KEY (`id_default_group_task`),
  KEY `default_group_task_fk_id_task` (`id_task`),
  KEY `default_group_task_fk_id_user_group` (`id_user_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `default_group_task`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `excersice`
--

CREATE TABLE IF NOT EXISTS `excersice` (
  `id_excersice` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `description` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_excersice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `excersice`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exercise_stimulus_map`
--

CREATE TABLE IF NOT EXISTS `exercise_stimulus_map` (
  `id_exercise_stimulus_map` int(11) NOT NULL AUTO_INCREMENT,
  `id_excersice` int(11) NOT NULL,
  `id_stimulus` int(11) NOT NULL,
  PRIMARY KEY (`id_exercise_stimulus_map`),
  KEY `exercise_stimulus_map_fk_id_excersice` (`id_excersice`),
  KEY `exercise_stimulus_map_fk_id_stimulus` (`id_stimulus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `exercise_stimulus_map`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `possible_solution`
--

CREATE TABLE IF NOT EXISTS `possible_solution` (
  `id_possible_solution` int(11) NOT NULL AUTO_INCREMENT,
  `id_excersice` int(11) NOT NULL,
  `id_answer` int(11) NOT NULL,
  `correct` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_possible_solution`),
  KEY `possible_solution_fk_id_excersice` (`id_excersice`),
  KEY `possible_solution_fk_id_answer` (`id_answer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `possible_solution`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `id_result` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_answer` int(11) NOT NULL,
  `id_task_exercise` int(11) NOT NULL,
  PRIMARY KEY (`id_result`),
  KEY `result_fk_id_user` (`id_user`),
  KEY `result_fk_id_answer` (`id_answer`),
  KEY `result_fk_id_task_exercise` (`id_task_exercise`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `result`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stimulus`
--

CREATE TABLE IF NOT EXISTS `stimulus` (
  `id_stimulus` int(11) NOT NULL AUTO_INCREMENT,
  `id_stimulus_group` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `path` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_stimulus`),
  KEY `stimulus_fk_id_stimulus_group` (`id_stimulus_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `stimulus`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stimulus_group`
--

CREATE TABLE IF NOT EXISTS `stimulus_group` (
  `id_stimulus_group` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_stimulus_group`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `repeatable` int(1) NOT NULL DEFAULT '0',
  `is_user_default` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `task`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `task_exercise`
--

CREATE TABLE IF NOT EXISTS `task_exercise` (
  `id_task_exercise` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(11) NOT NULL,
  `id_excersice` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id_task_exercise`),
  KEY `task_exercise_fk_id_task` (`id_task`),
  KEY `task_exercise_fk_id_excersice` (`id_excersice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `task_exercise`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) CHARACTER SET latin1 NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `gender` int(1) DEFAULT '1',
  `is_admin` int(1) NOT NULL DEFAULT '0',
  `hearing_loss_date` date DEFAULT NULL,
  `cause_deafnes` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `degree_deafness` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `duration_hearing_loss` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `implantation_date` date DEFAULT NULL,
  `cochlear_implant_type` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `cochlear_implant_model` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `sign_language` int(1) DEFAULT NULL,
  `sign_language_years` int(2) DEFAULT NULL,
  `speech_reading` int(1) DEFAULT NULL,
  `speech_reading_years` int(2) DEFAULT NULL,
  `speech_therapy` int(1) DEFAULT NULL,
  `speech_therapy_years` int(2) DEFAULT NULL,
  `educational_orientation` int(1) DEFAULT NULL,
  `educational_orientation_years` int(2) DEFAULT NULL,
  `bilingual` int(1) DEFAULT NULL,
  `languages` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `home_languages` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `learning_lenguages` int(1) DEFAULT NULL,
  `learning_lenguages_list` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `place_years_residence` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `place_years_residence_mother` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `place_years_residence_father` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `comments` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `admin_comments` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

--
-- Estructura de tabla para la tabla `user_group`
--

CREATE TABLE IF NOT EXISTS `user_group` (
  `id_user_group` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `is_default` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_user_group`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_group_map`
--

CREATE TABLE IF NOT EXISTS `user_group_map` (
  `id_user_group_map` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_user_group` int(11) NOT NULL,
  PRIMARY KEY (`id_user_group_map`),
  KEY `user_group_map_fk_id_user` (`id_user`),
  KEY `user_group_map_fk_id_user_group` (`id_user_group`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_task`
--

CREATE TABLE IF NOT EXISTS `user_task` (
  `id_user_task` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_task` int(11) NOT NULL,
  `complete` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_user_task`),
  KEY `user_task_fk_id_user` (`id_user`),
  KEY `user_task_fk_id_task` (`id_task`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;



--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_fk_id_answer_group` FOREIGN KEY (`id_answer_group`) REFERENCES `answer_group` (`id_answer_group`);

--
-- Filtros para la tabla `default_group_task`
--
ALTER TABLE `default_group_task`
  ADD CONSTRAINT `default_group_task_fk_id_task` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`),
  ADD CONSTRAINT `default_group_task_fk_id_user_group` FOREIGN KEY (`id_user_group`) REFERENCES `user_group` (`id_user_group`);

--
-- Filtros para la tabla `exercise_stimulus_map`
--
ALTER TABLE `exercise_stimulus_map`
  ADD CONSTRAINT `exercise_stimulus_map_fk_id_excersice` FOREIGN KEY (`id_excersice`) REFERENCES `excersice` (`id_excersice`),
  ADD CONSTRAINT `exercise_stimulus_map_fk_id_stimulus` FOREIGN KEY (`id_stimulus`) REFERENCES `stimulus` (`id_stimulus`);

--
-- Filtros para la tabla `possible_solution`
--
ALTER TABLE `possible_solution`
  ADD CONSTRAINT `possible_solution_fk_id_answer` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`id_answer`),
  ADD CONSTRAINT `possible_solution_fk_id_excersice` FOREIGN KEY (`id_excersice`) REFERENCES `excersice` (`id_excersice`);

--
-- Filtros para la tabla `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_fk_id_answer` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`id_answer`),
  ADD CONSTRAINT `result_fk_id_task_exercise` FOREIGN KEY (`id_task_exercise`) REFERENCES `task_exercise` (`id_task_exercise`),
  ADD CONSTRAINT `result_fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Filtros para la tabla `stimulus`
--
ALTER TABLE `stimulus`
  ADD CONSTRAINT `stimulus_fk_id_stimulus_group` FOREIGN KEY (`id_stimulus_group`) REFERENCES `stimulus_group` (`id_stimulus_group`);

--
-- Filtros para la tabla `task_exercise`
--
ALTER TABLE `task_exercise`
  ADD CONSTRAINT `task_exercise_fk_id_excersice` FOREIGN KEY (`id_excersice`) REFERENCES `excersice` (`id_excersice`),
  ADD CONSTRAINT `task_exercise_fk_id_task` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`);

--
-- Filtros para la tabla `user_group_map`
--
ALTER TABLE `user_group_map`
  ADD CONSTRAINT `user_group_map_fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `user_group_map_fk_id_user_group` FOREIGN KEY (`id_user_group`) REFERENCES `user_group` (`id_user_group`);

--
-- Filtros para la tabla `user_task`
--
ALTER TABLE `user_task`
  ADD CONSTRAINT `user_task_fk_id_task` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`),
  ADD CONSTRAINT `user_task_fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
