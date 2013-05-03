SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE  TABLE IF NOT EXISTS `coclear`.`answer_stimulus_map` (
  `id_answer_stimulus_map` INT(11) NOT NULL ,
  `id_answer` INT(11) NOT NULL ,
  `id_stimulus` INT(11) NOT NULL ,
  PRIMARY KEY (`id_answer_stimulus_map`) ,
  INDEX `answer_stimulus_map_fk_answer` (`id_answer` ASC) ,
  INDEX `answer_stimulus_map_fk_stimulus` (`id_stimulus` ASC) ,
  CONSTRAINT `answer_stimulus_map_fk_answer`
    FOREIGN KEY (`id_answer` )
    REFERENCES `coclear`.`answer` (`id_answer` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `answer_stimulus_map_fk_stimulus`
    FOREIGN KEY (`id_stimulus` )
    REFERENCES `coclear`.`stimulus` (`id_stimulus` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

ALTER TABLE `coclear`.`user` ADD COLUMN `origin_deafnes` VARCHAR(255) NULL DEFAULT NULL  AFTER `degree_deafness` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
