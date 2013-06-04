CREATE TABLE "member" 
(
   "user_id" CHARACTER VARYING(30) NOT NULL, 
   "password" CHARACTER VARYING(30), 
   CONSTRAINT pk_member_user_id PRIMARY KEY("user_id") 
);


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
   "frequency" INTEGER,
   "category" character varying(30),
   CONSTRAINT pk_bookmark_bookmark_id PRIMARY KEY("bookmark_id"), 
   FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT 
);


CREATE TABLE friendship 
(
	friendship_id INTEGER AUTO_INCREMENT(1, 1)NOT NULL, 
	user_id CHARACTER VARYING(30), 
	friend_id CHARACTER VARYING(30), 
	[status] CHARACTER VARYING(30), 
	FOREIGN KEY(user_id)REFERENCES member(user_id)ON DELETE CASCADE ON UPDATE RESTRICT, 
	CONSTRAINT pk_friendship_friendship_id PRIMARY KEY(friendship_id)
);


CREATE TABLE message(
	message_id integer AUTO_INCREMENT NOT NULL,
	user_id character varying(30),
	friend_id character varying(30),
	title character varying(50),
	contents character varying(1073741823),
	message_date datetime,
	read_num integer DEFAULT 0 NOT NULL,
	message_type character varying(30) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES member(user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT pk_message_message_id PRIMARY KEY(message_id)
);



CREATE TABLE "member_info" 
(
   "user_id" CHARACTER VARYING(30), 
   "name" CHARACTER VARYING(30), 
   "email" CHARACTER VARYING(30), 
   "me2day_key" CHARACTER VARYING(15), 
   "status" CHARACTER VARYING(30) DEFAULT '회원', 
   "img_url" CHARACTER VARYING(500), 
   "bg_img_url" CHARACTER VARYING(500), 
   FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT 
);

CREATE TABLE "register_time" 
(
   "register_id" INTEGER AUTO_INCREMENT(1, 1) NOT NULL, 
   "user_id" CHARACTER VARYING(30), 
   "register_time" DATE, 
   "leave_time" DATE, 
   CONSTRAINT pk_register_time_register_id PRIMARY KEY("register_id") 
);

CREATE TABLE "login_time" 
(
   "login_id" INTEGER AUTO_INCREMENT(1, 1) NOT NULL, 
   "user_id" CHARACTER VARYING(30) NOT NULL, 
   "login_time" DATETIME, 
   "logout_time" DATETIME, 
   CONSTRAINT pk_login_time_login_id_user_id PRIMARY KEY("login_id", "user_id") 
);


CREATE TABLE "design" 
(
   "user_id" CHARACTER VARYING(30) NOT NULL, 
   "type" CHARACTER VARYING(100) DEFAULT 'MacOS', 
   CONSTRAINT pk_design_user_id PRIMARY KEY("user_id"),
   CONSTRAINT "design_userid__member_userid" FOREIGN KEY("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT
);


CREATE TABLE "bookmarkship"(
	"bookmark_id" integer AUTO_INCREMENT NOT NULL,
	"bookmark_name" character varying(255),
	"bookmark_url" character varying(255),
	"bookmark_descript" character varying(4096),
	"user_id" character varying(30),
	"friend_id" character varying(30),
	"status" character varying(30),
	FOREIGN KEY ("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT pk_bookmarkship_bookmark_id PRIMARY KEY("bookmark_id")
);


/**음성인식과 관련된 테이블*/
CREATE TABLE "speech_site" 
(
   "url" CHARACTER VARYING(255) NOT NULL, 
   "speech" CHARACTER VARYING(255), 
   CONSTRAINT pk PRIMARY KEY("url") 
);
INSERT INTO speech_site (url, speech) VALUES ('http://www.google.co.kr', '구글');
INSERT INTO speech_site (url, speech) VALUES ('http://www.daum.net', '다음');
INSERT INTO speech_site (url, speech) VALUES ('http://www.naver.com', '네이버');
INSERT INTO speech_site (url, speech) VALUES ('http://nate.com', '네이트');
INSERT INTO speech_site (url, speech) VALUES ('http://www.facebook.com', '페이스북');
INSERT INTO speech_site (url, speech) VALUES ('http://www.linkedin.com', '링크드인');
INSERT INTO speech_site (url, speech) VALUES ('http://www.twitter.com', '트위터');
INSERT INTO speech_site (url, speech) VALUES ('http://www.donga.com/', '동아일보');
INSERT INTO speech_site (url, speech) VALUES ('http://www.tistory.cm', '티스토리');
INSERT INTO speech_site (url, speech) VALUES ('http://www.chosun.com/', '조선일보');
INSERT INTO speech_site (url, speech) VALUES ('http://joongang.joinsmsn.com/', '중앙일보');
INSERT INTO speech_site (url, speech) VALUES ('http://www.11st.co.kr/html/main.html', '11 번가');
INSERT INTO speech_site (url, speech) VALUES ('http://www.gmarket.co.kr/', '지마켓');
INSERT INTO speech_site (url, speech) VALUES ('http://www.mk.co.kr/', '매일경제');
INSERT INTO speech_site (url, speech) VALUES ('http://www.auction.co.kr/', '옥션');
INSERT INTO speech_site (url, speech) VALUES ('http://www.khan.co.kr/', '경향신문');
INSERT INTO speech_site (url, speech) VALUES ('http://www.nate.com/?f=cymain', '싸이월드');

/**category 테이블*/
CREATE TABLE "category"(
"category_id" integer AUTO_INCREMENT(1,1) NOT NULL,
"category_name" character varying(255),
"user_id" character varying(30),
CONSTRAINT pk_category_category_id PRIMARY KEY("category_id"),
FOREIGN KEY ("user_id") REFERENCES "member"("user_id") ON DELETE RESTRICT ON UPDATE RESTRICT
);



