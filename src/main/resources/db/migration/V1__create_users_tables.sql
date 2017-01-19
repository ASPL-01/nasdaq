CREATE TABLE `users` (
  `id`       INT           NOT NULL  AUTO_INCREMENT,
  `version`  INT           NOT NULL  DEFAULT 0,
  `email`    VARCHAR(255)  NOT NULL,
  `balance`  DECIMAL(12,2) NOT NULL  DEFAULT 0,
  `created`  TIMESTAMP     NOT NULL  DEFAULT NOW(),
  `modified` TIMESTAMP     NOT NULL  DEFAULT NOW(),
  PRIMARY KEY (`id`));
