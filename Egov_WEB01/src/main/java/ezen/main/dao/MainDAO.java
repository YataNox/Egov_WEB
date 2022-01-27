package ezen.main.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.MemberVO;

@Mapper(value="MainDAO")
public interface MainDAO {
	/* MemberVO getMember(String id); */
	/* void getMember(TransferVO container); */
	void getMember(HashMap<String, Object> paramMap);

	int getID(String id);

	void insertMember(HashMap<String, Object> paramMap);

	void updateMember(HashMap<String, Object> paramMap);

}
