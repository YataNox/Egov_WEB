package ezen.main.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.main.dao.BoardDAO;
import ezen.main.dto.BoardVO;
import ezen.main.dto.TransferVO;
import ezen.main.service.BoardService;

@Service(value="BoardService")
public class BoardServiceimpl extends EgovAbstractServiceImpl implements BoardService{

	@Resource(name="BoardDAO") BoardDAO bdao;

	//@Override
	//public ArrayList<BoardVO> getBoard() {
	//	return bdao.getBoard();
	//	}
	
	@Override
	public TransferVO getBoard() {
		TransferVO container = new TransferVO();
		bdao.getBoard(container);
		return container;
	}

	@Override
	public BoardVO getBoardOne(String num) {
		return bdao.getBoardOne(num);
	}

	@Override
	public void insertBoard(BoardVO bvo) {
		bdao.insertBoard(bvo);
	}

	@Override
	public void updateBoard(BoardVO bvo) {
		bdao.updateBoard(bvo);
	}

	@Override
	public void deleteBoard(String num) {
		bdao.deleteBoard(num);
	}
	
}
