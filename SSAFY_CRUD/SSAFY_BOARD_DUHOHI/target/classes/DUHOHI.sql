DROP DATABASE IF EXISTS duhohi_board;
CREATE DATABASE duhohi_board DEFAULT CHARACTER SET utf8mb4;

USE duhohi_board;

CREATE TABLE `board` (
	`id` INT AUTO_INCREMENT,
    `writer` VARCHAR(20) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `content` TEXT,
    `view_cnt` INT DEFAULT 0,
    `reg_date` TIMESTAMP DEFAULT now(),
    PRIMARY KEY(id)
);

CREATE TABLE `uploadfile` (
	`fileId` INT AUTO_INCREMENT PRIMARY KEY,
    `boardId` INT NOT NULL,
    FOREIGN KEY (`boardId`) REFERENCES `board`(`id`) ON DELETE CASCADE,
    `fileName` VARCHAR(300) NOT NULL,
    `orgFileName` VARCHAR(300) NOT NULL
);

CREATE TABLE `curriculum` (
	`code` INT NOT NULL PRIMARY KEY,
    `name` VARCHAR(40) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(40) NOT NULL,
  `password` varchar(60) NOT NULL,
  `name` varchar(40) NOT NULL,
  `img` varchar(60) DEFAULT NULL,
  `orgImg` varchar(60) DEFAULT NULL,
  `curriculum_code` INT NOT NULL,
  `authority` varchar(10) DEFAULT '일반유저',
  CONSTRAINT `curriculum_fk` FOREIGN KEY (`curriculum_code`) REFERENCES `curriculum`(`code`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `review` (
	`reviewId` INT AUTO_INCREMENT PRIMARY KEY,
    `boardId` INT NOT NULL,
    FOREIGN KEY (`boardId`) REFERENCES `board`(`id`) ON DELETE CASCADE,
    `writer` VARCHAR(40) NOT NULL,
    `content` VARCHAR(100) NOT NULL,
    `regDate` DATETIME DEFAULT NOW()
)ENGINE=InnoDB;

CREATE TABLE `likeBoard` (
	`likeId` INT AUTO_INCREMENT PRIMARY KEY,
	`boardId` INT NOT NULL,
    `userId` varchar(40) NOT NULL,
    FOREIGN KEY (`boardId`) REFERENCES `board`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`userId`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

INSERT INTO `curriculum` 
VALUES (100, 'Python'), (200, 'Java'), (300, 'Embedded'), (400, 'Mobile');

-- 관리자 id=ssafy, password=1234
INSERT INTO users (id, password, name, curriculum_code, authority)
VALUES ("peach", "$2a$10$rF6cosXLqmDO7WLFgnr95OQUGsakphkV42BvRe25Kh4OiRhcApU8C", "오지혜", 200, "관리자"),
("duhui", "$2a$10$A2wacMqXg76Kqe9b61a1Nuar6Np566j1QXmxv0ln/BT64j/B6MQ8m", "김두희", 200, "일반유저"),
('hohee', '$2a$10$ELU38KKIaGr/NIinPrw39uQY1zlhfgz8RxBYYkyI4I0vKGQTqUwz6', '호히', '200', '일반유저'),
("오정빈", "test", "오정빈", 200, "일반유저"),
("김민태", "test", "김민태", 200, "일반유저"),
("김민정", "test", "김민정", 200, "일반유저"),
("파이썬쟁이", "test", "파이썬쟁이", 100, "파이썬유저"),
("김보연", "test", "김보연", 200, "일반유저"),
("김소희", "test", "김소희", 200, "일반유저"),
("민병기", "test", "민병기", 200, "일반유저"),
("박동건", "test", "박동건", 200, "일반유저"),
("박성준", "test", "박성준", 200, "일반유저"),
("박승휘", "test", "박승휘", 200, "일반유저"),
("박재민", "test", "박재민", 200, "일반유저"),
("송채은", "test", "송채은", 200, "일반유저"),
("양을필", "test", "양을필", 200, "일반유저"),
("염순원", "test", "염순원", 200, "일반유저"),
("유승윤", "test", "유승윤", 200, "일반유저"),
("윤다정", "test", "윤다정", 200, "독립유저"),
("윤석준", "test", "윤석준", 200, "일반유저"),
("이은석", "test", "이은석", 200, "일반유저"),
("이주용", "test", "이주용", 200, "일반유저"),
("이창주", "test", "이창주", 200, "일반유저"),
("전혜련", "test", "전혜련", 200, "일반유저"),
("정민영", "test", "정민영", 200, "일반유저"),
("조윤상", "test", "조윤상", 200, "일반유저"),
("채현종", "test", "채현종", 200, "일반유저"),
("한상빈", "test", "한상빈", 200, "일반유저"),
("홍성민", "test", "홍성민", 200, "일반유저");

INSERT INTO board(title, writer, content, reg_date) 
VALUES ("8반의 시작","복숭아","애정합니다 8반", "23-01-11 09:00:00"),
	   ("호희 졸업 축하합니다", "김두희", "최고 쨈민이 짱", "23-02-15 09:32:20"),
       ("두희 생일 축하합니다", "호히", "예<br>뭐<br>형<br>생신<br>경축드립니다", "23-02-28 21:46:32"),
       ("MZ 생일 축하합니다", "호히", "우리의 MZ<br>김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고김민정최고<br>김민정최고김민정최고김민정최고김민정최고김민정최고<br>MZ만세<br>", "23-04-28 21:46:32"),
       ("민태 생일 축하합니다", "호히", "김민태 생일", "23-08-24 21:46:32"),
       ("보연 생일 축하합니다", "호히", "김보연 생일", "23-11-13 21:46:32"),
       ("소희 생일 축하합니다", "호히", "김소희 생일", "23-01-15 21:46:32"),
       ("병기 생일 축하합니다", "호히", "민병기 생일", "23-06-20 21:46:32"),
       ("동건 생일 축하합니다", "호히", "박동건 생일", "23-07-27 21:46:32"),
       ("성준 생일 축하합니다", "호히", "박성준 생일", "23-05-16 21:46:32"),
       ("승휘 생일 축하합니다", "호히", "박승휘 생일", "23-12-29 21:46:32"),
       ("성준 생일 축하합니다", "호히", "박성준 생일", "23-05-16 21:46:32"),
       ("잼민 생일 축하합니다", "호히", "박재민 생일", "23-08-24 21:46:32"),
       ("챙 생일 축하합니다", "호히", "송채은 생일", "23-04-02 21:46:32"),
       ("을필 생일 축하합니다", "호히", "양을필 생일", "23-08-03 21:46:32"),
       ("순원 생일 축하합니다", "호히", "염순원 생일", "23-12-15 21:46:32"),
       ("탈스터디 한 정빈 생일 축하합니다", "호히", "오정빈 생일", "23-01-03 21:46:32"),
       ("프론트천재 생일 축하합니다", "호히", "유승윤 생일", "23-03-08 21:46:32"),
       ("독립한 딸 생일 축하합니다", "호히", "윤다정 생일", "23-05-16 21:46:32"),
       ("석준 생일 축하합니다", "호히", "윤석준 생일", "23-04-07 21:46:32"),
       ("은석 생일 축하합니다", "호히", "윤다정 생일", "23-06-08 21:46:32"),
       ("애옹 생일 축하합니다", "호히", "이주용 생일", "23-08-26 21:46:32"),
       ("창주 생일 축하합니다", "호히", "이창주 생일", "23-08-25 21:46:32"),
       ("혜련 생일 축하합니다", "호히", "전혜련 생일", "23-05-31 21:46:32"),
       ("민영 생일 축하합니다", "호히", "정민영 생일", "23-05-01 21:46:32"),
       ("물구나무해주세요 생일 축하합니다", "호히", "조윤상 생일", "23-02-16 21:46:32"),
       ("현종 생일 축하합니다", "호히", "채현종 생일", "23-02-10 21:46:32"),
       ("자상빈 생일 축하합니다", "호히", "한상빈 생일", "23-06-10 21:46:32"),
       ("성민 생일 축하합니다", "호히", "홍성민 생일", "23-08-26 21:46:32");
       
INSERT INTO review
VALUES (0, 3, "김두희", "댓글 테스트", NOW());

INSERT INTO `likeBoard`
VALUES (0, 1, 'duhui')
, (0, 1, 'hohee')
, (0, 4, 'hohee');

SELECT * FROM uploadFile;
SELECT * FROM users;
SELECT * FROM board;
SELECT * FROM review;
SELECT * FROM likeboard;