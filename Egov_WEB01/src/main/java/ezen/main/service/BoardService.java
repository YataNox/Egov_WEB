package ezen.main.service;

import ezen.main.dto.BoardVO;
import ezen.main.dto.Paging;
import ezen.main.dto.TransferVO;

public interface BoardService {
	/* ArrayList<BoardVO> getBoard(); */
	TransferVO getBoard(Paging paging);

	/* BoardVO getBoardOne(String num); */
	TransferVO getBoardOne(String num);

	void insertBoard(BoardVO bvo);

	void updateBoard(BoardVO bvo);

	void deleteBoard(String num);

	TransferVO getBoardOneNotReadCount(String num);

	int getAllCount();
}
