package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="OrderDAO")
public interface OrderDAO {

	void insertOrder(HashMap<String, Object> paramMap);

	void selectOrder(HashMap<String, Object> paramMap);

	void selectOrderResultOne(HashMap<String, Object> paramMap);

}
