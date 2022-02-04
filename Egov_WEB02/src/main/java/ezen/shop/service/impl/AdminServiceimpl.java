package ezen.shop.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.AdminDAO;
import ezen.shop.service.AdminService;

@Service(value="AdminService")
public class AdminServiceimpl extends EgovAbstractServiceImpl implements AdminService{
	@Resource(name="AdminDAO")
	AdminDAO adao;

	@Override
	public void getAdmin(HashMap<String, Object> paramMap) {
		adao.getAdmin(paramMap);
	}

	@Override
	public void getProductList(HashMap<String, Object> paramMap) {
		adao.getProductList(paramMap);
	}
}
