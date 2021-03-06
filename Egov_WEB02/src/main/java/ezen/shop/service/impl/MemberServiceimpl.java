package ezen.shop.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.MemberDAO;
import ezen.shop.service.MemberService;

@Service(value="MemberService")
public class MemberServiceimpl extends EgovAbstractServiceImpl implements MemberService{
	@Resource(name="MemberDAO")
	MemberDAO mdao;

	public void getMember(HashMap<String, Object> paramMap) {
		mdao.getMember(paramMap);
	}

	public void selectAddressByDong(HashMap<String, Object> paramMap) {
		mdao.selectAddressByDong(paramMap);
	}

	public void insertMember(HashMap<String, Object> paramMap) {
		mdao.insertMember(paramMap);
	}

	public void updateMember(HashMap<String, Object> paramMap) {
		mdao.updateMember(paramMap);
	}
}
