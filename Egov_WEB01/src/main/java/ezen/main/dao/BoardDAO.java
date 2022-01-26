package ezen.main.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.BoardVO;
import ezen.main.dto.ReplyVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;

@Mapper(value="BoardDAO")
public interface BoardDAO {
	/* ArrayList<BoardVO> getBoard(); */
	/* void getBoard(TransferVO container); */
	 void getBoard(HashMap<String, Object> map); 
	
	/* BoardVO getBoardOne(String num); */
	void getBoardOne(HashMap<String, Object> paramMap);

	void insertBoard(BoardVO bvo);

	void updateBoard(BoardVO bvo);

	void deleteBoard(String num);

	/* void getBoardOneNotReadCount(TransferVO container); */
	void getBoardOneNotReadCount(HashMap<String, Object> paramMap);

	void getAllCount(TransferVO con);

	/* void getReply(TransferVO2 container); */
	void getReply(HashMap<String, Object> paramMap2);

	void insertReply(ReplyVO rvo);

	void plusRepCount(int boardnum);

	void deleteReply(int num);

	void minusRepCount(int boardnum);
}
