
CREATE TABLE IF NOT EXISTS users
(
  id         INTEGER AUTO_INCREMENT,
  login      VARCHAR(45) NOT NULL,
  password   VARCHAR(45) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(45),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS contacts (
  id          INTEGER AUTO_INCREMENT,
  user_id     INTEGER NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  patronymic VARCHAR(45) NOT NULL,
  mobile_phone_number VARCHAR(15),
  home_phone_number VARCHAR(15),
  address VARCHAR(45),
  email VARCHAR(30),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

