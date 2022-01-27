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

	/* void insertBoard(BoardVO bvo); */
	void insertBoard(HashMap<String, Object> paramMap);

	/* void updateBoard(BoardVO bvo); */
	void updateBoard(HashMap<String, Object> paramMap);

	void deleteBoard(HashMap<String, Object> paramMap);

	/* TransferVO getBoardOneNotReadCount(String num); */
	void getBoardOneNotReadCount(HashMap<String, Object> paramMap);

	int getAllCount();

	/* TransferVO2 getReplyList(String num); */
	void getReplyList(HashMap<String, Object> paramMap2);

	/* void insertReply(ReplyVO rvo); */
	void insertReply(HashMap<String, Object> paramMap);

	/* void deleteReply(int num, int boardnum); */

	void deleteReply(HashMap<String, Object> paramMap);
}
