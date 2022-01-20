package ezen.main.dao;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.BoardVO;

@Mapper(value="BoardDAO")
public interface BoardDAO {
	ArrayList<BoardVO> getBoard();

	BoardVO getBoardOne(String num);
}
