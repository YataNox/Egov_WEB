package ezen.shop.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.ProductDAO;
import ezen.shop.service.ProductService;

@Service(value="ProductService")
public class ProductServiceimpl extends EgovAbstractServiceImpl implements ProductService{
	@Resource(name="ProductDAO")
	ProductDAO pdao;

	@Override
	public void getBestList(HashMap<String, Object> paramMap1) {
		pdao.getBestList(paramMap1);
	}

	@Override
	public void getNewList(HashMap<String, Object> paramMap2) {
		pdao.getNewList(paramMap2);
	}
}
