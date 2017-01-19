CREATE TABLE `transactions` (
  `id`       INT                 NOT NULL  AUTO_INCREMENT,
  `version`  INT                 NOT NULL  DEFAULT 0,
  `action`   ENUM('BUY', 'SELL') NOT NULL,
  `symbol`   VARCHAR(10)         NOT NULL,
  `quantity` INT                 NOT NULL,
  `amount`   DECIMAL(12, 2)      NOT NULL,
  `user_id`  INT                 NOT NULL,
  `created`  TIMESTAMP           NOT NULL  DEFAULT NOW(),
  `modified` TIMESTAMP           NOT NULL  DEFAULT NOW(),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
