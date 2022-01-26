create or replace PROCEDURE selectBoard(
    p_rc OUT    SYS_REFCURSOR,
    p_sn in number,
    p_en in number
)
IS
BEGIN
    OPEN p_rc FOR
        select * from(
	 	select * from(
		select rownum as rn, b.* from((select * from board order by num desc) b) 
		) where rn >= p_sn
		) where rn <= p_en;
END;

create or replace PROCEDURE selectReply(
    p_rc OUT    SYS_REFCURSOR,
    p_num in reply.num%type
)
IS
BEGIN
    OPEN p_rc FOR
        select * from reply where boardnum=p_num;
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

create or replace procedure getBoardOneNotReadCount(
    p_num in Board.num%type,
    p_rc out sys_refcursor)
is
begin
    open p_rc for
        select * from board where num=p_num order by num desc;
end;

create or replace procedure selectMember(
    p_id in member.id%type,
    p_rc out sys_refcursor)
is
begin
    open p_rc for
        select * from member where id=p_id;
end;

create or replace procedure insertMember(
    p_id in member.id%type,
    p_pwd in member.pwd%type,
    p_name in member.name%type,
    p_email in member.email%type,
    p_phone in member.phone%type
)
is
begin
    insert into member(id, pwd, name, email, phone)
    values(p_id, p_pwd, p_name, p_email, p_phone);
    commit;
end;

create or replace procedure updateMember(
    p_id in member.id%type,
    p_pwd in member.pwd%type,
    p_name in member.name%type,
    p_email in member.email%type,
    p_phone in member.phone%type
)
is
begin
    update member set pwd=p_pwd, name=p_name, email=p_email, phone=p_phone
    where id=p_id;
    commit;
end;

create or replace procedure insertBoard(
    p_userid in board.userid%type,
    p_pass in board.pass%type,
    p_title in board.title%type,
    p_email in board.email%type,
    p_content in board.content%type,
    p_imgfilename in board.imgfilename%type
)
is
begin
    insert into board(num, userid, pass, title, content, email, imgfilename)
    values(board_seq.nextVal, p_userid, p_pass, p_title, p_content, p_email, p_imgfilename);
    commit;
end;

create or replace procedure insertReply(
    p_userid in reply.userid%type,
    p_boardnum in reply.boardnum%type,
    p_content in reply.content%type
)
is
begin
    insert into reply(num, boardnum, userid, content)
    values(reply_seq.nextVal, p_boardnum, p_userid, p_content);
    commit;
end;

create or replace procedure plusRepCount(
    p_boardnum in reply.boardnum%type
)
is
begin
    update board set replycnt = replycnt + 1 where num = p_boardnum;
    commit;
end;

create or replace procedure minusRepCount(
    p_boardnum in reply.boardnum%type
)
is
begin
    update board set replycnt = replycnt - 1 where num = p_boardnum;
    commit;
end;

create or replace procedure updateBoard(

    p_userid in board.userid%type,
    p_pass in board.pass%type,
    p_title in board.title%type,
    p_email in board.email%type,
    p_content in board.content%type,
    p_imgfilename in board.imgfilename%type,
    p_num in board.num%type
)
is
begin
    update board set pass=p_pass, email=p_email, title=p_title, content=p_content, imgfilename=p_imgfilename
    where num=p_num;
    commit;
end;

create or replace procedure getAllCount(
    p_count out number) -- 매개변수
is
    p_cnt number; -- 프로시저 내 지역변수
begin
    select count(*) into p_cnt from board;
    p_count :=p_cnt;
end;

create or replace procedure deleteBoard(
    p_num in board.num%type
)
is
begin
    delete from board where num=p_num; 
    commit;
end;

create or replace procedure deleteReply(
    p_num in reply.num%type
)
is
begin
    delete from reply where num=p_num; 
    commit;
end;