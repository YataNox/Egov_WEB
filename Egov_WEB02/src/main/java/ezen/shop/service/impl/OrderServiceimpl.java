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

	@Override
	public void selectOrderAll(HashMap<String, Object> paramMap) {
		odao.selectOrderAll(paramMap);
	}

	@Override
	public void selectOseqById(HashMap<String, Object> paramMap1) {
		odao.selectOseqById(paramMap1);
	}

	@Override
	public void listOrderByOseq(HashMap<String, Object> paramMap2) {
		odao.listOrderByOseq(paramMap2);
	}

	@Override
	public void selectOrderDetailList(HashMap<String, Object> paramMap) {
		odao.selectOrderDetailList(paramMap);
	}
	
	public void getOseqOrder(HashMap<String, Object> paramMap) {
		odao.getOseqOrder(paramMap);
	}
}
