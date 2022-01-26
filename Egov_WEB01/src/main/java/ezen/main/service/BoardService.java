package ezen.main.service;

import java.util.HashMap;

import ezen.main.dto.BoardVO;
import ezen.main.dto.ReplyVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;

public interface BoardService {
	/* ArrayList<BoardVO> getBoard(); */
//	TransferVO getBoard(Paging paging);
	void getBoard(HashMap<String, Object> map);

	/* BoardVO getBoardOne(String num); */
	/* TransferVO getBoardOne(String num); */
	void getBoardOne(HashMap<String, Object> paramMap);

	void insertBoard(BoardVO bvo);

	void updateBoard(BoardVO bvo);

	void deleteBoard(String num);

	TransferVO getBoardOneNotReadCount(String num);

	int getAllCount();

	/* TransferVO2 getReplyList(String num); */
	void getReplyList(HashMap<String, Object> paramMap2);

	void insertReply(ReplyVO rvo);

	void deleteReply(int num, int boardnum);
}
