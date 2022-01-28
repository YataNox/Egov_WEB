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