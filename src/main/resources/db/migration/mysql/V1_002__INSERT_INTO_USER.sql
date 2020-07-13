INSERT INTO `users` (
    `id`,
    `email`,
    `username`,
    `password`,
    `enabled`
) VALUES (
    UUID()	                                                       /* id           */ ,
    'system@example.co.jp'                                         /* email        */ ,
    'system'                                                       /* username     */ ,
    '$2a$10$CuG1yEyFC0tJQ2BuvvksVu4zYz4o44mNQN5ZIxAQ3/vRSsTzxOfnm' /* password     */ ,
    1
);
