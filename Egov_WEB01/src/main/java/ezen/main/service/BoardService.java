package ezen.main.service;

import java.util.ArrayList;

import ezen.main.dto.BoardVO;

public interface BoardService {
	ArrayList<BoardVO> getBoard();

	BoardVO getBoardOne(String num);

	void insertBoard(BoardVO bvo);

	void updateBoard(BoardVO bvo);
}
