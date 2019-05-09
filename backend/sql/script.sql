-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hoteldb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hoteldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hoteldb` DEFAULT CHARACTER SET utf8 ;
USE `hoteldb` ;

-- -----------------------------------------------------
-- Table `hoteldb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hoteldb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` TINYINT(1) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `mobile_number` VARCHAR(20) NOT NULL,
  `blocked` TINYINT(1) NOT NULL DEFAULT 0,
  `discount` INT NOT NULL DEFAULT 0,
  `birth_date` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hoteldb`.`room` (
  `id` INT NOT NULL,
  `price` DECIMAL NOT NULL,
  `room_type` VARCHAR(45) NOT NULL,
  `max_persons` INT NOT NULL,
  `is_active` TINYINT(1) NOT NULL,
  `has_baby_bed` TINYINT(1) NOT NULL,
  `has_tv` TINYINT(1) NOT NULL,
  `has_bath` TINYINT(1) NOT NULL,
  `has_fridge` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hoteldb`.`reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `date_from` DATE NOT NULL,
  `date_to` DATE NOT NULL,
  `person_count` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_reservation_room1_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `fk_reservation_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `hoteldb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `hoteldb`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hoteldb`.`review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `mark` TINYINT(10) NOT NULL,
  `comment` VARCHAR(65) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_reservation1_idx` (`reservation_id` ASC) VISIBLE,
  CONSTRAINT `fk_review_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `hoteldb`.`reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hoteldb`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_type` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `date_time_start` DATETIME NOT NULL,
  `date_time_end` DATETIME NOT NULL,
  `price` DECIMAL NOT NULL,
  `max_persons` INT NOT NULL,
  `logo_url` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`event_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hoteldb`.`event_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `person_count` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_record_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_event_record_event1_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_record_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `hoteldb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_record_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `hoteldb`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ('Спортивное', 'Футбол юниоры', 'Приглашаются все желающие посмотреть хорший футбол', '2019-12-12 14:00:00', '2019-12-12 22:00:00', '10', '700', 'default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ('Бизнес', 'Маркетинг для вашего бизнеса', 'Лучшие практики от самых современных спикеров', '2019-12-18 17:00:00', '2019-12-18 14:00:00', '12', '200', 'default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Спортивное', 'Волейбол', 'Приглашаются все желающие посмотреть хорший волейбол', '2019-11-12 11.00.00', '2019-11-12 14.00.00', '15', '200', 'default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ('Образовательное', 'История узбекистана', 'Главный историк узбекистана приезжает к нам в отель, что б дать лекцию по истории своей родины', '2019-11-12 14.30.00', '2019-11-12 15.00.00', '5', '100', 'default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Религиозное', 'Сжигание', 'Отлично провести время, вмести с христианами сжигающими ведьм', '2019-10-29 23.00.00', '2019-10-30 02.00.00', '199', '2000', 'default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Спортивное', 'Гандбол', 'Приглашаются все желающие посмотреть хорший по гандболу', '2019-09-09 09.00.00', '2019-09-12 18.00.00', '3', '1000', 'default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Политическое', 'Встреча с жириновским', 'Встреча с ведущим политическим спикером мира, обсуждение интересующих вопросов', '2019-10-10 14.00.00', '2019-10-12 21.00.00', '2', '200', 'default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Бизнес', 'Нефтяной', 'Обсуждение современных erp в нефтяном бизнесе', '2019-08-08 14.00.00', '2019-08-08 23.00.00', '0', '150','default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Образовательное', 'Высшая школа', 'Станите ну очень умным', '2019-05-01 19.00.00', '2019-05-01 22.00.00', '12', '100','default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Спортивное', 'Футбол деды', 'Приглашаются все желающие посмотреть хорший футбол', '2019-12-04 12.00.00', '2019-12-04 18.00.00', '32', '250','default-logo.jpg');
INSERT INTO `hoteldb`.`event` ( `event_type`, `name`, `description`, `date_time_start`, `date_time_end`, `price`, `max_persons`, `logo_url`) VALUES ( 'Образовательное', 'История футбола', 'Основатель Румынского футбола расскажет что это такое', '2019-02-07 17.00.00', '2019-02-07 19.00.00', '21', '100','default-logo.jpg');


INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('101', '100', 'Классический с видом на море', '4', '1', '0', '1', '0', '1');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('102', '100', 'Классический с видом на море', '4', '1', '0', '1', '0', '1');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('103', '50', 'Классический', '3', '1', '1', '0', '1', '0');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('104', '50', 'Классический', '3', '1', '1', '1', '0', '0');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('105', '35', 'Классический', '2', '0', '0', '0', '0', '0');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('211', '652', 'Роскошный люкс', '6', '1', '0', '1', '1', '1');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('212', '254', 'Люкс Premium', '5', '0', '1', '1', '1', '1');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('213', '211', 'Люкс Hospitality', '4', '1', '1', '1', '1', '1');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('225', '200', 'Люкс Hospitality', '4', '1', '0', '1', '1', '1');
INSERT INTO `hoteldb`.`room` (`id`, `price`, `room_type`, `max_persons`, `is_active`, `has_baby_bed`, `has_tv`, `has_bath`, `has_fridge`) VALUES ('301', '1200', 'Президентский люкс', '6', '1', '1', '1', '1', '1');
