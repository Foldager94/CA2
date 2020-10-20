-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CA2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CA2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CA2` DEFAULT CHARACTER SET utf8 ;
USE `CA2` ;

-- -----------------------------------------------------
-- Table `CA2`.`CITYINFO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`CITYINFO` (
  `zipCode` INT NOT NULL,
  `city` VARCHAR(45) NULL,
  PRIMARY KEY (`zipCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`ADDRESS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`ADDRESS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `zipCode` INT NULL,
  `additionalInfo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `zipCode_idx` (`zipCode` ASC) VISIBLE,
  CONSTRAINT `zipCode`
    FOREIGN KEY (`zipCode`)
    REFERENCES `CA2`.`CITYINFO` (`zipCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`PERSON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`PERSON` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `a_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `a_id_idx` (`a_id` ASC) VISIBLE,
  CONSTRAINT `a_id`
    FOREIGN KEY (`a_id`)
    REFERENCES `CA2`.`ADDRESS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`PHONE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`PHONE` (
  `number` INT NOT NULL,
  `description` VARCHAR(45) NULL,
  `p_id` INT NOT NULL,
  PRIMARY KEY (`number`),
  INDEX `p_id_idx` (`p_id` ASC) VISIBLE,
  CONSTRAINT `p_id`
    FOREIGN KEY (`p_id`)
    REFERENCES `CA2`.`PERSON` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`HOBBY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`HOBBY` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `wikiLink` VARCHAR(45) NULL,
  `category` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`PERSON_HOBBY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`PERSON_HOBBY` (
  `p_id` INT NOT NULL,
  `h_id` INT NOT NULL,
  PRIMARY KEY (`p_id`, `h_id`),
  INDEX `h_id_idx` (`h_id` ASC) VISIBLE,
  CONSTRAINT `person_id`
    FOREIGN KEY (`p_id`)
    REFERENCES `CA2`.`PERSON` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `hobby_id`
    FOREIGN KEY (`h_id`)
    REFERENCES `CA2`.`HOBBY` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
