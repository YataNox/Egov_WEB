select * from member;
select * from board;

DROP TABLE member CASCADE CONSTRAINTS;
CREATE TABLE member
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	email varchar2(40) NOT NULL,
	zip_num varchar2(10) NOT NULL,
	address varchar2(100) NOT NULL,
	phone varchar2(20) NOT NULL,
	useyn char(1) DEFAULT 'y',
	indate date DEFAULT sysdate,
	PRIMARY KEY (id)
);

insert into member(id, pwd, name, zip_num, address, phone, email) values
('scott', '1234', '홍길동', '133-110', '서울시 성동구 성수동1가 1번지21호', '017-777-7777','acc@abc.com');

insert into member(id, pwd, name, zip_num, address, phone, email) values
('one', '1111', '김나리', '133-110', '서울시 성동구 성수동1가 1번지21호', '017-777-7777','acc@abc.com');

insert into member(id, pwd, name, zip_num, address, phone, email)values
('two', '2222', '김길동', '130-120', '서울시 송파구 잠실2동 리센츠 아파트 201동 505호', '011-123-4567','acc@abc.com');