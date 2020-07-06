CREATE TABLE IF NOT EXISTS `restaurant_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) DEFAULT NULL,
  `no_of_seats` int(11) NOT NULL,
  `available_seats` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted` Bit(1) DEFAULT false,  
  KEY `USER_ID_FK` (`user_id`),
  CONSTRAINT `USER_ID_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO restaurant_table (name, no_of_seats, available_seats, user_id, created_at, updated_at) VALUES 
('Two Seats table',2,2,1,NOW(),NOW()),
('Two Seats table',2,2,1,NOW(),NOW()),
('Two Seats table',2,2,1,NOW(),NOW()),
('Two Seats table',2,2,1,NOW(),NOW()),
('Five Seats table',5,5,1,NOW(),NOW()),
('Five Seats table',5,5,1,NOW(),NOW()),
('Five Seats table',5,5,1,NOW(),NOW()),
('Five Seats table',5,5,1,NOW(),NOW()),
('Five Seats table',5,5,1,NOW(),NOW()),
('Five Seats table',5,5,1,NOW(),NOW()),
('Five Seats table',5,5,1,NOW(),NOW()),
('Ten Seats table',10,10,1,NOW(),NOW()),
('Ten Seats table',10,10,1,NOW(),NOW());
