create or replace procedure getMember(
	p_id in member.id%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from member where id=p_id;
end; 

create or replace procedure selectAddressByDong(
	p_dong in address.dong%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from address where dong like '%'||p_dong||'%';
end;

create or replace procedure insertMember(
	p_id in member.id%type,
    p_pwd in member.pwd%type,
    p_name in member.name%type,
    p_email in member.email%type,
    p_zip_num in member.zip_num%type,
    p_address in member.address%type,
    p_phone in member.phone%type
)
is

begin
	insert into member(id, pwd, name, email, zip_num, address, phone)
    values(p_id, p_pwd, p_name, p_email, p_zip_num, p_address, p_phone);
end;

create or replace procedure updateMember(
	p_id in member.id%type,
    p_pwd in member.pwd%type,
    p_name in member.name%type,
    p_email in member.email%type,
    p_zip_num in member.zip_num%type,
    p_address in member.address%type,
    p_phone in member.phone%type
)
is

begin
	update member set pwd=p_pwd, name=p_name, email=p_email, zip_num=p_zip_num,
    address=p_address, phone=p_phone
    where id=p_id;
end;

create or replace view cart_view
as 
select c.cseq, c.id, m.name as mname, c.pseq, p.name as pname,
c.quantity, p.price2, c.result, c.indate
from cart c, product p, member m
where c.pseq = p.pseq and m.id = c.id;

DROP TABLE qna CASCADE CONSTRAINTS;

create table orders(
	oseq number(10) primary key, -- 주문번호
	id varchar2(16) references member(id), -- 주문자 아이디
	indate date default sysdate -- 주문일
);

create table order_detail(
	odseq number(10) primary key, -- 주문상세번호
	oseq number(10), -- 주문번호
	pseq number(5) references product(pseq), -- 상품번호
	quantity number(5) default 1, -- 주문 수량
	result char(1) default '1' -- 1: 미처리 2: 처리
);

create or replace view order_view
as 
select d.odseq, o.oseq, o.id, o.indate, d.pseq, d.quantity, d.result,
m.name as mname, m.zip_num, m.address, m.phone,
p.name as pname, p.price2
from orders o, order_detail d, member m, product p
where o.oseq = d.oseq and o.id = m.id and d.pseq = p.pseq;

create table qna(
	qseq number(5) primary key, -- 글번호
	subject varchar2(300), -- 제목
	content varchar2(1000), -- 문의 내용
	reply varchar2(1000), -- 답변 내용
	id varchar2(20), -- 작성자(FK : member.id)
	rep char(1) default '1', -- 1: 답변무 2: 답변유
	indate date default sysdate -- 작성일
);