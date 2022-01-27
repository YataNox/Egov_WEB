package ezen.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.ProductDAO;
import ezen.shop.service.ProductService;

@Service(value="ProductService")
public class ProductServiceimpl extends EgovAbstractServiceImpl implements ProductService{
	@Resource(name="ProductDAO")
	ProductDAO pdao;
}
