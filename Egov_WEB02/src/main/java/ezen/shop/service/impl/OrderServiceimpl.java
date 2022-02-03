package ezen.shop.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.OrderDAO;
import ezen.shop.service.OrderService;

@Service(value="OrderService")
public class OrderServiceimpl extends EgovAbstractServiceImpl implements OrderService{
	@Resource(name="OrderDAO")
	OrderDAO odao;

	@Override
	public void insertOrder(HashMap<String, Object> paramMap) {
		odao.insertOrder(paramMap);
	}

	@Override
	public void selectOrder(HashMap<String, Object> paramMap) {
		odao.selectOrder(paramMap);
	}

	@Override
	public void selectOrderResultOne(HashMap<String, Object> paramMap) {
		odao.selectOrderResultOne(paramMap);
	}
}
