package ezen.main.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.main.dao.MainDao;
import ezen.main.dto.MemberVO;
import ezen.main.service.MainService;

@Service(value="MainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService{
	
	@Resource(name="MainDao")	
	MainDao mdao;
	
	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}
}
