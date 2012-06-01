SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `coclear2` ;
CREATE SCHEMA IF NOT EXISTS `coclear2` DEFAULT CHARACTER SET latin1 ;
SHOW WARNINGS;
USE `coclear2` ;

-- -----------------------------------------------------
-- Table `coclear2`.`answer_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`answer_group` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`answer_group` (
  `id_answer_group` INT(11) NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  PRIMARY KEY (`id_answer_group`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`answer` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`answer` (
  `id_answer` INT(11) NULL AUTO_INCREMENT ,
  `id_answer_group` INT(11) NOT NULL ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `value_name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `example` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  PRIMARY KEY (`id_answer`) ,
  INDEX `answer_fk_id_answer_group` (`id_answer_group` ASC) ,
  CONSTRAINT `answer_fk_id_answer_group`
    FOREIGN KEY (`id_answer_group` )
    REFERENCES `coclear2`.`answer_group` (`id_answer_group` ))
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`task` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`task` (
  `id_task` INT(11) NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `repeatable` TINYINT(1) NOT NULL DEFAULT '0' ,
  `is_user_default` TINYINT(1) NOT NULL ,
  `type` INT(11) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `help` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_task`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`user_group` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`user_group` (
  `id_user_group` INT(11) NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `is_default` TINYINT(1) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id_user_group`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`default_group_task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`default_group_task` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`default_group_task` (
  `id_default_group_task` INT(11) NULL AUTO_INCREMENT ,
  `id_task` INT(11) NOT NULL ,
  `id_user_group` INT(11) NOT NULL ,
  PRIMARY KEY (`id_default_group_task`) ,
  INDEX `default_group_task_fk_id_task` (`id_task` ASC) ,
  INDEX `default_group_task_fk_id_user_group` (`id_user_group` ASC) ,
  CONSTRAINT `default_group_task_fk_id_task`
    FOREIGN KEY (`id_task` )
    REFERENCES `coclear2`.`task` (`id_task` ),
  CONSTRAINT `default_group_task_fk_id_user_group`
    FOREIGN KEY (`id_user_group` )
    REFERENCES `coclear2`.`user_group` (`id_user_group` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`exercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`exercise` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`exercise` (
  `id_exercise` INT(11) NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `type` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id_exercise`) )
ENGINE = InnoDB
AUTO_INCREMENT = 402
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`stimulus_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`stimulus_group` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`stimulus_group` (
  `id_stimulus_group` INT(11) NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_stimulus_group`) )
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`stimulus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`stimulus` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`stimulus` (
  `id_stimulus` INT(11) NULL AUTO_INCREMENT ,
  `id_stimulus_group` INT(11) NOT NULL ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `type` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  PRIMARY KEY (`id_stimulus`) ,
  INDEX `stimulus_fk_id_stimulus_group` (`id_stimulus_group` ASC) ,
  CONSTRAINT `stimulus_fk_id_stimulus_group`
    FOREIGN KEY (`id_stimulus_group` )
    REFERENCES `coclear2`.`stimulus_group` (`id_stimulus_group` ))
ENGINE = InnoDB
AUTO_INCREMENT = 883
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`exercise_stimulus_map`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`exercise_stimulus_map` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`exercise_stimulus_map` (
  `id_exercise_stimulus_map` INT(11) NULL AUTO_INCREMENT ,
  `id_excersice` INT(11) NOT NULL ,
  `id_stimulus` INT(11) NOT NULL ,
  PRIMARY KEY (`id_exercise_stimulus_map`) ,
  INDEX `exercise_stimulus_map_fk_id_excersice` (`id_excersice` ASC) ,
  INDEX `exercise_stimulus_map_fk_id_stimulus` (`id_stimulus` ASC) ,
  CONSTRAINT `exercise_stimulus_map_fk_id_excersice`
    FOREIGN KEY (`id_excersice` )
    REFERENCES `coclear2`.`exercise` (`id_exercise` ),
  CONSTRAINT `exercise_stimulus_map_fk_id_stimulus`
    FOREIGN KEY (`id_stimulus` )
    REFERENCES `coclear2`.`stimulus` (`id_stimulus` ))
ENGINE = InnoDB
AUTO_INCREMENT = 386
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`possible_solution`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`possible_solution` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`possible_solution` (
  `id_possible_solution` INT(11) NULL AUTO_INCREMENT ,
  `id_excersice` INT(11) NOT NULL ,
  `id_answer` INT(11) NOT NULL ,
  `correct` TINYINT(1) NOT NULL DEFAULT '0' ,
  `order` INT(11) NULL DEFAULT '0' ,
  PRIMARY KEY (`id_possible_solution`) ,
  INDEX `possible_solution_fk_id_excersice` (`id_excersice` ASC) ,
  INDEX `possible_solution_fk_id_answer` (`id_answer` ASC) ,
  CONSTRAINT `possible_solution_fk_id_answer`
    FOREIGN KEY (`id_answer` )
    REFERENCES `coclear2`.`answer` (`id_answer` ),
  CONSTRAINT `possible_solution_fk_id_excersice`
    FOREIGN KEY (`id_excersice` )
    REFERENCES `coclear2`.`exercise` (`id_exercise` ))
ENGINE = InnoDB
AUTO_INCREMENT = 6458
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`task_exercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`task_exercise` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`task_exercise` (
  `id_task_exercise` INT(11) NULL AUTO_INCREMENT ,
  `id_task` INT(11) NOT NULL ,
  `id_excersice` INT(11) NOT NULL ,
  `order` INT(11) NOT NULL ,
  PRIMARY KEY (`id_task_exercise`) ,
  INDEX `task_exercise_fk_id_task` (`id_task` ASC) ,
  INDEX `task_exercise_fk_id_excersice` (`id_excersice` ASC) ,
  CONSTRAINT `task_exercise_fk_id_excersice`
    FOREIGN KEY (`id_excersice` )
    REFERENCES `coclear2`.`exercise` (`id_exercise` ),
  CONSTRAINT `task_exercise_fk_id_task`
    FOREIGN KEY (`id_task` )
    REFERENCES `coclear2`.`task` (`id_task` ))
ENGINE = InnoDB
AUTO_INCREMENT = 377
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`user` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`user` (
  `id_user` INT(11) NULL AUTO_INCREMENT ,
  `login` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `password` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `email` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `birthdate` DATE NULL DEFAULT NULL ,
  `phone` INT(11) NULL DEFAULT NULL ,
  `gender` TINYINT(1) NULL DEFAULT '1' ,
  `is_admin` TINYINT(1) NOT NULL DEFAULT '0' ,
  `hearing_loss_date` DATE NULL DEFAULT NULL ,
  `cause_deafnes` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `degree_deafness` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `duration_hearing_loss` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `implantation_date` DATE NULL DEFAULT NULL ,
  `cochlear_implant_type` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `cochlear_implant_model` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `sign_language` TINYINT(1) NULL DEFAULT '0' ,
  `sign_language_years` INT(2) NULL DEFAULT NULL ,
  `speech_reading` TINYINT(1) NULL DEFAULT '0' ,
  `speech_reading_years` INT(2) NULL DEFAULT NULL ,
  `speech_therapy` TINYINT(1) NULL DEFAULT '0' ,
  `speech_therapy_years` INT(2) NULL DEFAULT NULL ,
  `educational_orientation` TINYINT(1) NULL DEFAULT '0' ,
  `educational_orientation_years` INT(2) NULL DEFAULT NULL ,
  `bilingual` TINYINT(1) NULL DEFAULT '0' ,
  `languages` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `home_languages` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `learning_lenguages` TINYINT(1) NULL DEFAULT NULL ,
  `learning_lenguages_list` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `place_years_residence` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `place_years_residence_mother` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `place_years_residence_father` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `comments` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  `admin_comments` VARCHAR(255) CHARACTER SET 'latin1' NULL DEFAULT NULL ,
  PRIMARY KEY (`id_user`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`result` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`result` (
  `id_result` INT(11) NULL AUTO_INCREMENT ,
  `id_user` INT(11) NOT NULL ,
  `id_answer` INT(11) NOT NULL ,
  `id_task_exercise` INT(11) NOT NULL ,
  `start` DATETIME NULL ,
  `end` DATETIME NULL ,
  PRIMARY KEY (`id_result`) ,
  INDEX `result_fk_id_user` (`id_user` ASC) ,
  INDEX `result_fk_id_answer` (`id_answer` ASC) ,
  INDEX `result_fk_id_task_exercise` (`id_task_exercise` ASC) ,
  CONSTRAINT `result_fk_id_answer`
    FOREIGN KEY (`id_answer` )
    REFERENCES `coclear2`.`answer` (`id_answer` ),
  CONSTRAINT `result_fk_id_task_exercise`
    FOREIGN KEY (`id_task_exercise` )
    REFERENCES `coclear2`.`task_exercise` (`id_task_exercise` ),
  CONSTRAINT `result_fk_id_user`
    FOREIGN KEY (`id_user` )
    REFERENCES `coclear2`.`user` (`id_user` ))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`user_group_map`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`user_group_map` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`user_group_map` (
  `id_user_group_map` INT(11) NULL AUTO_INCREMENT ,
  `id_user` INT(11) NOT NULL ,
  `id_user_group` INT(11) NOT NULL ,
  PRIMARY KEY (`id_user_group_map`) ,
  INDEX `user_group_map_fk_id_user` (`id_user` ASC) ,
  INDEX `user_group_map_fk_id_user_group` (`id_user_group` ASC) ,
  CONSTRAINT `user_group_map_fk_id_user`
    FOREIGN KEY (`id_user` )
    REFERENCES `coclear2`.`user` (`id_user` ),
  CONSTRAINT `user_group_map_fk_id_user_group`
    FOREIGN KEY (`id_user_group` )
    REFERENCES `coclear2`.`user_group` (`id_user_group` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `coclear2`.`user_task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coclear2`.`user_task` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `coclear2`.`user_task` (
  `id_user_task` INT(11) NULL AUTO_INCREMENT ,
  `id_user` INT(11) NOT NULL ,
  `id_task` INT(11) NOT NULL ,
  `complete` TINYINT(1) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id_user_task`) ,
  INDEX `user_task_fk_id_user` (`id_user` ASC) ,
  INDEX `user_task_fk_id_task` (`id_task` ASC) ,
  CONSTRAINT `user_task_fk_id_task`
    FOREIGN KEY (`id_task` )
    REFERENCES `coclear2`.`task` (`id_task` ),
  CONSTRAINT `user_task_fk_id_user`
    FOREIGN KEY (`id_user` )
    REFERENCES `coclear2`.`user` (`id_user` ))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
