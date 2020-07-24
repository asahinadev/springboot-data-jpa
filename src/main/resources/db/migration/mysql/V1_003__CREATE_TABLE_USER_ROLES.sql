CREATE TABLE IF NOT EXISTS user_roles (

  user_id VARCHAR(255) NOT NULL,
  role_id VARCHAR(255) NOT NULL,

  PRIMARY KEY (user_id,role_id),
  FOREIGN KEY FK_USERROLES_01 (user_id) REFERENCES users(id),
  FOREIGN KEY FK_USERROLES_02 (role_id) REFERENCES roles(id)

) ENGINE=InnoDB;
