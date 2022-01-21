package ezen.main.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.BoardVO;
import ezen.main.dto.TransferVO;

@Mapper(value="BoardDAO")
public interface BoardDAO {
	/* ArrayList<BoardVO> getBoard(); */
	void getBoard(TransferVO container);
	
	BoardVO getBoardOne(String num);

	void insertBoard(BoardVO bvo);

	void updateBoard(BoardVO bvo);

	void deleteBoard(String num);
}
