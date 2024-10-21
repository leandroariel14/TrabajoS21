-- MySQL Script generated by MySQL Workbench
-- Fri Oct  4 09:20:08 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema srda.mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema srda.mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `srda.mydb` DEFAULT CHARACTER SET utf8 ;
USE `srda.mydb` ;

-- -----------------------------------------------------
-- Table `srda.mydb`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Usuarios` (
  `legajo` INT(6) NOT NULL,
  `tipo_usuario` INT(1) NULL,
  `nom_usuario` VARCHAR(45) NULL,
  `email` VARCHAR(90) NULL,
  `password` VARCHAR(90) NULL,
  PRIMARY KEY (`legajo`),
  UNIQUE INDEX `legajo_UNIQUE` (`legajo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`Termometria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Termometria` (
  `tipo_termometro` INT(3) NOT NULL,
  `total_grados` INT(3) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_termometro`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroTemp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroTemp` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `observacion` VARCHAR(250) NULL,
  `grados` FLOAT NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `Termometria_tipo_termometro` INT(3) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroTemp_Usuarios_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroTemp_Termometria1_idx` (`Termometria_tipo_termometro` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroTemp_Usuarios`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroTemp_Termometria1`
    FOREIGN KEY (`Termometria_tipo_termometro`)
    REFERENCES `srda.mydb`.`Termometria` (`tipo_termometro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`Heliofania`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Heliofania` (
  `tipo_instrumento` INT(2) NOT NULL,
  `capacidad` INT(2) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_instrumento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroHeliofania`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroHeliofania` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `lectura` INT(3) NULL,
  `observacion` VARCHAR(250) NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `Heliofania_tipo_instrumento` INT(2) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroHeliofania_Usuarios1_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroHeliofania_Heliofania1_idx` (`Heliofania_tipo_instrumento` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroHeliofania_Usuarios1`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroHeliofania_Heliofania1`
    FOREIGN KEY (`Heliofania_tipo_instrumento`)
    REFERENCES `srda.mydb`.`Heliofania` (`tipo_instrumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`Evaporimetrica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Evaporimetrica` (
  `tipo_contenedor` INT(2) NOT NULL,
  `capacidad` INT(4) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_contenedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroEvaporimetrica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroEvaporimetrica` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `volumen` INT(3) NULL,
  `observacion` VARCHAR(250) NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `Evaporimetrica_tipo_contenedor` INT(2) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroEvaporimetrica_Usuarios1_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroEvaporimetrica_Evaporimetrica1_idx` (`Evaporimetrica_tipo_contenedor` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroEvaporimetrica_Usuarios1`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroEvaporimetrica_Evaporimetrica1`
    FOREIGN KEY (`Evaporimetrica_tipo_contenedor`)
    REFERENCES `srda.mydb`.`Evaporimetrica` (`tipo_contenedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`Pluviometria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Pluviometria` (
  `tipo_pluviometro` INT(2) NOT NULL,
  `capacidad` INT(3) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_pluviometro`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroPluviometria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroPluviometria` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `volumen` FLOAT(4) NULL,
  `RegistroPluviometríacol` VARCHAR(45) NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `Pluviometria_tipo_pluviometro` INT(2) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroPluviometría_Usuarios1_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroPluviometria_Pluviometria1_idx` (`Pluviometria_tipo_pluviometro` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroPluviometría_Usuarios1`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroPluviometria_Pluviometria1`
    FOREIGN KEY (`Pluviometria_tipo_pluviometro`)
    REFERENCES `srda.mydb`.`Pluviometria` (`tipo_pluviometro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`Anemometria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Anemometria` (
  `tipo_anemometro` INT(2) NOT NULL,
  `capacidad` INT(6) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_anemometro`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroAnemometria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroAnemometria` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `lectura` INT(6) NULL,
  `observacion` VARCHAR(250) NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `Anemometria_tipo_anemometro` INT(2) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroAnemometría_Usuarios1_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroAnemometria_Anemometria1_idx` (`Anemometria_tipo_anemometro` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroAnemometría_Usuarios1`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroAnemometria_Anemometria1`
    FOREIGN KEY (`Anemometria_tipo_anemometro`)
    REFERENCES `srda.mydb`.`Anemometria` (`tipo_anemometro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`Nubosidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Nubosidad` (
  `tipo_nube` INT(2) NOT NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_nube`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroNubosidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroNubosidad` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `visibilidad` INT(2) NULL,
  `observacion` VARCHAR(250) NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `Nubosidad_tipo_nube` INT(2) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroNubosidad_Usuarios1_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroNubosidad_Nubosidad1_idx` (`Nubosidad_tipo_nube` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroNubosidad_Usuarios1`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroNubosidad_Nubosidad1`
    FOREIGN KEY (`Nubosidad_tipo_nube`)
    REFERENCES `srda.mydb`.`Nubosidad` (`tipo_nube`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`Psicrometria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`Psicrometria` (
  `tipo_instrumento` INT(2) NOT NULL,
  `capacidad` INT(3) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_instrumento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroPsicrometria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroPsicrometria` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `lectura` INT(3) NULL,
  `observacion` VARCHAR(250) NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `Psicrometria_tipo_instrumento` INT(2) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroPsicrometria_Usuarios1_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroPsicrometria_Psicrometria1_idx` (`Psicrometria_tipo_instrumento` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroPsicrometria_Usuarios1`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroPsicrometria_Psicrometria1`
    FOREIGN KEY (`Psicrometria_tipo_instrumento`)
    REFERENCES `srda.mydb`.`Psicrometria` (`tipo_instrumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`FenomenoM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`FenomenoM` (
  `tipo_numerico` INT(2) NOT NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`tipo_numerico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srda.mydb`.`RegistroFenomenoM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `srda.mydb`.`RegistroFenomenoM` (
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `lectura` VARCHAR(45) NULL,
  `observacion` VARCHAR(250) NULL,
  `Usuarios_legajo` INT(6) NOT NULL,
  `FenomenoM_tipo_numerico` INT(2) NOT NULL,
  PRIMARY KEY (`fecha`, `hora`),
  INDEX `fk_RegistroFenomenoM_Usuarios1_idx` (`Usuarios_legajo` ASC) VISIBLE,
  INDEX `fk_RegistroFenomenoM_FenomenoM1_idx` (`FenomenoM_tipo_numerico` ASC) VISIBLE,
  CONSTRAINT `fk_RegistroFenomenoM_Usuarios1`
    FOREIGN KEY (`Usuarios_legajo`)
    REFERENCES `srda.mydb`.`Usuarios` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RegistroFenomenoM_FenomenoM1`
    FOREIGN KEY (`FenomenoM_tipo_numerico`)
    REFERENCES `srda.mydb`.`FenomenoM` (`tipo_numerico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;