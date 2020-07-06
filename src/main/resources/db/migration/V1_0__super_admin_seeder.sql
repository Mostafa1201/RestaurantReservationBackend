CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(255) DEFAULT NULL,
`email` varchar(255) NOT NULL,
`password` varchar(255) NOT NULL,
`mobile` varchar(255) DEFAULT NULL,
`type` varchar(255) DEFAULT 'user',
`created_at` datetime NOT NULL,
`updated_at` datetime NOT NULL,
`deleted` Bit(1) DEFAULT false
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO user (name, email, password, mobile, type, created_at, updated_at)
VALUES ('Admin','admin@admin.com','$2a$10$YXzYx90u/Fs6ggnpLjEWse1l3TnL4ddd2baZK3Ll6huSMRbZ2Nigq','012312312','admin',NOW(),NOW());
