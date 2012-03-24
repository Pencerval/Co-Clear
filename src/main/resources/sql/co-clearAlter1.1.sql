SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

ALTER TABLE `coclear`.`exercise_stimulus_map` DROP FOREIGN KEY `exercise_stimulus_map_fk_id_excersice` ;

ALTER TABLE `coclear`.`possible_solution` DROP FOREIGN KEY `possible_solution_fk_id_excersice` ;

ALTER TABLE `coclear`.`task_exercise` DROP FOREIGN KEY `task_exercise_fk_id_excersice` ;

ALTER TABLE `coclear`.`answer` CHANGE COLUMN `id_answer` `id_answer` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  ;

ALTER TABLE `coclear`.`answer_group` CHANGE COLUMN `id_answer_group` `id_answer_group` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  ;

ALTER TABLE `coclear`.`default_group_task` CHANGE COLUMN `id_default_group_task` `id_default_group_task` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  ;

CREATE  TABLE IF NOT EXISTS `coclear`.`exercise` (
  `id_exercise` INT(11) NULL DEFAULT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL ,
  `type` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id_exercise`) )
ENGINE = InnoDB
AUTO_INCREMENT = 402
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;

ALTER TABLE `coclear`.`exercise_stimulus_map` CHANGE COLUMN `id_exercise_stimulus_map` `id_exercise_stimulus_map` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  , 
  ADD CONSTRAINT `exercise_stimulus_map_fk_id_excersice`
  FOREIGN KEY (`id_excersice` )
  REFERENCES `coclear`.`exercise` (`id_exercise` );

ALTER TABLE `coclear`.`possible_solution` ADD COLUMN `order` INT(11) NULL DEFAULT '0'  AFTER `correct` , CHANGE COLUMN `id_possible_solution` `id_possible_solution` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  , CHANGE COLUMN `correct` `correct` TINYINT(1) NOT NULL DEFAULT '0'  , 
  ADD CONSTRAINT `possible_solution_fk_id_excersice`
  FOREIGN KEY (`id_excersice` )
  REFERENCES `coclear`.`exercise` (`id_exercise` );

ALTER TABLE `coclear`.`result` ADD COLUMN `start` DATETIME NULL DEFAULT NULL  AFTER `id_task_exercise` , ADD COLUMN `end` DATETIME NULL DEFAULT NULL  AFTER `start` , CHANGE COLUMN `id_result` `id_result` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  ;

ALTER TABLE `coclear`.`stimulus` DROP COLUMN `path` , ADD COLUMN `type` VARCHAR(255) CHARACTER SET 'latin1' NOT NULL  AFTER `name` , CHANGE COLUMN `id_stimulus` `id_stimulus` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  ;

ALTER TABLE `coclear`.`stimulus_group` ADD COLUMN `description` VARCHAR(255) NULL DEFAULT NULL  AFTER `name` , CHANGE COLUMN `id_stimulus_group` `id_stimulus_group` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  ;

ALTER TABLE `coclear`.`task` ADD COLUMN `description` VARCHAR(255) NULL DEFAULT NULL  AFTER `type` , ADD COLUMN `help` VARCHAR(255) NULL DEFAULT NULL  AFTER `description` , CHANGE COLUMN `id_task` `id_task` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  , CHANGE COLUMN `repeatable` `repeatable` TINYINT(1) NOT NULL DEFAULT '0'  , CHANGE COLUMN `is_user_default` `is_user_default` TINYINT(1) NOT NULL  ;

ALTER TABLE `coclear`.`task_exercise` DROP COLUMN `number` , ADD COLUMN `order` INT(11) NOT NULL  AFTER `id_excersice` , CHANGE COLUMN `id_task_exercise` `id_task_exercise` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  , 
  ADD CONSTRAINT `task_exercise_fk_id_excersice`
  FOREIGN KEY (`id_excersice` )
  REFERENCES `coclear`.`exercise` (`id_exercise` );

ALTER TABLE `coclear`.`user` CHANGE COLUMN `id_user` `id_user` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  , CHANGE COLUMN `gender` `gender` TINYINT(1) NULL DEFAULT '1'  , CHANGE COLUMN `is_admin` `is_admin` TINYINT(1) NOT NULL DEFAULT '0'  , CHANGE COLUMN `sign_language` `sign_language` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `speech_reading` `speech_reading` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `speech_therapy` `speech_therapy` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `educational_orientation` `educational_orientation` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `bilingual` `bilingual` TINYINT(1) NULL DEFAULT '0'  , CHANGE COLUMN `learning_lenguages` `learning_lenguages` TINYINT(1) NULL DEFAULT NULL  ;

ALTER TABLE `coclear`.`user_group` CHANGE COLUMN `id_user_group` `id_user_group` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  , CHANGE COLUMN `is_default` `is_default` TINYINT(1) NOT NULL DEFAULT '0'  ;

ALTER TABLE `coclear`.`user_group_map` CHANGE COLUMN `id_user_group_map` `id_user_group_map` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  ;

ALTER TABLE `coclear`.`user_task` CHANGE COLUMN `id_user_task` `id_user_task` INT(11) NULL DEFAULT NULL AUTO_INCREMENT  , CHANGE COLUMN `complete` `complete` TINYINT(1) NOT NULL DEFAULT '0'  ;

DROP TABLE IF EXISTS `coclear`.`excersice` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
