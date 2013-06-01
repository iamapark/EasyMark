CREATE TABLE IF NOT EXISTS nhneasymark.bookmarkship (
  `bookmark_id` int(11) NOT NULL AUTO_INCREMENT,
  `bookmark_name` varchar(255) NOT NULL,
  `bookmark_url` varchar(255) NOT NULL,
  `bookmark_descript` varchar(4096) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `friend_id` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`bookmark_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.category (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`category_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.design (
  `user_id` varchar(30) NOT NULL,
  `type` varchar(100) DEFAULT 'MacOS',
  PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.friendship (
  `friendship_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `friend_id` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`friendship_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.login_time (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `login_time` datetime NOT NULL,
  `logout_time` datetime NOT NULL,
  PRIMARY KEY (`login_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.member (
  `user_id` varchar(30) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.member_info (
  `user_id` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `emailmembermember` varchar(30) NOT NULL,
  `me2day_key` varchar(15) DEFAULT NULL,
  `status` varchar(30) NOT NULL DEFAULT '회원',
  `img_url` varchar(500) DEFAULT NULL,
  `bg_img_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.message (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `friend_id` varchar(30) NOT NULL,
  `title` varchar(50) NOT NULL,
  `contents` varchar(4096) NOT NULL,
  `message_date` date NOT NULL,
  PRIMARY KEY (`message_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.register_time (
  `register_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `register_time` date NOT NULL,
  `leave_time` date NOT NULL,
  PRIMARY KEY (`register_id`)
);

CREATE TABLE IF NOT EXISTS nhneasymark.speech_site (
  `url` varchar(255) NOT NULL,
  `speech` varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS nhneasymark.bookmark (
  `bookmark_id` int(11) NOT NULL AUTO_INCREMENT,
  `bookmark_name` varchar(255) NOT NULL,
  `bookmark_url` varchar(255) NOT NULL,
  `bookmark_descript` varchar(4096) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `pos_x` int(11) NOT NULL,
  `pos_y` int(11) NOT NULL,
  `img_url` varchar(100) NOT NULL,
  `frequency` int(11) NOT NULL DEFAULT '0',
  `category` varchar(30) NOT NULL,
  PRIMARY KEY (`bookmark_id`)
);