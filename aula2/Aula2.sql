-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema biblioteca_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca_db` DEFAULT CHARACTER SET utf8mb3 ;
USE `biblioteca_db` ;

-- -----------------------------------------------------
-- Table `biblioteca_db`.`alunos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_db`.`alunos` (
  `id_alunos` INT NOT NULL AUTO_INCREMENT,
  `nome_aluno` VARCHAR(45) NULL DEFAULT NULL,
  `idade_aluno` INT NULL DEFAULT NULL,
  `telefone` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_alunos`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `biblioteca_db`.`livros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_db`.`livros` (
  `id_livro` INT NOT NULL AUTO_INCREMENT,
  `nome_livro` VARCHAR(45) NULL DEFAULT NULL,
  `autor` VARCHAR(45) NULL DEFAULT NULL,
  `genero` VARCHAR(45) NULL DEFAULT NULL,
  `isbn` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_livro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `biblioteca_db`.`emprestimos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_db`.`emprestimos` (
  `alunos_id_alunos` INT NOT NULL,
  `livros_id_livro` INT NOT NULL,
  `datahora` VARCHAR(45) NULL DEFAULT NULL,
  `id_emprestimo` INT NOT NULL,
  PRIMARY KEY (`alunos_id_alunos`, `livros_id_livro`, `id_emprestimo`),
  INDEX `fk_emprestimos_livros1_idx` (`livros_id_livro` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimos_alunos`
    FOREIGN KEY (`alunos_id_alunos`)
    REFERENCES `biblioteca_db`.`alunos` (`id_alunos`),
  CONSTRAINT `fk_emprestimos_livros1`
    FOREIGN KEY (`livros_id_livro`)
    REFERENCES `biblioteca_db`.`livros` (`id_livro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
