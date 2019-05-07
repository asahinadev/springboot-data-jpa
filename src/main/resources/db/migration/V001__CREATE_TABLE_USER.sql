DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id`       VARCHAR(255) NOT NULL,
    `email`    VARCHAR(255),
    `username` VARCHAR(255),
    `password` VARCHAR(255),
    `created`  DATETIME,
    `updated`  DATETIME,
    `deleted`  DATETIME,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB 
  DEFAULT CHARSET=latin1 
  COLLATE=latin1_general_ci ;

DELIMITER $
CREATE TRIGGER `users_before_insert` BEFORE INSERT ON `users` FOR EACH ROW BEGIN SET NEW.created = now(); SET NEW.updated = now(); END$
CREATE TRIGGER `users_before_update` BEFORE UPDATE ON `users` FOR EACH ROW BEGIN SET NEW.updated = now(); END$
DELIMITER ;

ALTER TABLE `users` ADD UNIQUE idx_u_users_username(`username`, `deleted`) USING BTREE;
ALTER TABLE `users` ADD UNIQUE idx_u_users_email(`email`, `deleted`) USING BTREE;


