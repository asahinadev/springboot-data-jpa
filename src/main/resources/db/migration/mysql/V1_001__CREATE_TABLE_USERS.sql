CREATE TABLE IF NOT EXISTS users (

 id                   VARCHAR(255) NOT NULL,
 username             VARCHAR(255) NOT NULL,
 email                VARCHAR(255) NOT NULL,
 password             VARCHAR(255) NOT NULL,
 enabled              BIT(1)       NOT NULL
    DEFAULT   b'0',
 locked               BIT(1)       NOT NULL
    DEFAULT   b'0',
 credentials_expired  DATETIME             ,
 account_expired      DATETIME             ,
 created              DATETIME     NOT NULL
    DEFAULT   CURRENT_TIMESTAMP,
 updated              DATETIME     NOT NULL
    DEFAULT   CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
 deleted              DATETIME             ,

 PRIMARY KEY (id),
 UNIQUE KEY UIDX_USERS_01 (username, deleted),
 UNIQUE KEY UIDX_USERS_02 (email   , deleted)
 
) ENGINE=InnoDB;

DELIMITER //

CREATE 
    TRIGGER TRG_BI_USERS
    BEFORE INSERT ON users
    FOR EACH ROW

BEGIN
    SET new.id = uuid();
END
//

