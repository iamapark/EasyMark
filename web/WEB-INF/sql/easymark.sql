    CREATE TABLE "member" 
(
   "user_id" CHARACTER VARYING(30) NOT NULL, 
   "password" CHARACTER VARYING(30), 
   CONSTRAINT pk_member_user_id PRIMARY KEY("user_id") 
);

/**
 * MySQL 버전 
 * CREATE  TABLE `member` (
  `user_id` VARCHAR(30) NOT NULL ,
  `password` VARCHAR(30) NULL ,
  PRIMARY KEY (`user_id`) );
*/

CREATE TABLE "admin" 
(
   "admin_id" CHARACTER VARYING(30) NOT NULL, 
   "password" CHARACTER VARYING(30), 
   CONSTRAINT pk_admin_admin_id PRIMARY KEY("admin_id") 
);

CREATE TABLE "bookmark" 
(
   "bookmark_id" INTEGER AUTO_INCREMENT(1, 1) NOT NULL, 
   "bookmark_name" CHARACTER VARYING(255), 
   "bookmark_url" CHARACTER VARYING(255), 
   "bookmark_descript" CHARACTER VARYING(4096), 
   "user_id" CHARACTER VARYING(30), 
   "status" CHARACTER VARYING(30), 
   "pos_x" INTEGER, 
   "pos_y" INTEGER, 
   "img_url" CHARACTER VARYING(100), 
   CONSTRAINT pk_bookmark_bookmark_id PRIMARY KEY("bookmark_id"), 
   FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT 
);

/*Called only by DBA or members of DBA group */
CALL change_owner('design', 'DBA') ON CLASS db_authorizations;

CREATE TABLE "friendship" 
(
   "friendship_id" INTEGER AUTO_INCREMENT(1, 1) NOT NULL, 
   "user_id" CHARACTER VARYING(30), 
   "friend_id" CHARACTER VARYING(30), 
   "status" CHARACTER VARYING(30), 
   CONSTRAINT pk_friendship_friendship_id PRIMARY KEY("friendship_id"), 
   FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT 
);


CREATE TABLE "message" 
(
   "message_id" INTEGER AUTO_INCREMENT NOT NULL, 
   "user_id" CHARACTER VARYING(30), 
   "friend_id" CHARACTER VARYING(30), 
   "title" CHARACTER VARYING(50), 
   "contents" CHARACTER VARYING(1073741823), 
   "message_date" DATE, 
   FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT, 
   CONSTRAINT pk_message_message_id PRIMARY KEY("message_id") 
);





//2013-04-30 추가분
/**MySQL 버전
 *
CREATE TABLE `member_info` (
  `user_id` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `me2day_key` varchar(15) DEFAULT NULL,
  `status` varchar(30) DEFAULT '회원',
  `img_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

*/

CREATE TABLE "member_info" 
(
   "user_id" CHARACTER VARYING(30), 
   "name" CHARACTER VARYING(30), 
   "email" CHARACTER VARYING(30), 
   "me2day_key" CHARACTER VARYING(15), 
   "status" CHARACTER VARYING(30) DEFAULT '회원', 
   FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT 
);

CREATE TABLE "register_time" 
(
   "user_id" CHARACTER VARYING(30) NOT NULL, 
   "register_time" DATE, 
   "leave_time" DATE, 
   CONSTRAINT pk_register_time_user_id PRIMARY KEY("user_id") 
);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('iamapark89@me2day', DATE'01/01/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('pkwlfkf89@me2day', DATE'01/05/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('kaka', DATE'02/01/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('test1', DATE'02/08/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('test2', DATE'02/15/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('dd', DATE'02/28/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('aa', DATE'03/01/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('ww', DATE'03/05/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('ee', DATE'04/01/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('rr', DATE'04/02/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('riot', DATE'04/03/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('1', DATE'04/03/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('2', DATE'04/03/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('4', DATE'04/04/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('5', DATE'04/04/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('6', DATE'04/05/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('7', DATE'04/06/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('8', DATE'04/06/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('9', DATE'04/07/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('0', DATE'04/08/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('z', DATE'04/08/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('x', DATE'04/08/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('c', DATE'04/08/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('v', DATE'04/09/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('b', DATE'04/05/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('bdbd827@me2day', DATE'01/05/2013', NULL);
INSERT INTO register_time ("user_id", "register_time", "leave_time") VALUES ('kaka2', DATE'01/05/2014', NULL);



CREATE TABLE "login_time" 
(
   "user_id" CHARACTER VARYING(30) NOT NULL, 
   "login_time" DATE, 
   "logout_time" DATE, 
   CONSTRAINT pk_login_time_user_id PRIMARY KEY("user_id") 
);



/**MySQL 버전
 * CREATE  TABLE `nhneasymark`.`design` (
  `user_id` VARCHAR(30) NOT NULL ,
  `type` VARCHAR(100) NULL DEFAULT 'MacOS' ,
  PRIMARY KEY (`user_id`) );*/
CREATE TABLE "design" 
(
   "user_id" CHARACTER VARYING(30) NOT NULL, 
   "type" CHARACTER VARYING(100) DEFAULT 'MacOS', 
   CONSTRAINT "design_userid__member_userid" FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT, 
   CONSTRAINT pk_design_user_id PRIMARY KEY("user_id") 
);


