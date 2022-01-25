package ezen.main.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.main.dao.BoardDAO;
import ezen.main.dto.BoardVO;
import ezen.main.dto.Paging;
import ezen.main.dto.ReplyVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;
import ezen.main.service.BoardService;

@Service(value="BoardService")
public class BoardServiceimpl extends EgovAbstractServiceImpl implements BoardService{

	@Resource(name="BoardDAO") BoardDAO bdao;

	//@Override
	//public ArrayList<BoardVO> getBoard() {
	//	return bdao.getBoard();
	//	}
	
	@Override
	public TransferVO getBoard(Paging paging) {
		TransferVO container = new TransferVO();
		container.setStartNum(paging.getStartNum());
		container.setEndNum(paging.getEndNum());
		bdao.getBoard(container);
		return container;
	}

	/*@Override
	public BoardVO getBoardOne(String num) {
		return bdao.getBoardOne(num);
	}*/
	
	public TransferVO getBoardOne(String num) {
		TransferVO container = new TransferVO();
		container.setNum(Integer.parseInt(num));
		bdao.getBoardOne(container);
		return container;
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

	@Override
	public TransferVO getBoardOneNotReadCount(String num) {
		TransferVO container = new TransferVO();
		container.setNum(Integer.parseInt(num));
		bdao.getBoardOneNotReadCount(container);
		return container;
	}

	@Override
	public int getAllCount() {
		TransferVO con = new TransferVO();
		bdao.getAllCount(con); 
		return con.getCount();
	}

	@Override
	public TransferVO2 getReplyList(String num) {
		TransferVO2 container = new TransferVO2();
		container.setNum(Integer.parseInt(num));
		bdao.getReply(container);
		return container;
	}

	@Override
	public void insertReply(ReplyVO rvo) {
		bdao.insertReply(rvo);
		bdao.plusRepCount(rvo.getBoardnum());
	}

	@Override
	public void deleteReply(int num, int boardnum) {
		bdao.deleteReply(num);
		bdao.minusRepCount(boardnum);
	}
	
}
