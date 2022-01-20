create table member(
	name varchar2(10),
	userid varchar2(10),
	userpwd varchar2(10),
	email varchar2(20),
	phone varchar2(15),
	admin number(1) default 0, -- 0은 일반 사용자, 1은 관리자
	primary key(userid)
);

DROP TABLE member CASCADE CONSTRAINTS;

insert into member values('홍길동', 'scott', '1234', 'gmd@naver.com','010-1234-1234', 0);
insert into member values('이소미', 'somi', '1234', 'gmd@naver.com','010-1234-1234', 0);
insert into member values('하상오', 'sang12', '1234', 'h12@naver.com','010-5555-6666', 0);
insert into member values('김윤승', 'light', '1234', 'yoon1@daum.net','010-9999-1111', 0);

select * from member;