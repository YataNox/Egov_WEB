package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="QnaDAO")
public interface QnaDAO {

	void getQnaList(HashMap<String, Object> paramMap);

}
