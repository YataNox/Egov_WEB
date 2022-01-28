package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="ProductDAO")
public interface ProductDAO {

	void getBestList(HashMap<String, Object> paramMap1);

	void getNewList(HashMap<String, Object> paramMap2);

	void getKindList(HashMap<String, Object> paramMap);

}
