create or replace procedure getMember(
	p_id in member.id%type,
	p_rc out SYS_REFCURSOR  
)
is

begin
	open p_rc for
		select * from member where id=p_id;
end; 