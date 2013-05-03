SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE  TABLE IF NOT EXISTS `coclear`.`tag` (
  `id_tag` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_tag`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `coclear`.`tag_group` (
  `id_tag_group` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_tag_group`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `coclear`.`tag_group_map` (
  `id_tag_group_map` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_tag` INT(11) NOT NULL ,
  `id_tag_group` INT(11) NOT NULL ,
  PRIMARY KEY (`id_tag_group_map`) ,
  INDEX `fk_tag_group_map_tag1_idx` (`id_tag` ASC) ,
  INDEX `fk_tag_group_map_tag_group1_idx` (`id_tag_group` ASC) ,
  CONSTRAINT `fk_tag_group_map_tag1`
    FOREIGN KEY (`id_tag` )
    REFERENCES `coclear`.`tag` (`id_tag` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_group_map_tag_group1`
    FOREIGN KEY (`id_tag_group` )
    REFERENCES `coclear`.`tag_group` (`id_tag_group` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `coclear`.`tag_stimulus_map` (
  `id_tag_stimulus_map` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_tag` INT(11) NOT NULL ,
  `id_stimulus` INT(11) NOT NULL ,
  PRIMARY KEY (`id_tag_stimulus_map`) ,
  INDEX `fk_tag_stimulus_map_tag1_idx` (`id_tag` ASC) ,
  INDEX `fk_tag_stimulus_map_stimulus1_idx` (`id_stimulus` ASC) ,
  CONSTRAINT `fk_tag_stimulus_map_tag1`
    FOREIGN KEY (`id_tag` )
    REFERENCES `coclear`.`tag` (`id_tag` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_stimulus_map_stimulus1`
    FOREIGN KEY (`id_stimulus` )
    REFERENCES `coclear`.`stimulus` (`id_stimulus` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

CREATE  TABLE IF NOT EXISTS `coclear`.`tag_group_stimulus_map` (
  `id_tag_group_stimulus_map` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_tag_group` INT(11) NOT NULL ,
  `id_stimulus` INT(11) NOT NULL ,
  PRIMARY KEY (`id_tag_group_stimulus_map`) ,
  INDEX `fk_tag_group_stimulus_map_tag_group1_idx` (`id_tag_group` ASC) ,
  INDEX `fk_tag_group_stimulus_map_stimulus1_idx` (`id_stimulus` ASC) ,
  CONSTRAINT `fk_tag_group_stimulus_map_tag_group1`
    FOREIGN KEY (`id_tag_group` )
    REFERENCES `coclear`.`tag_group` (`id_tag_group` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_group_stimulus_map_stimulus1`
    FOREIGN KEY (`id_stimulus` )
    REFERENCES `coclear`.`stimulus` (`id_stimulus` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
