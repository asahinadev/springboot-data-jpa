INSERT INTO `user_roles` (`user_id`,`role_id`)
SELECT `users`.`id`  AS user_id ,
       `roles`.`id`  AS role_id
FROM   `users`,
       `roles`;

