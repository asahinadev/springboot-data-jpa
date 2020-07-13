INSERT INTO `roles` (
    `id`,
    `code`,
    `name`
) VALUES (
    UUID()	       /* id           */ ,
    'SYSTEM'       /* username     */ ,
    'SYSTEM ADMIN' /* password     */ 
),(
    UUID()	        /* id           */ ,
    'ADMIN'         /* username     */ ,
    'WEBSITE ADMIN' /* password     */ 
),(
    UUID()	/* id           */ ,
    'USER'  /* username     */ ,
    'USER'  /* password     */ 
);
