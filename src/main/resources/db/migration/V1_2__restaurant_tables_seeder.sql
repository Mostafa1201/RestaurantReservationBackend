CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` int(11) DEFAULT NULL,
  `table_id` int(11) DEFAULT NULL,
  `no_of_seats` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted` Bit(1) DEFAULT false,
  KEY `TABLE_ID_FK1` (`table_id`),
  CONSTRAINT `TABLE_ID_FK1` FOREIGN KEY (`table_id`) REFERENCES `restaurant_table` (`id`),
  KEY `USER_ID_FK1` (`user_id`),
  CONSTRAINT `USER_ID_FK1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
