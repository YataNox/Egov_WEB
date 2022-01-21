package ezen.main.service;

import ezen.main.dto.BoardVO;
import ezen.main.dto.TransferVO;

public interface BoardService {
	/* ArrayList<BoardVO> getBoard(); */
	TransferVO getBoard();

	BoardVO getBoardOne(String num);

	void insertBoard(BoardVO bvo);

	void updateBoard(BoardVO bvo);

	void deleteBoard(String num);
}
