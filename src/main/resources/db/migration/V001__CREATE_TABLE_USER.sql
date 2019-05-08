DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
 `id`       varchar(255) COLLATE latin1_general_ci     NOT NULL,
 `email`    varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
 `username` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
 `password` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
 `created`  datetime                               DEFAULT NULL,
 `updated`  datetime                               DEFAULT NULL,
 `deleted`  datetime                               DEFAULT NULL,
 `actived`  set('0','1') COLLATE latin1_general_ci DEFAULT '1',
 
 PRIMARY KEY (`id`),
 UNIQUE KEY `idx_u_users_username` (`username`,`actived`) USING BTREE,
 UNIQUE KEY `idx_u_users_email`    (`email`   ,`actived`) USING BTREE
 
) ENGINE=InnoDB;

DELIMITER $
CREATE TRIGGER `users_before_insert` BEFORE INSERT ON `users` FOR EACH ROW BEGIN 
    SET NEW.created = now(); 
    SET NEW.updated = now();
    IF NEW.actived = '0' THEN
        SET NEW.actived = NULL;
    END IF;
END$
	
CREATE TRIGGER `users_before_update` BEFORE UPDATE ON `users` FOR EACH ROW BEGIN 
    SET NEW.updated = now();
    IF NEW.actived = '0' THEN
        SET NEW.actived = NULL;
    END IF;
END$

DELIMITER ;
