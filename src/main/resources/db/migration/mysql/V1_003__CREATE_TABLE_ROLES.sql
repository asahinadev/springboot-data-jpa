CREATE TABLE `roles` (
 `id`   VARCHAR(255) CHARACTER SET latin1 NOT NULL    ,
 `code` VARCHAR(255) CHARACTER SET latin1 DEFAULT NULL,
 `name` VARCHAR(255) CHARACTER SET latin1 DEFAULT NULL,

 PRIMARY KEY (`id`)                                                   ,
 UNIQUE KEY `idx_u_roles_code` (`code`) USING BTREE           ,
 UNIQUE KEY `idx_u_roles_name` (`name`) USING BTREE           
 
) ENGINE=InnoDB;
