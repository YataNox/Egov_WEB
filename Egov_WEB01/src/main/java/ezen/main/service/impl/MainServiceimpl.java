package ezen.main.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.main.dao.MainDAO;
import ezen.main.dto.MemberVO;
import ezen.main.dto.TransferVO;
import ezen.main.service.MainService;

@Service(value="MainService")
public class MainServiceimpl extends EgovAbstractServiceImpl implements MainService{

	@Resource(name="MainDAO") MainDAO mdao;
	
	/*@Override
	public MemberVO getMember(String id) {		
		return mdao.getMember(id);
	}*/
	
	/*@Override
	public TransferVO getMember(String id) {
		TransferVO container = new TransferVO();
		container.setId(id);
		mdao.getMember(container);
		return container;
	}*/
	
	@Override
	public void getMember(HashMap<String, Object> paramMap) {
		mdao.getMember(paramMap);
	}

	@Override
	public int getID(String id) {
		int cnt = mdao.getID(id);
		int result = 0;
		if(cnt > 0) {
			result = 1;
		}else if(cnt == 0) {
			result = -1;
		}
		return result;
	}

	@Override
	public void insertMember(HashMap<String, Object> paramMap) {
		mdao.insertMember(paramMap);
	}

	@Override
	public void updateMember(HashMap<String, Object> paramMap) {
		mdao.updateMember(paramMap);
	}


}
