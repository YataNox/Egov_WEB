package ezen.main.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.BoardVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;

@Mapper(value="BoardDAO")
public interface BoardDAO {
	/* ArrayList<BoardVO> getBoard(); */
	void getBoard(TransferVO container);
	
	/* BoardVO getBoardOne(String num); */
	void getBoardOne(TransferVO container);

	void insertBoard(BoardVO bvo);

	void updateBoard(BoardVO bvo);

	void deleteBoard(String num);

	void getBoardOneNotReadCount(TransferVO container);

	void getAllCount(TransferVO con);

	void getReply(TransferVO2 container);
}
