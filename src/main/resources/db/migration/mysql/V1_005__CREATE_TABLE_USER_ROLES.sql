CREATE TABLE `user_roles` (
 `user_id`   VARCHAR(255) CHARACTER SET latin1 NOT NULL,
 `role_id`   VARCHAR(255) CHARACTER SET latin1 NOT NULL,

 PRIMARY KEY (`user_id`,`role_id`),
 
 FOREIGN KEY `idx_f_user_roles__users` (`user_id`) 
   REFERENCES `users`(`id`),
 FOREIGN KEY `idx_f_user_roles__roles` (`role_id`) 
   REFERENCES `roles`(`id`)
) ENGINE=InnoDB;
