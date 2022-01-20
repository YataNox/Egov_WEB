package ezen.main.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.MemberVO;

@Mapper(value="MainDao")
public interface MainDao {

	MemberVO getMember(String id);
	
}
