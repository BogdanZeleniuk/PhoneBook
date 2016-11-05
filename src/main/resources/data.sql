
INSERT INTO lardi.users
(login,password,full_name) VALUES
  ('Bill', '112233', 'user'),
  ('John', '112233', 'user'),
  ('Mark', '112233', 'user');

INSERT INTO lardi.user_roles
(role,user_id) VALUES
  ('ROLE_USER',1),
  ('ROLE_USER',2),
  ('ROLE_USER',3);

INSERT INTO lardi.contacts
(first_name, last_name, patronymic, mobile_phone_number, home_phone_number, address, email, user_id) VALUES
  ('Ivan','Ivanov','Ivanovych','+380(66)1234567','','USA','bill@gmail.com', 1),
  ('Petro','Petrov','Petrovych','+380(66)9876543','+380(44)1122334','USA','mark@gmail.com', 1),
  ('Sydor','Sydorov','Sydorovych','+380(99)1234567','','USA','barak@gmail.com', 1),
  ('Mykola','Mykolaiov','Mykolaiovych','+380(99)9876543','','USA','michel@gmail.com', 1),
  ('Aleksandr','Aleksandrov','Aleksandrovych','+380(50)5557799','+380(44)0000009','UK','david@gmail.com', 2),
  ('Vasyl','Vasyliov','Vasyliovych','+380(00)1100999','','USA','steve@gmail.com', 2),
  ('Viktor','Viktorov','Viktorovych','+380(00)2244888','','USA','tim@gmail.com', 2),
  ('Kostia','Konstantynov','Konstantynovych','+380(69)8881188','+380(44)1111119','USA','jim@gmail.com', 3),
  ('Anton','Antonov','Antonovych','+380(67)9000001','','UK','david@gmail.com', 3);