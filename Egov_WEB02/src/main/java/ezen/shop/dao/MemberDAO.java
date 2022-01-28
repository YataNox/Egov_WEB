package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="MemberDAO")
public interface MemberDAO {

	void getMember(HashMap<String, Object> paramMap);

}
