-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank_solvd` ;
CREATE DATABASE `bank_solvd`;
USE `bank_solvd` ;

-- -----------------------------------------------------
-- Table `CREDIT SUMMARY`
-- -----------------------------------------------------
CREATE TABLE `CREDIT SUMMARY` (
  `PERSON ID` BIGINT NOT NULL,
  `SALARY` DOUBLE NULL,
  `PATRIMONY` DOUBLE NULL,
  `CREDIT TAKEN` TINYINT(1) NULL,
  PRIMARY KEY (`PERSON ID`)
 );


-- -----------------------------------------------------
-- Table `PERSON`
-- -----------------------------------------------------
CREATE TABLE `PERSON` (
  `PERSON ID` BIGINT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NULL,
  `SURNAME` VARCHAR(255) NULL,
  `COUNTRY` VARCHAR(255) NULL,
  `ADDRESS` VARCHAR(255) NULL,
  PRIMARY KEY (`PERSON ID`),
    FOREIGN KEY (`PERSON ID`) REFERENCES `PERSON` (`PERSON ID`)
   );


-- -----------------------------------------------------
-- Table`BANK BRANCH OFFICE`
-- -----------------------------------------------------
CREATE TABLE`BANK BRANCH OFFICE` (
  `OFFICE ID` BIGINT NOT NULL,
  `GENERAL BALANCE` DOUBLE NULL,
  `ADDRESS` VARCHAR(255) NULL,
  `COUNTRY` VARCHAR(255) NULL,
  PRIMARY KEY (`OFFICE ID`)
  );



-- -----------------------------------------------------
-- Table `ACCOUNTS MAIN TABLE`
-- -----------------------------------------------------
CREATE TABLE`ACCOUNTS MAIN TABLE` (
  `ACCOUNT ID` BIGINT NOT NULL AUTO_INCREMENT,
  `PERSON ID` BIGINT NOT NULL,
  `CERTIFICATE DEPOSIT ACCOUNT ID` BIGINT NULL,
  `SAVING ACCOUNT ID` BIGINT NULL,
  `CHECKING ACCOUNT ID` BIGINT NULL,
  `BALANCE` DOUBLE NULL,
  `OFFICE ID` BIGINT NULL,
  PRIMARY KEY (`ACCOUNT ID`),
    FOREIGN KEY (`PERSON ID`) REFERENCES `PERSON` (`PERSON ID`),
    FOREIGN KEY (`OFFICE ID`) REFERENCES `BANK BRANCH OFFICE` (`OFFICE ID`)
    );



-- -----------------------------------------------------
-- Table `CREDIT CARD`
-- -----------------------------------------------------
CREATE TABLE `CREDIT CARD` (
  `CREDIT CARD ID` BIGINT NOT NULL AUTO_INCREMENT,
  `LIMIT IN 1 DUE` DOUBLE NULL,
  `LIMIT IN DUES` DOUBLE NULL,
  `EXPIRATION DATE` DATE NULL,
  `SECURITY CODE` TINYINT NULL,
  `PROVIDER` VARCHAR(255) NULL,
  `ACCOUNT ID` BIGINT NULL,
  PRIMARY KEY (`CREDIT CARD ID`),
    FOREIGN KEY (`ACCOUNT ID`) REFERENCES `ACCOUNTS MAIN TABLE` (`ACCOUNT ID`)
    );



-- -----------------------------------------------------
-- Table `DEBIT CARD`
-- -----------------------------------------------------
CREATE TABLE `DEBIT CARD` (
  `DEBIT CARD ID` BIGINT NOT NULL AUTO_INCREMENT,
  `EXPIRATION DATE` DATE NULL,
  `SECURITY CODE` TINYINT NULL,
  ` PROVIDER` VARCHAR(255) NULL,
  `ACCOUNT ID` BIGINT NOT NULL,
  PRIMARY KEY (`DEBIT CARD ID`),
    FOREIGN KEY (`ACCOUNT ID`) REFERENCES `ACCOUNTS MAIN TABLE` (`ACCOUNT ID`)
    );



-- -----------------------------------------------------
-- Table `CHECKING ACCOUNT`
-- -----------------------------------------------------
CREATE TABLE `CHECKING ACCOUNT` (
  `CHECKS` TINYINT NULL,
  `CHECKING ACCOUNT ID` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`CHECKING ACCOUNT ID`),
    FOREIGN KEY (`CHECKING ACCOUNT ID`) REFERENCES `ACCOUNTS MAIN TABLE` (`CHECKING ACCOUNT ID`)
    );



-- -----------------------------------------------------
-- Table `SAVING ACCOUNT`
-- -----------------------------------------------------
CREATE TABLE`SAVING ACCOUNT` (
  `MONTH WITHDRAWALS` TINYINT NULL,
  `SAVING ACCOUNT ID` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`SAVING ACCOUNT ID`),
    FOREIGN KEY (`SAVING ACCOUNT ID`) REFERENCES `ACCOUNTS MAIN TABLE` (`SAVING ACCOUNT ID`)
    );


-- -----------------------------------------------------
-- Table `mydb`.`CERTIFICATE DEPOSIT ACCOUNT`
-- -----------------------------------------------------
CREATE TABLE `CERTIFICATE DEPOSIT ACCOUNT` (
  `START DATE` DATE NULL,
  `FINISH DATE` DATE NULL,
  `INTEREST RATE` DECIMAL NULL,
  `CERTIFICATE DEPOSIT ACCOUNT ID` BIGINT NOT NULL,
  PRIMARY KEY (`CERTIFICATE DEPOSIT ACCOUNT ID`),
    FOREIGN KEY (`CERTIFICATE DEPOSIT ACCOUNT ID`) REFERENCES `ACCOUNTS MAIN TABLE` (`CERTIFICATE DEPOSIT ACCOUNT ID`)
    );



-- -----------------------------------------------------
-- Table `HOMEBANKING`
-- -----------------------------------------------------
CREATE TABLE `HOMEBANKING` (
  `PERSON ID` BIGINT NOT NULL,
  `USERNAME` VARCHAR(255) NULL,
  `PASSWORD` VARCHAR(255) NULL,
  PRIMARY KEY (`PERSON ID`),
    FOREIGN KEY (`PERSON ID`) REFERENCES `PERSON` (`PERSON ID`)
    );



-- -----------------------------------------------------
-- Table `ATM`
-- -----------------------------------------------------
CREATE TABLE `ATM` (
  `ATM ID` BIGINT NOT NULL,
  `BALANCE` DOUBLE NULL,
  `OFFICE ID` BIGINT NULL,
  PRIMARY KEY (`ATM ID`),
    FOREIGN KEY (`OFFICE ID`) REFERENCES `BANK BRANCH OFFICE` (`OFFICE ID`)
    );



