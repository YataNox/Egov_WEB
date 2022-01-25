select * from member;
select * from board;
select * from REPLY;

create or replace PROCEDURE selectBoard(
    p_rc OUT    SYS_REFCURSOR)
IS
BEGIN
    OPEN p_rc FOR
        SELECT * FROM BOARD ORDER BY NUM DESC
END;

create or replace procedure selectBoardOne(
    p_num in Board.num%type,
    p_rc out sys_refcursor)
is
begin
    update Board set ReadCount = readcount +1 where num=p_num;
    commit;

    open p_rc for
        select * from board where num=p_num order by num desc;
end;

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

alter table member modify zip_num varchar2(20) null;
alter table member modify address varchar2(100) null;
alter table member modify phone varchar2(20) null;

insert into member(id, pwd, name, zip_num, address, phone, email) values
('scott', '1234', '홍길동', '133-110', '서울시 성동구 성수동1가 1번지21호', '017-777-7777','acc@abc.com');

insert into member(id, pwd, name, zip_num, address, phone, email) values
('one', '1111', '김나리', '133-110', '서울시 성동구 성수동1가 1번지21호', '017-777-7777','acc@abc.com');

insert into member(id, pwd, name, zip_num, address, phone, email)values
('two', '2222', '김길동', '130-120', '서울시 송파구 잠실2동 리센츠 아파트 201동 505호', '011-123-4567','acc@abc.com');

insert into reply(num, boardnum, userid, content)
		values(reply_seq.nextVal, 164, 'adfxcgcx', '댓글 테스트');