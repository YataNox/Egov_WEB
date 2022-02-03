package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="CartDAO")
public interface CartDAO {

	void insertCart(HashMap<String, Object> paramMap);

	void selectCart(HashMap<String, Object> paramMap);

	void deleteCart(HashMap<String, Object> paramMap);

}
