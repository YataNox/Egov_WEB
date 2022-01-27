package ezen.main.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.main.dao.BoardDAO;
import ezen.main.dto.BoardVO;
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
	
//	@Override
//	public TransferVO getBoard(Paging paging) {
//		TransferVO container = new TransferVO();
//		container.setStartNum(paging.getStartNum());
//		container.setEndNum(paging.getEndNum());
//		bdao.getBoard(container);
//		return container;
//	}
	
	@Override
	public void getBoard(HashMap<String, Object> map) {
		bdao.getBoard(map);
	}

	/*@Override
	public BoardVO getBoardOne(String num) {
		return bdao.getBoardOne(num);
	}*/
	
	/*public TransferVO getBoardOne(String num) {
		TransferVO container = new TransferVO();
		container.setNum(Integer.parseInt(num));
		bdao.getBoardOne(container);
		return container;
	}*/
	
	
	 public void getBoardOne(HashMap<String, Object> paramMap) 
	 {
		 bdao.getBoardOne(paramMap); 
	 }

	/*@Override
	public void insertBoard(BoardVO bvo) {
		bdao.insertBoard(bvo);
	}*/
	 
	public void insertBoard(HashMap<String, Object> paramMap) {
		bdao.insertBoard(paramMap);
	} 

	/*@Override
	public void updateBoard(BoardVO bvo) {
		bdao.updateBoard(bvo);
	}*/
	
	@Override
	public void updateBoard(HashMap<String, Object> paramMap){
		bdao.updateBoard(paramMap);
	}

	@Override
	public void deleteBoard(HashMap<String, Object> paramMap) {
		bdao.deleteBoard(paramMap);
	}

	/*@Override
	public TransferVO getBoardOneNotReadCount(String num) {
		TransferVO container = new TransferVO();
		container.setNum(Integer.parseInt(num));
		bdao.getBoardOneNotReadCount(container);
		return container;
	}*/
	
	@Override
	public void getBoardOneNotReadCount(HashMap<String, Object> paramMap) {
		bdao.getBoardOneNotReadCount(paramMap);
	}

	@Override
	public int getAllCount() {
		TransferVO con = new TransferVO();
		bdao.getAllCount(con); 
		return con.getCount();
	}

	/*@Override
	public TransferVO2 getReplyList(String num) {
		TransferVO2 container = new TransferVO2();
		container.setNum(Integer.parseInt(num));
		bdao.getReply(container);
		return container;
	}*/
	
	@Override
	public void getReplyList(HashMap<String, Object> paramMap2) {
		bdao.getReply(paramMap2);
	}

//	@Override
//	public void insertReply(ReplyVO rvo) {
//		bdao.insertReply(rvo);
//		bdao.plusRepCount(rvo.getBoardnum());
//	}
	
	@Override
	public void insertReply(HashMap<String, Object> paramMap) {
		bdao.insertReply(paramMap);
		bdao.plusRepCount(Integer.parseInt((String) paramMap.get("boardnum")));
	}

	/*@Override
	public void deleteReply(int num, int boardnum) {
		bdao.deleteReply(num);
		bdao.minusRepCount(boardnum);
	}*/
	
	@Override
	public void deleteReply(HashMap<String, Object> paramMap) {
		bdao.deleteReply(Integer.parseInt((String) paramMap.get("num")));
		bdao.minusRepCount(Integer.parseInt((String) paramMap.get("boardnum")));
	}
	
}
