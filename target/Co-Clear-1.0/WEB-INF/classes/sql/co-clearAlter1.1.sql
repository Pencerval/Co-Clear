SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

ALTER TABLE `coclear`.`exercise_stimulus_map` DROP FOREIGN KEY `exercise_stimulus_map_fk_id_excersice` ;

ALTER TABLE `coclear`.`possible_solution` DROP FOREIGN KEY `possible_solution_fk_id_excersice` ;

ALTER TABLE `coclear`.`task_exercise` DROP FOREIGN KEY `task_exercise_fk_id_excersice` ;

CREATE  TABLE IF NOT EXISTS `coclear`.`exercise` (
  `id_exercise` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `type` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id_exercise`) )
ENGINE = InnoDB
AUTO_INCREMENT = 958
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

ALTER TABLE `coclear`.`exercise_stimulus_map` 
  ADD CONSTRAINT `exercise_stimulus_map_fk_id_excersice`
  FOREIGN KEY (`id_excersice` )
  REFERENCES `coclear`.`exercise` (`id_exercise` );

ALTER TABLE `coclear`.`possible_solution` ADD COLUMN `answer_order` INT(11) NULL DEFAULT '0'  AFTER `correct` , CHANGE COLUMN `correct` `correct` TINYINT(1) NOT NULL DEFAULT '0'  , 
  ADD CONSTRAINT `possible_solution_fk_id_excersice`
  FOREIGN KEY (`id_excersice` )
  REFERENCES `coclear`.`exercise` (`id_exercise` );

ALTER TABLE `coclear`.`result` ADD COLUMN `start` DATETIME NULL DEFAULT NULL  AFTER `id_task_exercise` , ADD COLUMN `end` DATETIME NULL DEFAULT NULL  AFTER `start` ;

ALTER TABLE `coclear`.`stimulus` DROP COLUMN `path` , ADD COLUMN `type` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL  AFTER `name` ;

ALTER TABLE `coclear`.`stimulus_group` ADD COLUMN `description` VARCHAR(255) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL  AFTER `name` ;

ALTER TABLE `coclear`.`task` ADD COLUMN `description` VARCHAR(255) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL  AFTER `type` , ADD COLUMN `help` VARCHAR(255) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL  AFTER `description` , CHANGE COLUMN `repeatable` `repeatable` TINYINT(1) NOT NULL DEFAULT '0'  , CHANGE COLUMN `is_user_default` `is_user_default` TINYINT(1) NOT NULL  ;

ALTER TABLE `coclear`.`task_exercise` DROP COLUMN `number` , ADD COLUMN `exercise_order` INT(11) NOT NULL  AFTER `id_excersice` , 
  ADD CONSTRAINT `task_exercise_fk_id_excersice`
  FOREIGN KEY (`id_excersice` )
  REFERENCES `coclear`.`exercise` (`id_exercise` );

ALTER TABLE `coclear`.`user` CHANGE COLUMN `gender` `gender` TINYINT(1) NULL DEFAULT '1'  , CHANGE COLUMN `is_admin` `is_admin` TINYINT(1) NOT NULL DEFAULT '0'  , CHANGE COLUMN `sign_language` `sign_language` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `speech_reading` `speech_reading` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `speech_therapy` `speech_therapy` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `educational_orientation` `educational_orientation` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `bilingual` `bilingual` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `learning_lenguages` `learning_lenguages` TINYINT(1) NULL DEFAULT NULL  ;

ALTER TABLE `coclear`.`user_group` CHANGE COLUMN `is_default` `is_default` TINYINT(1) NOT NULL DEFAULT '0'  ;

ALTER TABLE `coclear`.`user_task` CHANGE COLUMN `complete` `complete` TINYINT(1) NOT NULL DEFAULT '0'  ;

DROP TABLE IF EXISTS `coclear`.`excersice` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
